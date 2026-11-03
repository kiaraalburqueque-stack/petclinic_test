# Entrega de KIARA del Laboratorio 11

Fecha: 3 de noviembre de 2026. Proyecto: https://github.com/kiaraalburqueque-stack/petclinic_test. Se implementaron exclusivamente las cinco pruebas asignadas a KIARA. La suite final ejecutó 20 pruebas sin fallos, errores ni omisiones.

## Verificación inicial

- Rama: `KIARA`. Árbol limpio antes de crear las evidencias y modificar código.
- Origin de lectura y escritura: `https://github.com/kiaraalburqueque-stack/petclinic_test.git`.
- Autor Git: `kiaraalburqueque-stack <kiara.alburqueque@tecsup.edu.pe>`. No se cambió la identidad.
- Base de trabajo: `04e591c`.
- Java usado: Microsoft 21.0.12.1; Maven Wrapper 3.9.11; Spring Boot 3.5.14.
- Dependencias efectivas de `spring-boot-starter-test`: JUnit Jupiter 5.12.2, Mockito Core y Mockito JUnit Jupiter 5.17.0. No fue necesario modificar `pom.xml`.

## Documento oficial y requisitos

Se leyó todo el texto del documento `C:/Users/Kiara/Downloads/GLAB-S11-JGOMEZM-2026-2 (1).docx`, incluyendo tablas, encabezado, pie y relaciones de enlaces mediante extracción OOXML. No existe la copia indicada en `docs/`. La revisión fue de contenido, sin validar visualmente las imágenes incrustadas.

El documento solicita pruebas de creación, actualización, búsqueda y eliminación de owners coordinadas mediante Git, evidencias en el informe, ruta del repositorio y conclusiones. Se revisaron también los enlaces oficiales:

- https://github.com/jgomezz/petclinic_test
- https://github.com/jgomezz/petclinic_test/blob/main/TASKS.md

Se priorizó Grupo 1 de `TASKS.md`, con la distribución explícita de dos integrantes solicitada. Se mantuvo `KIARA` por instrucción del equipo, aunque la guía general propone ramas `feature/...`.

La rúbrica de TASKS asigna 6 puntos a Git, 4 a completar pruebas, 3 a conflictos, 4 a calidad y 3 a ejecución. Esta entrega aporta pruebas, commits propios y ejecución correcta. No acredita todavía protección de main, revisión, merge por integrante ni conflicto resuelto. No se garantiza una calificación máxima mientras esos requisitos del equipo estén pendientes.

## Cambio mínimo de funcionalidad

En la base solo existía la funcionalidad Pet. No había Owner, OwnerRepository, OwnerService ni OwnerNotFoundException. OwnerServiceTest contenía únicamente un ejemplo comentado, conservado en el archivo.

Se agregaron Owner con los seis campos reales de la tabla `owners` del schema, repositorio JpaRepository con ID Integer, excepción comprobada e interfaz/implementación del servicio. Se siguieron los nombres create/update/delete/findById, la inyección por constructor y la semántica de PetServiceImpl. Create y update delegan en save; delete consulta primero y borra solo si existe; findById lanza la excepción si el Optional está vacío. No se añadieron consultas por apellido o ciudad ni pruebas de Sebastián. No se cambió el esquema ni las pruebas oficiales.

Se añadió `.maven-cache/` al gitignore para excluir la distribución local del Wrapper. Los primeros intentos de Maven fallaron por permisos y descarga en el entorno restringido; la ejecución autorizada posterior completó la línea base. La consulta inicial de dependencias también requirió permisos para la caché de Maven; el reintento final pasó. Esos fallos de entorno no se ocultaron y se conservaron sus logs.

## Pruebas y resultados reales

OwnerServiceTest usa MockitoExtension, Mock e InjectMocks sin levantar Spring ni conectarse a una base de datos. Cada método prepara datos y stubs propios; Mockito renueva los mocks para cada prueba. Estas pruebas validan el contrato del servicio, no la persistencia real de Owner en H2.

| Prueba | Comprobaciones |
| --- | --- |
| testCreateOwner | Resultado e ID no nulos, ID asignado, todos los datos y save una vez |
| testUpdateOwner | ID conservado, nombre, apellido, dirección, ciudad y teléfono actualizados, save una vez |
| testDeleteOwner | Consulta del ID y eliminación del dueño exacto, en ese orden |
| testFindOwnerById_NotFound | OwnerNotFoundException, mensaje con ID y única consulta |
| testDeleteOwner_NotFound | OwnerNotFoundException y ausencia de cualquier eliminación |

