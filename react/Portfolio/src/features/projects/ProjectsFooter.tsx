import {useTranslation} from "react-i18next";
import {Button} from "@mui/material";
import {useNavigate, useParams} from "react-router-dom";
import {projects} from "@data/projects.ts";
import {setFont} from "@app/fontsSlice.ts";

export default function ProjectsFooter() {
    const {t} = useTranslation();
    const {id, lang} = useParams();
    const projectId: number = Number(id);
    const navigate = useNavigate();
    const previous = "◀ " + t('general.previous');
    const next = t('general.next') + " ▶";
    const handlePreviousProject = (): void => {
        if (projectId === 0) {
            setFont('FiraCode');
            navigate(`intro/${lang}`);
        } else {
            switch(projectId+1) {
                case 0: setFont('Orbitron'); break;
                case 1: setFont('Fredoka'); break;
                default: setFont('FiraCode'); break;
            }
            navigate(`/projects/${projectId - 1}/${lang}`);
        }
    }
    const handleNextProject = (): void => {
        if (projectId < projects.length - 1) {
            switch(projectId+1) {
                case 0: setFont('Orbitron'); break;
                case 1: setFont('Fredoka'); break;
                default: setFont('FiraCode'); break;
            }
            navigate(`/projects/${projectId + 1}/${lang}`);
        }
    }

    const hasPrevious: boolean = projectId > 0;
    const hasNext: boolean = projectId < projects.length - 1;
    return (
        <div className="sticky bottom-0 flex w-full h-[5vh] justify-between items-center bg-black">
            {hasPrevious ? <Button onClick={handlePreviousProject}>{previous}</Button> : <p> </p>}
            {hasNext ? <Button onClick={handleNextProject}>{next}</Button> : <p> </p>}
        </div>
    );
}