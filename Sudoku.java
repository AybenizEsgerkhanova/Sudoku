import java.util.Random;
import java.util.Scanner;

/**
 * Console-based Sudoku Game
 * - Generates a random puzzle (empty cell count depends on difficulty)
 * - The player fills the board by entering row, column, and value
 * - Includes hints, invalid-move checks, and a win check
 */
public class Sudoku {

    private static final int SIZE = 9;
    private static final int BOX = 3;

    private int[][] solution = new int[SIZE][SIZE];   // Full solution (hidden)
    private int[][] board = new int[SIZE][SIZE];       // Board shown to the player
    private boolean[][] fixed = new boolean[SIZE][SIZE]; // Starting cells that cannot be changed
    private final Random random = new Random();

    public static void main(String[] args) {
        Sudoku game = new Sudoku();
        game.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("         SUDOKU GAME");
        System.out.println("=================================");

        int difficulty = selectDifficulty(scanner);
        generateSolvedBoard();
        board = copyBoard(solution);
        removeCells(difficulty);
        markFixedCells();

        System.out.println("\nThe game is starting! Commands:");
        System.out.println(" - To make a move: row column value (e.g. 3 5 7)");
        System.out.println(" - For a hint: hint");
        System.out.println(" - To show the board: show");
        System.out.println(" - To quit: exit\n");

        printBoard();

        while (true) {
            System.out.print("\nEnter your move: ");
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("exit")) {
                System.out.println("You left the game. See you!");
                break;
            }

            if (line.equalsIgnoreCase("show")) {
                printBoard();
                continue;
            }

            if (line.equalsIgnoreCase("hint")) {
                giveHint();
                printBoard();
                if (isSolved()) {
                    System.out.println("\nCongratulations! You completed the Sudoku!");
                    break;
                }
                continue;
            }

            String[] parts = line.split("\\s+");
            if (parts.length != 3) {
                System.out.println("Invalid input! Example: 3 5 7  (row column value)");
                continue;
            }

            try {
                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;
                int val = Integer.parseInt(parts[2]);

                if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || val < 1 || val > 9) {
                    System.out.println("Row/column must be 1-9, and the value must be 1-9!");
                    continue;
                }

                if (fixed[row][col]) {
                    System.out.println("This cell is fixed and cannot be changed!");
                    continue;
                }

                if (solution[row][col] != val) {
                    System.out.println("Wrong value! Try again.");
                    continue;
                }

                board[row][col] = val;
                printBoard();

                if (isSolved()) {
                    System.out.println("\nCongratulations! You successfully completed the Sudoku!");
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter numbers! Example: 3 5 7");
            }
        }

        scanner.close();
    }

    private int selectDifficulty(Scanner scanner) {
        System.out.println("\nSelect a difficulty level:");
        System.out.println("1 - Easy   (35 empty cells)");
        System.out.println("2 - Medium (45 empty cells)");
        System.out.println("3 - Hard   (55 empty cells)");
        System.out.print("Your choice (1-3): ");

        while (true) {
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": return 35;
                case "2": return 45;
                case "3": return 55;
                default:
                    System.out.print("Invalid choice, enter a number from 1-3: ");
            }
        }
    }

    // ---------- Board generation ----------

    private void generateSolvedBoard() {
        fillDiagonalBoxes();
        solve(solution, 0, 0);
    }

    // Filling the 3 diagonal boxes at random speeds up solving
    private void fillDiagonalBoxes() {
        for (int b = 0; b < SIZE; b += BOX) {
            fillBox(b, b);
        }
    }

    private void fillBox(int startRow, int startCol) {
        int[] nums = shuffledNumbers();
        int idx = 0;
        for (int i = 0; i < BOX; i++) {
            for (int j = 0; j < BOX; j++) {
                solution[startRow + i][startCol + j] = nums[idx++];
            }
        }
    }

    private int[] shuffledNumbers() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = nums.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }

    // Generates a complete Sudoku solution with backtracking
    private boolean solve(int[][] grid, int row, int col) {
        if (row == SIZE) return true;
        int nextRow = (col == SIZE - 1) ? row + 1 : row;
        int nextCol = (col == SIZE - 1) ? 0 : col + 1;

        if (grid[row][col] != 0) {
            return solve(grid, nextRow, nextCol);
        }

        int[] nums = shuffledNumbers();
        for (int num : nums) {
            if (isValidPlacement(grid, row, col, num)) {
                grid[row][col] = num;
                if (solve(grid, nextRow, nextCol)) return true;
                grid[row][col] = 0;
            }
        }
        return false;
    }

    private boolean isValidPlacement(int[][] grid, int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == num || grid[i][col] == num) return false;
        }
        int boxRow = (row / BOX) * BOX;
        int boxCol = (col / BOX) * BOX;
        for (int i = 0; i < BOX; i++) {
            for (int j = 0; j < BOX; j++) {
                if (grid[boxRow + i][boxCol + j] == num) return false;
            }
        }
        return true;
    }

    private void removeCells(int count) {
        int removed = 0;
        while (removed < count) {
            int r = random.nextInt(SIZE);
            int c = random.nextInt(SIZE);
            if (board[r][c] != 0) {
                board[r][c] = 0;
                removed++;
            }
        }
    }

    private void markFixedCells() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                fixed[i][j] = board[i][j] != 0;
            }
        }
    }

    private int[][] copyBoard(int[][] src) {
        int[][] dest = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            dest[i] = src[i].clone();
        }
        return dest;
    }

    // ---------- Game helpers ----------

    private void giveHint() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = solution[i][j];
                    System.out.println("Hint: (" + (i + 1) + ", " + (j + 1) + ") -> " + solution[i][j]);
                    return;
                }
            }
        }
        System.out.println("The board is already full!");
    }

    private boolean isSolved() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != solution[i][j]) return false;
            }
        }
        return true;
    }

    private void printBoard() {
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            if (i % BOX == 0) {
                System.out.println(" -----------------------");
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < SIZE; j++) {
                if (j % BOX == 0) sb.append("| ");
                sb.append(board[i][j] == 0 ? "." : String.valueOf(board[i][j])).append(" ");
            }
            sb.append("|");
            System.out.println(sb);
        }
        System.out.println(" -----------------------");
    }
}
