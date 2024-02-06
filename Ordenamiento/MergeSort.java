package Ordenamiento;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] array) {
        int n = array.length;

        // Tamaño del subarray a fusionar, comenzando desde 1 hasta n/2
        for (int currentSize = 1; currentSize < n; currentSize = 2 * currentSize) {

            // Seleccionar los índices de inicio de los subarrays para fusionar
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currentSize) {
                int mid = Math.min(leftStart + currentSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currentSize - 1, n - 1);

                // Fusionar los subarrays
                merge(array, leftStart, mid, rightEnd);
            }
        }
    }

    // Función para fusionar dos subarrays
    static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Crear subarrays temporales
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copiar datos a subarrays temporales
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        // Fusionar los subarrays temporales

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copiar elementos restantes de leftArray (si los hay)
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // Copiar elementos restantes de rightArray (si los hay)
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};

        System.out.println("Array antes del ordenamiento: " + Arrays.toString(array));

        mergeSort(array);

        System.out.println("Array después del ordenamiento: " + Arrays.toString(array));
    }
}
