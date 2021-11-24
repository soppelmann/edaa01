package bst;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bst.BinarySearchTree;

import static org.junit.jupiter.api.Assertions.*;


public class TestBST {

    private BinarySearchTree<Integer> tree1;
    private BinarySearchTree<Integer> tree2;
    private BinarySearchTree<String> tree3;

    @BeforeEach
    void setUp() throws Exception {
        tree1 = new BinarySearchTree<Integer>();

        // Egen konstruktor, Skevt träd
        tree2 = new BinarySearchTree<Integer>((e1, e2) -> e1 - e2);


    }

    @AfterEach
    protected void tearDown() throws Exception {
        System.out.println("Running: tearDown");
        tree1 = null;
        tree2 = null;
        assertNull(tree1);
        assertNull(tree2);
    }

    @Test
    public void testAddSizeHeight() {
        System.out.println("Running: testAddSizeHeight");
        assertTrue(tree1.add(1));
        assertTrue(tree1.add(2));
        assertTrue(tree1.add(3));

        assertFalse(tree1.add(3));

        assertEquals(tree1.size(), 3);

        assertTrue(tree2.add(1));
        assertEquals(tree2.size(), 1);

    }

    @Test
    public void testAddSizeClearSize() {
        assertTrue(tree1.add(1));
        assertTrue(tree1.add(52));
        assertTrue(tree1.add(8));
        assertEquals(tree1.size(), 3);
        tree1.clear();
        assertEquals(tree1.size(), 0);
    }
}
