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

import simpy
import random

RANDOM_SEED = 1
INTERVAL = 10
RAM_CAPACITY = 100
INSTRUCTIONS_IN_CYCLE = 3
CPU_SPEED = 1
CPU_ACCEPTANCE_QUANTITY = 1
PROCESS_NUMBER = 25

DURATION = []

class OperativeSystem:
    def __init__(self, environment, num, memory, instruction, RAM):
        self.CPU = simpy.Resource(environment, capacity = CPU_ACCEPTANCE_QUANTITY)
        self.RAM = simpy.Container(environment, init = RAM_CAPACITY, capacity = RAM_CAPACITY)
        self.mon_proc = environment.process(self.monitor_RAM(environment))
        self.env =environment

    def monitor_RAM(self, environment):
        while True:
            if self.RAM.level == 0:
                print('No hay memoria disponible, debe esperar a que se libere')
            yield environment.timeout(10)

def run(self, name, mem_request, instruction):
    start = self.environment.now
    
    yield self.RAM.get(mem_request)

    while process_generator.instructions > 0:
            with self.CPU.request() as request:
                yield request
            remaining_instructions = min(INSTRUCTIONS_IN_CYCLE, process_generator.instructions)
            yield environment.timeout(CPU_SPEED)
            process_generator.instructions -= remaining_instructions
            operative_system.RAM.put(process_generator.mem_request)
            if process_generator.instructions <= 0:
                print('El proceso ha terminado')
                end = self.environment.now
                DURATION.append(end - start)
                break
            # Check if the process should wait or be ready again
            else: 
                wait_ready = random.randint(1, 2)
                if wait_ready == 1:
                # Waiting state
                    print('Está en la cola waiting')
                    yield self.environment.timeout(3)
                    print('El proceso salió de la cola waiting y está listo para continuar')
                elif wait_ready == 2:
                # Ready state
                    with self.CPU.request() as request:
                        yield request

def process_generator(env, operative_system):
    for i in range(PROCESS_NUMBER):
        mem_request = random.randint(1, 10)
        instructions = random.randint(1, 10)
        yield environment.timeout(random.expovariate(1.0 / INTERVAL))

environment = simpy.Environment()
operative_system = OperativeSystem(environment)
random.seed(RANDOM_SEED)
environment.process(process_generator(environment, operative_system))
environment.run(until = PROCESS_NUMBER)