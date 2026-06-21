# Día 8: Playground

## El Reto
### Parte A
Conectar las cajas más cercanas usando la menor cantidad de cable posible, agrupándolas y calculando el tamaño de las redes resultantes.

### Parte B
Continuar conectando los grupos aislados hasta lograr que todas las cajas formen parte de un único y gran circuito eléctrico continuo.

---

## Lógica Estructural
* **`Playground`**: Toma las decisiones de conexión basándose en las longitudes de los cables y dirige el flujo de ejecución.
* **`ElectricalGrid`**: Interfaz matemática abstracta. Define el contrato estricto para fusionar circuitos y obtener sus tamaños.
* **`UnionFindElectricalGrid`**: Implementación concreta de la red eléctrica. Responsable de la lógica matemática subyacente para agrupar redes eficientemente (mediante la estructura de datos *Disjoint-Set*).
* **`StringOfLights`**: Entidad inmutable (`record`) portadora de datos que relaciona dos cajas y conoce la distancia que las separa.
* **`JunctionBox`**: Entidad inmutable (`record`) del dominio físico que conoce su ubicación espacial en 3D y calcula distancias geométricas.

## Algoritmos
* **Algoritmo de Kruskal (Árbol de Expansión Mínima - MST):** Enfoque codicioso (*Greedy*) que conecta todos los puntos del mapa usando la menor cantidad de cable posible. Selecciona iterativamente los cables más cortos y consulta a la red si las cajas ya están conectadas, descartando aquellos que generen ciclos cerrados (cortocircuitos inútiles) hasta formar un único circuito continuo.
* **Disjoint-Set (Union-Find):** Estructura de datos avanzada implementada para soportar a Kruskal. Permite agrupar las cajas eléctricas en conjuntos disjuntos(su intersección es vacía) y resolver si dos cajas ya pertenecen a la misma red, optimizando la detección de cortocircuitos.

## Técnicas de Implementación
* **Inmutabilidad del Modelo:** Al modelar entidades del dominio como `JunctionBox` y `StringOfLights` mediante *records*, se garantiza que su estado interno (como las coordenadas espaciales) jamás mute tras su creación.
* **Tell, Don't Ask:** El gestor ordena a los objetos realizar operaciones sobre sí mismos (`tryConnecting`) en lugar de extraer su estado interno para tomar la decisión desde fuera, garantizando un encapsulamiento estricto.

## Patrones de Diseño
* **Patrón Factory Method (Creacional):** Ocultación de la complejidad de instanciación mediante métodos estáticos semánticos (ej. `JunctionBox.parse()`, `StringOfLights.between()`), protegiendo la creación de los objetos frente a datos en bruto.

## Principios de Diseño
### SOLID
* **Principio de Responsabilidad Única (SRP):** Cada módulo tiene un único propósito. `JunctionBox` maneja geometría 3D, `UnionFindElectricalGrid` maneja la teoría de grafos, y `Playground` orquesta las reglas de negocio.
* **Principio de Abierto/Cerrado (OCP) y Sustitución de Liskov (LSP):** El sistema orquestador está abierto a operar con nuevos algoritmos matemáticos en el futuro. Cualquier clase que implemente la interfaz `ElectricalGrid` funcionará sin romper la simulación.
* **Segregación de Interfaces (ISP):** La interfaz `ElectricalGrid` es hiperespecífica, exponiendo únicamente las operaciones de negocio necesarias (`tryConnecting` y `getCircuitSizes`).
* **Inversión de Dependencias (DIP):** El orquestador de alto nivel (`Playground`) depende de la abstracción pura (`ElectricalGrid`), nunca de la implementación algorítmica concreta (`UnionFindElectricalGrid`).