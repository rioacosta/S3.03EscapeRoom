### ¿Qué es una Pull Request?

Una pull request es una petición para que los cambios que hiciste en una rama (branch) de un repositorio sean revisados y, si todo está correcto, integrados (mergeados) a otra rama, normalmente la rama principal (main o master).


### ¿Cómo funciona una Pull Request?

- Creas una rama nueva para tu trabajo 
  - Partes de la rama principal y creas una rama nueva, por ejemplo feature/nueva-funcionalidad.


- Realizas cambios en tu rama
  - Agregas código, archivos, mejoras, arreglas bugs, etc., y haces commits con tus cambios.


- Subes tu rama al repositorio remoto
  - Empujas (git push) tu rama al repositorio remoto para que esté disponible para revisión.


- Abres una Pull Request
  - En la plataforma que uses (GitHub, GitLab), abres una PR pidiendo que tus cambios en feature/nueva-funcionalidad se integren a main (o la rama de destino que sea).
  - Puedes describir qué cambios hiciste, por qué, e incluso asignar revisores.

- Revisión y discusión
  - Otros desarrolladores revisan tu código, comentan, sugieren mejoras o reportan errores.
  - Puedes hacer más commits para corregir o mejorar tu PR según el feedback recibido.


- Aprobación y merge 
  - Cuando todos están conformes, la PR se aprueba. 
  - Luego se hace el merge, es decir, tus cambios se integran a la rama destino.

- Cerrar la PR
  - La PR se cierra, quedando registrada la historia de cambios y discusión.

### ¿Por qué usar Pull Requests?

Colaboración: Permite que el equipo revise y apruebe los cambios antes de integrarlos.

Control de calidad: Evita que código con errores o malas prácticas llegue a la rama principal.

Registro histórico: Queda documentado qué cambios se hicieron, cuándo y por qué.

Discusión: Permite aclarar dudas o mejorar el código mediante la retroalimentación.