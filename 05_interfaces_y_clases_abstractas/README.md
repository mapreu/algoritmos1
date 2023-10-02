# Clases abstractas

Una clase abstracta es una clase que **no se puede instanciar** directamente, no podemos crear un objeto directamente a partir de una clase abstracta. Las clases abstractas sirven como plantillas o modelos para otras clases que las extienden. Estas clases derivadas o subclases heredan la estructura y el comportamiento de la clase abstracta.

> Una clase abstracta se compone igual que cualquier otra clase, sólo que no puede instanciarse. Una clase no abstracta se suele decir **clase concreta**.

## ¿Cuándo usarlas?
Las clases abstractas son útiles cuando deseamos definir **una estructura común** para un grupo de clases que comparten ciertas características o comportamientos similares. Al hacerlo podemos evitar duplicar código y promover la reutilización y la coherencia en nuestro diseño. A su vez, las clases abstractas nos permiten **establecer contratos** o reglas que las subclases deben seguir si las definimos con **métodos abstractos**.

## Método abstracto
Un método abstracto es un método que **se declara su firma pero no contiene una implementación** en esa clase. No tiene un cuerpo de código definido para esa clase. La responsabilidad de proporcionar la implementación recae en las subclases que heredan de la clase abstracta o implementan la interfaz.

Los métodos abstractos son útiles cuando deseamos definir un **contrato o un comportamiento común** en una clase abstracta o interfaz, pero no podemos proporcionar una implementación concreta a ese nivel, así que esperamos que clases en niveles inferiores de la jerarquía proporcionen sus propias implementaciones específicas a través de la **sobreescritura** de estos métodos.

Son esenciales para la **abstracción** y la **generalización**. Nos permiten definir una interfaz común que las subclases deben seguir, lo que garantiza que todas las subclases tengan ciertos comportamientos en común, pero también la flexibilidad de personalizar esas implementaciones según sea necesario.

## ¿Cómo se definen en Java?
Para declarar una clase abstracta en Java utilizamos la palabra clave **_abstract_** en la definición de clase.


```java
abstract class ClaseAbstracta {
    // Otros métodos y propiedades
    
    // Método abstracto (sin implementación)
    abstract void metodoAbstracto();
}
```
Observemos que el método abstracto **no contiene un cuerpo de código**, y está precedido por la palabra clave **_abstract_**. Cualquier clase que herede de esta clase abstracta debe proporcionar una implementación concreta para este método abstracto, de lo contrario será también clase abstracta.

```java
public abstract class Persona {
	private String nombre;

	public Persona(String n) {
		nombre = n;
	}

	public String getNombre() {
		return nombre;
	}

	public abstract void controlarAsistencia();
} 	
```
En este ejemplo, no podemos instanciar objetos de la  clase _Persona_ porque está definida como abstracta. Aún si quisiéramos quitar la palabra _abstract_ de la primera línea, el compilador no nos dejaría avanzar porque tiene el método _controlarAsistencia_ definido como abstracto.

```java
public class Estudiante extends Persona {
	private int matricula;

	public Estudiante(String n, int mat) {
		super(n);
		matricula = mat;
	}

	@Override
	public void controlarAsistencia() {
		… implementación para estudiantes …
	}
} 	
```
En cambio, _Estudiante_ hereda el método abstracto _controlarAsistencia_ de _Persona_ (junto con el resto de atributos y métodos que tenga visibilidad) y le agrega su propia implementación para poder ser clase concreta.

## Reglas a recordar

> - Una clase abstracta **puede** tener definidos **métodos abstractos**.
> - Si una clase tiene definidos **métodos abstractos**, entonces debe ser definida como **clase abstracta**.
> - Si una clase hereda algún método abstracto, debe **sobreescribirlo** con su propia implementación para poder ser una **clase concreta**.

## Ejercicio: Personajes en juego de rol

Imaginemos que estamos desarrollando un juego de rol (RPG) en el que los jugadores pueden elegir entre diferentes tipos de personajes. Queremos utilizar clases abstractas para representar los personajes genéricos y luego crear subclases específicas para cada tipo de personaje.
1. Crear una clase abstracta llamada _Personaje_ que contenga las siguientes propiedades y métodos abstractos:
	- nombre (String)
	- nivel (int)
	- puntosVida (int)
	- atacar() - representa la acción de atacar y debe ser implementado en las subclases.
	- defender() - representa la acción de defenderse y debe ser implementado en las subclases.

