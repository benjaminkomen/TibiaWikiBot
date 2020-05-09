package com.wikia.tibia.gateways;

import com.wikia.tibia.objects.Book;

public class BookGateway extends WikiObjectGateway<Book> {

    public BookGateway() {
        super(Contracts.BOOKS);
    }
}
