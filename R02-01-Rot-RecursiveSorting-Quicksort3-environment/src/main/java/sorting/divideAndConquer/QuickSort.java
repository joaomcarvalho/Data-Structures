package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex){
			int posicaoPivot = particiona(array, leftIndex, rightIndex);
			sort(array, leftIndex, posicaoPivot - 1);
			sort (array, posicaoPivot + 1, rightIndex);
		}
	}

	private int particiona(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[leftIndex];
		int i = leftIndex;
		
		for (int j = leftIndex + 1; j <= rightIndex; j++){
			if (array[j].compareTo(pivot) < 0){
				i++;
				T aux = array[j];
				array[j] = array[i];
				array[i] = aux;
			}
		}
		
		T aux = array[leftIndex];
		array[leftIndex] = array[i];
		array[i] = aux;
		return i;
	}
}