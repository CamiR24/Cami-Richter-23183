package Ordenamiento;

import java.util.Arrays;

public class SelectionSort {
    public static void selectionSort(int[] array) {
        int n = array.length;

        // Iterar sobre todo el array
        for (int i = 0; i < n - 1; i++) {
            // Encontrar el índice del elemento mínimo en el subarray no ordenado
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // Intercambiar el elemento mínimo con el primer elemento no ordenado
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = {64, 25, 12, 22, 11};

        System.out.println("Array antes del ordenamiento: " + Arrays.toString(array));

        selectionSort(array);

        System.out.println("Array después del ordenamiento: " + Arrays.toString(array));
    }    
}
