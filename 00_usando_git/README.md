# Primera Actividad con GIT y Java
Comenzamos a trabajar con Java y GitHub

## Preparar el entorno

1. Instalar el Coding Pack for Java 

Incluye VS Code, el Java Development Kit (JDK) y extensiones de Java.

https://code.visualstudio.com/docs/java/java-tutorial

2. Descargar la extensión de Git en VS Code

Observación, en “Choosing the default editor used by Git” seleccionar “Use Visual Studio Code as Git’s default editor”

## Comenzando con GitHub y Java
### Fork
El **fork** es una acción que se realiza en del lado del servidor (Github, en este caso) que nos permite copiar un repositorio actual en uno nuevo independiente del original. Este nuevo repositorio vivirá en nuestra cuenta Github y seremos dueñxs del mismo.

3. Realizar un fork de este repositorio **algoritmos1**

4. Ejercicio: Realizar la siguiente actividad

Implementar la clase Lamparita, que sirva para representar el estado de encendido de una lamparita (encendido o apagado). Defina, asimismo, dos métodos que permitan encender y apagar la luz de la lamparita y otro que indique en qué estado se encuentra. La lamparita inicialmente está apagada.

Recordar una vez realizado el ejercicio commitear y pushear los cambios.

### Clone

```bash
$ git clone HTTPS_del_repo
```

### Commit 

```bash
$ git stage --all
```

```bash
$ git commit -am "mensaje_del_commit"
```

### Push

```bash
$ git push
```

### Branch

5. Agregar una nueva función a la lamparita.

Para esto crear una nueva rama y realizar sobre esta los nuevos cambios.

```bash
$ git checkout -b nueva_rama
```
(creo y me cambio a la nueva_rama)

```bash
$ git push -u origin nueva_rama
```
(subo nueva_rama al repositorio remoto)

6. Cuando la nueva función este completada unir la rama feature al main

### Merge

a. Verificar que no haya nada para commitear
```bash
$ git status
```

b. Cambiar a la rama main

```bash
$ git checkout main
```

c. Realizar el marge

```bash
$ git marge nombre_rama
```