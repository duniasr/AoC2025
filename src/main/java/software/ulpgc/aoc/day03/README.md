# Día 3: Lobby

## El Reto
### Parte A
Las escaleras mecánicas del vestíbulo están sin energía. Para encenderlas, debemos utilizar bancos de baterías de emergencia. Cada banco está representado por una secuencia de dígitos (del 1 al 9). El objetivo es extraer exactamente **2 baterías** (manteniendo su orden original de izquierda a derecha) para formar el número de dos dígitos más alto posible. La solución es la suma de los voltajes máximos de todos los bancos.

### Parte B
Las escaleras requieren aún más voltaje para superar la fricción estática, para ello debemos encender exactamente **12 baterías** de cada banco para formar un número de 12 dígitos. El objetivo vuelve a ser calcular la suma total final.

## Lógica Estructural
* **`BatteryBank`**: Encapsula la secuencia inmutable de texto de un banco individual. Su única responsabilidad es resolver su propio máximo matemático.
* **`EmergencyPowerSystem`**: Lee el archivo de texto en bruto, lo limpia, instancia los bancos de baterías y actua como agregador para calcular la suma final.

## Principios de Diseño y Arquitectura
### SOLID
* **S (Responsabilidad Única - SRP):** Separación estricta entre el parseo de datos espaciales (`EmergencyPowerSystem`) y el cálculo algorítmico profundo de un banco individual (`BatteryBank`).
* **Tell, Don't Ask:** El orquestador no extrae la secuencia del banco para calcularla desde fuera; simplemente le "ordena" al banco calcular su máximo pasándole el objetivo, respetando la encapsulación.

## Fundamentos y Patrones de Diseño
* **Greedy Algorithm (Algoritmo Voraz):** En lugar de calcular todas las permutaciones posibles (lo cual colapsaría en la Parte B), el algoritmo emplea una "Ventana Deslizante" restrictiva. Busca el número más grande disponible garantizando siempre que queda suficiente espacio físico a la derecha para completar el resto de baterías requeridas.
* **Static Factory Method:** Se ocultan los constructores (`private`) del sistema de emergencia para evitar instanciaciones erróneas. El parseo y limpieza de datos (espacios en blanco, líneas vacías) se aísla detrás del semántico `from(String rawNotes)`.

## Técnicas de Implementación
* **Recursion:** La extracción secuencial de baterías se logra mediante un método que se llama a sí mismo pasando el estado actualizado (baterías restantes, nuevo índice de búsqueda, acumulador).
* **Don't Repeat Yourself (DRY)**: Al inyectar la regla de negocio (targetBatteries) directamente en el método de cálculo logramos que un solo núcleo algorítmico resuelva cualquier variación del puzzle.