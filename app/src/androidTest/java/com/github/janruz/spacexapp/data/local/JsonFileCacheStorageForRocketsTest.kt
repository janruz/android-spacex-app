package com.github.janruz.spacexapp.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.janruz.spacexapp.data.models.Rocket
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.FileNotFoundException
import java.nio.charset.StandardCharsets

@RunWith(AndroidJUnit4::class)
class JsonFileCacheStorageForRocketsTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var jsonCacheForRockets: JsonFileCacheStorage<List<Rocket>>
    private lateinit var jsonAdapter: JsonAdapter<List<Rocket>>

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val type = Types.newParameterizedType(List::class.java, Rocket::class.java)
        val moshi = Moshi.Builder().build()
        jsonAdapter = moshi.adapter(type)

        jsonCacheForRockets = JsonFileCacheStorage(context, jsonAdapter)
    }

    @After
    fun tearDown() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        context.filesDir.listFiles()?.forEach { file ->
            file.delete()
        }
    }

    @Test
    fun getFromNotExistingFile_shouldReturnFailureOfFileNotFound() {
        val result = jsonCacheForRockets.get(fileName = "file_that_does_not_exist")
        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isInstanceOf(FileNotFoundException::class.java)
    }

    @Test
    fun getWithFileNameBlank_shouldReturnFailureOfIllegalArgumentException() {
        val result = jsonCacheForRockets.get(fileName = "")

        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun saveEmptyListOfRockets_shouldReturnSuccess() {
        val result = jsonCacheForRockets.save(data = emptyList(), fileName = "rockets")
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun getSavedEmptyListOfRockets_shouldReturnSuccessOfEmptyList() {
        val fileName = "rockets"
        jsonCacheForRockets.save(data = emptyList(), fileName)

        val result = jsonCacheForRockets.get(fileName)
        assertThat(result.isSuccess).isTrue()

        val rockets = result.getOrNull()
        assertThat(rockets).isNotNull()
        assertThat(rockets).isEmpty()
    }

    @Test
    fun saveListOfRocketsButLeaveFileNameEmpty_shouldReturnFailureOfIllegalArgumentException() {
        val result = jsonCacheForRockets.save(data = newListOfMockRockets(variant = 0), fileName = "")

        assertThat(result.isFailure).isTrue()
        assertThat(result.exceptionOrNull()).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun saveListOfRocketsAndGetItBack_shouldReturnSuccessOfTheSameList() {
        val fileName = "rockets"
        val saveResult = jsonCacheForRockets.save(data = newListOfMockRockets(variant = 1), fileName)
        assertThat(saveResult.isSuccess).isTrue()

        val getResult = jsonCacheForRockets.get(fileName)
        assertThat(getResult.isSuccess).isTrue()

        val savedRockets = getResult.getOrNull()
        assertThat(savedRockets).isNotNull()
        assertThat(savedRockets).isEqualTo(newListOfMockRockets(variant = 1))
    }

    @Test
    fun saveListOfRockets_shouldSaveItToInternalStorage() {
        val fileName = "rockets"
        jsonCacheForRockets.save(data = newListOfMockRockets(variant = 0), fileName)

        val context = ApplicationProvider.getApplicationContext<Context>()
        val file = context.filesDir.listFiles()?.singleOrNull { it.name == "$fileName.json" }
        assertThat(file).isNotNull()
    }

    @Test
    fun saveListOfRockets_shouldSaveItAsValidJson() {
        val fileName = "rockets"
        jsonCacheForRockets.save(data = newListOfMockRockets(variant = 0), fileName)

        val context = ApplicationProvider.getApplicationContext<Context>()
        val file = context.filesDir.listFiles()?.singleOrNull { it.name == "$fileName.json" }
        assertThat(file).isNotNull()

        val savedJson = String(file!!.readBytes(), StandardCharsets.UTF_8)
        val rockets = jsonAdapter.fromJson(savedJson)
        assertThat(rockets).isEqualTo(newListOfMockRockets(variant = 0))
    }

    @Test
    fun saveListOfRocketsThenSaveAnotherListAndGet_shouldReturnSuccessOfTheLastList() {
        val fileName = "rockets"
        val saveResult0 = jsonCacheForRockets.save(data = newListOfMockRockets(variant = 0), fileName)
        assertThat(saveResult0.isSuccess).isTrue()

        val saveResult1 = jsonCacheForRockets.save(data = newListOfMockRockets(variant = 1), fileName)
        assertThat(saveResult1.isSuccess).isTrue()

        val getResult = jsonCacheForRockets.get(fileName)
        assertThat(getResult.isSuccess).isTrue()

        val rockets = getResult.getOrNull()
        assertThat(rockets).isNotNull()
        assertThat(rockets).isEqualTo(newListOfMockRockets(variant = 1))
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