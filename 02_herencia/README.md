# Herencia

La herencia es uno de los conceptos clave en la programación orientada a objetos que permite la creación de clases nuevas basadas en clases existentes. Java es un lenguaje que admite la herencia y este módulo exploraremos este concepto con ejemplos prácticos.

## ¿Cuándo la utilizamos?
Cuando analizamos cómo se relacionan las clases de nuestro sistema existen situaciones donde una clase representa cierta entidad pero también puede representar a otra más abstracta. En ese caso tendremos una relación **"es un"** o **"es de tipo"** entre estas clases y será candidata a modelarse con una herencia.

Dado que la herencia se representa como una jerarquía de abstracciones, es normal que identifiquemos que una abstracción es también de tipo de otra abstracción. Por ejemplo, si estamos pensando hacer una clase para representar una _Cabaña_ y tenemos otra clase que representa _Departamento_, posiblemente sea conveniente definir una clase _Vivienda_ que modele una abstracción mayor. En ese caso, _Cabaña_ "es una" _Vivienda_ y ocurre lo mismo con _Departamento_. Entonces tenemos una relación de herencia entre _Vivienda_-_Cabaña_ y _Vivienda_-_Departamento_.

## Herencia simple
La herencia permite que una clase (llamada clase derivada o **subclase**) herede los atributos y métodos de otra clase (llamada clase base o **superclase**). Esto facilita la reutilización del código y la creación de jerarquías de clases para favorecer la _extensibilidad_. 

>Una subclase hereda los miembros de una superclase y puede agregar nuevos miembros o modificar los existentes según sea necesario a través de la **sobreescritura** de métodos de instancia u **ocultamiento** (_hiding_) de métodos de clase.

Veamos cómo se define la herencia.

```java
public class Subclase extends Superclase {
    // Campos y métodos de la subclase
}
```

Supongamos ahora que tenemos una superclase _Animal_ y queremos crear una subclase _Perro_ que herede de _Animal_.

```java
class Animal {
    void comer() {
        System.out.println("El animal come alimentos.");
    }
}

class Perro extends Animal {
    void ladrar() {
        System.out.println("El perro ladra.");
    }
}

public class HerenciaSimple {
    public static void main(String[] args) {
        Perro miPerro = new Perro();
        miPerro.comer();    // Heredado de Animal
        miPerro.ladrar();   // Propio de Perro
    }
}
```

De esta forma, un objeto de tipo _Perro_ tiene dos métodos, uno propio (_ladrar_) y otro heredado (_comer_). En ambos casos podemos accederlos internamente desde la clase con el operador **this**, porque son miembros de _Perro_.

### La superclase madre

En Java, todas las clases heredan siempre de la clase **Object**. Por lo tanto, toda clase definida en Java tiene como superclase máxima a _Object_ de forma implícita y hereda así todos sus métodos. Por ejemplo, si no especificamos una superclase particular, nuestra clase es subclase directa de _Object_. Ahora bien, si definimos una superclase con la palabra _extends_, nuestra clase herede de ella de forma directa, pero eventualmente heredará también de _Object_ cuando se suba en la jerarquía de clases.

En el ejemplo previo, la clase _Animal_ no hereda explícitamente de otra (no definimos una superclase con _extends_), por lo que hereda implícitamente de _Object_. Luego, la clase _Perro_ hereda directamente solo de _Animal_ y por transitividad también de _Object_.

## Herencia Múltiple
Java no admite la herencia múltiple de clases, lo que significa que una clase solo puede heredar de una única superclase. Sin embargo, puede implementar múltiples _interfaces_, lo que logra un comportamiento similar. Hablaremos de interfaces más adelante.

```java
interface Volador {
    void volar();
}

interface Nadador {
    void nadar();
}

class Ave extends Animal implements Volador {
    public void volar() {
        System.out.println("El ave vuela en el cielo.");
    }
}

class Pez extends Animal implements Nadador {
    public void nadar() {
        System.out.println("El pez nada en el agua.");
    }
}

public class HerenciaMultiple {
    public static void main(String[] args) {
        Ave miAve = new Ave();
        Pez miPez = new Pez();
        
        miAve.comer();  // Heredado de Animal
        miPez.comer();  // Heredado de Animal
        miAve.volar();  // Implementado de Volador
        miPez.nadar();  // Implementado de Nadador
    }
}
```

## Consideraciones de la herencia

