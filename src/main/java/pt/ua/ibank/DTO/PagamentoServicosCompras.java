package pt.ua.ibank.DTO;

public class PagamentoServicosCompras {

   public int referencia;
   public int entidade;
   public double valor;
   public boolean pago;
   public int cliente;
   public int cliente_cria;
   public boolean cancelada;

    public PagamentoServicosCompras(int referencia, int entidade, double valor, boolean pago, int cliente, int cliente_cria, boolean cancelada) {
        this.referencia = referencia;
        this.entidade = entidade;
        this.valor = valor;
        this.pago = pago;
        this.cliente = cliente;
        this.cliente_cria = cliente_cria;
        this.cancelada = cancelada;
    }
}
