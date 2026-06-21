# Advent of Code 2025

Resolución de los retos del *Advent of Code 2025* (Días 1 al 12).


---

## Filosofía de Diseño Global

El código de todos los retos está diseñado respetando la siguiente jerarquía estructural y conceptual:

### Paradigmas
* **Orientación a Objetos:** Utilizada como base estructural para modelar los dominios, donde las entidades encapsulan sus propias reglas de negocio.
* **Programación Funcional:** Aplicada al flujo de control y cálculo algorítmico, y estrechamente relacionada con el uso de clases inmutables.

### Fundamentos
* **Abstracción:** Ocultamiento de la complejidad matemática e infraestructural detrás de interfaces o métodos con semántica de negocio.
* **Modularidad:** Separación entre la capa de entrada (lectura de archivos y parseo) y el núcleo de cálculo.
* **Alta Cohesión y Bajo Acoplamiento:** Los objetos tienen una única razón para cambiar. Los orquestadores interactúan con los modelos inyectando los datos sin acoplarse a la implementación interna de los mismos.
* **Código Expresivo (Good Naming):** Uso del lenguaje del dominio para que el código se lea de forma fluida, minimizando la necesidad de comentarios explicativos.

### Principios de Diseño
* **SOLID:** Máximo respeto al Principio de Responsabilidad Única (SRP) y apertura a extensiones mediante polimorfismo sin modificar código existente (OCP).
* **Don’t Repeat Yourself (DRY):** Centralización de la lógica común y matemática de coordenadas o parseo para evitar la duplicación de conocimiento.
* **Law of Demeter (LoD):** Aplicación de la regla "Tell, Don't Ask". Los orquestadores ordenan a los objetos ejecutar acciones, en lugar de pedirles sus datos internos para calcularlo fuera.
* **Keep It Simple, Stupid (KISS) y YAGNI:** Resoluciones directas enfocadas exclusivamente en los requerimientos del día, evitando abstracciones preventivas (sobre-ingeniería) para problemas que aún no existen.

### Técnicas
* **Inmutabilidad del Modelo:** Uso extensivo de `records` en Java. El estado de las entidades se fija en su creación, garantizando un entorno seguro y libre de mutaciones accidentales.
* **Fluent API:** Diseño de métodos puros que devuelven el propio objeto o flujos de datos (`Streams`), permitiendo el encadenamiento léxico.
* **Métodos Delegados:** Ruptura de algoritmos complejos en métodos más pequeños y descriptivos que delegan la responsabilidad paso a paso, eliminando la necesidad de bloques lógicos masivos.
* **Inyección de Dependencias:** Envío de estructuras de estado a través de los parámetros de las funciones en lugar de mantener variables globales mutables en las clases.
* **Refactorización Continua:** El código es el resultado de un ciclo constante de purificación, eliminando variables temporales de bloque en favor del inlining y la inyección.

### Patrones de Diseño
* **Factory Method (Creacional):** La inicialización de objetos complejos se delega en métodos estáticos semánticos. Por un lado, PresentShape.from() encapsula la lógica matemática para generar y normalizar las 8 posturas espaciales de un regalo. Por otro, PuzzleReader.readFrom() centraliza el parseo, transformando el texto en crudo directamente en entidades inmutables del dominio listas para usarse.