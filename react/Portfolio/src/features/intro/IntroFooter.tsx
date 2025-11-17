import HoverText from "../../app/shared/components/HoverText.tsx";
import toast from "react-hot-toast";
import {useTranslation} from "react-i18next";

export default function IntroFooter() {
    const { t } = useTranslation();
    const handleCopyToClipboard = () => {
        navigator.clipboard.writeText("christophermx95@gmail.com").then(() => {
            toast.success(t('general.copied_to_clipboard'));
        }).catch((err) => {
            console.log(err);
        })

    };
    const handleOpenGithub = () => {
        window.open("https://github.com/Theotorus", "_blank");
    };

    return (
        <div className="flex w-full justify-evenly items-center bg-[rgba(0,0,0,0.75)]">
            <HoverText text="christophermx95@gmail.com" action={handleCopyToClipboard}/>
            <HoverText text="https://github.com/Theotorus" action={handleOpenGithub}/>
        </div>
    );
}