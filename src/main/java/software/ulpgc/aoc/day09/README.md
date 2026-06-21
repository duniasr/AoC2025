# Día 9: Movie Theater

## El Reto
### Parte A
Encontrar el rectángulo de mayor área posible dentro de la cuadrícula del cine. El rectángulo debe formarse seleccionando dos baldosas rojas cualesquiera de la lista proporcionada para que actúen como sus esquinas opuestas.

### Parte B
Una valla de baldosas verdes conecta todas las baldosas rojas formando un polígono cerrado irregular. El rectángulo elegido no solo debe tener esquinas rojas, sino que debe estar 100% contenido en el interior de este polígono. Ninguna pared verde puede atravesarlo, y ninguna parte de su área puede caer en el exterior del polígono.

---

## Lógica Estructural
* **`RedTile`**: Entidad matemática atómica (`record`). Representa un vértice y centraliza la lógica trigonométrica bidimensional (cálculo de área mediante valor absoluto).
* **`GreenTileLine`**: Entidad física (`record`). Modela un segmento (pared) del perímetro. Define la física de colisión informando si atraviesa o "raja" un área rectangular imaginaria.
* **`GreenTileLoop`**: El polígono completo. Evalúa el espacio cerrado. Coordina las líneas y responde a una única pregunta estructural: si un área rectangular está estrictamente contenida en su interior.
* **`MovieTheater`**: Gestor del sistema, mantiene el estado inmutable de las baldosas y aplica los motores de búsqueda iterativa.

## Algoritmos
* **Trazado de Rayos (Ray Casting):** Algoritmo implementado para resolver el clásico problema del "Punto en Polígono" (PIP). Dispara un rayo láser virtual hacia el infinito (eje X) y cuenta las intersecciones con paredes verticales. Si cruza un número impar, certifica matemáticamente que el punto está dentro; si es par, está fuera.

## Técnicas de Implementación
* **Inmutabilidad del Modelo:** Al modelar `RedTile` y `GreenTileLine` como *records*, se garantiza que las coordenadas del plano jamás puedan desplazarse por accidente durante los cruces matriciales.
* **Métodos Delegados:** Ruptura de bloques geométricos complejos. La validación espacial del polígono delega la carga en métodos secuenciales de una sola línea (`isCrossedByAnyLine()`, `isInteriorContained()`), evitando anidar sentencias de control y manteniendo un flujo limpio.

## Patrones de Diseño
* **Patrón Factory Method (Creacional):** Toda la lógica de limpieza de *strings*, ignorado de saltos de línea y parseo de coordenadas numéricas crudas se oculta de forma segura tras la factoría estática `MovieTheater.from(String input)`.

## Principios de Diseño
### SOLID
* **Principio de Responsabilidad Única (SRP):** La segregación es total. La baldosa (`RedTile`) solo sabe medir distancias; la pared (`GreenTileLine`) solo sabe detectar colisiones contra sí misma; el polígono (`GreenTileLoop`) solo valida interiores; y el cine (`MovieTheater`) solo itera opciones.