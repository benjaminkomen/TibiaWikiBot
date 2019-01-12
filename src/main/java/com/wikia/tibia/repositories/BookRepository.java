package com.wikia.tibia.repositories;

import com.wikia.tibia.gateways.BookGateway;
import com.wikia.tibia.objects.Book;

public class BookRepository extends WikiObjectRepository {

    public BookRepository() {
        super(Book.class, new BookGateway());
    }
}
