package Sudoku;

import javax.swing.*;

public class SudokuApplication {
    public static void main(String[] args) {

        try {
            // set windows look and feel
            UIManager.setLookAndFeel(UIManager.
                    getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        }

        SudokuSolver solver = new Sudoku();

        new SudokuView(solver);
    }
}
