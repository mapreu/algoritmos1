class Persona implements Cloneable {
    private String nombre;
    private int edad;
    private Documento documento;

    private Persona() {}

    public Persona(String nombre, int edad, int nroDocumento) {
        this.nombre = nombre;
        this.edad = edad;
        this.documento = new Documento(nroDocumento);
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNroDocumento(int nroDocumento) {
        this.documento.setNumero(nroDocumento);
    }

    @Override
    public String toString() {
        return this.nombre + ", " + this.edad + ", " + this.documento.getNumero();
    }

    @Override
    public Persona clone() {
        try {
            Persona copia = (Persona) super.clone();    // Copia superficial de persona
            copia.documento = this.documento.clone();   // Copia profunda de Documento
            return copia;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Persona copiaSuperficial() {
        Persona copia = new Persona();
        copia.nombre = this.nombre;
        copia.edad = this.edad;
        copia.documento = this.documento;   // Se asigna la misma referencia original
        return copia;
    }

    
}