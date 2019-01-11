package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.WikiObjectGateway;
import com.wikia.tibia.jackson.Parser;
import com.wikia.tibia.objects.WikiObject;

import java.util.List;

public abstract class WikiObjectRepository<T extends Class<? extends WikiObject>, U extends WikiObjectGateway<WikiObject>> {

    private Class<T> wikiObjectClass;
    private U wikiObjectGateway;

    public WikiObjectRepository(Class<T> wikiObjectClass, U wikiObjectGateway) {
        this.wikiObjectClass = wikiObjectClass;
        this.wikiObjectGateway = wikiObjectGateway;
    }

    public List<T> getWikiObjects() {
        return Parser.listOneByOne(wikiObjectClass, wikiObjectGateway.getWikiObjects(true));
    }

    public List<String> getWikiObjectsList() {
        return Parser.list(String.class, wikiObjectGateway.getWikiObjects(false));
    }

    public String saveWikiObject(WikiObject wikiObject, String editSummary) {
        return wikiObjectGateway.saveWikiObject(wikiObject, editSummary);
    }
}
