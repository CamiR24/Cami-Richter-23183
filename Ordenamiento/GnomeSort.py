import cProfile
import random

def gnome_sort(arr, ascending=True):
    index = 0
    n = len(arr)

    while index < n:
        if index == 0 or (ascending and arr[index] >= arr[index - 1]) or (not ascending and arr[index] <= arr[index - 1]):
            index += 1
        else:
            # Intercambiar elementos y retroceder
            arr[index], arr[index - 1] = arr[index - 1], arr[index]
            index -= 1

def profile_gnome_sort(arr, ascending=True):
    # Utilizar cProfile para perfilar la función de Gnome Sort
    profiler = cProfile.Profile()
    profiler.enable()
    
    gnome_sort(arr, ascending)
    
    profiler.disable()
    profiler.print_stats(sort='cumulative')

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
        profile_gnome_sort(array, ascending_order)
    else:
        gnome_sort(array, ascending_order)

    print("Array después del ordenamiento:", array)