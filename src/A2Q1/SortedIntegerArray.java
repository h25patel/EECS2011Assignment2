package A2Q1;
import java.util.*;
/**
 * Represents a sorted integer array.  Provides a method, kpairSum, that
 * determines whether the array contains two elements that sum to a given
 * integer k.  Runs in O(n) time, where n is the length of the array.
 * @author jameselder
 */
public class SortedIntegerArray {

    protected int[] sortedIntegerArray;

    public SortedIntegerArray(int[] integerArray) {
        sortedIntegerArray = integerArray.clone();
        Arrays.sort(sortedIntegerArray);
    }

/**
 * Determines whether the array contains two elements that sum to a given
 * integer k. Runs in O(n) time, where n is the length of the array.
 * @author jameselder
 */
    public boolean kPairSum(Integer k) {
 //implement this method
            return CalculateKPair(k, 0, sortedIntegerArray.length-1);
        }
    	//my method to calculate kPair with long using two arrays.
        private boolean CalculateKPair(Integer k, int ArrayH, int ArrayP) {
              if (ArrayH >= ArrayP) {
                  return false;
              }
              //casting ArrayH and ArrayP into long
              long  kPaircalc = sortedIntegerArray[ArrayH] + sortedIntegerArray[ArrayP];
              //check for the kpair greater then the Integer K then return k value with ArrayH value and Arrayp values decreased by 1
              if (kPaircalc > k) {
                  return CalculateKPair(k, ArrayH, ArrayP - 1);
              } else if (kPaircalc < k) {
             //check for the kpair less then the Integer K then return k value with ArrayH value and Arrayp values decreased by 1
                  return CalculateKPair(k, ArrayH + 1, ArrayP);
              } else {
            	  //otherwise return true
            	  return true;
              }
          }
        	
                
    
    
    
}