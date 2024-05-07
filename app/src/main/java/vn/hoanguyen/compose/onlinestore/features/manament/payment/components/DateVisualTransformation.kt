package vn.hoanguyen.compose.onlinestore.features.manament.payment.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val transformedText = StringBuilder().apply {
            text.text.forEachIndexed { index, c ->
                append(c)
                if (index == 1) append('/')
            }
        }.toString()

        return TransformedText(
            text = AnnotatedString(text = transformedText),
            offsetMapping = MyOffsetMapping()
        )
    }
}

class MyOffsetMapping : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        if (offset >= 2) return offset + 1
        return offset
    }

    override fun transformedToOriginal(offset: Int): Int {
        if (offset >= 2) return offset - 1
        return offset
    }
}