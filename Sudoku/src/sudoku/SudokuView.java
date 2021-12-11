package Sudoku;

import java.awt.*;
import java.net.URL;
import java.util.Random;

import javax.swing.*;

public class SudokuView {

    private SudokuSolver game;
    private JPanel SudokuGrid;
    private JTextField[][] matrix;


    /**
     *  Default constructor, initialize attributes
     * @param board
     */
    public SudokuView(SudokuSolver board) {
        this.game = board;
        this.matrix = new JTextField[9][9];


        SwingUtilities.invokeLater(() -> createWindow());
    }

    //Private help functions below

    /**
     *  Constructor that creates the GUI for the sudoku solver
     */
    private void createWindow() {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();

        this.SudokuGrid = new JPanel();
        SudokuGrid.setLayout(new GridLayout(9, 9, -1, -1)); //rows,cols,hgap,vgap
        SudokuGrid.setPreferredSize(new Dimension(600, 600));

        //menubar controls
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        /*  Has not yet been implemented
        JMenu File = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        File.add(open);
        menubar.add(File);
        open.addActionListener((e) -> LoadFile());
         */
        JMenu Game = new JMenu("Game");
        JMenuItem gen = new JMenuItem("Generate");
        Game.add(gen);
        menubar.add(Game);
        gen.addActionListener((e) -> GenGame());
        JMenu help = new JMenu("Help");
        JMenuItem rules = new JMenuItem("Rules");
        help.add(rules);
        menubar.add(help);
        rules.addActionListener((e) -> ShowRules());

        //Sudoku controls
        JPanel controlsPanel = new JPanel();
        JButton solveBtn = new JButton("Solve");
        JButton clearBtn = new JButton("Clear");
        controlsPanel.add(solveBtn);
        controlsPanel.add(clearBtn);
        solveBtn.addActionListener((e) -> solveSudoku());
        clearBtn.addActionListener((e) -> clearSudoku());
        controlsPanel.setPreferredSize(new Dimension(600, 50));

        drawSudoku(true);

        pane.add(SudokuGrid, BorderLayout.CENTER);
        pane.add(controlsPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Helper method for generating a randomly filled Sudoku adhering to rules
     */
    private void GenGame() {
        clearSudoku();

        Random rn = new Random();
        int counter = 0;

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (rn.nextInt(100) < 15 && (counter < 20)) {
                    counter++;
                    this.game.add(r, c, (1 + rn.nextInt(8)));
                }
            }
        }
        if (this.game.isValid()) {
        drawSudoku(false);
        } else {
            GenGame();
        }
    }

    /**
     * Helper method for menubar to show the rules of Sudoku
     */
    private void ShowRules() {
        JLabel label = new JLabel("<html><center>Every square has to contain a single number<br>" +
                "        Only the numbers from 1 through to 9 can be used<br>" +
                "        Each 3×3 box can only contain each number from 1 to 9 once<br>" +
                "        Each vertical column can only contain each number from 1 to 9 once<br>" +
                "        Each horizontal row can only contain each number from 1 to 9 once<br>");

        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JOptionPane.showMessageDialog(SudokuGrid, label, "Rules of Sudoku", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     *
     * @param freshBuild    Defines if first build
     */
    private void drawSudoku(boolean freshBuild) {

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

                if (freshBuild) {
                    this.SudokuGrid.add(SingleTextField);
                    matrix[r][c] = SingleTextField;
                }

                matrix[r][c].setText(val);
            }
        }
    }

    /**
     *  Clears the board of inputs in solver and in GUI
     */
    private void clearSudoku() {
        this.game.clear();
        drawSudoku(false);
    }

    /**
     *  Writes out solution matrix on board, if there is a solution,
     *  else, pops up an error dialog window in case of broken rules,
     *  illegal values or if no solution exists.
     */
    private void solveSudoku() {
        boolean failed = false;

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                String val = matrix[r][c].getText();
                if (val.equals("0")) { //handle 0 as input as we usually treat 0 as blank
                    this.game.add(r, c, 0);
                    matrix[r][c].setText("");
                    failed = true;
                }
                if (val.equals("")) {
                    val = "0";
                }
                try {
                    int nbr = Integer.parseInt(val);
                    if (nbr < 0 || nbr > 9) { //here we also deal with < 0 and > 9, this is also taken care of in Sudoku.java
                        throw new IllegalArgumentException(); //make illegal values equal 0
                    }
                    this.game.add(r, c, nbr);
                } catch (Exception err) { //error handling for non integers
                    this.game.add(r, c, 0);
                    matrix[r][c].setText("");
                    failed = true;
                }
            }
        }
        if (!this.game.isValid() || failed) {
            failed = true; //avoid checking slow isvalid function for each value in try catch
            JOptionPane.showMessageDialog(SudokuGrid, "The law of Sudoku has been broken, thus the Sudoku has not been solved!", "Illegal values detected", 0, emojis(3));
        }
        if (!failed) {
            if(this.game.solve()) {
            drawSudoku(false);
                JOptionPane.showMessageDialog(SudokuGrid, "The law of Sudoku has not been broken, thus the Sudoku has been solved!", "Solution found", 1, emojis(1));
            } else { //isvalid true, solve false
                JOptionPane.showMessageDialog(SudokuGrid, "The rules weren't broken but there is no valid solution", "No solution found", 2, emojis(2));
                this.game.clear();
            }

        }
    }

    /**
     *
     * @param i which smiley to choose
     * @return chosen smiley
     */
    private ImageIcon emojis(int i) { // get a smiley icon from the interwebz :)
        ImageIcon smiley = new ImageIcon();
        if (i == 1) {
            try {
                smiley = new ImageIcon(new URL("https://envs.sh/EtW.gif"));
                return smiley;
            }
            catch (Exception e) {
            }
        } else if (i == 2) {
            try {
                smiley = new ImageIcon(new URL("https://envs.sh/EtB.gif"));
                return smiley;
            }
            catch (Exception e) {
            }
        } else if (i == 3) {
        }        try {
            smiley = new ImageIcon(new URL("https://envs.sh/EtI.gif"));
            return smiley;
        }
        catch (Exception e) {
        }
        return smiley;
    }

}
