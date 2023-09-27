# Igualdad

En esta sección efiniremos brevemente cómo analizar la igualdad entre dos variables. Este concepto es clave ya que se utiliza de forma regular para comparar, por ejemplo, si existe cierto elemento en un arreglo.

## Identidad vs Igualdad

Un tipo de **igualdad de identidad** o de referencia aplica para determinar si dos variables tienen la misma referencia a una instancia. Por otro lado, si queremos determinar la **igualdad semántica o lógica** deberíamos implementarla contemplando tanto los tipos de datos como los estados de los objetos comparados.

La igualdad de identidad podemos realizarla con el operador **==**, mientras que la semántica se puede hacer a través del método **equals** que está definido en _Object_. Por defecto, el método _equals_ realiza una comparación de identidad, tal como está implementado en _Object_. Entonces, si deseamos definir una igualdad más apropiada para nuestras clases, debemos **sobreescribir el método _equals_**.

Veamos el siguiente ejemplo:

```java
Integer a = 10;
Integer b = new Integer(10);
System.out.println(a == b);         // false
System.out.println(a.equals(b));    // true
```

Ambas variables de tipo _Integer_ pueden compararse con el operador _==_ para determinar si son la misma referencia, caso contrario el compilador detectaría que son de distinto tipo y no podrían compararse así. En este caso, la comparación de identidad es falsa porque _b_ es una nueva instancia explícita de Integer. La comparación de igualdad sí es verdadera porque en ese caso el método _equals_ de Integer (sboreescribe al de Object) compara el valor entero que almacena esa referencia.

## Variables primitivas

En el caso de variables primitivas, el operador **==** realiza una **comparación de igualdad**, ya que no son variables de referencia y no pueden compararse en su identidad.

## Propiedades de la igualdad

Recordemos las propiedades algebraicas que deben cumplirse en una relación de equivalencia en la teoría de conjuntos, pensándolas desde la igualdad:
- **Reflexividad**: Todo elemento es igual a sí mismo.
- **Simetría**: Si un elemento es igual a otro, entonces ese otro elemento también es igual al primero.
- **Transitividad**: Si un elemento es igual a otro y ese otro es igual a un tercero, entonces el primero también es igual a ese último.

Estas propiedades son importantes al momento de definir la igualdad en nuestras clases cuando implementamos nuestra versión del método _equals_.

### El contrato del _equals_

Según la API de Java8: https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-, podemos ver que se apoya en las propiedades previas.

> The equals method implements an equivalence relation on non-null object references:
> - It is _reflexive_: for any non-null reference value x, x.equals(x) should return true.
> - It is _symmetric_: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.
> - It is _transitive_: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.
> - It is _consistent_: for any non-null reference values x and y, multiple invocations of x.equals(y) consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.
> - For any non-null reference value x, x.equals(null) should return false.

Le agrega la idea de _consistencia_ para reforzar que la comparación devuelva el mismo valor siempre y cuando el estado de los objetos comparados no cambie. También agrega el detalle de comparar contra _null_.

## El método _equals_

> **Lectura de interés**: _Item 10: Obey the general contract when overriding equals - Effective Java 3rd, de Joshua Bloch_.

La firma del método _equals_ definido en _Object_ es la siguiente:

```java
@Override
public boolean equals(Object otro)
```

Podemos seguir los siguientes pasos para implementar (sobreescribir) nuestro método _equals_.
1. Verificar misma referencia

    Siempre validamos la propiedad de reflexividad, si es la misma referencia será también igual con el _equals_.

    ```java
    if (this == otro) {
        return true;
    }
    ```

