package testqueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import queue_delegate.FifoQueue;
import queue_singlelinkedlist.FifoQueue;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class TestAppendFifoQueue {
    private FifoQueue<Integer> q1;
    private FifoQueue<Integer> q2;

    @BeforeEach
    public void setUp() throws Exception {
        q1 = new FifoQueue<Integer>();
        q2 = new FifoQueue<Integer>();
    }

    @AfterEach
    public void tearDown() throws Exception {
        q1 = null;
        q2 = null;
    }

    @Test
    public void testEmptyConcat(){
        q1.append(q2);
        assertEquals(null, q1.peek(), "Front of queue q1 not null");
        assertEquals(null, q2.peek(), "Front of queue a2 not null");
        assertEquals(0, q1.size(), "Size of q1 is not 0");
        assertEquals(0, q2.size(), "Size of q2 is not 0");
    }

    @Test
    public void testAppendEmptyToNonEmpty(){
        int n = 10;

        for(int i = 0; i < n; i++){
            q1.offer(i);
        }

        q1.append(q2);

        // Check size of q1
        assertEquals(n, q1.size(), "Size of q1 is not n");

        // Check order
        for(int i = 0; i < n; i++){
            int j = q1.poll();
            assertEquals(i, j, "poll returned unexpected value!");
        }

        // Check that q2 is empty
        assertEquals(null, q2.peek(), "Peek of q2 is not null");
        assertEquals(0, q2.size(), "Size of q2 is not 0");
    }

    @Test
    public void testAppendNonEmptyToEmpty(){
        int n = 10;

        for(int i = 0; i < n; i++){
            q2.offer(i);
        }

        q1.append(q2);

        // Check size of q1
        assertEquals(n, q1.size(), "Size of q1 is not n");

        // Check order
        for(int i = 0; i < n; i++){
            int j = q1.poll();
            assertEquals(i, j, "poll returned unexpected value!");
        }

        // Check that q2 is empty
        assertEquals(null, q2.peek(), "Peek of q2 is not null");
        assertEquals(0, q2.size(), "Size of q2 is not 0");
    }

    @Test
    public void testNonEmptyQueues(){
        int n = 10;

        for(int i = 0; i < n; i++){
            if(i < n/2){
                q1.offer(i);
            }else{
                q2.offer(i);
            }
        }

        q1.append(q2);

        // Check size of q1
        assertEquals(n, q1.size(), "Size of q1 is not n");

        // Check order
        for(int i = 0; i < n; i++){
            int j = q1.poll();
            assertEquals(i, j, "poll returned unexpected value!");
        }

        // Check that q2 is empty
        assertEquals(null, q2.peek(), "Peek of q2 is not null");
        assertEquals(0, q2.size(), "Size of q2 is not 0");
    }

    @Test
    public void testSelfConcat(){
        try{
            q1.append(q1);
            fail("Should raise IllegalArgumentException");
        }catch(IllegalArgumentException e){
        }
    }


}
