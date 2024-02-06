package Ordenamiento;

import java.util.Arrays;
import java.util.Stack;

public class QuickSort {
    public static void quickSort(int[] array) {
        int low = 0;
        int high = array.length - 1;

        // Crear una pila para almacenar los límites del subarray
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);

        // Iterativamente procesar subarrays
        while (!stack.isEmpty()) {
            high = stack.pop();
            low = stack.pop();

            // Obtener el índice del pivote después de particionar
            int pivotIndex = partition(array, low, high);

            // Si hay elementos a la izquierda del pivote, agregar el subarray izquierdo
            if (pivotIndex - 1 > low) {
                stack.push(low);
                stack.push(pivotIndex - 1);
            }

            // Si hay elementos a la derecha del pivote, agregar el subarray derecho
            if (pivotIndex + 1 < high) {
                stack.push(pivotIndex + 1);
                stack.push(high);
            }
        }
    }

    // Función para particionar el array y devolver el índice del pivote
    static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                // Intercambiar array[i] y array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Intercambiar array[i+1] y array[high] (pivote)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};

        System.out.println("Array antes del ordenamiento: " + Arrays.toString(array));

        quickSort(array);

        System.out.println("Array después del ordenamiento: " + Arrays.toString(array));
    }
}
