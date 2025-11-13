import {useAppSelector} from "../../store/store.ts";

type AppTextProps = {
    text: string;
    color?: string;
    fontSize: number;
    fontStyle?: "normal" | "italic";
    textDecoration?: "none" | "underline" | "line-through";
    className?: string;
}

export default function AppText({
        text,
        color = "#ffffff",
        fontSize,
        fontStyle = "normal",
        textDecoration = "none",
        className = "",
    }: AppTextProps
){
    const currentFont = useAppSelector(state => state.fonts.currentFont);
    return (
        <span
            className={`select-none ${className} whitespace-pre-line`}
            style={{
                color: color,
                fontSize: `${fontSize}px`,
                fontFamily: currentFont,
                lineHeight: `${fontSize}px`,
                fontStyle: fontStyle,
                textDecoration: textDecoration,
            }}
        >
            {text}
        </span>
    )
}