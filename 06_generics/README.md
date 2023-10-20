# Tipos paramétricos

En la programación orientada a objetos vimos que uno de los conceptos clave es la reutilización de código. Los objetos permiten encapsular datos y comportamiento en una unidad coherente, lo que facilita la creación de aplicaciones más mantenibles y escalables. Sin embargo, cuando trabajamos con objetos en Java, a veces enfrentamos el desafío de manejar diferentes tipos de datos de manera segura y eficiente. Aquí es donde entran en juego los tipos paramétricos o **Generics** en Java.

## ¿Qué es Generics?
Es una característica del lenguaje Java que nos permite crear clases, interfaces y métodos que funcionan con tipos de datos específicos sin comprometer la seguridad de tipos. Podremos así utilizar en nuestro código **variables de tipo** en lugar de tipos de datos concretos. Nos permiten escribir código que puede ser reutilizado con diferentes tipos de datos de manera segura.

Imaginemos que estamos escribiendo una clase para almacenar una lista de cualquier tipo de elemento. Sin _Generics_, podríamos hacer algo así:

```java
public class ListaNoGenerica {
    private Object[] elementos = new Object[10];
    private int size = 0;

    public void agregar(Object elemento) {
        // TODO: Resize de arreglo elementos
        elementos[size++] = elemento;
    }

    public Object obtener(int indice) {
        // TODO: Validación de índice fuera de rango
        return elementos[indice];
    }
}
```

En esta implementación estamos usando un arreglo de tipo _Object_ para almacenar elementos en nuestra lista. Esto significa que podemos almacenar cualquier tipo de objeto en la lista. Sin embargo, también significa que cuando obtengamos un elemento de la lista, necesitaremos realizar una conversión explícita de tipo (_downcasting_), lo cual puede ser propenso a errores en tiempo de ejecución.

```java
ListaNoGenerica lista = new ListaNoGenerica();
lista.agregar(11);              // Autoboxing a Integer y Upcasting a Object
listaEnteros.agregar("hola");   // Upcasting de String a Object
listaEnteros.obtener(0);        // Devuelve Object con referencia a Integer (11)
(Integer) listaEnteros.obtener(0);  // Devuelve 11 (Integer), pero es inseguro.
(Integer) listaEnteros.obtener(1);  // ClassCastException
```

Con _Generics_ podemos hacer esto de manera más segura y sin necesidad de conversiones de tipo:

```java
public class ListaGenerica<T> {
    private T[] elementos;
    private int size = 0;

    public ListaGenerica(int capacidad) {
        elementos = (T[]) new Object[capacidad];
    }

    public void agregar(T elemento) {
        // TODO: Resize de arreglo elementos
        elementos[size++] = elemento;
    }

    public T obtener(int indice) {
        // TODO: Validación de índice fuera de rango
        return elementos[indice];
    }
}
```
Con esta implementación genérica, podemos crear una lista que almacena elementos de cualquier tipo sin necesidad de conversiones de tipo. Notemos que ahora utilizamos una variable de tipo _T_ en lugar de _Object_. Cuando declaramos una variable del tipo _ListaGenerica_ debemos hacerlo con un tipo de dato (clase o interfaz) específico en su parámetro que _sustituirá_ a _T_, de forma que en **tiempo de compilación** se pueda hacer el _type checking_ necesario.

```java
ListaGenerica<Integer> listaEnteros = new ListaGenerica<>(10);
listaEnteros.agregar(11);
listaEnteros.agregar(21);
listaEnteros.obtener(1);    // Devuelve 21 (Integer)
```

## Declaración

### Tipo Genérico

```java
public class MiClaseGenerica<T> {...}
```