- Una subclase hereda todos los miembros **public** y **protected** de su superclase, sin importar en qué paquete se encuentre. 
- Si ambas se encuentran en el mismo paquete, la subclase también hereda los miembros **sin modificador** (_package-private_) de la superclase.
- Los miembros heredados se pueden acceder como si fueran propios.
- Si definimos un atributo en la subclase con mismo nombre que uno de la superclase, estaremos **ocultando** al atributo de la superclase (no se recomienda).
- Podemos declarar nuevos miembros en la subclase que no se encuentren en la superclase.
- Podemos definir un método en la subclase con misma firma que la superclase para reescribir su implementación (**override**).
- Si definimos un método estático en la subclase con mismo nombre que uno estático de la superclase, estaremos **ocultando** al método de la superclase.
- Podemos escribir un constructor en la subclase que llame al constructor de la superclase implícitamente, o explícitamente con **super()**.

> Si bien no podemos heredar atributos privados, si la superclase define métodos visibles desde la subclase que acceden/modifican estos atributos, podremos así acceder/modificar los atributos privados mediante la invocación de esos métodos de la superclase. Es una práctica recomendada para evitar **acoplar** las clases con la herencia.

### Palabra Clave super
La palabra clave **super** se utiliza en una subclase para acceder a los miembros de la superclase de forma explícita. Puede ser útil cuando la subclase tiene un miembro con el mismo nombre que el de la superclase y desea acceder al miembro de la superclase. Por ejemplo:


```java
class Vehiculo {
    void encenderMotor() {
        System.out.println("El motor del vehículo se enciende.");
    }
}

class Auto extends Vehiculo {
    void encenderMotor() {
        super.encenderMotor();  // Llama al método de la superclase
        System.out.println("El auto está listo para conducir.");
    }
}

public class LlamadaSuper {
    public static void main(String[] args) {
        Auto miAuto = new Auto();
        miAuto.encenderMotor();
    }
}
```

### Ejercicio: Figuras Geométricas
Crear una superclase llamada _Figura_ con un campo _nombre_ y un método _calcularArea()_. Luego, crear dos subclases llamadas _Rectangulo_ y _Circulo_ que hereden de _Figura_ y proporcionen implementaciones específicas para calcular el área de un rectángulo y un círculo. Crear objetos de ambas subclases, calcular y mostrar sus áreas.

## Sobreescritura

La sobreescritura (**overriding**) de métodos permite a una subclase proporcionar su propia variación de un método heredado de su superclase. En Java, se utiliza la vinculación dinámica de métodos (**dynamic binding**) que resuelve en **tiempo de ejecución** qué versión de implementación de un método debe ejecutarse.

### ¿Cuándo ocurre?
La sobreescritura de métodos ocurre cuando una subclase proporciona una implementación específica para un **método de instancia** que ya está definido en su superclase y es visible por la subclase. Esto **no aplica para métodos de clase (estáticos)**, ya que son estáticos y se _ocultan_, no se _sobreescriben_. Para que una sobreescritura sea válida deben cumplirse las siguientes condiciones:

- El nombre, cantidad de parámetros, orden de parámetros y tipos de datos de parámetros del método en la subclase deben conicidir con los de la superclase.
- El tipo de retorno del método de la subclase debe coincidir o ser subtipo (_covariante_) del tipo de retorno del método de la superclase.
- El modificador de acceso del método en la subclase debe ser igual o menos restrictivo que el modificador de acceso en la superclase.

### ¿Cómo se realiza?
Veamos un ejemplo donde deseamos modificar el comportamiento heredado por uno específico.

```java
class Animal {
    void hacerSonido() {
        System.out.println("Animal hace un sonido genérico.");
    }
}

class Perro extends Animal {
    @Override
    void hacerSonido() {
        System.out.println("El perro ladra.");
    }
}

class Gato extends Animal {
    @Override
    void hacerSonido() {
        System.out.println("El gato maulla.");
    }
}

public class PruebaSobreescritura {
    public static void main(String[] args) {
        Animal miAnimal;
        
        miAnimal = new Animal();
        miAnimal.hacerSonido(); // Salida: Animal hace un sonido genérico.
        
        miAnimal = new Perro();
        miAnimal.hacerSonido(); // Salida: El perro ladra.
        
        miAnimal = new Gato();
        miAnimal.hacerSonido(); // Salida: El gato maulla.
    }
}
```

Se incorpora la anotación _@Override_ para notificar al compilador que deseamos sobreescribir un método heredado. Esta anotación es opcional pero recomendada, ya que facilita la lectura y permite evitar errores que pueden ser difíciles de detectar.

En el ejemplo vemos que estamos definiendo comportamiento específico a cada subclase a través de la sobreescritura del método _hacerSonido_. Esto provee también **polimorfismo de inclusión** (también denominado de subtipo o herencia), porque _miAnimal_ se comporta de diferentes formas según la instancia que tenga asociada.

### Vinculación Dinámica de Métodos
La vinculación dinámica (**dynamic binding**) es el proceso mediante el cual Java decide en **tiempo de ejecución** qué versión de un método debe invocarse en función del tipo real del objeto o instancia al que se hace referencia, en lugar del tipo declarado de la variable de referencia.

