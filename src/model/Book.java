package model;

public class Book extends MaterialBibliografico {
    private String resenaCorta;
    private Genre genero;
    private double valorVenta;
    private int numEjemplaresVendidos;
    private int acumuladoPaginasLeidas;

    // This is the implementation of a Book class that extends from a
    // MaterialBibliografico class. It
    // includes several attributes such as resenaCorta, genero, valorVenta,
    // numEjemplaresVendidos, and
    // acumuladoPaginasLeidas. It also includes several methods such as getters and
    // setters for these
    // attributes. The constructor initializes the attributes with the given
    // parameters and calls the
    // constructor of the superclass with some of these parameters.
    public Book(String id, String nombre, int numPaginas, String resenaCorta,
            String fechaPublicacion, Genre genero, String urlPortada,
            double valorVenta, int numEjemplaresVendidos,
            int acumuladoPaginasLeidas) {
        super(id, nombre, numPaginas, fechaPublicacion, urlPortada);
        this.resenaCorta = resenaCorta;
        this.genero = genero;
        this.valorVenta = valorVenta;
        this.numEjemplaresVendidos = numEjemplaresVendidos;
        this.acumuladoPaginasLeidas = acumuladoPaginasLeidas;
    }

    public String getNombre() {

        return nombre;

    }

    public String getResenaCorta() {
        return resenaCorta;
    }

    public void setResenaCorta(String resenaCorta) {
        this.resenaCorta = resenaCorta;
    }

    public Genre getGenero() {
        return genero;
    }

    public void setGenero(Genre genero) {
        this.genero = genero;
    }

    public double getPrice() {
        return valorVenta;
    }

    public void setValorVenta(double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public int getNumEjemplaresVendidos() {
        return numEjemplaresVendidos;
    }

    public void setNumEjemplaresVendidos(int numEjemplaresVendidos) {
        this.numEjemplaresVendidos = numEjemplaresVendidos;
    }

    public int getAcumuladoPaginasLeidas() {
        return acumuladoPaginasLeidas;
    }

    public void setAcumuladoPaginasLeidas(int acumuladoPaginasLeidas) {
        this.acumuladoPaginasLeidas = acumuladoPaginasLeidas;
    }
}
