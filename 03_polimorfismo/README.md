# Polimorfismo

## Herencia

El polimorfismo de **inclusión** o **subtipo** es un componente esencial de la herencia y nos permite escribir código más **flexible** y **reutilizable**. Se apoya en el concepto de **sobreescritura**.

Este tipo de polimorfismo nos permite tratar a objetos de clases derivadas como si fueran objetos de la clase base. En otras palabras, podemos utilizar una variable de una clase base para acceder a objetos de diferentes clases derivadas, y el comportamiento real invocado será determinado en **tiempo de ejecución** (_dynamic binding_). Esto es fundamental para lograr una mayor flexibilidad y extensibilidad en el diseño.

### Ejemplo: Figuras Geométricas
Imaginemos que estamos creando un programa para trabajar con figuras geométricas como círculos, rectángulos y triángulos. Para simplificar crearemos una clase base llamada _Figura_ y clases derivadas _Circulo_, _Rectangulo_ y _Triangulo_. Veamos cómo funciona el polimorfismo de inclusión en este contexto.

```java
public class Figura {
    public void dibujar() {
        System.out.println("Dibujando una figura genérica.");
    }
}

public class Circulo extends Figura {
    @Override
    public void dibujar() {
        System.out.println("Dibujando un círculo.");
    }
}

public class Rectangulo extends Figura {
    @Override
    public void dibujar() {
        System.out.println("Dibujando un rectángulo.");
    }
}

public class Triangulo extends Figura {
    @Override
    public void dibujar() {
        System.out.println("Dibujando un triángulo.");
    }
}
```

Uso del Polimorfismo de Inclusión:
```java
public class Main {
    public static void main(String[] args) {
        Figura figura1 = new Circulo();
        Figura figura2 = new Rectangulo();
        Figura figura3 = new Triangulo();
        
        figura1.dibujar();  // Resultado: Dibujando un círculo.
        figura2.dibujar();  // Resultado: Dibujando un rectángulo.
        figura3.dibujar();  // Resultado: Dibujando un triángulo.
    }
}
```

En este ejemplo, creamos instancias de las clases derivadas (_Circulo_, _Rectangulo_ y _Triangulo_) y las asignamos a variables de referencia de la clase base _Figura_. Aunque las variables son de tipo _Figura_, el método _dibujar()_ invocado se basa en el tipo real del objeto en tiempo de ejecución. Esto es el polimorfismo de inclusión.

### Métodos heredados
Cuando utilizamos una variable de una superclase (o interfaz) para referenciar una instancia de un objeto que puede ser del mismo tipo o cualquier subtipo.

### ¿Qué ventajas nos puede dar?
- **Flexibilidad**: Podemos escribir código genérico que funcione con clases base y, automáticamente, con todas sus clases derivadas (gracias a la herencia).
- **Extensibilidad**: Podemos agregar nuevas clases derivadas sin modificar el código existente, o bien especializando el comportamiento deseado.
- **Mantenibilidad**: Facilita la gestión de objetos similares pero de diferentes tipos de forma.

> El polimorfismo de inclusión es una herramienta poderosa que nos permite tratar objetos de clases derivadas como si fueran objetos de superclases o interfaces. Simplifica el diseño y facilita su mantenimiento.

### Ejercicio: Biblioteca de medios

Estamos desarrollando una aplicación para gestionar una biblioteca de medios, como libros, películas y música. Creamos una clase base llamada _Medio_ y tres clases derivadas: _Libro_, _Pelicula_ y _Musica_. Cada una de estas clases tiene un método _reproducir()_ que muestra información específica del medio.

Debemos crear un programa que utilice el polimorfismo de inclusión para reproducir diferentes medios y mostrar la información correspondiente.

a) Crear una clase _Medio_ con un método _reproducir()_ que muestre un mensaje genérico como "Reproduciendo medio genérico".

b) Crear tres subclases que extienden a _Medio_: _Libro_, _Pelicula_ y _Musica_. Cada una de estas clases debe tener su propio método _reproducir()_ que muestre información específica del medio (por ejemplo, título y autor para un libro, título y director para una película, y título y artista para música).
c) En la clase Main, crear un arreglo de objetos de tipo _Medio_ y agregar instancias de _Libro_, _Pelicula_ y _Musica_.
d) Iterar a través del arreglo llamando al método _reproducir()_ para cada elemento y mostrar la información correspondiente.

Ejemplo de salida esperada:
```java
Reproduciendo Libro: "El Gran Gatsby" de F. Scott Fitzgerald
Reproduciendo Película: "La La Land" dirigida por Damien Chazelle
Reproduciendo Música: "Bohemian Rhapsody" por Queen
```

## Sobrecarga 

La sobrecarga (**_overloading_**) es un concepto en programación que permite a una clase tener múltiples métodos con el mismo nombre pero diferentes parámetros. Esto significa que dos o más métodos en una clase pueden tener el mismo nombre, pero Java podrá diferenciarlos según los parámetros que reciban en **tiempo de compilación**.

Se le suele denominar falso polimorfismo o _ad-hoc_ ya que, a diferencia del polimorfismo de subtipo, no se resuelve en tiempo de ejecución para dar diversas formas o comportamiento a objetos en una jerarquía de clases, sino que provee un comportamiento polimórfico a las **operaciones**. Algunos lenguajes también soportan la **sobrecarga de operadores** (por ejemplo, el operador **+** para aritmética y concatenación).

