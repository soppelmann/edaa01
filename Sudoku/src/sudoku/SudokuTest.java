package Sudoku;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuTest {

    private SudokuSolver game;
    private final int[][] Prefilled = {{0, 0, 8, 0, 0, 9, 0, 6, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 5},
            {1, 0, 2, 5, 0, 0, 0, 0, 0},
            {0, 0, 0, 2, 1, 0, 0, 9, 0},
            {0, 5, 0, 0, 0, 0, 6, 0, 0},
            {6, 0, 0, 0, 0, 0, 0, 2, 8},
            {4, 1, 0, 6, 0, 8, 0, 0, 0},
            {8, 6, 0, 0, 3, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 4, 0, 0}};

    private final int[][] FaultyPrefilled1 = {{9, 5, 2, 0, 0, 9, 0, 6, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 5},
            {1, 0, 2, 5, 0, 0, 0, 0, 0},
            {0, 0, 0, 2, 1, 0, 0, 9, 0},
            {0, 5, 0, 0, 0, 0, 6, 0, 0},
            {6, 0, 0, 0, 0, 0, 0, 2, 8},
            {4, 1, 0, 6, 0, 8, 0, 0, 0},
            {8, 6, 0, 0, 3, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 4, 0, 0}};

    private final int[][] FaultyPrefilled2 = {{1, 2, 3, 0, 0, 0, 0, 0, 0},
            {4, 5, 6, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 7, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}};


    @BeforeEach
    void setUp() throws Exception {
        this.game = new Sudoku();
    }

    @AfterEach
    protected void tearDown() throws Exception {
        this.game = null;
    }

    //Testfall 1: Lösa tomt sudoku
    @Test
    void testEmpty() {
        assertTrue(this.game.solve(), "Found solution to empty board"); //123456789 solution
    }

    //Testfall 2: Olösligt sudoku, siffrorna uppfyller ej reglerna
    @Test
    void testFaultyfill() {
        this.game.setMatrix(FaultyPrefilled1);
        assertFalse(this.game.solve());
    }


    //Testfall 3: Olösligt sudoku, samt tömma en ruta
    @Test
    void testFaultyFillClear() {
        this.game.setMatrix(FaultyPrefilled2);
        assertFalse(this.game.solve());
        this.game.clear();
        assertTrue(this.game.solve());
    }


    //Testfall 4: Lösbart Sudoku
    @Test
    void testPrefilled() {
        this.game.setMatrix(Prefilled);
        assertTrue(this.game.solve(), "Found solution to given board"); //example solution
    }

    //Testfall 5: Valid Sudoku
    @Test
    void testValidFilled() {
        this.game.setMatrix(Prefilled);
        assertTrue(this.game.isValid(), "Board is valid");
    }

    //Testfall 6: Get value from Sudoku
    @Test
    void testGetValueFromFilled() {
        this.game.setMatrix(FaultyPrefilled2);
        assertEquals(this.game.get(0,0), 1);
    }

    //Testfall 7: add remove get
    @Test
    void testAddRemove() {
        this.game.setMatrix(FaultyPrefilled2);
        assertEquals(this.game.get(0,0), 1);
        this.game.remove(0,0);
        assertEquals(this.game.get(0,0), 0);
        this.game.add(0,0,1);
        assertEquals(this.game.get(0,0), 1);
    }

    //Testfall 8: getMatrix
    @Test
    void testAddGet() {
        this.game.setMatrix(Prefilled);
        assertTrue(this.game.isValid(), "Board is valid");
        assertTrue(this.game.getMatrix() == Prefilled, "Able to retrieve board");
    }

}
