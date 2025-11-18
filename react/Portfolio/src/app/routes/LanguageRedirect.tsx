import {useEffect} from "react";
import {useNavigate} from "react-router-dom";
import i18n from "i18next";

export default function LanguageRedirect() {
    const navigate = useNavigate();

    useEffect(() => {
        const systemLang = navigator.language || "en";

        const lang = systemLang.split("-")[0];

        const supported = ["fr", "en", "nl"];
        const finalLang = supported.includes(lang) ? lang : "en";
        i18n.changeLanguage(finalLang);

        navigate(`/intro/${finalLang}`, { replace: true });
    }, []);

    return null;
}