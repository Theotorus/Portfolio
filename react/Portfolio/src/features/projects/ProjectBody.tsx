import DetailsColumn from "./DetailsColumn.tsx";
import PicturesBox from "./PicturesBox.tsx";


export default function ProjectBody() {
    return (
        <div className="flex flex-col w-full grow px-4 py-1 pb-[5vh] items-center">
            <div className="flex w-full min-h-[70vh]">
                <DetailsColumn/>
                <PicturesBox/>
            </div>
        </div>
    );
}