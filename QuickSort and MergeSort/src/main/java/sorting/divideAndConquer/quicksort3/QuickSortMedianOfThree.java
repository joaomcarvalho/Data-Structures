package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte: 1. Comparar o elemento mais a
 * esquerda, o central e o mais a direita do intervalo. 2. Ordenar os elemento,
 * tal que: A[left] < A[center] < A[right]. 3. Adotar o A[center] como pivô. 4.
 * Colocar o pivô na penúltima posição A[right-1]. 5. Aplicar o particionamento
 * considerando o vetor menor, de A[left+1] até A[right-1]. 6. Aplicar o
 * algoritmo na metade a esquerda e na metade a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array == null)
			return;
		if (leftIndex < 0)
			leftIndex = 0;
		if (rightIndex > array.length -1)
			rightIndex = array.length - 1;
		if (leftIndex > rightIndex)
			return;
		
		if (leftIndex < rightIndex){
			encontraMediana(array, leftIndex, rightIndex);
			int posicaoPivot = particiona(array, leftIndex , rightIndex);
			sort(array, leftIndex, posicaoPivot - 1);
			sort (array, posicaoPivot + 1, rightIndex);
		}
	}

	private void encontraMediana(T[] array, int leftIndex, int rightIndex) {
		int meio = (leftIndex + rightIndex) / 2;
		
		// ordena left < meio < right
		if (array[rightIndex].compareTo(array[leftIndex]) < 0)
	        Util.swap(array, leftIndex, rightIndex);        
		if (array[meio].compareTo(array[leftIndex]) < 0)
	        Util.swap(array, leftIndex, meio);  
		if (array[rightIndex].compareTo(array[meio]) < 0)
	        Util.swap(array, meio, rightIndex);  
		
		// coloca o meio em right - 1
		Util.swap(array, meio, rightIndex - 1);
	
		
	}
	

	private int particiona(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[rightIndex - 1];
		int i = leftIndex - 1;
		
		for (int j = leftIndex; j < rightIndex - 1; j++){
			if (array[j].compareTo(pivot) <= 0){
				i++;
				Util.swap(array, i, j);
			}
		}
		Util.swap(array, rightIndex - 1, i+1);
		return i + 1;
	}
}
