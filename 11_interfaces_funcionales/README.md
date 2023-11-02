# Interfaces funcionales
Las interfaces funcionales de Java permiten en cierta forma aplicar conceptos de la programación funcional en este lenguaje y son fundamentales para el desarrollo de aplicaciones más elegantes y mantenibles. Proporcionan una forma flexible de trabajar con funciones o comportamientos, lo que puede mejorar la claridad y la reutilización del código.

Para comprender la razón por la cual necesitamos este tipo de interfaces en Java, veamos antes de qué se trata un concepto clave en la programación funcional.

## Ciudadanos de primera clase
El concepto de **funciones como ciudadanos de primera clase** está estrechamente relacionado con otro concepto de **funciones de orden superior** que viene de las matemáticas. Una **función de orden superior** cumple al menos una de estas condiciones:
- Recibe una o más funciones como argumento
- Devuelve una función como retorno

En la computación, algunos lenguajes permite definir funciones de orden superior, es principalmente una característica clave en la **programación funcional**. De esta forma, estos lenguajes consideran a las funciones como **ciudadanos de primera clase**, ya que podemos:
- pasarlas como parámetros de una función
- devolverlas como resultado de una función
- asignarlas a variables
- almacenarlas en estructuras de datos

Entonces, las funciones de orden superior son aquellas funciones que pueden aceptar otras funciones como argumentos y/o devolver funciones como resultados. Nos permiten la creación de abstracciones más poderosas y genéricas en el código, ya que las funciones de orden superior pueden ser configuradas o personalizadas para realizar una variedad de tareas. A su vez, nos facilitan la composición de funciones, la modularidad y la reutilización del código.

En un lenguaje que admite funciones como ciudadanos de primera clase, **las funciones son tratadas como cualquier otro tipo de dato**, como números o cadenas de texto. Esto significa que las funciones pueden ser asignadas a variables, pasadas como argumentos a otras funciones, retornadas como resultados de funciones y almacenadas en estructuras de datos.

Esto nos provee de gran flexibilidad en el diseño de soluciones. Imaginemos que deseamos aplicar diferentes operaciones sobre los elementos de un arreglo. Una opción sería implementar diferentes métodos, uno para cada operación, donde dentro de un bucle se aplique esta operación particular. Por ejemplo, si queremos elevar al cuadrado todos los números de un arreglo, implementaríamos ese método específico realizando la operación `elemento * elemento` dentro del bucle. Ahora bien, si pudiéramos pasar la operación a realizar como parámetro, podríamos definir un único método que la aplique a los elementos. Luego, al momento de invocarlo le decimos qué operación queremos hacer. Esta versión más flexible es propia del estilo funcional (la función `map`).

Veamos este ejemplo en python:

```python
# Función de orden superior que aplica una función a cada elemento de una lista
def aplicar_operacion(lista, operacion):
    resultado = []
    for elemento in lista:
        resultado.append(operacion(elemento))
    return resultado

# Definición de funciones que se aplicarán a la lista
def cuadrado(x):
    return x * x

def inverso(x):
    return 0 - x

# Uso de funcion de orden superior
numeros = [1, -2, 3, -4, 5, -6]
numeros_cuadrados = aplicar_operacion(numeros, cuadrado)  # Elevar al cuadrado
numeros_inversos = aplicar_operacion(numeros, inverso)   # Inverso aditivo

print(numeros_cuadrados)  # [1, 4, 9, 16, 25, 36]
print(numeros_inversos)  # [-1, 2, -3, 4, -5, 6]
```
En este ejemplo definimos una función de orden superior `aplicar_operacion` que recibe una función como segundo parámetro. Luego, como en Python las funciones son ciudadanos de primera clase (son objetos), podemos pasarlas como argumento a nuestra función previa. 

>En Java, las funciones (métodos) no son ciudadanos de primera clase, por lo cual debemos utilizar el concepto de **interfaz funcional** para _pasar funcionalidad_ como argumento. De lo contrario, podríamos utilizar una instancia de una clase (o una [clase anónima](https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html)) para pasar como argumento de un método, algo que no suele ser muy elegante o práctico de implementar.

