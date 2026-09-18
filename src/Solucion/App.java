package Solucion;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Pedido pedido = new Pedido();
        Cuenta cuentaAna = new Cuenta("Ana", new DescuentoVIP());
        pedido.agregarPlato("Bandeja paisa", 28000);
        pedido.agregarPlato("Limonada", 6000);

        System.out.println("Total: " + pedido.calcularTotal(cuentaAna));
        PedidoRepositorio pedidoRepositorio = new PedidoRepositorio();
        pedidoRepositorio.guardarEnBaseDeDatos(pedido);
        ImpresionRecibos impresionRecibos = new ImpresionRecibos();
        impresionRecibos.imprimirRecibo(pedido, cuentaAna);
        EnviarCorreos enviarCorreos = new EnviarCorreos();
        enviarCorreos.enviarCorreoConfirmacion(pedido);

        // El código cliente confía en que TODO MetodoPago se puede cobrar igual...
        List<MetodoPago> pagosDelDia = List.of(
                new PagoTarjeta(),
                new PagoEfectivo(),
                new PagoPuntosFidelidad());

        for (MetodoPago pago : pagosDelDia) {
            pago.cobrar(15000); // esto revienta con PagoPuntosFidelidad si el monto supera los puntos
        }

        // El mesero queda obligado a "implementar" trabajos que no le corresponden
        Mesero mesero = new Mesero();
        mesero.atenderMesa();
    }
}
