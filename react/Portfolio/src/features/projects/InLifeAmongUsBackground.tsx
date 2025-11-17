import {projects} from "@data/projects.ts";
import {useEffect, useRef} from "react";

export default function InLifeAmongUsBackground() {
    const canvasRef = useRef<HTMLCanvasElement>(null);

    const imagePaths = [
        '/images/among_us_black_nb.png',
        '/images/among_us_blue_nb.png',
        '/images/among_us_cyan_nb.png',
        '/images/among_us_gray_nb.png',
        '/images/among_us_green_nb.png',
        '/images/among_us_orange_nb.png',
        '/images/among_us_pink_nb.png',
        '/images/among_us_red_nb.png',
        '/images/among_us_violet_nb.png',
        '/images/among_us_white_nb.png',
        '/images/among_us_yellow_nb.png',
    ];

    useEffect(() => {
        const canvas = canvasRef.current;
        if (!canvas) return;
        const ctx = canvas.getContext("2d");
        if (!ctx) return;

        // Charger les images
        const loadedImages: HTMLImageElement[] = [];
        let loadedCount = 0;

        const rand = (min: number, max: number) =>
            Math.random() * (max - min) + min;

        imagePaths.forEach((src, i) => {
            const img = new Image();
            img.src = src;

            img.onload = () => {
                loadedImages[i] = img;
                loadedCount++;

                if (loadedCount === imagePaths.length) startAnimation();
            };
        });

        const startAnimation = () => {
            const guys = loadedImages.map((img) => {
                const size = rand(40, 70);
                const spawnLeft = Math.random() < 0.5;

                // Angle selon le côté
                let angleDeg: number;
                if (spawnLeft) {
                    angleDeg = Math.random() < 0.5
                        ? rand(330, 360)
                        : rand(0, 30);
                } else {
                    angleDeg = rand(150, 210);
                }

                const angleRad = angleDeg * Math.PI / 180;

                return {
                    img,
                    x: spawnLeft ? -size - rand(0, 50) : window.innerWidth + rand(0, 50),
                    y: Math.random() * window.innerHeight,
                    size,
                    angle: angleRad,
                    speed: rand(1, 3),
                    rotation: rand(0, Math.PI * 2),
                    rotationSpeed: rand(-0.05, 0.05), // tourne à gauche ou à droite
                };
            });

            const render = () => {
                const w = canvas.width = window.innerWidth;
                const h = canvas.height = window.innerHeight;

                ctx.clearRect(0, 0, w, h);

                guys.forEach(g => {
                    // Déplacement selon l’angle
                    g.x += Math.cos(g.angle) * g.speed;
                    g.y += Math.sin(g.angle) * g.speed;

                    // Rotation
                    g.rotation += g.rotationSpeed;

                    // Quand il sort trop loin → respawn de l’autre côté
                    if (g.x < -200 || g.x > w + 200 || g.y < -200 || g.y > h + 200) {
                        const spawnLeft = Math.random() < 0.5;
                        g.x = spawnLeft ? -g.size - rand(0, 50) : w + rand(0, 50);
                        g.y = Math.random() * h;

                        // Nouvel angle selon le côté
                        if (spawnLeft) {
                            g.angle = (Math.random() < 0.5 ? rand(330, 360) : rand(0, 30)) * Math.PI / 180;
                        } else {
                            g.angle = rand(150, 210) * Math.PI / 180;
                        }

                        g.rotationSpeed = rand(-0.05, 0.05);
                    }

                    // Dessin avec rotation
                    ctx.save();
                    ctx.translate(g.x + g.size / 2, g.y + g.size / 2);
                    ctx.rotate(g.rotation);
                    ctx.drawImage(g.img, -g.size / 2, -g.size / 2, g.size, g.size);
                    ctx.restore();
                });

                requestAnimationFrame(render);
            };

            render();
        };
    }, []);

    return (
        <canvas
            ref={canvasRef}
            className='fixed inset-0 w-full h-screen -z-10'
            style={{ backgroundColor: projects[1].backgroundColor }}
        />
    );
}
