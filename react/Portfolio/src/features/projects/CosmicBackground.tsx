import {projects} from "@data/projects.ts";
import {useEffect, useRef} from "react";

export default function CosmicBackground() {
    const canvasRef = useRef<HTMLCanvasElement>(null);

    useEffect(() => {
        const canvas = canvasRef.current;
        if (!canvas) return;
        const ctx = canvas.getContext("2d");
        if (!ctx) return;

        const stars = Array.from({length: 250}, () => ({
            x: Math.random() * window.innerWidth,
            y: Math.random() * window.innerHeight,
            size: Math.random()*1.1,
            speed: Math.random() + 0.3
        }));

        const render = () => {
            const w = canvas.width = window.innerWidth;
            const h = canvas.height = window.innerHeight;

            ctx.clearRect(0, 0, w, h);

            ctx.fillStyle = "white";

            stars.forEach(star => {
                star.x -= star.speed;

                if (star.x < -5) {
                    star.x = w + Math.random() * 50;
                    star.y = Math.random() * h;
                }

                ctx.beginPath();
                ctx.arc(star.x, star.y, star.size, 0, Math.PI * 2);
                ctx.fill();
            });

            requestAnimationFrame(render);
        };

        render();
    }, []);
    return (
        <canvas
            ref={canvasRef}
            className="fixed inset-0 w-full h-full -z-10"
            style={{backgroundColor: projects[0].backgroundColor}}
        />
    );
}