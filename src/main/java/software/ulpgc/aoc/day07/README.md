# Día 7: Laboratories

## El Reto
### Parte A
Simular el recorrido de un rayo de taquiones cayendo a través de un colector. El rayo se divide en dos cada vez que choca contra un divisor (`^`). El objetivo es calcular cuántas divisiones se producen en total.

### Parte B
Aplicar la interpretación de los "muchos mundos" a una sola partícula cuántica. Cada choque no divide el rayo físico, sino que bifurca la realidad creando líneas temporales paralelas. El objetivo es calcular cuántos universos paralelos (líneas temporales únicas) existen al finalizar el recorrido.

---

## Diagramas
*Diagrama de clases parte 1:*
![Diagrama de Clases del Día 7](../../../../../../../diagrams/day07a.png)
*Diagrama de clases parte 2:*
![Diagrama de Clases del Día 7](../../../../../../../diagrams/day07b.png)

## Lógica Estructural
* **`TachyonManifold`**: [`TachyonManifold`](TachyonManifold.java) - Almacena el diagrama de texto original de forma inmutable, lo recorre fila por fila de arriba a abajo y delega los cálculos a la física inyectada.
* **`TachyonPhysics<SimulationState>`**: [`TachyonPhysics`](TachyonPhysics.java) - Interfaz genérica. Define el contrato estricto que cualquier ley física del universo debe cumplir (`initialize` y `propagateThrough`).
* **`ClassicalTachyonPhysics`**: [`ClassicalTachyonPhysics`](a/ClassicalTachyonPhysics.java) - Implementación de las reglas de la Parte A. Gestiona divisiones simples y coordenadas únicas.
* **`ClassicalTachyonPhysics.State`**: (Ver definición en [`ClassicalTachyonPhysics`](a/ClassicalTachyonPhysics.java)) - Entidad inmutable (`record`) que encapsula el estado clásico: las posiciones actuales del rayo (`Set<Integer>`) y el contador global de choques.
* **`QuantumTachyonPhysics`**: [`QuantumTachyonPhysics`](b/QuantumTachyonPhysics.java) - Implementación de las reglas de la Parte B. Gestiona la bifurcación de las líneas temporales.
* **`QuantumTachyonPhysics.State`**: (Ver definición en [`QuantumTachyonPhysics`](b/QuantumTachyonPhysics.java)) - Entidad inmutable (`record`) que encapsula el estado cuántico: un diccionario (`Map<Integer, Long>`) que relaciona cada coordenada con la cantidad de universos paralelos que la atraviesan.

---

## Fundamentos
* **Abstracción** *(Simplificación de detalles complejos mediante interfaces o contratos claros)*: La interfaz [`TachyonPhysics`](TachyonPhysics.java) expone contratos limpios para la evolución de partículas, abstrayendo a los clientes de las leyes y cálculos físicos internos de cada dimensión.
* **Modularidad** *(División del programa en módulos bien definidos e independientes)*: Clara separación estructural del sistema: por un lado el contenedor espacial que gestiona el mapa (`TachyonManifold`), y por otro lado los motores que calculan las leyes físicas (`ClassicalTachyonPhysics` y `QuantumTachyonPhysics`).
* **Alta Cohesión y Bajo Acoplamiento** *(Los módulos hacen una sola cosa y dependen mínimamente entre sí)*: Existe alta cohesión porque `TachyonPhysics` implementa las leyes de propagación y `TachyonManifold` dirige el recorrido del mapa. El acoplamiento es bajo porque este contenedor delega los cálculos en una interfaz genérica sin conocer la física interna concreta.
* **Código Expresivo (Clean Code)** *(Código autodocumentado que se lee como lenguaje natural)*: Uso de nombres de dominio claros como `TachyonManifold`, `propagateThrough` y `timelines` que explican la física cuántica de la simulación sin requerir comentarios.

## Principios de Diseño
* **SOLID**
    * **Single Responsibility Principle (SRP)** *(Una clase debe tener un único motivo para cambiar)*: Cada clase gestiona un concepto aislado. `TachyonManifold` tiene la responsabilidad de iterar la cuadrícula espacial. Por su parte, `ClassicalTachyonPhysics` se encarga de calcular la dispersión básica de un rayo, mientras que `QuantumTachyonPhysics` asume únicamente la responsabilidad de contabilizar la bifurcación matemática de los universos paralelos.
    * **Open/Closed Principle (OCP)** *(Abierto a la extensión, cerrado a la modificación)*: El motor de simulación está cerrado a modificaciones. Si se define una nueva física cuántica o clásica avanzada, basta con crear una clase que implemente `TachyonPhysics` sin tocar `TachyonManifold`.
    * **Liskov Substitution Principle (LSP)** *(Los subtipos deben ser sustituibles por sus tipos base)*: `ClassicalTachyonPhysics` y `QuantumTachyonPhysics` pueden pasarse indistintamente al método `simulate` de `TachyonManifold`, y el programa funcionará perfectamente sin alteraciones.
    * **Interface Segregation Principle (ISP)** *(Ningún cliente debe ser forzado a depender de métodos que no usa)*: La interfaz `TachyonPhysics` es minimalista, exponiendo únicamente `initialize` y `propagateThrough`, ocultando todos los cálculos privados complejos al `TachyonManifold`.
    * **Dependency Inversion Principle (DIP)** *(Depender de abstracciones, no de clases concretas)*: `TachyonManifold` depende de la abstracción `TachyonPhysics<T>` y no de sus implementaciones concretas.
