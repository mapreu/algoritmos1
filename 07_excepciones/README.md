# Excepciones
En esta sección exploraremos qué son las excepciones, cómo manejarlas y por qué son importantes en la programación orientada a objetos. 

## ¿Qué son las excepciones?
Una excepción es un **evento anormal** que ocurre durante la **ejecución** de un programa y puede interrumpir el flujo normal de ejecución. Estos eventos pueden ser errores lógicos, como la división por cero, o situaciones imprevistas, como el error de lecura de un archivo que el programa intenta abrir.

En Java las excepciones se representan mediante **objetos** de clases que heredan de la clase **Throwable**. Estos objetos encapsulan información sobre el error, como el tipo de excepción, el mensaje de error y la pila de llamadas que muestra dónde ocurrió el error.

### Clasificación de excepciones
Las excepciones son clases, por ende tipos de datos, y también se relacionan a través de la herencia. Esto nos ofrece **comportamiento polimórfico** en los objetos de excepciones, algo muy útil al momento de definir _handlers_ de excepciones. Veamos algunas excepciones predefinidas en Java:

 ![Jerarquía de excepciones en Java](./images/jerarquia_excepciones.jpg)

En Java existen dos **tipos de excepciones**: las excepciones **verificadas** (_checked exceptions_) y las excepciones **no verificadas** (_unchecked exceptions_).

- **Checked Exceptions**: Son excepciones que el **compilador requiere que se manejen explícitamente** en el código. Por ejemplo, _IOException_ o _SQLException_. Deben ser capturadas o propagadas usando un bloque _try-catch_, o declarando que el método las lanza con la palabra clave **throws** al final de la firma.

    > Diseñaremos o usaremos _checked exceptions_ (subtipos de _Exception_) cuando esperamos que **el programa se recupere** razonablemente ante ese tipo de evento anómalo.

- **Unchecked exceptions**: Son excepciones que **no se requieren manejar de manera explícita** y pueden ocurrir en cualquier momento durante la ejecución del programa. **No deberíamos capturarlas** sino permitir que se propaguen hasta detener la ejecución para informar el problema.
    - **Error exceptions**: Son excepciones que heredan de la clase _Error_ que indican eventos excepcionales **externos a la aplicación**, por lo cual no pueden ser anticipados para recuperar así una normal ejecución. Suelen estar asociados a **errores de hardware**, por ejemplo, _IOError_. Por convención se reservan para uso de la JVM y **no es recomendable definir excepciones personalizadas de este tipo**.
    - **Runtime exceptions**: Son excepciones que heredan de la clase _RuntimeException_ que indican eventos excepcionales **internos en la aplicación**. Suelen indicar **errores de programación** (_bugs_), en el mal uso o consumo de una API, generalmente violando precondiciones o ignorando validaciones. Ejemplos comunes incluyen _NullPointerException_ o _ArrayIndexOutOfBoundsException_.

    > Diseñaremos o usaremos _unchecked exceptions_ (subtipos de _RuntimeException_) cuando esperamos que **el programa no se recupere** ante ese tipo de evento anómalo, por lo cual **no deberían ser capturadas** porque podrían dejar al programa en un estado inconsistente.

### Ejercicio: Clasificando excepciones
Dados los siguientes casos de error, determinar si corresponde modelar la excepción de tipo _checked_ o _unchecked_.
- División de un número por 0.
- Formato de número de teléfono incorrecto.
- No se puede abrir el archivo solicitado.
- No existe el archivo a acceder.
- Operación aritmética no soportada en cierto tipo de dato.
- Se superó el límite de capacidad de la estructura.
- La configuración externa de la aplicación es incorrecta.
- Falló la conexión a la base de datos.
- Se convierte una referencia a un subtipo (_downcasting_) que no corresponde.

