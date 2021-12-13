package Sudoku;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Sudoku implements SudokuSolver {
    private int[][] board;
    private int attempts;

    public Sudoku() {
        this.board = new int[9][9];
    }

    @Override
    public boolean solve() {
        attempts = 0;
        return solve(0, 0);
    }

    /**
     * Private helper method from solve, uses recursive backtracking.
     * @param row The row that is being evaluated
     * @param col The column that is being evaluated
     * @return true/false if sudoku was solvable
     */
    private boolean solve(int row, int col) { //better algo by Knuth described here https://arxiv.org/pdf/cs/0011047.pdf
        attempts++;
        if (col == 9) {
            row++;
            if (row == 9 && isValid()) {
                return true; //walked through entire matrix
            }
            col = 0;
        }

        if (attempts > 1000000) { //max attempts, should be able to get a prettier solution for unsolvable boards
            return false;
        }

        // if at col 9 go to next row
        // check points, check fill, add [1-9]
        if (get(row, col) != 0) {
            if (isValid()) {
                return solve(row, col + 1);
            }
            return false;
        }
        for (int i = 1; i < 10; i++) {
            add(row, col, i);
            if (isValid()) {
                if (solve(row, col + 1)) {
                    return true;
                }
            }
        }
        remove(row, col); //due to recursion
        return false;
    }

    /**
     *
     * @param row   The row
     * @param col   The column
     * @param digit The digit to insert in box row, col
     */
    @Override
    public void add(int row, int col, int digit) {
        board[row][col] = digit;
        //System.out.println("We just added" + digit + "to row" + row + "column" + col);
    }

    /**
     *
     * @param row The row of cell whose value will be removed
     * @param col The column of cell whose value will be removed
     */
    @Override
    public void remove(int row, int col) {
        board[row][col] = 0;
    }

    /**
     *
     * @param row The row of cell whose value will be fetched
     * @param col The column of cell whose value will be fetched
     * @return
     */
    @Override
    public int get(int row, int col) {
        return this.board[row][col];
    }

    /**
     * Checks if grid adheres to sudoku rules
     * @return true/false if adhering to rules
     */
    @Override
    public boolean isValid() { //check sudoku rules
        Set<Integer> Set1 = new HashSet<>();
        Set<Integer> Set2 = new HashSet<>();
        Set<Integer> BigSet = new HashSet<>();

        //Check rows
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) { //check rows
                if (this.board[r][c] < 0 || this.board[r][c] > 9 || (this.board[r][c] != 0 && !Set1.add(this.board[r][c]))) { //check for 1-9
                    return false;
                }
                if (this.board[c][r] != 0 && !Set2.add(this.board[c][r])) {
                    return false;
                }
            }
            Set1.clear();
            Set2.clear();
        }

        //Check 3x3 boxes
        for (int br = 0; br < 9; br += 3) {
            for (int bc = 0; bc < 9; bc += 3) {
                for (int sr = 0; sr < 3; sr++) {
                    for (int sc = 0; sc < 3; sc++) {
                        if (this.board[br + sr][bc + sc] != 0 && !BigSet.add(this.board[br + sr][bc + sc])) {
                            return false;
                        }
                    }
                }
                BigSet.clear();
            }
        }
        return true;
    }

    /**
     * Clears the board of values, i.e sets them all to 0
     */
    @Override
    public void clear() {
        this.board = new int[9][9];
    }

    /**
     *
     * @param m the matrix with the digits to insert
     */
    @Override
    public void setMatrix(int[][] m) {
        this.board = m;
    }

    /**
     * Returns cell values from grid. The digit 0 represents an empty box.
     * @return the cell values in the matrix
     */
    @Override
    public int[][] getMatrix() {
        return this.board;
    }
}
