import {useEffect} from "react";
import {useNavigate} from "react-router-dom";

export default function LanguageRedirect() {
    const navigate = useNavigate();

    useEffect(() => {
        const systemLang = navigator.language || "en";

        const lang = systemLang.split("-")[0];

        const supported = ["fr", "en", "nl"];
        const finalLang = supported.includes(lang) ? lang : "en";

        navigate(`/intro/${finalLang}`, { replace: true });
    }, []);

    return null;
}