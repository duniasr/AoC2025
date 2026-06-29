# Día 5: Cafeteria

## El Reto
### Parte A
Los elfos han implementado un nuevo sistema de inventario en la cafetería. La base de datos exporta dos bloques de texto: una lista de rangos inclusivos que definen qué IDs de ingredientes están frescos (ej. `3-5`), y una lista de IDs de ingredientes físicos actualmente disponibles. El objetivo es cruzar los datos y calcular cuántos de los ingredientes disponibles caen dentro de algún rango de frescura.

### Parte B
La lista de ingredientes disponibles se descarta por ser irrelevante. Ahora, el objetivo es calcular la capacidad matemática total del inventario: cuántos IDs únicos en total abarcan los rangos de frescura. Dado que los rangos pueden solaparse espacialmente o ser continuos, se fusionan antes de calcular el recuento total para evitar duplicidades.

---

## Diagramas
*Diagrama de clases parte 1:*
![Diagrama de Clases del Día 5](../../../../../../../diagrams/day05a.png)
*Diagrama de clases parte 2:*
![Diagrama de Clases del Día 5](../../../../../../../diagrams/day05b.png)

## Lógica Estructural
* **`InventoryDatabase`**: (Parte A: [`InventoryDatabase`](a/InventoryDatabase.java) / Parte B: [`InventoryDatabase`](b/InventoryDatabase.java)) - Orquesta el flujo de negocio principal, procesando los datos crudos y devolviendo el cálculo final requerido (ya sea el conteo de ingredientes o la capacidad fresca).
* **`FreshIdRange`**: (Parte A: [`FreshIdRange`](a/FreshIdRange.java) / Parte B: [`FreshIdRange`](b/FreshIdRange.java)) - Entidad inmutable del dominio (`record`). No es un simple contenedor de datos, sino que posee la inteligencia espacial para calcular su propio tamaño (`size()`), detectar colisiones limítrofes (`canMergeWith()`) y generar nuevas instancias fusionadas (`mergeWith()`).

---


## Fundamentos
* **Abstracción** *(Simplificación de detalles complejos mediante interfaces o contratos claros)*: `FreshIdRange` expone métodos públicos claros (`covers`, `mergeWith`) que esconden a los clientes los complejos detalles algebraicos de la unión de intervalos.
* **Encapsulamiento** *(Ocultación del estado interno y protección de los datos)*: El objeto de dominio `FreshIdRange` blinda en su interior los límites matemáticos del intervalo (inicio y fin). Ninguna clase externa tiene acceso a modificarlos ni a manipularlos.
* **Modularidad** *(División del programa en módulos bien definidos e independientes)*: División clara entre el procesador agregador del inventario (`InventoryDatabase`) y la entidad matemática de rangos (`FreshIdRange`).
* **Alta Cohesión y Bajo Acoplamiento** *(Los módulos hacen una sola cosa y dependen mínimamente entre sí)*: Existe alta cohesión porque `FreshIdRange` solo contiene la lógica matemática de intervalos e `InventoryDatabase` orquesta su procesamiento. El acoplamiento es bajo porque el modelo numérico ignora cómo se almacenan o extraen del fichero de inventario.
* **Código Expresivo (Clean Code)** *(Código autodocumentado que se lee como lenguaje natural)*: Uso de nombres expresivos como `canMergeWith` o `calculateTotalFreshCapacity` que se leen como una oración natural, eliminando la necesidad de escribir comentarios explicando el código.

## Principios de Diseño
* **SOLID**
    * **Single Responsibility Principle (SRP)** *(Una clase debe tener un único motivo para cambiar)*: `FreshIdRange` maneja únicamente intervalos matemáticos e `InventoryDatabase` orquesta el parseo del texto para obtener datos para la resolución de la lógica de negocio.
    * **Open/Closed Principle (OCP)** *(Abierto a la extensión, cerrado a la modificación)*: Si se añaden nuevos criterios de frescura, `InventoryDatabase` no cambia, simplemente se añaden operaciones sobre la entidad de dominio `FreshIdRange`.
