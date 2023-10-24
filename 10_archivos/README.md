# Archivos en Java

## Streams
El concepto de streams desempeña un papel importante en el manejo de archivos y en la manipulación de datos en general. Los streams son secuencias o flujos de datos que se transfieren desde una fuente a un destino. En Java, los streams se utilizan para la lectura y escritura de datos en muchas situaciones, incluyendo el manejo de archivos.

Podemos diferenciar dos tipos principales de streams en Java:

- **InputStream**: Este tipo de stream se utiliza para **leer** datos desde una fuente, como un archivo de entrada, una conexión de red o la entrada estándar (teclado).

- **OutputStream**: Este tipo de stream se utiliza para escribir **datos** en una fuente, como un archivo de salida, una conexión de red o la salida estándar (pantalla).

Los streams se basan en una **secuencia de bytes**. Esto significa que los datos se leen o escriben en forma de **bytes**, lo que es especialmente útil para archivos binarios (como imágenes) y también para archivos de texto.

### Funcionamiento de los Streams
Los streams funcionan con un enfoque de lectura y escritura **secuencial**. Esto significa que los datos se procesan uno tras otro, de principio a fin. Cuando leemos datos de un _InputStream_, avanzamos a través de la secuencia de bytes. Cuando escribimos en un _OutputStream_, los datos se agregan al final de la secuencia de bytes existente.

Por ejemplo, cuando leemos un archivo de texto línea por línea, avanzamos a la siguiente línea después de cada lectura, y cuando escribimos en un archivo, los datos se agregan al final del archivo sin afectar el contenido anterior.

### Características Clave de los Streams
- **Buffering**: Los streams en Java suelen admitir buffering. Esto significa que pueden leer o escribir datos en búferes temporales antes de transferirlos realmente a la fuente o el destino. Esto mejora la eficiencia y el rendimiento al reducir la cantidad de operaciones de lectura/escritura en disco o en la red.

