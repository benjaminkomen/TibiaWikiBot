package com.wikia.tibia.objects;

import com.wikia.tibia.enums.Article;
import com.wikia.tibia.enums.Status;

public class WikiObject {

    private String name;
    private Article article;
    private String actualname;
    private String plural;
    private String implemented;
    private String notes;
    private String history;
    private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getActualname() {
        return actualname;
    }

    public void setActualname(String actualname) {
        this.actualname = actualname;
    }

    public String getPlural() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }

    public String getImplemented() {
        return implemented;
    }

    public void setImplemented(String implemented) {
        this.implemented = implemented;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}