| Ejecución | Pruebas | Fallos | Errores | Omitidas | Evidencia |
| --- | ---: | ---: | ---: | ---: | --- |
| Línea base | 15 | 0 | 0 | 0 | baseline-authorized.log |
| Soporte Owner, suite existente | 15 | 0 | 0 | 0 | owner-support.log |
| Creación | 1 | 0 | 0 | 0 | 01-create.log |
| Más actualización | 2 | 0 | 0 | 0 | 02-update.log |
| Más eliminación | 3 | 0 | 0 | 0 | 03-delete.log |
| Más búsqueda inexistente | 4 | 0 | 0 | 0 | 04-find-not-found.log |
| Cinco pruebas KIARA | 5 | 0 | 0 | 0 | 05-delete-not-found.log |
| Suite final completa | 20 | 0 | 0 | 0 | final-suite.log |

Cada ejecución invocó el ciclo test de Maven, incluyendo compilación. `TEST-com.tecsup.petclinic.services.OwnerServiceTest.xml` conserva los cinco resultados finales. `dependencies-verified.log` y `maven-version.log` acreditan las herramientas. No hubo errores funcionales que exigieran cambiar pruebas oficiales.

Comandos reproducibles en PowerShell desde la raíz:

```powershell
$env:JAVA_HOME='C:/Users/Kiara/.jdks/ms-21.0.12.1'
$env:MAVEN_USER_HOME="$PWD/.maven-cache"
.\mvnw.cmd -B -ntp '-Dtest=OwnerServiceTest' test
.\mvnw.cmd -B -ntp test
```

## Historial y archivos

| Commit | Propósito |
| --- | --- |
| aaead22 | Soporte mínimo de Owner y exclusión de caché |
| e6221a0 | Prueba de creación |
| 1758525 | Prueba de actualización |
| b4d797e | Prueba de eliminación |
| 892d31f | Excepción al buscar inexistente |
| c1fc9b6 | Excepción al eliminar inexistente |

Archivos de código: `entities/Owner.java`, `repositories/OwnerRepository.java`, `exceptions/OwnerNotFoundException.java`, `services/OwnerService.java`, `services/OwnerServiceImpl.java` bajo `src/main/java/com/tecsup/petclinic/`; y `src/test/java/com/tecsup/petclinic/services/OwnerServiceTest.java`. También se actualizaron `.gitignore`, README y esta carpeta de evidencias. `git-graph.txt` conserva el historial gráfico textual y `commits.txt` acredita autores. Para la captura exigida por la guía, ejecutar `git log --all --graph --oneline` y capturar la terminal.

## Cuenta GitHub y Pull Request

La identidad de autor Git no demuestra la cuenta autenticada para el push. Git Credential Manager contiene dos cuentas; se solicitó explícitamente la credencial de `kiaraalburqueque-stack` y se verificó mediante GET autenticado a `https://api.github.com/user`. GitHub devolvió `kiaraalburqueque-stack`; véase `github-account.txt`. No se imprimió ni guardó el token.

Para inspección manual: `git config user.name`, `git config user.email`, `git remote -v`, `git credential-manager github list`. Si GitHub CLI está instalado, `gh auth status` y `gh api user --jq .login` verifican la sesión de gh, que puede diferir de la credencial utilizada por Git. No publicar tokens en capturas.

Abrir https://github.com/kiaraalburqueque-stack/petclinic_test/compare/main...KIARA?expand=1, seleccionar base `main` y compare `KIARA`, revisar los cambios y crear el PR con título `test(owner): cinco pruebas de KIARA`. Indicar 5 pruebas de Owner y 20 totales aprobadas, enlazar este informe y solicitar revisión a Sebastián. No hacer merge en esta fase. La publicación y su verificación se registran por separado en `push.log` y la comprobación final local/remota.

## Conclusiones

1. Los cinco escenarios de KIARA validan resultados y colaboraciones del servicio con Mockito, sin depender de registros compartidos.
2. El soporte mínimo de Owner cubre la ausencia de la funcionalidad en la base y mantiene las 15 pruebas existentes aprobadas.
3. La fase individual está completada; la máxima evaluación del equipo requiere terminar las cuatro pruebas de Sebastián y documentar revisión, integración y conflicto real en la fase correspondiente.
