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

La sobreescritura (**overriding**) de métodos permite a una subclase proporcionar su propia variación de un método heredado de su superclase. En Java, se utiliza la vinculación dinámica de métodos (**dynamic binding**) que resuelve en **tiempo de ejecución** qué versión o implementación de un método debe ejecutarse.

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