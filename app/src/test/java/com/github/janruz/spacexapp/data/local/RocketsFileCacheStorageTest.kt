package com.github.janruz.spacexapp.data.local

import com.github.janruz.spacexapp.data.models.Rocket
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import java.io.IOException

class RocketsFileCacheStorageTest {

    private lateinit var instance: RocketsFileCacheStorage
    private lateinit var fakeCache: FakeRocketsCacheStorage

    @Before
    fun setUp() {
        fakeCache = FakeRocketsCacheStorage()
        instance = RocketsFileCacheStorage(fakeCache)
    }

    @Test
    fun `empty cache, should return success of null`() {
        val result = instance.getAll()

        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isNull()
    }

    @Test
    fun `save rockets, should return success of the same list of rockets`() {
        val saveResult = instance.save(newListOfMockRockets(variant = 0))
        assertThat(saveResult.isSuccess).isTrue()

        val getResult = instance.getAll()
        assertThat(getResult.isSuccess).isTrue()
        assertThat(getResult.getOrNull()).isNotNull()

        val savedRockets = getResult.getOrNull()!!
        assertThat(savedRockets).isEqualTo(newListOfMockRockets(variant = 0))
    }

    @Test
    fun `save rockets and then overwrite them with  a new list of rockets, should return success of the new list of rockets`() {
        val saveResult0 = instance.save(newListOfMockRockets(variant = 0))
        assertThat(saveResult0.isSuccess).isTrue()

        val saveResult1 = instance.save(newListOfMockRockets(variant = 1))
        assertThat(saveResult1.isSuccess).isTrue()

        val getResult = instance.getAll()
        assertThat(getResult.isSuccess).isTrue()
        assertThat(getResult.getOrNull()).isNotNull()

        val savedRockets = getResult.getOrNull()!!
        assertThat(savedRockets).isNotEqualTo(newListOfMockRockets(variant = 0))
        assertThat(savedRockets).isEqualTo(newListOfMockRockets(variant = 1))
    }

    @Test
    fun `save rockets and get them back, should get the rockets from the same file`() {
        instance.save(newListOfMockRockets(variant = 0))
        val fileName = fakeCache.passedFileName

        instance.getAll()
        assertThat(fakeCache.passedFileName).isEqualTo(fileName)
    }

    @Test
    fun `save empty list, should return success of empty list of rockets`() {
        instance.save(emptyList())

        val result = instance.getAll()
        assertThat(result.isSuccess).isTrue()

        val savedRockets = result.getOrNull()
        assertThat(savedRockets).isNotNull()
        assertThat(savedRockets).isEmpty()
    }

    @Test
    fun `save rockets and cache storage returns error, should return it as well`() {
        val message = "Something has gone wrong."
        fakeCache.exception = IOException(message)

        val result = instance.save(newListOfMockRockets(variant = 0))

        assertThat(result.isFailure).isTrue()

        val exception = result.exceptionOrNull()

        assertThat(exception).isNotNull()
        assertThat(exception!!).isInstanceOf(IOException::class.java)

        assertThat(exception.message).isEqualTo(message)
    }

    @Test
    fun `get rockets and cache storage returns error, should return it as well`() {
        val message = "Something has gone wrong."
        fakeCache.exception = IOException(message)

        val result = instance.getAll()

        assertThat(result.isFailure).isTrue()

        val exception = result.exceptionOrNull()

        assertThat(exception).isNotNull()
        assertThat(exception!!).isInstanceOf(IOException::class.java)
        assertThat(exception.message).isEqualTo(message)
    }

    private fun newListOfMockRockets(variant: Int): List<Rocket> {
        return when (variant) {
            0 -> {
                listOf(
                    Rocket(
                        id = "5e9d0d95eda69974db09d1ed",
                        name = "Falcon Heavy",
                        active = true,
                        firstFlight = "2018-02-06",
                        description = "With the ability to lift into orbit over 54 metric tons (119,000 lb)--a mass equivalent to a 737 jetliner loaded with passengers, crew, luggage and fuel--Falcon Heavy can lift more than twice the payload of the next closest operational vehicle, the Delta IV Heavy, at one-third the cost.",
                        successRate = 40U,
                        images = listOf(
                            "https://farm5.staticflickr.com/4599/38583829295_581f34dd84_b.jpg"
                        )
                    ),
                    Rocket(
                        id = "5e9d0d95eda69973a809d1ec",
                        name = "Falcon 9",
                        active = true,
                        firstFlight = "2010-06-04",
                        description = "Falcon 9 is a two-stage rocket designed and manufactured by SpaceX for the reliable and safe transport of satellites and the Dragon spacecraft into orbit.",
                        successRate = 60U,
                        images = listOf(
                            "https://farm1.staticflickr.com/929/28787338307_3453a11a77_b.jpg"
                        )
                    ),
                    Rocket(
                        id = "5e9d0d96eda699382d09d1ee",
                        name = "Starship",
                        active = false,
                        firstFlight = "2021-12-01",
                        description = "Starship and Super Heavy Rocket represent a fully reusable transportation system designed to service all Earth orbit needs as well as the Moon and Mars. This two-stage vehicle — composed of the Super Heavy rocket (booster) and Starship (ship) — will eventually replace Falcon 9, Falcon Heavy and Dragon.",
                        successRate = 80U,
                        images = listOf(
                            "https://live.staticflickr.com/65535/48954138962_ee541e6755_b.jpg"
                        )
                    )
                )
            }
            1 -> {
                return listOf(
                    Rocket(
                        id = "329sud2E09jskd9023Lkdsk",
                        name = "Eagle",
                        active = false,
                        firstFlight = "2000-04-08",
                        description = "It is incredible.",
                        successRate = 72U,
                        images = listOf(
                            "https://www.google.com"
                        )
                    ),
                    Rocket(
                        id = "28032dasda983jksdf232",
                        name = "Pigeon",
                        active = true,
                        firstFlight = "2023-04-22",
                        description = "Pretty good.",
                        successRate = 82U,
                        images = listOf(
                            "https://www.stackoverflow.com"
                        )
                    )
                )
            }
            else -> {
                throw IllegalArgumentException("Wrong mock rockets variant number.")
            }
        }
    }
}