import {type Project, projects} from "@data/projects.ts";
import {useParams} from "react-router-dom";

export function useCurrentProject(): Project {
    const {id} = useParams();
    const projectId = Number(id);
    return projects[projectId];
}