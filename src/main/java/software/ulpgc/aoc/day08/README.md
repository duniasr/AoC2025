# Día 8: Playground

## El Reto
### Parte A
Conectar las cajas más cercanas usando la menor cantidad de cable posible, agrupándolas y calculando el tamaño de las redes resultantes.

### Parte B
Continuar conectando los grupos aislados hasta lograr que todas las cajas formen parte de un único y gran circuito eléctrico continuo.

## Algoritmos
He modelado este problema basándome en la teoría de grafos, calculando un **Árbol de Expansión Mínima (MST)** mediante el enfoque codicioso (*Greedy*) del **Algoritmo de Kruskal**.

Este algoritmo conecta todos los puntos de un mapa usando la menor cantidad de cable posible siguiendo estos pasos:
1. Recopila todos los cables posibles que existen en la sala y los ordena del más corto al más largo.
2. Selecciona el cable más corto de la lista.
3. Consulta a la red eléctrica: "¿Estas dos cajas ya estaban conectadas por algún otro lado?". Si la respuesta es NO (circuitos separados), se coloca el cable. Si la respuesta es SÍ (ya formaban parte de la misma red), se descarta el cable para evitar un ciclo cerrado (un cortocircuito inútil).
4. Repite los pasos 2 y 3 hasta que todas las cajas formen un único circuito continuo.

## Lógica Estructural
* **`Playground`**: Controlador principal y orquestador del sistema. Toma las decisiones de conexión basándose en las longitudes de los cables y dirige el flujo de ejecución.
* **`ElectricalGrid`**: Interfaz matemática abstracta. Define el contrato estricto para fusionar circuitos y obtener sus tamaños.
* **`UnionFindElectricalGrid`**: Implementación concreta de la red eléctrica. Responsable de la lógica matemática subyacente para agrupar redes eficientemente (mediante la estructura de datos *Disjoint-Set*).
* **`StringOfLights`**: Entidad inmutable (`record`) portadora de datos que relaciona dos cajas y conoce la distancia que las separa.
* **`JunctionBox`**: Entidad inmutable (`record`) del dominio físico que conoce su ubicación espacial en 3D y calcula distancias geométricas.

## Principios de Diseño
### SOLID
* **S (Responsabilidad Única - SRP):** Cada clase tiene una sola razón de cambio. `JunctionBox` maneja coordenadas geométricas, `UnionFindElectricalGrid` maneja teoría de grafos, y `Playground` maneja el flujo de ejecución del negocio.
* **O (Abierto/Cerrado - OCP):** El sistema orquestador está abierto a usar nuevos algoritmos matemáticos en el futuro sin modificar su código interno.
* **L (Sustitución de Liskov - LSP):** Cualquier clase matemática futura que implemente la interfaz `ElectricalGrid` funcionará sin romper la simulación del gestor.
* **I (Segregación de Interfaces - ISP):** La interfaz `ElectricalGrid` es hiperespecífica, exponiendo únicamente las operaciones necesarias (`tryConnecting` y `getCircuitSizes`).
* **D (Inversión de Dependencias - DIP):** El orquestador de alto nivel (`Playground`) depende de la abstracción (`ElectricalGrid`), nunca de la implementación algorítmica concreta.

## Fundamentos y Patrones de Diseño
* **Bajo Acoplamiento y Alta Cohesión:** El cálculo de las distancias físicas espaciales es completamente independiente de la agrupación abstracta de los circuitos en el motor matemático.
* **Factory Method (Creacional):** Ocultación de la complejidad de instanciación mediante métodos estáticos semánticos (ej. `JunctionBox.parse`, `StringOfLights.between`), protegiendo la creación de los objetos.

## Técnicas de Implementación
* **Inmutabilidad:** Entidades del dominio modeladas como **`records`** nativos de Java, garantizando que su estado interno (como las coordenadas espaciales) no mute tras su creación.
* **Tell, Don't Ask:** El gestor *ordena* a los objetos realizar operaciones sobre sí mismos (`tryConnecting`) en lugar de extraer su estado interno para tomar la decisión desde fuera, respetando el encapsulamiento.
* **Fluent API:** Diseño de métodos en el orquestador que devuelven `this` para encadenar llamadas, logrando que el punto de entrada del programa se lea de forma lógica y declarativa (ej: `.connectShortest(...).productOf(...)`).
* **Good Naming (Lenguaje Ubicuo):** Uso de un vocabulario autodescriptivo y alineado con el dominio del problema (ej. `StringOfLights`, `JunctionBox`, `Playground`), evitando términos informáticos genéricos para que el código documente las reglas de negocio por sí mismo.