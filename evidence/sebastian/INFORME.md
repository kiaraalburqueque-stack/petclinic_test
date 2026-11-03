# Pruebas de Sebastián del Laboratorio 11

Fecha: 3 de noviembre de 2026. Grupo 1, OwnerService. Base: `40631c9`, que incorpora el PR de KIARA. Rama verificada: `SEBASTIAN`, inicialmente limpia. Identidad conservada: `SebastianEspiritu <sebastian.espiritu@tecsup.edu.pe>`. Origin: `https://github.com/kiaraalburqueque-stack/petclinic_test.git`.

## Alcance y cambios

Se añadieron exclusivamente cuatro pruebas en OwnerServiceTest usando JUnit 5 y MockitoExtension. Cada prueba prepara sus propias entidades y stubs; no depende de base de datos, red ni del orden de ejecución. Las cinco pruebas de KIARA se compararon con la base y permanecen intactas; véase `kiara-preserved.txt`. Las pruebas oficiales no se modificaron ni deshabilitaron.

| Prueba | Verificación |
| --- | --- |
| testFindOwnerById | Resultado no nulo, ID, nombre, apellido, dirección, ciudad, teléfono y única consulta al repositorio |
| testFindByLastName | Dos coincidencias, contenido e IDs esperados, apellido coincidente e interacción exacta |
| testFindByCity | Dos resultados con distintos apellidos, contenido e IDs esperados, ciudad coincidente e interacción exacta |
| testFindByLastName_Empty | Resultado no nulo, lista vacía, tamaño cero y única consulta |

El método findById ya existía. Solo fue necesario agregar findByLastName y findByCity a OwnerRepository y OwnerService y delegarlos desde OwnerServiceImpl. Spring Data obtiene las consultas a partir de los campos existentes de Owner. No se modificaron la entidad, el esquema, pom.xml ni otros casos del laboratorio. Los mocks verifican el contrato y la delegación del servicio; no prueban por sí solos el filtrado SQL. La suite completa también levanta el contexto Spring con las nuevas consultas.

## Ejecuciones reales

Se utilizó Maven Wrapper con Java Microsoft 21.0.12.1 y las dependencias existentes de JUnit 5 y Mockito.

| Ejecución | Pruebas | Fallos | Errores | Omitidas | Evidencia |
| --- | ---: | ---: | ---: | ---: | --- |
| Suite inicial | 20 | 0 | 0 | 0 | baseline.log |
| Búsqueda por ID | 1 | 0 | 0 | 0 | 01-find-id.log |
| Búsqueda por apellido | 1 | 0 | 0 | 0 | 02-last-name.log |
| Búsqueda por ciudad | 1 | 0 | 0 | 0 | 03-city.log |
| Cuatro pruebas nuevas | 4 | 0 | 0 | 0 | four-new-tests.log |
| OwnerServiceTest completo | 9 | 0 | 0 | 0 | owner-service.log |
| Suite final completa | 24 | 0 | 0 | 0 | full-suite.log |

El XML `TEST-com.tecsup.petclinic.services.OwnerServiceTest.xml` conserva los nueve resultados de la ejecución final. Cada comando test incluye las fases de compilación de Maven.

```powershell
$env:JAVA_HOME='C:/Users/Kiara/.jdks/ms-21.0.12.1'
$env:MAVEN_USER_HOME="$PWD/.maven-cache"
.\mvnw.cmd -B -ntp '-Dtest=OwnerServiceTest#testFindOwnerById+testFindByLastName+testFindByCity+testFindByLastName_Empty' test
.\mvnw.cmd -B -ntp '-Dtest=OwnerServiceTest' test
.\mvnw.cmd -B -ntp test
```

## Commits y publicación

Los primeros tres commits son `0d46ae0` (ID), `6a2b693` (apellido) y `9c925ad` (ciudad). El cuarto commit contiene la prueba sin coincidencias y estas evidencias; consultar `git log -4 --format='%h | %an <%ae> | %s'` para ver los cuatro hashes con su autor. `commits.txt` y `git-graph.txt` conservan el historial anterior al cuarto commit.

Se solicitó explícitamente la credencial de SebastianEspiritu a Git Credential Manager, sin mostrar ni guardar el secreto. GET autenticado a `https://api.github.com/user` confirmó `SebastianEspiritu`. Sin embargo, GET a `https://api.github.com/repos/kiaraalburqueque-stack/petclinic_test` no confirmó `permissions.push = true`. Por la condición expresa del usuario se detuvo el push y la creación del PR, y se solicitó confirmar acceso de colaborador e invitación aceptada. No se intentó publicar con la cuenta de Kiara.

La implementación y validación local están completas. La publicación y el PR quedan pendientes de resolver la verificación de escritura. Una vez verificada, publicar SEBASTIAN y crear el PR con base main en el repositorio de Kiara. No hacer merge.
