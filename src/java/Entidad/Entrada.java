package Entidad;


public class Entrada {
    private int id_entrada;
    private int igv;
    private int descuento;
    private int serie;
    private String fecha;
    private int id_proveedor;
    private int numero_comp;
    private int id_comprobante;
    private int estado;
    private int id_usuario;
    private int id_forma_pago;

    public Entrada() {
    }

    public int getId_Entrada() {
        return id_entrada;
    }

    public void setId_Entrada(int id_entrada) {
        this.id_entrada = id_entrada;
    }

    public int getIgv() {
        return igv;
    }

    public void setIgv(int igv) {
        this.igv = igv;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getNumero_comp() {
        return numero_comp;
    }

    public void setNumero_comp(int numero_comp) {
        this.numero_comp = numero_comp;
    }

    public int getId_comprobante() {
        return id_comprobante;
    }

    public void setId_comprobante(int id_comprobante) {
        this.id_comprobante = id_comprobante;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_forma_pago() {
        return id_forma_pago;
    }

    public void setId_forma_pago(int id_forma_pago) {
        this.id_forma_pago = id_forma_pago;
    }
}
