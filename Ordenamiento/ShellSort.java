package Ordenamiento;

import java.util.Arrays;

public class ShellSort {
    public static void shellSort(int[] array) {
        int n = array.length;

        // Inicializar el intervalo (gap)
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Realizar el ordenamiento usando el algoritmo de inserción con el intervalo actual
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;

                // Mover los elementos del subarray ordenado hacia adelante
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }

                // Insertar el elemento en la posición correcta
                array[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Array antes del ordenamiento: " + Arrays.toString(array));

        shellSort(array);

        System.out.println("Array después del ordenamiento: " + Arrays.toString(array));
    }    
}
