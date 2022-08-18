package com.github.janruz.spacexapp.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.github.janruz.spacexapp.data.models.Rocket
import com.squareup.moshi.JsonAdapter
import java.io.IOException
import java.nio.charset.StandardCharsets
import javax.inject.Inject

interface RocketsLocalStorage {
    fun getRockets(): Result<List<Rocket>>
    fun saveRockets(rockets: List<Rocket>): Result<Unit>
}

class RocketsLocalStorageImpl @Inject constructor(
    private val context: Context,
    private val jsonAdapter: JsonAdapter<List<Rocket>>
) : RocketsLocalStorage {

    override fun getRockets(): Result<List<Rocket>> {
        val files = context.filesDir.listFiles()
        val bytes = files?.singleOrNull { it.name == CACHE_FILE_NAME }?.readBytes()

        return Result.success(
            if (bytes == null) {
                emptyList()
            } else {
                val savedJson = String(bytes, StandardCharsets.UTF_8)
                jsonAdapter.fromJson(savedJson) ?: emptyList()
            }
        )
    }

    override fun saveRockets(rockets: List<Rocket>): Result<Unit> {
        return try {
            context.openFileOutput(CACHE_FILE_NAME, MODE_PRIVATE).use { outputStream ->
                outputStream.write(jsonAdapter.toJson(rockets).toByteArray())
            }
            Result.success(Unit)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }

    companion object {
        private const val CACHE_FILE_NAME = "rockets-cache.json"
    }
}