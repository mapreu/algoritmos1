# Colecciones en Java
Las colecciones son estructuras de datos que permiten almacenar, recuperar y manipular conjuntos de elementos de manera eficiente. Estas estructuras son muy útiles para una amplia gama de aplicaciones y proporcionan una forma flexible y dinámica de gestionar datos.

Java ofrece una biblioteca de colecciones muy completa que se encuentra en el paquete _java.util_. Veamos la jerarquía de **interfaces** provistas.

![Colecciones de Java](./images/colls-coreInterfaces.gif)

- [**Collection**](https://docs.oracle.com/javase/tutorial/collections/interfaces/collection.html): La interfaz base que representa simplemente un grupo de elementos.
- [**Set**](https://docs.oracle.com/javase/tutorial/collections/interfaces/set.html): Colección que no permite elementos duplicados y no garantiza un orden específico de elementos.
    - [SortedSet](https://docs.oracle.com/javase/tutorial/collections/interfaces/sorted-set.html): Versión de _Set_ que mantiene a los elementos con orden ascendente.
- [**List**](https://docs.oracle.com/javase/tutorial/collections/interfaces/list.html): Colección lineal ordenada (secuencia) que permite elementos duplicados. Podemos acceder a los elementos de una lista por su posición (índice).
- [**Queue**](https://docs.oracle.com/javase/tutorial/collections/interfaces/queue.html): La cola es una colección lineal que sigue el principio de "primero en entrar, primero en salir" (_FIFO_). Un caso especial es la _priority queue_ que ordena los elementos con un comparador específico.
- [**Deque**](https://docs.oracle.com/javase/tutorial/collections/interfaces/deque.html): Una colección lineal que permite insertar y remover tanto desde el principio como el final (_double-ended-queue_), por lo cual puede utilizarse como una cola (_FIFO_) o una pila (_LIFO_).
- [**Map**](https://docs.oracle.com/javase/tutorial/collections/interfaces/map.html): Estructura (diccionario) que almacena pares clave-valor y no permite claves duplicadas.
    - [SortedMap](https://docs.oracle.com/javase/tutorial/collections/interfaces/sorted-map.html): Versión de _Map_ que mantiene las claves con orden ascendente.

Java también proporciona varias **clases concretas** implementan las interfaces mencionadas anteriormente y proveen diversas implementaciones, como _ArrayList_, _LinkedList_, _HashSet_, _TreeSet_, _HashMap_, _TreeMap_, entre otras.

> **Tipos Genéricos**: Las colecciones en Java pueden ser parametrizadas con tipos genéricos, lo que permite especificar el tipo de elementos que la colección contendrá. Esto aumenta la **seguridad** y la **legibilidad** del código, también otorgando **polimorfismo paramétrico**.

## Usando colecciones
### Declaración e inicialización
Para utilizar una colección en Java, primero debemos importarla del paquete java.util. Luego, podremos declarar e inicializar una colección, por ejemplo:

```java
List<String> miLista = new ArrayList<>();
Set<Integer> miConjunto = new HashSet<>();
Map<String, Integer> miMapa = new HashMap<>();
```
Siempre recordemos que es preferible declarar nuestras variables con el tipo de dato más abstracto posible en la jerarquía. Esto nos ofrecerá mayor flexibilidad y favorece al principio de **inversión de dependencias**, porque evitamos depender de clases concretas de las colecciones. Luego, necesariamente deberemos instanciar un objeto de una clase concreta para asignarlo a nuestras variables declaradas con el tipo de las interfaces.

> - **Colecciones Sincronizadas**: Java proporciona versiones sincronizadas de algunas colecciones, que son seguras para su uso en entornos _multithreading_. Por ejemplo, [Collections._synchronizedList_](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedList-java.util.List-) y [Collections._synchronizedMap_](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#synchronizedMap-java.util.Map-).
> - **Colecciones No Modificables**: También podemos crear colecciones no modificables utilizando los métodos de ayuda de [Collections](https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html)._unmodifiableXXX_.

### Operaciones comunes
Las colecciones en Java proporcionan una amplia variedad de métodos para realizar operaciones comunes, como agregar elementos, eliminar elementos, buscar elementos, obtener el tamaño de la colección, entre otras. Esto nos permite aprovechar el **polimorfismo** a través del uso de las interfaces.

#### List (Lista)
| Método | Descripción |
|--------|-------------|
| `add(E elemento)` | Agrega un elemento al final de la lista. |
| `get(int índice)` | Obtiene el elemento en el índice especificado. |
| `size()` | Devuelve el número de elementos en la lista. |
| `remove(int índice)` | Elimina el elemento en el índice especificado. |
| `contains(Object objeto)` | Verifica si la lista contiene un objeto específico. |
| `clear()` | Elimina todos los elementos de la lista. |

#### Set (Conjunto)
| Método | Descripción |
|--------|-------------|
| `add(E elemento)` | Agrega un elemento al conjunto. |
| `contains(Object objeto)` | Verifica si el conjunto contiene un objeto específico. |
| `size()` | Devuelve el número de elementos en el conjunto. |
| `remove(Object objeto)` | Elimina un objeto específico del conjunto. |
| `clear()` | Elimina todos los elementos del conjunto. |
| `isEmpty()` | Verifica si el conjunto está vacío. |

#### Map (Mapa)
| Método | Descripción |
|--------|-------------|
| `put(K clave, V valor)` | Asocia una clave con un valor en el mapa. |
| `get(Object clave)` | Obtiene el valor asociado con la clave especificada. |
| `containsKey(Object clave)` | Verifica si el mapa contiene una clave específica. |
| `containsValue(Object valor)` | Verifica si el mapa contiene un valor específico. |
| `size()` | Devuelve el número de pares clave-valor en el mapa. |
| `remove(Object clave)` | Elimina la entrada asociada con la clave especificada. |

#### Queue (Cola)
| Método | Descripción |
|--------|-------------|
| `offer(E elemento)` | Agrega un elemento a la cola. |
| `poll()` | Remueve y devuelve el elemento en el frente de la cola. |
| `peek()` | Devuelve el elemento en el frente de la cola sin eliminarlo. |
| `size()` | Devuelve el número de elementos en la cola. |
| `isEmpty()` | Verifica si la cola está vacía. |
| `clear()` | Elimina todos los elementos de la cola. |

#### Deque (Doble Cola)
| Método | Descripción |
|--------|-------------|
| `addFirst(E elemento)` | Agrega un elemento al principio de la doble cola. |
| `addLast(E elemento)` | Agrega un elemento al final de la doble cola. |
| `removeFirst()` | Remueve y devuelve el primer elemento de la doble cola. |
| `removeLast()` | Remueve y devuelve el último elemento de la doble cola. |
| `getFirst()` | Devuelve el primer elemento de la doble cola sin eliminarlo. |
| `getLast()` | Devuelve el último elemento de la doble cola sin eliminarlo. |

### Orden y Duplicados
Diferentes tipos de colecciones tienen diferentes propiedades en cuanto a orden y duplicados. Veamos algunos ejemplos de **clases concretas** que podemos utilizar.

| Tipo de Colección | Descripción | Orden de Elementos | Duplicados Permitidos |
|--------------------|-------------|--------------------|-----------------------|
| [`ArrayList`](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)       | Implementación de `List`. Almacena elementos en un arreglo dinámico.  | Mantiene el orden de inserción.     | Permite duplicados. |
| [`LinkedList`](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html)      | Implementación de `List`. Almacena elementos en una lista doblemente enlazada. | Mantiene el orden de inserción.     | Permite duplicados. |
| [`HashSet`](https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html)         | Implementación de `Set`. Almacena elementos en una tabla hash. | No garantiza un orden específico.    | No permite duplicados. |
| [`LinkedHashSet`](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashSet.html)   | Implementación de `Set`. Almacena elementos en una tabla hash con acceso ordenado. | Mantiene el orden de inserción.     | No permite duplicados. |
| [`TreeSet`](https://docs.oracle.com/javase/8/docs/api/java/util/TreeSet.html)         | Implementación de `SortedSet`. Almacena elementos en un árbol balanceado. | Ordena los elementos en orden natural o definido por el comparador. | No permite duplicados. |
| [`HashMap`](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)         | Implementación de `Map`. Almacena pares clave-valor en una tabla hash. | No garantiza un orden específico de las claves. | No permite claves duplicadas; los valores pueden ser duplicados. |
| [`LinkedHashMap`](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html)   | Implementación de `Map`. Almacena pares clave-valor en una tabla hash con acceso ordenado. | Mantiene el orden de inserción de las claves. | No permite claves duplicadas; los valores pueden ser duplicados. |
| [`TreeMap`](https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html)         | Implementación de `SortedMap`. Almacena pares clave-valor en un árbol balanceado. | Ordena las claves en orden natural o definido por el comparador. | No permite claves duplicadas; los valores pueden ser duplicados. |
| [`PriorityQueue`](https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html)   | Implementación de `Queue`. Almacena elementos en una cola de prioridad basada en un árbol _heap_. | Ordena los elementos en orden natural o definido por el comparador. | Permite duplicados. |

## Ejemplos

### List
Veamos un ejemplo de cómo utilizar _ArrayList_, una implementación muy común de la interfaz _List_, ya que usualmente es la opción más eficiente.

```java
import java.util.ArrayList;
import java.util.List;

public class EjemploLista {
    public static void main(String[] args) {
        // Crear una lista de cadenas
        List<String> listaCadenas = new ArrayList<>();

        // Agregar elementos a la lista
        listaCadenas.add("Manzana");
        listaCadenas.add("Banana");
        listaCadenas.add("Cereza");

        // Acceder a elementos por índice
        String primeraFruta = listaCadenas.get(0);
        System.out.println("Primera fruta: " + primeraFruta);

        // Iterar a través de la lista
        for (String fruta : listaCadenas) {
            System.out.println(fruta);
        }
    }
}
```

### Set
Veamos un ejemplo de cómo utilizar _HashSet_, una implementación común de la interfaz _Set_, que también suele ser la opción más eficiente.

```java
import java.util.HashSet;
import java.util.Set;

public class EjemploConjunto {
    public static void main(String[] args) {
        // Crear un conjunto de números enteros
        Set<Integer> conjuntoNumeros = new HashSet<>();

        // Agregar elementos al conjunto
        conjuntoNumeros.add(10);
        conjuntoNumeros.add(20);
        conjuntoNumeros.add(10); // Intento de duplicado, se ignora

        // Iterar a través del conjunto (sin garantía de orden)
        for (int numero : conjuntoNumeros) {
            System.out.println(numero);
        }
    }
}
```

### Map
Veamos un ejemplo de cómo utilizar _HashMap_, una implementación de la interfaz _Map_ que admite claves y valores con _null_:

```java
import java.util.HashMap;
import java.util.Map;

public class EjemploMap {
    public static void main(String[] args) {
        // Crear un mapa de nombres de frutas y sus cantidades
        Map<String, Integer> inventarioFrutas = new HashMap<>();

        // Agregar elementos al mapa
        inventarioFrutas.put("Manzana", 50);
        inventarioFrutas.put("Banana", 30);
        inventarioFrutas.put("Cereza", 25);
        inventarioFrutas.put("Cereza", 15); // Reemplaza el valor previo de Cereza

        // Obtener el valor asociado a una clave
        int cantidadManzanas = inventarioFrutas.get("Manzana");
        System.out.println("Cantidad de manzanas: " + cantidadManzanas);

        // Iterar a través del mapa con entrySet
        for (Map.Entry<String, Integer> entrada : inventarioFrutas.entrySet()) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue() + " unidades");
        }

        // Iterar a través del mapa con keySet
        for (String clave : inventarioFrutas.keySet()) {
            System.out.println(clave + ": " + inventarioFrutas.get(clave) + " unidades");
        }

        // Iterar a través del mapa con values
        int total = 0;
        for (Integer valor : inventarioFrutas.values()) {
            total += valor;
        }
        System.out.println("Frutas totales: " + total);
    }
}
```

### Queue
Veamos un ejemplo de cómo utilizar _LinkedList_ como una implementación de _Queue_.

```java
import java.util.LinkedList;
import java.util.Queue;

public class EjemploCola {
    public static void main(String[] args) {
        // Crear una cola de números enteros
        Queue<Integer> colaNumeros = new LinkedList<>();

        // Agregar elementos a la cola (al final de la linkedlist)
        colaNumeros.offer(10);
        colaNumeros.offer(20);
        colaNumeros.offer(30);

        // Acceder y eliminar elementos de la cola
        int primerNumero = colaNumeros.poll(); // Retira y devuelve el primer elemento
        System.out.println("Primer número en la cola: " + primerNumero);

        // Iterar a través de la cola
        for (int numero : colaNumeros) {
            System.out.println(numero);
        }
    }
}
```

## Ejercicio: La Escuela
En este ejercicio crearemos un programa para gestionar información de estudiantes en una escuela.
1. Crear una clase _Materia_ con las propiedades: _nombre_ (String), _código_ (int), _estado_ (boolean).
2. Implementar los métodos _equals_ y _hashCode_ de _Materia_ para definir la igualdad de esos objetos a través del código de materia.
3. Crear una clase llamada _HistoriaAcademica_ que tenga un _Map_ que relacione a una materia con un conjunto de calificaciones (Set de Double). Implementar también:
    - Una función que permita calcular el promedio de calificaciones. 
    - Una función que devuelva la cantidad de aplazos (calificación < 2).
    - Una función que devuelva la cantidad de materias cursadas.
4. Crear una clase _Estudiante_ con las siguientes propiedades: _nombre_ (String), _matrícula_ (int), _edad_ (int) y su historia académica (_HistoriaAcademica_).
5. Implementar los métodos _equals_ y _hashCode_ de _Estudiante_ para definir la igualdad de esos objetos a través de la matrícula.
6. Crear una clase llamada _Escuela_ que contendrá:
    - Una lista de estudiantes.
    - Un conjunto para almacenar las materias que se ofrecen en la escuela.
7. En el método main:
    - Crear un objeto _Escuela_, agregar algunos estudiantes a la escuela y algunas materias al conjunto de materias ofrecidas (algunas pueden estar con estado _cerrada_).
    - Ingresar calificaciones de los estudiantes en distintas materias. Validar que las materias existan en la escuela (deben ser objetos del conjunto de materias ofrecidas). Cada ingreso implica que se agrega la calificación al conjunto de calificaciones asociado a la materia en la historia académica del estudiante.
    - Mostrar para cada estudiante:
        - Listado de materias cursadas con su calificación
        - Promedio de calificaciones
        - Cantidad de aplazos
        - Cantidad de materias cursadas