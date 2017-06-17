package com.wikia.tibia.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BookType {

    BOOK_ATLAS("Book (Atlas)"),
    BOOK_BLACK("Book (Black)"),
    BOOK_BLUE("Book (Blue)"),
    BOOK_BROWN("Book (Brown)"),
    BOOK_BROWN_SQUARE("Book (Brown Square)"),
    BOOK_BROWN_THIN("Book (Brown Thin)"),
    BOOK_FAT_GREEN("Book (Fat Green)"),
    BOOK_GEMMED("Book (Gemmed)"),
    BOOK_GREEN("Book (Green)"),
    BOOK_GREY("Book (Grey)"),
    BOOK_ORANGE("Book (Orange)"),
    BOOK_RED("Book (Red)"),
    BOTANY_ALMANACH("Botany Almanach"),
    DOCUMENT_CERTIFICATE("Document (Certificate)"),
    LARGE_BOOK("Large Book"),
    OLD_PIECE_OF_PAPER("Old Piece of Paper"),
    PAPER("Paper"),
    PARCHMENT("Parchment"),
    PARCHMENT_YELLOW("Parchment (Yellow)"),
    PARCHMENT_WHITE("Parchment (White)"),
    SEASHELL_BOOK_BLUE("Seashell Book (Library Blue)"),
    SEASHELL_BOOK_GREEN("Seashell Book (Library Green)"),
    SEASHELL_BOOK_YELLOW("Seashell Book (Library Yellow)"),
    STAMPED_LETTER("Stamped Letter"),
    WRINKLED_PARCHMENT("Wrinkled Parchment"),
    ;

    private String description;

    BookType(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
