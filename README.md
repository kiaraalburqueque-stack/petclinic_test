# Spring Boot : Application PetClinic

## Features

### 1.  Unit Test  --> Tag v1.0.0

mvn test -Dspring.profiles.active=h2

## Laboratorio 11 — Grupo 1 OwnerService

Equipo de dos integrantes:

- KIARA (`kiaraalburqueque-stack`), rama `KIARA`: `testCreateOwner`, `testUpdateOwner`, `testDeleteOwner`, `testFindOwnerById_NotFound`, `testDeleteOwner_NotFound`. Implementadas y ejecutadas.
- SEBASTIAN: `testFindOwnerById`, `testFindByLastName`, `testFindByCity`, `testFindByLastName_Empty`. Pendientes; no implementadas en esta entrega.

Validación de KIARA: 5 pruebas unitarias con JUnit 5 y Mockito; suite completa de 20 pruebas sin fallos, errores ni omisiones. Ejecutar con Java 21: `./mvnw test` (Windows: `.\mvnw.cmd test`).

Conflictos resueltos: ninguno en esta fase. La revisión del PR, la integración y la evidencia de un conflicto real quedan pendientes para la fase colaborativa. No se ha hecho merge a `main`.

Informe y evidencias: [entrega de KIARA](evidence/kiara/INFORME.md).
