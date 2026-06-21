# Día 12: Granja de Árboles de Navidad

## El Reto

### Parte A
Encajar piezas irregulares (regalos) en cuadrículas bidimensionales (el espacio bajo los árboles). Las piezas se pueden rotar y voltear (efecto espejo). Dado un catálogo de formas y una lista de pedidos para cada árbol, el objetivo es calcular en cuántas regiones es físicamente posible empaquetar todos los regalos asignados sin que se superpongan ni se salgan de los límites.

---

## Lógica Estructural
* **`PuzzleReader`**: Descompone el archivo en crudo para extraer las plantillas de los regalos y los requisitos de área, transformando cadenas de texto en objetos inmutables.
* **`Point`**: Encapsula las reglas del universo 2D: sabe cómo rotar 90º (`-y, x`) y cómo voltearse en modo espejo (`-x, y`).
* **`PresentShape`**: Representa un regalo. Genera y normaliza (anclando al 0,0) sus 8 posibles variaciones espaciales en el momento de su instanciación.
* **`TreeRegion`**: Valida heurísticamente si es físicamente posible empaquetar los regalos antes de delegar el trabajo pesado al motor.
* **`PresentFitter`**: Ignora la geometría 2D y resuelve el puzzle cruzando conjuntos numéricos puros unidimensionales (`Set<Integer>`).

## Algoritmos
* **Búsqueda en Profundidad (DFS) con Backtracking:** El método `fit()` en `PresentFitter` explora el árbol de decisiones de forma recursiva. Coloca una pieza y avanza; si choca o se queda sin opciones, retrocede (*backtrack*) y prueba otra configuración.
* **Memoización (Prevención de Ciclos):** Uso memoization que registra estados del tablero explorados que no tienen solución, cortando ramas muertas del árbol de recursión de inmediato.

## Técnicas de Implementación
* **Métodos Delegados:**  La generación de rotaciones (`rot`) y la exploración del tablero (`fit`) delegan en sí mismas mediante llamadas recursivas y operadores ternarios.
* **Inmutabilidad del Modelo:** Al modelar `Point` y `PresentShape` como *records*, se garantiza que las piezas que viajan por el árbol de recursión jamás mutan su estado original ni sufren efectos secundarios durante las pruebas de colisión.
* **Inyección de Dependencias (DI):** Los métodos no instancian internamente las estructuras que necesitan para trabajar ni dependen de variables globales.
* **Inversión del Control (IoC):** El código cede el control del flujo de ejecución a mecanismos externos (como el API de Streams de Java o la propia pila de recursión), los métodos actúan de forma reactiva, ejecutándose solo cuando el motor funcional los invoca. Cualquier estado necesario se inyecta estrictamente por parámetro.

## Patrones de Diseño
* **Patrón Factory Method (Creacional):** Toda la complejidad de parseo y generación de las 8 posturas de un regalo queda oculta detrás del método estático `PresentShape.from()`.

## Principios de Diseño
### SOLID
* **Principio de Abierto/Cerrado (OCP):** `PresentFitter` está cerrado a modificaciones. Si en el futuro los regalos pasan a ser formas 3D, el motor no cambia; simplemente recibirá conjuntos generados con una fórmula matemática diferente.
* **Inversión de Dependencias (DIP):** `PresentFitter` no depende de implementaciones de bajo nivel como `Point`. Se comunican a través de abstracciones puras, desacoplando la lógica algorítmica de la representación física.
* **Principio de Responsabilidad Única (SRP):** El dominio geométrico (`Point`, `PresentShape`) no tiene idea de cómo resolver el puzzle. El motor de resolución (`PresentFitter`) no tiene idea de qué es una coordenada X o Y. Cada módulo hace solo una cosa.