* **Don't Repeat Yourself (DRY)** *(Evitar la duplicación de lógica)*: El parseo numérico de rangos a partir de strings planos se centraliza en el método `from` de la clase `FreshIdRange`.

## Técnicas
* **Inmutabilidad del Modelo** *(Uso de estados que no cambian una vez creados)*: `FreshIdRange` es un `record`. Su fusión no modifica sus límites internos, sino que devuelve una nueva instancia inmutable `FreshIdRange`. (Ver [`FreshIdRange.java (B)`](b/FreshIdRange.java)).
* **Métodos Delegados** *(Dividir tareas complejas y delegar sub-operaciones)*: `calculateTotalFreshCapacity` en [`InventoryDatabase (B)`](b/InventoryDatabase.java) delega el proceso de fusión en la función estática `accumulateRange`.
* **Inversión del Control (IoC)** *(Delegar el control del flujo a un motor o framework externo)*: El motor de reducción de Java se hace cargo del flujo de acumulación de los rangos al llamar a `collect(...)` de la API de Streams. (Ver [`InventoryDatabase.java (B)`](b/InventoryDatabase.java)).
* **Fluent API** *(Encadenamiento de métodos para crear un flujo de lectura fluido)*: En [`InventoryDatabase (B)`](b/InventoryDatabase.java) se utiliza una tubería funcional encadenada (`mergedRanges.stream().mapToLong(FreshIdRange::size).sum()`) que se lee como: *"Toma los rangos fusionados, extrae el tamaño matemático de cada uno, y súmalos todos"*.
* **Good Naming** *(Nombres descriptivos y precisos)*: Términos matemáticos claros como `covers`, `size` y `accumulateRange`.

## Patrones de Diseño
* **Factory Method (Creacional)** *(Encapsulación de la creación de objetos en métodos estáticos dedicados)*: Las factorías estáticas `FreshIdRange.from(...)` e `InventoryDatabase.from(...)` encapsulan de forma segura la creación de objetos validados a partir de entradas de texto crudo.

* **Closure (Funcional)** *(Expresiones que capturan el estado léxico de su entorno)*: Las lambdas del motor de Streams capturan limpiamente variables locales de su contexto envolvente para operarlas sin requerir mutación global.
## Paradigmas
* **Orientación a Objetos** *(Organización del software en objetos que encapsulan estado y comportamiento)*: Destaca el uso del **Encapsulamiento** y la **Abstracción** matemática, envolviendo el comportamiento lógico del intervalo y sus fusiones dentro de un objeto de dominio (`FreshIdRange`) en lugar de tratarlo como una simple tupla de enteros.
* **Programación Funcional** *(Estilo declarativo basado en funciones puras y datos inmutables)*: Destaca el uso de sus pilares fundamentales: la **Inmutabilidad** (el `record` `FreshIdRange` nunca muta al fusionarse, sino que devuelve instancias nuevas) y el **Estilo Declarativo** mediante Streams (`sorted`, `mapToLong`, `sum`) para calcular sumatorios sin usar variables de estado acumulativas.

---

## Verificación y Tests
Las soluciones se validan de forma automática mediante pruebas unitarias escritas con JUnit 5 y AssertJ, estructuradas semánticamente siguiendo el patrón Given-When-Then (Dado un contexto, Cuando ocurre una acción, Entonces se espera un resultado). Esta estructura, heredada del enfoque BDD (Behavior-Driven Development), orienta los tests a comprobar el comportamiento del sistema maximizando su legibilidad.

* **Parte A:** [`aTest`](../../../../../../test/java/test/day05/aTest.java) - Verifica que se cuenten correctamente los ingredientes disponibles que están contenidos dentro de los rangos de frescura (resultado esperado = `4`).
* **Parte B:** [`bTest`](../../../../../../test/java/test/day05/bTest.java) - Verifica la capacidad total del inventario tras la fusión de los rangos solapados y contiguos (resultado esperado = `14`).

