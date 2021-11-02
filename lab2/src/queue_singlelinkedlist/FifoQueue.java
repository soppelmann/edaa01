package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) { //ignore false as we ignore fixed size lists
		QueueNode<E> n = new QueueNode<>(e);

		if(last == null) {
			n.next = n;
		} else {
			n.next = last.next;

			last.next = n;
		}

		last = n;
		size++;


		return false;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return this.size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if (isEmpty()) {
			return null;
		} else {
			QueueNode<E> temp = new QueueNode<>(last.next.element);
			last.next = last.next.next;
			size--;
			if(size == 0) {
				last = null;
			}
			return temp.element;
		}
	}

	/**
	 * Appends the specified queue to this queue
	 * post: all elements from the specified queue are appended
	 * to this queue. The specified queue (q) is empty after the call.
	 * @param q the queue to append.
	 * @throws IllegalArgumentException if this queue and q are identical.
	 */
	public void append(FifoQueue<E> q){
		if (q == this) {
			throw new IllegalArgumentException();
		}
		if (last == null) {
			last = q.last;
		} else if (q.last != null) {
			QueueNode<E> app_last = q.last;
			QueueNode<E> app_first = q.last.next;

			app_last.next = last.next;
			last.next = app_first;

			last = app_last;
		}

		size += q.size;

		q.last = null; //Garbage collector might handle this, destroy()
		q.size = 0;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

	private class QueueIterator implements Iterator<E> { //inre klass
		private QueueNode<E> pos;

		private QueueIterator() {
			if (last == null) {
				pos = null;
			} else {
				pos = last.next;
			}
		}

		@Override
		public boolean hasNext() {
			return pos != null;
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			QueueNode<E> temp = pos;
			if (pos != last) { //dont go around in circles.
				pos = pos.next;
			} else {
				pos = null;
			}
			return temp.element;
		}
	}
}
