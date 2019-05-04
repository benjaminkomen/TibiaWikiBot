package com.wikia.tibia.objects;

import com.wikia.tibia.enums.BookType;
import com.wikia.tibia.enums.Status;
import com.wikia.tibia.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Book extends WikiObject {

    private static final Logger LOG = LoggerFactory.getLogger(Book.class);

    private BookType booktype;
    private BookType booktype2;
    private BookType booktype3;
    private BookType booktype4;
    private BookType booktype5;
    private BookType booktype6;
    private BookType booktype7;
    private BookType booktype8;
    private String title;
    private String pagename;
    private String location;
    private String location2;
    private String location3;
    private String location4;
    private String location5;
    private String location6;
    private String location7;
    private String location8;
    private String blurb;
    private String author;
    private String returnpage;
    private String returnpage2;
    private String returnpage3;
    private String returnpage4;
    private String returnpage5;
    private String returnpage6;
    private String returnpage7;
    private String returnpage8;
    private String returnpage9;
    private String returnpage10;
    private String returnpage11;
    private String returnpage12;
    private String returnpage13;
    private String returnpage14;
    private String returnpage15;
    private String returnpage16;
    private String prevbook;
    private String nextbook;
    private String relatedpages;
    private String text;
    private String text2;
    private String text3;
    private String text4;
    private String text5;
    private String text6;
    private String text7;
    private String text8;
    private String implemented2;
    private String implemented3;
    private String implemented4;
    private String implemented5;
    private String implemented6;
    private String implemented7;
    private String implemented8;

    @Builder
    public Book(String name, String implemented, String notes, String history, Status status, BookType booktype, String title,
                String pagename, String location, String blurb, String author, String returnpage, String prevbook,
                String nextbook, String relatedpages, String text) {
        super(name, null, null, null, implemented, notes, history, status);
        this.booktype = booktype;
        this.booktype2 = null;
        this.booktype3 = null;
        this.booktype4 = null;
        this.booktype5 = null;
        this.booktype6 = null;
        this.booktype7 = null;
        this.booktype8 = null;
        this.title = title;
        this.pagename = pagename;
        this.location = location;
        this.location2 = null;
        this.location3 = null;
        this.location4 = null;
        this.location5 = null;
        this.location6 = null;
        this.location7 = null;
        this.location8 = null;
        this.blurb = blurb;
        this.author = author;
        this.returnpage = returnpage;
        this.returnpage2 = null;
        this.returnpage3 = null;
        this.returnpage4 = null;
        this.returnpage5 = null;
        this.returnpage6 = null;
        this.returnpage7 = null;
        this.returnpage8 = null;
        this.returnpage9 = null;
        this.returnpage10 = null;
        this.returnpage11 = null;
        this.returnpage12 = null;
        this.returnpage13 = null;
        this.returnpage14 = null;
        this.returnpage15 = null;
        this.returnpage16 = null;
        this.prevbook = prevbook;
        this.nextbook = nextbook;
        this.relatedpages = relatedpages;
        this.text = text;
        this.text2 = null;
        this.text3 = null;
        this.text4 = null;
        this.text5 = null;
        this.text6 = null;
        this.text7 = null;
        this.text8 = null;
        this.implemented2 = null;
        this.implemented3 = null;
        this.implemented4 = null;
        this.implemented5 = null;
        this.implemented6 = null;
        this.implemented7 = null;
        this.implemented8 = null;
    }

    @Override
    public String getName() {
        return getPagename();
    }

    @Override
    public void setDefaultValues() {

        if (ObjectUtils.isEmpty(getImplemented())) {
            setImplemented("?");
        }

        if (ObjectUtils.isEmpty(booktype)) {
            LOG.warn("Book '{}' has no booktype set", getName());
        }

        if (ObjectUtils.isEmpty(title)) {
            title = "?";
            LOG.warn("Book '{}' has no title set", getName());
        }

        if (ObjectUtils.isEmpty(pagename)) {
            pagename = "?";
            LOG.warn("Book '{}' has no pagename set", getName());
        }

        if (ObjectUtils.isEmpty(location)) {
            location = "?";
        }

        if (ObjectUtils.isEmpty(blurb)) {
            blurb = "?";
        }

        if (ObjectUtils.isEmpty(author)) {
            author = "?";
        }

        if (ObjectUtils.isEmpty(text)) {
            LOG.warn("Book '{}' has no text set", getName());
        }
    }
}