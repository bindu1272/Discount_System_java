import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerVisitTest{
    private static CustomerVisit customerVisit;
    @BeforeClass
    public static void initCustomer() throws SQLException {
        customerVisit = new CustomerVisit();
        customerVisit.dbConnection();
        customerVisit.createCustomers();
        customerVisit.createProducts();
        customerVisit.createServices();
    }
    @Test
    public void checkCustomerVisit() throws SQLException {
        float totalCost = 0;
        Customer customer = customerVisit.getCustomer(101);
        totalCost += customerVisit.getProductById(202);
        totalCost += customerVisit.getServiceById(customer,301,totalCost);
        assertEquals(716.0,totalCost);
    }
    @Test
    public  void checkProducts() throws SQLException {
        List<Product> product = customerVisit.getProduct();
        assertEquals("def",product.get(1).getProductName());
    }
    @Test
    public  void checkServices() throws SQLException {
        List<Service> service = customerVisit.getService();
        assertEquals(500,service.get(2).getServiceCost());
    }
    @Test
    public void checkProductCost() throws SQLException {
        float totalCost = 0;
        Customer customer = customerVisit.getCustomer(101);
        totalCost += customerVisit.getProductById(202);
        assertEquals(344.0,totalCost);
    }
    @Test
    public void checkServiceCost() throws SQLException {
        float totalCost = 0;
        Customer customer = customerVisit.getCustomer(104);
        totalCost += customerVisit.getServiceById(customer,302,totalCost);
        assertEquals(400,totalCost);
    }
}
