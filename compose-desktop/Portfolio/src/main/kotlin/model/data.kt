package mc.model

import androidx.compose.ui.graphics.Color
import mc.model.PictureOrientation.LANDSCAPE
import mc.model.PictureOrientation.PORTRAIT
import java.time.LocalDate
import java.time.LocalDateTime


val projects: List<Project> = listOf(
    Project(
        title = "Cosmic",
        titleColor = Color(74, 35, 165, 255),
        creationDate = LocalDate.of(2024, 5, 15),
        description = "Cosmic est une application Android qui relaye les informations issues d'une API publique" +
                " sur les objets de notre système solaire : étoiles, planètes, lunes, astéroïdes, comètes et planètes naines." +
                "Elle permet de visualiser, filtrer et ordonner les corps célestes, accéder à leurs détails et " +
                "se représenter leurs distances par rapport au Soleil.",
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
            "Affichage d'une liste de corps célestes avec informations principales",
            "Filtrage et tri des objets selon différents critères",
            "Détails complets d’un corps céleste sur un second écran",
            "Visualisation de la distance par rapport au Soleil sur un écran dédié",
            "Gestion des favoris avec persistance locale (Room)"
        ),
        whatILearn = listOf(
            "Premier projet kotlin & jetpack compose",
            "Intégration d'une API REST avec Retrofit",
            "Gestion d’état avec MVVM et Compose",
            "Persistance locale avec Room",
            "Navigation multi-écran avec Navigation Compose",
            "Visualisation et interaction graphique dans Compose",
            "Intégration de publicité bannière"
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
        description = "In Life Among Us est un jeu multijoueur inspiré d’Among Us, mais ancré dans le monde réel. " +
                "Les joueurs se connectent à un serveur local Spring Boot via une application Android. Chaque joueur " +
                "reçoit un rôle — imposteur ou équipier — et doit interagir avec son environnement physique. " +
                "Les QR codes placés dans le lieu servent de points d’interaction : les joueurs les scannent pour " +
                "réaliser des actions, des missions ou même déclencher un appel d’urgence. Ainsi, presque toutes les activités " +
                "passent par ces codes. Cette dynamique permet de créer " +
                "une expérience immersive, mêlant jeu social et déplacement réel.",
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
            "Connexion des joueurs à un serveur local Spring Boot pour synchroniser les parties",
            "Attribution automatique des rôles : imposteur ou crew mate",
            "Interaction via QR codes : preque toutes les actions du jeu nécessitent de scanner un code présent dans le lieu",
            "Gestion d’actions contextuelles (missions, sabotages, appels d’urgence) depuis l’application",
            "Suivi de la dernière position connue des joueurs via les QR codes scannés",
            "Communication client-serveur sécurisée via Retrofit / OkHttp",
            "Utilisation de CameraX et permissions dynamiques pour le scan des codes QR"
        ),
        whatILearn = listOf(
            "Premier projet personnel avec un serveur spring",
            "Gestion de la caméra, des permissions, du flash et des QR codes"
        ),
        images = listOf(
            "drawable/in_life_among_us_intro_screen.jpg" to PORTRAIT,
            "drawable/in_life_among_us_task_list_screen.png" to PORTRAIT,
            "drawable/in_life_among_us_map_screen.png" to LANDSCAPE
        ),
        backgroundColor = Color(10, 0, 0, 255),
        otherTextColor = Color(219, 33, 33, 255),
        remarks = "⚠️Ce projet est un fan work non commercial réalisé à des fins de démonstration.*\nEn cours de développement."
    ),
    Project(
        title = "Cobria",
        titleColor = Color.Black,
        creationDate = LocalDate.of(2025,7,28),
        description = "Cobria est un site web réel crée par Jak Solutions dont le but est d'accompagner les entrepreneurs " +
                "dans leur travail. Ma contribution dans ce vaste projet est minime mais elle m'a permis de travailler " +
                "sur un véritable projet professionnel. J'ai principalement travailler sur l'onboarding, le système de " +
                "récompense et sur le responsive de certains composants de Cobria.",
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
            "Créer et gérer ses contacts (clients, fournisseurs, etc.)",
            "Créer et gérer ses devis",
            "Créer et gérer les factures liés aux devis",
            "Construire et gérer un planning",
            "Assister la création des différents documents par IA"
        ),
        whatILearn = listOf(
            "React",
            "Dotnet",
            "Redux",
            "React Joyride",
            "Travail en équipe avec git"
        ),
        images = listOf(
            "drawable/cobria_1.png" to LANDSCAPE,
        ),
        backgroundColor = Color(255, 228, 196, 255),
        otherTextColor = Color(253, 138, 58, 255),
        remarks = ""
    )
)