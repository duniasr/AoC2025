# Día 11: Reactor


## El Reto

### Parte A
Dado un listado de dispositivos y las direcciones estáticas de sus cables (flujo unidireccional), el objetivo es calcular el número total de caminos únicos posibles desde un dispositivo inicial (`you`) hasta la salida final (`out`).

### Parte B
Los datos revelan que la ruta problemática atraviesa obligatoriamente dos cuellos de botella: un conversor (`dac`) y un transformador de Fourier (`fft`). El objetivo es calcular cuántos de los caminos totales desde el servidor central (`svr`) hasta la salida (`out`) visitan ambos nodos, teniendo en cuenta que pueden ser visitados en cualquier orden.

---

## Lógica Estructural
* **`DeviceNetwork`**: Modelo de datos atómico (`record`). Representa el grafo inmutable de conexiones.
* **`DeviceReader`**: Deserializa el archivo de texto en bruto, transformando las líneas en el diccionario (`Map<String, List<String>>`) que da vida a la red.
* **`PathFinder`**: Oculta la complejidad de la búsqueda en grafos y la instanciación de cachés de memoria detrás de interfaces limpias, resolviendo rutas mediante recursividad pura.

## Algoritmos
* **Búsqueda en Profundidad (DFS):** El método `trace()` explora el grafo navegando nodo a nodo hasta alcanzar el caso base (llegar al destino `out` o a un callejón sin salida) y colapsando los resultados hacia arriba mediante la función de reducción `.sum()`.
* **Memoización:** Uso de caché dinámica que registra cuántos caminos válidos existen desde un nodo previamente visitado, abortando la exploración repetida de ramas y devolviendo el valor.

## Técnicas de Implementación
* **Métodos Delegados:** Operaciones accesorias, como el guardado en caché (`memoize`) o la extracción de destinos (`extractOutputs`), se delegan a pequeños métodos de una sola línea para mantener limpio el flujo principal.
* **Inmutabilidad del Modelo:** Al modelar `DeviceNetwork` como *record*, se garantiza que las conexiones de la red jamás mutan su estado original tras ser instanciadas.
* **Inyección de Dependencias (DI):** Por ejemplo, tanto el motor algorítmico (PathFinder) como el modelo de datos (DeviceNetwork) operan sobre el concepto general de un diccionario, desacoplándose de infraestructuras de memoria específicas y flexibilizando el sistema.
* **Inversión del Control (IoC):** El código cede el control del flujo de ejecución a mecanismos externos (como el API de Streams de Java o la propia pila de recursión), ejecutándose solo cuando el motor funcional lo invoca.

## Patrones de Diseño
* **Patrón Factory Method (Creacional):** Toda la lógica de limpieza de texto, uso de expresiones regulares (`\\s+`) y empaquetado inmutable queda aislada en `DeviceReader.readFrom()`.

## Principios de Diseño
### SOLID
* **Inversión de Dependencias (DIP):** El motor algorítmico (`PathFinder`) no depende de implementaciones concretas, sino que exige contratos abstractos nativos (`Map`, `List`) para operar, desacoplándose de infraestructuras específicas.
* **Principio de Responsabilidad Única (SRP):** La clase que alberga los datos (`DeviceNetwork`) no tiene código de búsqueda, y la clase algorítmica (`PathFinder`) no almacena el estado persistente de la red. Cada módulo tiene un único propósito.