# Console-Based Sudoku Game 🧩

A simple, interactive, command-line Sudoku game written in Java. The game dynamically generates unique puzzles with a fully functioning backtracking algorithm and offers an engaging terminal-based gameplay experience.

## ✨ Features

* **Dynamic Puzzle Generation**: Every time you play, a brand-new, unique Sudoku board is generated.
* **Three Difficulty Levels**:
  * **Easy** (35 empty cells)
  * **Medium** (45 empty cells)
  * **Hard** (55 empty cells)
* **Smart Validation**: The game checks your input instantly. It prevents you from overriding fixed (starting) cells and immediately lets you know if you enter a number that doesn't match the hidden solution.
* **Hint System**: Stuck on a puzzle? Type `hint` to reveal the correct number for the next available empty cell.
* **Auto-Win Detection**: The game automatically detects when the board is successfully completed and congratulates the player.

## 🛠️ Prerequisites

To compile and run this game, you will need:
* **Java Development Kit (JDK)** installed on your machine (JDK 8 or higher is recommended).

## 🚀 How to Run

1. Open your terminal or command prompt.
2. Navigate to the directory where the `Sudoku.java` file is saved.
3. Compile the Java file using the following command:
   ```bash
   javac Sudoku.java
