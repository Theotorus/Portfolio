import {createBrowserRouter, Navigate} from "react-router-dom";
import App from "../layout/App.tsx";
import IntroductionPage from "../../features/intro/IntroductionPage.tsx";
import ProjectsPage from "../../features/projects/ProjectsPage.tsx";
import LanguageRedirect from "@app/routes/LanguageRedirect.tsx";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
        children: [
            {index: true, element: <LanguageRedirect/>},
            {path: 'intro/:lang', element: <IntroductionPage/>},
            {path: 'projects/:id/:lang', element: <ProjectsPage/>},
        ]
    },
    {path: '*', element: <Navigate replace to={'intro/en'}/> }
])