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

## Principios de Diseño y Arquitectura
### SOLID
* **S (Responsabilidad Única - SRP):** El orquestador (`Safe`) no sabe cómo se calcula un giro, y el `Dial` no sabe de dónde vienen los datos.

## Fundamentos y Patrones de Diseño
* **Domain-Driven Design (DDD) y Lenguaje Ubicuo:** Las clases y métodos utilizan la misma terminología que el enunciado del problema (`Safe`, `Dial`, `Rotation`, `document`). El código explica el negocio por sí solo.
* **Static Factory Methods:** Se sustituyen los constructores tradicionales por métodos de factoría semánticos.
    * `Rotation.from(line)`: Para el parseo natural (Fluent API).
    * `Dial.initial()`: Para ocultar la regla de negocio del estado inicial (50, 0) y evitar que el orquestador dependa de "números mágicos".

## Técnicas de Implementación
* **Aritmética Modular Circular:** Para calcular las posiciones en la rueda (0-99), se utiliza el operador módulo (`%`). Se implementa la fórmula de compensación `((pos - steps) % 100 + 100) % 100` para los giros a la izquierda, evitando errores de desbordamiento de índices negativos propios de Java.
* **Inmutabilidad:** Todo el sistema carece de variables temporales de bloque.