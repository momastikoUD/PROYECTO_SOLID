package Solucion;

public class DescuentoEmpleado implements PoliticaDescuento {
    @Override
    public double aplicar(double subtotal) {
        return subtotal * 0.5;
    }

}
