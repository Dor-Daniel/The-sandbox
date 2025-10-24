# Arkanoid — Java OOP, Events, and Animation

A classic brick-breaker implemented from scratch in Java. The project demonstrates object-oriented design, event-driven programming, simple game physics, and a lightweight animation loop.

## What i try to achieve

- Clear separation between rendering, game logic, and flow control

- Interface-first architecture (extensible, loosely coupled)

- Uses common design patterns (Listener/Observer, Strategy, Decorator/Wrapper)

- Easy to add new levels, mechanics, or UI screens without breaking core code

## Gallery

![]()
---
![]()
---
![]()
---

## Tech & Architecture

**External Library**

biuoop.jar — minimal GUI/keyboard helper (commonly used in academic settings)

### Core Interfaces

- Sprite — anything drawable/updatable

- Collidable — objects participating in collisions

- Animation — “screen with a loop” (runs until told to stop)

- LevelInformation — data/behavior contract for a level (layout, ball speed, paddle size, etc.)

- Key Modules (example package layout)

        src/
        |- Calc/
        |- Externals/   # Here u can find external libs files
        |- Game/        # Game loop and environemnt
        |- GameLevels/ 
        |- GameObjects/ # Logical game objects
        |- Geometry/    # Meshes and renderable premitives
        |- Interfaces/
        |- Listeners/
        |- Methods/     # Const general methods
        |- Test/
        |- Main.java



### Design Patterns

- Listener/Observer — HitListener decouples “what happened” from “how to react”

- Strategy — each LevelInformation is a pluggable strategy for gameplay parameters/layouts

- Decorator/Wrapper — KeyPressStoppableAnimation wraps another animation to add a stop condition

### Features

- 3 playable levels with distinct layouts and backgrounds

- Collision system for ball–block/paddle with basic reflection

- Score tracking (points per block, HUD indicator)

- Pause/Resume (keyboard toggle)

- End screens (Win / Game Over)

- Clean extension points for power-ups, lives, new blocks, etc.

### Controls
#### Key	Action
- **$\rightarrow, \leftarrow$:**	*Move paddle left / right*
- **SPACE**	*Pause / Continue*
- **R**	*Restart after Game Over (opt.)*
- **Q**	*Quit after Game Over (opt.)*
## Build & Run

### Project prerequisites

- Java 8+ (JDK)

- biuoop.jar placed in the project root (or adjust the classpath below)

### Compile (Unix/macOS)

```bash
 javac -cp biuoop.jar -d out $(find src -name "*.java")
```


### Compile (Windows PowerShell)

```bash
javac -cp ".;biuoop.jar" -d out (Get-ChildItem -Recurse src\*.java | ForEach-Object { $_.FullName })
```


### Run (Unix/macOS)
```bash
java -cp "biuoop.jar:out" Main.java
```

### Run (Windows)
```bash
java -cp ".;biuoop.jar;out" Main.java
```

## Future Extensions

- Lives system and HUD indicator

- Power-ups (multi-ball, expand/shrink paddle, sticky paddle, laser)

- Level editor (JSON → LevelInformation adapter)

- Sound effects & background music

- Fancy collision (spin/english from paddle hit position)

### Code Quality Practices

- Interface-based APIs for sprites, collisions, and animations

- Single-responsibility classes where possible

- Immutable geometry (Point, Line, Rectangle)

- Small, testable utilities (e.g., collision detection helpers)

---

*Build with love and java.* **Enjoy!**