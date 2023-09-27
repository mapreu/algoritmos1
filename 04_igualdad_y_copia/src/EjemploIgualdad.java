public class EjemploIgualdad {
    public static void main(String[] args) {
        String x = "Esto es un texto";
        String y = x;
        System.out.println(x == y);         // True: son la misma instancia
        System.out.println(x.equals(y));    // True: al ser la misma instancia, son iguales
        y = "Esto es otro texto";           
        System.out.println(x == y);         // False: Son instancias distintas
        System.out.println(x.equals(y));    // False: Son cadenas distintas
        y = "Esto es un texto";
        System.out.println(x == y);         // True: Si bien son instancias distintas, en el caso de String Java que permite asociar la misma instancia si ya existe en memoria (intern)
        System.out.println(x.equals(y));    // True: al ser la misma instancia, son iguales
        y = new String(x);
        System.out.println(x == y);         // False: Son instancias distintas
        System.out.println(x.equals(y));    // True: Son cadenas iguales
    }
}