> Cuando se llama a un método sobreescrito en un objeto, Java determina cuál de las implementaciones (la de la superclase o la de la subclase) debe ejecutarse, según el tipo real del objeto.

En el ejemplo anterior, la vinculación dinámica permite que el método _hacerSonido()_ se ejecute según el tipo real del objeto _miAnimal_, incluso si la referencia es del tipo de su superclase _Animal_.

### Ejercicio: Alquilando vehículos

a) Crear una clase _Vehiculo_ con los siguientes atributos y métodos:
- Atributos: 
    - marca (String)
    - modelo (String)
    - precioBase (double).
- Métodos:
    - Un constructor que acepte la marca, modelo y precio base del vehículo.
    - Un método _calcularCostoAlquiler(int dias)_ que calcule el costo de alquiler del vehículo durante el número de días especificado. El costo se calcula como precioBase * dias.

b) Crear dos subclases _Auto_ y _Moto_, que hereden de la clase _Vehiculo_. Las subclases deben incluir un constructor que llame al constructor de la superclase y también deben sobrescribir el método _calcularCostoAlquiler(int dias)_ de la siguiente manera:
- Para Auto, el costo de alquiler se calcula incrementando un 20% el costo común.
- Para Moto, el costo de alquiler se calcula con un descuento del 15% respecto al vehículo.

c) En el método main de una clase llamada _PruebaAlquiler_, crear instancias de Vehiculo, Auto y Moto para calcular y mostrar el costo de alquiler para un número de días específico.

## Ejercicio: Tipos de Viajes
Una empresa ferroviaria administra viajes en tren entre dos estaciones terminales de su red.

Un viaje tiene asociado un **trayecto** (desde una estación terminal de origen a una de destino, con una distancia determinada y una cantidad de estaciones), una cierta **cantidad de vagones** y una **capacidad máxima de pasajeros**.

También posee qué tipo de viaje corresponde en relación a sus características técnicas, si es un viaje con tecnología diesel, si es eléctrico o si es de alta velocidad (esto es independiente del trayecto recorrido).

- **Viaje diesel:** El tiempo de demora promedio -en minutos- es la distancia en kilómetros multiplicada por la cantidad de estaciones dividido 2 sumada a la cantidad de estaciones y de pasajeros dividido 10.

- **Viaje eléctrico:** El tiempo de demora promedio -en minutos- es la distancia en kilómetros multiplicada por la cantidad de estaciones dividido 2. 

- **Viaje de alta velocidad:** El tiempo de demora promedio -en minutos-es la distancia en kilómetros dividido 10.

Definir dentro de la clase _Viaje_ el método _tiempoDeDemora_, que retorne la cantidad de minutos que tarda en efectuar su recorrido con las siguientes variantes:

a) Especializando la clase _Viaje_ en función del tipo de viaje.

b) Sin especializar la clase _Viaje_, relacionándola con la clase _TipoDeViaje_, que está especializada por cada tipo de viaje.

# Conversión de tipos

Finalizamos esta sección analizando lo que significa la conversión de tipos o **type casting** en Java. Este concepto es necesario para comprender las reglas que utiliza el lenguaje para determinar cuándo es posible convertir un tipo de dato en otro de forma segura. 

