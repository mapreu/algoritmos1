# Introducción a Java
En este módulo, aprenderemos los conceptos básicos de Java y la programación orientada a objetos. Utilizaremos Visual Studio Code como nuestro entorno de desarrollo para escribir, compilar y ejecutar programas Java.

## ¿Qué es Java?
Java es un lenguaje de programación de alto nivel desarrollado por Sun Microsystems (ahora parte de Oracle). Es conocido por su portabilidad y su capacidad para crear aplicaciones robustas y escalables. Java se basa en el paradigma de la programación orientada a objetos (POO).

## Conceptos Básicos de la Programación Orientada a Objetos (POO)
Recordemos que la POO se basa en los siguientes conceptos:

**Clase**: Una clase es una plantilla que define los atributos (propiedades) y métodos (comportamientos) que un objeto puede tener. Por ejemplo, una clase "Auto" puede tener atributos como "marca" y "modelo", y métodos como "arrancar" y "detener".

**Objeto**: Un objeto es una instancia de una clase. Por ejemplo, un objeto "Auto" sería una instancia específica de cierto auto con valores concretos para sus atributos.

**Abstracción**: Mecanismo a través del cual podemos concentrarnos en aspectos relevantes e ignorar los detalles. Nos permite definir qué es lo que deseamos modelar sin entrar en detalle de cómo lo realizaremos y así favorecer a la reusabilidad de estos modelos. En la POO podemos construir abstracciones tanto de datos como de comportamiento.

**Encapsulamiento**: Es el concepto de asociar la estructura de datos con las operaciones que soporta y así también proveer **ocultamiento de información** (ocultar los detalles internos de una clase y exponer solo lo que es necesario). Esto se logra mediante el uso de modificadores de acceso como "public" y "private".

**Herencia**: La herencia permite que una clase herede atributos y métodos de otra clase. Esto promueve la reutilización de código y facilita la extensibilidad. Por ejemplo, una clase "Camión" puede heredar de la clase "Transporte".

**Polimorfismo**: Si bien existen distintos tipos de polimorfismo, en la POO el polimorfismo de _inclusión_ o _subtipo_ permite que objetos de diferentes clases respondan a un mismo método de manera diferente. Esto se logra mediante el uso de la herencia.

## Compilación y Ejecución de un Programa Java
Para escribir, compilar y ejecutar programas Java en Visual Studio Code, podemos seguir estos pasos:

