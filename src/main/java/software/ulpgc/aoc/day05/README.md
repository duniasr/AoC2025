# Día 5: Cafeteria

## El Reto
### Parte A
Los elfos han implementado un nuevo sistema de inventario en la cafetería. La base de datos exporta dos bloques de texto: una lista de rangos inclusivos que definen qué IDs de ingredientes están frescos (ej. `3-5`), y una lista de IDs de ingredientes físicos actualmente disponibles. El objetivo es cruzar los datos y calcular cuántos de los ingredientes disponibles caen dentro de algún rango de frescura.

### Parte B
La lista de ingredientes disponibles se descarta por ser irrelevante. Ahora, el objetivo es calcular la capacidad matemática total del inventario: cuántos IDs únicos en total abarcan los rangos de frescura. Dado que los rangos pueden solaparse espacialmente o ser continuos, es imperativo fusionarlos antes de calcular el recuento total para evitar duplicidades.

## Lógica Estructural
* **`InventoryDatabase`**: Centraliza el parseo del texto y expone dos vías de resolución independientes para responder a las dos preguntas de negocio sin duplicar el estado en memoria.
* **`FreshIdRange`**: Entidad inmutable del dominio (`record`). No es un simple contenedor de datos, sino que posee la inteligencia espacial para calcular su propio tamaño (`size()`), detectar colisiones limítrofes (`canMergeWith()`) y generar nuevas instancias fusionadas (`mergeWith()`).

## Principios de Diseño
### SOLID y Filosofía Arquitectónica
* **S (Responsabilidad Única - SRP):** Separación estricta entre el parseo estructural del archivo bidimensional (`InventoryDatabase`) y la matemática pura de intervalos numéricos (`FreshIdRange`).
* **Pragmatismo (KISS & YAGNI):** Decisión arquitectónica consciente de **omitir la creación de interfaces** (como se hizo en los días 6 y 7). Dado que la naturaleza de los datos de entrada es idéntica para ambas partes y solo varía la pregunta final, la creación de implementaciones separadas habría incurrido en sobreingeniería.
* **Domain-Driven Design (DDD):** Aplicación estricta del **Lenguaje Ubicuo**. Los nombres de clases, variables y métodos (`InventoryDatabase`, `FreshIdRange`, `countFreshAvailableIngredients`) son un reflejo exacto de la terminología del enunciado, logrando un código autodescriptivo.

## Fundamentos y Patrones de Diseño
* **Fluent API y Factory Methods:** Ocultación de los constructores tradicionales en favor de métodos de instanciación semántica (ej. `InventoryDatabase.from(content)`), permitiendo que el flujo principal se lea de izquierda a derecha como inglés natural.
* **Algoritmo de Fusión de Intervalos (Interval Merging):** Implementación clásica de geometría computacional para resolver la Parte B, requiriendo una ordenación previa del conjunto de datos.
* **Extract Method (Clean Code):** Reducción de la complejidad ciclomática extrayendo la compuerta lógica (`if/else`) de la fusión de intervalos a un método semántico privado (`accumulateRange`), manteniendo el pipeline funcional superior completamente declarativo.

## Técnicas de Implementación
* **Inmutabilidad y Ordenación Nativa:** Uso de la palabra clave `record` para garantizar la inmutabilidad de los rangos matemáticos. Implementación de la interfaz `Comparable<FreshIdRange>` para inyectar la lógica de ordenación espacial (de izquierda a derecha basándose en la cota inferior) directamente en la naturaleza del objeto.
* **Expresiones Regulares Multiplataforma:** Uso del token `\\R` de Java para garantizar que el particionado del archivo de texto sea seguro e idéntico tanto en entornos Windows (`\r\n`) como en sistemas UNIX (`\n`).