2. Verificar _null_

    Cuando comparamos contra un _null_, devolvemos falso (última condición del [contrato](#el-contrato-del-equals)).
     ```java
    if (otro == null) {
        return false;
    }
    ```

3. Verificar misma clase o superclase

    Este punto puede ser controversial, porque depende de la abstracción que estemos modelando. Podríamos comparar si el otro objeto es del mismo tipo exacto (misma clase) así:

    ```java
    if (this.getClass() != otro.getClass()) {
        return false;
    }
    ```
    Si bien es válido para comparar objetos de misma clase, puede fallar para comparar objetos de subclase y superclase. Por ejemplo, si comparamos una _Persona_ con _Estudiante_ devolvería siempre falso más allá que tengan mismo nombre. Otra opción sería:
    ```java
    if (!(otro instanceof Persona)) {
        return false;
    }
    ```
    El operador **instanceof** valida si una instancia es de cierto tipo, lo cual incluye comparar el tipo exacto y todos los supertipos (recordemos que por herencia un _Estudiante_ es de tipo _Estudiante_ y _Persona_). Así es posible comparar objetos de distintas clases en la jerarquía de herencia, pero también se podría violar la propiedad de **simetría**. Si _Estudiante_ sobreescribe el _equals_ para comparar por un campo nuevo (la matrícula), comparar _Persona_ con _Estudiante_ llamaría al _equals_ de _Persona_, mientras que _Estudiante_ con _Persona_ llamaría al nuevo _equals_ que valida este nuevo campo que no existe en _Persona_.
    ```java
    Persona objPersona = new Persona("Juana");  // Nombre
    Estuudiante objEstudiante = new Estudiante("Juana", 12345678);  // Nombre y matrícula
    objPersona.equals(objEstudiante);       // Invoca el equals de Persona
    objEstudiante.equals(objPersona);       // Invoca el equals de Estudiante
    ```
    Una alernativa sería distinguir en el _equals_ de _Estudiante_ si estamos comparando contra una instancia de _Estudiante_ o _Persona_ para evitar comparar la matrícula si _otro_ es de tipo _Persona_. El problema más sutil con esta opción es que viola la propiedad de **transitividad**, porque podría suceder lo siguiente:
    ```java
    Persona objPersona = new Persona("Juana");
    Estuudiante objEstudiante1 = new Estudiante("Juana", 12345678);
    Estuudiante objEstudiante2 = new Estudiante("Juana", 87654321);
    objEstudiante1.equals(objPersona);      // true: mismo nombre
    objPersona.equals(objEstudiante2);      // true: mismo nombre
    objEstudiante1.equals(objEstudiante2);  // false: distinta matrícula, cuando por transitividad debería ser true
    ```
    En conclusión, dependerá de la situación que modelemos sobre qué alternativa de validación de clase utilizar. Deberemos recordar:
    - Si utilizamos _getClass_, siempre que comparemos instancias de subclase y superclase será falso.
    - Si utilizamos _instanceof_, podríamos comparar igualdad a nivel de la superclase. Deberíamos declarar **_final_** al _equals_ de la superclase para evitar que sea sobreescrito en las subclases con validaciones de atributos específicos de ellas.

4. Verificar atributos

    El último paso consiste en la validación de los campos relevantes que deseamos incluir para verificar la igualdad de dos instancias. Por ejemplo, el nombre o número de documento de una persona.
    - Si el atributo es de tipo primitivo utilizamos el operador **==**.
    - Si el atributo es de tipo referencia utilizamos el método estático de _java.util_ **Objects.equals** que facilita la comparación de atributos que pueden no estar inicializados.

    ```java
    // Asumiendo que documento es tipo int y nombre es String
    return this.getDocumento() == otro.getDocumento() 
            && Objects.equals(this.getNombre(), otro.getNombre());
    ```

Resumiendo, un ejemplo de implementación para _MiClase_ podría ser:

```java
@Override
public boolean equals(Object otro) {
    if (this == otro) {
        return true;
    }

    if (otro == null || this.getClass() != otro.getClass()) {
        return false;
    }

    MiClase otro2 = (MiClase) otro;   // Downcasting a MiClase
    return this.getAtrPrimitivo() == otro2.getAtrPrimitivo() 
            && Objects.equals(this.getAtrReferencia(), otro2.getAtrReferencia());
}
```

## El método _hashCode_

> **Lectura de interés**: _Item 11: Always override hashCode when you override equals - Effective Java 3rd, de Joshua Bloch_.

La sobreescritura del método _equals_ es necesaria para definir la igualdad de dos objetos, pero **no es suficiente**. Internamente Java utiliza en ciertos casos una **función hash** para mejorar la performance en la comparación, por ejemplo en estructuras de datos como _HashMap_. Básicamente, el método **hashCode** (también definido en _Object_) provee un **valor entero** (_hash_) que representa el estado de una instancia.

Como toda función _hash_, existe el riesgo de colisión porque el rango de valores posibles del valor entero no puede representar a todos los objetos del sistema posibles con un único _hash_. Por lo tanto, pueden existir objetos distintos que compartan un mismo _hash_ (no es lo ideal), pero siempre debemos garantizar que **dos objetos iguales tengan el mismo _hash_**.

### El contrato del _hashCode_

Similar al método _equals_, Java establece ciertas reglas que deben cumplirse con el _hashCode_: https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#hashCode--

> - Whenever it is invoked on the same object more than once during an execution of a Java application, the hashCode method must consistently return the same integer, provided no information used in equals comparisons on the object is modified. This integer need not remain consistent from one execution of an application to another execution of the same application.
> - If two objects are equal according to the equals(Object) method, then calling the hashCode method on each of the two objects must produce the same integer result.
> - It is not required that if two objects are unequal according to the equals(java.lang.Object) method, then calling the hashCode method on each of the two objects must produce distinct integer results. However, the programmer should be aware that producing distinct integer results for unequal objects may improve the performance of hash tables.

Entonces **siempre que sobreescribimos _equals_ debemos también sobreescribir _hashCode_**, que debe devolver el mismo valor si dos objetos son iguales e intentar que, si son distintos, el valor sea diferente (no entraremos en detalle con esto por ahora). Una forma simple sin entrar en detalles de performance sería apoyarnos en otra utilidad del paquete _java.util_: **_Objects.hash_**.

```java
@Override
public int hashCode() {
    return Objects.hash(atributo1, atributo2, atributo3);
}
```

Los atributos que se deben incluir son los que se comparan para determinar la igualdad o un subconjunto de ellos. La razón de usar un subconjunto es para evitar problemas de rendimiento y de inconsistencias cuando se mutan objetos dentro de una colección que utiliza _hashCode_ para indexar sus elementos. A modo práctico, por el momento es recomendable **incluir aquellos atributos que se usan en el _equals_ que no sean mutables**.

A continuación se muestra un algoritmo de _hashCode_ tal como se implementa en la clase _Arrays_, utilizando el número primo _31_ para mejorar la distribución de valores y reducir la posibilidad de colisiones.

```java
public static int hashCode(Object[] a) {
    if (a == null)
        return 0;

    int result = 1;

    for (Object element : a)
        result = 31 * result + (element == null ? 0 : element.hashCode());

    return result;
}
```
Esta misma implementación podría utilizarse para nuestras clases donde, en lugar de tener un arreglo de _Object_, usaríamos un conjunto de atributos no mutables que son parte de la validación del _equals_.

## Comparando igualdad de objetos

Finalmente veamos un ejemplo de cómo se comparan los objetos utilizando el método _equals_ o el operador _==_.

```java
public class Main {
    public static void main(String[] args) {
        MiClase objeto1 = new MiClase("A", 1);
        MiClase objeto2 = new MiClase("A", 1);
        
        // Comparación usando equals
        boolean sonIguales = objeto1.equals(objeto2);
        System.out.println("¿Son iguales? " + sonIguales);
        
        // Comparación de identidad
        boolean sonMismaReferencia = (objeto1 == objeto2);
        System.out.println("¿Son el mismo objeto? " + sonMismaReferencia);
    }
}
```

## Ejercicio: Comparación de Estudiantes

En tu sistema de gestión universitaria tenés estudiantes de grado y posgrado. Necesitamos crear clases EstudianteGrado y EstudiantePosgrado que hereden de una clase base llamada Estudiante para manejar tanto a los estudiantes de grado como a los de posgrado.

a) Crear una clase base llamada _Estudiante_ con los siguientes atributos y métodos:
- String nombre
- int edad
- int matrícula
- Constructor para inicializar nombre y edad.

b) Crear una clase _EstudianteGrado_ que herede de _Estudiante_ y agregue los siguientes atributos:
- String carreraGrado
- Constructor que inicialice nombre, edad, matrícula, y carrera de grado.

