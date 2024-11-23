package com.sopkathon.team2.data.datasourceimpl.local

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.core.content.edit
import com.sopkathon.team2.data.datasource.local.ImageLocalDataSource

class ImageLocalDataSourceImpl(
    context: Context
) : ImageLocalDataSource {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override var imageUri: Uri?
        get() = sharedPreferences.getString(IMAGE, null)?.let { Uri.parse(it) }
        set(value) = sharedPreferences.edit { putString(IMAGE, value.toString()) }

    companion object {
        private const val PREFERENCES_NAME = "image_preferences"
        private const val IMAGE = "image"
    }
}
