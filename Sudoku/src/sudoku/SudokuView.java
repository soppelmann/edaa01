package Sudoku;

import java.awt.*;

import javax.swing.*;

public class SudokuView {

    private SudokuSolver game;
    private JPanel SudokuGrid;
    private JTextField[][] matrix;

    /**
     *
     * @param board
     */
    public SudokuView(SudokuSolver board) {
        this.game = board;
        this.matrix = new JTextField[9][9];

        SwingUtilities.invokeLater(() -> createWindow());
    }

    //Private help functions below

    /**
     *
     */
    private void createWindow() {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();

        //build game here, call draw, add buttons, create textfields etc.
        //pane.add(object)

        //SudokuGrid Jpanel object //this.game = the one we can control //fields = textfields in SudokuGrid
        this.SudokuGrid = new JPanel();
        SudokuGrid.setLayout(new GridLayout(9, 9));
        SudokuGrid.setPreferredSize(new Dimension(600, 600));


        JPanel controlsPanel = new JPanel(); //collects controls
        JButton solveBtn = new JButton("Solve");
        JButton clearBtn = new JButton("Clear");
        controlsPanel.add(solveBtn);
        controlsPanel.add(clearBtn);
        solveBtn.addActionListener((e) -> solveSudoku());
        clearBtn.addActionListener((e) -> clearSudoku());
        controlsPanel.setPreferredSize(new Dimension(600, 50));

        drawSudoku(true, false);

        pane.add(SudokuGrid, BorderLayout.CENTER);
        pane.add(controlsPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    //We need to interface the gui matrix (jtextfields) with the sudoku solver
    //a nicer solution would be to just load the entire matrix to textfields, Jpanel maybe?

    /**
     *
     * @param freshBuild
     * @param clear
     */
    private void drawSudoku(boolean freshBuild, boolean clear) {

        if (clear) {
            this.game.clear();
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                int nbr = this.game.get(r, c);
                String val;
                if (nbr > 0) { //make 0's blank
                    val = String.valueOf(nbr);
                } else {
                    val = "";
                }

                JTextField SingleTextField = new JTextField();
                //Styling -- could move this to another help method, also use actionlistener for async updates.
                SingleTextField.setHorizontalAlignment(JTextField.CENTER);
                SingleTextField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));

                if (((r < 3 || r > 5) && c < 3) || ((r < 3 || r > 5) && c > 5) || ((r > 2 && r < 6) && c > 2 && c < 6)) {
                    SingleTextField.setBackground(new Color(254, 127, 71, 255));
                }

                // If first build, set attributes and add them to the panel
                if (freshBuild) {
                    this.SudokuGrid.add(SingleTextField);
                    matrix[r][c] = SingleTextField;
                }

                matrix[r][c].setText(val);
            }
        }
    }

    /**
     *
     */
    private void clearSudoku() {
        drawSudoku(false, true);
    }

    /**
     *
     */
    private void solveSudoku() { //need to check for no alphebetical characters, replace those with "" maybe and try catch..
        boolean failed = false;

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                String val = matrix[r][c].getText();
                if (val.equals("")) {
                    val = "0";
                }
                try {
                    int nbr = Integer.parseInt(val);
                    this.game.add(r, c, nbr);
                } catch (Exception err) { //error handling for non integers
                    this.game.add(r, c, 0);
                    matrix[r][c].setText("");
                    failed = true;
                }
            }
        }
        if (!this.game.isValid() || failed) {
            failed = true; //avoid checking isvalid for each value for catch
            JOptionPane.showMessageDialog(SudokuGrid, "The law of Sudoku has been broken, thus the Sudoku has not been solved!");
        }
        if (!failed) {
            if(this.game.solve()) {
            drawSudoku(false, false);
                JOptionPane.showMessageDialog(SudokuGrid, "The law of Sudoku has not been broken, thus the Sudoku has been solved!");
            } else {
                JOptionPane.showMessageDialog(SudokuGrid, "The laws aint broken but there aint no solution");
            }

        }
    }

}