c) Crear una clase _EstudiantePosgrado_ que herede de _Estudiante_ y agregue los siguientes atributos:
- String carreraPosgrado
- Constructor que inicialice nombre, edad, matrícula, y carrera de posgrado.

d) Sobrescribir el método _equals_ en las clases _Estudiante_, _EstudianteGrado_ y _EstudiantePosgrado_ para comparar dos estudiantes en función de su matrícula y su carrera (ya sea grado o posgrado).

e) Crear un programa de prueba (Main) que:
- Cree al menos dos objetos _EstudianteGrado_ y dos objetos _EstudiantePosgrado_.
- Compruebe si dos estudiantes son iguales utilizando el método equals y muestre un mensaje que indique si son iguales o diferentes.
- Cree dos objetos de _Estudiante_ con misma matrícula y verifique si son iguales.
- También, realizar una comparación de identidad (usando ==) de al menos dos de los objetos y mostrar un mensaje que indique si son iguales o diferentes.

# La copia

Similar a la igualdad, existen dos tipos o formas de copias de objetos.

## Copia Superficial
La copia superficial (**_shallow copy_**) implica duplicar un objeto, pero no necesariamente duplicar todos los objetos que contiene como atributos. Se crea una nueva instancia del objeto a copiar, pero las referencias a objetos internos siguen siendo las mismas. Esto significa que, si modificamos un objeto interno en la copia superficial, este cambio se reflejará en el objeto original y viceversa. Es como tener dos objetos diferentes que comparten algunas partes en común.

