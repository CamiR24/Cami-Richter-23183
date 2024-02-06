import cProfile
import random

def heap_sort(arr, ascending=True):
    n = len(arr)

    # Construir el montículo
    for i in range(n // 2 - 1, -1, -1):
        heapify(arr, n, i, ascending)

    # Extraer elementos del montículo uno por uno
    for i in range(n - 1, 0, -1):
        # Intercambiar el elemento raíz (máximo o mínimo) con el último elemento
        arr[i], arr[0] = arr[0], arr[i]

        # Llamar a heapify en el montículo reducido
        heapify(arr, i, 0, ascending)

def heapify(arr, n, i, ascending=True):
    root = i
    left_child = 2 * i + 1
    right_child = 2 * i + 2

    # Determinar si se busca un máximo o mínimo
    if ascending:
        if left_child < n and arr[left_child] > arr[root]:
            root = left_child

        if right_child < n and arr[right_child] > arr[root]:
            root = right_child
    else:
        if left_child < n and arr[left_child] < arr[root]:
            root = left_child

        if right_child < n and arr[right_child] < arr[root]:
            root = right_child

    # Si el índice más grande (máximo) o más pequeño (mínimo) no es la raíz
    if root != i:
        # Intercambiar la raíz con el índice más grande o más pequeño
        arr[i], arr[root] = arr[root], arr[i]

        # Recursivamente continuar con la subárbol afectado
        heapify(arr, n, root, ascending)

def profile_heap_sort(arr, ascending=True):
    # Utilizar cProfile para perfilar la función de Heap Sort
    profiler = cProfile.Profile()
    profiler.enable()

    heap_sort(arr, ascending)

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
        profile_heap_sort(array, ascending_order)
    else:
        heap_sort(array, ascending_order)

    print("Array después del ordenamiento:", array)