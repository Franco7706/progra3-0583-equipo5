package pe.edu.pucp.softpac.daoImpl.util;

//esta clase representa la clase que incorporará los metadatos a la solución
public class Columna {
    private String nombre;
    private Boolean esLlavePrimaria;
    private Boolean esAutoGenerado;

    public Columna(String nombre, Boolean esLlavePrimaria, Boolean esAutoGenerado) {
        this.nombre = nombre;
        this.esLlavePrimaria = esLlavePrimaria;
        this.esAutoGenerado = esAutoGenerado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEsLlavePrimaria() {
        return esLlavePrimaria;
    }

    public void setEsLlavePrimaria(Boolean esLlavePrimaria) {
        this.esLlavePrimaria = esLlavePrimaria;
    }

    public Boolean getEsAutoGenerado() {
        return esAutoGenerado;
    }

    public void setEsAutoGenerado(Boolean esAutoGenerado) {
        this.esAutoGenerado = esAutoGenerado;
    }


}
