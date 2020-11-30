package com.wikia.tibia.repositories

import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Creature
import io.vavr.control.Try
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.lang.reflect.Field

class CreatureRepositoryTest {

    @Mock
    private lateinit var creatureGateway: WikiObjectGateway<Creature>
    private lateinit var creatureRepository: CreatureRepository

    @Suppress("UNCHECKED_CAST")
    @Before
    fun setup() {
        creatureGateway = mock(WikiObjectGateway::class.java) as WikiObjectGateway<Creature>
        creatureRepository = CreatureRepository()
        `inject mocked gateway`(creatureRepository, creatureGateway)
    }

    @Test
    fun `should get empty list of Creature objects`() {
        val someJson =
            """[]"""
        `when`(creatureGateway.getWikiObjects(true)).thenReturn(Try.success(someJson))

        val result = creatureRepository.getWikiObjects()

        MatcherAssert.assertThat(result.isSuccess, Matchers.`is`(true))
        MatcherAssert.assertThat(result.get().size, Matchers.`is`(0))
    }

    @Test
    fun `should get list of two Creature objects`() {
        val someJson = getResourceAsText("/creatures.json")
        `when`(creatureGateway.getWikiObjects(true)).thenReturn(Try.success(someJson))

        val result = creatureRepository.getWikiObjects()

        MatcherAssert.assertThat(result.isSuccess, Matchers.`is`(true))
        MatcherAssert.assertThat(result.get().size, Matchers.`is`(2))
    }

    // TODO it would be cool to rewrite this to Kotlin some time
    private fun `inject mocked gateway`(creatureRepository: CreatureRepository, creatureGateway: WikiObjectGateway<Creature>) {
        val field: Field = creatureRepository.javaClass.superclass.getDeclaredField("wikiObjectGateway")
        field.trySetAccessible()
        field.set(creatureRepository, creatureGateway)
    }

    private fun getResourceAsText(path: String): String? {
        return object {}.javaClass.getResource(path)?.readText()
    }
}
