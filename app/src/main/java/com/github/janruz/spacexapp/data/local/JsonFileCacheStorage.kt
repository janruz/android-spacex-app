package com.github.janruz.spacexapp.data.local

import android.content.Context
import com.github.janruz.spacexapp.utilities.runCatchingExceptions
import com.squareup.moshi.JsonAdapter
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import kotlin.reflect.KClass

class JsonFileCacheStorage<T> @Inject constructor(
    private val context: Context,
    private val jsonAdapter: JsonAdapter<T>
): FileCacheStorage<T> {

    override fun get(fileName: String): Result<T?> = runCatchingExceptions(
        IllegalArgumentException::class,
        FileNotFoundException::class
    ) {
        if(fileName.isEmpty()) {
            throw IllegalArgumentException("File name is empty.")
        }

        val files = context.filesDir.listFiles()
        val bytes = files?.singleOrNull { it.name == "$fileName.json" }?.readBytes()

        if (bytes == null) {
            throw FileNotFoundException("Nothing stored in the cache.")
        } else {
            val savedJson = String(bytes, StandardCharsets.UTF_8)
            jsonAdapter.fromJson(savedJson)
        }
    }

    override fun save(data: T, fileName: String): Result<Unit> = runCatchingExceptions(
        IllegalArgumentException::class,
        IOException::class
    ) {
        if(fileName.isEmpty()) {
            throw IllegalArgumentException("File name is empty.")
        }

        context.openFileOutput("$fileName.json", Context.MODE_PRIVATE).use { outputStream ->
            outputStream.write(jsonAdapter.toJson(data).toByteArray())
        }
    }
}