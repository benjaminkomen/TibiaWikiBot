package com.wikia.tibia.gateways

import com.wikia.tibia.enums.Contracts
import com.wikia.tibia.objects.Book

class BookGateway : WikiObjectGateway<Book?>(Contracts.BOOKS)