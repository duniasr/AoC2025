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
* **`HorizontalWorksheetProcessor` / `VerticalWorksheetProcessor`**: Encargadas del parseo algorítmico puro. Manipulan las cadenas de texto, detectan las columnas vacías, aíslan los bloques espaciales y traducen el texto en bruto a instancias del dominio `MathProblem`.

## Principios de Diseño
### SOLID
* **S (Responsabilidad Única - SRP):** Separación tajante entre la manipulación geométrica de los `Strings` (clases `Processor`) y la resolución matemática (`MathProblem` y `Operator`).
* **O (Abierto/Cerrado - OCP):** El sistema permite añadir nuevos paradigmas de lectura creando nuevas clases, sin necesidad de tocar ni romper la lógica ya validada de las partes A y B.
* **L (Sustitución de Liskov - LSP):** El flujo principal del programa puede ejecutar una `HorizontalCephalopodWorksheet` o una `VerticalCephalopodWorksheet` de manera indistinta a través de la interfaz genérica, manteniendo la estabilidad del cálculo.
* **I (Segregación de Interfaces - ISP):** La interfaz `CephalopodWorksheet` exige solo las dos operaciones estrictamente necesarias para el flujo de trabajo, sin obligar a implementar métodos accesorios.
* **D (Inversión de Dependencias - DIP):** El cálculo final depende de abstracciones matemáticas y de dominio, manteniéndose ciego e independiente de cómo se extrajo el texto del archivo original.

## Fundamentos y Patrones de Diseño
* **Enum-based Strategy:** Uso de enumerados con funciones inyectadas por constructor (`LongBinaryOperator`) para seleccionar el comportamiento matemático en tiempo de ejecución.
* **Tell, Don't Ask:** Aplicado en el objeto `MathProblem`, al cual simplemente se le ordena resolverse (`.solve()`) delegando internamente la ejecución a su operador, en lugar de extraer sus datos internos para calcularlos desde fuera.

## Técnicas de Implementación
* **Fluent API:** Diseño de métodos que devuelven nuevas instancias inmutables para permitir un encadenamiento elegante (ej. `VerticalCephalopodWorksheet.from(lines).unroll().calculateGrandTotal()`).
* **Inmutabilidad:** Uso de `records` para garantizar la pureza de los datos.
* **Package-Private Encapsulation:** Restricción de visibilidad de las clases `Processor` (omitiendo la palabra clave `public`) para convertirlas en mecanismos internos y evitar su acoplamiento accidental desde otros paquetes.