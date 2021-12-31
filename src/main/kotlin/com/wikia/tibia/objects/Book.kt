package com.wikia.tibia.objects

import com.wikia.tibia.enums.BookType
import com.wikia.tibia.enums.Status
import com.wikia.tibia.utils.ObjectUtils.isEmpty
import org.slf4j.LoggerFactory

data class Book(
  val name: String,
  val implemented: String?,
  val notes: String,
  val history: String,
  val status: Status,
  val booktype: BookType,
  val booktype2: BookType,
  val booktype3: BookType,
  val booktype4: BookType,
  val booktype5: BookType,
  val booktype6: BookType,
  val booktype7: BookType,
  val booktype8: BookType,
  val title: String?,
  val pagename: String?,
  val location: String?,
  val location2: String,
  val location3: String,
  val location4: String,
  val location5: String,
  val location6: String,
  val location7: String,
  val location8: String,
  val blurb: String?,
  val author: String?,
  val returnpage: String,
  val returnpage2: String,
  val returnpage3: String,
  val returnpage4: String,
  val returnpage5: String,
  val returnpage6: String,
  val returnpage7: String,
  val returnpage8: String,
  val returnpage9: String,
  val returnpage10: String,
  val returnpage11: String,
  val returnpage12: String,
  val returnpage13: String,
  val returnpage14: String,
  val returnpage15: String,
  val returnpage16: String,
  val prevbook: String,
  val nextbook: String,
  val relatedpages: String,
  val text: String,
  val text2: String,
  val text3: String,
  val text4: String,
  val text5: String,
  val text6: String,
  val text7: String,
  val text8: String,
  val implemented2: String,
  val implemented3: String,
  val implemented4: String,
  val implemented5: String,
  val implemented6: String,
  val implemented7: String,
  val implemented8: String
) : WikiObject() {

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
