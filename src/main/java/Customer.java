public  class Customer {
    private int id;
    private String name;
    private String phoneNumber;
    private  String membership;
    public Customer() {
    }
    public Customer(int id, String name, String phoneNumber,String membership) {
        this.id = id;
        this.name = name;
        this.membership = membership;
        this.phoneNumber = phoneNumber;
    }

    public Customer(int cId) {
    }

    public Customer(String cName) {
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getMembership() {
        return membership;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}