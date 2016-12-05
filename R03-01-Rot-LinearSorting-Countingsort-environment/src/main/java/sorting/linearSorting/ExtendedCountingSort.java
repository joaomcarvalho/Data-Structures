package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array == null || array.length == 0)
			return;
		if (leftIndex < 0)
			leftIndex = 0;
		if (rightIndex > array.length - 1)
			rightIndex = array.length - 1;
		if (leftIndex > rightIndex)
			return;
		
		Integer maior = array[leftIndex];
		Integer menor = array[leftIndex];
		
		for (int i = leftIndex + 1; i <= rightIndex; i++){
			if (array[i].compareTo(maior) > 0)
				maior = array[i];
			if (array[i].compareTo(menor) < 0)
				menor = array[i];
		}
		
		int freq[] = new int[maior - menor + 1];
		
		for (int i = leftIndex; i <= rightIndex; i++){
			freq[array[i] - menor]++;
		}
		
		for (int i = 1; i < freq.length; i++){
			freq[i] += freq[i - 1];
		}
		
		Integer[] b = new Integer[rightIndex - leftIndex + 1];
		
		for (int i = leftIndex; i <= rightIndex; i++){
			b[freq[array[i] - menor] - 1] = array[i];
			freq[array[i] - menor]--;
		}
		
		int j = 0;
		for (int i = leftIndex; i <= rightIndex; i++){
			array[i] = b[j];
			j++;
		}
	}
}
