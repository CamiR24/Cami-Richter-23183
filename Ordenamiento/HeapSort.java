package Ordenamiento;

import java.util.Arrays;

public class HeapSort {
    public static void heapSort(int[] array) {
        int n = array.length;

        // Construir el heap (montículo)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Extraer elementos del heap uno por uno
        for (int i = n - 1; i > 0; i--) {
            // Intercambiar el elemento raíz (máximo elemento) con el último elemento
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Llamar al heapify en el montículo reducido
            heapify(array, i, 0);
        }
    }

    // Función para convertir un subárbol con raíz en el índice i en un montículo
    static void heapify(int[] array, int n, int i) {
        int largest = i; // Inicializar el índice del nodo raíz como el más grande
        int leftChild = 2 * i + 1; // Índice del hijo izquierdo
        int rightChild = 2 * i + 2; // Índice del hijo derecho

        // Si el hijo izquierdo es más grande que la raíz
        if (leftChild < n && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        // Si el hijo derecho es más grande que la raíz
        if (rightChild < n && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        // Si el índice más grande no es la raíz
        if (largest != i) {
            // Intercambiar la raíz con el índice más grande
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            // Recursivamente continuar con la subárbol afectado
            heapify(array, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};
        
        System.out.println("Array antes del ordenamiento: " + Arrays.toString(array));
        
        heapSort(array);
        
        System.out.println("Array después del ordenamiento: " + Arrays.toString(array));
    }
}
