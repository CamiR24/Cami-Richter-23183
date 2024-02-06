package Ordenamiento;

import java.util.Arrays;

public class RadixSort {
    // Función para encontrar el número máximo en el array
    static int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    // Función principal de Radix Sort que realiza el ordenamiento
    public static void radixSort(int[] array) {
        int max = getMax(array);
        
        // Realizar el ordenamiento para cada posición del dígito
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(array, exp);
        }
    }

    // Función de ordenamiento por cuenta para ordenar el array basándose en la posición del dígito
    static void countingSort(int[] array, int exp) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Inicializar el array de conteo
        Arrays.fill(count, 0);

        // Contar la frecuencia de cada dígito en la posición actual
        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % 10]++;
        }

        // Actualizar el array de conteo para almacenar las posiciones reales
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Construir el array de salida
        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        // Copiar el array de salida al array original
        System.arraycopy(output, 0, array, 0, n);
    }

    public static void main(String[] args) {
        int[] array = {170, 45, 75, 90, 802, 24, 2, 66};

        System.out.println("Array antes del ordenamiento: " + Arrays.toString(array));

        radixSort(array);

        System.out.println("Array después del ordenamiento: " + Arrays.toString(array));
    }    
}