2. Crear dos subclases concretas de _Personaje_ llamadas _Paladín_ y _Wizard_.
	- En ambas clases implementar el método atacar() de manera específica contemplando que:
		- _Paladín_: al atacar quita 5 puntos de vida al contrincante.
		- _Wizard_: al atacar quita 3 puntos de vida al contrincante.
	- En ambas clases implementar el método defender() de manera específica contemplando que:
		- _Paladín_: al defender reduce en 2 puntos de vida el daño recibido por ataque.
		- _Wizard_: al defender reduce en 3 puntos de vida el daño recibido por ataque.
	- En todos los métodos de ataque y defensa imprimir por consola el personaje y la acción.

4. En el main crear instancias de un _Paladín_ y un _Wizard_, y permite que ambos realicen acciones de ataque y defensa.

5. Mostrar el estado de los personajes (nombre, nivel y puntos de vida) después de cada acción.

# Interfaces

Una interfaz es un **contrato** que define el comportamiento mínimo que debe cumplir una clase que la implementa u otra interfaz que la extiende. Básicamente podríamos decir que es una **colección de métodos abstractos** (sin implementación) que define un conjunto de acciones o comportamientos que una clase debe proporcionar. Al igual que con las clases abstractas, una interfaz **no puede instanciarse**.

## Interfaz en Java
En Java, una interfaz es un **tipo de referencia**, similar a una clase, que puede componerse de los siguientes miembros:
- Constantes
	- variables static final
	- se pueden heredar (podría provocar error de compilación por ambigüedad)
- Métodos abstractos
- Métodos _default_
	- método que tienen una implementación por defecto
	- sobreescriben métodos abstractos heredados
- Métodos estáticos
- Tipos anidados (implícitamente estáticos)

> Todos los miembros de una interfaz son implícitamente de accesibilidad **public**. 

> Una interfaz puede utilizarse en cualquier lugar donde pueda usarse un **tipo de dato**.

Para declarar una interfaz en Java, utilizamos la palabra clave **interface**, seguida del nombre de la interfaz y los miembros.

```java
public interface NombreDeLaInterfaz {
	// Constante
	static final int CONSTANTE = 10;

    // Declaración de método abstracto
    tipoDeRetorno nombreDelMetodo(parametros);
    
	// Método default
	default metodoDef() {
		// Implementación del método
	}

    // ...

}
```
Para que una clase siga un contrato de interfaz, debe _implementar_ o _realizar_ dicha interfaz utilizando la palabra clave **_implements_**. La clase debe entonces proporcionar implementaciones concretas para todos los métodos abstractos para poder ser una clase concreta, de lo contrario debe definirse como **clase abstracta**.

```java
public class MiClaseConcreta implements NombreDeLaInterfaz {
    // Implementación de los métodos de la interfaz
	@Override
    public tipoDeRetorno nombreDelMetodo(parametros) {
        // Código de implementación
    }
    
    // ...
}

public abstract class MiClaseAbstracta implements NombreDeLaInterfaz {   
    // No implementa nombreDelMetodo heredado de la interfaz
	// ...
}
```

## Herencia en interfaces
Una característica interesante de las interfaces en Java es que pueden **extenderse** a través de una jerarquía similar que con la herencia de clases. Aún mejor, las interfaces **soportan la herencia múltiple**. Podemos entonces tener una interfaz que extienda a más de una interfaz, es decir, que tenga más de una superinterfaz.

Veamos un ejemplo más elaborado.

