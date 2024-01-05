package pt.ua.ibank.DTO;

public class PagamentoServicosCompras {

   public int referencia;
   public int entidade;
   public double valor;
   public boolean pago;
   public int cliente;

    public PagamentoServicosCompras(int referencia, int entidade, double valor, boolean pago, int cliente) {
        this.referencia = referencia;
        this.entidade = entidade;
        this.valor = valor;
        this.pago = pago;
        this.cliente = cliente;
    }
}
