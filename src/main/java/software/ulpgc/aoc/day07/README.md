# Día 7: Laboratories

## El Reto
### Parte A
Simular el recorrido de un rayo de taquiones cayendo a través de un colector. El rayo se divide en dos cada vez que choca contra un divisor (`^`). El objetivo es calcular cuántas divisiones se producen en total.

### Parte B
Aplicar la interpretación de los "muchos mundos" a una sola partícula cuántica. Cada choque no divide el rayo físico, sino que bifurca la realidad creando líneas temporales paralelas. El objetivo es calcular cuántos universos paralelos (líneas temporales únicas) existen al finalizar el recorrido.

## Lógica Estructural
* **`Main`**: Controlador principal. Ensambla el sistema leyendo el archivo e inyectando la estrategia física correspondiente en el motor.
* **`TachyonManifold`**: Almacena el diagrama de texto original de forma inmutable, lo recorre fila por fila de arriba a abajo y delega los cálculos a la física inyectada.
* **`TachyonPhysics<SimulationState>`**: Interfaz genérica. Define el contrato estricto que cualquier ley física del universo debe cumplir (`initialize` y `propagateThrough`).
* **`ClassicalTachyonPhysics`**: Implementación de las reglas de la Parte A. Gestiona divisiones simples y coordenadas únicas.
* **`ClassicalTachyonPhysics.State`**: Entidad inmutable (`record`) que encapsula el estado clásico: las posiciones actuales del rayo (`Set<Integer>`) y el contador global de choques.
* **`QuantumTachyonPhysics`**: Implementación de las reglas de la Parte B. Gestiona la bifurcación exponencial de las líneas temporales.
* **`QuantumTachyonPhysics.State`**: Entidad inmutable (`record`) que encapsula el estado cuántico: un diccionario (`Map<Integer, Long>`) que relaciona cada coordenada con la cantidad de universos paralelos que la atraviesan.

## Principios de Diseño
### SOLID
* **S (Responsabilidad Única - SRP):** Separación estricta de tareas. `TachyonManifold` se encarga exclusivamente de la iteración del mapa, mientras que las clases de Física manejan exclusivamente la matemática de colisiones.
* **O (Abierto/Cerrado - OCP):** El motor orquestador (`TachyonManifold`) está abierto a procesar nuevas leyes físicas en el futuro sin que su código interno sufra modificaciones.
* **L (Sustitución de Liskov - LSP):** Cualquier clase que implemente `TachyonPhysics` funcionará dentro del simulador garantizando la integridad matemática del recorrido.
* **I (Segregación de Interfaces - ISP):** La interfaz `TachyonPhysics` es hiperespecífica y no obliga a las clases a implementar métodos innecesarios.
* **D (Inversión de Dependencias - DIP):** El orquestador principal de alto nivel depende de una abstracción genérica, nunca de las implementaciones concretas clásicas o cuánticas.

## Fundamentos y Patrones de Diseño
* **Bajo Acoplamiento y Alta Cohesión:** El recorrido visual/espacial del mapa es completamente independiente de las estructuras de datos que cuentan partículas o universos.
* **Factory Method (Creacional):** Ocultación de la lógica de instanciación y protección de la inmutabilidad mediante el método estático semántico `TachyonManifold.fromDiagram()`, forzando a que la creación pase por un canal controlado (el constructor se mantiene privado).

## Técnicas de Implementación
* **Fluent API:** Diseño de las interfaces del orquestador para permitir el encadenamiento de llamadas (ej. `TachyonManifold.fromDiagram(diagram).simulate(physics)`), logrando que el punto de entrada del programa se lea de forma fluida y declarativa.
* **Good Naming:** Uso de un vocabulario autodescriptivo y alineado con el dominio del problema
