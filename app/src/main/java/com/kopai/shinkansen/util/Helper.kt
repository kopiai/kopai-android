package com.kopai.shinkansen.util

import android.annotation.SuppressLint
import android.app.Application
import android.app.Dialog
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.kopai.shinkansen.R
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

object Helper {
    @SuppressLint("ConstantLocale")
    val currentTimestamp: String =
        SimpleDateFormat(
            "ddMMyySSSSS",
            Locale.getDefault(),
        ).format(System.currentTimeMillis())

    fun dialogInfoBuilder(
        context: Context,
        message: String,
        alignment: Int = Gravity.CENTER,
    ): Dialog {
        val dialog = Dialog(context)
        dialog.setCancelable(false)
        dialog.window!!.apply {
            val params: WindowManager.LayoutParams = this.attributes
            params.width = WindowManager.LayoutParams.MATCH_PARENT
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            attributes.windowAnimations = android.R.transition.fade
            setGravity(Gravity.CENTER)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.setContentView(R.layout.custom_dialog)
        val tvMessage = dialog.findViewById<TextView>(R.id.message)
        when (alignment) {
            Gravity.CENTER -> tvMessage.gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER
            Gravity.START -> tvMessage.gravity = Gravity.CENTER_VERTICAL or Gravity.START
            Gravity.END -> tvMessage.gravity = Gravity.CENTER_VERTICAL or Gravity.END
        }
        tvMessage.text = message
        return dialog
    }

    fun showDialogInfo(
        context: Context,
        message: String,
        alignment: Int = Gravity.CENTER,
    ) {
        val dialog = dialogInfoBuilder(context, message, alignment)
        val btnOk = dialog.findViewById<Button>(R.id.button_ok)
        btnOk.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    fun uriToFile(
        selectedImg: Uri,
        context: Context,
    ): File {
        val contentResolver: ContentResolver = context.contentResolver
        val myFile = createCustomTempFile(context)
        val inputStream = contentResolver.openInputStream(selectedImg) as InputStream
        val outputStream: OutputStream = FileOutputStream(myFile)
        val buf = ByteArray(1024)
        var len: Int
        while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
        outputStream.close()
        inputStream.close()
        return myFile
    }

    fun createFile(application: Application): File {
        val mediaDir =
            application.externalMediaDirs.firstOrNull()?.let {
                File(it, "story").apply { mkdirs() }
            }
        val outputDirectory =
            if (
                mediaDir != null && mediaDir.exists()
            ) {
                mediaDir
            } else {
                application.filesDir
            }

        return File(outputDirectory, "STORY-$currentTimestamp.jpg")
    }

    fun rotateBitmap(
        bitmap: Bitmap,
        isBackCamera: Boolean = false,
    ): Bitmap {
        val matrix = Matrix()
        return if (isBackCamera) {
            matrix.postRotate(0f)
            Bitmap.createBitmap(
                bitmap,
                0,
                0,
                bitmap.width,
                bitmap.height,
                matrix,
                true,
            )
        } else {
            matrix.postRotate(0f)
            Bitmap.createBitmap(
                bitmap,
                0,
                0,
                bitmap.width,
                bitmap.height,
                matrix,
                true,
            )
        }
    }

    fun resizeBitmap(
        bm: Bitmap,
        newWidth: Int,
        newHeight: Int,
    ): Bitmap {
        val width = bm.width
        val height = bm.height
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height

        // init matrix to resize bitmap
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)

        // recreate new bitmap as new defined size
        val resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false)
        bm.recycle()
        return resizedBitmap
    }

    fun rupiah(number: Double): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }
}
