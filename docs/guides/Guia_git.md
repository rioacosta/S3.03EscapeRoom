# <span style="color: skyblue;">Git Flow</span>


### Ramas:

    - master
    - develop
    - feature/nombre: De feature se derivan muchas ramas. Cada nueva tarea (por ejemplo, implementar el patrón Builder, crear DAOs, corregir errores, etc.) debe tener su propia rama feature.
    Ejemplos: feature/builder, feature/daos, etc.
    <br><br>

### <span style="color: skyblue;">¿Cómo trabajamos?</span>

¡En master no se trabaja!

Una vez el proyecto esté finalizado, se hará merge desde develop a master.

Siempre partimos de develop.

Desde develop, hacemos un pull para copiar los últimos cambios del remoto al local, y luego creamos una nueva rama para trabajar.
Crear y subir una rama:

    - git branch nombre-rama — Crea una rama local
    - git push origin nombre-rama — Sube la rama a GitHub

Una vez que estemos en la rama (¡asegúrate de en qué rama estás!), trabajamos y escribimos nuestro código.

Cuando el código esté finalizado, se crea una pull request. Cuando la pull request se aprueba o corrige, se hace un merge a develop.

⚠️ Cuidado en este paso:
Antes de hacer el merge, probablemente será necesario hacer pull de develop, resolver posibles conflictos, y luego hacer el merge de tu rama a develop.
<br><br>

* * *

<span style="color: skyblue;">**Comandos varios**</span>

`git status`  
`git log`  
`git checkout`
`git commit -m "message"`  
`git tag <tagname>`  
`git tag <tagname> <commit-hash> - Tag a specific commit`  
`git tag - List tags`  
`git add - Añade archivo (o archivos, si se hace git add .) al area de staging`
