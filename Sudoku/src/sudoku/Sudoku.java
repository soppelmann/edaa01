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

    /**
     *
     * @return
     */
    @Override
    public boolean solve() {
        return solve(0, 0);
    }

    /**
     *
     * @param row
     * @param col
     * @return
     */
    private boolean solve(int row, int col) {
        attempts++;
        if (col == 9) {
            row++;
            if (row == 9 && isValid()) {
                return true; //gått igenom hela matrisen
            }
            col = 0;
        }

        if (attempts > 1000000) { //max attempts, should be able to get a prettier solution
            return false;
        }

        //om vi är på kolumn 9 går vi nästa rad
        //kolla punkter, kolla ifylld, sen sätt i 1-9
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
        remove(row, col); //tack vare rekursion, träd
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
     * @param row
     * @param col
     */
    @Override
    public void remove(int row, int col) {
        board[row][col] = 0;
    }

    /**
     *
     * @param row
     * @param col
     * @return
     */
    @Override
    public int get(int row, int col) {
        return this.board[row][col];
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isValid() { //check sudoku rules
        Set<Integer> Set1 = new HashSet<>();
        Set<Integer> Set2 = new HashSet<>();
        Set<Integer> BigSet = new HashSet<>();

        //Check rows
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) { //check rows
                if (this.board[r][c] < 0 || this.board[r][c] > 9 || (this.board[r][c] != 0 && !Set1.add(this.board[r][c]))) { //added check for g.t 9
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
     *
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
     *
     * @return
     */
    @Override
    public int[][] getMatrix() {
        return this.board;
    }
}
