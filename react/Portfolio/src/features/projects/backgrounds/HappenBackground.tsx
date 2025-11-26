import { useEffect, useRef } from "react";
import {projects} from "@data/projects.ts";

type Confetti = {
    x: number;
    y: number;
    vx: number;
    vy: number;
    alpha: number;
    rotation: number;
    rotationSpeed: number;
    size: number;
    color: string;
    life: number;
    maxLife: number;
};

export default function ConfettiBackground() {
    const canvasRef = useRef<HTMLCanvasElement>(null);
    const confettis: Confetti[] = [];
    let phase = 0; // 0: up/down, 1: left/right, 2: corners
    let lastPhaseSwitch = 0;

    const colors = [
        "rgba(255, 150, 150,",   // pastel red
        "rgba(255, 240, 150,",   // pastel yellow
        "rgba(150, 180, 255,",   // pastel blue
        "rgba(170, 255, 170,",   // pastel green
        "rgba(255, 180, 220,",   // pastel pink
        "rgba(255, 255, 255,"    // white
    ];

    function spawnFromPoints(_canvas: HTMLCanvasElement, points: { x: number; y: number; angleBase: number }[]) {
        const count = 24;
        const dispersion = (120 * Math.PI) / 180;

        for (const p of points) {
            for (let i = 0; i < count; i++) {
                const angle = p.angleBase + (Math.random() - 0.5) * dispersion;
                const speed = 200 + Math.random() * 200;

                confettis.push({
                    x: p.x,
                    y: p.y,
                    vx: Math.cos(angle) * speed,
                    vy: Math.sin(angle) * speed,
                    alpha: 1,
                    rotation: Math.random() * Math.PI * 2,
                    rotationSpeed: (Math.random() - 0.5) * 4,
                    size: 6 + Math.random() * 6,
                    color: colors[(Math.random() * colors.length) | 0],
                    life: 0,
                    maxLife:  1.6 + Math.random() * 0.8
                });
            }
        }
    }

    function spawn(canvas: HTMLCanvasElement) {
        const w = canvas.width;
        const h = canvas.height;

        if (phase === 0) {
            // haut + bas
            spawnFromPoints(canvas, [
                { x: w / 2, y: 0, angleBase: Math.PI / 2 },       // vers le bas
                { x: w / 2, y: h + 50, angleBase: -Math.PI / 2 }    // vers le haut
            ]);
        }

        else if (phase === 1) {
            // gauche + droite
            spawnFromPoints(canvas, [
                { x: -50, y: h / 2, angleBase: 0 },                 // droite
                { x: w + 50, y: h / 2, angleBase: Math.PI }         // gauche
            ]);
        }

        else if (phase === 2) {
            // 4 coins
            spawnFromPoints(canvas, [
                { x: -50, y: -50, angleBase: Math.PI / 4 },         // bas-droite
                { x: w + 50, y: -50, angleBase: (3 * Math.PI) / 4 },// bas-gauche
                { x: -50, y: h, angleBase: -Math.PI / 4 },     // haut-droite
                { x: w + 50, y: h, angleBase: -(3 * Math.PI) / 4 } // haut-gauche
            ]);
        }

        phase = (phase + 1) % 3;
    }

    function loop(timestamp: number) {
        const canvas = canvasRef.current;
        if (!canvas) return;
        const ctx = canvas.getContext("2d");
        if (!ctx) return;

        const w = canvas.width;
        const h = canvas.height;

        ctx.clearRect(0, 0, w, h);

        // Switch every 1s
        if (timestamp - lastPhaseSwitch > 1000) {
            lastPhaseSwitch = timestamp;
            spawn(canvas);
        }

        // update & draw
        for (let i = confettis.length - 1; i >= 0; i--) {
            const c = confettis[i];

            c.x += c.vx * 0.016;
            c.y += c.vy * 0.016;
            c.rotation += c.rotationSpeed * 0.016;

            c.life += 0.014;

            let t = c.life / c.maxLife;
            if (t > 1) t = 1;

            const eased = t * t;

            c.alpha = 1 - eased;

            if (c.alpha <= 0) {
                confettis.splice(i, 1);
                continue;
            }


            ctx.save();
            ctx.translate(c.x, c.y);
            ctx.rotate(c.rotation);
            ctx.fillStyle = c.color + c.alpha + ")";
            ctx.fillRect(-c.size / 2, -c.size / 2, c.size, c.size);
            ctx.restore();
        }

        requestAnimationFrame(loop);
    }

    useEffect(() => {
        const canvas = canvasRef.current;
        if (!canvas) return;

        const resize = () => {
            canvas.width = window.innerWidth;
            canvas.height = window.innerHeight;
        };
        resize();

        window.addEventListener("resize", resize);
        requestAnimationFrame(loop);

        return () => window.removeEventListener("resize", resize);
    }, []);

    return (
        <canvas
            ref={canvasRef}
            className='fixed inset-0 w-full h-full pointer-events-none -z-10'
            style={{
                backgroundColor: projects[3].backgroundColor,
            }}
        />
    );
}
