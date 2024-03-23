import java.util.ArrayList;

public class Sort {
	/**
	 * Bubble Sort method for array list type
	 * @param <E> type of the array list elements
	 * @param list to be sorted
	 * list is modified and the elements are sorted using 
	 *  their natural ordering
	 */
	public static <E extends Comparable<E>>
	void bubbleSort(ArrayList<E> list) { 
		boolean sorted = false; 
		for (int k=1; 
				k < list.size() && !sorted; k++) {
			sorted = true; 
			for (int i=0; i<list.size()-k; i++) { 
				if (list.get(i).compareTo(list.get(i+1)) > 0) { 
					// swap 
					E temp = list.get(i);
					list.set(i,  list.get(i+1));
					list.set(i+1, temp);
					sorted = false; 
				} 
			} 
		}
	}
}
