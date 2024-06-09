package com.kopai.shinkansen.view.custom

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.kopai.shinkansen.R

class PasswordEditText
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
    ) : AppCompatEditText(context, attrs) {
        override fun onTextChanged(
            s: CharSequence,
            start: Int,
            before: Int,
            count: Int,
        ) {
            if (s.toString().length < 8) {
                setError(resources.getString(R.string.password_length_error), null)
            } else {
                error = null
            }
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        hint = "Password"
        context.apply {
            background = ContextCompat.getDrawable(this, R.drawable.custom_form_input)
            setTextColor(ContextCompat.getColor(this, R.color.primary))
            setHintTextColor(ContextCompat.getColor(this, R.color.secondary))
        }
        maxLines = 1
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }
    }
