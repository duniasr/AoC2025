# Día 10: Factory

## El Reto
### Parte A
Restaurar las máquinas de una fábrica encendiendo/apagando luces indicadoras mediante botones interconectados. Al estar en un sistema de interruptores, las matemáticas operan en Álgebra de Boole (Módulo 2, donde 1 + 1 = 0). El objetivo es calcular el número mínimo de pulsaciones para alcanzar el patrón de luces objetivo.

### Parte B
Las reglas físicas de las máquinas cambian: los botones ya no alternan luces, sino que suman +1 a contadores de voltaje. El objetivo es encontrar la combinación exacta de pulsaciones que alcance el voltaje objetivo con el mínimo esfuerzo, teniendo en cuenta que el sistema divide la carga por la mitad en cada ciclo.

---

## Diagramas
*Diagrama de clases parte 1:*
![Diagrama de Clases del Día 10](../../../../../../../diagrams/day10a.png)
*Diagrama de clases parte 2:*
![Diagrama de Clases del Día 10](../../../../../../../diagrams/day10b.png)


## Lógica Estructural
* **`Machine`**: Modelo atómico inmutable (`record`). Representa físicamente la máquina con sus luces y esquemas de cableado.
* **`MachineManual`**: Descompone la sintaxis del texto en bruto y lo transforma en entidades `Machine` puras.
* **`LightConfigurator`**: Servicio matemático (Parte A). Aísla la resolución de sistemas de ecuaciones lineales algebraicas.
* **`JoltageConfigurator`**: Servicio matemático (Parte B). Aísla el motor de búsqueda en árboles de decisión para alcanzar los objetivos de voltaje.

## Algoritmos
* **Eliminación de Gauss-Jordan:** Para la Parte A, la matriz de botones y luces se reduce a su forma escalonada. Al operar en binario puro, las restas tradicionales se sustituyen por operaciones lógicas XOR (`^`), despejando el sistema para encontrar qué botones son obligatorios.
* **Fuerza Bruta Controlada:** Uso de la combinatoria (`1 << n`) para probar exclusivamente el espacio nulo del sistema (los botones que no están restringidos por la matriz de Gauss), minimizando drásticamente el espacio de búsqueda.
* **Búsqueda en Profundidad (DFS) con Memoización:** Para la Parte B, el algoritmo explora las decisiones de pulsación dividiendo los voltajes. Se utiliza un `Map` como caché para recordar el coste mínimo de configuraciones pasadas, podando ramas imposibles o redundantes.

## Técnicas de Implementación
* **Métodos Delegados:** La compleja lógica de extracción con expresiones regulares se rompe en pequeñas funciones privadas (como `extractIndicatorLights` o `extractWiringSchematics`) para que el método de lectura actúe solo como un orquestador limpio.
* **Inyección de Dependencias (DI):** En la Parte B, el mapa de memoización no es un atributo global; se inyecta un nuevo `HashMap` vacío a través de la firma del método principal para garantizar que cada máquina procesada tenga una caché estrictamente aislada.
* **Inmutabilidad del Modelo:** Al modelar `Machine` como un *record*, se garantiza que las luces y esquemas de cableado de la máquina jamás mutan su estado original durante las reducciones matriciales.

## Patrones de Diseño
* **Patrón Factory Method (Creacional):** Toda la lógica de extracción de texto y uso de Expresiones Regulares (`\\)\\s*\\(`) para decodificar corchetes y paréntesis queda encapsulada tras el método estático `MachineManual.readMachineFrom()`.

## Principios de Diseño
### SOLID
* **Principio de Responsabilidad Única (SRP):** El modelo (`Machine`) no sabe cómo parsear texto; el parser (`MachineManual`) no resuelve matemáticas; y los configuradores (`LightConfigurator`, `JoltageConfigurator`) no tienen estado. Las responsabilidades de almacenamiento, traducción y cálculo están separadas.
* **Principio de Abierto/Cerrado (OCP):** Ante el cambio de las reglas físicas entre la Parte A y la Parte B, el modelo `Machine` y su parser no sufrieron modificaciones. Simplemente se añadió un nuevo servicio (`JoltageConfigurator`) abierto a la extensión de la nueva lógica.