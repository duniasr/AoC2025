# Día 6: Cephalopod Worksheets

## El Reto
### Parte A
Procesar y resolver una hoja de cálculo donde los números y operadores están distribuidos en bloques delimitados por columnas vacías. La lectura de los números se realiza de izquierda a derecha y la última fila indica la operación matemática a aplicar al bloque. El objetivo es calcular el "Grand Total" sumando los resultados de todas las operaciones.

### Parte B
Interpretar la misma hoja de cálculo aplicando el sistema de lectura nativo de los cefalópodos: el texto se lee verticalmente. Las columnas, leídas de derecha a izquierda dentro de cada bloque, forman los números reales. El objetivo es recalcular el "Grand Total" bajo este nuevo paradigma espacial.

## Lógica Estructural
* **`CephalopodWorksheet`**: Interfaz genérica. Define el contrato fluido que cualquier paradigma de lectura (horizontal o vertical) debe cumplir para ser procesado (`unroll` y `calculateGrandTotal`).
* **`Operator`**: Motor matemático basado en un `enum`. Asocia los símbolos de texto (`+`, `*`) con sus funciones matemáticas reales y sus elementos "identity" para ser aplicadas sobre colecciones de números.
* **`MathProblem`**: Entidad inmutable (`record`) del dominio. Encapsula un bloque matemático ya traducido (lista de operandos y su operador) y contiene la lógica para resolverse a sí mismo.
* **`HorizontalCephalopodWorksheet` / `VerticalCephalopodWorksheet`**: Su única función es proporcionar un punto de entrada limpio, coordinar el flujo y delegar el trabajo complejo al procesador correspondiente.
* **`HorizontalWorksheetProcessor` / `VerticalWorksheetProcessor`**: Manipulan las cadenas de texto, detectan las columnas vacías, aíslan los bloques espaciales y traducen el texto en bruto a instancias del dominio `MathProblem`.

## Técnicas de Implementación
* **Inmutabilidad:** Uso extensivo de `records` y estructuras de datos inmutables en los procesadores. Cualquier operación de "desenrollado" devuelve una nueva instancia con el texto formateado, evitando efectos secundarios en la lectura original.

## Patrones de Diseño
* **Patrón Factory Method (Creacional):** La lógica de instanciación de las hojas de trabajo queda oculta tras métodos estáticos `from(List<String>)`. Esto garantiza que los objetos se construyan con las líneas de texto ya validadas, protegiendo al sistema de estados inconsistentes.

## Principios de Diseño
### SOLID
* **Principio de Responsabilidad Única (SRP):** Existe una separación tajante entre la manipulación geométrica de `Strings` (clases `Processor`) y la resolución matemática (`MathProblem` y `Operator`).
* **Principio de Abierto/Cerrado (OCP):** El diseño es altamente extensible. Para implementar nuevos paradigmas de lectura (por ejemplo, diagonal o en espiral), basta con implementar un nuevo procesador y una nueva clase de hoja, sin tocar la lógica ya validada de las partes existentes.
* **Inversión de Dependencias (DIP):** El flujo principal depende de la interfaz `CephalopodWorksheet`. Esto desacopla la ejecución de la lógica matemática de los detalles de implementación de la lectura espacial.