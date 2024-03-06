##############################################################

#                      DOCUMENTACION INTERNA
#
#   Nombre del programa: OperativeSystem.py
#
#   Programador: Camila Richter
#                
#   Lenguaje: Python 3.10.6
# 
#   Recursos: 
#           Documentación y ejemplos de SimPy, en específico el ejemplo del gas_station
#           ChatGpt3.5, para la creación de los gráfico y estadísticas
#           Instrucciones y ayudas proporcionadas en el pdf de la UVG
# 
##############################################################

import os
import simpy
import random
import numpy as np

RANDOM_SEED = 1
INTERVAL = 1
RAM_CAPACITY = 100
INSTRUCTIONS_IN_CYCLE = 3
CPU_SPEED = 1
CPU_ACCEPTANCE_QUANTITY = 2
PROCESS_NUMBER = 200
SIM_DURATION = 10000

DURATION = []

random.seed(RANDOM_SEED)

class OperativeSystem:
    def __init__(self, environment):
        print('Definiendo')
        self.CPU = simpy.Resource(environment, capacity = CPU_ACCEPTANCE_QUANTITY)
        self.RAM = simpy.Container(environment, init = RAM_CAPACITY, capacity = RAM_CAPACITY)
        self.mon_proc = environment.process(self.monitor_RAM(environment))
        self.env =environment

    def monitor_RAM(self, environment):
        while True:
            if self.RAM.level == 0:
                print('No hay memoria disponible, debe esperar a que se libere')
            yield environment.timeout(10)

    def run_Operative_System(self, mem_request, instructions):
        print('Running')
        start = self.env.now
        
        yield self.RAM.get(mem_request)

        while instructions > 0:
                with self.CPU.request() as request:
                    yield request
                remaining_instructions = min(INSTRUCTIONS_IN_CYCLE, instructions)
                yield self.env.timeout(CPU_SPEED)
                instructions -= remaining_instructions
                operative_system.RAM.put(mem_request)
                if instructions <= 0:
                    print('El proceso ha terminado')
                    end = self.env.now
                    DURATION.append(end - start)
                    break
                else: 
                    wait_ready = random.randint(1, 2)
                    if wait_ready == 1:
                    # Waiting
                        print('Está en la cola waiting')
                        yield self.env.timeout(3)
                        print('El proceso salió de la cola waiting y está listo para continuar')
                    elif wait_ready == 2:
                    # Ready
                        with self.CPU.request() as request:
                            yield request

def process_generator(operative_system, environment):
    print('Generando proceso')
    print(f'Cantidad de procesos: {PROCESS_NUMBER}')
    for i in range(PROCESS_NUMBER):
        mem_request = random.randint(1, 10)
        instructions = random.randint(1, 10)
        environment.process(operative_system.run_Operative_System(mem_request,instructions))
        yield environment.timeout(random.expovariate(1.0 / INTERVAL))
        print(f'Memoria del proceso {i}: {mem_request}')
        print(f'Instrucciones del proceso {i}: {instructions}')


environment = simpy.Environment()
operative_system = OperativeSystem(environment)

environment.process(process_generator(operative_system, environment))
environment.run(until = SIM_DURATION)

# Calcular el promedio y la desviación estándar de los tiempos de duración
mean_duration = np.mean(DURATION)
std_dev_duration = np.std(DURATION)

print("Promedio del tiempo de duración:", mean_duration)
print("Desviación estándar del tiempo de duración:", std_dev_duration)
