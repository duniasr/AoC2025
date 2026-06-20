# Día 4: Printing Department

## El Reto
### Parte A
Analizar el plano bidimensional del departamento de impresión para ayudar a los elfos a localizar los rollos de papel (`@`). Una carretilla elevadora (*forklift*) solo puede acceder a un rollo si este tiene menos de 4 rollos adyacentes a su alrededor (considerando las 8 direcciones posibles). El objetivo es contar cuántos rollos son accesibles en el estado inicial de la fábrica.

### Parte B
Simular un proceso de limpieza en cadena. Al retirar los rollos accesibles, los rollos que estaban bloqueados en el interior pueden volverse accesibles en el siguiente turno. El objetivo es calcular el total histórico de rollos que las carretillas logran retirar, hasta que la fábrica se queda atascada y no se pueden sacar más.

## Lógica Estructural
* **`Coordinate`**: Representa un punto en el espacio 2D y encapsula la lógica geométrica para generar un flujo (`Stream`) de sus propias coordenadas adyacentes.
* **`PaperRollDiagram(a)`**: Representación inmutable del mapa. Su única responsabilidad es procesar un array de `Strings` para filtrar y contar estados matemáticos sin alterar los datos originales.
* **`PaperRollDiagram(b)`**: Representación mutable del mapa. Utiliza una matriz interna de caracteres (`char[][]`) para permitir la alteración del estado (cambiar `@` por `.`) y gestionar el algoritmo de simulación destructiva.

## Principios de Diseño
### SOLID
* **S (Responsabilidad Única - SRP):** Se separó por completo la Parte A de la Parte B para garantizar que ninguna clase contenga métodos (ej. `removeRoll`) que no utiliza. Cada diagrama tiene una única razón para existir y cambiar.

## Fundamentos y Patrones de Diseño
* **Static Factory Method:** Se ocultan los constructores (`private`) para evitar que la lógica de inicialización y parseo de matrices se filtre al cliente. La instanciación se controla exclusivamente mediante el método semántico `from(String rawDiagram)`.
* **Domain-Driven Design (DDD):** El código habla el Lenguaje Ubicuo del problema real mediante un *Fluent API*. Las condicionales se leen como reglas de negocio naturales: `.filter(this::isRoll).filter(this::isAccessibleByForklift)`.

## Técnicas de Implementación
* **Recursividad:** Se utiliza recursividad (`recursiveRemove`), pasando el estado acumulado como parámetro hasta alcanzar la condición de parada (lista de extracción vacía).
* **Fluent API:** Diseño de métodos que devuelven nuevas instancias inmutables para permitir un encadenamiento elegante.
* **Inmutabilidad:** Uso de `records` para garantizar la pureza de los datos.
* **Good Naming:** Uso de un vocabulario autodescriptivo y alineado con el dominio del problema
