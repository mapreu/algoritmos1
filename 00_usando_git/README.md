# Introducción a Git
En este módulo aprenderemos a utilizar la herramienta Git y GitHub para utilizarla en nuestros proyectos a lo largo de la cursada.

## ¿Qué es GitHub?

GitHub es una plataforma para alojar nuestros proyectos utilizando el sistema de control de versiones Git. GitHub permite compartir y colaborar en proyectos. Es una herramienta sumamente útil para realizar proyectos en equipos.
Recomendamos leer la [documentación oficial sobre la introducción a GitHub.](https://docs.github.com/es/get-started)

## Preparar el entorno Visual Studio Code para trabajar con Git y Java

1 - Lo primero que debemos hacer es instalar el Coding Pack for Java. Incluye VS Code, el Java Development Kit (JDK) y extensiones de Java.
Siguiendo el link instalamos el Coding Pack para el sistema operativo correspondiente. Para aquellos sistemas operativos como Linux hay que instalar las herramientas de manera individual.

→ https://code.visualstudio.com/docs/java/java-tutorial

2 - Luego descargamos la extensión de Git en VS Code. Descargamos desde el link o desde el VS Code. En el VS Code vamos al panel izquierdo y seleccionamos la pestaña de source control, desde ahí podremos descargar Git para nuestro sistema operativo.

→ https://git-scm.com/downloads 

Observación, en “Choosing the default editor used by Git” seleccionar “Use Visual Studio Code as Git’s default editor”

## Comenzamos a usar Git

Como primer paso debemos crearnos una cuenta de GitHub y ver algunos términos y acciones que se pueden realizar con git.

### Términos y acciones Git

→ **Repositorio** es donde se encuentran los archivos, el código y el historial de revisiones de cada archivo de un proyecto. 

→ **Clonar** es descargar una copia completa de los datos de un repositorio de GitHub. El repositorio clonado sigue asociado al original, si se quisieran subir cambios se suben al original.

```bash
$ git clone HTTPS_del_repo
```

→ **Fork** es una acción que se realiza del lado del servidor (GitHub, en este caso) que nos permite copiar un repositorio actual en uno nuevo independiente del original. Este nuevo repositorio vivirá en nuestra cuenta GitHub y seremos dueñxs del mismo.

→ **Commit** es la acción que realizamos para guardar los cambios dentro de nuestro repositorio local. Es decir, los cambios no se verán en GitHub, sino que se verán en nuestro directorio .git.

Los archivos en Git tienen tres estados: untrucked, staged y committed. En el área working tree o working directory los archivos están untrucked, Git no tiene en cuenta los cambios realizados. El el staging area los cambios ya están preparados para ser incorporados al repositorio local. Aquí se puede volver para atrás y seguir modificando el proyecto o realizar un ‘commit’. El repositorio local son los archivos que se encuentran en tu directorio .git, una vez en está área se pueden sincronizar los cambios con GitHub.

```bash
//Información sobre cuál es la working tree y sobre el estado de los archivos
$git status
//Notificar los cambios de un archivo
$git add [filename]
//Commitear los cambios para guardarlos en el repositorio local
$git commit -m "mensaje_del_commit"
```
→ **Push** es la acción que realizamos para sincronizar el repositorio local con el repositorio remoto. Una vez realizado los cambios se verán reflejados en GitHub. Cuando tenemos más de una rama es importante verificar sobre qué rama se realizará el push.

```bash
//Verificó sobre qué rama estoy trabajando. La rama con '*' será la activa
$git branch
//Cambiar de rama
$git checkout branch
//Otra alternativa si quisiera crear y cambiar de rama
$git checkout -b new-branch
$git push -u origin new-branch
//Realizo el push desde la rama elegida
$git push
```

```-u``` es una abreviación para  ```--set-upstream / --set-upstream-to``` con este comando asociamos la nueva rama creada de forma local al repositorio remoto.

→ **Ramas o branch**. Las ramas son versiones paralelas del código dentro del mismo repositorio que no afectan a la rama principal. Se utilizan principalmente para agregar nuevas funcionalidades y resolver problemas del código. El código anterior muestra cómo ver, crear y alternar ramas. Existen muchas maneras de realizar estas acciones (git branch, git switch, git checkout), recomendamos ver cada comando en la documentación de Git.

→ **Merge** se utiliza para aplicar los cambios de una rama a otra. Es importante antes de hacer el merge encontrarnos en la rama donde deseamos que ocurra el merge, generalmente la rama master/main.

```bash
//Me ubico en la rama master
$git checkout master
//Realizo el merge con la rama new-feature
$git merge new-feature
//Una vez realizado el merge puedo eliminar la rama new-feature
$git branch -d new-feature
```

→ **Pull request** es una solicitud para combinar los cambios de una rama a otra cuando queremos contribuir en proyectos no propios.

Para empezar debemos realizar un fork y antes de empezar a trabajar es importante crear una nueva rama desde la rama master. En esta nueva rama haremos las contribuciones al proyecto.
Una vez que realizamos un push podremos realizar el pull request desde GitHub. Luego el dueñx del proyecto decidirá si realiza un merge o cierra la solicitud.

**Recomendamos ver la [documentación oficial](https://git-scm.com/doc) de Git para conocer las diferentes opciones que existen para cada comando.**


### Gitignore

Un archivo **.gitignore** especifica qué archivos o carpetas Git deberá ignorar. Estos archivos son aquellos que intencionalmente queremos fuera de seguimiento. Para crear un archivo .gitignore local, hay que crear un archivo de texto y asignarle el nombre ".gitignore". Luego, incluir los archivos o carpetas que deseamos que Git ignore.

[Colección de plantillas gitignore](https://github.com/github/gitignore) 

## Ejercicio    

### Proyecto Java en Visual Studio Code

Crear un nuevo *proyecto java* desde VS Code y luego subir ese proyecto vacío a GitHub. Para realizar este ejercicio se debe crear el proyecto java y el repositorio por separado, luego asociarlos utilizando ```$git remote add origin HTTPS_del_repo```. Luego modificar el archivo README.md y sincronizar los cambios con el repositorio remoto. 

Por último realizar un fork  de la actividad de un compañerx. Crear una nueva rama para funciones matemáticas. Desarrollar algunas funciones, testearlas y realizar un pull request.
