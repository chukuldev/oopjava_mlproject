public class Data {
    private String gardenType;
    private String bedType;
    private String propertyType;
    private String leaseType;

    public Data (String gardenType, String bedType, String propertyType, String leaseType){
        setGardenType(gardenType);
        setBedType(bedType);
        setPropertyType(propertyType);
        setLeaseType(leaseType);
    }

    public String getGardenType() {
        return gardenType;
    }

    public void setGardenType(String gardenType) {
        this.gardenType = gardenType;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public void setLeaseType(String leaseType) {
        this.leaseType = leaseType;
    }
}
