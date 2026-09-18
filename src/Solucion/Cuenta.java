package Solucion;

import java.util.List;

public class Cuenta {
    private PoliticaDescuento politicaDescuento;
    private String nombre;

    public Cuenta(String nombre, PoliticaDescuento politicaDescuento) {
        this.nombre = nombre;
        this.politicaDescuento = politicaDescuento;
    }

    public double calcularTotal(double subtotal) {
        return politicaDescuento.aplicar(subtotal);
    }

}