Para declarar una clase o interfaz genérica, definimos los parámetros de tipo separados por coma y encerrados entre los símbolos < y > después del nombre de la clase. También podemos usar otros nombres para las variables de tipo. Según [documentación oficial](https://docs.oracle.com/javase/tutorial/java/generics/types.html):

> - E - Element (used extensively by the Java Collections Framework)
> - K - Key
> - N - Number
> - T - Type
> - V - Value
> - S,U,V etc. - 2nd, 3rd, 4th types

### Método Genérico

También podemos declarar **métodos genéricos** dentro de clases no genéricas. Para hacerlo usamos la misma sintaxis <_T_> **antes del tipo de retorno** del método. En este caso, el parámetro _T_ sólo están en el alcance (_scope_) del método. Podemos usarlo para métodos estáticos, de instancia o constructores.

```java
public class MiClaseNoGenerica {
    public <K, V> void miMetodoGenerico(K clave, V valor) {
        // ...
    }
}
```
> Recordemos que podemos definir más de un parámetro de tipo cuando usamos _Generics_.

## Creación de Objetos Genéricos
Cuando creamos objetos de una clase genérica especificamos el tipo de datos que queremos utilizar entre los signos de mayor y menor **< >**. Por ejemplo:

```java
MiClaseGenerica<Integer> objetoEntero = new MiClaseGenerica<Integer>();
MiClaseGenerica<String> objetoString = new MiClaseGenerica<>();
```

En este ejemplo creamos dos objetos de _MiClaseGenerica_, uno que almacena enteros y otro que almacena cadenas. Notemos que en la primera inicialización agregamos _Integer_ como parámetro en el constructor, pero esto no es necesario porque Java puede inferirlo a partir de la declaración de la variable (_MiClaseGenerica\<Integer>_). Por eso al inicializar _objetoString_ podemos usar el operador diamante <> en la inicialización.

### Ejercicio: Caja contenedora
1. Implementar una clase genérica _Contenedor\<T>_ que permita almacenar un elemento de cualquier tipo de dato.
2. Agregar el siguiente comportamiento a la clase:
    - agregar elemento al contenedor
    - quitar el elemento del contenedor
    - verificar si el contenedor está vacío
    - visualizar el elemento dentro del contenedor
    - comparar si el elemento dentro del contenedor es igual a otro elemento
3. Generar una instancia de _Contenedor_ y probar su funcionamiento insertando diferentes objetos, visualizando el contenido y comparando si es igual a cierto otro objeto.
## Parámetros acotados
A veces es necesario limitar los tipos que se pueden utilizar en una clase genérica. Esto puede hacer mediante parámetros de tipos acotados o **_bounded type parameters_**. Por ejemplo, si quisiéramos que una clase genérica sólo pueda aceptar un tipo numérico como parámetro lo haríamos así:

```java
public class ClaseGenericaNumerica<T extends Number> {...}
```
De esta forma estamos acotando al tipo aceptado como parámetro a alguno que sea _Number_ o cualquiera de sus clases derivadas. Esto aplica también para interfaces, es decir, que el _extends_ puede funcionar para definir que sea de cierta interfaz o cualquiera de las clases que la implementa. Esto último podemos verlo con el ejemplo de aceptar cualquier tipo _Comparable_, aquellos que implementan esa interfaz.

```java
public class ClaseLimitada<T extends Comparable<T>> {...}
```

### En métodos genéricos
Para el caso de métodos genéricos también es posible acotar los tipos aceptados. Se define de la misma forma en la declaración del parámetro. Por ejemplo, si deseamos implementar una operación que cuente la cantidad de elementos menores a cierto elemento de un arreglo de cualquier tipo, podríamos hacer algo así:

```java
public static <T extends Comparable<T>> int contarMenores(T[] arreglo, T elemento) {
    int total = 0;
    for (T e : arreglo) {
        if (e.compareTo(elemento) < 0) {
            total++;
        }
    }
    return total;
}
```
Lo iteresante de esto es que este método funciona para arreglos de cualquier tipo de dato, siempre y cuando implementen la interfaz _Comparable_ y, por lo tanto, tengan definido el método _compareTo_ que exige esa intefaz.

### Múltiples restricciones
Es posible definir más de una cota superior al tipo de dato. Por ejemplo, si quisiéramos definir una clase genérica que acepte tipos numéricos, comparables y clonables.

```java
public class ClaseGenericaMasLimitada<T extends Number & Comparable<T> & Clonable> {...}
```
Entonces _T_ debería ser de tipo _Number_, _Comparable\<Number>_ y _Clonable_ para poder ser utilizado en esta nueva clase genérica. Notemos que si alguna de las restricciones es una clase (_Number_ en este caso), se debe colocar **antes que las interfaces** en la definición.

### Ejercicio: Caja contenedora numérica
Contemplando el ejercicio previo [Caja Contenedora](#ejercicio-caja-contenedora), implementar una nueva clase _ContenedorNumerico_ donde el parámetro de tipo debe ser Number o algún subtipo.

## Herencia en _Generics_
Es común confundirnos con la jerarquía de herencia de los tipos que pasamos como argumentos a tipos genéricos, porque podríamos pensar que, si tenemos dos objetos de un tipo genérico donde uno acepta un parámetro _Number_ y otro acepta un _Integer_, entonces el segundo objeto sería subtipo del primero por la relación entre los parámetros. Esto no es correcto, ya que **los tipos genéricos son invariantes**.

> Si desean profundizar los conceptos de _Covarianza_, _Contravarianza_ e _Invarianza_ en tipos de datos:
> - [Covarianza y contravarianza en genéricos](https://learn.microsoft.com/en-us/archive/blogs/ericlippert/whats-the-difference-between-covariance-and-assignment-compatibility)
> - [What's the difference between covariance and assignment compatibility?](https://learn.microsoft.com/en-us/archive/blogs/ericlippert/whats-the-difference-between-covariance-and-assignment-compatibility)

```java
Number numero;
Integer entero = Integer.valueOf(3);     
numero = entero;    // Upcasting correcto

List<Number> numeros;
List<Integer> enteros = new ArrayList<>();
numeros = enteros;  // Error de compilación: Type mismatch: cannot convert from List<Integer> to List<Number>
```
Si bien podemos asignar una referencia de _Integer_ a una variable de tipo _Number_ porque la primera es también de tipo _Number_, no debemos confundir que esa herencia sea trasladada al tipo paramétrico. **_List\<Number>_ no tiene ninguna relación con _List\<Integer>_**.

Por otra parte, sí podemos establecer jerarquías de herencia a través de extender de otras clases genéricas o implementar interfaces genéricas. Esta relación aplica sobre los tipos paramétricos y no sobre los argumentos de tipo. Por ejemplo, en las [colecciones de Java](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html) se define esa relación de herencia porque _ArrayList\<E>_ implementa _List\<E>_ y esta última extiende a _Collection\<E>_. Entonces podemos afirmar que _ArrayList\<Integer>_ es subtipo de _List\<Integer>_, la cual es subtipo de _Collection\<Integer>_.

## Wildcards
Una forma de lograr que un tipo paramétrico no sea **invariante** respecto a su argumento es a través del uso de comodines o **wildcards** (**?**), lo cual nos provee mayor flexibilidad pero con cierto recaudo en las operaciones que podemos hacer sobre ellos. Estos comodines se pueden utilizar en el momento de declarar un tipo paramétrico, no al momento definirlo. Entonces, en lugar de utilizar un tipo específico como argumento, podemos utilizar un comodín que básicamente significa **tipo desconocido**. En general, se utilizan acotados (**_Bounded_**) para describir que puede usarse cualquier tipo de dato con cierta restricción.

### Upper Bounded
Un comodín que actúa como cota superrior nos permite indicar que es un tipo desconocido que puede ser de **cierto tipo o cualquiera de sus subtipos**, a través del cual se logra que sea **covariante**.

```java
List<? extends Number> numeros = new ArrayList<Integer>();
```
La asignación es válida porque ahora _numeros_ es de tipo _List\<? extends Number>_, mientras que _ArrayList\<Integer>_ es subtipo de _List\<Integer>_ (por definición de las colecciones) y esta última es subtipo de la primera. También podríamos afirmar que _List\<? extends Number>_ es subtipo de _List\<?>_, la cual sería una interfaz de lista que puede tener cualquier tipo de dato como elemento (**_Unbounded_**).

Esta capacidad de utilizar comodines como argumentos de tipo es poderosa para lograr esa relación de herencia en tipos paramétricos y así aprovechar el **polimorfismo**, pero conlleva también un riesgo. Por ejemplo, **no podríamos nunca _insertar_ elementos** a la variable _numeros_ porque no es posible saber con certeza qué objeto tiene asociado (en particular, con qué argumento de tipo se ha instanciado _ArrayList_).

```java
List<? extends Number> numeros = new ArrayList<Integer>();
numeros.add(10);    // Error en tiempo de compilación
```
El compilador nos detiene porque no tiene forma de saber de qué subtipo de _Number_ es la lista asociada a _numeros_. Entonces, ¿para qué nos serviría usar wildcards acotadas superiormente? En este caso, donde estamos restringiendo con una cota superior de clase _Number_, es útil **únicamente para _leer_** los elementos de arreglo de forma segura (tratarlo como **productor** o **producer**).

```java
ArrayList<? extends Number> numeros;
ArrayList<Integer> enteros = new ArrayList<Integer>();
numeros = enteros;
enteros.add(10);
// ...
for (Number numero : numeros) {
    // Hacer algo con numero (es un objeto de Integer)
}
```

### Lower Bounded
Similar al caso previo, también podemos pasar como argumento a un tipo genérico un **lower bounded wildcard** que indica un tipo desconocido que puede ser de **un cierto tipo o cualquiera de sus supertipos**, a través del cual logramos que sea **contravariante**.

```java
public static void insertarNumeros(List<? super Integer> numeros) {...}
```
Esta cota inferior (_Integer_) para el comodin del tipo de la lista permite entonces que el método _insertarNumero_ sea invocado con un argumento que puede ser: _List\<Integer>_, _List\<Number>_ o _List\<Object>_, ya que existe una relación de herencia entre ellos gracias al comodín.

A diferencia del caso previo, en el parámetro _numeros_ podemos **únicamente _escribir_ elementos** de forma segura (tratarlo como **consumidor** o **consumer**), pero no podemos leerlos porque no sabríamos qué instancia de lista tenemos realmente. No sabríamos qué tipo de dato tiene un elemento obtenido (puede ser _Integer_, _Number_ u _Object_). La única excepción de _lectura_ segura sería si tratamos sus elementos siempre como _Object_.

### Unbounded
Finalmente, ¿qué podríamos hacer si necesitamos tratar a un tipo genérico tanto como _consumer_ y _producer_ a la vez? En ese caso podríamos usar directamente el comodín sin restricción: **unbounded wildcard** (?). El problema de usar esta opción es que deberíamos tratar el parámetro del tipo **siempre como _Object_**.

```java
public static void mostrarElementos(List<?> elementos) {
    for (Object elemento: elementos) {
        System.out.print(elemento + ", ");
    }
}
```
El método del ejemplo funciona bien porque sirve para listas de cualquier tipo de elemento, porque la operación que se aplica sobre cada uno de ellos es _Object.toString()_ cuando se lo muestra. Al no necesitar realizar operación especial según el tipo de dato real de cada instancia, es una muy buena opción para definir métodos con mayor flexibilidad.

### ¿Cómo y cuándo usar _wildcards_?
Siempre dependerá de la situación o diseño que estemos construyendo, pero suele ser **recomendable el uso de _wildcards_** porque siempre tendremos mayor flexibilidad en nuestra solución. A continuación describiremos algunos consejos para el uso de correcto de comodines:

> - Cuando necesitamos que una variable genérica _produzca_ información (**_variable de entrada_**), podemos definirla con **upper bounded wildcard**.
> - Cuando necesitamos que una variable genérica _consuma_ información (**_variable de salida_**), podemos definirla con **lower bounded wildcard**.
> - Cuando podemos acceder a una **_variable de entrada_** únicamente con métodos de _Object_, podemos definirla con **unbounded wildcard**.
> - Si tenemos la necesidad de usar una **_variable de entrada/salida_**, entonces **no utilicemos wildcard**.

Recordemos que los parámetros de operaciones pueden clasificarse como:
- **variable de _entrada_** (_in_): provee información necesaria para la operación. Por ejemplo, la variable _arreglo_ en _contarDuplicados(List\<T> arreglo)_.
- **variable de _salida_** (_out_): sirve para almacenar información durante la ejecución de la operación para luego ser utilizada en otro lugar. Por ejemplo, la variable _arreglo_ en _insertar(T elemento, List\<T> arreglo)_.
- **variable de _entrada/salida_**: cumple ambos roles a la vez. Por ejemplo, la variable _arreglo_ en _ordenar(List\<T> arreglo)_.

## Ejercicio: Nuestra Lista Genérica
1. Generar una versión propia llamada _ListaGenérica_ que extienda a la clase _AbstractList\<E>_. Debe utilizarse como estructura un arreglo nativo de Java para almacenar los elementos, el cual debe crecer y reducirse a medida que se agregan o eliminan los elementos. No importa el criterio utilizado, puede copiarse ese comportamiento de la [Lista No Genérica](./src/ListaNoGenerica.java), no se busca eficiencia.

    [Ver newInstance de la clase Array](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Array.html#newInstance-java.lang.Class-int-).
    
2. Completar la funcionalidad sobreescribiendo los métodos:
    - public E set(int index, E element)
    - public void add(int index, E element)
    - public E remove(int index)

## Conclusión
Los tipos genéricos o paramétricos ofrecen una herramienta muy útil al momento de diseñar soluciones ya que nos proveen:
- **Seguridad de tipos**: Nos ayudan a detectar errores de tipo en tiempo de compilación en lugar de en tiempo de ejecución.
- **Reutilización de código**: Podemos escribir componentes genéricos que funcionen con una amplia variedad de tipos de datos.
- **Legibilidad del código**: Hacen que el código sea más claro y autodocumentado al indicar explícitamente qué tipo de datos se espera.

> **Lectura de interés**: 
> - [The Java Tutorials. Lesson: Generics](https://docs.oracle.com/javase/tutorial/java/generics/index.html)
> - _Effective Java 3rd, de Joshua Bloch_
>   - Item 26: Don’t use raw types
>   - Item 27: Eliminate unchecked warnings
>   - Item 28: Prefer lists to arrays
>   - Item 29: Favor generic types
>   - Item 30: Favor generic methods
>   - Item 31: Use bounded wildcards to increase API flexibility