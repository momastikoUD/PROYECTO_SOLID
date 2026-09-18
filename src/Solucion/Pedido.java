package Solucion;
// ==========================================================
// Sistema de gestión de pedidos de un restaurante (versión CON violaciones)
// ==========================================================

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private String cliente;
    private String tipoCliente; // "REGULAR", "VIP", "EMPLEADO"
    private List<String> platos = new ArrayList<>();
    private List<Double> precios = new ArrayList<>();

    public void agregarPlato(String nombre, double precio) {
        platos.add(nombre);
        precios.add(precio);
    }

    // REVISAR (1): calcula el total Y decide el descuento con un if/else
    // que crece cada vez que el restaurante inventa un tipo de cliente nuevo.
    public double calcularTotal() {
        double subtotal = 0;
        for (double precio : precios) {
            subtotal += precio;
        }

        if (tipoCliente.equals("REGULAR")) {
            return subtotal;
        } else if (tipoCliente.equals("VIP")) {
            return subtotal * 0.9;
        } else if (tipoCliente.equals("EMPLEADO")) {
            return subtotal * 0.5;
        }
        return subtotal;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    public String getCliente(){
        return this.cliente;
    }
    public String getTipoCliente(){
        return this.tipoCliente;
    }
}

 // REVISAR (2): SOLUCIONADO

class PedidoRepositorio{
    public void guardarEnBaseDeDatos(Pedido pedido) {
        System.out.println("Conectando a la BD...");
        System.out.println("INSERT INTO pedidos VALUES (...)");
    }
}
class ImpresionRecibos {
    public void imprimirRecibo(Pedido pedido) {
        ImpresoraTermica impresora = new ImpresoraTermica();
        impresora.imprimir("Recibo de " + pedido.getCliente() + ": $" + pedido.calcularTotal());
    }
}
class EnviarCorreos{
    public void enviarCorreoConfirmacion(Pedido pedido) {
        System.out.println("Enviando correo de confirmación a " + pedido.getCliente() + "...");
    }

}

class ImpresoraTermica {
    public void imprimir(String texto) {
        System.out.println("[Impresora térmica] " + texto);
    }
}

// --- Métodos de pago ---

abstract class MetodoPago {
    public abstract boolean cobrar(double monto);
}

class PagoTarjeta extends MetodoPago {
    @Override
    public boolean cobrar(double monto) {
        System.out.println("Cobrando $" + monto + " con tarjeta.");
        return true;
    }
}

class PagoEfectivo extends MetodoPago {
    @Override
    public boolean cobrar(double monto) {
        System.out.println("Cobrando $" + monto + " en efectivo.");
        return true;
    }
}

// REVISAR (3): Solucionado.
class PagoPuntosFidelidad extends MetodoPago {
    private double puntosDisponibles = 20.0;

    @Override
    public boolean cobrar(double monto) {
        if (monto > puntosDisponibles) {
            System.out.println("No hay suficientes puntos para cobrar este monto.");
            return false;
        }
        System.out.println("Cobrando $" + monto + " con puntos de fidelidad.");
        return true;

    }
    /*     
            Este metodo incumplia la regla LSP ya que la clase puntosFIdelidad incumplia lo que promete la super clase.
            se modificó el método cobrar para que devuelva un booleano indicando si el cobro fue exitoso o no,
            en lugar de lanzar una excepción.
    */
}

// --- Personal del restaurante ---

// REVISAR (4): una sola interfaz para roles que no hacen lo mismo.
interface Empleado {
    void atenderMesa();

    void cocinar();

    void repartirPedido();

    void cobrarEnCaja();
}

class Mesero implements Empleado {
    @Override
    public void atenderMesa() {
        System.out.println("El mesero atiende la mesa.");
    }

    @Override
    public void cocinar() {
        throw new UnsupportedOperationException("Un mesero no cocina.");
    }

    @Override
    public void repartirPedido() {
        throw new UnsupportedOperationException("Un mesero no reparte a domicilio.");
    }

    @Override
    public void cobrarEnCaja() {
        throw new UnsupportedOperationException("Un mesero no cobra en caja.");
    }
}

class Cocinero implements Empleado {
    @Override
    public void atenderMesa() {
        throw new UnsupportedOperationException("Un cocinero no atiende mesas.");
    }

    @Override
    public void cocinar() {
        System.out.println("El cocinero prepara el plato.");
    }

    @Override
    public void repartirPedido() {
        throw new UnsupportedOperationException("Un cocinero no reparte pedidos.");
    }

    @Override
    public void cobrarEnCaja() {
        throw new UnsupportedOperationException("Un cocinero no cobra en caja.");
    }
}