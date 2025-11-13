import DynamicBackground from "./DynamicBackground.tsx";
import AppText from "../../app/shared/components/AppText.tsx";
import HoverButton from "../../app/shared/components/HoverButton.tsx";
import {useTranslation} from "react-i18next";
import {useNavigate, useParams} from "react-router-dom";
import MainHeader from "../../app/layout/MainHeader.tsx";
import {setFont} from "../../app/fontsSlice.ts";
import IntroFooter from "./IntroFooter.tsx";

function IntroductionPage() {
    const {t} = useTranslation();
    const white = "#ffffff"
    const navigate = useNavigate();
    const lang = useParams();

    const handleSelectProject = (index: number) => {
        switch(index) {
            case 0: setFont('Orbitron'); break;
            case 1: setFont('Fredoka'); break;
            default: setFont('FiraCode'); break;
        }
        navigate(`/projects/${index}/${lang}`);
    };
    return (
        <div className='relative w-full h-screen'>
            <DynamicBackground/>
            <div className='relative w-full h-full flex flex-col'>
                <MainHeader/>
                    <div className='flex flex-col px-4 w-full h-1/2 justify-evenly'>

                        <div className='flex flex-col px-1'>
                            <AppText text='Christopher Melis' color='#ffffff' fontSize={44}/>
                            <AppText text={t('intro.title')} fontSize={40} />
                        </div>
                        <AppText text={t('intro.welcome')} color={white} fontSize={28} className='p-1 bg-[rgba(0,0,0,0.2)]'/>
                    </div>
                    <div className={'grow flex flex-col w-full items-center justify-evenly'}>
                        <AppText text={t('intro.my_projects')} textDecoration={'underline'} fontSize={36}/>
                        <HoverButton text='Cosmic' onClick={() => handleSelectProject(0)}/>
                        <HoverButton text='In Life Among Us' onClick={() => handleSelectProject(1)}/>
                        <HoverButton text='Cobria' onClick={() => handleSelectProject(2)}/>
                    </div>
                <IntroFooter/>
            </div>
        </div>
    );
}

export default IntroductionPage;
