import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CustomerVisit {
    static  CustomerVisit customerVisit = new CustomerVisit();
    static Connection connection = null;
    static PreparedStatement preparedStatement = null;
    public void dbConnection(){
        try{
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");
                    System.out.println("connection successfull");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public  void createCustomers() throws SQLException {
        try {
            preparedStatement = connection.prepareStatement("DROP DATABASE IF EXISTS discount");
            preparedStatement.execute();
            preparedStatement.execute("create database discount");
            preparedStatement.execute("use discount");
            preparedStatement.execute("drop table  if exists customer_details cascade");
            preparedStatement.execute("create table customer_details(cId int,cName varchar(50),phoneNumber varchar(15),membership varchar(15))");
            String sql = "insert into customer_details(cId,cName,phoneNumber,membership) values (?,?,?,?)";
                    preparedStatement = connection.prepareStatement(sql);
            //        preparedStatement.setInt(1,101);
            //        preparedStatement.setString(2,"bindu");
            //        preparedStatement.setString(3,"65456789");
            //        preparedStatement.setString(4,"platinum");
            //        preparedStatement.addBatch();
            //        preparedStatement.setInt(1,102);
            //        preparedStatement.setString(2,"jyothi");
            //        preparedStatement.setString(3,"98765487");
            //        preparedStatement.setString(4,"diamond");
            //        preparedStatement.addBatch();
            //        preparedStatement.executeBatch();
            List<Customer> customers = new ArrayList<>();
            customers.add(new Customer(101, "bindu", "56787698", "platinum"));
            customers.add(new Customer(102, "jyothi", "456876", "platinum"));
            customers.add(new Customer(103, "padma", "56787456", "diamond"));
            customers.add(new Customer(104, "varma", "56787698", "others"));
            customers.add(new Customer(105, "binduhdj", "56787698", "diamond"));
            for (Customer customer : customers) {
                preparedStatement.setInt(1, customer.getId());
                preparedStatement.setString(2, customer.getName());
                preparedStatement.setString(3, customer.getPhoneNumber());
                preparedStatement.setString(4, customer.getMembership());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void createProducts() throws SQLException {
        try {
            preparedStatement.execute("use discount");
            preparedStatement.execute("drop table if exists product_details");
            preparedStatement.execute("create table product_details(productId int,productName varchar(50),productCost int)");
            preparedStatement = connection.prepareStatement("insert into product_details(productId,productName,productCost) values(?,?,?)");
            List<Product> products = new ArrayList<>();
            products.add(new Product(201, "abc", 200));
            products.add(new Product(202, "def", 400));
            products.add(new Product(203, "ghi", 500));
            products.add(new Product(204, "jkl", 2000));
            products.add(new Product(205, "lmn", 2050));
            for (Product product : products) {
                preparedStatement.setInt(1, product.getProductId());
                preparedStatement.setString(2, product.getProductName());
                preparedStatement.setInt(3, product.getProductCost());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void createServices() throws SQLException {
        try {
            preparedStatement.execute("use discount");
            preparedStatement.execute("drop table if exists service_details");
            preparedStatement.execute("create table service_details(serviceId int,serviceName varchar(50),serviceCost int)");
            preparedStatement = connection.prepareStatement("insert into service_details(serviceId,serviceName,serviceCost) values(?,?,?)");
            List<Service> services = new ArrayList<>();
            services.add(new Service(301, "abc", 200));
            services.add(new Service(302, "def", 400));
            services.add(new Service(303, "ghi", 500));
            services.add(new Service(304, "jkl", 2000));
            services.add(new Service(305, "lmn", 2050));
            for (Service service: services) {
                preparedStatement.setInt(1, service.getServiceId());
                preparedStatement.setString(2, service.getServiceName());
                preparedStatement.setInt(3, service.getServiceCost());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Customer getCustomer(int customerId) throws SQLException {
        String sql = "select * from customer_details WHERE cId = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,customerId);
        ResultSet rs = preparedStatement.executeQuery();
        Customer customer = new Customer();
        while (rs.next()) {
            int id = rs.getInt("cId");
            String name = rs.getString("cName");
            String number = rs.getString("phoneNumber");
            String membership = rs.getString("membership");
            customer.setId(id);
            customer.setName(name);
            customer.setPhoneNumber(number);
            customer.setMembership(membership);
        }
        return customer;
    }
    public  List<Product> getProduct() throws SQLException {
        String sql = "select * from product_details";
        ResultSet rs = preparedStatement.executeQuery(sql);
        List<Product> products = new ArrayList<>();
        while (rs.next()){
            int productId = rs.getInt("productId");
            String productName = rs.getString("productName");
            int productCost = rs.getInt("productCost");
            Product product = new Product(productId,productName,productCost);
            products.add(product);
        }
        return products;
    }
    public float getProductById(int productId) throws SQLException {
        String sql = "select * from product_details where productId = ? limit 1";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,productId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Product product = new Product();
        while (resultSet.next()){
            product.setProductId(resultSet.getInt("productId"));
            product.setProductName(resultSet.getString("productName"));
            product.setProductCost(resultSet.getInt("productCost"));
        }
        return product.getProductCost() - ((product.getProductCost()/100)*14);
//        return product;
    }
    public  List<Service> getService() throws SQLException {
        String sql = "select * from service_details";
        ResultSet rs = preparedStatement.executeQuery(sql);
        List<Service> services = new ArrayList<>();
        while (rs.next()){
            int serviceId = rs.getInt("serviceId");
            String serviceName = rs.getString("serviceName");
            int serviceCost = rs.getInt("serviceCost");
            Service service = new Service(serviceId,serviceName,serviceCost);
            services.add(service);
        }
        return services;
    }
    public float getServiceById(Customer customer,int serviceId,float totalCost) throws SQLException {
        Discount discount = new Discount();
        String sql = "select * from service_details where serviceId = ? limit 1";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,serviceId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Service service = new Service();
        while (resultSet.next()){
            service.setServiceId(resultSet.getInt("serviceId"));
            service.setServiceName(resultSet.getString("serviceName"));
            service.setServiceCost(resultSet.getInt("serviceCost"));
        }
        if(customer.getMembership().equals("platinum")){
            totalCost += (service.getServiceCost()/100) * discount.getPlatinum();
        }else if(customer.getMembership().equals("diamond")){
            totalCost += (service.getServiceCost()/100) * discount.getDiamond();
        }else{
            totalCost += service.getServiceCost();
        }
//        return service;
        return totalCost;
    }
        public static  void main(String args[]) throws SQLException {
        customerVisit.dbConnection();
        customerVisit.createCustomers();
        customerVisit.createProducts();
        customerVisit.createServices();
        System.out.print("Enter Customer Id: ");
        Scanner sc = new Scanner(System.in);
        int customerId = sc.nextInt();
        Customer customer = customerVisit.getCustomer(customerId);
        if(customer != null){
            System.out.println("Welcome "+customer.getName());
            System.out.println("1:Product 2:Service 3:quit");
            int option=sc.nextInt();
            float totalCost = 0;
            do{
                switch (option){
                    case 1 :
                        List<Product> products = customerVisit.getProduct();
                        System.out.println("productId\tproductName\tproductCost");
                        for(Product product : products){
                            System.out.print(product.getProductId()+"\t");
                            System.out.print(product.getProductName()+"\t");
                            System.out.println(product.getProductCost());
                        }
                        System.out.print("Enter ProductId: ");
                        int productId = sc.nextInt();
//                        Product product = customerVisit.getProductById(productId);
//                        totalCost += product.getProductCost() - ((product.getProductCost()/100)*14);
                        totalCost += customerVisit.getProductById(productId);
                        break;
                    case 2:
                        List<Service> services = customerVisit.getService();
                        System.out.println("serviceId\tserviceName\tserviceCost");
                        for(Service service : services){
                            System.out.print(service.getServiceId()+"\t");
                            System.out.print(service.getServiceName()+"\t");
                            System.out.println(service.getServiceCost());
                        }
                        System.out.print("Enter ServiceId: ");
                        int serviceId = sc.nextInt();
//                        Service service = customerVisit.getServiceById(serviceId);
                        totalCost += customerVisit.getServiceById(customer,serviceId,totalCost);
                        break;
                }
                System.out.println("1:Product 2:Service 3:quit");
                option=sc.nextInt();
            }while(option < 3);
            System.out.println("Total Bill: "+totalCost);
            System.out.println("Thank You for visiting");
        }
    }
}
