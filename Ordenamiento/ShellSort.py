import cProfile
import random
import time

def shell_sort(arr, ascending=True):
    n = len(arr)
    gap = n // 2
    while gap > 0:
        for i in range(gap, n):
            temp = arr[i]
            j = i
            while j >= gap and ((ascending and arr[j - gap] > temp) or (not ascending and arr[j - gap] < temp)):
                arr[j] = arr[j - gap]
                j -= gap
            arr[j] = temp
        gap //= 2

def profile_shell_sort(arr, ascending=True):
    profiler = cProfile.Profile()
    profiler.enable()
    shell_sort(arr, ascending)
    profiler.disable()
    profiler.print_stats(sort='cumulative')

def sort_and_time(arr, ascending=True):
    start_time = time.time()
    shell_sort(arr, ascending)
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
        profile_shell_sort(array, ascending_order)
    else:
        shell_sort(array, ascending_order)

    print("Array después del ordenamiento:", array)

    sort_again = input("¿Desea ordenar el array nuevamente (Sí/No)? ").lower()
    if sort_again.startswith('s'):
        start_time = time.time()
        shell_sort(array, ascending_order)
        end_time = time.time()
        print("Array ordenado nuevamente:", array)
        print("Tiempo de ordenamiento para un array ya ordenado:", end_time - start_time)