package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length == 0)
			return;
		
		Integer maior = array[leftIndex];
		for (int i = leftIndex + 1; i <= rightIndex; i++){
			if (array[i].compareTo(maior) > 0){
				maior = array[i];
			}
		}
		
		int[] freq = new int[maior + 1];
		
		for (int i = leftIndex; i <= rightIndex; i++){
			freq[array[i]]++;
		}
		
		for (int i = 1; i < freq.length; i++){
			freq[i] += freq[i - 1];
		}
		
		Integer[] b = new Integer [rightIndex + 1];
		
		for (int i = rightIndex; i >= leftIndex; i--){
			b[freq[array[i]]-1] = array[i];
			freq[array[i]]--;
		}
		
		for (int i = leftIndex; i <= rightIndex; i++){
			array[i] = b[i];
		}
	}

}
