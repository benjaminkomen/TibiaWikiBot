package com.wikia.tibia.repositories

import com.wikia.tibia.gateways.WikiObjectGateway
import com.wikia.tibia.objects.Creature
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mock
import kotlin.reflect.full.findParameterByName

class CreatureRepositoryTest {

    @Mock
    private lateinit var creatureGateway: WikiObjectGateway<Creature>
    private lateinit var creatureRepository: CreatureRepository

    @Before
    fun setup() {
//        creatureGateway = Mockito.mock(WikiObjectGateway::class.java)
        creatureRepository = CreatureRepository()
        val field = ::creatureRepository.findParameterByName("wikiObjectGateway")
    }

    @Ignore // TODO finish writing this test (inject wikiObjectGateway with reflection?)
    @Test
    fun `should get empty list of LootWrapper objects`() {
        val someJson = """[]"""
//        `when`(lootGateway.getLoot(true)).thenReturn(Try.success(someJson))

        val result = creatureRepository.getWikiObjects()

        MatcherAssert.assertThat(result.isSuccess, Matchers.`is`(true))
        MatcherAssert.assertThat(result.get().size, Matchers.`is`(0))
    }
}