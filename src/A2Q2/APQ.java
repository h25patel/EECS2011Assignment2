package A2Q2;

import java.util.*;

/**
 * Adaptible priority queue using location-aware entries in a min-heap, based on
 * an extendable array.  The order in which equal entries were added is preserved.
 *
 * @author jameselder
 * @param <E> The entry type.
 */
public class APQ<E> {

    private final ArrayList<E> apq; //will store the min heap
    private final Comparator<E> comparator; //to compare the entries
    private final Locator<E> locator;  //to locate the entries within the queue

    /**
     * Constructor
     * @param comparator used to compare the entries
     * @param locator used to locate the entries in the queue
     * @throws NullPointerException if comparator or locator parameters are null
     */
    public APQ(Comparator<E> comparator, Locator<E> locator) throws NullPointerException {
        if (comparator == null || locator == null) {
            throw new NullPointerException();
        }
        apq = new ArrayList<>();
        apq.add(null); //dummy value at index = 0
        this.comparator = comparator;
        this.locator = locator;
    }

    /**
     * Inserts the specified entry into this priority queue.
     *
     * @param e the entry to insert
     * @throws NullPointerException if parameter e is null
     */
    public void offer(E e) throws NullPointerException {
    //implement this method    
    	if ( e == null){
    		throw new NullPointerException();
    	} 
    	
    	this.apq.add(e);
    	this.locator.set(e, size());
    	this.upheap(size());
    }

   /**
     * Removes the entry at the specified location.
     *
     * @param pos the location of the entry to remove
     * @throws BoundaryViolationException if pos is out of range
     */
    public void remove(int pos) throws BoundaryViolationException {
    //implement this method
    	 E e;
         if (pos < 1 || pos > this.size()) {
             throw new BoundaryViolationException();
         }

         locator.set(apq.get(pos), 0); //null location of removed entry
         e = apq.remove(this.size()); //remove last entry
         if (pos != this.size()+1) { //if element to be removed was not last
             this.apq.set(pos, e); //store last entry at pos
             this.locator.set(e, pos); //update location
             this.downheap(pos); //move to correct location
         }
    }

   /**
     * Removes the first entry in the priority queue.
     **/
    public E poll() {
        //implement this method
    	E element1, element2; // have to take two elements because some methods only takes element as second arguments
        //obtain first element using get method
        element1 = this.apq.get(1);
        //remove the last element
        element2 = this.apq.remove(this.size());
        //if the element was first then set the entry back and update the location
        //at the end using downheap to obtain ideal location
        if((this.size() > 0)) {
            this.apq.set(1, element2);
            this.locator.set(element2, 1); 
            this.downheap(1); 
        } 
        this.locator.set(element1, 0);
        return element1;
    }

  /**
     * Returns but does not remove the first entry in the priority queue.
     */
     public E peek() {
        if (isEmpty()) {
            return null;
        }
        return apq.get(1);
    }

   public boolean isEmpty() {
        return (size() == 0); 
    }

    public int size() {
        return apq.size() - 1; //dummy node at location 0
    }


    /**
     * Shift the entry at pos upward in the heap to restore the minheap property
     * @param pos the location of the entry to move
     */
    private void upheap(int pos) {
        //implement this method
		int positionParent = pos - 1 /2; //truncating division. parent of position

    	if (pos > 0){
    		if (comparator.compare(this.apq.get(pos), this.apq.get(positionParent))>= 0){
    			return;
    		}
    	}
    	 
    	this.swap(pos,positionParent);// swap the positions using swap method
    	pos = positionParent;
    }

    /**
     * Shift the entry at pos downward in the heap to restore the minheap property
     * @param pos the location of the entry to move
     */
    private void downheap(int pos) {
        //implement this method
    	
    	//left and right of the current possions
    	int leftOfPosition = 2 * pos + 1;
    	int rightOfPosition = 2 * pos + 2;
    	//check for left
    	if (leftOfPosition <= this.size()){
    		if (comparator.compare(this.apq.get(leftOfPosition), this.apq.get(rightOfPosition)) >=0){
    			this.swap(pos,leftOfPosition);
    			pos = leftOfPosition;
    		}
    		//check for right
    		if ( rightOfPosition <= this.size()){
    			if (comparator.compare(this.apq.get(leftOfPosition), this.apq.get(rightOfPosition)) >= 0){
    				leftOfPosition = rightOfPosition;
    			}
    		}
    		
    	}
    }

    /**
     * Swaps the entries at the specified locations.
     *
     * @param pos1 the location of the first entry 
     * @param pos2 the location of the second entry 
     */
    private void swap(int pos1, int pos2) {
        //implement this method
    	E elementOne = apq.get(pos1); //element position two
    	E elementTwo = apq.get(pos2); //element position two
    	//set element two at position one
    	this.apq.set(pos1, elementTwo);
    	this.locator.set(this.apq.get(pos1), pos1);
    	//set element one at position two
    	this.apq.set(pos2, elementOne);
    	this.locator.set(this.apq.get(pos2), pos2);
    	//return the result
    	return;
    	
    	
    }
}