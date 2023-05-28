package model;

import java.net.URL;

abstract class MaterialBibliografico {
    protected String id;
    protected String nombre;
    protected int numPaginas;
    protected String fechaPublicacion;
    protected String urlPortada;
    protected int acumuladoPaginasLeidas;

    // This is the definition of an abstract class called `MaterialBibliografico`.
    // It has several
    // instance variables such as `id`, `nombre`, `numPaginas`, `fechaPublicacion`,
    // `urlPortada`, and
    // `acumuladoPaginasLeidas`. It also has a constructor that initializes these
    // variables, and several
    // getter and setter methods to access and modify them. This class is meant to
    // be extended by other
    // classes that represent specific types of bibliographic materials, such as
    // books or articles.
    public MaterialBibliografico(String id, String nombre, int numPaginas, String fechaPublicacion, String urlPortada) {
        this.id = id;
        this.nombre = nombre;
        this.numPaginas = numPaginas;
        this.fechaPublicacion = fechaPublicacion;
        this.urlPortada = urlPortada;
        this.acumuladoPaginasLeidas = 0;
    }

    public void setUrlPortada(String coverUrl) {
        this.urlPortada = coverUrl;
    }

    public void setAcumuladoPaginasLeidas(int totalPagesRead) {
        this.acumuladoPaginasLeidas = totalPagesRead;
    }

    public void setFechaPublicacion(String publicationDate) {
        this.fechaPublicacion = publicationDate;
    }

    public Object getId() {
        return id;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public void setNumPaginas(int numPages) {
        this.numPaginas = numPages;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public String getName() {
        return nombre;
    }

    public int getAcumuladoPaginasLeidas() {
        return acumuladoPaginasLeidas;
    }
}
