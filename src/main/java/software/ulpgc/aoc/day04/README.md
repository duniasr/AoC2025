# Día 4: Printing Department

## El Reto
### Parte A
Analizar el plano bidimensional del departamento de impresión para ayudar a los elfos a localizar los rollos de papel (`@`). Una carretilla elevadora (*forklift*) solo puede acceder a un rollo si este tiene menos de 4 rollos adyacentes a su alrededor (considerando las 8 direcciones posibles). El objetivo es contar cuántos rollos son accesibles en el estado inicial de la fábrica.

### Parte B
Simular un proceso de limpieza en cadena. Al retirar los rollos accesibles, los rollos que estaban bloqueados en el interior pueden volverse accesibles en el siguiente turno. El objetivo es calcular el total histórico de rollos que las carretillas logran retirar, hasta que la fábrica se queda atascada y no se pueden sacar más.

---

## Diagramas
*Diagrama de clases parte 1:*
![Diagrama de Clases del Día 4](../../../../../../../diagrams/day04a.png)
*Diagrama de clases parte 2:*
![Diagrama de Clases del Día 4](../../../../../../../diagrams/day04b.png)

## Lógica Estructural
* **`Coordinate`**: [Coordinate.java](./Coordinate.java) - Representa un punto en el espacio 2D y encapsula la lógica geométrica para generar un flujo (`Stream`) de sus propias coordenadas adyacentes.
* **`PaperRollDiagram (A)`**: [PaperRollDiagram.java](./a/PaperRollDiagram.java) - Representación inmutable del mapa. Su única responsabilidad es procesar un array de `Strings` para filtrar y contar estados matemáticos sin alterar los datos originales.
* **`PaperRollDiagram (B)`**: [PaperRollDiagram.java](./b/PaperRollDiagram.java) - Representación mutable del mapa. Utiliza una matriz interna de caracteres (`char[][]`) para permitir la alteración del estado (cambiar `@` por `.`) y gestionar el algoritmo de simulación destructiva.

