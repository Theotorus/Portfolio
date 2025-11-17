// src/data/projects.ts

export type RGBA = {
    r: number;
    g: number;
    b: number;
    a: number;
};

export type PictureOrientation = "PORTRAIT" | "LANDSCAPE";

export type ProjectImage = {
    path: string;
    orientation: PictureOrientation;
};

export type Project = {
    title: string;
    titleColor: string;
    creationDate: string; // ISO date (yyyy-mm-dd)
    description: string; // translation key
    linkedTechnologies: string[]; //all in english
    keyFunctionalities: string[]; // translation keys
    whatILearn: string[]; // translation keys
    images: ProjectImage[];
    backgroundColor: string;
    otherTextColor: string;
    remarks: string; //translation key
};

export const projects: Project[] = [
    {
        title: "Cosmic",
        titleColor: '#4A23A5',
        creationDate: "15/05/2024",
        description: "projects.cosmic.description",
        linkedTechnologies: [
            "Android Studio",
            "Kotlin",
            "Jetpack Compose",
            "MVVM",
            "Material 3",
            "Retrofit",
            "Room",
            "Coroutines",
            "Navigation Compose"
        ],
        keyFunctionalities: [
            "projects.cosmic.key_functionalities.0",
            "projects.cosmic.key_functionalities.1",
            "projects.cosmic.key_functionalities.2",
            "projects.cosmic.key_functionalities.3",
            "projects.cosmic.key_functionalities.4"
        ],
        whatILearn: [
            "projects.cosmic.learn.0",
            "projects.cosmic.learn.1",
            "projects.cosmic.learn.2",
            "projects.cosmic.learn.3",
            "projects.cosmic.learn.4",
            "projects.cosmic.learn.5",
            "projects.cosmic.learn.6"
        ],
        images: [
            { path: "/images/cosmic1.jpg", orientation: "PORTRAIT" },
            { path: "/images/cosmic2.jpg", orientation: "PORTRAIT" },
            { path: "/images/cosmic3.jpg", orientation: "LANDSCAPE" }
        ],
        backgroundColor: '#00000A',
        otherTextColor: '#2D2ECD',
        remarks: ""
    },
    {
        title: "In life Among us",
        titleColor: '#6F0F0F',
        creationDate: "10/10/2024",
        description: "projects.in_life_among_us.description",
        linkedTechnologies: [
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
        ],
        keyFunctionalities: [
            "projects.in_life_among_us.key_functionalities.0",
            "projects.in_life_among_us.key_functionalities.1",
            "projects.in_life_among_us.key_functionalities.2",
            "projects.in_life_among_us.key_functionalities.3",
            "projects.in_life_among_us.key_functionalities.4",
            "projects.in_life_among_us.key_functionalities.5",
            "projects.in_life_among_us.key_functionalities.6"
        ],
        whatILearn: [
            "projects.in_life_among_us.learn.0",
            "projects.in_life_among_us.learn.1"
        ],
        images: [
            { path: "/images/in_life_among_us_intro_screen.jpg", orientation: "PORTRAIT" },
            { path: "/images/in_life_among_us_task_list_screen.png", orientation: "PORTRAIT" },
            { path: "/images/in_life_among_us_map_screen.png", orientation: "LANDSCAPE" }
        ],
        backgroundColor: '#0A0000',
        otherTextColor: '#DB2121',
        remarks: "projects.in_life_among_us.remarks"
    },
    {
        title: "Cobria",
        titleColor: '#000',
        creationDate: "28/07/2025",
        description: "projects.cobria.description",
        linkedTechnologies: [
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
        ],
        keyFunctionalities: [
            "projects.cobria.key_functionalities.0",
            "projects.cobria.key_functionalities.1",
            "projects.cobria.key_functionalities.2",
            "projects.cobria.key_functionalities.3",
            "projects.cobria.key_functionalities.4"
        ],
        whatILearn: [
            "projects.cobria.learn.0",
            "projects.cobria.learn.1"
        ],
        images: [
            { path: "/images/cobria_1.png", orientation: "LANDSCAPE" }
        ],
        backgroundColor: '#FFE4C4',
        otherTextColor: '#FD8A3A',
        remarks: ""
    }
];