package Solucion;

public class DescuentoVIP implements PoliticaDescuento {
    @Override
    public double aplicar(double subtotal) {
        return subtotal * 0.9;
    }

}