## Lanzamiento de excepciones
El mecanismo con el cual determinamos cuándo se produce un evento anormal se denomina **lanzamiento** de una exepción. Cuando se produce un error que deseamos modelar con este mecanismo de excepciones, se genera un objeto de tipo _Throwable_ (_exception object_) que contiene información del error específico y se lo entregamos al entorno de ejecución para que sea capturado (o no) por algún método previo en la pila de ejecución. 

Veamos este proceso en los siguientes pasos:

**1. Ocurrencia de un Evento Anormal**

Un evento anormal, como un acceso a un índice fuera de rango en un arreglo o la apertura de un archivo que no existe, ocurre durante la ejecución del programa en cierto método.

**2. Creación de un Objeto de Excepción**

Cuando ocurre el evento anormal se crea un objeto de excepción que encapsula información sobre el error. Como mencionamos, este objeto pertenece a una clase que hereda de la clase base _Throwable_, aunque seguramente sea de alguna especialización de ésta. Por ejemplo, si ocurre una división por cero, se crea un objeto _ArithmeticException_ para describir lo mejor posible el evento ocurrido.

**3. Lanzamiento de la Excepción**

Una vez que se crea el objeto de excepción, se _lanza_ al flujo de ejecución. Esto se hace utilizando la palabra clave _throw_: 

`throw algunObjetoThrowable;`

Por ejemplo, instanciamos un objeto de _ArithmeticException_ y lo lanzamos así:

```java
throw new ArithmeticException("División por cero");
```

## Manejo de excepciones
Una excepción _lanzada_ puede ser **capturada** o no por los métodos que se encuentran en la pila de ejecución. Por ejemplo, si un _método1_ invoca a un _método2_ y este último _lanza_ una excepción de tipo _IndexOutOfBoundsException_, el método1 podría intentar **capturarla** (_catch_) para **manejarla** y así evitar que se interrumpa la ejecución del programa. Si ningún método en la pila de ejecución captura la excepción, entonces **el programa se detiene** de forma imprevista y presenta el error asociado.

Continuando el proceso previo donde se lanzó una excepción, veamos los pasos que siguen:

**4. Búsqueda de un Manejador de Excepción**

El entorno de ejecución comienza a buscar un bloque **try-catch** adecuado para **manejar** (_handle_) la excepción. Comienza en el punto donde se lanzó la excepción y busca en la pila de ejecución en orden reverso algún **manejador** (_handler_) que pueda manejar el tipo de excepción lanzado. Esto se conoce como **desapilamiento de la pila de ejecución** (_unwinding the call stack_).

Si se encuentra un _handler_ adecuado en la pila de ejecución, el flujo de control se desplaza al **bloque catch correspondiente**. Recordemos que ese bloque catch debe tratar el **tipo de excepción lanzada**, de lo contrario no puede capturarla.

![Propagación de excepción en la pila](./images/exceptions-errorOccurs.gif)

**5. Manejo de la Excepción o Propagación**

Dentro del **bloque catch** que corresponde se puede **manejar la excepción** de manera adecuada. Esto puede incluir la impresión de un mensaje de error, la recuperación de datos o la toma de decisiones basadas en la excepción. Después de manejar la excepción, el programa puede continuar su ejecución normalmente **a partir del punto donde se manejó la excepción**.

Si no se maneja la excepción en el lugar donde se lanzó, la excepción **se propaga hacia atrás en la pila** en busca de un manejador adecuado. Si no se encuentra un manejador en ninguna parte de la pila, **el programa se detendrá** y mostrará un mensaje de error.

> También es posible que en el bloque _catch_ donde se maneja una excepción de cierto tipo, luego se lance otra excepción de mismo tipo u otro, lo que se denomina **chained exceptions**.

### El requerimiento Capturar o Especificar
Java provee un mecanismo que permite validar en **tiempo de compilación** si nuestro manejo de excepciones es correcto. Para ello establece un acuerdo que denomina **_Catch or Specify Requirement_**. Si no cumplimos este acuerdo, **no podremos compilar**.

