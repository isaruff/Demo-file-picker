package com.isaruff.demo_file_picker

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.core.content.ContentResolverCompat
import java.io.File


fun Uri.getFile(): File? {
    return this.path?.let {
        File(it)
    }
}

fun Uri.getFileName(context: Context): String? {
    var name: String? = null
    val cursor = ContentResolverCompat.query(
        context.contentResolver ?: return null,
        this,
        null,
        null,
        null,
        null,
        null
    ) ?: return null

    cursor.apply {
        val columnIndex = getColumnIndex(OpenableColumns.DISPLAY_NAME)
        return if (moveToFirst() && columnIndex >= 0) {
            name = this.getString(columnIndex)
            close()
            name
        } else {
            close()
            name
        }

    }


}