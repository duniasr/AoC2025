# Día 1: Secret Entrance

## El Reto
### Parte A
Disponemos de un documento con instrucciones para girar el dial de una caja fuerte (numerado del 0 al 99). Las instrucciones indican la dirección y la cantidad de clics (por ejemplo, `R8` para derecha, `L19` para izquierda). El dial comienza en el 50. El objetivo es calcular la verdadera contraseña, que equivale al número de veces que el dial se detiene exactamente en el 0 al final de cualquier rotación.

### Parte B
Descubrimos un nuevo protocolo de seguridad llamado método `0x434C49434B`. Las reglas cambian: ahora debemos contar cada vez que el dial pasa por el 0 durante el giro, y no solo al finalizar la rotación. Por ejemplo, un giro inmenso de `R1000` hará que el dial cruce el cero 10 veces en un solo movimiento. La contraseña final es la suma de todas estas intersecciones.

## Lógica Estructural
* **`Rotation`**: Modelo de datos común (inmutable). Recibe la cadena de texto cruda y la traduce a una instrucción estructurada con dirección (`char`) y distancia (`int`).
* **`Dial`**: Representa el estado inmutable de la rueda de la caja fuerte (posición actual y puntuación acumulada). Contiene la lógica matemática para transicionar a un nuevo estado tras aplicar una rotación.
* **`SafeDecoder`**: Se encarga de procesar el flujo completo del documento, aplicando las rotaciones al dial de forma secuencial.

## Algoritmos
* **Aritmética Modular Circular:** Para calcular las posiciones en un espacio cerrado (0-99), se utiliza el operador módulo (`%`). Se aplica la fórmula de compensación `((pos - steps) % 100 + 100) % 100` para rotaciones a la izquierda, garantizando que el dial se comporte correctamente en un plano circular y evitando errores de desbordamiento en índices negativos.

## Técnicas de Implementación
* **Inmutabilidad del Modelo:** Todo el sistema se fundamenta en la generación de nuevos estados (`Dial`) en lugar de la mutación de variables. Esto garantiza la integridad del sistema al no existir variables temporales de bloque ni estados globales.

## Patrones de Diseño
* **Patrón Factory Method (Creacional):** Se sustituyen los constructores tradicionales por métodos de factoría semánticos (`Rotation.from(line)`, `Dial.initial()`). Esto oculta la lógica de inicialización (como el estado base 50,0) y evita el uso de números mágicos en el código cliente.

## Principios de Diseño
### SOLID
* **Principio de Responsabilidad Única (SRP):** Existe separación entre las clases: el procesador (`SafeDecoder`) no conoce las reglas matemáticas del giro, y el dial (`Dial`) no conoce el origen ni el formato de los datos de entrada.
