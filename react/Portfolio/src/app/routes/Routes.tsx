import {createBrowserRouter, Navigate} from "react-router-dom";
import App from "../layout/App.tsx";
import IntroductionPage from "../../features/intro/IntroductionPage.tsx";
import ProjectsPage from "../../features/projects/ProjectsPage.tsx";

export const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
        children: [
            {path: 'intro/:lang', element: <IntroductionPage/>},
            {path: 'projects/:id/:lang', element: <ProjectsPage/>},
            {path: '*', element: <Navigate replace to={'intro/en'}/> },
        ]
    }
])