import {useLocation, useNavigate, useParams} from "react-router-dom";
import {Button, IconButton} from "@mui/material";
import HomeIcon from '@mui/icons-material/Home';
import AppText from "../shared/components/AppText.tsx";
import i18n from "../../i18n.ts";

export default function MainHeader() {
    const location = useLocation();
    const navigate = useNavigate();
    const {lang = 'fr'} = useParams();

    const handleLanguageChange = (newLang: "fr" | "en" | "nl") => {
        if (/(\/fr|\/en|\/nl)$/.test(location.pathname)) {
            navigate(location.pathname.replace(/(\/fr|\/en|\/nl)$/, `/${newLang}`));
        } else {
            navigate(`${location.pathname}/${newLang}`);
        }
        i18n.changeLanguage(newLang);
    };

    const isIntro = location.pathname.startsWith("/intro");
    const white = '#ffffff'
    return (
        <header className="flex items-center justify-between bg-[rgba(0,0,0,0.75)]">
            {!isIntro ? (
                <IconButton onClick={() => navigate(`/intro/${lang}`)}>
                    <HomeIcon color={white}/>
                </IconButton>
            ) : (<p>_</p>)}
            <div className="flex">
                <Button onClick={() => handleLanguageChange("en")}>
                    <AppText text="EN" fontSize={20} color={lang === 'en' ? 'white' : 'gray'}/>
                </Button>
                <Button onClick={() => handleLanguageChange("fr")}>
                    <AppText text="FR" fontSize={20} color={lang === 'fr' ? 'white' : 'gray'}/>
                </Button>
                <Button onClick={() => handleLanguageChange("nl")}>
                    <AppText text="NL" fontSize={20} color={lang === 'nl' ? 'white' : 'gray'}/>
                </Button>
            </div>
        </header>
    );
}