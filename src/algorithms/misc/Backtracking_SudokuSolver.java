package algorithms.misc;

import static java.lang.String.format;

import java.util.Arrays;

public class Backtracking_SudokuSolver {

    private int[][] sudokuBoard;
    private boolean[][] isOccupied;
    private int freeCells;
    private boolean isSolved = false;
    private boolean abort = false;
    private int counter = 0;

    public int getCounter() {
        return counter;
    }
    
    public int[][] solve(int[][] sudoku) {
        if (sudoku.length != 9) {
            throw new IllegalArgumentException(format("Input sudoku size is not 9, got: %s", sudoku.length));
        }
        sudokuBoard = sudoku.clone();
        freeCells = countFreeCells();
        isOccupied = getOccupationBoard();
        backtrack(0, 0);
        return sudokuBoard;
    }

    private void backtrack(int i, int j) {
        counter++;
        if (isSolved) return;
        if (freeCells == 0) {
            isSolved = true;
            return;
        }

        for (int num = 1; num <= 9; num++) {
            if (i == 0 && j == 0) abort = false;
            if (!isOccupied[i][j] && !isInLilSquare(num, i, j) && !isInRow(num, i) && !isInColumn(num, j)) {
                sudokuBoard[i][j] = num;
                isOccupied[i][j] = true;
                freeCells--;
            } else {
                if (num == 9) {
                    abort = true;
                    return;
                }
                continue;
            }

            if (i + 1 < sudokuBoard.length) {
                backtrack(i + 1, j);
            }
            if (j + 1 < sudokuBoard.length) {
                backtrack(i, j + 1);
            }
            if (j + 1 < sudokuBoard.length && i + 1 < sudokuBoard.length) {
                backtrack(i + 1, j + 1);
            }
            
            if (abort) {
                isOccupied[i][j] = false;
                freeCells++;
            }
        }
    }

    private boolean isInRow(int num, int row) {
        for (int i = 0; i < sudokuBoard.length; i++) {
            if (sudokuBoard[row][i] == num) return true;
        }
        return false;
    }

    private boolean isInColumn(int num, int column) {
        for (int i = 0; i < sudokuBoard.length; i++) {
            if (sudokuBoard[i][column] == num) return true;
        }
        return false;
    }

    private boolean isInLilSquare(int num, int x, int y) {
        int squareStartI = (x / 3) * 3;
        int squareStartJ = (y / 3) * 3;
        for (int i = squareStartI; i < squareStartI + 3; i++) {
            for (int j = squareStartJ; j < squareStartJ + 3; j++) {
                if (sudokuBoard[i][j] == num) return true;
            }
        }
        return false;
    }

    private int countFreeCells() {
        int count = 0;
        for (int i = 0; i < sudokuBoard.length; i++) {
            for (int j = 0; j < sudokuBoard.length; j++) {
                if (sudokuBoard[i][j] == 0) count++;
            }
        }
        return count;
    }
    
    private boolean[][] getOccupationBoard() {
        boolean[][] occupationBoard = new boolean[sudokuBoard.length][sudokuBoard.length];
        for (int i = 0; i < sudokuBoard.length; i++) {
            for (int j = 0; j < sudokuBoard.length; j++) {
                if (sudokuBoard[i][j] == 0) {
                    occupationBoard[i][j] = false;
                } else {
                    occupationBoard[i][j] = true;
                }
            }
        }
        return occupationBoard;
    }

    public static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
        System.out.println("--------------------");
    }

    public static void main(String[] args) {
        int[][] sudokuBoard = {
            {0, 0, 0, 0, 0, 0, 0, 1, 2},
            {0, 0, 0, 0, 3, 5, 0, 0, 0},
            {0, 0, 0, 6, 0, 0, 0, 7, 0},
            {7, 0, 0, 0, 0, 0, 3, 0, 0},
            {0, 0, 0, 4, 0, 0, 8, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 2, 0, 0, 0, 0},
            {0, 8, 0, 0, 0, 0, 0, 4, 0},
            {0, 5, 0, 0, 0, 0, 6, 0, 0}
        };

        Backtracking_SudokuSolver sudokuSolver = new Backtracking_SudokuSolver();
        printArray(sudokuSolver.solve(sudokuBoard));
        System.out.println(sudokuSolver.getCounter());
    }
}
