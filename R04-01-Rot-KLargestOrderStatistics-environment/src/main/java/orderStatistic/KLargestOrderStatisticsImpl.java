package orderStatistic;

import java.util.Arrays;

import util.Util;

// 1 4 6 8 9 12

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Voce pode modificar o array original
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		if (array == null)
			return null;
		if (k > array.length)
			return null;
		if (k == array.length)
			return array;
		
		T[] novoArray = (T[]) new Comparable[array.length - (array.length - k)];
		T estatisticaOrdem = orderStatistics(array, array.length - k);
		int j = 0;
		
		for (int i = 0; i < array.length; i++){
			if (array[i].compareTo(estatisticaOrdem)>0){
				novoArray[j] = array[i];
				j++;
			}
		}
		return novoArray;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		if (k > array.length || k < 1)
			return null;
		
		int minIndex, j;
		for (int i = 0; i < k; i++){
			minIndex = i;	
			for (j = i + 1; j < array.length; j++){
				if (array[j].compareTo(array[minIndex]) < 0){
					minIndex = j;
				}
			}
			if (minIndex != i)
				Util.swap(array, i, minIndex);
		}
		return array[k-1];
	}
}
