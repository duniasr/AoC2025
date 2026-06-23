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
* **`Coordinate`**: Representa un punto en el espacio 2D y encapsula la lógica geométrica para generar un flujo (`Stream`) de sus propias coordenadas adyacentes.
* **`PaperRollDiagram(a)`**: Representación inmutable del mapa. Su única responsabilidad es procesar un array de `Strings` para filtrar y contar estados matemáticos sin alterar los datos originales.
* **`PaperRollDiagram(b)`**: Representación mutable del mapa. Utiliza una matriz interna de caracteres (`char[][]`) para permitir la alteración del estado (cambiar `@` por `.`) y gestionar el algoritmo de simulación destructiva.

## Algoritmos
* **Recursividad:** El proceso de limpieza se ejecuta mediante una función recursiva (`recursiveRemove`). En cada ciclo, el algoritmo identifica los rollos disponibles, los elimina, y se invoca a sí mismo con el nuevo estado acumulado hasta alcanzar el caso base (sin rollos accesibles).
* **Escaneo de Vecindad (Moore Neighborhood):** Algoritmo de detección espacial que calcula la densidad de rollos en las 8 direcciones adyacentes, aplicando restricciones de límites para evitar excepciones de acceso a memoria fuera de rango.

## Técnicas de Implementación
* **Inmutabilidad del Modelo:** El uso de estructuras de datos inmutables asegura que el mapa original permanezca intacto durante todo el análisis.

## Patrones de Diseño
* **Patrón Factory Method (Creacional):** La instanciación de los diagramas queda oculta tras el método estático `from(String rawDiagram)`. Esto garantiza que la lógica de parseo y transformación de los datos en bruto sea privada, protegiendo al sistema frente a la instanciación de estados inválidos.

## Principios de Diseño
### SOLID
* **Principio de Responsabilidad Única (SRP):** Se ha aplicado una separación entre la Parte A (solo lectura/conteo) y la Parte B (lectura/escritura/mutación). Cada clase tiene una única razón de ser, evitando métodos de "utilidad" cruzada que no pertenezcan a su ciclo de vida.