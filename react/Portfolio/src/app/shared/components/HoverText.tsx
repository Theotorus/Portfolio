import {useState} from "react";
import {useAppSelector} from "../../store/store.ts";

type HoverTextProps = {
    text: string;
    action: () => void;
}

function HoverText(props: HoverTextProps) {
    const [isHovered, setHovered] = useState(false);
    const currentFont = useAppSelector(state => state.fonts.currentFont);
    return (
        <span
            onMouseEnter={() => setHovered(true)}
            onMouseLeave={() => setHovered(false)}
            className={`${isHovered ? "text-red-800" : "text-white"}`}
            style={{
                fontFamily: currentFont,
                fontSize: 22,
            }}
            onClick={props.action}
        >
            {props.text}
        </span>
    );
}

export default HoverText;