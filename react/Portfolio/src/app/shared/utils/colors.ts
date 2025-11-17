// src/utils/color.ts
import type { RGBA } from "../../../data/projects";

export function rgbaToCss({ r, g, b, a }: RGBA): string {
    return `rgba(${r}, ${g}, ${b}, ${a / 255})`;
}