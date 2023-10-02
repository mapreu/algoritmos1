public class MainGenerics {

    public static void main(String[] args) {
        // Lista no Generica
        ListaNoGenerica l1 = new ListaNoGenerica();
        l1.agregar(1);
        l1.agregar(2);
        l1.agregar(3);
        l1.agregar("hola");
        l1.agregar(5);

        Integer x = (Integer) l1.obtener(4);    // Downcasting inseguro
        System.out.println(x);

        l1.remover(5);
        l1.remover(1);
        l1.remover(2);
        l1.remover(3);
        l1.remover("hola");
    }
}