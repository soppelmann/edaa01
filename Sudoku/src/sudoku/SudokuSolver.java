package Sudoku;

public interface SudokuSolver {


    /**
     * Attempts to solve the grid by defined rules by backtracking.
     * @return true/false if grid was solvable
     */
    boolean solve();

    /**
     * Puts digit in the box row, col.
     *
     * @param row   The row
     * @param col   The column
     * @param digit The digit to insert in box row, col
     * @throws IllegalArgumentException if row, col or digit is outside the range
     *                                  [0..9]
     */
    void add(int row, int col, int digit);

    /**
     *
     * @param row The row of the value
     * @param col The column of the value
     * @throws IllegalArgumentException if row or col is outside the range
     *                                  [0..9]
     */
    void remove(int row, int col);

    /**
     *
     * @param row The row of the value
     * @param col The column of the value
     * @throws IllegalArgumentException if row or col is outside the range
     *                                  [0..9]
     * @return value of the cell
     */
    int get(int row, int col);

    /**
     * Checks that all filled in digits follows the the sudoku rules.
     */
    boolean isValid();

    /**
     * Clears all boxes by setting them to "0"
     */
    void clear();

    /**
     * Fills the grid with the digits in m. The digit 0 represents an empty box.
     *
     * @param m the matrix with the digits to insert
     * @throws IllegalArgumentException if m has the wrong dimension or contains
     *                                  values outside the range [0..9]
     */
    void setMatrix(int[][] m);

    /**
     * Returns cell values from grid. The digit 0 represents an empty box.
     * @return the cell values in the matrix
     */
    int[][] getMatrix();
}

