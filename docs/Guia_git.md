# <span style="color: #eccafa;">**Git Flow** </span>

### Ramas:

- master
- develop
- feature/nombre: De Feature hay muchas ramas. Cada cosa que se cree (el patrón Builder, las DAOs, arreglas fallos, etc) será una rama feature individual. Por ejemplo, feature/builder, feature/daos, etc

* * *

### Convenciones del nombre de los commit:

En inglés!

verbo + nombre/despripción

Ejemplos:

update README.md
create builder pattern
fix observer pattern issue

* * *

**Antes de hacer nada, mejor hacer un pull para estar actualizado con el repositorio remote!**

- Crear y pushear rama
  - `git branch nombrerama` (crea una rama el local)
  - `git push origin nombrerama` (sube la rama a github)


- Borrar rama local y remote
  - `git branch -D branch-test` (esto funciona pero solo si no está actualmente en checkout)
  - `git push origin --delete branch-test`
