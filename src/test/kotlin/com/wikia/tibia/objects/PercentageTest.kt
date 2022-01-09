package com.wikia.tibia.objects

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test

class PercentageTest {

  @Test
  fun `should construct Percentage successfully`() {
    assertThat(Percentage.of("100").toString(), `is`("100"))
    assertThat(Percentage.of("100%").toString(), `is`("100"))
    assertThat(Percentage.of("100%?").toString(), `is`("100"))
    assertThat(Percentage.of("<99%?").toString(), `is`("99"))
    assertThat(Percentage.UNKNOWN.toString(), `is`("100"))
    assertThat(Percentage.EMPTY.toString(), `is`(""))
    assertThat(Percentage.of(100).toString(), `is`("100"))
  }
}