Todo código que pueda lanzar una **_checked exception_** debe estar abarcado por alguno de los siguientes:
- Un bloque **try-catch** que maneje ese tipo de excepción.
- Un método que **especifique** que puede lanzar ese tipo de excepción (_throws_ en la firma).

### El bloque try-catch
Java proporciona bloques _try-catch_ para **manejar excepciones**. El bloque **_try_** abarca el **código que puede generar alguna excepción**. Seguido del bloque _try_ se definen **uno o más bloques _catch_** que actúan como manejadores o **_handlers_** de cierto tipo de excepción y tienen dentro el código a ejecutar en cada caso.

```java
try {
    // Código que puede generar una excepción
    int resultado = 10 / 0;     // Esto generará una ArithmeticException
} catch (ArithmeticException e) {
    // Manejo de la excepción
    System.out.println("Error: División por cero. " + e.getMessage());
}
```
En este ejemplo, el código dentro del bloque _try_ puede generar una excepción. Si ocurre una excepción, el flujo de control se desplaza al primer bloque _catch_ (en este caso hay uno solo que maneja el tipo de excepción _ArithmeticException_), donde se puede manejar la excepción de manera adecuada, como mostrar un mensaje de error. 

> El objeto de excepción (en el ejemplo, _e_) contiene métodos heredados que ofrecen información del error. Podríamos sobreescribirlos o crear nuevos con nuestras excepciones propias.

#### Múltiples manejadores
Si el tipo de excepción lanzada dentro del _try_ no coincide con el tipo de excepción establecido en el _catch_, se avanza al próximo bloque _catch_. Si ningún _catch_ puede manejar ese tipo de excepción, se propaga la excepción hacia atrás en la pila de ejecución.

```java
try {
    // Código que puede generar una excepción
} catch (NumberFormatException | IndexOutOfBoundsException  e) {
    // Manejo de la excepción NumberFormatException o IndexOutOfBoundsException
} catch (IllegalArgumentException e) {
    // Manejo de la excepción IllegalArgumentException
} catch (RuntimeException e) {
    // Manejo de la excepción RuntimeException
} catch (Exception e) {
    // Manejo de la excepción Exception
}
```
En este caso definimos cuatro _handlers_ que tratan diferentes excepciones. Notemos que el primero trata excepciones de tipo _NumberFormatException_ o _IndexOutOfBoundsException_. El segundo trata excepciones de tipo _IllegalArgumentException_, la cual es superclase de _NumberFormatException_. Es importante tener esto presente porque si invertimos el orden de los _catch_ no vamos a poder tratar la excepción _NumberFormatException_ específicamente porque siempre la capturaría el _handler_ más general de _IllegalArgumentException_. Por lo cual, **siempre definimos primero las más especializadas** y luego las más generales. Los dos _handlers_ finales tratan excepciones bastante abstractas, lo cual **no está recomendado**, pero se muestra para reforzar el concepto de captura según orden de herencia.

### El bloque finally
Además del bloque _try-catch_, Java también proporciona el bloque _**finally**_. El código dentro del bloque _finally_ **se ejecuta siempre**, independientemente de si se produce una excepción o no. Esto es útil para realizar tareas de limpieza, como cerrar archivos o conexiones de bases de datos, asegurando que se realicen incluso en caso de una excepción.

```java
try {
    // Código que puede generar una excepción
} catch (IOException e) {
    // Manejo de la excepción
} finally {
    // Código que se ejecutará siempre
}
```

### El bloque try-with-resources
En versiones previas de Java se utilizaba el bloque _finally_ para cerrar recursos que se abrían dentro del bloque _try_. A partir de Java 7 aparece el bloque **_try-with-resources_** que encapsula este comportamiento y es recomendable para tratar estos casos. En este bloque **declaramos uno o más recursos** que serán utilizados (archivos, conexiones, etc).

