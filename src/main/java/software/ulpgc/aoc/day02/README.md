# Día 2: Gift Shop

## El Reto
### Parte A
Los elfos de la tienda de regalos necesitan limpiar su base de datos. Recibimos un registro con listas de rangos de números (por ejemplo, `11-22`). El objetivo es identificar y sumar todos los IDs inválidos. En esta primera fase, un ID se considera inválido si sus dígitos forman un patrón simétrico que se repite exactamente dos veces (por ejemplo, `1212`, donde la primera mitad es idéntica a la segunda).

### Parte B
Ahora, un ID se considera inválido si está formado por cualquier patrón repetido, sin importar si se repite 2, 3 o más veces (por ejemplo, `123123` o `1111`). El objetivo vuelve a ser calcular la suma total final de los IDs inválidos bajo esta nueva premisa procesando los mismos rangos.

## Lógica Estructural
* **`Range`**: Modelo de dominio de datos implementado como un `record`. Encapsula el intervalo inicial y final, y su única responsabilidad es saber construirse a sí mismo desde un texto y generar los números que lo componen.
* **`GiftShopDatabase`**: Coordina el flujo de datos desde el parseo inicial, aplica los filtros de validación basados en las reglas elfas y agrega los resultados finales.

## Algoritmos
* **Filtrado por Patrones Regulares (Regex):** Se utiliza el motor de expresiones regulares para identificar secuencias repetitivas en los IDs. Para la Parte B, el patrón `^(.+)\1+$` detecta dinámicamente cualquier subcadena que se repita, independientemente de su longitud o cantidad de repeticiones.

## Técnicas de Implementación
* **Inmutabilidad del Modelo:** Al modelar `Range` como un `record`, se garantiza que los límites del intervalo sean inmutables, eliminando errores de estado compartido durante la generación de secuencias.

## Patrones de Diseño
* **Patrón Factory Method (Creacional):** La lógica de parseo de los rangos (gestión de comas y guiones) se oculta tras el método estático `Range.from(String text)`. Esto protege al sistema frente a la creación de intervalos malformados.

## Principios de Diseño
### SOLID
* **Principio de Responsabilidad Única (SRP):** Separación estricta entre la lectura de archivos, la representación estructural del dato (`Range`) y la aplicación de las reglas de negocio elfas (`GiftShopDatabase`).
* **Principio de Abierto/Cerrado (OCP):** La lógica de validación es fácilmente extensible. Si las reglas de "invalidez" de los IDs cambian en el futuro, solo es necesario modificar el predicado de filtrado sin alterar la infraestructura de procesamiento de rangos.