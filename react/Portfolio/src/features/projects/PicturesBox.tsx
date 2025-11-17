import {useCurrentProject} from "../../lib/hooks/useCurrentProject.ts";
import {useEffect, useState} from "react";

export default function PicturesBox() {
    const currentProject = useCurrentProject();

    const [activeStep, setActiveStep] = useState(0);
    const [animState, setAnimState] = useState<"fadeIn" | "fadeOut">("fadeIn");
    const [pendingIndex, setPendingIndex] = useState<number | null>(null);

    useEffect(() => {
        setActiveStep(0);
        setAnimState("fadeIn");
        setPendingIndex(null);
    }, [currentProject]);

    const handleSelect = (index: number) => {
        if (index === activeStep) return;

        setPendingIndex(index);
        setAnimState("fadeOut");
    };

    const onAnimationEnd = () => {
        if (animState === "fadeOut" && pendingIndex !== null) {
            setActiveStep(pendingIndex);
            setPendingIndex(null);
            setAnimState("fadeIn");
        }
    };

    const getImageStyle = () => {
        switch (currentProject.images[activeStep].orientation) {
            case "LANDSCAPE":
                return "w-[53vw] h-auto";
            case "PORTRAIT":
                return "w-auto h-[66vh]";
            default:
                return "";
        }
    };

    return (
        <div
            className="
                fixed top-[60vh] -translate-y-1/2 translate-x-[48vw]
                w-[50vw] flex flex-col items-center
                z-[10] select-none
            "
        >
            <img
                src={currentProject.images[activeStep].path}
                onAnimationEnd={onAnimationEnd}
                className={`
                    max-w-[50vw] h-[64vh]
                    object-contain rounded-l
                    ${getImageStyle()}
                    ${animState === "fadeIn"
                    ? "animate-fadeInGrow"
                    : "animate-fadeOutShrink"
                }
                `}
            />

            {/* DOTS */}
            <div className="flex gap-3 mt-4">
                {currentProject.images.map((_, index) => {
                    const isActive = index === activeStep;

                    return (
                        <button
                            key={index}
                            onClick={() => handleSelect(index)}
                            className="rounded-full transition-all"
                            style={{
                                width: "14px",
                                height: "14px",
                                border: "2px solid white",
                                backgroundColor: isActive ? "white" : "transparent",
                            }}
                        />
                    );
                })}
            </div>
        </div>
    );
}