- **Instalar el Kit de Desarrollo de Java (JDK)**: Asegurate de tener el JDK instalado en tu computadora. Podés descargarlo desde [aquí](https://www.oracle.com/java/technologies/javase-downloads.html) e instalarlo siguiendo las instrucciones.

- **Instalar Visual Studio Code**: Si aún no tenés Visual Studio Code, podés descargarlo e instalarlo desde [Visual Studio Code](https://code.visualstudio.com/).

- **Instalar la Extensión de Java**: Abrí Visual Studio Code, en a la pestaña de extensiones (icono de cuadrado en la barra lateral izquierda), buscá "Java Extension Pack" y hacé clic en "Instalar".

- **Crear un Nuevo Proyecto**: En Visual Studio Code creá una carpeta para tu proyecto. Luego, crea un archivo con extensión **.java**. Podés hacerlo haciendo clic derecho en la carpeta y seleccionando "Nuevo archivo". Una vez hayas generado el archivo, verás que aparece en la vista de _Explorador_ la sección _Java Projects_ que facilita la organización del proyecto Java.

- **Escribir tu Programa Java**: Abrí el archivo .java y escribí tu código Java. Por ejemplo:

```java
public class MiPrograma {
    public static void main(String[] args) {
        System.out.println("¡Hola, Mundo!");
    }
}
```

- **Compilar y Ejecutar**: En una terminal en Visual Studio Code (menú "Terminal" > "Nueva Terminal"), navegá a la carpeta de tu proyecto y compilá tu programa Java usando el comando:

```bash
$ javac MiPrograma.java
```
Esto generará un archivo .class que contiene el _bytecode_ Java, es un archivo binario que puede ejecutarse por la _JVM_ (Java Virtual Machine) que viene en el entorno de ejecución (_JRE_, Java Runtime Environment).

- **Ejecutar el Programa**: Para ejecutar el programa, usá el comando:

```bash
$ java MiPrograma
```
Deberías ver la salida "¡Hola, Mundo!" en la terminal.

## Estructura de Archivos y Paquetes
En Java, los programas se organizan en clases y se agrupan en **paquetes**. La estructura de archivos y carpetas debe coincidir con la estructura de paquetes. Por ejemplo, si tienes una clase llamada MiClase en el paquete com.miorganizacion, la estructura de carpetas debería ser:

```bash
mi-proyecto/
    src/
        com/
            miorganizacion/
                MiClase.java
```

Esta estructura de archivos y paquetes ayuda a organizar y gestionar proyectos Java más grandes y complejos. Para agregar una clase a un paquete se define en su primera línea cuál es el nombre del paquete al cual pertenece con el operador **package**.
 
```java
package miPaquete;

class MiClase {
    ...
}
```

### Ejercicio: Crear un paquete

Incorporá el programa HolaMundo en un paquete llamado _introduccion_ y probá el proceso completo de compilarlo y ejecutarlo.

### Import 
El operador **import** es importante cuando se trabaja con clases de bibliotecas estándar de Java o definidas en otros paquetes. Se utiliza para traer miembros de otros paquetes a tu scope actual (la clase donde estás importando). Esto permite que tu código acceda y utilice las clases y tipos definidos en esos paquetes. Sin embargo, no necesitás usar import para las clases que pertenecen al mismo paquete o están en el paquete _java.lang_, ya que se importan automáticamente.

La sintaxis básica del operador import es la siguiente:

`import paquete.NombreDeLaClase;`

- paquete: Es el nombre del paquete que contiene la clase que vas a importar.
- NombreDeLaClase: Es el nombre de la clase que vas importar.

Veamos este ejemplo. Supongamos que tenés una clase llamada _MiClase_ en el paquete _miPaquete_, y queremos importarla en otra clase para usarla.

```java
import miPaquete.MiClase;

public class OtraClase {
    public static void main(String[] args) {
        MiClase objeto = new MiClase();
        // Y utilizamos el objeto y métodos de MiClase...
    }
}
```

#### Importación de Clases de Java Estándar
Para usar clases de la biblioteca estándar de Java, como _Scanner_ para entrada de usuarix o _ArrayList_ para listas dinámicas, también debemos importarlas si no están en el paquete java.lang. Por ejemplo:

```java
import java.util.ArrayList;
import java.util.Scanner;

public class MiPrograma {
    public static void main(String[] args) {
        ArrayList<String> lista = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        // Utiliza ArrayList y Scanner aquí
    }
}
```

#### Importación Estática
Además de importar clases, también es posible realizar una _importación estática_ de miembros estáticos de una clase utilizando la palabra clave **static**. Esto te permite acceder a los miembros estáticos directamente sin utilizar el nombre de la clase. Veremos más adelante de qué se tratan.

```java
import static paquete.ClaseEstatica.miMetodoEstatico;

public class MiPrograma {
    public static void main(String[] args) {
        miMetodoEstatico(); // Llamada al método estático sin usar el nombre de la clase
    }
}
```

## Clases y objetos

Las clases son la base de la programación orientada a objetos en Java. Una clase define un objeto y puede contener atributos (propiedades) y métodos (comportamiento). Por ejemplo:

```java
public class Persona {
    // Atributos
    String nombre;
    int edad;
    
    // Método constructor
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    
    // Método
    public void saludar() {
        System.out.println("Hola, soy " + nombre + " y tengo " + edad + " años.");
    }
}
```
Para consumir la clase _Persona_ se puede instanciar un objeto a partir de ella de la siguiente forma:

```java
Persona juana = new Persona("Juana", 25);
juana.saludar();
```
En este caso, el operador _new_ permite generar una instancia u objeto (**instanciación**) de tipo _Persona_ en memoria dinámica (_Heap_). La **inicialización** de la instancia se realiza ejecutando el constructor de Persona que recibe dos argumentos tal como se indica en su firma (nombre y edad). Finalmente, la referencia a esta instancia alojada en memoria se asigna a la variable _juana_ que es de tipo _Persona_.

### Atributos
Los atributos en Java son variables que se definen dentro de una clase y representan las propiedades o características de un objeto. Estos atributos definen el estado de un objeto y pueden tener diferentes tipos de datos, como enteros, cadenas, números decimales, etc. Los atributos de una clase son características que cada objeto de esa clase posee.

Por ejemplo, en la clase _Persona_, los atributos podrían ser nombre, edad, altura, etc.

```java
public class Persona {
    String nombre; // Atributo de tipo String
    int edad;      // Atributo de tipo int
    double altura; // Atributo de tipo double
}
```

Cada atributo se define con su tipo de dato asociado y puede tener también otros modificadores que veremos más adelante que determinan su acceso, si es estático o mutable.

### Métodos
Los métodos en Java son funciones que se definen dentro de una clase y representan el comportamiento de un objeto. Estos métodos definen las acciones que un objeto puede realizar. Los métodos pueden aceptar parámetros y devolver resultados.

Por ejemplo, en una clase _Auto_, podríamos tener métodos como arrancar(), detener(), acelerar(int velocidad), etc.

```java
public class Auto {
    // Atributos
    
    // Métodos
    public void arrancar() {
        // Código para arrancar el auto
    }
    
    public void detener() {
        // Código para detener el auto
    }
    
    public void acelerar(int velocidad) {
        // Código para acelerar el auto a la velocidad proporcionada
    }
}
```

Un método se compone de una **firma** y una **implementación** (si no es _abstracto_). La implementación es el bloque de código asociado (se abarca entre {}), mientras que la firma puede tener los siguientes componentes en orden:

1. [Modificadores de acceso](#modificadores-de-acceso).
2. Otros **modificadores** opcionales que iremos viendo (_static_, _final_, _abstract_, etc.).
3. El **tipo** de retorno del método (si es procedimiento: _void_).
4. El **nombre** del método (por convención se utiliza _camelCase_).
5. La lista de **parámetros** entre paréntesis y separados por coma (cada uno precedido por su tipo).
6. Una lista de **excepciones**, si corresponde.

#### El método main
En Java, cuando una clase tiene definido un método especial **main**, podremos ejecutarla y así se invocará a este método como punto de entrada de la ejecución. La firma del método es la siguiente:

`public static void main(String[] args)`

El parámetro args permite aceptar argumentos de entrada al programa cuando se ejecuta la clase. Por ejemplo:

```java
public class Prueba {
    public static void main(String[] args) {
        for (int i=0; i < args.length; i++) {
            System.out.println("Argumento " + i + ": " + args[i]);
        }
    }
}
```
```bash
$ java Prueba Estos son 4 argumentos
```
La salida de la ejecución de la clase _Prueba_ será:
```bash
Argumento 0: Estos
Argumento 1: son
Argumento 2: 4
Argumento 3: argumentos
```

En general sólo implementaremos este método en sólo una clase en nuestro programa, la cual será la clase a ejecutar.

### Constructores
Los constructores en Java son **métodos especiales** que se utilizan para **inicializar** objetos de una clase. Estos métodos se llaman automáticamente cuando se crea una nueva instancia de la clase (cuando utilizamos el operador _new_, el cual realiza la **instanciación** un objeto).

Los constructores son esenciales en la programación orientada a objetos porque permiten establecer un estado inicial coherente para los objetos, y así nos aseguran que se creen con un estado válido. A su vez podemos realizar la inicialización de los atributos dentro de constructores, lo que evita que se utilicen valores no inicializados. Dado que los constructores pueden aceptar parámetros, podemos personalizar la inicialización de objetos según las necesidades del programa.

#### Definición de Constructores
Para definir un constructor en Java, debemos seguir las siguientes reglas:

- El nombre del constructor debe coincidir exactamente con el nombre de la clase.
- Los constructores no tienen un tipo de retorno, ni siquiera void.

A continuación, se muestra un ejemplo simple de un constructor:

```java
public class MiClase {
    // Constructor
    public MiClase() {
        // Código de inicialización
    }
}
```

#### Constructores con Parámetros
Pueden aceptar parámetros para inicializar objetos de manera personalizada y así crear objetos con diferentes estados iniciales según las necesidades.

```java
public class Persona {
    private String nombre;
    private int edad;
    
    // Constructor con parámetros
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
}
```

#### Uso de Constructores
Para crear una instancia de una clase y llamar a su constructor, utilizamos la palabra clave _new_, seguida del nombre del constructor. Por ejemplo:

```java
Persona persona1 = new Persona("Juana", 30);
```
Así creamos un nuevo objeto _Persona_ llamado _persona1_ utilizando el constructor con dos parámetros (String e int).

#### Constructor por Defecto
Si no definimos ningún constructor en una clase, el compilador proporcionará un constructor por defecto sin argumentos automáticamente. Este constructor por defecto inicializa los campos con valores predeterminados (por ejemplo, 0 para enteros, null para objetos, etc.) y simplemente invoca al constructor de la superclase (_super();_).

#### Sobrecarga de Constructores
En Java podemos tener múltiples constructores en una clase, siempre y cuando tengan diferentes parámetros (firma). Esto se conoce como **sobrecarga de constructores**. Podés proporcionar diferentes formas de inicializar objetos según sea necesario.

**Atención**: Si una clase tiene definido al menos un constructor, el compilador no provee un constructor por defecto. Por lo tanto, si definimos un constructor con parámetros, eso implica que no podremos inicializar un objeto de esa clase con un constructor sin parámetros.

```java
Persona persona1 = new Persona();   // Error de compilación, porque Persona no tiene constructor sin parámetros definido.
```

### Ejercicio: Constructores de Libro
Crear una clase llamada Libro con los atributos _titulo_, _autor_ y _añoPublicacion_. Definir un constructor que acepte solo el título y el autor, y establezca el añoPublicacion en el año actual (ver LocalDate.now().getYear()). Luego crear otro constructor que acepte todos los atributos (titulo, autor y añoPublicacion). Utilizar la sobrecarga de constructores para crear dos objetos Libro, uno con el año de publicación especificado y otro sin especificarlo. Mostrar los detalles de ambos libros.

## Variables Primitivas y Referencias
### Variables Primitivas
En Java, las variables primitivas almacenan valores simples, como números enteros, números decimales o caracteres. Estas variables ocupan una cantidad fija de memoria y almacenan directamente el valor.

Algunos ejemplos de variables primitivas incluyen:

- int: Almacena números enteros.
- double: Almacena números decimales de punto flotante.
- char: Almacena caracteres Unicode.
- boolean: Almacena valores booleanos (verdadero o falso).

```java
int edad = 25;       // Variable primitiva de tipo int
double precio = 19.99; // Variable primitiva de tipo double
char letra = 'A';    // Variable primitiva de tipo char
boolean esMayor = true; // Variable primitiva de tipo boolean
```

### Variables de Referencia
Las variables de referencia en Java almacenan direcciones de memoria donde se encuentra el objeto. En lugar de almacenar el objeto directamente, estas variables apuntan al objeto en la memoria. Estas variables se utilizan para acceder a objetos y llamar a sus métodos.

```java
Persona juana = new Persona("Juana", 25);
```
En este caso, juana es una variable de referencia que apunta a un objeto de tipo Persona.

### Clases Relacionadas con Variables Primitivas
Java proporciona **clases relacionadas con variables primitivas** para realizar operaciones y proporcionar funcionalidad adicional. Estas clases están en el paquete java.lang y son parte del lenguaje de Java. Algunas de las clases relacionadas con variables primitivas son:

- **Integer**: Para operaciones con enteros.
- **Double**: Para operaciones con números de punto flotante.
- **Character**: Para operaciones con caracteres.
- **Boolean**: Para operaciones con valores booleanos.

#### Autoboxing y Unboxing
El **autoboxing** es una característica de Java que permite que las variables primitivas se conviertan automáticamente en objetos de las clases relacionadas cuando sea necesario. Por ejemplo:

```java
// Autoboxing: int (el literal 25) se convierte en Integer automáticamente
Integer edad = 25;
```

El **unboxing** es la operación inversa, que convierte automáticamente un objeto de una clase relacionada en su valor primitivo correspondiente:

```java
// Unboxing: Integer se convierte en int automáticamente
int valor = edad;
```

El autoboxing y unboxing simplifican la manipulación de variables primitivas al permitir que se utilicen como objetos de clases relacionadas cuando sea necesario y viceversa. Esto facilita el uso de tipos primitivos en colecciones, como listas o mapas, que generalmente requieren objetos en lugar de tipos primitivos.

### Ejercicio: Crear una Clase y un Objeto

Crear una clase llamada _Estudiante_ que tenga los siguientes atributos:

- Nombre (String)
- Edad (int)
- Carrera (String)
- Materias (arreglo de String)

Luego, crear un objeto de tipo _Estudiante_ con valores concretos para estos atributos e imprimir la información del estudiante en la consola.

TIP: Investigar el método **toString** y cómo puede utilizarse para mostrar en formato String la información del estudiante.

### Ejercicio: Clases y Métodos
Crear una clase llamada _Rectangulo_ que represente un rectángulo. Esta clase debe tener los siguientes atributos:

- Largo (double)
- Ancho (double)
Agregar métodos para calcular el área y el perímetro del rectángulo. Luego, crear objetos de tipo Rectangulo, calcular y mostrar sus áreas y perímetros.

### Ejercicio: Ingreso y salida
Investigando la herramienta _Scanner_ que trae Java, escribir un programa que tenga dos paquetes. 
- Un paquete se debe llamar _entrada_ y contiene una clase _EntradaConsola_. Esta clase tiene el método _main_ y recibe como argumento un número entero que determina cuántas entradas de usuarix se aceptarán.
- El otro paquete se llamará _salida_ y contiene una clase _SalidaConsola_ que simplemente imprime cadenas en consola con un método _mostrar_.

El programa recibe entonces un número X cuando se ejecuta. Luego, solicitará X entradas de texto que serán ingresadas por teclado y a medida que se ingresan se muestran en la misma consola.

## Expresiones, sentencias y bloques

### Expresiones
Las expresiones (_expressions_) son fragmentos de código que producen un valor (pueden evaluarse a algún valor). Pueden ser tan simples como una constante o más complejas como una operación matemática. Algunos ejemplos de expresiones en Java incluyen:

```java
int x = 5; // Una expresión de asignación
int y = x + 3; // Una expresión matemática
String mensaje = "Hola, mundo!"; // Una expresión de asignación de cadena
```
En los ejemplos, las expresiones asignan valores a variables o realizan operaciones matemáticas y de cadena.

### Sentencias
Las sentencias (_statements_) son instrucciones que realizan acciones en un programa. Cada sentencia termina con un punto y coma (;) y conforma una unidad completa de ejecución. Algunos ejemplos de sentencias incluyen:

```java
int numero = 10; // Una sentencia de declaración y asignación
System.out.println("Este es un mensaje"); // Una sentencia de salida
if (numero > 5) {
    System.out.println("El número es mayor que 5"); // Una sentencia condicional
}
```
Las sentencias son la forma en que controlamos el flujo de un programa, tomando decisiones y realizando acciones. Podemos encontrarnos sentencias de:
- Asignación
- Declaración
- Operación ++ o --
- Expresiones
- Control de flujo (condicionales, bucles)

### Bloques
Los bloques (_blocks_) son grupos de cero o más sentencias encerradas entre llaves {}. Los bloques se utilizan para agrupar múltiples sentencias y crear áreas de alcance (_scope_) para las variables. Pueden utilizarse en cualquier lugar que pueda usarse una sentencia. Aquí hay un ejemplo de un bloque:

```java
public class EjemploBloque {
    public static void main(String[] args) {
        int x = 5;
        {
            int y = 10;
            System.out.println("x: " + x); // x: 5
            System.out.println("y: " + y); // y: 10
        }
        // La variable 'y' no está disponible fuera del bloque
        System.out.println("x: " + x); // x: 5
        // System.out.println("y: " + y); // Esto generará un error
    }
}
```
En este ejemplo, las variables x e y están en diferentes _scopes_ debido a los bloques. La variable y solo es accesible dentro del bloque en el que se declara.


## Estructuras de Control
### Estructura if
La estructura **if** se utiliza para tomar decisiones en un programa. Permite ejecutar un bloque de código si una condición es verdadera.

```java
int edad = 22;

if (edad >= 18) {
    System.out.println("Sos mayor de edad.");
} else {
    System.out.println("Sos menor de edad.");
}
```

### Estructura if-else if-else
Esta estructura se utiliza cuando hay múltiples condiciones que se deben verificar en orden. El bloque de código correspondiente a la primera condición verdadera será el que se ejecute, de lo contrario si no se cumple ninguna condición se ejecuta el último bloque.

```java
int nota = 85;

if (nota >= 90) {
    System.out.println("Aprobaste con Excelente.");
} else if (nota >= 80) {
    System.out.println("Aprobaste con Muy bueno.");
} else if (nota >= 70) {
    System.out.println("Aprobaste con Bueno.");
} else if (nota >= 60) {
    System.out.println("Aprobaste con Suficiente.");
} else {
    System.out.println("Reprobaste.");
}
```

### Estructura switch
El **switch** se utiliza para realizar múltiples verificaciones de igualdad sobre el valor de una expresión. Se utiliza principalmente para casos en los que se necesita comparar una variable con múltiples valores posibles.

```java
int diaDeLaSemana = 3;
String nombreDia;

switch (diaDeLaSemana) {
    case 1:
        nombreDia = "Lunes";
        break;
    case 2:
        nombreDia = "Martes";
        break;
    // ...
    default:
        nombreDia = "Día no válido";
}
```

### Estructura for
El bucle **for** se utiliza para repetir un bloque de código un número conocido de veces.

```java
for (int i = 1; i <= 5; i++) {
    System.out.println("Iteración " + i);
}
```
Un caso especial de esta estructura es el **for each** el cual facilita la iteración sobre secuencias lineales, visitando cada uno de los elementos que contiene.

```java
for (String argumento : arregloArgumentos) {
    System.out.println("Valor argumento: " + argumento);
}
```

### Estructura while
El bucle **while** se utiliza para repetir un bloque de código mientras una condición sea verdadera.

```java
int contador = 0;

while (contador < 5) {
    System.out.println("Contador: " + contador);
    contador++;
}
```

### Estructura do-while
El bucle **do-while** se utiliza para repetir un bloque de código al menos una vez y luego repetirlo mientras una condición sea verdadera.

```java
int numero = 1;

do {
    System.out.println("Número: " + numero);
    numero++;
} while (numero <= 5);
```

## Operadores

### Operadores Aritméticos
- **+**: Suma
- **-**: Resta
- **\***: Multiplicación
- **/**: División
- **%**: Módulo (resto de la división)

```java
int a = 10;
int b = 3;
int suma = a + b;
int resta = a - b;
int multiplicacion = a * b;
int division = a / b;
int modulo = a % b;
```

### Operadores de Comparación
- **==**: Igual a
- **!=**: No igual a
- **<**: Menor que
- **<=**: Menor o igual que
- **\>**: Mayor que
- **\>**=: Mayor o igual que

```java
int x = 5;
int y = 10;

boolean igual = x == y;
boolean noIgual = x != y;
boolean menorQue = x < y;
boolean mayorQue = x > y;
```

### Operadores Lógicos
- **&&**: Y lógico (AND)
- **||**: O lógico (OR)
- **!**: Negación lógica (NOT)

```java
boolean esMayorDeEdad = true;
boolean tieneLicencia = false;

boolean puedeConducir = esMayorDeEdad && tieneLicencia;
boolean noPuedeConducir = !puedeConducir;
```

## Arreglos

Los arreglos son estructuras de datos que permiten almacenar colecciones de elementos del mismo tipo de forma indexada. Cada elemento en un arreglo se identifica mediante un índice numérico, y se puede acceder a esos elementos utilizando ese índice. En Java, los arreglos son objetos y se almacenan en la heap.

### Declaración y Creación
Para declarar un arreglo en Java, se especifica su _tipo_ seguido de corchetes []:

`Tipo[] nombreDelArreglo`

Por ejemplo, para declarar un arreglo de enteros:

```java
// Arreglo de variables primitivas
int[] numeros;

// Arreglo de variables de referencia
Integer[] numeros;
```

Luego, para crear un arreglo y asignarle memoria, se puede utilizar el operador new:

```java
numeros = new int[5]; // Crea un arreglo de enteros con capacidad para 5 elementos
```

También se puede declarar y crear un arreglo en una sola línea:

```java
int[] numeros = new int[5];
```

### Inicialización
Es posible inicializar un arreglo con valores en el momento de la creación:

```java
int[] numeros = {1, 2, 3, 4, 5}; // Inicializa el arreglo con valores
```

### Acceso a Elementos
Los elementos de un arreglo se acceden mediante un índice numérico, comenzando desde 0 para el primer elemento. Por ejemplo, para acceder al tercer elemento del arreglo numeros:

```java
int tercerElemento = numeros[2]; // El índice es 2 para acceder al tercer elemento
```

### Tamaño de un Arreglo
Se puede obtener el tamaño (longitud) de un arreglo utilizando la propiedad length:

```java
int tamaño = numeros.length; // Tamaño del arreglo
```

### Iteración a través de un Arreglo
Se utilizan bucles, como el **for**, para recorrer todos los elementos de un arreglo:

```java
// Imprime cada elemento del arreglo iterando sobre el índice
for (int i = 0; i < numeros.length; i++) {
    System.out.println(numeros[i]); 
}

// Imprime cada elemento del arreglo iterando para cada elemento
for (int numero : numeros) {
    System.out.println(numero);
}
```

### Arreglos Multidimensionales
Java admite arreglos multidimensionales, que son arreglos de arreglos. Por ejemplo, un arreglo de dos dimensiones se declara y crea de la siguiente manera:

```java
int[][] matriz = new int[3][4]; // Una matriz 3x4
```

## Tipos enumerables

Los tipos enumerables (_enum types_) son una forma poderosa de definir un conjunto fijo de valores constantes que representan opciones o categorías específicas. Los tipos enumerables son útiles cuando necesites asegurarte de que una variable solo pueda tomar valores de un conjunto predefinido.

### Definición de un Enumerable
Para crear un tipo enumerable en Java utilizamos la palabra clave **enum**.

```java
enum DiaDeLaSemana {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
}
```
Así efinimos un tipo enumerable llamado _DiaDeLaSemana_ con siete valores posibles que representan los días de la semana.

### Usando un Enumerable
Una vez que hayas definido un tipo enumerable, podés declarar variables utilizando este tipo y asignarles uno de los valores enumerados. Por ejemplo:

```java
DiaDeLaSemana dia = DiaDeLaSemana.MARTES;
```
Aquí declaramos una variable _dia_ de tipo _DiaDeLaSemana_ y le hemos asignado el valor _MARTES_.

### Comparación de Valores
Podemos comparar valores enumerados utilizando el operador de igualdad (==). Por ejemplo:

```java
if (dia == DiaDeLaSemana.LUNES) {
    System.out.println("Es el comienzo de la semana.");
} else if (dia == DiaDeLaSemana.VIERNES) {
    System.out.println("¡Es viernes! Fin de semana a la vista.");
} else {
    System.out.println("Es un día normal.");
}
```

### Iteración a través de Valores
Podemos iterar a través de todos los valores enumerados utilizando un bucle _for-each_. Por ejemplo:

```java
for (DiaDeLaSemana dia : DiaDeLaSemana.values()) {
    System.out.println(dia);
}
```
Esto imprimirá todos los valores enumerados en orden.

### Métodos y Campos en Enumerables
Podemos agregar métodos y campos a los tipos enumerables para hacerlos más útiles. Por ejemplo, podríamos agregar un método para obtener el día siguiente:

```java
enum DiaDeLaSemana {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO;

    public DiaDeLaSemana siguiente() {
        if (this.ordinal() == DiaDeLaSemana.values().length - 1) {
            return DiaDeLaSemana.LUNES;
        } else {
            return DiaDeLaSemana.values()[this.ordinal() + 1];
        }
    }
}

DiaDeLaSemana dia = DiaDeLaSemana.MIERCOLES;
DiaDeLaSemana siguienteDia = dia.siguiente(); // Devuelve JUEVES
```

### Ventajas de los Tipos Enumerables
- Los tipos enumerables proporcionan un conjunto fijo de opciones, lo que hace que el código sea **más legible y menos propenso a errores** (código más limpio y seguro).
- Ayudan a **prevenir valores no válidos**, ya que solo se pueden asignar valores enumerados válidos a las variables de tipo enumerable.
- Facilitan la **iteración a través de opciones** y la realización de **operaciones** basadas en el valor enumerado.

## Modificadores de Acceso
Los modificadores de acceso controlan la visibilidad y accesibilidad de clases, atributos y métodos dentro de un programa.

### public
Accesible desde cualquier clase.

```java
public class MiClase {
    public int miAtributo; // Atributo público
    public void miMetodo() { // Método público
        // Código del método
    }
}
```

### private
Solo accesible dentro de la misma clase.

```java
public class MiClase {
    private int miAtributo; // Atributo privado
    private void miMetodo() { // Método privado
        // Código del método
    }
}
```

### Sin Modificador o Paquete (Default)
Accesible solo dentro del mismo paquete.

```java
package miPaquete;

public class MiClase {
    int miAtributo; // Atributo sin modificador (default)
    void miMetodo() { // Método sin modificador (default)
        // Código del método
    }
}
```

```java
package miOtroPaquete;

import miPaquete.MiClase;

public class MiOtraClase {
    void miMetodo() {
        ...
        MiClase prueba = new MiClase();
        prueba.miMetodo();  // Error de compilación porque no tiene acceso
    }
}
```
Si se intenta compilar, arroja el error _error: miMetodo() is not public in MiClase; cannot be accessed from outside package_.

### protected
Accesible dentro del mismo paquete y por subclases (incluso si están en un paquete diferente).

```java
package paquete1;

public class ClaseBase {
    protected int miAtributo; // Atributo protegido
    protected void miMetodo() { // Método protegido
        // Código del método
    }
}
```
```java
package paquete2;

public class SubClase extends paquete1.ClaseBase {
    void otroMetodo() {
        miAtributo = 42; // Acceso permitido al atributo protegido de la clase base
        miMetodo(); // Acceso permitido al método protegido de la clase base
    }
}
```

### Consideraciones Importantes
Los modificadores de acceso permiten controlar el encapsulamiento y la seguridad en una aplicación Java.
- Usar private para ocultar detalles internos de una clase y exponer solo lo necesario.
- Usar public para proporcionar una interfaz pública bien definida.
- Usar protected cuando se desea que las subclases tengan acceso a ciertos miembros.
- Usar el modificador sin especificar para permitir el acceso dentro del mismo paquete.

## Anotaciones
Las anotaciones son metadatos que se utilizan para proporcionar información adicional sobre elementos del código fuente, como clases, métodos, variables, etc. Son usualmente utilizadas para documentación, configuración y procesamiento durante la compilación o en tiempo de ejecución. Son etiquetas que se colocan directamente antes de elementos del código fuente. Proporcionan información adicional sobre esos elementos y pueden ser interpretadas por varias herramientas y librerías. Las anotaciones comienzan con el símbolo **@** seguido del nombre de la anotación.

### Predefinidas en Java
Java proporciona un conjunto de anotaciones predefinidas que se utilizan comúnmente en aplicaciones y librerías. Algunas de las anotaciones más utilizadas son:

- @Override: Indica que un método anula un método en su clase base.
```java
@Override
public void miMetodo() {
    // Código del método
}
```
- @Deprecated: Marca un elemento (método, clase, etc) como obsoleto, lo que significa que se desaconseja su uso.

```java
@Deprecated
public void metodoAntiguo() {
    // Código del método
}
```
- @SuppressWarnings: Suprime advertencias del compilador para elementos específicos, como advertencias no deseadas.

```java
@SuppressWarnings("unchecked")
public List<String> obtenerLista() {
    // Código del método
}
```

### Anotaciones Personalizadas
Es posible crear tus propias anotaciones personalizadas en Java. Para hacerlo se debe definir una interfaz que actúe como la anotación y marcarla con la anotación @interface.

```java
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // Especifica cuándo estará disponible en tiempo de ejecución
@Target(ElementType.METHOD) // Especifica dónde se puede aplicar esta anotación (en métodos)
public @interface MiAnotacion {
    String autor() default "Desconocido";
    String fecha();
}
```
En este ejemplo, creamos la anotación @MiAnotacion que puede aplicarse a métodos y tiene dos atributos: autor y fecha.

Una vez que tenemos definida una anotación personalizada, podemos usarla en el código de la siguiente manera:

```java
public class MiClase {

    @MiAnotacion(autor = "Laura", fecha = "2023-09-01")
    public void miMetodo() {
        // Código del método
    }
}
```

### Procesamiento de Anotaciones
Las anotaciones pueden ser procesadas en **tiempo de compilación** o en **tiempo de ejecución** utilizando librerías o marcos específicos. Esto permite realizar tareas personalizadas basadas en las anotaciones, como generación de código, validaciones, configuración automática, entre otras.


## Ejercicios

### Calculadora Simple
Crear una calculadora simple que acepte dos números y un operador (+, -, *, /) como entrada del consola. Luego, realizar la operación y mostrar el resultado.

Ejemplo de entrada/salida:
```bash
Ingrese el primer número: 10
Ingrese el operador (+, -, *, /): *
Ingrese el segundo número: 5
Resultado: 50
```

### Conversión de Temperatura
Escribir un programa que permita convertir una temperatura de grados Celsius a grados Fahrenheit o viceversa. Debe solicitar la temperatura y la unidad de entrada, y luego mostrar la temperatura convertida.

Ejemplo de entrada/salida:
```bash
Ingrese la temperatura: 25
Ingrese la unidad de temperatura (C/F): C
Temperatura en Fahrenheit: 77.0°F
```

### Suma de Números Pares
Escribir un programa que calcule la suma de todos los números pares entre 1 y un número ingresado por consola. Recordar validar que el número ingresado sea positivo.

Ejemplo de entrada/salida:
```bash
Ingrese un número positivo: 10
La suma de los números pares del 1 al 10 es: 30
```

### Tabla de Multiplicar
Escribir un programa que solicite un número y luego muestre la tabla de multiplicar del 1 al 10 para ese número.

Ejemplo de entrada/salida:

```bash
Ingrese un número: 7
Tabla de multiplicar del 7:
7 x 1 = 7
7 x 2 = 14
7 x 3 = 21
...
7 x 10 = 70
```

### Contador de Palabras Especial
Crear un programa que cuente la cantidad de palabras en una cadena de texto ingresada por consola. Considerar que, por definición, las palabras están separadas por espacios, pero permitir que se pueda especificar un caracter especial distinto que actúe como separador (por ejemplo, la coma).

Ejemplo de entrada/salida:

```bash
Ingrese una frase: Hola, esto es un ejemplo.
Ingrese un separador: 
Cantidad de palabras: 5

Ingrese una frase: Hola, esto es un ejemplo.
Ingrese un separador: ,
Cantidad de palabras: 2
```