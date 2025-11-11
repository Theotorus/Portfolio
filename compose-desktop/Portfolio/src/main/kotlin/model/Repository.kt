package mc.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.platform.Font

object Repository {
    var currentFont = mutableStateOf(Font("font/firacode-regular.ttf"))
    fun buildText(list: List<String>, chip: String): String{
        var formattedText = ""
        for(element in list){
            val builder = StringBuilder()
            builder.append("$chip  ")
            builder.append(element)
            builder.append("\n")
            formattedText += builder.toString()
        }
        return formattedText
    }
}