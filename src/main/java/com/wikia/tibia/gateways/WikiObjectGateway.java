package com.wikia.tibia.gateways;

import com.wikia.tibia.enums.Contracts;
import com.wikia.tibia.http.Header;
import com.wikia.tibia.http.Request;
import com.wikia.tibia.objects.WikiObject;

public abstract class WikiObjectGateway<T extends WikiObject> {

    private Request request;
    private Contracts contracts;

    protected WikiObjectGateway(Contracts contracts) {
        this.contracts = contracts;
        request = new Request();
    }

    public String getWikiObjects() {
        return getWikiObjects(false);
    }

    public String getWikiObjects(boolean expand) {
        return request.get(contracts.getDescription() + "?expand=" + expand);
    }

    public String saveWikiObject(T wikiObject, String editSummary, boolean dryRun) {
        final Header header = Header.builder()
                .name("X-WIKI-Edit-Summary")
                .value(editSummary)
                .build();

        return request.put(contracts.getDescription(), wikiObject, header, dryRun);
    }

    public String getWikiObject(String pageName) {
        return request.get(contracts.getDescription() + "/" + pageName);
    }
}
