package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array == null){
			return;
		}
		if (leftIndex < 0){
			leftIndex = 0;
		}
		if (rightIndex > array.length - 1){
			rightIndex = array.length -1;
		}
		if (leftIndex > rightIndex){
			return;
		}
		
		for (int i = leftIndex; i <= rightIndex; i++){
			T atual =  array[i];
			int j = i;
			while ((j > leftIndex) && array[j - 1].compareTo(atual) > 0){
				array[j] = array[j - 1];
				j--;
			}
			array[j] = atual;
		}
	}
}
