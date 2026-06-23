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
* **`InventoryDatabase`**: Centraliza el parseo del texto y expone dos vías de resolución independientes para responder a las dos preguntas de negocio sin duplicar el estado en memoria.
* **`FreshIdRange`**: Entidad inmutable del dominio (`record`). No es un simple contenedor de datos, sino que posee la inteligencia espacial para calcular su propio tamaño (`size()`), detectar colisiones limítrofes (`canMergeWith()`) y generar nuevas instancias fusionadas (`mergeWith()`).

## Técnicas de Implementación
* **Inmutabilidad del Modelo:** Al modelar `FreshIdRange` como `record`, se garantiza que los límites del rango sean inmutables desde su nacimiento hasta su fusión, eliminando riesgos de efectos secundarios durante los cálculos matemáticos.

## Patrones de Diseño
* **Patrón Factory Method (Creacional):** La instanciación de `InventoryDatabase` y `FreshIdRange` se oculta tras métodos estáticos semánticos (`from()`). Esto asegura que los objetos se construyan con datos validados, evitando estados inconsistentes desde la base.

## Principios de Diseño
### SOLID
* **Principio de Responsabilidad Única (SRP):** Separación estricta entre el parseo estructural del archivo (`InventoryDatabase`) y la matemática pura de intervalos (`FreshIdRange`).
* **Principio de Abierto/Cerrado (OCP):** El diseño permite añadir nuevas formas de consulta (ej. rangos de caducidad, rangos de precio) simplemente extendiendo la lógica del dominio, sin modificar los métodos de parseo base.