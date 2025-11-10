package mc.model

import androidx.compose.ui.graphics.Color
import mc.ui.viewmodel.MainViewModel
import java.time.LocalDate

data class Project(
    val title: String,
    val titleColor: Color,
    val creationDate: LocalDate,
    val description: String,
    val linkedTechnologies: List<String>,
    val keyFunctionalities: List<String>,
    val whatILearn: List<String>,
    val images: List<Pair<String, PictureOrientation>>,
    val backgroundColor: Color,
    val otherTextColor: Color,
    val remarks: String
)