- **Cierre**: Es esencial cerrar los streams cuando ya no se necesiten. Esto garantiza que los recursos se liberen correctamente. Podemos utilizar el bloque [try-with-resources](../07_excepciones/README.md#el-bloque-try-with-resources) para asegurarnos de que los streams se cierren automáticamente después de su uso.

- **Conversiones de Datos**: Los streams se encargan de la conversión entre tipos de datos primitivos (como bytes) y tipos de datos más legibles o utilizables (como caracteres o tipos de datos específicos de nuestra aplicación).

## La clase File
En Java, la clase `File` se utiliza para representar archivos y directorios en el sistema de archivos. Podemos crear objetos `File` para interactuar con archivos y directorios, por ejemplo para crearlos, eliminarlos, verificar su existencia y obtener información sobre ellos. La clase `File` es parte del paquete `java.io`, por lo que debemos importarla para usarla.

A continuación se detallan algunas de las operaciones más comunes que podemos realizar con esta clase:

### Creación de un Objeto File
Podemos crear un objeto `File` para representar un archivo o directorio específico en el sistema de archivos. Por ejemplo:

```java
File archivo = new File("miarchivo.txt");
File directorio = new File("miDirectorio");
```

No confundir la creación de un objeto que represente un archivo en el sistema, con la operación de la creación del archivo físico. En las líneas previas simplemente estamos **creando objetos** que representen esos archivos en el sistema.

### Verificación de la existencia
Podemos verificar si un archivo o directorio existe utilizando el método exists():

```java
if (archivo.exists()) {
    // El archivo existe
}
```

### Creación de un Archivo
Si deseamos crear un archivo vacío podemos utilizar el método createNewFile():

```java
if (archivo.createNewFile()) {
    // Archivo creado con éxito
}
```
Se debe tener en cuenta que este método puede lanzar una excepción `IOException` que es de tipo _checked_.

### Creación de un Directorio
Ahora si deseamos crear un directorio podemos utilizar el método mkdir():

```java
if (directorio.mkdir()) {
    // Directorio creado con éxito
}
```

### Eliminación de un Archivo o Directorio
Podemos eliminar un archivo o directorio con el método delete():

```java
if (archivo.delete()) {
    // Archivo eliminado con éxito
}
```

### Listado de Archivos en un Directorio
Podemos obtener una lista de archivos en un directorio utilizando el método listFiles():

```java
File[] archivosEnDirectorio = directorio.listFiles();
```

## Clases relacionadas a File
Java también proporciona clases relacionadas a `File` que ofrecen funcionalidad adicional y específica para trabajar con archivos y directorios. Algunas de estas clases incluyen:

### FileInputStream y FileOutputStream
Estas clases permiten la lectura y escritura de datos **binarios** desde y hacia archivos, respectivamente. Su documentación: [FileInputStream](https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html) y [FileOutputStream](https://docs.oracle.com/javase/8/docs/api/java/io/FileOutputStream.html).

### FileReader y FileWriter
Estas clases permiten la lectura y escritura de datos de **texto** desde y hacia archivos, respectivamente. Su documentación: [FileReader](https://docs.oracle.com/javase/8/docs/api/java/io/FileReader.html) y [FileWriter](https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html).

### BufferedReader y BufferedWriter
Estas clases se utilizan para mejorar el rendimiento de lectura y escritura al utilizar búferes para almacenar datos en memoria antes de leerlos desde un archivo o escribirlos a un archivo. Su documentación: [BufferedReader](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html) y [BufferedWriter](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedWriter.html).

### RandomAccessFile
A diferencia de las previas, esta clase permite el **acceso aleatorio** a un archivo, lo que significa que podemos leer y escribir en cualquier posición del archivo. Su documentación: [RandomAccessFile](https://docs.oracle.com/javase/8/docs/api/java/io/RandomAccessFile.html).

## Escritura en un Archivo
Para escribir datos en un archivo a través de un **flujo de bytes** primero necesitamos un `OutputStream` que utilizaremos para ir enviando información que será almacenada luego en el archivo. Veamos un ejemplo de cómo escribir texto en un archivo:

```java
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EscribirArchivo {
    public static void main(String[] args) {
        try {
            // Especifica el nombre del archivo
            String nombreArchivo = "miarchivo.txt";
            
            // Crea un OutputStream para escribir en el archivo
            OutputStream outputStream = new FileOutputStream(nombreArchivo);
            
            // Convierte el texto en bytes y escribe en el archivo
            String texto = "Este es el texto que aparece en mi archivo";
            byte[] datos = texto.getBytes();
            outputStream.write(datos);
            
            // Cierra el OutputStream
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
Este ejemplo se crea un archivo llamado _miarchivo.txt_ y se escribe en el mismo la cadena _Este es el texto que aparece en mi archivo_. Dado que utilizamos `OutputStream` para escribir bytes en el archivo, debemos previamente convertir el texto a bytes con el método `getBytes()`. Obviamente, podremos guardar cualquier tipo de información de esta forma ya que utilizamos el flujo de bytes, por ejemplo, imágenes.

### Cierre de Streams
Como mencionamos al principio, es importante cerrar los streams una vez que hayamos terminado de trabajar con ellos. Es por ello que siempre debemos invocar el método `close()` del _handler_ del archivo o stream. La alternativa recomandada es que, en lugar de cerrar manualmente con ese método, utilicemos el bloque [try-with-resources](../07_excepciones/README.md#el-bloque-try-with-resources).

```java
try (OutputStream outputStream = new FileOutputStream("miarchivo.txt")) {
    // Escribe datos en el archivo
} catch (IOException e) {
    e.printStackTrace();
}
```
Esto mismo es aplicable para cualquier otro tipo de stream, no sólo el `OutputStream` como se ve en este ejemplo.

### Escritura con FileWriter
La clase `FileWriter` se puede utilizar para escribir caracteres en un **archivo de texto**. Es una subclase de `java.io.OutputStreamWriter` que facilita la escritura de texto ccodificándolo con el [charset](https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html) por defecto. Tiene sobrecargado su constructor para aceptar tanto un objeto de tipo `File`, un [FileDescriptor](https://docs.oracle.com/javase/8/docs/api/java/io/FileDescriptor.html), o una cadena que especifique la ruta del archivo a escribir. También es posible definir si se sobreescribirá el archivo destino o si se agregará texto al final de uno existente con el flag _append_. Veamos un ejemplo de cómo utilizarla:

```java
import java.io.FileWriter;
import java.io.IOException;

public class EscribirEnArchivoDeTexto {
    public static void main(String[] args) {
        String nombreArchivo = "miarchivo.txt";

        try (FileWriter archivoWriter = new FileWriter(nombreArchivo)) {
            String texto = "Este es un ejemplo de escritura en un archivo de texto.";
            archivoWriter.write(texto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
En este ejemplo creamos un nuevo archivo "miarchivo.txt" para escritura utilizando `FileWriter`. Luego escribimos la cadena de texto en el archivo utilizando el método `write()`. Notemos que ya no es necesario convertir el texto a bytes ya que esto se realiza automáticamente con el método heredado de [Writer](https://docs.oracle.com/javase/8/docs/api/java/io/Writer.html#write-char:A-).

## Lectura desde un Archivo
De forma similar al caso del `OutputStream` cuando escribimos, para leer datos como un **flujo de bytes** desde un archivo, necesitamos un `InputStream`. Veamos un ejemplo de cómo leer datos desde un archivo:

```java
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LeerArchivo {
    public static void main(String[] args) {
        try {
            // Especifica el nombre del archivo
            String nombreArchivo = "miarchivo.txt";
            
            // Crea un InputStream para leer el archivo
            InputStream inputStream = new FileInputStream(nombreArchivo);
            
            // Lee los datos del archivo en un buffer de 1Kb
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String texto = new String(buffer, 0, bytesRead);
                System.out.println(texto);
            }
            
            // Cierra el InputStream
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
En este ejemplo se lee el contenido del archivo _miarchivo.txt_ y se muestra en la consola. Dado que utilizamos un `InputStream` para leer bytes del archivo, debemos generar una instancia de `String` a partir de los bytes leídos para convertirlos a texto. También utilizamos un buffer para ir leyendo de a 1Kb por vez, hasta que no queden más bytes por leer. El método `inputStream.read(buffer))` devuelve _-1_ cuando ya no quedan bytes por leer en el flujo.

### Lectura con FileReader
La clase `FileReader` se puede utilizar para leer datos de un **archivo de texto**. Es una subclase de `InputStreamReader` que facilita la obtención de texto a partir de la decodificación de los bytes leídos con el [charset](https://docs.oracle.com/javase/8/docs/api/java/nio/charset/Charset.html) por defecto. Veamos un ejemplo de cómo utilizarla:

```java
import java.io.FileReader;
import java.io.IOException;

public class LeerArchivoDeTexto {
    public static void main(String[] args) {
        String nombreArchivo = "miarchivo.txt";

        try (FileReader archivoReader = new FileReader(nombreArchivo)) {
            int caracter;
            while ((caracter = archivoReader.read()) != -1) {
                System.out.print((char) caracter); // Convierte el valor numérico en carácter
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
El método `read()` se hereda desde [InputStreamReader](https://docs.oracle.com/javase/8/docs/api/java/io/InputStreamReader.html) y [Reader](https://docs.oracle.com/javase/8/docs/api/java/io/Reader.html) y está sobrecargado para aceptar diferentes parámetros. En el ejemplo se utiliza la versión de `InputStreamReader` sin parámetros donde se lee un caracter a la vez (devuelve su valor numérico según el _charset_) hasta que devuelve _-1_ cuando llega al final del _stream_.

## Eficiencia con Buffered
El uso de `BufferedReader` y `BufferedWriter` puede hacer que el manejo de **archivos de texto** en Java sea más eficiente al reducir la cantidad de lecturas y escrituras directas en el archivo. Suele ser recomendado para evitar problemas al momento de definir un _buffer_ manualmente. Además proveen métodos prácticos para leer o escribir líneas de texto completas.

### Lectura con BufferedReader
Veamos un ejemplo de cómo leer un archivo de texto línea por línea:

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerArchivoConBuffer {
    public static void main(String[] args) {
        String nombreArchivo = "miarchivo.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nombreArchivo))) {    
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
Es común encontrar la construcción del `BufferedReader` con el objeto `FileReader` inicializado como argumento de su constructor. En este caso también optamos por utilizar el _try-with-resources_ para evitar cerrar explícitamente el _stream_. El método `readLine()` nos provee cada línea de texto del archivo hasta llegar al final, donde devuelve `null`.

### Escritura con BufferedWriter
Ahora veamos un ejemplo de cómo escribir en un archivo de texto utilizando `BufferedWriter`:

```java
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

public class EscribirArchivoConBuffer {
    public static void main(String[] args) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("miarchivo.txt"))) {
            String texto = "Voy a escribir esta primera línea.";
            bufferedWriter.write(texto);
            // Agregamos un separador de línea (\n)
            bufferedWriter.newLine();

            bufferedWriter.write("Esta es la segunda línea.");

            // No olvidemos escribir una nueva línea al final (si es necesario)
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
En este caso utilizamos el método `write()` para escribir texto en el archivo y luego utilizamos `newLine()` para agregar el separador de nueva línea (_line break_). De esa forma, la próxima vez que escribimos estaremos escribiendo en la línea siguiente, lo cual se verifica con la segunda invocación al `write()`.

## Ejercicio: Parser CSV
Implementar un programa que permita leer un archivo formato csv (valores separados por coma) y lo almacene en una matriz de `String`. Probarlo con los archivos en [datasets](./datasets/).
- No olvidar realizar las validaciones necesarias, lanzando excepciones descriptivas cuando ocurre alguna violación de formato. Por ejemplo, si alguna línea no tiene todos los valores que corresponden, se debe informar en la excepción qué línea tiene el error.
- Agregar un parámetro que permita tomar la primera línea como header de columnas y guardarlas en un arreglo de `String`.

## Ejercicio: Parser CSV 2
Extender el ejercicio previo de forma que permita almacenar las columnas con el tipo de dato correcto. En principio, si una columna es de tipo numérica, podríamos almacenar los datos con subclases de `Number`. El resto podrían continuar siendo de tipo `String`, pero la idea es que eventualmente podamos extender a guardar otro tipo de datos.

Tip: `NumberFormat.getInstance().parse(cadena)` 

## Ejercicio: Estadísticos de un CSV
Incorporar al parser previo la funcionalidad que permita resumir con estadísticos clásicos aquellas columnas numéricas. Por lo tanto, para cada columna se deben poder computar los siguientes:
- Media
- Desviación estándar
- Mínimo
- Máximo
- Cuartiles: Q1, Q2 (mediana) y Q3.