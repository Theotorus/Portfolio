import AppText from "../../app/shared/components/AppText.tsx";
import {useCurrentProject} from "../../lib/hooks/useCurrentProject.ts";
import {useTranslation} from "react-i18next";

export default function ProjectHeader() {
    const currentProject = useCurrentProject();
    const {t} = useTranslation();
    const description = t(currentProject.description);
    return (
        <div className="flex flex-col w-full px-4" style={{ backgroundColor: currentProject.backgroundColor }}>
            <div className='flex w-full h-[1/10] justify-between items-center'>
                <AppText text={currentProject.title} fontSize={54} color={currentProject.titleColor}/>
                <AppText text={currentProject.creationDate} fontSize={48} color={currentProject.titleColor}
                         fontStyle={'italic'}/>
            </div>
            <AppText text={description} fontSize={28} color={currentProject.otherTextColor} className='mt-4'/>
        </div>
    );
}