## Algoritmos
* **Recursividad:** El proceso de limpieza se ejecuta mediante una función recursiva (`recursiveRemove`). En cada ciclo, el algoritmo identifica los rollos disponibles, los elimina, y se invoca a sí mismo con el nuevo estado acumulado hasta alcanzar el caso base (sin rollos accesibles). (Ver [PaperRollDiagram.java (B)](./b/PaperRollDiagram.java#L28-L38)).
* **Escaneo de Vecindad (Moore Neighborhood):** Algoritmo de detección espacial que calcula la densidad de rollos en las 8 direcciones adyacentes, aplicando restricciones de límites para evitar excepciones de acceso a memoria fuera de rango. (Ver generación en [Coordinate.java](./Coordinate.java#L6-L17)).

---

## Evaluación de Principios, Técnicas y Patrones de Diseño

### 1. Fundamentos
* **Abstracción** *(Simplificación de detalles complejos mediante interfaces o contratos claros)*: La clase [Coordinate](./Coordinate.java) abstrae la complejidad de la geometría en 2D, exponiendo contratos limpios para acceder a adyacencias sin revelar sus cálculos internos a los clientes.
* **Modularidad** *(División del programa en módulos bien definidos e independientes)*: Se aísla por completo la geometría espacial ([Coordinate](./Coordinate.java)) de la representación de la fábrica y su estado ([PaperRollDiagram](./a/PaperRollDiagram.java)).
* **Alta Cohesión y Bajo Acoplamiento** *(Los módulos hacen una sola cosa y dependen mínimamente entre sí)*: Existe alta cohesión porque `Coordinate` solo maneja posiciones espaciales y `PaperRollDiagram` gestiona el estado y reglas del montacargas. El acoplamiento es bajo porque la geometría es ciega respecto al contenido visual o estructural del diagrama (no sabe qué es un rollo `@`).
* **Código Expresivo** *(Código autoexplicativo, limpio y fácil de leer)*: El pipeline funcional en `isAccessibleByForklift` (`streamAdjacentCoordinates().filter(this::isWithinBounds).filter(this::isRoll)...`) se lee casi como lenguaje natural, documentando la regla de accesibilidad por sí mismo sin necesidad de comentarios.

### 2. Principios de Diseño
* **SOLID**
    * **Single Responsibility Principle (SRP)** *(Una clase debe tener un único motivo para cambiar)*: `Coordinate` tiene la única responsabilidad de manejar posiciones espaciales. La clase `PaperRollDiagram` de la parte A y la parte B se segregan para separar limpiamente la simulación puramente matemática (solo lectura) de la destructiva (lectura y escritura).
    * **Open/Closed Principle (OCP)** *(Abierto a la extensión, cerrado a la modificación)*: En lugar de modificar el diagrama original de la parte A para acomodar la lógica compleja de la parte B llenándolo de sentencias `if`, se crea una nueva implementación `PaperRollDiagram` (B), manteniendo el código original cerrado a alteraciones.
* **Don't Repeat Yourself (DRY)** *(Evitar la duplicación de lógica)*: El modelo geométrico [Coordinate](./Coordinate.java) se comparte tanto por el diagrama de lectura de la Parte A como por el de simulación de la Parte B.
* **Law of Demeter (LoD) / Tell, Don't Ask** *(Evitar acoplamiento ordenando acciones en lugar de consultar estado interno)*: En la simulación (Parte B), el código cliente simplemente ordena al diagrama limpiarse (`diagram.cleanAllRolls()`) en lugar de intentar inspeccionar la matriz de caracteres desde fuera.
* **Keep It Simple, Stupid (KISS) & You Aren't Gonna Need It (YAGNI)** *(Simplicidad y no añadir código innecesario)*: El forklift consulta adyacencias mediante un simple conteo menor a 4 sobre el Stream, evitando implementar complejos algoritmos de grafos o motores de pathfinding pesados cuando un filtrado funcional es más que suficiente.

### 3. Técnicas
* **Inmutabilidad del Modelo** *(Uso de estados que no cambian una vez creados)*: [Coordinate](./Coordinate.java) es un `record` de Java, impidiendo mutar sus coordenadas una vez instanciado.
* **Métodos Delegados** *(Dividir tareas complejas y delegar sub-operaciones)*: El conteo de rollos accesibles en [PaperRollDiagram (A)](./a/PaperRollDiagram.java#L19-L24) delega en los predicados privados `isRoll` e `isAccessibleByForklift`.
* **Good Naming** *(Nombres descriptivos y precisos)*: Nombres claros como `streamAdjacentCoordinates`, `isAccessibleByForklift` e `isWithinBounds`.

### 4. Patrones de Diseño
* **Factory Method (Creacional)** *(Encapsulación de la creación de objetos en métodos estáticos dedicados)*: Las clases `PaperRollDiagram` utilizan el método estático `from(String rawDiagram)` para aislar el parseo del texto y la conversión a arreglos de caracteres. (Ver [PaperRollDiagram.java (A)](./a/PaperRollDiagram.java#L15-L17)).

### 5. Paradigmas
* **Orientación a Objetos** *(Organización del software en objetos que encapsulan estado y comportamiento)*: Modelado rico del dominio donde la posición y el cálculo de sus coordenadas adyacentes se encapsulan en el objeto `Coordinate`.
* **Programación Funcional** *(Estilo declarativo basado en funciones puras y datos inmutables)*: Uso de Streams para recorrer las coordenadas del plano bidimensional y filtrar adyacencias. (Ver [PaperRollDiagram.java (A)](./a/PaperRollDiagram.java#L42-L46)).

---

## Verificación y Tests
Las soluciones se validan de forma automática mediante pruebas unitarias escritas con **JUnit 5** y **AssertJ**, estructuradas semánticamente siguiendo el patrón **Given-When-Then** (Dado un contexto, Cuando ocurre una acción, Entonces se espera un resultado). Esta estructura, heredada del enfoque **BDD** *(Behavior-Driven Development)*, orienta los tests a comprobar el comportamiento del sistema maximizando su legibilidad.

* **Parte A:** [aTest.java](../../../../../../../../test/java/test/day04/aTest.java) - Valida que se cuenten correctamente los rollos accesibles iniciales en el mapa de prueba (resultado esperado = `5`).
* **Parte B:** [bTest.java](../../../../../../../../test/java/test/day04/bTest.java) - Simula la extracción recursiva en cascada y valida que el total histórico de rollos retirados coincida con la especificación (resultado esperado = `12`).

