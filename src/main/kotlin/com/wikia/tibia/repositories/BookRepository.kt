package com.wikia.tibia.repositories

import com.wikia.tibia.enums.Contract
import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Book

class BookRepository : WikiObjectRepository<Book>(
    wikiObjectClass = Book::class.java,
    wikiObjectGateway = WikiObjectGateway(Contract.BOOKS)
)
