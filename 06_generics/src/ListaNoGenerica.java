import java.util.Arrays;

public class ListaNoGenerica {
    private static final int LONGITUD_BASE = 3;
    private Object[] elementos = new Object[LONGITUD_BASE];
    private int size = 0;

    public boolean esVacia() {
        return size == 0;
    }

    private boolean estructuraLlena() {
        return size == elementos.length;
    }

    public void agregar(Object elemento) {
        if (estructuraLlena()) {
            elementos = Arrays.copyOf(elementos, size + LONGITUD_BASE);
        }
        elementos[size++] = elemento;
    }

    public Object obtener(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException(indice);
        }
        return elementos[indice];
    }

    public void remover(Object elemento) {
        int i = 0;
        boolean encontrado = false;
        while (i < size - 1) {
            if (encontrado || elementos[i].equals(elemento)) {
                encontrado = true;
                elementos[i] = elementos[i + 1];
            }
            i++;
        }
        if (encontrado || elementos[size-1].equals(elemento)) {
            elementos[i] = null;
            size--;
        }
    }

}