```java
public interface DriverBaseDeDatos {
    void conectar();
    void desconectar();
    void ejecutarQuery(String query);
}

public interface ConexionDB {
	DriverBaseDeDatos getDriver();
}

public interface RepoBaseDeDatos<T> {
    T guardar(T entity);
    T buscar(Long id);
    void eliminar(Long id);
}

public interface Auditable {
    void auditar(String message);
}

public class UserRepo implements RepoBaseDeDatos<User>, Auditable {
    private final ConexionDB conexion;

    public UserRepository(ConexionDB conexion) {
        this.conexion = conexion;
    }

    @Override
    public User guardar(User user) {
		DriverBaseDeDatos driver = conexion.getDriver();
        driver.conectar();
        String query = "INSERT INTO users (name, email) VALUES (?, ?)";
        driver.ejecutarQuery(query, user.getName(), user.getEmail());
        driver.desconectar();
        auditar("User guardado con ID: " + user.getId());
        return user;
    }

    @Override
    public User buscar(Long id) {
        // Buscar user en DB
        auditar("User buscado con ID: " + id);
        return user;
    }

    @Override
    public void eliminar(Long id) {
        // Eliminar user en DB
        auditar("User eliminado con ID: " + id);
    }

    @Override
    public void auditar(String message) {
        // Guardar el mensaje de auditoria
    }
}
```
La interfaz _DriverBaseDeDatos_ sirve para definir el comportamiento general de cualquier driver de una base de datos. Por lo cual, no importa qué motor utilicemos de forma concreta siempre que respetemos la interfaz utilizada. Si cambiamos de motor, sólo deberíamos implementar la interfaz y el resto de nuestro código **no se ve afectado**. Notemos que hay una relación de **dependencia** con la interfaz _ConexionDB_, que la utiliza como tipo de retorno en el método _getDriver_. Esto demuestra que **una interfaz es un tipo de datos**.

Por otro lado, _RepoBaseDeDatos_ es una **interfaz genérica** y sirve para modelar cualquier repositorio para almacenar modelos de datos, sin importar el motor que lo soporte (podría ser MySQL, Oracle, un archivo CSV, etc). El detalle de implementación de cómo realizar estas operaciones se define en una clase concreta que la implemente (_UserRepo_), junto con la clase concreta que implemente _DriverBaseDeDatos_ (que no está en el ejemplo). A su vez, _UserRepo_ implementa también _Auditable_, permitiendo la **herencia múltiple**, por lo cual _UserRepo_ debe sobreescribir todos los métodos declarados en ambas interfaces.

## Beneficios de usar interfaces
Las interfaces son útiles por varias razones:
- Establecen **contratos**: Las interfaces definen contratos que las clases deben seguir, lo que garantiza que ciertos métodos estén disponibles y tengan la misma firma en todas las clases que implementen la interfaz.
- Promueven la **abstracción**: Las interfaces permiten la abstracción, ya que los detalles de implementación no se especifican en la interfaz, sino solo las firmas de los métodos. Esto ayuda a separar la especificación de la implementación.
- Facilitan la **interoperabilidad**: Diferentes clases pueden implementar la misma interfaz, lo que facilita la sustitución de objetos y la construcción de sistemas más flexibles y extensibles.

## Interfaces vs Clases abstractas
Una duda que suele surgir es identificar cuándo utilizar una clase abstracta o una interfaz, ya que ambas proveen características similares. Si bien cada situación depende del contexto y problema que estemos modelando, podemos apoyarnos las siguientes guías:

- Considerar **Clases abstractas** si:
	- Necesitamos compartir implementación de métodos entre clases bien relacionadas.
	- Necesitamos mantener un **estado** o estructura en común para objetos de clases relacionadas (definir atributos o métodos de **instancia**).
- Considerar **Interfaces** si:
	- Esperamos aplicar cierto comportamiento a clases no relacionadas.
	- Necesitamos sólo definir un comportamiento para cierto tipo de dato.
	- Necesitamos herencia múltiple.
	
> **Lectura de interés**: _Effective Java 3rd, de Joshua Bloch_:
> - Item 20: Prefer interfaces to abstract classes
> - Item 21: Design interfaces for posterity
> - Item 22: Use interfaces only to define types
> - Item 64: Refer to objects by their interfaces
> - Item 14: Consider implementing Comparable

## Ejercicio: Personajes en juego de rol II

Extender el ejercicio [Personajes en juego de rol](#ejercicio-personajes-en-juego-de-rol) para que soporte personajes que puedan atacar a distancia. Este tipo de ataque sólo lo pueden hacer algunos personajes y permiten evadir la defensa común.

La idea es pensar cómo utilizar interfaces para proveer este comportamiento al personaje _Wizard_, por ejemplo incorporando el comportamiento _atacarDistancia()_ que permite realizar el ataque sin descontar puntos por defensa. Tener en cuenta que este comportamiento podría luego aplicarse a otros personajes, por lo que no debe ser exclusivo de la clase _Wizard_.

En el main crear un arreglo de 3 personajes que pueden atacar a distancia y luego realizar los ataques sobre otros objetos de personajes.