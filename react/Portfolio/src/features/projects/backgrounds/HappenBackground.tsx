import {projects} from "@data/projects.ts";
import {useRef} from "react";

export default function HappenBackground() {
    const canvasRef = useRef<HTMLCanvasElement | null>(null);

    return (
        <canvas
            ref={canvasRef}
            className="fixed inset-0 w-full h-screen -z-10"
            style={{ backgroundColor: projects[3].backgroundColor }}
        />
    );
}