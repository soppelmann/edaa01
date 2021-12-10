package Sudoku;

import java.awt.*;

import javax.swing.*;

public class SudokuView {


    private SudokuSolver game;
    private JPanel SudokuGrid;
    private JTextField[][] matrix;


    /**
     * Constructor, initializes basic attributes
     *
     * @param board SudokuSolver object
     */

    public SudokuView(SudokuSolver board) {
        this.game = board;
        this.matrix = new JTextField[9][9];

        SwingUtilities.invokeLater(() -> createWindow());
    }

    //Private help functions below

    /**
     * Creates the view and initalizes all helper methods'
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
        SudokuGrid.setPreferredSize( new Dimension( 600, 600 ) );


        JPanel controlsPanel = new JPanel(); //collects controls
        JButton solveBtn = new JButton("Solve");
        JButton clearBtn = new JButton("Clear");
        controlsPanel.add(solveBtn);
        controlsPanel.add(clearBtn);
        solveBtn.addActionListener((e) -> solveSudoku());
        clearBtn.addActionListener((e) -> clearSudoku());
        controlsPanel.setPreferredSize( new Dimension( 600, 50 ) );

        drawSudoku(true, false);



        pane.add(SudokuGrid, BorderLayout.CENTER);
        pane.add(controlsPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    //We need to interface the gui matrix (jtextfields) with the sudoku solver
    //a nicer solution would be to just load the entire matrix to textfields, Jpanel maybe?

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
                SingleTextField.setHorizontalAlignment(JTextField.CENTER);
                SingleTextField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));

                // If first build, set attributes and add them to the panel
                if (freshBuild) {
                    this.SudokuGrid.add(SingleTextField);
                    matrix[r][c] = SingleTextField;
                }

                matrix[r][c].setText(val);
            }
        }
    }

    private void clearSudoku() {
        drawSudoku(false, true);
    }

    private void solveSudoku() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                String val = matrix[r][c].getText();
                if (val.equals("")) {
                    val = "0";
                }

                int nbr = Integer.parseInt(val);

                this.game.add(r,c,nbr);
            }
        }
        if(!this.game.isValid()) {
           JOptionPane.showMessageDialog(null, "ILLegal");
        }
        this.game.solve();
        drawSudoku(false,false);

    }

}
