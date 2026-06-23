# Día 3: Lobby

## El Reto
### Parte A
Las escaleras mecánicas del vestíbulo están sin energía. Para encenderlas, debemos utilizar bancos de baterías de emergencia. Cada banco está representado por una secuencia de dígitos (del 1 al 9). El objetivo es extraer exactamente **2 baterías** (manteniendo su orden original de izquierda a derecha) para formar el número de dos dígitos más alto posible. La solución es la suma de los voltajes máximos de todos los bancos.

### Parte B
Las escaleras requieren aún más voltaje para superar la fricción estática, para ello debemos encender exactamente **12 baterías** de cada banco para formar un número de 12 dígitos. El objetivo vuelve a ser calcular la suma total final.

---

## Diagramas
*Diagrama de clases parte 1 y 2:*
![Diagrama de Clases del Día 3](../../../../../../../diagrams/day03ab.png)


## Lógica Estructural
* **`BatteryBank`**: Encapsula la secuencia inmutable de texto de un banco individual. Su única responsabilidad es resolver su propio máximo matemático.
* **`EmergencyPowerSystem`**: Lee el archivo de texto en bruto, lo limpia, instancia los bancos de baterías y actua como agregador para calcular la suma final.

## Algoritmos
* **Algoritmo Voraz (Greedy):** Para alcanzar el máximo voltaje, se emplea una "Ventana Deslizante" restrictiva. El algoritmo selecciona iterativamente el dígito más alto disponible, garantizando que el espacio restante en la secuencia sea suficiente para extraer el número exacto de baterías requeridas en los turnos posteriores.
* **Recursividad:** La extracción secuencial de baterías se modela como una función que se invoca a sí misma. Cada llamada transfiere el estado actualizado (baterías restantes, posición de búsqueda y acumulador) hasta alcanzar el caso base, evitando el uso de estados mutables.

## Técnicas de Implementación
* **Inmutabilidad del Modelo:** Las secuencias de voltajes se encapsulan en entidades inmutables. Una vez definido el banco, su estructura interna no puede ser alterada, asegurando la integridad durante las iteraciones recursivas.

## Patrones de Diseño
* **Patrón Factory Method (Creacional):** La lógica de instanciación y limpieza de datos (gestión de espacios en blanco, líneas vacías) se oculta tras métodos estáticos semánticos (`from()`). Esto protege al sistema frente a la creación de bancos de baterías con datos malformados.
* **Tell, Don't Ask:** El sistema central no extrae datos internos del banco para realizar cálculos; simplemente le ordena al banco realizar su propia operación (`bank.calculateMaxJoltage(target)`), respetando el encapsulamiento.

## Principios de Diseño
### SOLID
* **Principio de Responsabilidad Única (SRP):** Separación estricta entre el parseo de datos de entrada (`EmergencyPowerSystem`) y el cálculo algorítmico de un banco individual (`BatteryBank`).
* **Principio de Abierto/Cerrado (OCP):** El diseño permite ajustar la cantidad de baterías necesarias para encender el sistema sin modificar la estructura interna de los objetos de dominio; la lógica de extracción es genérica y parametrizada.