Supongamos que tenemos una clase _Persona_ y queremos hacer una copia superficial de un objeto _Persona_. Código completo en carpeta [src](./src/).

```java
class Persona {
    private String nombre;
    private int edad;
    private Documento documento;

    // Constructor y métodos...

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNroDocumento(int nroDocumento) {
        this.documento.setNumero(nroDocumento);
    }

    public Persona copiaSuperficial() {
        Persona copia = new Persona();
        copia.nombre = this.nombre;
        copia.edad = this.edad;
        copia.documento = this.documento; // Se asigna la misma referencia original
        return copia;
    }
}

public class EjemploCopiaSuperficial {
    public static void main(String[] args) {
        Persona juana = new Persona("Juana", 22, 12345678);
        Persona copiaJuana = juana.copiaSuperficial();

        System.out.println(juana);      // Juana, 22, 12345678
        System.out.println(copiaJuana); // Juana, 22, 12345678

        // Modificar un atributo primitivo no afecta al original
        copiaJuana.setEdad(33);
        // Modificar un atributo referencia sí afecta al original
        copiaJuana.setNroDocumento(87654321);
        
        System.out.println(juana);      // Juana, 22, 87654321
        System.out.println(copiaJuana); // Juana, 33, 87654321
    }
}
```

En este ejemplo, creamos un método _copiaSuperficial()_ en la clase _Persona_ que crea una nueva instancia y copia solo los valores de los campos, no clona/copia los objetos que componen a _Persona_. Por lo tanto, se mantienen las mismas referencias que en el objeto original. La modificación de la _edad_ en _copiaJuana_ no afecta a la edad de _juana_ porque es un campo primitivo (_int_), pero el campo _documento_ es una referencia. Al modificar el estado de _documento_ se ve reflejado ese cambio en objeto original y copia.

> Se debe tener mucho cuidado con este tipo de copia porque es muy probable que introduzca defectos y comportamiento no deseado, provocando seguramente errores de tipo _NullPointerException_.

## Copia Profunda
La copia profunda (**_deep copy_**) implica clonar no solo el objeto principal sino también todos los objetos internos de manera recursiva. Esto asegura que no haya ninguna relación de referencia compartida entre el objeto original y su copia. En otras palabras, los objetos duplicados son completamente independientes.