[Especificación para conversión en Java 8](https://docs.oracle.com/javase/specs/jls/se8/html/jls-5.html)

> En Java, las conversiones de tipos se dividen en dos categorías: ampliación (**widening**) y estrechamiento (**narrowing**). A su vez, podemos distinguir el tratamiento entre variables primitivas y de referencia.

## Conversión de Tipos primitivos

### Widening
Es **la conversión de un tipo de dato más pequeño en uno más grande sin pérdida de datos**. Esta conversión se realiza automáticamente de forma **implícita** cuando se asigna un valor de un tipo más pequeño a un tipo más grande. Esto se debe a que no hay riesgo de pérdida de información, ya que el rango de valores del tipo más pequeño es siempre un subconjunto del rango del tipo más grande.

```java
int numeroInt = 13;
double numeroDouble = numeroInt;    // Conversión automática de int a double
System.out.println(numeroDouble);   // Resultado: 13.0
```
En este ejemplo, el valor de _numeroInt_ se amplía a _numeroDouble_ sin ningún problema.

### Narrowing
Es **la conversión de un tipo de dato más grande en uno más pequeño**. Esta conversión puede resultar en la **pérdida de información** si el valor no se puede representar con precisión en el tipo de destino. Por lo tanto, el estrechamiento debe realizarse de manera **explícita** en Java usando paréntesis y especificando el tipo de destino.

```java
double numeroDouble = 13.55;
int numeroInt = (int) numeroDouble;     // Conversión explícita de double a int
System.out.println(numeroInt);          // Resultado: 13
```
En este ejemplo, el valor de _numeroDouble_ se estrecha a _numeroInt_, y **la parte decimal se trunca**.

## Conversión de Tipos de Referencia

La conversión de tipos para variables de referencia se utiliza contemplando la jerarquía de herencia. Es necesario que los tipos de datos que deseamos convertir estén **relacionados a través de la herencia**, de lo contrario no podremos hacerlo.

### Widening
Se denomina conversión ascendente o **upcasting** cuando se pasa de una referencia de una clase derivada a una referencia de una superclase o interfaz implementada. Esto se hace de manera **implícita** y **es siempre segura**, ya que la clase derivada es una extensión de la clase base.

```java
class Animal { }
class Perro extends Animal { }

Perro miPerro = new Perro();
Animal miAnimal = miPerro;      // Upcasting implícito
```

### Narrowing
Se denomina conversión descendente o **downcasting** cuando pasamos de una referencia de una superclase o interfaz a una referencia de una clase derivada. Esto se hace de manera **explícita** y **puede generar una excepción _ClassCastException_** si la conversión no es válida.

```java
class Animal { }
class Perro extends Animal { }

Animal miAnimal = new Perro();
Perro miPerro = (Perro) miAnimal;   // Downcasting explícito
```

### Relación de tipos en Arreglos
Si bien no entraremos en detalle en este curso sobre los conceptos de covarianza, contravarianza e invarianza, mencionaremos que los arreglos en Java son **covariantes** respecto al tipo de dato de sus elementos. Esto significa que respetan el orden que puede existir con los tipos de datos de elementos. Es importante tener claro esto cuando intentemos convertir este tipo de referencias. Veamos un ejemplo para clarificar.

```java
Number[] numeros;   // Arreglo de elementos de tipo Number
Integer[] enteros = new Integer[3];  // Arreglo de elementos de tipo Integer
numeros = enteros;  // Asignación OK. Integer[] es subtipo de Number[]
numeros[0] = 1;     // Asignación OK. Implícitamente es Integer.valueOf(1)
numeros[1] = 2.5;   // Arroja excepción ArrayStoreException
```
Es correcta la asignación de _enteros_ a la variable _numeros_ porque _Integer[]_ es subtipo de _Number[]_, al igual que _Integer_ es subtipo de _Number_, por ello se dice que son covariantes. El error en **tiempo de ejecución** (_ArrayStoreException_) ocurre porque el literal 2.5 es implícitamente _Double.valueOf(2.5)_, lo que devuelve un objeto de tipo _Double_, y ese tipo no tiene ninguna relación con _Integer_. Por eso no puede hacerse la conversión para insertarlo en el arreglo. Esta es una desventaja que resulta de disponer esta característica en arreglos de Java, porque **no valida esa conversión ilegal en tiempo de compilación**.

> Es recomedable inclinarse a utilizar tipos genéricos (_Generics_) antes que arreglos. Los veremos más adelante.

## Ejercicio: Corregir el _ClassCastException_
Supongamos que tenemos una jerarquía de clases que incluye una clase base _Vehiculo_ y dos clases derivadas, _Auto_ y _Moto_. Intentaremos realizar un downcasting de un objeto _Vehiculo_ a la clase _Auto_, lo cual generará un error:

```java
public class Main {
    public static void main(String[] args) {
        Vehiculo[] vehiculos = new Vehiculo[2];
        vehiculos[0] = new Moto();      // Upcasting implícito
        vehiculos[1] = new Auto();      // Upcasting implícito
        
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.acelerar();
            Auto auto = (Auto) vehiculo;  // Downcasting: error en tiempo de ejecución
            auto.subirVentanas();         // Método exclusivo de Auto
        }
    }
}
```
El código fuente está disponible en la [carpeta src](./src/) de esta unidad.

En este ejemplo, inicialmente realizamos upcasting implícitos al asignar un objeto de la clase _Auto_ y _Moto_ a una variable de la clase base _Vehiculo_. Sin embargo, cuando intentamos hacer un downcasting de _vehiculo_ a _Auto_, Java generará un error en tiempo de ejecución, ya que el primer objeto subyacente es de tipo _Moto_. Esto genera una excepción _ClassCastException_. El código después del intento de downcasting no se ejecutará debido a este error.

- a) Probar qué sucede si eliminamos la línea del downcasting y cambiamos la última línea por: `vehiculo.subirVentanas()`.
- b) Corregir el error modificando la clase _Main_ contemplando que sólo debemos subir las ventanas cuando tengamos un objeto de _Auto_.
- c) Agregar un parámetro de tipo _int_ en el método _acelerar()_ y modificar el _main_ para que suceda una conversión de estrechamiento con ese parámetro.