package com.wikia.tibia.usecases;

import com.wikia.tibia.factories.ArticleFactory;
import com.wikia.tibia.objects.Building;
import com.wikia.tibia.objects.TibiaWikiBot;
import com.wikia.tibia.repositories.InputRepository;
import com.wikia.tibia.repositories.WikiArticleRepository;
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.actions.queries.CategoryMembersSimple;
import one.util.streamex.StreamEx;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Update 266 building pages with images.
 */
public class BuildingImages {

    private static final Logger log = LoggerFactory.getLogger(BuildingImages.class);
    private static final String FILE_UPLOAD_EDIT_SUMMARY = "House image made by [[User:Vapaus|Vapaus]] using the TibiaML map.";

    private InputRepository inputRepository = new InputRepository();
    private WikiArticleRepository wikiArticleRepository;
    private TibiaWikiBot tibiaWikiBot = new TibiaWikiBot();

    private static final String CATEGORY_LISTS = "Lists";
    private static final String CATEGORY_BUILDINGS = "Player-Ownable Buildings";
    private static final boolean DEBUG_ENABLED = true;

    public void uploadBuildingImagesAndAddImageLink() {
        tibiaWikiBot.login();
        wikiArticleRepository = new WikiArticleRepository(tibiaWikiBot);

        final List<Path> pathsToHouseImages = inputRepository.getFolderContents("temp/house_images");
        final Map<Integer, Path> pathsByHouseImageIds = extractHouseImageIds(pathsToHouseImages);

        final List<Building> allBuildings = getAllBuildings();

        final Map<Building, Path> relevantBuildings = allBuildings.stream()
                .filter(b -> pathsByHouseImageIds.keySet().contains(b.getHouseid()))
                .collect(Collectors.toMap(Function.identity(),
                        b -> pathsByHouseImageIds.get(b.getHouseid())
                ));

        relevantBuildings.forEach((building, pathToHouseImage) -> {
            this.uploadHouseImage(building, pathToHouseImage);
            pauseForABit();
            this.editWikiWithModifiedBuilding(this.modifyBuilding(building));
            pauseForABit();
        });

        log.info("Finished uploading 266 images and editing 266 pages with a link to the new image.");
    }

    private void editWikiWithModifiedBuilding(Building building) {
        final Article article = ArticleFactory.createArticle(tibiaWikiBot, building);

        if (!DEBUG_ENABLED) {
            article.save("[bot] Adding link to new house image, made by User:Vapaus.");
            log.info("Article {} is modified.", building.getName());
        }
        log.info("Article {} not modified, debug is enabled.", building.getName());
    }

    private Map<Integer, Path> extractHouseImageIds(List<Path> pathsToHouseImages) {
        return pathsToHouseImages.stream()
                .collect(Collectors.toMap(p -> Integer.valueOf(FilenameUtils.removeExtension(p.getFileName().toString())),
                        Function.identity())
                );
    }

    private void uploadHouseImage(Building building, Path path) {
        if (!DEBUG_ENABLED) {
            final String response = wikiArticleRepository.uploadFile(building.getName(), FILE_UPLOAD_EDIT_SUMMARY, path);
            log.info("After attempting to upload image the following response was recorded: {}", response);
        }
        log.info("File {} not uploaded, debug is enabled.", building.getName());
    }

    private Building modifyBuilding(Building building) {
        if (building.getImage() == null || "".equals(building.getImage())) {
            building.setImage("<gallery>\nFile:" + building.getName() + ".png\n</gallery>");
        } else {
            log.error("Building {} does not have an empty image parameter!", building.getName());
        }
        return building;
    }

    private List<Building> getAllBuildings() {
        CategoryMembersSimple pagesInBuildingsCategory = wikiArticleRepository.getMembersFromCategory(CATEGORY_BUILDINGS);
        CategoryMembersSimple pagesInListsCategory = wikiArticleRepository.getMembersFromCategory(CATEGORY_LISTS);

        long startTime = System.nanoTime();
        List<String> buildingsCategory = new ArrayList<>();
        for (String pageName : pagesInBuildingsCategory) {
            buildingsCategory.add(pageName);
        }

        List<String> listsCategory = new ArrayList<>();
        for (String pageName : pagesInListsCategory) {
            listsCategory.add(pageName);
        }

        List<String> pagesInBuildingsCategoryButNotLists = buildingsCategory.stream()
                .filter(page -> !listsCategory.contains(page))
                .collect(Collectors.toList());

        try {
            return StreamEx.ofSubLists(pagesInBuildingsCategoryButNotLists, 250)
                    .flatMap(pageNames -> wikiArticleRepository.getWikiObjects(pageNames).stream())
                    .filter(Building.class::isInstance)
                    .map(Building.class::cast)
                    .collect(Collectors.toList());
        } finally {
            long difference = System.nanoTime() - startTime;

            if (log.isInfoEnabled()) {
                log.info(String.format("%s buildings were obtained in %d seconds.",
                        pagesInBuildingsCategoryButNotLists.size(), TimeUnit.NANOSECONDS.toSeconds(difference)));
            }
        }
    }

    private void pauseForABit() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
