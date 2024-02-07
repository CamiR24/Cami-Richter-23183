import cProfile
import random
import time

def counting_sort(arr, exp, ascending=True):
    n = len(arr)
    output = [0] * n
    count = [0] * 10

    for i in range(n):
        index = arr[i] // exp
        count[index % 10] += 1

    if not ascending:
        count.reverse()

    for i in range(1, 10):
        count[i] += count[i - 1]

    i = n - 1
    while i >= 0:
        index = arr[i] // exp
        output[count[index % 10] - 1] = arr[i]
        count[index % 10] -= 1
        i -= 1

    for i in range(n):
        arr[i] = output[i]

def radix_sort(arr, ascending=True):
    max_num = max(arr)
    exp = 1
    while max_num // exp > 0:
        counting_sort(arr, exp, ascending)
        exp *= 10

def profile_radix_sort(arr, ascending=True):
    profiler = cProfile.Profile()
    profiler.enable()
    radix_sort(arr, ascending)
    profiler.disable()
    profiler.print_stats(sort='cumulative')

def sort_and_time(arr, ascending=True):
    start_time = time.time()
    radix_sort(arr, ascending)
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
        profile_radix_sort(array, ascending_order)
    else:
        radix_sort(array, ascending_order)

    print("Array después del ordenamiento:", array)

    sort_again = input("¿Desea ordenar el array nuevamente (Sí/No)? ").lower()
    if sort_again.startswith('s'):
        start_time = time.time()
        radix_sort(array, ascending_order)
        end_time = time.time()
        print("Array ordenado nuevamente:", array)
        print("Tiempo de ordenamiento para un array ya ordenado:", end_time - start_time)