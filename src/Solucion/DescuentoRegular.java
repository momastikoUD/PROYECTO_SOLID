package Solucion;

public class DescuentoRegular implements PoliticaDescuento {
    @Override
    public double aplicar(double subtotal) {
        return subtotal;
    }

}
