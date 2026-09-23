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
   ```
4. Run the compiled program:
   ```bash
   java Sudoku
   ```

## 🎮 How to Play

Once the game starts, you will be prompted to select a difficulty level (1, 2, or 3). After that, the board will be displayed. 

### Commands
Type your commands directly into the terminal and press `Enter`:

* **Make a move**: Type the row (1-9), column (1-9), and value (1-9) separated by spaces.
  * *Example*: `3 5 7` (Places the number `7` in row `3`, column `5`).
* **Get a hint**: Type `hint`. The game will automatically fill in an empty cell for you.
* **Show the board**: Type `show`. This reprints the current state of the board in case it gets pushed out of your terminal view.
* **Quit the game**: Type `exit` to end the game immediately.

### Board Layout
The board is represented by a 9x9 grid divided into 3x3 boxes. 
* Empty cells are represented by a dot (`.`).
* Numbers (`1-9`) represent filled cells.

## 📝 Example Gameplay

```text
=================================
         SUDOKU GAME
=================================

Select a difficulty level:
1 - Easy   (35 empty cells)
2 - Medium (45 empty cells)
3 - Hard   (55 empty cells)
Your choice (1-3): 1

The game is starting! Commands:
 - To make a move: row column value (e.g. 3 5 7)
 - For a hint: hint
 - To show the board: show
 - To quit: exit

 -----------------------
| 5 3 . | . 7 . | . . . |
| 6 . . | 1 9 5 | . . . |
| . 9 8 | . . . | . 6 . |
 -----------------------
| 8 . . | . 6 . | . . 3 |
| 4 . . | 8 . 3 | . . 1 |
| 7 . . | . 2 . | . . 6 |
 -----------------------
| . 6 . | . . . | 2 8 . |
| . . . | 4 1 9 | . . 5 |
| . . . | . 8 . | . 7 9 |
 -----------------------

Enter your move: 1 3 4
```
