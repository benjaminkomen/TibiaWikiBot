package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.WikiObjectGateway;
import com.wikia.tibia.jackson.Parser;
import com.wikia.tibia.objects.WikiObject;
import io.vavr.control.Try;

import java.util.List;

public abstract class WikiObjectRepository<T extends Class<? extends WikiObject>, U extends WikiObjectGateway<WikiObject>> {

    private Class<T> wikiObjectClass;
    private U wikiObjectGateway;

    public WikiObjectRepository(Class<T> wikiObjectClass, U wikiObjectGateway) {
        this.wikiObjectClass = wikiObjectClass;
        this.wikiObjectGateway = wikiObjectGateway;
    }

    public Try<List<T>> getWikiObjects() {
        return wikiObjectGateway.getWikiObjects(true)
                .map(json -> Parser.listOneByOne(wikiObjectClass, json, -1));
    }

    public Try<List<T>> getWikiObjects(long limit) {
        return wikiObjectGateway.getWikiObjects(true)
                .map(json -> Parser.listOneByOne(wikiObjectClass, json, limit));
    }

    public Try<List<String>> getWikiObjectsList() {
        return wikiObjectGateway.getWikiObjects(false)
                .map(json -> Parser.list(String.class, json));
    }

    public Try<String> saveWikiObject(WikiObject wikiObject, String editSummary, boolean dryRun) {
        return wikiObjectGateway.saveWikiObject(wikiObject, editSummary, dryRun);
    }

    public Try<Object> getWikiObject(String pageName) {
        return wikiObjectGateway.getWikiObject(pageName)
                .map(json -> Parser.parse(wikiObjectClass, json));
    }
}
