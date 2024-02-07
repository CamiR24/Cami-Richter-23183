import cProfile
import random
import time

def heapify(arr, n, i):
    largest = i
    left = 2 * i + 1
    right = 2 * i + 2

    if left < n and arr[left] > arr[largest]:
        largest = left

    if right < n and arr[right] > arr[largest]:
        largest = right

    if largest != i:
        arr[i], arr[largest] = arr[largest], arr[i]
        heapify(arr, n, largest)

def heap_sort(arr, ascending=True):
    n = len(arr)

    for i in range(n // 2 - 1, -1, -1):
        heapify(arr, n, i)

    for i in range(n - 1, 0, -1):
        arr[0], arr[i] = arr[i], arr[0]
        heapify(arr, i, 0)

    if not ascending:
        arr.reverse()

def profile_heap_sort(arr, ascending=True):
    profiler = cProfile.Profile()
    profiler.enable()
    heap_sort(arr, ascending)
    profiler.disable()
    profiler.print_stats(sort='cumulative')

def sort_and_time(arr, ascending=True):
    start_time = time.time()
    heap_sort(arr, ascending)
    end_time = time.time()
    return end_time - start_time

if __name__ == "__main__":
    size = int(input("Ingrese la cantidad de elementos: "))
    array = [random.randint(1, 100) for _ in range(size)]
    print("Array antes del ordenamiento:", array)

    choice = input("¿Desea ordenar de manera ascendente (Sí/No)? ").lower()
    ascending_order = choice.startswith('s')

    profile_choice = input("¿Desea perfilar el código (Sí/No)? ").lower()
    profiling_enabled = profile_choice.startswith('s')

    if profiling_enabled:
        profile_heap_sort(array, ascending_order)
    else:
        heap_sort(array, ascending_order)

    print("Array después del ordenamiento:", array)

    sort_again = input("¿Desea ordenar el array nuevamente (Sí/No)? ").lower()
    if sort_again.startswith('s'):
        start_time = time.time()
        heap_sort(array, ascending_order)
        end_time = time.time()
        print("Array ordenado nuevamente:", array)
        print("Tiempo de ordenamiento para un array ya ordenado:", end_time - start_time)