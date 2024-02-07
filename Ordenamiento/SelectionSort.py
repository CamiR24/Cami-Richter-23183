import cProfile
import random
import time

def selection_sort(arr, ascending=True):
    n = len(arr)
    for i in range(n):
        min_index = i
        for j in range(i+1, n):
            if (ascending and arr[j] < arr[min_index]) or (not ascending and arr[j] > arr[min_index]):
                min_index = j
        arr[i], arr[min_index] = arr[min_index], arr[i]

def profile_selection_sort(arr, ascending=True):
    profiler = cProfile.Profile()
    profiler.enable()
    selection_sort(arr, ascending)
    profiler.disable()
    profiler.print_stats(sort='cumulative')

def sort_and_time(arr, ascending=True):
    start_time = time.time()
    selection_sort(arr, ascending)
    end_time = time.time()
    return end_time - start_time

if __name__ == "__main__":
    size = int(input("Ingrese la cantidad de elementos: "))
    array = [random.randint(1, 1000) for _ in range(size)]
    print("Array antes del ordenamiento:", array)

    choice = input("¿Desea ordenar de manera ascendente (Sí/No)? ").lower()
    ascending_order = choice.startswith('s')

    profile_choice = input("¿Desea perfilar el código (Sí/No)? ").lower()
    profiling_enabled = profile_choice.startswith('s')

    if profiling_enabled:
        profile_selection_sort(array, ascending_order)
    else:
        selection_sort(array, ascending_order)

    print("Array después del ordenamiento:", array)

    sort_again = input("¿Desea ordenar el array nuevamente (Sí/No)? ").lower()
    if sort_again.startswith('s'):
        start_time = time.time()
        selection_sort(array, ascending_order)
        end_time = time.time()
        print("Array ordenado nuevamente:", array)
        print("Tiempo de ordenamiento para un array ya ordenado:", end_time - start_time)