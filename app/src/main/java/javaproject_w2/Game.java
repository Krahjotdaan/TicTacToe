package app.src.main.java.javaproject_w2;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a Tic-Tac-Toe game.
 * This class provides methods to initialize and format the game board.
 */
public class Game {

    /**
     * Enum representing the possible states of a cell on the game board.
     * X: Cell occupied by 'X'
     * O: Cell occupied by 'O'
     * EMPTY: Cell is empty.
     */
    public enum Cell {
        X, O, EMPTY
    }

    /**
     * Initializes the game board with all cells set to EMPTY.
     *
     * @return A 2D array of Cell enums representing the initialized game board.  The board is 3x3.
     */
    public static Cell[][] initGame() {

        Cell[][] field = new Cell[3][3]; 

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = Cell.EMPTY;
            }
        }

        return field;
    }

    /**
     * Formats the current game board into a human-readable string representation.
     * The output includes row and column labels to represent the board's coordinates.
     *
     * @param field A 2D array of Cell enums representing the current state of the game board.
     * @return A String representing the formatted game board.
     */
    public static String formatGame(Cell[][] field) {
        Map<Cell, String> symbols = new HashMap<>();
        symbols.put(Cell.X, "X");
        symbols.put(Cell.O, "O");
        symbols.put(Cell.EMPTY, "_");

        StringBuilder builder = new StringBuilder();
        builder.append("   1 2 3\n");
        builder.append("  ------\n");
        builder.append(String.format("a| %s %s %s\n", 
                                    symbols.get(field[0][0]), 
                                    symbols.get(field[0][1]),
                                    symbols.get(field[0][2])));

        builder.append(String.format("b| %s %s %s\n", 
                                    symbols.get(field[1][0]), 
                                    symbols.get(field[1][1]),
                                    symbols.get(field[1][2])));

        builder.append(String.format("c| %s %s %s\n", 
                                    symbols.get(field[2][0]), 
                                    symbols.get(field[2][1]),
                                    symbols.get(field[2][2])));

        return builder.toString();
    }

    /**
     * Checks if there are any available moves left on the game board (i.e., any empty cells).
     *
     * @param field A 2D array of Cell enums representing the current state of the game board.
     * @return True if there are available moves (at least one EMPTY cell), false otherwise.
     */
    public static boolean isMoveAvailable(Cell[][] field) {
        int takenCells = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] != Cell.EMPTY) {
                    takenCells++;
                }
            }
        }

        if (takenCells < 9) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks for a winning condition in rows and columns.
     *
     * @param field A 2D array of Cell enums representing the current state of the game board.
     * @return "X" if 'X' has won, "O" if 'O' has won, and "EMPTY" if there is no winner in rows or columns.
     */
    public static String checkRowsCols(Cell[][] field) {
        int xRowCount = 0;
        int oRowCount = 0;
        int xColCount = 0;
        int oColCount = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (field[i][j] == Cell.X) {
                    xRowCount++;
                }
                else if (field[i][j] == Cell.O) {
                    oRowCount++;
                }

                if (field[j][i] == Cell.X) {
                    xColCount++;
                }
                else if (field[j][i] == Cell.O) {
                    oColCount++;
                }
            }

            if (xRowCount == 3 || xColCount == 3) {
                return "X";
            }
            else if (oRowCount == 3 || oColCount == 3) {
                return "O";
            }

            xRowCount = 0;
            oRowCount = 0;
            xColCount = 0;
            oColCount = 0;
        }

        return "EMPTY";
    }

    /**
     * Checks for a winning condition along the diagonals.
     *
     * @param field A 2D array of Cell enums representing the current state of the game board.
     * @return "X" if 'X' has won, "O" if 'O' has won, and "EMPTY" if there is no winner along the diagonals.
     */
    public static String checkDiags(Cell[][] field) {
        int xMainDiagCount = 0;
        int oMainDiagCount = 0;
        int xSecDiagCount = 0;
        int oSecDiagCount = 0;

        for (int i = 0; i < 3; i++) {

            if (field[i][i] == Cell.X) {
                xMainDiagCount++;
            }
            else if (field[i][i] == Cell.O) {
                oMainDiagCount++;
            }

            if (field[i][2 - i] == Cell.X) {
                xSecDiagCount++;
            }
            else if (field[i][2 - i] == Cell.O) {
                oSecDiagCount++;
            }
        }

        if (xMainDiagCount == 3 || xSecDiagCount == 3) {
            return "X";
        }
        else if (oMainDiagCount == 3 || oSecDiagCount == 3) {
            return "O";
        }

        return "EMPTY";
    }

    /**
     * Checks if there is a winner (X or O) on the board.
     *
     * @param field A 2D array of Cell enums representing the current state of the game board.
     * @return Cell.X if player X has won, Cell.O if player O has won, and Cell.EMPTY if no one has won yet.
     */
    public static Cell checkWin(Cell[][] field) {
        String rowsColsResult = checkRowsCols(field);
        String diagsResult = checkDiags(field);

        if (rowsColsResult == "X" || diagsResult == "X") {
            return Cell.X;
        }
        else if (rowsColsResult == "O" || diagsResult == "O") {
            return Cell.O;
        }
        else {
            return Cell.EMPTY;
        }
    }

    /**
     * Handles a player's move, taking input from the scanner, validating the move, and updating the game board.
     *
     * @param field   A 2D array of Cell enums representing the current state of the game board.
     * @param player  The Cell enum representing the player making the move (X or O).
     * @param scanner The Scanner object used for reading player input.
     * @throws IllegalArgumentException If the player enters an invalid move (e.g., incorrect format, already occupied cell).
     */
    public static void move(Cell[][] field, Cell player, Scanner scanner)
        throws IllegalArgumentException {

            String[] allowedMoves = new String[]{"a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"};

            System.out.println("Разрешенные ходы: " + Arrays.toString(allowedMoves));
            System.out.println("Введите ход: ");
            String move = scanner.nextLine();

            if (!Arrays.asList(allowedMoves).contains(move) || move.length() != 2) {
                throw new IllegalArgumentException("\nНекорректный ввод");
            }

            char[] coords = move.toCharArray();

            if (field[coords[0] - 'a'][coords[1] - '0' - 1] != Cell.EMPTY) {
                throw new IllegalArgumentException("\nЯчейка уже занята");
            }

            field[coords[0] - 'a'][coords[1] - '0' - 1] = player;
        }

    
    /**
     * Main method to run the Tic-Tac-Toe game. Handles game flow, player turns, input, and win conditions.
     */
    public static void main() {
        Cell[][] field = initGame();
        int moveNumber = 0;
        Cell[] moves = new Cell[]{Cell.X, Cell.O};

        Scanner scanner = new Scanner(System.in);

        while (isMoveAvailable(field) && checkWin(field) == Cell.EMPTY) {
            if (moveNumber % 2 == 0) {
                System.out.println("\n\nХод игрока X");
            }
            else {
                System.out.println("\n\nХод игрока O");
            }

            System.out.println(formatGame(field));

            try {
                move(field, moves[moveNumber % 2], scanner);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            moveNumber++;
        }

        scanner.close();

        if (checkWin(field) == Cell.X) {
            System.out.println(formatGame(field));
            System.out.println("Победил игрок X");
        }
        else if (checkWin(field) == Cell.O) {
            System.out.println(formatGame(field));
            System.out.println("Победил игрок O");
        }
        else {
            System.out.println(formatGame(field));
            System.out.println("Ничья");
        }
    }
}