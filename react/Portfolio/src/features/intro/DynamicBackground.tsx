import React, { useEffect, useRef } from "react";

const DynamicBackground: React.FC = () => {
    const canvasRef = useRef<HTMLCanvasElement>(null);

    useEffect(() => {
        const canvas = canvasRef.current;
        if (!canvas) return;
        const ctx = canvas.getContext("2d");
        if (!ctx) return;

        let animationFrameId: number;
        const start = performance.now();

        const draw = (time: number) => {
            const w = canvas.width = window.innerWidth;
            const h = canvas.height = window.innerHeight;

            const t = ((time - start) / 5000) % 2; // cycle de 5s
            const progress = t < 1 ? t : 2 - t; // aller-retour

            ctx.clearRect(0, 0, w, h);
            ctx.fillStyle = "black";
            ctx.fillRect(0, 0, w, h);

            // gradient 1
            const grad1 = ctx.createRadialGradient(
                500 * progress - 750,
                400 * progress,
                0,
                500 * progress - 750,
                400 * progress,
                Math.max(1,w * 5 * progress)
            );
            grad1.addColorStop(0.1, "transparent");
            grad1.addColorStop(0.2, "#E03535");
            grad1.addColorStop(0.4, "#C9B21C");
            grad1.addColorStop(0.6, "#25BA1A");
            grad1.addColorStop(0.8, "#1A3DBA");
            grad1.addColorStop(1, "transparent");

            ctx.fillStyle = grad1;
            ctx.fillRect(0, 0, w, h);

            // gradient 2
            const grad2 = ctx.createRadialGradient(
                w * 1.5,
                h * 0.9,
                0,
                w * 1.5,
                h * 0.9,
                Math.max(1,w * 5 * progress)
            );
            grad2.addColorStop(0.1, "transparent");
            grad2.addColorStop(0.2, "#E03535");
            grad2.addColorStop(0.3, "#C9B21C");
            grad2.addColorStop(0.5, "#25BA1A");
            grad2.addColorStop(0.7, "#1A3DBA");
            grad2.addColorStop(0.9, "#571ABA");
            grad2.addColorStop(1, "transparent");

            ctx.fillStyle = grad2;
            ctx.fillRect(0, 0, w, h);

            animationFrameId = requestAnimationFrame(draw);
        };

        animationFrameId = requestAnimationFrame(draw);
        return () => cancelAnimationFrame(animationFrameId);
    }, []);

    return (
        <div className="absolute inset-0">
            <canvas ref={canvasRef} className="w-full h-full" />
            <img
                src={"/images/hexagons3.png"}
                alt="hexagons background"
                className="absolute inset-0 w-full h-full object-cover pointer-events-none"
            />
        </div>
    );
};

export default DynamicBackground;
