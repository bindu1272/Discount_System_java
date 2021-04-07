public class Service {
    private int serviceId;
    private String serviceName;
    private int serviceCost;

    public Service() {
    }

    public Service(int serviceId, String serviceName, int serviceCost) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.serviceCost = serviceCost;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(int serviceCost) {
        this.serviceCost = serviceCost;
    }
}
