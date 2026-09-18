package Errores;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Pedido pedido = new Pedido();
        pedido.setCliente("Ana");
        pedido.setTipoCliente("VIP");
        pedido.agregarPlato("Bandeja paisa", 28000);
        pedido.agregarPlato("Limonada", 6000);

        System.out.println("Total: " + pedido.calcularTotal());
        pedido.guardarEnBaseDeDatos();
        pedido.imprimirRecibo();
        pedido.enviarCorreoConfirmacion();

        // El código cliente confía en que TODO MetodoPago se puede cobrar igual...
        List<MetodoPago> pagosDelDia = List.of(
                new PagoTarjeta(),
                new PagoEfectivo(),
                new PagoPuntosFidelidad());

        for (MetodoPago pago : pagosDelDia) {
            pago.cobrar(15000); // esto revienta con PagoPuntosFidelidad si el monto supera los puntos
        }

        // El mesero queda obligado a "implementar" trabajos que no le corresponden
        Empleado mesero = new Mesero();
        mesero.atenderMesa();
    }
}