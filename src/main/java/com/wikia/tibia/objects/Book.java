package com.wikia.tibia.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wikia.tibia.enums.BookType;
import com.wikia.tibia.enums.Grade;
import com.wikia.tibia.enums.YesNo;

@JsonIgnoreProperties({ "objectType" })
public class Book extends WikiObject {

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

    public Book() {
        // constructor for Jackson
    }

    public BookType getBooktype() {
        return booktype;
    }

    public void setBooktype(BookType booktype) {
        this.booktype = booktype;
    }

    public BookType getBooktype2() {
        return booktype2;
    }

    public void setBooktype2(BookType booktype2) {
        this.booktype2 = booktype2;
    }

    public BookType getBooktype3() {
        return booktype3;
    }

    public void setBooktype3(BookType booktype3) {
        this.booktype3 = booktype3;
    }

    public BookType getBooktype4() {
        return booktype4;
    }

    public void setBooktype4(BookType booktype4) {
        this.booktype4 = booktype4;
    }

    public BookType getBooktype5() {
        return booktype5;
    }

    public void setBooktype5(BookType booktype5) {
        this.booktype5 = booktype5;
    }

    public BookType getBooktype6() {
        return booktype6;
    }

    public void setBooktype6(BookType booktype6) {
        this.booktype6 = booktype6;
    }

    public BookType getBooktype7() {
        return booktype7;
    }

    public void setBooktype7(BookType booktype7) {
        this.booktype7 = booktype7;
    }

    public BookType getBooktype8() {
        return booktype8;
    }

    public void setBooktype8(BookType booktype8) {
        this.booktype8 = booktype8;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }

    public String getLocation3() {
        return location3;
    }

    public void setLocation3(String location3) {
        this.location3 = location3;
    }

    public String getLocation4() {
        return location4;
    }

    public void setLocation4(String location4) {
        this.location4 = location4;
    }

    public String getLocation5() {
        return location5;
    }

    public void setLocation5(String location5) {
        this.location5 = location5;
    }

    public String getLocation6() {
        return location6;
    }

    public void setLocation6(String location6) {
        this.location6 = location6;
    }

    public String getLocation7() {
        return location7;
    }

    public void setLocation7(String location7) {
        this.location7 = location7;
    }

    public String getLocation8() {
        return location8;
    }

    public void setLocation8(String location8) {
        this.location8 = location8;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReturnpage() {
        return returnpage;
    }

    public void setReturnpage(String returnpage) {
        this.returnpage = returnpage;
    }

    public String getReturnpage2() {
        return returnpage2;
    }

    public void setReturnpage2(String returnpage2) {
        this.returnpage2 = returnpage2;
    }

    public String getReturnpage3() {
        return returnpage3;
    }

    public void setReturnpage3(String returnpage3) {
        this.returnpage3 = returnpage3;
    }

    public String getReturnpage4() {
        return returnpage4;
    }

    public void setReturnpage4(String returnpage4) {
        this.returnpage4 = returnpage4;
    }

    public String getReturnpage5() {
        return returnpage5;
    }

    public void setReturnpage5(String returnpage5) {
        this.returnpage5 = returnpage5;
    }

    public String getReturnpage6() {
        return returnpage6;
    }

    public void setReturnpage6(String returnpage6) {
        this.returnpage6 = returnpage6;
    }

    public String getReturnpage7() {
        return returnpage7;
    }

    public void setReturnpage7(String returnpage7) {
        this.returnpage7 = returnpage7;
    }

    public String getReturnpage8() {
        return returnpage8;
    }

    public void setReturnpage8(String returnpage8) {
        this.returnpage8 = returnpage8;
    }

    public String getReturnpage9() {
        return returnpage9;
    }

    public void setReturnpage9(String returnpage9) {
        this.returnpage9 = returnpage9;
    }

    public String getReturnpage10() {
        return returnpage10;
    }

    public void setReturnpage10(String returnpage10) {
        this.returnpage10 = returnpage10;
    }

    public String getReturnpage11() {
        return returnpage11;
    }

    public void setReturnpage11(String returnpage11) {
        this.returnpage11 = returnpage11;
    }

    public String getReturnpage12() {
        return returnpage12;
    }

    public void setReturnpage12(String returnpage12) {
        this.returnpage12 = returnpage12;
    }

    public String getReturnpage13() {
        return returnpage13;
    }

    public void setReturnpage13(String returnpage13) {
        this.returnpage13 = returnpage13;
    }

    public String getReturnpage14() {
        return returnpage14;
    }

    public void setReturnpage14(String returnpage14) {
        this.returnpage14 = returnpage14;
    }

    public String getReturnpage15() {
        return returnpage15;
    }

    public void setReturnpage15(String returnpage15) {
        this.returnpage15 = returnpage15;
    }

    public String getReturnpage16() {
        return returnpage16;
    }

    public void setReturnpage16(String returnpage16) {
        this.returnpage16 = returnpage16;
    }

    public String getPrevbook() {
        return prevbook;
    }

    public void setPrevbook(String prevbook) {
        this.prevbook = prevbook;
    }

    public String getNextbook() {
        return nextbook;
    }

    public void setNextbook(String nextbook) {
        this.nextbook = nextbook;
    }

    public String getRelatedpages() {
        return relatedpages;
    }

    public void setRelatedpages(String relatedpages) {
        this.relatedpages = relatedpages;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }

    public String getText5() {
        return text5;
    }

    public void setText5(String text5) {
        this.text5 = text5;
    }

    public String getText6() {
        return text6;
    }

    public void setText6(String text6) {
        this.text6 = text6;
    }

    public String getText7() {
        return text7;
    }

    public void setText7(String text7) {
        this.text7 = text7;
    }

    public String getText8() {
        return text8;
    }

    public void setText8(String text8) {
        this.text8 = text8;
    }

    public String getImplemented2() {
        return implemented2;
    }

    public void setImplemented2(String implemented2) {
        this.implemented2 = implemented2;
    }

    public String getImplemented3() {
        return implemented3;
    }

    public void setImplemented3(String implemented3) {
        this.implemented3 = implemented3;
    }

    public String getImplemented4() {
        return implemented4;
    }

    public void setImplemented4(String implemented4) {
        this.implemented4 = implemented4;
    }

    public String getImplemented5() {
        return implemented5;
    }

    public void setImplemented5(String implemented5) {
        this.implemented5 = implemented5;
    }

    public String getImplemented6() {
        return implemented6;
    }

    public void setImplemented6(String implemented6) {
        this.implemented6 = implemented6;
    }

    public String getImplemented7() {
        return implemented7;
    }

    public void setImplemented7(String implemented7) {
        this.implemented7 = implemented7;
    }

    public String getImplemented8() {
        return implemented8;
    }

    public void setImplemented8(String implemented8) {
        this.implemented8 = implemented8;
    }
}