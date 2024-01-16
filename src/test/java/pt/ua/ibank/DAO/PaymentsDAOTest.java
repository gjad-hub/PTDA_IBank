package pt.ua.ibank.DAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.PagamentoServicosCompras;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PaymentsDAOTest {

    @Test
    void testPayService() {
       int result = PaymentsDAO.payService(1, 123, 789);
       assertEquals(PaymentsDAO.codigoSucesso, result);
    }

    @Test
    void getServicosCompras() {
        PagamentoServicosCompras psc = PaymentsDAO.getServicosCompras(789, 123);
        assertNotNull(psc);
    }

    @Test
    void getServicosComprasFalse() {
        PagamentoServicosCompras psc = PaymentsDAO.getServicosCompras(9999999, 999999);
        assertNull(psc);
    }

    @Test
    public void testGetAllServicosSuccess() {
        ArrayList<PagamentoServicosCompras> pagamentos = PaymentsDAO.getAllServicos(5);
        assertNotNull(pagamentos);
        assertFalse(pagamentos.isEmpty());
    }

    @Test
    public void testCreatePaymentWithValidData() {
        int cliente = 1;
        int entidade = 123;
        int referencia = 456;
        double valor = 100.0;
        int resultado = PaymentsDAO.createPayment(cliente, entidade, referencia, valor);
        assertEquals(PaymentsDAO.codigoSucesso, resultado);
    }

    @Test
    public void testCreatePaymentWithInvalidData() {
        int cliente = 0;
        int entidade = -1;
        int referencia = -1;
        double valor = -100.0;
        int resultado = PaymentsDAO.createPayment(cliente, entidade, referencia, valor);
        assertEquals(PaymentsDAO.codigoErro, resultado);
    }

    @Test
    public void testGetAllServicosIDClientNonExistent() {
        ArrayList<PagamentoServicosCompras> pagamentos = PaymentsDAO.getAllServicos(99999);
        assertNull(pagamentos);
    }

    @Test
    public void testCancelPaymentWithValidData() {
        int resultado = PaymentsDAO.cancelPayment(123, 789);
        assertEquals(PaymentsDAO.codigoSucesso, resultado);
    }
}