### La interfaz _Cloneable_
En Java existe una interfaz [**_java.util.Cloneable_**](https://docs.oracle.com/javase/8/docs/api/java/lang/Cloneable.html) que permite indicar que una clase puede ser clonada, lo que significa que puede realizar copias de objetos de esa clase. Sin embargo, es importante tener en cuenta que _Cloneable_ no tiene métodos propios. Solo actúa como una marca para informar a la JVM que la clase es clonable. Para realizar una copia profunda a través de _Cloneable_, debemos seguir algunos pasos específicos:

1. **Implementar la interfaz _Cloneable_**: Debemos asegurarnos de que la clase implemente la interfaz _Cloneable_. Esto se hace simplemente agregando la interfaz a la declaración de clase.

```java
public class Persona implements Cloneable {...}
```

2. **Sobreescribir el método clone()**: Se debe sobreescribir el método [**_Object.clone()_**](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#clone--) de la superclase (_Object_ seguramente) en la clase. El método _clone()_ es protegido, por lo que debemos cambiar su visibilidad a public para consumirlo desde afuera.

```java
@Override
public Persona clone() throws CloneNotSupportedException {...}
```

> El método _Object.clone()_ **propone** que las siguientes condiciones sean verdaderas:
> - `x.clone() != x`
> - `x.clone().getClass() == x.getClass()`
> - `x.clone().equals(x)` 

3. **Llamar a super.clone()**: En el método _clone() llamar a _super.clone()_ para crear una copia superficial del objeto. Luego, realizar copias profundas con sus propios _clone()_ de los campos internos que lo requieran para evitar compartir referencias con el objeto original.

```java
@Override
public Persona clone() throws CloneNotSupportedException {
    Persona copia = (Persona) super.clone();    // Copia superficial de Persona
    copia.documento = this.documento.clone();   // Copia profunda de Documento
    return copia;
}
```

En este último paso se realiza primero la clonación invocada al método de _Object_ (la superclase de _Persona_), lo cual genera una nueva instancia de tipo _Persona_ (como el tipo de retorno de _clone_ devuelve _Object_, debemos castearlo a _Persona_). Luego se realiza el clonado del objeto del atributo _documento_ para obtener su copia independiente. La sobreescritura de nuestro _clone_ devuelve el tipo _Persona_ en lugar de _Object_, y esto es válido porque Java soporta tipos de retorno _covariantes_.

> Lamentablemente, debemos contemplar que el método _clone()_ tiene declarada la excepción _CloneNotSupportedException_, la cual es _checked_ (veremos más adelante excepciones). Por tal motivo debemos también declararla cuando sobreescribimos el método, mejor aún, capturarla dentro de nuestra implementación.

```java
try {
    Persona copia = (Persona) super.clone();    // Copia superficial de persona
    copia.documento = this.documento.clone();   // Copia profunda de Documento
    return copia;
} catch (CloneNotSupportedException e) {
    throw new AssertionError();
}
```
De esta forma, dado que nunca debería producirse la excepción _CloneNotSupportedException_ porque _Persona_ es _Cloneable_, documentamos esa imposibilidad lanzando la excepción _AssertionError_ si eso es violado por error.

El ejemplo de implementación completo se encuentra en la carpeta [src](./src/).

### Estrategia propia

> **Lectura de interés**: _Item 13: Override clone judiciously - Effective Java 3rd, de Joshua Bloch_.

Es cierto que la implementación de la copia profunda a través de la interfaz _Cloneable_ introduce varias complejidades que se mencionan en el _item 13_ del libro de _Bloch_. Una alternativa que propone es generar un **constructor de copia** que reciba por parámetro otro objeto de la misma clase para inicializar la nueva instancia clonada, o bien, utilizar un método estático de **construcción de copia** a partir de otro objeto de misma clase que se pase por parámetro. Ambas opciones deben contemplar la copia real de los atributos con referencias para evitar el problema de la copia superficial.

```java
// Constructor de copia
public Persona(Persona original) {...};

// Método de construcción de copia
public static Persona copiaProfunda(Persona original) {...};
```

## Ejercicio: Copia profunda sin _Cloneable_

Dadas las clases [Persona](./src/Persona.java) y [Documento](./src/Documento.java):

a) Implementar el clonado de personas a través de la estrategia de constructor de copia.

a) Implementar el clonado de personas a través de la estrategia de método de construcción de copia.