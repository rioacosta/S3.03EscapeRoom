# <span style="color: #eccafa;">**Git Flow** </span>


**WORK IN PROGRESS** 

**AÚN NO HE ACABADO DE RELLENAR Y EDITARLO**

***

Organización ramas:

- MAIN
- DEVELOP
- FEATURE/Nombre: De Feature hay muchas ramas. Cada cosa que se cree (el patrón Buider, las DAOs, arreglas fallos, etc) será una rama feature individial. Osea Feature/builder, feature/dao, etc

* * *

Convenciones de nombre de los commit:

verbo + nombre/despripción

Ejemplos:

update README.md
create builder pattern
fix observer pattern issue

* * *

**Antes de hacer nada, mejor hacer un pull para estar actualizado con el repositorio remote!**

Crear y pushear rama
`git branch nombrerama` (crea una rama el local)
`git push origin nombrerama` (sube la rama a github)

Borrar rama local y remote

`git branch -D branch-test` (esto funciona pero solo si no está actualmente en checkout)
`git push origin --delete branch-test`
