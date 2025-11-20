import {useParams} from "react-router-dom";
import CosmicBackground from "./backgrounds/CosmicBackground.tsx";
import InLifeAmongUsBackground from "./backgrounds/InLifeAmongUsBackground.tsx";
import CobriaBackground from "./backgrounds/CobriaBackground.tsx";
import type {JSX} from "react";
import MainHeader from "@app/layout/MainHeader";
import ProjectHeader from "./ProjectHeader";
import ProjectBody from "./ProjectBody.tsx";
import ProjectsFooter from "./ProjectsFooter.tsx";
import {useCurrentProject} from "../../lib/hooks/useCurrentProject.ts";
import HappenBackground from "./backgrounds/HappenBackground.tsx";

function ProjectsPage() {
    const { id } = useParams();
    const projectId: number = Number(id);
    const backgrounds: JSX.Element[] = [<CosmicBackground />, <InLifeAmongUsBackground />, <CobriaBackground />, <HappenBackground />]
    const headerBackgroundColor = useCurrentProject().backgroundColor;
    return (
        <div className='relative w-full min-h-screen'>
            {backgrounds[projectId] ?? null}
            <div className='sticky top-0 z-[999] w-full' style={{ backgroundImage: headerBackgroundColor }}>
                <MainHeader/>
                <ProjectHeader/>
            </div>
            <ProjectBody/>
            <ProjectsFooter/>
        </div>
    );
}

export default ProjectsPage;