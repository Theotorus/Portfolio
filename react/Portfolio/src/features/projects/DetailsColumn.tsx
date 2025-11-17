import AppText from "@app/shared/components/AppText.tsx";
import {useCurrentProject} from "../../lib/hooks/useCurrentProject.ts";
import {useTranslation} from "react-i18next";

export default function DetailsColumn() {
    const {t} = useTranslation();
    const titles = [
        t('projects.categories.0'),
        t('projects.categories.1'),
        t('projects.categories.2')
    ];
    const project = useCurrentProject();

    const elements = [
        project.linkedTechnologies,
        project.keyFunctionalities,
        project.whatILearn
    ];
    const chips = ["⚙️", "🧩", "✨"];

    return (
        <div className="flex flex-col gap-10 w-[47vw] my-10">
            <HorizontalCategory
                title={titles[0]}
                colors={[project.titleColor, project.otherTextColor]}
                elements={elements[0]}
                chip={chips[0]}
            />

            <Category
                title={titles[1]}
                colors={[project.titleColor, project.otherTextColor]}
                elements={elements[1]}
                chip={chips[1]}
            />
            <Category
                title={titles[2]}
                colors={[project.titleColor, project.otherTextColor]}
                elements={elements[2]}
                chip={chips[2]}
            />

            {project.remarks && (
                <AppText
                    text={`Remarque : ${t(project.remarks)}`}
                    color={project.otherTextColor}
                    fontSize={20}
                />
            )}
        </div>
    );
}

interface categoryProps {
    title: string;
    colors: Array<string>;
    elements: string[];
    chip: string;
}

function HorizontalCategory({title, colors, elements, chip}: categoryProps) {
    return (
        <div>
            <AppText
                text={title}
                fontSize={40}
                color={colors[0]}
                textDecoration={'underline'}
            />

            {/* Équivalent de FlowRow */}
            <div className="flex flex-wrap gap-x-6 gap-y-4 mt-4">
                {elements.map((el, i) => (
                    <div
                        key={i}
                        className="px-4 py-2 rounded-3xl border"
                        style={{borderColor: colors[0], color: colors[1]}}
                    >
                        {chip} {el}
                    </div>
                ))}
            </div>
        </div>
    );
}

function Category({title, colors, elements, chip}: categoryProps) {
    const {t} = useTranslation();
    return (
        <div>
            <AppText
                text={title}
                fontSize={40}
                color={colors[0]}
                textDecoration={'underline'}
            />

            <div className="flex flex-wrap gap-x-4 gap-y-3 mt-3">
                {elements.map((el, i) => (
                    <div
                        key={i}
                        className="px-3 py-2 rounded-2xl border"
                        style={{borderColor: colors[0], color: colors[1]}}
                    >
                        {chip} {t(el)}
                    </div>
                ))}
            </div>
        </div>
    );
}
