# IA-LocalSearch

## Tabla de Contenidos

- [IA-LocalSearch](#ia-localsearch)
  - [Tabla de Contenidos](#tabla-de-contenidos)
  - [Descripción del Proyecto](#descripción-del-proyecto)
  - [Requisitos](#requisitos)
  - [Dependencias](#dependencias)
  - [Instrucciones de Ejecución](#instrucciones-de-ejecución)

## Descripción del Proyecto

Este proyecto implementa un algoritmo de búsqueda local aplicado al problema de Azamon. Utiliza técnicas como Simulated Annealing (SA) y Hill Climbing (HC) para encontrar soluciones óptimas o aproximadas.

## Requisitos

Para compilar y ejecutar este proyecto, necesitarás:

- **Java Development Kit (JDK)** versión 17 o más. Se ha programado con la versión (17.0.12).

## Dependencias

Este proyecto requiere varias librerías que ya están incluidas en las carpetas `AIMA/` y `Azamon/`. Estas son:

- **AIMA.jar**: Implementaciones de algoritmos del libro "Artificial Intelligence: A Modern Approach".
- **Azamon.jar**: Contiene las clases relacionadas con el problema de Azamon.
- **Librerías adicionales en AIMA/lib/**: Entre ellas, jFreeChart, junit, iText, y otras.

## Instrucciones de Ejecución

Para compilar el código, abre una terminal en la carpeta raíz del proyecto y ejecuta el siguiente comando:

```bash
javac -cp ".:AIMA/AIMA.jar:Azamon/Azamon.jar:AIMA/lib/*" src/Main.java src/implementation/*.java -d .
```

Una vez que la compilación se haya completado, puedes ejecutar el programa con el siguiente comando:

```bash
java -cp ".:AIMA/AIMA.jar:Azamon/Azamon.jar:AIMA/lib/*:src" Main
```

Este comando iniciará la clase `Main`, que es el punto de entrada para el programa. El mismo programa ya guiará al usuario de como debe usarse a través de la consola.

---
