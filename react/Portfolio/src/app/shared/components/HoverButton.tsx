import {useState} from "react";
import {useAppSelector} from "../../store/store.ts";

type HoverButtonProps = {
    text: string,
    onClick?: () => void,
}

export default function HoverButton({text, onClick}: HoverButtonProps) {
    const [isHovered, setHovered] = useState(false);
    const currentFont = useAppSelector(state => state.fonts.currentFont);
    return (
        <button
            color="inherit"
            onClick={onClick}
            onMouseEnter={() => setHovered(true)}
            onMouseLeave={() => setHovered(false)}
            className={`rounded-md px-4 py-2 transition-all duration-200
                ${isHovered ? "!bg-white/50 !text-neutral-900 scale-105" : "!bg-transparent !text-white scale-100"}
            `}
            style={{
                transformOrigin: "center",
                fontSize: "36px",
                fontFamily: currentFont + "!important"
            }}
        >
            {text}
        </button>
    )
}