### Criterios de Sobrecarga
Para que dos métodos se consideren sobrecargados en Java, deben cumplirse al menos uno de los siguientes criterios:
- Los métodos deben tener una **cantidad** diferente de parámetros.
- Los métodos deben tener **tipos** de parámetros diferentes.
- Los métodos pueden tener un **orden** diferente de tipos de parámetros.

En la sobrecarga no contemplamos el **tipo de dato del retorno**, porque el compilador no puede inferir qué versión de método usar.

#### Ejemplo con diferente cantidad de parámetros
```java
public class Calculadora {
    public int sumar(int a, int b) {
        return a + b;
    }
    
    public int sumar(int a, int b, int c) {
        return a + b + c;
    }
    
    public double sumar(double a, double b) {
        return a + b;
    }
    
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();
        System.out.println("Suma de enteros: " + calc.sumar(5, 7));
        System.out.println("Suma de enteros: " + calc.sumar(3, 6, 9));
        System.out.println("Suma de dobles: " + calc.sumar(3.5, 2.8));
    }
}
```

En este ejemplo la clase _Calculadora_ tiene tres métodos _sumar_, cada uno con una cantidad diferente de parámetros. El compilador determinará qué método ejecutar según la cantidad de argumentos proporcionados.

#### Ejemplo con Tipos de Parámetros Diferentes
```java
public class Balanza {
    public double convertir(double libras) {
        return libras * 0.45359237;
    }
    
    public double convertir(double libras, String unidad) {
        if (unidad.equals("kg")) {
            return libras * 0.45359237;
        } else if (unidad.equals("g")) {
            return libras * 453.59237;
        } else {
            throw new IllegalArgumentException("Unidad no válida: " + unidad);
        }
    }
    
    public static void main(String[] args) {
        Balanza balanza = new Balanza();
        System.out.println("5 libras en kg: " + balanza.convertir(5));
        System.out.println("10 libras en g: " + balanza.convertir(10, "g"));
    }
}
```
En este ejemplo la clase _Balanza_ tiene dos métodos _convertir_, uno que acepta solo libras y otro que acepta libras y una unidad de medida. Los tipos de parámetros son diferentes en cada método, lo que permite la sobrecarga. El compilador seleccionará el método adecuado según los tipos de argumentos proporcionados.


### Ejercicio: Calculadora

En este ejercicio crearemos una clase _CalculadoraAvanzada_ que realizará diversas operaciones matemáticas mediante la sobrecarga de métodos. Esta clase permitirá realizar operaciones como sumar, restar, multiplicar y dividir números enteros y de punto flotante de diversas maneras. El objetivo es utilizar la sobrecarga de métodos para proporcionar una calculadora versátil.

a. Crear una clase llamada _CalculadoraAvanzada_.

b. Agregar los siguientes métodos a la clase _CalculadoraAvanzada_:

- sumar(int a, int b) para sumar dos números enteros.
- sumar(double a, double b) para sumar dos números de punto flotante.
- restar(int a, int b) para restar dos números enteros.
- restar(double a, double b) para restar dos números de punto flotante.
- multiplicar(int a, int b) para multiplicar dos números enteros.
- multiplicar(double a, double b) para multiplicar dos números de punto flotante.
- dividir(int a, int b) para dividir dos números enteros.
- dividir(double a, double b) para dividir dos números de punto flotante.

c. Implementar un método _main_ en una clase llamada _PruebaCalculadoraAvanzada_ que crea una instancia de _CalculadoraAvanzada_ y realizar diversas operaciones matemáticas utilizando los métodos sobrecargados.

## Otros polimorfismos

Si bien varían definiciones según autores, podemos resumir dos tipos de polimorfismos que se implementan en Java.

### Coerción

Sucede en ciertas **conversiones de tipo implícitas**, muy común en el pasaje de parámetros de métodos. En Java ocurre por ejemplo con la conversión de primitivos de un valor menor a mayor (widening), cuando se convierte con su clase de referencia (Boxing o Unboxing), o cuando se produce el _upcasting_ implícito en variables de referencia. De esta forma una operación puede funcionar con distintos tipos de argumentos ya que el lenguaje los convierte al tipo necesario.

```java
public static float division(float x, float y) {
    return x / y;
}

int p = 10;
int q = 3;
division(p, q);        // 3.3333333
```

En el ejemplo se coerce el tipo de dato de _p_ y _q_ en la operación de división cuando se pasan como argumentos. Se convierten de _int_ a _float_ de forma implícita.

### Paramétrico

Este caso de polimorfismo aparece cuando definimos tipos genéricos o paramétricos para lograr abstracciones más generalizadas. En Java se utiliza **Generics**. Por ejemplo: List<T>, Comparable<T>, etc. 

Es de gran utilidad para reutilizar código definiendo un mismo tipo de dato para diferentes situaciones. En los TADs lo podemos ver en tipos paramétricos como Lista(a), donde una misma implementación de Lista sirve para consumir listas de cualquier tipo de dato en sus nodos. Veremos más adelante cómo hacerlo con _Generics_ de Java.