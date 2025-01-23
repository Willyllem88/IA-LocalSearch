# IA-LocalSearch

## Table of Contents

- [IA-LocalSearch](#ia-localsearch)
  - [Project Description](#project-description)
  - [Requirements](#requirements)
  - [Dependencies](#dependencies)
  - [Execution Instructions](#execution-instructions)

## Project Description

This project implements a local search algorithm applied to the Azamon problem. It uses techniques such as **Simulated Annealing (SA)** and **Hill Climbing (HC)** to find optimal or near-optimal solutions. The project was an assignment for Artificial Intelligence course at UPC-FIB.

## Requirements

To compile and run this project, you will need:

- **Java Development Kit (JDK)** version 17 or higher. The project has been developed and tested with version **17.0.12**.

## Dependencies

The project requires several libraries, which are included in the `AIMA/` and `Azamon/` folders. These include:

- **AIMA.jar**: Implements algorithms from the book *Artificial Intelligence: A Modern Approach*.
- **Azamon.jar**: Contains classes related to the Azamon problem.
- **Additional libraries in AIMA/lib/**: Includes jFreeChart, JUnit, iText, and others.

## Execution Instructions

To compile the code, open a terminal in the project's root directory and run the following command:

```bash
javac -cp ".:AIMA/AIMA.jar:Azamon/Azamon.jar:AIMA/lib/*" src/Main.java src/implementation/*.java -d .
```

Once compiled, you can execute the program with the following command:

```bash
java -cp ".:AIMA/AIMA.jar:Azamon/Azamon.jar:AIMA/lib/*:src" Main
```

This will launch the `Main` class, which serves as the program's entry point. The program will guide you through its usage directly via the console.
