# Día 2: Gift Shop

## El Reto
### Parte A
Los elfos de la tienda de regalos necesitan purgar su base de datos. Recibimos un registro con listas de rangos de números (por ejemplo, `11-22`). El objetivo es identificar y sumar todos los IDs inválidos. En esta primera fase, un ID se considera inválido si sus dígitos forman un patrón simétrico que se repite exactamente dos veces (por ejemplo, `1212`, donde la primera mitad es idéntica a la segunda).

### Parte B
Ahora, un ID se considera inválido si está formado por cualquier patrón repetido, sin importar si se repite 2, 3 o más veces (por ejemplo, `123123` o `1111`). El objetivo vuelve a ser calcular la suma total final de los IDs inválidos bajo esta nueva premisa procesando los mismos rangos.

## Lógica Estructural
* **`Range`**: Modelo de dominio de datos implementado como un `record`. Encapsula el intervalo inicial y final, y su única responsabilidad es saber construirse a sí mismo desde un texto y generar los números que lo componen.
* **`GiftShopDatabase`**: Su responsabilidad es transformar listas de rangos y aplicar los algoritmos de filtrado para devolver la suma final.

## Principios de Diseño y Arquitectura
### SOLID y Arquitectura Funcional
* **S (Responsabilidad Única - SRP):** Separación estricta entre la lectura de archivos (`Main`), la representación estructural del dato (`Range`) y la aplicación de las reglas de negocio elfas (`GiftShopDatabase`).

## Fundamentos y Patrones de Diseño
* **Domain-Driven Design (DDD):** El problema se modela en base a entidades del negocio (como la clase `Range`) en lugar de manipular `Strings` genéricos a lo largo de todo el código. Esto hace que el código sea autodocumentado y seguro a nivel de tipado.
* **Static Factory Method:** Se omite el uso de constructores públicos directos a favor del método semántico `from(String text)`. Esto centraliza y aísla la lógica de parseo, asegurando que los objetos nazcan siempre en un estado válido.

## Técnicas de Implementación
* **Expresiones Regulares Optimizadas (Regex):** En la Parte B, la detección de patrones múltiples (`^(.+)\1+$`) se delega al motor Regex de Java. Al precompilar este patrón como una constante `static final`, evitamos la recompilación por cada número, eliminando el mayor cuello de botella del procesamiento de texto.
