import cProfile
import random
import time

def merge_sort(arr, ascending=True):
    if len(arr) > 1:
        mid = len(arr) // 2
        left_half = arr[:mid]
        right_half = arr[mid:]

        merge_sort(left_half, ascending)
        merge_sort(right_half, ascending)

        merge(arr, left_half, right_half, ascending)

def merge(arr, left_half, right_half, ascending=True):
    i = j = k = 0

    while i < len(left_half) and j < len(right_half):
        if (ascending and left_half[i] <= right_half[j]) or (not ascending and left_half[i] >= right_half[j]):
            arr[k] = left_half[i]
            i += 1
        else:
            arr[k] = right_half[j]
            j += 1
        k += 1

    while i < len(left_half):
        arr[k] = left_half[i]
        i += 1
        k += 1

    while j < len(right_half):
        arr[k] = right_half[j]
        j += 1
        k += 1

def profile_merge_sort(arr, ascending=True):
    # Utilizar cProfile para perfilar la función de Merge Sort
    profiler = cProfile.Profile()
    profiler.enable()

    merge_sort(arr, ascending)

    profiler.disable()
    profiler.print_stats(sort='cumulative')

def sort_and_time(arr, ascending=True):
    start_time = time.time()
    merge_sort(arr, ascending)
    end_time = time.time()
    return end_time - start_time

if __name__ == "__main__":
    # Ejemplo de uso
    size = int(input("Ingrese la cantidad de elementos: "))
    array = [random.randint(1, 100) for _ in range(size)]

    print("Array antes del ordenamiento:", array)

    choice = input("¿Desea ordenar de manera ascendente (Sí/No)? ").lower()
    ascending_order = choice.startswith('s')

    profile_choice = input("¿Desea perfilar el código (Sí/No)? ").lower()
    profiling_enabled = profile_choice.startswith('s')

    if profiling_enabled:
        profile_merge_sort(array, ascending_order)
    else:
        merge_sort(array, ascending_order)

    print("Array después del ordenamiento:", array)

    sort_again = input("¿Desea ordenar el array nuevamente (Sí/No)? ").lower()
    if sort_again.startswith('s'):
        sorted_array = sorted(array, reverse=not ascending_order)  # Ordena nuevamente el array
        start_time = time.time()
        merge_sort(sorted_array, ascending_order)
        end_time = time.time()
        print("Array ordenado nuevamente:", sorted_array)
        print("Tiempo de ordenamiento para un array ya ordenado:", end_time - start_time)