package com.wikia.tibia.objects

import com.wikia.tibia.enums.BookType
import com.wikia.tibia.enums.Status
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Book( 
        private val name: String,
        private val implemented: String?,
        private val notes: String,
        private val history: String,
        private val status: Status,
        private val booktype: BookType,
        private val booktype2: BookType,
        private val booktype3: BookType,
        private val booktype4: BookType,
        private val booktype5: BookType,
        private val booktype6: BookType,
        private val booktype7: BookType,
        private val booktype8: BookType,
        private val title: String?,
        private val pagename: String?,
        private val location: String?,
        private val location2: String,
        private val location3: String,
        private val location4: String,
        private val location5: String,
        private val location6: String,
        private val location7: String,
        private val location8: String,
        private val blurb: String?,
        private val author: String?,
        private val returnpage: String,
        private val returnpage2: String,
        private val returnpage3: String,
        private val returnpage4: String,
        private val returnpage5: String,
        private val returnpage6: String,
        private val returnpage7: String,
        private val returnpage8: String,
        private val returnpage9: String,
        private val returnpage10: String,
        private val returnpage11: String,
        private val returnpage12: String,
        private val returnpage13: String,
        private val returnpage14: String,
        private val returnpage15: String,
        private val returnpage16: String,
        private val prevbook: String,
        private val nextbook: String,
        private val relatedpages: String,
        private val text: String,
        private val text2: String,
        private val text3: String,
        private val text4: String,
        private val text5: String,
        private val text6: String,
        private val text7: String,
        private val text8: String,
        private val implemented2: String,
        private val implemented3: String,
        private val implemented4: String,
        private val implemented5: String,
        private val implemented6: String,
        private val implemented7: String,
        private val implemented8: String
): WikiObject() {

    override fun setDefaultValues() {
        if (isEmpty(implemented)) {
            this.copy(implemented = "?")
        }
        if (isEmpty(booktype)) {
            logger.warn("Book '{}' has no booktype set", name)
        }
        if (isEmpty(title)) {
            this.copy(title = "?")
            logger.warn("Book '{}' has no title set", name)
        }
        if (isEmpty(pagename)) {
            this.copy(pagename = "?")
            logger.warn("Book '{}' has no pagename set", name)
        }
        if (isEmpty(location)) {
            this.copy(location = "?")
        }
        if (isEmpty(blurb)) {
            this.copy(blurb = "?")
        }
        if (isEmpty(author)) {
            this.copy(author = "?")
        }
        if (isEmpty(text)) {
            logger.warn("Book '{}' has no text set", name)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Book::class.java)
    }
}