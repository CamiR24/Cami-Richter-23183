package Ordenamiento;

import java.util.Arrays;

public class GnomeSort {
    public static void gnomeSort(int[] array) {
        int index = 0;

        while (index < array.length) {
            if (index == 0 || array[index] >= array[index - 1]) {
                index++;
            } else {
                // Intercambiar elementos y retroceder
                int temp = array[index];
                array[index] = array[index - 1];
                array[index - 1] = temp;
                index--;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};
        
        System.out.println("Array antes del ordenamiento: " + Arrays.toString(array));
        
        gnomeSort(array);
        
        System.out.println("Array después del ordenamiento: " + Arrays.toString(array));
    }
}