# Advent of Code 2025

Resolución de los retos del *Advent of Code 2025*.

---

## Filosofía de Diseño Global
El código de todos los retos está diseñado respetando la siguiente jerarquía estructural y conceptual:

### Paradigmas
* **Programación Orientada a Objetos (POO):** Utilizada como base para modelar el dominio, donde las entidades físicas encapsulan su propio estado y comportamiento.
* **Programación Declarativa y Funcional:** Aplicada sistemáticamente en el procesamiento de datos. El uso de la *Stream API* de Java permite definir *qué* resultado se busca, delegando el *cómo* al lenguaje, lo que reduce la complejidad ciclomática.

### Fundamentos
* **Abstracción:** Ocultamiento de la complejidad técnica para destacar lo esencial.
* **Encapsulamiento:** Protección del estado interno.
* **Modularidad:** División del sistema en piezas independientes.
* **Alta Cohesión:** Agrupación de elementos fuertemente relacionados. Las clases tienen un propósito centralizado.
* **Bajo Acoplamiento:** Los módulos interactúan mediante interfaces estables, minimizando el impacto de los cambios.

### Principios de Diseño
* **Principios SOLID:** * 
  * **SRP (Responsabilidad Única):** Separación estricta entre dominio, matemática algorítmica y orquestación.
  * **OCP (Abierto/Cerrado) e ISP (Segregación de Interfaces):** Uso de interfaces específicas abiertas a nuevas implementaciones.
  * **DIP (Inversión de Dependencias):** El flujo principal depende de abstracciones, no de detalles de bajo nivel.

### Patrones de Diseño
* **Factory Method (Creacional):** Creación de objetos complejos delegada a métodos estáticos.

### Técnicas
* **Inmutabilidad de Dominio:** Modelado de entidades a través de **`records`** de Java, garantizando que el estado no muta una vez creado, asegurando la trazabilidad y previniendo efectos secundarios.
* **Fluent API:** Diseño de métodos que devuelven `this` para permitir el encadenamiento de llamadas, logrando que el código se lea de izquierda a derecha como lenguaje natural.