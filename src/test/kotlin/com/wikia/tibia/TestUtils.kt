package com.wikia.tibia

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue

infix fun <T> T.shouldBe(actual: T?) = assertEquals(actual, this)

infix fun <T> T.shouldNotBe(actual: T?) = assertNotEquals(actual, this)

infix fun String.shouldStartWith(actual: String) = assertTrue(this.startsWith(actual))
