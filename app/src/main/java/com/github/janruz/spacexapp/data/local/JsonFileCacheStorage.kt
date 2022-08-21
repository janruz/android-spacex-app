package com.github.janruz.spacexapp.data.local

import android.content.Context
import com.squareup.moshi.JsonAdapter
import java.io.IOException
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class JsonFileCacheStorage<T> @Inject constructor(
    private val context: Context,
    private val jsonAdapter: JsonAdapter<T>
): FileCacheStorage<T> {

    override fun get(fileName: String): Result<T?> {
        val files = context.filesDir.listFiles()
        val bytes = files?.singleOrNull { it.name == fileName }?.readBytes()

        return Result.success(
            if (bytes == null) {
                null
            } else {
                val savedJson = String(bytes, StandardCharsets.UTF_8)
                jsonAdapter.fromJson(savedJson)
            }
        )
    }

    override fun save(data: T, fileName: String): Result<Unit> {
        return try {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
                outputStream.write(jsonAdapter.toJson(data).toByteArray())
            }
            Result.success(Unit)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
}