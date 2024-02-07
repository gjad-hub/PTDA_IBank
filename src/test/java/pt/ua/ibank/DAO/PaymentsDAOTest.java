package pt.ua.ibank.DAO;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pt.ua.ibank.DTO.PaymentServices;
import static pt.ua.ibank.utilities.Configs.SUCCESS_CODE;
import static pt.ua.ibank.utilities.Configs.ERROR_CODE;

class PaymentsDAOTest {

    @Test
    void testPayService() {
        int result = PaymentsDAO.payService(1, 123, 789);
        assertEquals(SUCCESS_CODE, result);
    }

    @Test
    void getServicosCompras() {
        PaymentServices psc = PaymentsDAO.getServicosCompras(567567567, 22789);
        assertNotNull(psc);
    }

    @Test
    void getServicosComprasFalse() {
        PaymentServices psc = PaymentsDAO.getServicosCompras(9999999,
                                                                      999999);
        assertNull(psc);
    }

    @Test
    public void testGetAllServicosSuccess() {
        ArrayList<PaymentServices> pagamentos =
                                            PaymentsDAO.getAllServicos(5);
        assertNotNull(pagamentos);
        assertFalse(pagamentos.isEmpty());
    }

    @Test
    public void testCreatePaymentWithValidData() {
        int cliente = 1;
        int entidade = 123;
        int referencia = 456;
        double valor = 100.0;
        int resultado = PaymentsDAO.createPayment(cliente, entidade, referencia,
                                                  valor);
        assertEquals(SUCCESS_CODE, resultado);
    }

    @Test
    public void testCreatePaymentWithInvalidData() {
        int cliente = 0;
        int entidade = -1;
        int referencia = -1;
        double valor = -100.0;
        int resultado = PaymentsDAO.createPayment(cliente, entidade, referencia,
                                                  valor);
        assertEquals(ERROR_CODE, resultado);
    }

    @Test
    public void testGetAllServicosIDClientNonExistent() {
        ArrayList<PaymentServices> pagamentos =
                                            PaymentsDAO.getAllServicos(99999);
        assertNull(pagamentos);
    }

    @Test
    public void testCancelPaymentWithValidData() {
        int resultado = PaymentsDAO.cancelPayment(123, 789);
        assertEquals(SUCCESS_CODE, resultado);
    }
}