> Los recursos deben ser objetos de tipo [**_AutoCloseable_**](https://docs.oracle.com/javase/8/docs/api/java/lang/AutoCloseable.html), separados por **_;_** si es más de uno.

```java
try (BufferedReader br = new BufferedReader(new FileReader("/tmp/miarchivo.txt"))) {
	String linea;
	while ((linea = br.readLine()) != null) {
		System.out.println(linea);
	}
}
catch (IOException e) {
	// Manejo de la excepción ante error de lectura
}
```
En este ejemplo, tanto _BufferedReader_ como _FileReader_ son recursos que implementan _AutoCloseable_, por lo cual contienen un método _close()_ que se invoca implícitamente siempre al finalizar el bloque _try_ o si ocurre cualquier tipo de excepción. Esto nos permite **evitar definir el bloque _finally_** para cerrar estos recursos.

> Si en el bloque _try_ se lanza una excepción y también se lanza una en los recursos (por ejemplo al tratar de cerrarlos), la primera _suprime_ a la segunda y es la que se propaga. Podemos acceder a las excepciones **_suprimidas_** con el método **_getSuppressed_** del objeto de la excepción lanzada.

### Especificación de excepciones
En algunos casos es mejor **no manejar una excepción en un método**, sino **propagarla hacia arriba** en la jerarquía de invocaciones almacenada en la pila de ejecución. Para respetar el acuerdo [_Catch or Specify Requirement_](#el-requerimiento-capturar-o-especificar), si la excepción es _checked_, debemos agregar la palabra clave **throws** en la firma del método para indicar que el método puede arrojar ese tipo de excepción.

```java
public void miMetodo() throws MiAppException {
    // Código que puede generar MiAppException
}
```
Supongamos que mi _MiAppException_ hereda directamente de _Exception_, entonces es de tipo _checked_ y estamos obligados por el compilador en especificarla en la firma del método. Esto facilita documentar la API para que quien lo consume sabe que debe manejar o propagar ese tipo de excepción en código que abarque la invocación de _miMetodo_.

> También podríamos **especificar excepciones _unchecked_**, aunque **no es una buena práctica**. Sí es buena práctica documentarlas, por ejemplo utilizando el tag **_@throws_** de [Javadoc](https://docs.oracle.com/en/java/javase/20/docs/specs/javadoc/doc-comment-spec.html).

## Definiendo excepciones personalizadas
Si bien en Java tenemos una gran cantidad de excepciones predefinidas en las distintas librerías, también podemos construir nuestros propios paquetes de excepciones y aprovechar la jerarquía de herencia para facilitar el manejo de errores en nuestras aplicaciones.

Para diseñar una excepción personalizada simplemente lo hacemos al igual que una **clase**, pero contemplando lo siguiente al momento de definir su clase base:
- Si es **_unchecked_**, debe ser subtipo de **_RuntimeException_**.
- Si es **_checked_**, debe ser subtipo de **_Exception_**, pero no de **_RuntimeException_**.

```java
public class MiAppException extends Exception {};
public class MiCheckedException extends MiAppException {};
public class MiAppUnCheckedException extends RuntimeException {};
```
En este caso, la primera excepción sería la base para todas las excepciones _checked_ de la aplicación, que se extiende con la segunda excepción para algún error más particular. La tercera sirve de base para todas las excepciones _unchecked_ de la aplicación. También podríamos incorporarlas todas a un paquete de excepciones para un mejor orden.

### ¿Necesitamos excepciones personalizadas?
Definir nuestras propias excepciones es una opción interesante para modelar errores específicos de nuestra aplicación, pero recordemos que Java ya ofrece varias excepciones que seguramente sean aplicables a errores comunes. Es recomendable entonces utilizar las excepciones predefinidas para tratar estos tipos de problemas, usualmente los que se producen por **errores de programación**, porque facilitan la interpretación de quienes consuman nuestro código ya que son excepciones conocidas.

Algunas excepciones comunes que podemos utilizar son:
| Excepción | Cuándo usarla |
| --- | --- |
| **_NullPointerException_** | Parámetro es _null_ cuando no está permitido |
| **_IllegalArgumentException_** | Parámetro no _null_ inválido |
| **_IllegalStateException_** | Estado del objeto inválido para invocar algún método |
| **_IndexOutOfBoundsException_** | El valor del índice está fuera de rango permitido |
| **_UnsupportedOperationException_** | El objeto no soporta ese método |

> Siempre **evitar** lanzar excepciones directamente de _Exception_, _RuntimeException_ o _Error_, porque son demasiado abstractas y si quisiéramos capturarlas sería difícil comprender el error específico.

### El mensaje
Si analizamos la definición de la clase [_Exception_](https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html) veremos que tiene varios constructores. Uno que resulta de utilidad es el que recibe un _String_ como argumento, lo cual es práctico para agregar información del error ocurrido. **Es una buena práctica definir un mensaje con información relevante cuando construimos nuestra excepción**.

```java
public class MiAppException extends Exception {
    public MiAppException() {
        super("Error en MiApp");
    }

    public MiAppException(String mensaje) {
        super(mensaje);
    }
}
```
Ahora podemos generar objetos de _MiAppException_ con dos constructores, uno incorporando un mensaje que puede definirse en el momento que se lanza (segundo constructor). En ambos casos, se construye mediante el constructor de la clase base _Exception_ que recibe un _String_ y lo guarda como mensaje de la excepción a través de la invocación también del constructor de _Throwable_.

Así como _Throwable_ tiene un atributo donde almacena el mensaje de error de la excepción, nosotros podríamos también construir excepciones personalizadas otros atributos para guardar información relevante del error para eventualmente accederla de forma programática.

### Ejemplo con excepciones encadenadas
Veamos un ejemplo de lanzamiento y captura de una excepción personalizada:

```java
public class UrlInvalidaException extends Exception {
    public UrlInvalidaException() {
        super("Formato de URL invalido");
    }
}

public class ErrorConexionException extends Exception {
    public ErrorConexionException() {
        super("Error de conexion");
    }
}

public class MiClienteHTTP {
    public static HTTPReq crearHttpRequest(String url) throws UrlInvalidaException {
        // En algún lado se valida la URL y se podría lanzar...
        throw new UrlInvalidaException();
    }

    public void descargar(String url) throws ErrorConexionException {
        try {
            crearHttpRequest(url);
            ...  // El resto del código no se ejecuta si ocurre UrlInvalidaException
        } catch (UrlInvalidaException e) {       // handler de UrlInvalidaException
            // Guarda error de UrlInvalidaException
            System.err.println(e.printStackTrace());
            // Lanza otra excepcion
            throw new ErrorConexionException();
        }
    }

    public void descargarTodos(String[] urls) throws ErrorConexionException {
        for (String url: urls) {
            descargar(url);
        }
    }
}
```
Definimos dos excepciones propias _UrlInvalidaException_ y _ErrorConexionException_ que heredan de _Exception_, por lo cual son _checked_. Por esa razón, los métodos donde se puede lanzar una excepción de ese tipo y no se capturan deben declarar explícitamente en su firma el _throws_ ([_Catch or Specify Requirement_](#el-requerimiento-capturar-o-especificar)). En _crearHttpRequest_ se lanza una excepción si la url pasada por parámetro es inválida. En _descargar_ se invoca a ese método, por lo cual se captura la exepción con un _handler_ apropiado. En la captura se guarda un log la información del error con _printStackTrace_ y se lanza una nueva excepción más genérica _ErrorConexionException_ (_chained exception_). Dado que el método _descargarTodos_ invoca al _descargar_ y en ningún momento intenta capturar _ErrorConexionException_, la declara en su firma con _throws_. Entonces, si alguien consume los métodos _descargar_ o _descargarTodos_ debemos intentar capturar esa excepción o declararla con _throws_ en el método donde los consumimos para poder compilar.

## El objeto de excepción
Mencionamos que los objetos de excepción siempre heredan de [_Throwable_](https://docs.oracle.com/javase/8/docs/api/java/lang/Throwable.html). Esta clase tiene definidos campos y métodos que nos permiten acceder a información del evento ocurrido. Por ejemplo, el mensaje de la excepción o las excepciones suprimidas, accedidos mediante _getMessage()_ y _getSuppressed()_, respecticamente. También almacena la **causa** de la excepción, que puede accederse mediante el método _getCause()_ que devuelve otro objeto de tipo _Throwable_ (o _null_ si no fue causada por otra excepción).

### El _stack trace_
Probablemente la información más importante que tiene una excepción es el **volcado de la pila de ejecución**, conocido como **_stack trace_**, donde se guarda la secuencia de métodos invocados desde el actual donde se lanzó la excepción hasta el _main_. **Es muy importante saber interpretarla**. Veamos un ejemplo.

```java
package com.miorganizacion.excepciones;
public class MiExcepcion extends RuntimeException {
    public MiExcepcion() {
        super("Mensaje de mi excepción");
    }
}

package com.miorganizacion.paquete1;
import com.miorganizacion.excepciones.MiExcepcion;
public class MiClase {
    public static void miMetodo() {
        // En algún lado se lanza la excepción
        throw new MiExcepcion();
    }
    public static void main(String[] args) {
        ...
        miMetodo();
        ...
    }
}
```
Definimos una excepción _unchecked_ llamada _MiExcepcion_ dentro de un paquete de excepciones. Luego se lanza esta excepción en algún lugar de _miMetodo_ manifestando algún error de programación. Cuando se lance esta excepción, como no la capturamos en ningún momento, **se detendrá el programa y se mostrará el _stack trace_** en la consola.

```bash
Exception in thread "main" com.miorganizacion.excepciones.MiExcepcion: Mensaje de mi excepción
        at com.miorganizacion.paquete1.MiClase.miMetodo(MiClase.java:39)
        at com.miorganizacion.paquete1.MiClase.main(MiClase.java:52)
```
Este ejemplo de _stack trace_ indica que ocurrió una excepción de tipo _MiExcepcion_ lanzada con el mensaje "Mensaje de mi excepción". El método donde se originó la excepción es la línea siguiente: _com.miorganizacion.paquete1.MiClase.miMetodo_. Inclusive nos indica el archivo fuente (_MiClase.java_) y la línea específica donde se lanzó (_39_). En la línea siguiente se muestra el otro elemento de la pila de ejecución, el que invocó a _miMetodo_, en este caso es el _main_. Este patrón es siempre el mismo y describe **en cada línea a cada elemento que se encontraba en la pila de ejecución al momento de lanzarse la excepción**.

Si necesitáramos acceder de forma programática a esta información, _Throwable_ provee el método **_getStackTrace()_** que devuelve un arreglo de elementos del volcado de la pila (_StackTraceElement[]_), donde el primer elemento sería el método desde donde se lanzó la excepción y el último el _main_.

```java
catch (RuntimeException e) {
    StackTraceElement elementos[] = e.getStackTrace();
    for (int i = 0; i < elementos.length; i++) {       
        System.err.println(
            elementos[i].getMethodName() + "("
            + elementos[i].getFileName() + ":"
            + elementos[i].getLineNumber() + ")"
        );
    }
}
```

## Ejercicio: Registro de Estudiantes
1. Crear una clase llamada _Estudiante_ con las siguientes propiedades: _nombre_ (String), _edad_ (int) y _promedio_ (double).
2. Crear una clase llamada _RegistroEstudiantes_ que permita a un usuarix registrar estudiantes. La clase debe contener un arreglo (o una colección) para almacenar objetos de tipo _Estudiante_.
3. Implementar un método en la clase _RegistroEstudiantes_ que permita agregar un nuevo estudiante al registro. Podemos utilizar la librería _Scanner_ para ingresar datos por consola.
4. Verificar que manejemos las siguientes excepciones:
    - Si el nombre del estudiante es nulo o una cadena vacía, lanza una excepción personalizada llamada _NombreInvalidoException_.
    - Si la edad es menor de 0 o mayor de 120, lanza una excepción _EdadInvalidaException_.
    - Si el promedio no está dentro del rango de 0.0 a 10.0, lanza una excepción _PromedioInvalidoException_.
5. En el método _main_, crear un proceso interactivo que permita ingresar los datos de un estudiante (nombre, edad y promedio) y agregarlo al registro. Manejar cualquier excepción que pueda surgir durante este proceso y mostrar un mensaje amigable en caso de un error de ingreso.

## Ejercicio: Manejo de error en listas
Dados los ejercicios de la unidad previa [Generics](../06_generics/README.md), incorporar el manejo de errores a través de excepciones a las implementaciones de listas (tanto la lista genérica como la no genérica). Por ejemplo, identificando cuando se intenta remover un elemento en una lista vacía.

## Beneficios de usar excepciones
El uso adecuado de excepciones mejora la **robustez**, la **legibilidad** y la **mantenibilidad** del código, además de permitir un manejo más eficiente de errores y situaciones excepcionales en una aplicación. Por lo tanto, es una práctica importante en la programación orientada a objetos. A continuación resumimos algunos beneficios:

- **Manejo de errores**: Las excepciones permiten identificar y manejar errores de manera controlada en lugar de que el programa falle abruptamente. Esto mejora la **robustez** y la **confiabilidad** del software.

- **Claridad del código**: El uso de excepciones puede hacer que el código sea más limpio y **legible**. En lugar de realizar verificaciones de errores en cada punto de un programa, podemos agrupar el manejo de errores en bloques específicos, lo que facilita la comprensión del flujo principal del programa.

- **Separación de intereses**: Las excepciones permiten separar el código que realiza tareas normales del código que maneja situaciones excepcionales. Esto hace que el código sea más **modular** y más **fácil de mantener**, ya que los detalles del manejo de errores se aíslan en bloques específicos.

- **Mensajes de error informativos**: Al proporcionar mensajes de error detallados cuando se lanza una excepción, **facilitamos la identificación y corrección de problemas**. Estos mensajes pueden ser útiles tanto para desarrolladorxs como para usuarixs finales.

- **Recuperación controlada**: Las excepciones permiten tomar decisiones sobre **cómo manejar un error**. Podemos decidir si se debe intentar una acción alternativa, registrar el error para su posterior análisis o simplemente notificar de manera adecuada. Esto da lugar a una recuperación controlada y opciones de contingencia.

- **Extensibilidad**: El manejo de excepciones facilita la extensibilidad de una aplicación. Podemos agregar nuevas excepciones y manejo de errores a medida que se identifiquen nuevos problemas **sin tener que modificar todo el código existente**.

- **Depuración efectiva**: Las excepciones proporcionan información valiosa para la depuración de programas. Los mensajes de error y los _stack trace_ pueden ayudar a desarrolladorxs a **identificar la causa raíz** de los problemas y corregirlos de manera más eficiente.

> **Lectura de interés**: 
> - [The Java Tutorials. Lesson: Exceptions](https://docs.oracle.com/javase/tutorial/essential/exceptions/index.html)
> - _Effective Java 3rd, de Joshua Bloch_
>   - Item 9: Prefer try-with-resources to try-finally
>   - Item 69: Use exceptions only for exceptional conditions
>   - Item 70: Use checked exceptions for recoverable conditions and
runtime exceptions for programming errors
>   - Item 71: Avoid unnecessary use of checked exceptions
>   - Item 72: Favor the use of standard exceptions
>   - Item 73: Throw exceptions appropriate to the abstraction
>   - Item 74: Document all exceptions thrown by each method
>   - Item 75: Include failure-capture information in detail messages
>   - Item 76: Strive for failure atomicity
>   - Item 77: Don’t ignore exceptions