## ¿Qué es una interfaz funcional?
Es una **interfaz que tiene sólo un [método abstracto](../05_interfaces_y_clases_abstractas/README.md#método-abstracto)** (**_SAM_**: Single Abstract Method). Esto significa que la interfaz define **un único comportamiento** que debe ser implementado por las clases que la utilicen. 

> Tengamos en cuenta que los [métodos default](../05_interfaces_y_clases_abstractas/README.md#interfaz-en-java), y los métodos estáticos **no cuentan** para esta restricción. Una interfaz funcional podría tener varios de esos métodos. Lo importante es que tenga **un único método abstracto**.

Ejemplo de interfaz funcional:

```java
@FunctionalInterface
interface OperacionBinariaEntera {
    int evaluar(int a, int b);
}
```
En este ejemplo la interfaz `OperacionBinariaEntera` define un único método abstracto llamado `evaluar` que toma dos números enteros como argumentos y devuelve un resultado. 

> La anotación **`@FunctionalInterface`** no es obligatoria, pero **es una buena práctica** para indicar que esta es una interfaz funcional, así el compilador puede hacer las validaciones correspondientes.

### Predefinidas en Java
Si bien podemos definir nuestras propias interfaces funcionales, en Java 8 se introdujo un conjunto predefinidas en el paquete [java.util.function](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html). Estas interfaces proporcionan un conjunto de operaciones comunes que suelen satisfacer cualquier necesidad, por lo cual se recomienda explorar el paquete antes de implementar una propia.

Algunas de las interfaces funcionales predefinidas son:

- [Function\<T,R>](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html): Representa una función que acepta **un argumento de tipo T** y devuelve un resultado de **tipo R**.
- [Predicate\<T>](https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html): Representa una función que acepta **un argumento de tipo T** y devuelve un valor **booleano**.
- [Supplier\<T>](https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html): Representa una fuente de datos que **no acepta argumentos** y devuelve un valor de **tipo T**.
- [Consumer\<T>](https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html): Representa una operación que acepta **un argumento de tipo T** pero **no devuelve un valor**.

Estas interfaces tienen versiones especializadas para tipos de datos primitivos como _int_, _double_, etc, que pueden utilizarse tanto como argumentos o tipos de retorno. A su vez, veremos que existen extensiones que pueden aceptar más de un argumento como `BiFunction<T,U,R>`.

## Expresiones lambda
Una de las ventajas más notables de las interfaces funcionales es la capacidad de utilizar **expresiones lambda para crear implementaciones** de estas interfaces de una manera más concisa y legible. Las expresiones lambda permiten definir funciones de manera anónima (métodos anónimos), es decir, sin declarar su nombre. En otras palabras, es una manera de **crear una función sin necesidad de definir una clase completa**.

Sintaxis de una expresión lambda:

`(parámetros) -> { cuerpo de la función }`
- parámetros: Los parámetros que la función toma como entrada.
- cuerpo de la función: El código que se ejecutará cuando la función sea invocada.

Veamos un ejemplo de expresión lambda usando la interfaz funcional que definimos antes.
```java
OperacionBinariaEntera suma = (a, b) -> a + b;
System.out.println(suma.evaluar(1,2));  // 3
```
En este ejemplo creamos una instancia de la interfaz `OperacionBinariaEntera` utilizando una expresión lambda. La expresión lambda toma dos parámetros `a` y `b` y devuelve la suma de los mismos. Notemos que **no es necesario definir una clase separada que implemente la interfaz**, por lo cual hace que el código sea más claro y conciso. De esta forma, la variable `suma` tiene una **instancia** de tipo `OperacionBinariaEntera` donde se sobreescribió el método `evaluar` con la expresión lambda `(a, b) -> a + b`.

> Si bien podemos declarar el tipo de dato de los parámetros de una expresión lambda, **es recomendable no hacerlo** para que Java lo infiera automáticamente.

También podríamos haber utilizado una **clase anónima** para hacer lo mismo, pero veamos que sería necesario más código y realmente no mejora la legibilidad respecto a las expresiones lambda. Debajo estamos generando una instancia de `OperacionBinariaEntera` y sobreescribiendo el método `evaluar` con una clase anónima.

```java
OperacionBinariaEntera suma = new OperacionBinariaEntera() {
    @Override
    public int evaluar(int a, int b) {
        return a + b;
    }
};
System.out.println(suma.evaluar(1,2));  // 3
```
> Una [**clase anónima**](https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html) nos permite **declararla e instanciarla al mismo tiempo**. Es similar a una **clase local** pero que sólo se utilizaría una vez (en la instancia generada).

### Combinando con interfaces funcionales
La interfaz `OperacionBinariaEntera` básicamente define un comportamiento o funcionalidad donde se reciben dos argumentos y se devuelve un resultado del mismo tipo. Si revisamos las interfaces predefinidas del paquete `java.util.function` veremos que existen dos interesantes para reemplazarla en nuestro ejemplo.

Ejemplo con [BiFunction](https://docs.oracle.com/javase/8/docs/api/java/util/function/BiFunction.html):
```java
BiFunction<Integer,Integer,Integer> suma = (a, b) -> a + b;
System.out.println(suma.apply(3, 7));  // 10
```
La interfaz `BiFunction<T,U,R>` permite recibir dos argumentos que pueden ser de distinto tipo y devolver un resultado que también puede ser de otro tipo de datos. Tiene definido el método abstracto `apply`.

Ejemplo con [BinaryOperator](https://docs.oracle.com/javase/8/docs/api/java/util/function/BinaryOperator.html):
```java
BinaryOperator<Integer> suma = (a, b) -> a + b;
System.out.println(suma.apply(3, 7));  // 10
```
La interfaz `BinaryOperator<T>` es una especialización de `BiFunction<T,T,T>`, recibe dos argumentos del mismo tipo de datos y devuelve el resultado del mismo tipo también.

### La interfaz `Function`
Estas interfaces que vimos son especializaciones de una más general, `Function<T,R>`, que recibe un argumento de cierto tipo y devuelve un retorno que puede ser de otro tipo. Es una de las interfaces más simples y representa un caso general para utilizar con expresiones lambda que reciben un parámetro y devuelve un valor. El método abstracto definir es el `apply`.

#### Asignación en variables
Como hemos visto previamente, podemos asignar funciones a una variable utilizando interfaces funcionales. Veamos el ejemplo con `Function`.

```java
Function<Integer, Integer> duplicar = x -> x * 2;
int resultado = duplicar.apply(9); // resultado es 18
```
En `Function<Integer, Integer>`, el primer `Integer` es el tipo de dato del parámetro, mientras que el segundo es el tipo de dato del retorno de la función. 

#### Función como argumento
También podemos pasar como argumento en un método a una función de este tipo así:
```java
void aplicarFuncionYmostrar(Function<Integer, Integer> funcion, int valor) {
    int resultado = funcion.apply(valor);
    System.out.println("Resultado: " + resultado);
}

operacionConFuncion(x -> x * x, 8); // Muestra "Resultado: 64"
```

Un ejemplo de uso puede verse en el método `computeIAbsent` de la interfaz [Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html), donde podemos cargar el valor de una clave según cierta función sobre ella, si no existe previamente.

```java
Map<String, Integer> nameMap = new HashMap<>();
Integer value = nameMap.computeIfAbsent("Juana", s -> s.length());
```
Analicemos cómo se consume internamente en la implementación de `Map`:

```java
default V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
    Objects.requireNonNull(mappingFunction);
    V v;
    if ((v = get(key)) == null) {
        V newValue;
        if ((newValue = mappingFunction.apply(key)) != null) {
            put(key, newValue);
            return newValue;
        }
    }

    return v;
}
```
En `newValue = mappingFunction.apply(key)` podemos observar que la función que definimos como argumento (nuestra expresión lambda) se aplica a la clave invocando el `apply`, que pasó a ser sobreescrito por la expresión lambda. Entonces, el valor asociado a la nueva clave será la longitud de su texto.

#### Retornando funciones
Una función puede devolver otra función como resultado. Esto es especialmente útil para crear funciones de orden superior que generan funciones personalizadas. Veamos un ejemplo:

```java
Function<Integer, Function<Integer, Integer>> sumaParcial = x -> (y -> x + y);
Function<Integer, Integer> incrementarEnDos = sumaParcial.apply(2);
int resultado = incrementarEnDos.apply(6); // resultado es 8
```
Podemos ver que combinando `incrementarEnDos` y `sumaParcial` estamos realizando una operación de _suma_ que recibe dos argumentos. La técnica de transformar esta _suma_ en dos funciones diferentes que reciben un argumento cada una se denomina **currificación** (_currying_), popular en la programación funcional. Sería la transformación inversa a la composición de funciones.

#### Funciones en estructuras
Podemos almacenar funciones en estructuras de datos como listas, arrays o mapas. Esto es útil para manejar colecciones de funciones y operar sobre ellas.

```java
List<Function<Integer, Integer>> funciones = new ArrayList<>();
funciones.add(x -> x * 3);
funciones.add(x -> x * x);

int resultado1 = funciones.get(0).apply(4); // resultado1 es 12
int resultado2 = funciones.get(1).apply(4); // resultado2 es 16
```

### Referencias a métodos
En casos donde una expresión lambda invoque a otro método existente, es posible utilizar la [referencia de ese método](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html), facilitando así la lectura de código. Veamos un ejemplo aplicado a la expresión lambda que calculaba la longitud de una cadena.

```java
// Utilizando la expresión lambda
Integer value = nameMap.computeIfAbsent("Juana", s -> s.length());

// Utilizando la referencia a método
Integer value = nameMap.computeIfAbsent("Juana", String::length);
```
En este caso se puede reemplazar la expresión por la referencia `String::length`.

#### Se diferencian 4 tipos de referencias de métodos:
- Referencia a método estático: `NombreClase::nombreMetodoEstatico`
- Referencia a método de instancia de cierto objeto: `variableObjeto::nombreMetodoInstancia`
- Referencia a método de instancia de cualquier objeto de cierto tipo: `NombreTipo::nombreMetodoInstancia`
- Referencia a un constructor: `NombreClase::new`

### La interfaz `Consumer`
Regresemos al ejemplo donde tenemos una lista de números y queremos aplicar una operación a cada elemento, pero en lugar de esperar un resultado deseamos producir algún **efecto secundario** al **consumirlos**. Ahora podríamos utilizar una expresión lambda junto con la interfaz funcional predefinida `Consumer` para lograrlo de manera concisa:

```java
import java.util.Arrays;
import java.util.List;

public class EjemploExpresionLambda {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);

        // Uso de una expresión lambda con Consumer para imprimir cada número
        numeros.forEach(numero -> System.out.println(numero));
    }
}
```
En este ejemplo, `numero -> System.out.println(numero)` es una expresión lambda que se ajusta a la firma del método `accept` de la interfaz `Consumer`. Este método retorna `void`, por lo cual el objetivo es producir algún efecto o cambio en el programa. Esto lo podemos observar al mirar la implementación del método `forEach` de la interfaz [`Iterable<T>`](https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html):

```java
default void forEach(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    for (T t : this) {
        action.accept(t);
    }
}
```
La ejecución de `action.accept(t)` simplemente realiza alguna acción (consumimos al elemento `t`) donde no nos interesa conocer el resultado para tratarlo dentrodel bucle.

### La interfaz `Supplier`
A diferencia de `Consumer`, donde podemos aplicar una función que no devuelve resultado, en la interfaz `Supplier` existe un único método abstracto `get()` que no recibe argumentos pero sí devuelve un valor de retorno. De esta forma podemos utilizar esta interfaz para **proveer** _perezosamente_ (_lazy_) valores.

```java
import java.util.function.Supplier;

public class EjemploSupplierLambda {
    public static void main(String[] args) {
        // Crea una expresión lambda que actúa como un proveedor de números aleatorios entre 1 y 100
        Supplier<Integer> generadorDeNumero = () -> (int) (Math.random() * 100) + 1;

        // Obtenemos un número generado por el proveedor
        int numeroAleatorio = generadorDeNumero.get();

        System.out.println("Número aleatorio: " + numeroAleatorio);
    }
}
```
Creamos una expresión lambda que no toma argumentos y genera un número aleatorio entre 1 y 100 utilizando `Math.random()`. Luego, llamamos al método `get()` en el proveedor para obtener un número aleatorio y lo imprimimos. La interfaz `Supplier` se utiliza comúnmente para generar valores o realizar cálculos cuando se necesitan, sin necesidad de pasarle argumentos.

### La interfaz Runnable
La interfaz [`Runnable`](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html) es parte del paquete `java.lang` y se utiliza para definir una tarea que puede ejecutarse en un hilo (_thread_) **sin devolver ningún resultado**. La interfaz tiene un único método abstracto llamado `run()`, que no toma argumentos y no devuelve un valor.

> `Runnable` y `Callable` son interfaces que se utilizan para ejecutar tareas en hilos de manera concurrente. Tienen diferencias clave en cuanto a cómo se utilizan y qué pueden devolver. En esta sección no trataremos el tema de **concurrencia**, pero es importante que sepamos que existen estas interfaces y pueden ser útiles cuando simplemente deseamos ejecutar algún comportamiento pasado como argumento.

Supongamos que queremos definir una función de orden superior llamada _ejecutar_ que toma una interfaz funcional como argumento y la ejecuta:

```java
static void ejecutar(Runnable tarea) {
    tarea.run();
}

ejecutar(() -> System.out.println("Este es un mensaje!"));
```

## Implementando comportamientos
Las interfaces funcionales permiten definir contratos para comportamientos sin especificar cómo se deben implementar. Esto permite que diferentes partes del código proporcionen **diferentes implementaciones de una misma interfaz funcional**, lo que resulta en una mayor **flexibilidad**.

Supongamos que tenemos una interfaz funcional llamada `Validador` que verifica si un elemento cumple con ciertos criterios. Podríamos tener múltiples implementaciones de esta interfaz para diferentes validaciones, según sea necesario:

```java
@FunctionalInterface
interface Validador<T> {
    boolean validar(T elemento);
}
```
```java
Validador<Integer> esPositivo = num -> num > 0;
Validador<String> tieneMinCaracteres = str -> str.length() >= 5;
```
Considerando que el validador devuelve un `boolean`, podríamos utilizar este comportamiento análogo para filtrar diversos elementos de un arreglo según diversos criterios. Esto se asemeja a la interfaz funcional `Predicate<T>`.

```java
public static <T> List<T> filtrar(List<T> elementos, Predicate<T> criterio) {
    List<T> filtrados = new ArrayList<>();
    for(T elemento : elementos) {
        if (criterio.test(elemento)) {
            filtrados.add(elemento);
        }
    }
    return filtrados;
}

public static void main(String[] args) {
    List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> filtrados = filtrar(numeros, e -> e > 3);    // [4, 5]
}
```
En este caso utilizamos el método `test` que es el único abstracto que trae `Predicate<T>` para evaluar, dado un argumento de tipo `T`, si se cumple cierto criterio gracias a la devolución de un `boolean`. Nuestro predicado aquí, definido con una expresión lambda, verifica si el elemento es mayor a 3.

Por otra parte, si quisiéramos ordenar una lista de cadenas de texto en función de su longitud, podríamos utilizar una expresión lambda con la interfaz funcional [`Comparator`](../04_igualdad_y_copia/README.md#usando-un-comparador), la cual recordemos tiene un método abstracto `compare`.

```java
List<String> frutas = Arrays.asList("manzana", "pera", "banana", "uva");
Comparator<String> comparaLongStr = (s1, s2) -> Integer.compare(s1.length(), s2.length());
frutas.sort(comparaLongStr);
System.out.println(frutas);    // [uva, pera, banana, manzana]
```
A efectos educativos, declaramos la variable `comparaLongStr` para definir el comparador, pero en general este paso se evita y se coloca directamente la expresión lambda como argumento. Entonces, quedaría simplemente `frutas.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()))`.

### Composición de funciones
Podemos combinar varias interfaces funcionales para crear comportamientos más complejos. Esto nos permite componer lógica de manera modular y reutilizable.

Supongamos que tenemos dos validadores simples y queremos combinarlos en uno solo que verifica que un número sea positivo y par, podríamos hacer algo así:

```java
Validador<Integer> esPositivo = num -> num > 0;
Validador<Integer> esPar = num -> num % 2 == 0;

Validador<Integer> esPositivoYPar = num -> esPositivo.validar(num) && esPar.validar(num);
```
Si analizamos la interfaz predefinida `Predicate<T>`, veremos que ya tiene incorporada esta funcionalidad que nos permite componer funciones a través de métodos `default`. Veamos cómo podemos utilizarla con el ejemplo de las futas y el método filtrar que implementamos previamente:

```java
Predicate<String> minCaracteres = str -> str.length() >= 5;
Predicate<String> terminaEnA = str -> str.endsWith("a");
System.out.println(
    filtrar(frutas, minCaracteres.and(terminaEnA))
); // [banana, manzana]
```
En este ejemplo generamos dos criterios de filtrado a través de predicados. El primero selecciona cadenas con mínimo cinco caracteres, mientras que el segundo selecciona aquellas cadenas que terminan con la letra 'a'. Luego, aplicamos el filtro combinado con el operador lógico `and` que ya viene implementado para combinar predicados como método default de `Predicate<T>`.

> La interfaz `Predicate<T>` incorpora también los métodos default `or` y `negate` que permiten generar un predicado compuesto a partir de otro/s.

## Ejercicio: Filtrado con predicado
Implementar un método `filtrarConPredicado` que reciba una colección de elementos y un predicado, para devolver una nueva colección que contenga sólo aquellos elementos que cumplan la condición del predicado. Probar el método con los siguientes ejemplos:
- Filtrar una colección numérica devolviendo sólo los números pares.
- Filtrar una colección numérica devolviendo sólo los números mayores a 10.
- Filtrar una colección de cadenas devolviendo sólo las cadenas de más de 5 caracteres.
- Filtrar una colección de cualquier tipo devolviendo sólo los elementos mayores a cierto elemento.

## Programación funcional y Streams
Los [Streams](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) son una característica poderosa que proporcionan una forma eficiente y expresiva de trabajar con **colecciones de datos**, como listas o arrays. En esta sección presentaremos una reducida introducción para conocer cómo operarlas.

### ¿Qué es un Stream en Java?
Es una **secuencia de elementos** que se procesan de manera **secuencial o paralela**. Permiten realizar operaciones en colecciones de datos de manera declarativa, lo que significa que podemos describir lo que deseamos hacer con los datos en lugar de especificar cómo hacerlo. Estas operaciones se aplican sobre una colección inicial de forma **encadenada**, su objetivo no es almacenar datos sino **procesarlos**. Dada su naturaleza, tienen un tiempo de vida diferente a otras colecciones en Java, ya que **no pueden ser reutilizadas**. Podríamos imaginarlas como estructuras efímeras.

### Creación de un Stream
Podemos crear un `Stream` de diferentes maneras. Veamos algunos ejemplos.

#### Stream vacía
```java
Stream<String> streamVacia = Stream.empty();
```
#### Desde una colección
Podemos generar a partir de una colección, como una lista o un array, utilizando el método stream():

```java
Collection<String> listaNombres = Arrays.asList("Juana", "Carlos", "Natalia", "Jose");
Stream<String> streamNombres = listaNombres.stream();

String[] arregloNombres = new String[]{"Juana", "Carlos", "Natalia", "Jose"};
Stream<String> streamNombres = Arrays.stream(arregloNombres);
Stream<String> streamNombresParcial = Arrays.stream(arregloNombres, 1, 3);  // [Carlos, Natalia]
```
El método `stream()` permite generar desde una colección o arreglo completo, o se puede seleccionar un rango dado por `[inicio, final)`. En el ejemplo, la selección parcial se da desde el primer elemento incluido hasta el tercero (excluido).

#### Construyendo con `builder`
Otra forma de crear _streams_ es con el método `builder()`, lo cual nos permite incorporar elementos con el método `add` para construirlo.

```java
Stream<Integer> streamConstruido = Stream.<Integer>builder().add(1).add(2).build();
```
Notemos que `builder()` es un método genérico, por lo cual debemos especificar el tipo de dato de los elementos, de lo contrario asumirá que es `Object`.

#### Streams infinitos
Si utilizamos el método `generate()` podemos generar un `Stream` infinito (limitado por la memoria) o hasta cierta cantidad especificada con `limit`.

```java
Stream<String> streamCadenas = Stream.generate(() -> "cadena").limit(10);
```
En este caso generamos un _stream_ de 10 elementos `String` con contenido "cadena".

Otra forma de construir _streams_ infinitos es mediante el método `iterate`:

```java
Stream<Integer> streamPotencias2 = Stream.iterate(2, n -> n * 2).limit(10);
```
Así generamos las primeras diez potencias de dos, desde 2 hasta 1024.

#### Streams con primitivos
Similar al caso de las interfaces funcionales del paquete `java.util.function`, podemos utilizar versiones específicas de `Stream` a partir de sus especializaciones `IntStream`, `LongStream` y `DoubleStream`.

```java
IntStream ints = IntStream.range(0, 3);             // 0, 1, 2
LongStream longs = LongStream.rangeClosed(3, 5);    // 3, 4, 5
DoubleStream doubles = longs.asDoubleStream();      // 3.0, 4.0, 5.0
```
La generación por rango es aplicable para números enteros ya que tiene un _step_ de 1. En el caso de flotantes como `Double`, aquí lo generamos desde otro _stream_. También se pueden usar otras clases para construirlos.

#### Streams paralelizables
Hasta el momento vimos cómo crear _streams_ secuenciales, donde cada operación se aplica secuencialmente a cada elemento del _stream_. Una forma práctica de aprovechar la capacidad **multithreading** de Java es mediante la generación de _streams_ paralelizables. Una vez más, podemos distinguir la creación según si se realiza desde una `Collection` o un arreglo nativo.

```java
Stream<String> parallelStreamNombres = listaNombres.parallelStream();
boolean empiezaConJ = parallelStreamNombres
    .map(String::toLowerCase)
    .anyMatch(nombre -> n.startsWith("j"));

IntStream parallelStreamNros = IntStream.range(1, 1000).parallel();
long paresDesde500 = parallelStreamNros
    .skip(500)
    .filter(n -> n % 2 == 0)
    .count();
System.out.println(empiezaConJ);    // true
System.out.println(paresDesde500);  // 249
```

### Operaciones en Streams
Las operaciones en _Streams_ se pueden dividir en dos categorías: operaciones intermedias y operaciones terminales.

#### Operaciones intermedias
Estas operaciones **no producen un resultado final**, sino que **crean un nuevo Stream** que se puede utilizar en operaciones sucesivas, que se encadenan a continuación. Algunos ejemplos de operaciones intermedias son `filter()`, `map()`, `distinct()`, `sorted()` y `limit()`. Estas operaciones son _perezosas_ (_lazy_), por lo cual no se ejecutan a menos que lo requiera una operación terminal.

#### Operaciones terminales
Estas operaciones **producen un resultado final o un valor concreto**. A partir de la ejecución de este tipo de operación, el _Stream_ **resulta inaccesible**. No podremos continuar usándolo porque estará _cerrado_. Podríamos decir que este tipo de operaciones son una operación de **agregación** sobre el stream. Algunos ejemplos de operaciones terminales son `forEach()`, `collect()`, `reduce()`, `min()`, `max()`, `count()` y `anyMatch()`.

Por ejemplo, si en el caso previo quisiéramos acceder al _stream_ `longs`, obtendríamos la siguiente excepción:
```bash
java.lang.IllegalStateException: stream has already been operated upon or closed
```
Esto sucede porque la operación `asDoubleStream()` es de tipo terminal.

### Pipelines
La definición de una secuencia encadenada de operaciones sobre una colección inicial determina un `Pipeline`. Es recomendable utilizar la sintaxis de **funciones encadenadas** para operar con _streams_, ya que facilita la interpretación del procesamiento. Entonces, comenzando con una colección inicial, aplicamos sucesivas operaciones intermedias que producen _streams_ a los cuales se aplican hasta llegar a una operación terminal.

```java
List<String> nombres = Arrays.asList("Juana", "Carlos", "Natalia", "Jose");

// Filtrar nombres que contienen la letra "a" y convertirlos a mayúsculas
List<String> resultado = nombres.stream()
        .filter(nombre -> nombre.contains("a"))
        .map(String::toUpperCase)
        .collect(Collectors.toList());

System.out.println(resultado);  // [JUANA, CARLOS, NATALIA]
```
En este ejemplo primero filtramos los nombres que contienen la letra "a" y luego los convertimos a mayúsculas. Esto se realiza aplicando las operaciones intermedias `filter` y `map`, para finalmente aplicar la operación terminal `collect` que permite generar la lista definitiva y almacenarla en la variable `resultado`.

#### El orden de operaciones
Desde un punto de vista de rendimiento, es importante comprender que el orden de operaciones es relevante. No es lo mismo aplicar una operación en un _stream_ de cierto tamaño que en otro más reducido. Recordemos que cada operación intermedia produce otro _stream_. Por lo tanto, buscaremos invocar operaciones sobre la menor cantidad de elementos en cuanto sea posible.

```java
List<String> resultado = nombres.stream()
        .map(String::toUpperCase)
        .filter(nombre -> nombre.contains("a"))
        .collect(Collectors.toList());
```
Si hubiéramos implementado el `Pipeline` previo de esta forma, habríamos invocado la operación `toUpperCase` para los 4 elementos de la lista inicial, en lugar de hacerlo sobre la lista filtrada. En este caso no es tan importante porque se redujo en un elemento, pero si tuviésemos una colección mucho más grande u otra operación más costosa podría haber resultado en una diferencia sustancial de rendimiento.

### Reducción
Si bien disponemos de operaciones terminales predefinidas para producir algún resultado deseado del procesamiento sobre los elementos, también podemos generar nuestras propias implementaciones de [reducciones](https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html). Los métodos `reduce()` y `collect()` son útiles para lograrlo.

```java
List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);
int suma = numeros.stream()
        .reduce(0, (parcial, actual) -> parcial + actual);

System.out.println("La suma es: " + suma);  // La suma es: 15
```
En este caso utilizamos la operación `reduce()` para calcular la suma de los números en la lista. El primer parámetro es el **valor inicial** (0) y como segundo parámetro se pasa la **función acumuladora** a evaluar para cada elemento. Esta función tiene dos argumentos, el primero es el valor parcial o acumulado que devolvió la evaluación con los elementos previos, y el segundo argumento es el elemento actual. También podríamos haber usado la operación predefinida `sum()` que ya viene implementada.

La operación `reduce` se utiliza para combinar los elementos de un _Stream_ en un solo valor, ya sea realizando una operación de acumulación (como suma o multiplicación) o aplicando una función binaria a los elementos. Esta operación está sobrecargada con las siguientes firmas:

```java
T reduce(T identity, BinaryOperator<T> accumulator);

<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);

Optional<T> reduce(BinaryOperator<T> accumulator);

```
- **_identity_**: Es el valor inicial o de identidad que se utiliza como punto de partida para la acumulación. Este valor debería ser un valor neutro para la operación que se está realizando. Por ejemplo, si estamos sumando números, el valor inicial podría ser 0. Si estamos multiplicando números, el valor inicial podría ser 1.
- **_accumulator_**: Es una función binaria que toma dos argumentos y los combina en un solo valor. En la primera versión, el valor combinado es del mismo tipo de los elementos `T`, mientras que la segunda firma produce un valor de otro tipo `U` a partir del valor inicial o combinación previa y el siguiente elemento.
- **_combiner_**: Es una función de combinación que definir cómo se combinarán los resultados generados por la acumuladora, por lo cual es una operación binaria que toma dos argumentos de tipo `U` y produce un valor del mismo tipo `U`. Se utiliza para la ejecución paralela para combinar resultados de _threads_.

> Las funciones acumuladoras y combinadoras deben ser **asociativas y puras** (_stateless_), de forma que no alteren el resultado según se agrupen.

Veamos cómo puede utilizarse para convertir una lista de elementos en un valor de otro tipo. Por ejemplo, si tenemos una lista de cadenas y queremos concatenarlas en una sola cadena separada por guión bajo, podemos hacerlo de la siguiente manera:

```java
List<String> palabras = Arrays.asList("Este", "es", "un", "mensaje");

String mensaje = palabras.stream()
                        .reduce("", (a, b) -> a + "_" + b);

System.out.println("Resultado: " + mensaje);  // Resultado: _Este_es_un_mensaje
```
En este caso, el valor inicial es una cadena vacía "", y la función binaria concatena las palabras separándolas por "\_". En esta versión de `reduce` se utiliza como interfaz funcional a `BinaryOperator` que recibe dos argumentos de tipo `T` y devuelve un tipo `T`, en nuestro caso `T` sería `String` porque es el tipo de dato de los elementos de `palabras`. Notemos que el resultado es una cadena que comienza con un "\_", por lo cual no es una solución ideal. Probablemente sería mejor utilizar una operación especializada llamada `Collectors.joining("_")` con la operación terminal `collect`.

> La operación `reduce()` **genera un nuevo valor** como resultado de evaluar la función acumuladora, por lo cual **puede resultar ineficiente** si el tipo de dato de retorno es complejo. Por ejemplo, si la reducción se trata de agregar elementos a una lista, en cada invocación de la acumuladora se genera una nueva lista copia de la previa con el actual elemento agregado. En esos casos conviene utilizar una única lista mutable donde se inserten los elementos, que lo podemos hacer con `collect()`.

#### Uso de reduce con tipos diferentes
Cuando necesitamos devolver un valor con un tipo de datos diferente al de los elementos, debemos usar la versión de `reduce` que acepta 3 parámetros, siendo el primero el valor inicial, el segundo una interfaz `BiFunction` y el tercero la operación del `combiner` que es un `BinaryOperator`. En estos casos debemos especificar esa última operación para saber cómo combinar los resultados de las funciones acumuladoras, de lo contrario el compilador no puede inferir el tipo de dato del elemento.

```java
int caracteres = palabras.stream()
        .reduce(0, (parcial, actual) -> parcial + actual.length(), Integer::sum);
System.out.println("Resultado: " + caracteres);  // Resultado: 15
```
En este ejemplo estamos contando los caracteres de la lista previa, comenzando por un valor inicial 0 y generando la suma parcial en la función acumuladora.

#### Terminando con `collect()`
Otra forma de reducir un _Stream_ es a través de la operación `collect()`. Recordemos que `reduce()` genera un nuevo valor al procesar cada elemento, sin embargo `collect()` funciona modificando un único valor de salida en cada procesamiento. Tiene dos implementaciones sobrecargadas.

```java
<R> R collect(Supplier<R> supplier,
              BiConsumer<R, ? super T> accumulator,
              BiConsumer<R, R> combiner);
```
- **_supplier_**: Función generadora de nuevas instancias, la responsable de crear el contenedor de retorno de tipo `R`.
- **_accumulator_**: Función acumuladora que incorpora el elemento actual (segundo parámetro de tipo `T`) al contenedor de retorno (primer parámetro de tipo `R`).
- **_combiner_**: Función que recibe dos contenedores de retorno y los combina en uno solo. De utilidad para paralelizar.

> Al igual que en el caso de `reduce()`, las funciones acumuladoras y combinadoras deben ser **asociativas y puras** (_stateless_).

```java
<R, A> R collect(Collector<? super T, A, R> collector);
```
Esta versión de `collect()` recibe un único argumento de tipo [`Collector`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collector.html), una interfaz que encapsula las tres funciones que recibe la otra versión de `collect()`. En la clase [Collectors](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html) vienen predefinidas varias operaciones de reducción mutables para utilizar con este método.

```java
List<String> palabras = Arrays.asList("Este", "es", "un", "mensaje");

String mensaje = palabras.stream().collect(Collectors.joining("_"));

System.out.println("Resultado: " + mensaje);    // Resultado: Este_es_un_mensaje
```
Revisando el ejemplo previo que realizamos con `reduce()`, esta vez lo resolvemos con `collect()` y la operación predefinida `Collectors.joining`. A su vez, también podríamos reemplazar la reducción de la suma de caracteres así:

```java
int caracteres = palabras.stream().collect(Collectors.summingInt(String::length));
System.out.println("Resultado: " + caracteres);  // Resultado: 15
```
Utilizando la reducción `Collectors.summingInt`, donde le pasamos la función a invocar para cada elemento `String::length`, podemos lograr el mismo resultado utilizando `collect()`.
