package mc.model

import androidx.compose.ui.graphics.Color
import mc.model.PictureOrientation.LANDSCAPE
import mc.model.PictureOrientation.PORTRAIT
import mc.utils.TranslationManager
import java.time.LocalDate
import java.time.LocalDateTime


val projects: List<Project> = listOf(
    Project(
        title = "Cosmic",
        titleColor = Color(74, 35, 165, 255),
        creationDate = LocalDate.of(2024, 5, 15),
        description = "projects.cosmic.description",
        linkedTechnologies = listOf(
            "Android Studio",
            "Kotlin",
            "Jetpack Compose",
            "MVVM",
            "Material 3",
            "Retrofit",
            "Room",
            "Coroutines",
            "Navigation Compose"
        ),
        keyFunctionalities = listOf(
            "projects.cosmic.key_functionalities.0",
            "projects.cosmic.key_functionalities.1",
            "projects.cosmic.key_functionalities.2",
            "projects.cosmic.key_functionalities.3",
            "projects.cosmic.key_functionalities.4"
        ),
        whatILearn = listOf(
            "projects.cosmic.learn.0",
            "projects.cosmic.learn.1",
            "projects.cosmic.learn.2",
            "projects.cosmic.learn.3",
            "projects.cosmic.learn.4",
            "projects.cosmic.learn.5",
            "projects.cosmic.learn.6"
        ),
        images = listOf(
            "drawable/cosmic1.jpg" to PORTRAIT,
            "drawable/cosmic2.jpg" to PORTRAIT,
            "drawable/cosmic3.jpg" to LANDSCAPE
        ),
        backgroundColor = Color(0, 0, 10, 255),
        otherTextColor = Color(45, 46, 205, 255),
        remarks = "",
    ),
    Project(
        title = "In life Among us",
        titleColor = Color(111, 15, 15, 255),
        creationDate = LocalDate.of(2024, 10, 10),
        description = "projects.in_life_among_us.description",
        linkedTechnologies = listOf(
            "Java",
            "Spring",
            "Lombok",
            "Intellij",
            "Android Studio",
            "Kotlin",
            "Jetpack Compose",
            "MVVM",
            "Retrofit",
            "Material 3",
            "Navigation Compose",
            "OkHttp",
            "Moshi",
            "Lifecycle KTX",
            "CameraX",
            "Accompanist Permissions"
        ),
        keyFunctionalities = listOf(
            "projects.in_life_among_us.key_functionalities.0",
            "projects.in_life_among_us.key_functionalities.1",
            "projects.in_life_among_us.key_functionalities.2",
            "projects.in_life_among_us.key_functionalities.3",
            "projects.in_life_among_us.key_functionalities.4",
            "projects.in_life_among_us.key_functionalities.5",
            "projects.in_life_among_us.key_functionalities.6"
        ),
        whatILearn = listOf(
            "projects.in_life_among_us.learn.0",
            "projects.in_life_among_us.learn.1"
        ),
        images = listOf(
            "drawable/in_life_among_us_intro_screen.jpg" to PORTRAIT,
            "drawable/in_life_among_us_task_list_screen.png" to PORTRAIT,
            "drawable/in_life_among_us_map_screen.png" to LANDSCAPE
        ),
        backgroundColor = Color(10, 0, 0, 255),
        otherTextColor = Color(219, 33, 33, 255),
        remarks = "projects.in_life_among_us.remarks",
    ),
    Project(
        title = "Cobria",
        titleColor = Color.Black,
        creationDate = LocalDate.of(2025,7,28),
        description = "projects.cobria.description",
        linkedTechnologies = listOf(
            "Docker",
            "Rider",
            "Webstorm",
            "React",
            "Typescript",
            "Dotnet",
            "C#",
            "React Joyride",
            "Tailwind",
            "Redux"
        ),
        keyFunctionalities = listOf(
            "projects.cobria.key_functionalities.0",
            "projects.cobria.key_functionalities.1",
            "projects.cobria.key_functionalities.2",
            "projects.cobria.key_functionalities.3",
            "projects.cobria.key_functionalities.4"
        ),
        whatILearn = listOf(
            "projects.cobria.learn.0",
            "projects.cobria.learn.1",
        ),
        images = listOf(
            "drawable/cobria_1.png" to LANDSCAPE,
        ),
        backgroundColor = Color(255, 228, 196, 255),
        otherTextColor = Color(253, 138, 58, 255),
        remarks = ""
    )
)