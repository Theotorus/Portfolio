import { useEffect, useRef } from "react";
import { projects } from "@data/projects.ts";

export default function CobrIaBackground() {
    const canvasRef = useRef<HTMLCanvasElement | null>(null);

    useEffect(() => {
        const canvas = canvasRef.current;
        if (!canvas) return;
        const ctx = canvas.getContext("2d");
        if (!ctx) return;

        let animationFrameId: number;

        const HEX_COUNT = 45;

        interface Hex {
            x: number;
            y: number;
            size: number;
            speed: number;
            alpha: number;
            lineWidth: number;
            wobbleSpeed: number;
            wobbleAmplitude: number;
            wobblePhase: number;
            rotation: number;
            rotationSpeed: number;
        }

        const hexes: Hex[] = [];

        const rand = (min: number, max: number) =>
            Math.random() * (max - min) + min;

        const initHex = (
            hex: Hex,
            canvasWidth: number,
            canvasHeight: number,
        ) => {
            hex.size = rand(35, 90);
            hex.x = Math.random() * canvasWidth;
            hex.y = canvasHeight + rand(0, canvasHeight * 0.3);
            hex.speed = rand(0.3, 1.2);
            hex.alpha = rand(0.25, 0.55);
            hex.lineWidth = rand(1, 3);
            hex.wobbleSpeed = rand(0.02, 0.06);
            hex.wobbleAmplitude = rand(10, 40);
            hex.wobblePhase = Math.random() * Math.PI * 2;
            hex.rotation = rand(0, Math.PI * 2);
            hex.rotationSpeed = rand(-0.003, 0.003);
        };

        const handleResize = () => {
            const { innerWidth, innerHeight } = window;
            canvas.width = innerWidth;
            canvas.height = innerHeight;

            if (hexes.length === 0) {
                for (let i = 0; i < HEX_COUNT; i++) {
                    const h = {} as Hex;
                    initHex(h, innerWidth, innerHeight);
                    h.y = rand(innerHeight * 0.3, innerHeight * 1.1);
                    hexes.push(h);
                }
            }
        };

        handleResize();
        window.addEventListener("resize", handleResize);

        const drawHex = (ctx: CanvasRenderingContext2D, size: number) => {
            const sides = 6;
            const radius = size / 2;
            ctx.beginPath();
            for (let i = 0; i < sides; i++) {
                const angle = (Math.PI / 3) * i - Math.PI / 6;
                const px = Math.cos(angle) * radius;
                const py = Math.sin(angle) * radius;
                if (i === 0) ctx.moveTo(px, py);
                else ctx.lineTo(px, py);
            }
            ctx.closePath();
        };

        const render = () => {
            const w = canvas.width;
            const h = canvas.height;

            ctx.clearRect(0, 0, w, h);

            hexes.forEach((hex) => {
                hex.y -= hex.speed;
                hex.wobblePhase += hex.wobbleSpeed;
                hex.rotation += hex.rotationSpeed;

                if (hex.y + hex.size < -50) {
                    initHex(hex, w, h);
                }

                const offsetX = Math.sin(hex.wobblePhase) * hex.wobbleAmplitude;

                ctx.save();
                ctx.translate(hex.x + offsetX, hex.y);
                ctx.rotate(hex.rotation);
                ctx.globalAlpha = hex.alpha;
                ctx.lineWidth = hex.lineWidth;
                ctx.strokeStyle = "white";

                drawHex(ctx, hex.size);
                ctx.stroke();
                ctx.restore();
            });

            animationFrameId = requestAnimationFrame(render);
        };

        animationFrameId = requestAnimationFrame(render);

        return () => {
            cancelAnimationFrame(animationFrameId);
            window.removeEventListener("resize", handleResize);
        };
    }, []);

    return (
        <canvas
            ref={canvasRef}
            className="fixed inset-0 w-full h-screen -z-10"
            style={{ backgroundColor: projects[2].backgroundColor }}
        />
    );
}
