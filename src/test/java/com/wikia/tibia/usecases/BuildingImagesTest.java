package com.wikia.tibia.usecases;

import org.junit.Before;
import org.junit.Test;

public class BuildingImagesTest {

    private BuildingImages target;

    @Before
    public void setup() {
        target = new BuildingImages();
    }

    @Test
    public void testUploadBuildingImagesAndAddImageLink() {
        target.uploadBuildingImagesAndAddImageLink();
    }
}