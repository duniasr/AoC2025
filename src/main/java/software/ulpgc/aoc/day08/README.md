# Día 8: Optimización de la Red Eléctrica
## El Reto
### Parte A ###
Conectar las cajas más cercanas usando la menor cantidad de cable posible, agrupándolas y calculando el tamaño de las redes resultantes.

### Parte B ###
Continuar conectando los grupos aislados hasta lograr que todas las cajas formen parte de un único y gran circuito eléctrico continuo.
## Algoritmos
He modelado este problema basándome en la teoría de grafos, calculando un **Árbol de Expansión Mínima (MST)**, usando el  enfoque codicioso (*Greedy*) basado en el **Algoritmo de Kruskal**.

Con el Algoritmo Kruskal se conectan todos los puntos de un mapa usando la menor cantidad de cable posible. Los pasos que sigue son:
1. Coge todos los cables posibles que existen en la sala y los ordena del más corto al más largo.
2. Coge el cable más corto de la lista.
3. Pregúntale a la red eléctrica: "¿Estas dos cajas ya estaban conectadas por algún otro lado?" * Si la respuesta es NO (circuitos separados), pones el cable.
Si la respuesta es SÍ (ya formaban parte de la misma red), tiras el cable a la basura porque haría un círculo cerrado (un cortocircuito inútil).
4. Se repiten los pasos 2 y 3 hasta que todas las cajas formen un único circuito.

## Lógica Estructural
* **`Playground`**: Toma las decisiones de conexión basándose en las longitudes y orquesta el sistema.
* **`ElectricalGrid` (y su implementación `UnionFindElectricalGrid`)**: Responsable de la lógica matemática para fusionar circuitos y contar tamaños.
* **`StringOfLights`**: Portador de datos que relaciona dos cajas y conoce la distancia que las separa.
* **`JunctionBox`**: Conoce su ubicación espacial en 3D y calcula distancias matemáticas.

## Principios (SOLID)
* **S (Responsabilidad Única - SRP):** Cada clase tiene una sola razón de cambio. `JunctionBox` maneja coordenadas, `UnionFind` maneja grafos, y `Playground` maneja el flujo de ejecución.
* **O (Abierto/Cerrado - OCP):** El sistema orquestador está abierto a usar nuevos algoritmos matemáticos sin modificar su código interno.
* **L (Sustitución de Liskov - LSP):** Cualquier clase matemática futura que implemente `ElectricalGrid` funcionará sin romper el gestor.
* **I (Segregación de Interfaces - ISP):** La interfaz `ElectricalGrid` es hiperespecífica, exponiendo solo `tryConnecting` y `getCircuitSizes`.
* **D (Inversión de Dependencias - DIP):** El orquestador de alto nivel (`Playground`) depende de la abstracción (`ElectricalGrid`), nunca de la implementación concreta (`UnionFindElectricalGrid`).

## Fundamentos y Patrones de Diseño
* **Bajo Acoplamiento y Alta Cohesión:** El cálculo de distancias es independiente de la agrupación de circuitos.
* **Factory Method (Creacional):** Ocultación de la complejidad de instanciación mediante métodos estáticos semánticos (`JunctionBox.parse`, `StringOfLights.between`).

## Técnicas y Buenas Prácticas (Clean Code)
* **Inmutabilidad:** Entidades del dominio modeladas como **`records`** en Java, garantizando que su estado (coordenadas) no mute tras la creación.
* **Tell, Don't Ask:** El gestor *ordena* realizar conexiones (`tryConnecting`) en lugar de extraer el estado interno de la red para decidir por fuera.
* **Fluent API:** Diseño de métodos que devuelven `this` para encadenar llamadas lógicas legibles (ej: `.connectShortest(...).productOf(...)`).