* **Composition Over Inheritance (COI)** *(Composición sobre herencia)*: En lugar de crear un `ClassicalManifold` que herede de `TachyonManifold` para cambiar su comportamiento, simplemente le pasamos el tipo de física que queremos usar como parámetro al método `simulate`.
* **Law of Demeter (LoD)** *(Evitar acoplamiento ordenando acciones en lugar de consultar estado interno)*: `TachyonManifold` simplemente ordena `physics.propagateThrough(...)` en cada paso. No extrae los datos del estado cuántico, ni los modifica él mismo para luego guardarlos.
* **Keep It Simple, Stupid (KISS) & You Aren't Gonna Need It (YAGNI)** *(Simplicidad y no añadir código innecesario)*: En la Parte B se pueden llegar a generar miles de millones de rayos. En lugar de intentar simular cada rayo uno por uno (lo que colapsaría el ordenador), usamos un simple `Map<Integer, Long>` que cuenta cuántos rayos hay apilados en cada posición.

## Técnicas
* **Inmutabilidad del Modelo** *(Uso de estados que no cambian una vez creados)*: El colector y los registros internos `State` son inmutables. En cada fila se genera un nuevo `State` descartando el anterior.
* **Inyección de Dependencias** *(Pasar colaboradores/datos en los parámetros de los métodos/constructores)*: La física se inyecta directamente como parámetro al método `simulate` en `TachyonManifold`. (Ver [`TachyonManifold`](TachyonManifold.java)).
* **Genéricos (Polimorfismo Paramétrico)** *(Parametrizar tipos para reutilización y seguridad)*: Se utiliza parametrización mediante `<T>` en la interfaz `TachyonPhysics<T>` para permitir que cada física determine libremente su tipo de estado (`Set` vs `Map`) conservando seguridad de tipos estática.
* **Clases Internas (Static Inner Records)** *(Encapsulación de estructuras de soporte locales)*: Uso de registros estáticos internos `State` dentro de `ClassicalTachyonPhysics` y `QuantumTachyonPhysics` para mantener el estado de la simulación agrupado conceptualmente a su física asociada.
* **Inversión del Control (IoC)** *(Delegar el control del flujo a un motor o framework externo)*: El motor de simulación espacial delega el bucle iterativo de filas a la API de Streams mediante la llamada `diagram.stream().reduce(...)`.
* **Fluent API** *(Encadenamiento de métodos para crear un flujo de lectura fluido)*: En [`Main.java (A)`](a/Main.java) se diseña la arquitectura del dominio para ser encadenada de forma fluida y natural (`TachyonManifold.fromDiagram(diagram).simulate(new ClassicalTachyonPhysics()).totalSplits()`), leyéndose textualmente como: *"Construye el colector desde el diagrama de texto, simúlalo inyectándole la física clásica, y extrae el total de divisiones"*.
* **Good Naming** *(Nombres descriptivos y precisos)*: Nombres de métodos como `fromDiagram`, `simulate` y `initialize`.

## Patrones de Diseño
* **Factory Method (Creacional)** *(Encapsulación de la creación de objetos en métodos estáticos dedicados)*: `TachyonManifold.fromDiagram(List<String>)` actúa como factoría estática para inicializar el plano.

## Paradigmas
* **Orientación a Objetos** *(Organización del software en objetos que encapsulan estado y comportamiento)*: Destaca el uso de la **Abstracción** y el **Polimorfismo** mediante la interfaz `TachyonPhysics`, la cual actúa como un contrato común que permite aislar y esconder las complejas fórmulas matemáticas (el **Encapsulamiento**) dentro de sus respectivas implementaciones.
* **Programación Funcional** *(Estilo declarativo basado en funciones puras y datos inmutables)*: Destaca el uso de sus pilares fundamentales: la **Inmutabilidad** (mediante `records` que se descartan y regeneran en cada estado paralelo) y el **Estilo Declarativo**, eliminando los bucles iterativos en favor de flujos puros de estado.

---

## Verificación y Tests
Las soluciones se validan de forma automática mediante pruebas unitarias escritas con JUnit 5 y AssertJ, estructuradas semánticamente siguiendo el patrón Given-When-Then (Dado un contexto, Cuando ocurre una acción, Entonces se espera un resultado). Esta estructura, heredada del enfoque BDD (Behavior-Driven Development), orienta los tests a comprobar el comportamiento del sistema maximizando su legibilidad.

* **Parte A:** [`aTest`](../../../../../../test/java/test/day07/aTest.java) - Simula la física de taquiones clásica y verifica el número de divisiones del rayo (resultado esperado = `16`).
* **Parte B:** [`bTest`](../../../../../../test/java/test/day07/bTest.java) - Simula el comportamiento cuántico sumando universos y líneas temporales paralelas (resultado esperado = `1048576`).

