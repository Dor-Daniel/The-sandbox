
---

# The Sandbox — Experiments in Code, Math & Art

---

A personal space for learning, exploration, and curiosity - where **mathematics**, **graphics**, and **creativity** meet through code.

This repository is a living notebook: a mix of **p5.js sketches**, **Java mini-games**, and **GLSL shader experiments**, each created to test an idea or visualize a concept.

Every project here began with a simple question:

> “What if I implement this concept myself — from scratch?”

---

## Overview

**The Sandbox** collects self-contained experiments in:

* **Graphics & Simulation** — visual explorations in 2D and 3D
* **Procedural Generation** — randomness, noise, and structure
* **Mathematical Visualization** — geometry, fractals, transformations
* **Game Mechanics & Animation Systems** — small interactive worlds
* **Shader Art & GPU Thinking** — writing pixels directly with math

These projects are not about completion — they’re about understanding.
Each one captures a single idea, expressed through visual computation.

---

## Repository Structure

```
THE-SANDBOX/
├── glsl/                 # Shaders & GPU experiments
│   └── CubeTransformations.glsl
│
├── java/                 # Java projects (academic-level OOP)
│   ├── ArkanoidGame/     # Brick breaker with modular OOP design
│   └── game of life/     # Simple cellular automaton
│
├── p5/                   # Generative sketches and math visualizations
│   ├── 3b1b cube collision simulation
│   ├── ball in ellipse trajectory simulation
│   ├── cauchy epsilon delta visualizer
│   ├── drawing curves using complex numbers
│   ├── drawing grayscale pics using Fourier transform
│   ├── drawing parametric curves using circles
│   ├── group permutation – math animation
│   ├── kind of three body simulation
│   ├── line movement to rotation appearance
│   ├── linear transformations visualizer
│   ├── lotus creation animation
│   ├── mandelbrot set generation
│   ├── molds simulation
│   ├── perlin noise 1D visualizer
│   ├── periodic functions generation using circles
│   ├── sierpinski triangle deterministic
│   ├── sierpinski triangle random
│   └── string art – picture copy greedy
│
└── README.md
```

Each folder contains a local README (or notes) explaining the idea, how to run it, and screenshots under `/docs/images`.

---

##  Featured Sections

###  p5.js Sketches

Visual experiments exploring geometry, chaos, and artistic structure.

Includes:

* **Fractals:** Mandelbrot, Sierpinski, and diffusion-like molds
* **Motion & Symmetry:** Parametric curves, rotations, line transformations
* **Fourier & Noise:** Generating functions and waveforms from circles
* **Creative Explorations:** String art, group permutations, and blooming forms

[See the full table here →](./p5/README.md)

---

### Java Projects

Projects designed around strong software engineering principles and academic OOP structure.

#### Arkanoid Game

A full brick-breaker built from scratch using `biuoop.jar`, featuring event-driven design, listeners, and modular architecture.
**Focus:** OOP design patterns, clean architecture, animation loop, game systems.
*(See `java/ArkanoidGame/README.md` for full explanation.)*

#### Game of Life

Classic simulation of Conway’s cellular automaton.
**Focus:** Data structures, 2D grid updates, emergent behavior.

---

### GLSL Experiments

GPU-side explorations of mathematical transformations.

#### Cube Transformations

Tests different transformation matrices (rotation, scaling, shear) directly in shader code.
**Focus:** Understanding coordinate systems, matrix math, and 3D intuition.

---

##  Learning Themes

Across these small experiments, recurring themes appear:

* Linear algebra & coordinate transformations
* Fractals, chaos, and emergent geometry
* Noise functions & generative structure
* Simulation and physical intuition
* Art through algorithmic form

Each project is a snapshot of curiosity — a focused attempt to learn something by building it, visually.

---

##  How to Explore

Each subfolder includes:

* Source files and scripts
* A small README or comments
* Screenshots/GIFs (when available)
* Minimal setup requirements

**Examples:**

* Open `.js` sketches in your browser or the [p5.js editor](https://editor.p5js.org/)
* Run Java games via command line (`javac` + `java`)
* View `.glsl` shaders using ShaderToy or any GLSL viewer

---

## Philosophy


This repository isn’t about finished products - it’s about *insight through creation.*
It’s where math becomes visual, and logic becomes art.
Each file is an experiment, a question asked in the language of computation.

---

## Future Directions

* 3D p5.js sketches exploring projections and rotation groups
* More GLSL experiments (distance fields, lighting, transformations)
* Mini Unity physics sandboxes
* Procedural texture and shape generators

---

##  Closing Thoughts

**The Sandbox** is a place to explore freely — where structure and beauty meet in unexpected ways.
It’s a reminder that programming isn’t just about solving problems; it’s about *seeing* patterns emerge.

> *Each line of code is a brushstroke; each algorithm, a shape of thought.*

---
*Enjoy!*
