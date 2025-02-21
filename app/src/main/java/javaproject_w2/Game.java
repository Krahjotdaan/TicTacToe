package javaproject_w2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a Tic-Tac-Toe game.
 * This class provides methods to initialize and format the game board.
 */
public class Game {

        final public static List<String> allowedMoves = new ArrayList<>(List.of("a1", "a2", "a3", "b1", "b2", "b3", "c1", "c2", "c3"));

        /**
         * Enum representing the possible states of a cell on the game board.
         * X: Cell occupied by 'X'
         * O: Cell occupied by 'O'
         * EMPTY: Cell is empty.
         */
        public enum Cell {
            X("X"), O("O"), EMPTY("_");

            private String symbol;

            Cell(String symbol) {
                this.symbol = symbol;
            }

            public String getSymbol() {
                return symbol;
            }
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

            StringBuilder builder = new StringBuilder();
            builder.append("   1 2 3\n");
            builder.append("  ------\n");
            builder.append(String.format("a| %s %s %s\n", 
                field[0][0].getSymbol(), 
                field[0][1].getSymbol(),
                field[0][2].getSymbol()));

            builder.append(String.format("b| %s %s %s\n", 
                field[1][0].getSymbol(), 
                field[1][1].getSymbol(),
                field[1][2].getSymbol()));
            
            builder.append(String.format("c| %s %s %s\n", 
                field[2][0].getSymbol(), 
                field[2][1].getSymbol(),
                field[2][2].getSymbol()));
    
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
    
            return takenCells < 9;
        }
    
        /**
         * Checks for a winning condition in rows and columns.
         *
         * @param field A 2D array of Cell enums representing the current state of the game board.
         * @return "X" if 'X' has won, "O" if 'O' has won, and "EMPTY" if there is no winner in rows or columns.
         */
        public static Cell checkRowsCols(Cell[][] field) {
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
                    return Cell.X;
                }
                else if (oRowCount == 3 || oColCount == 3) {
                    return Cell.O;
                }
    
                xRowCount = 0;
                oRowCount = 0;
                xColCount = 0;
                oColCount = 0;
            }
    
            return Cell.EMPTY;
        }
    
        /**
         * Checks for a winning condition along the diagonals.
         *
         * @param field A 2D array of Cell enums representing the current state of the game board.
         * @return Cell.X if 'X' has won, Cell.O if 'O' has won, and Cell.EMPTY if there is no winner along the diagonals.
         */
        public static Cell checkDiags(Cell[][] field) {
            int xMainDiagCount = 0; // количество Х на главной диагонали
            int oMainDiagCount = 0; // количество О на главной диагонали
            int xSecDiagCount = 0; // количество Х на побочной диагонали
            int oSecDiagCount = 0; // количество О на побочной диагонали
    
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
                return Cell.X;
            }
            else if (oMainDiagCount == 3 || oSecDiagCount == 3) {
                return Cell.O;
            }
    
            return Cell.EMPTY;
        }
    
        /**
         * Checks if there is a winner (X or O) on the board.
         *
         * @param field A 2D array of Cell enums representing the current state of the game board.
         * @return Cell.X if player X has won, Cell.O if player O has won, and Cell.EMPTY if no one has won yet.
         */
        public static Cell checkWin(Cell[][] field) {
            Cell rowsColsResult = checkRowsCols(field);
            Cell diagsResult = checkDiags(field);
    
            if (rowsColsResult == Cell.X || diagsResult == Cell.X) {
                return Cell.X;
            }
            else if (rowsColsResult == Cell.O || diagsResult == Cell.O) {
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
    
                System.out.println("Разрешенные ходы: " + allowedMoves.toString());
                System.out.println("Введите ход: ");
                String move = scanner.nextLine();
    
                if (!allowedMoves.contains(move) || move.length() != 2) {
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
    public static void main(String[] args) {
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

        Cell gameResult = checkWin(field);

        switch (gameResult) {
            case Cell.X:
                System.out.println(formatGame(field));
                System.out.println("Победил игрок X");

            case Cell.O:
                System.out.println(formatGame(field));
                System.out.println("Победил игрок O");

            case Cell.EMPTY:
                System.out.println(formatGame(field));
                System.out.println("Ничья");
        }
    }
}