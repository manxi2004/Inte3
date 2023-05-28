package model;

public class Magazine extends MaterialBibliografico {
    private Category categoria;
    private double valorSuscripcion;
    private String periodicidadEmision;
    private int numSuscripcionesActivas;
    private int acumuladoPaginasLeidas;
    private String name;
    // This is the implementation of a class called `Magazine` that extends the
    // `MaterialBibliografico`
    // class. It has several instance variables such as `categoria`,
    // `valorSuscripcion`,
    // `periodicidadEmision`, `numSuscripcionesActivas`, `acumuladoPaginasLeidas`,
    // and `name`. The
    // constructor initializes these variables along with the ones inherited from
    // the superclass.

    public Magazine(String id, String name, int numPaginas,
            String fechaPublicacion, Category categoria,
            String urlPortada, double valorSuscripcion,
            String periodicidadEmision, int numSuscripcionesActivas,
            int acumuladoPaginasLeidas) {
        super(id, name, numPaginas, fechaPublicacion, urlPortada);
        this.categoria = categoria;
        this.valorSuscripcion = valorSuscripcion;
        this.periodicidadEmision = periodicidadEmision;
        this.numSuscripcionesActivas = numSuscripcionesActivas;
        this.acumuladoPaginasLeidas = acumuladoPaginasLeidas;
    }

    public String getName() {

        return name;
    }

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }

    public double getPrice() {
        return valorSuscripcion;
    }

    public void setValorSuscripcion(double valorSuscripcion) {
        this.valorSuscripcion = valorSuscripcion;
    }

    public String getPeriodicidadEmision() {
        return periodicidadEmision;
    }

    public void setPeriodicidadEmision(String periodicidadEmision) {
        this.periodicidadEmision = periodicidadEmision;
    }

    public int getNumSuscripcionesActivas() {
        return numSuscripcionesActivas;
    }

    public void setNumSuscripcionesActivas(int numSuscripcionesActivas) {
        this.numSuscripcionesActivas = numSuscripcionesActivas;
    }

    public int getAcumuladoPaginasLeidas() {
        return acumuladoPaginasLeidas;
    }

    public void setAcumuladoPaginasLeidas(int acumuladoPaginasLeidas) {
        this.acumuladoPaginasLeidas = acumuladoPaginasLeidas;
    }
}
