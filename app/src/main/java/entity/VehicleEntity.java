package entity;

/**
 * @package entity
 * @fileName VehicleEntity
 * @Author Bob on 2018/5/14 11:29.
 * @Describe TODO
 */

public class VehicleEntity {

    private int id;
    private String vehicleNum;
    private String vehicleWeight;
    private int isCheck;
    private String checkInDate;
    private String checkOutDate;
    private String driverName;
    private String vehicleDepartment;
    private String qrCode;

    public VehicleEntity() {
    }

    public int getId() {
        return id;
    }

    public VehicleEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public VehicleEntity setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
        return this;
    }

    public String getVehicleWeight() {
        return vehicleWeight;
    }

    public VehicleEntity setVehicleWeight(String vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
        return this;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public VehicleEntity setIsCheck(int isCheck) {
        this.isCheck = isCheck;
        return this;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public VehicleEntity setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
        return this;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public VehicleEntity setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public VehicleEntity setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public String getVehicleDepartment() {
        return vehicleDepartment;
    }

    public VehicleEntity setVehicleDepartment(String vehicleDepartment) {
        this.vehicleDepartment = vehicleDepartment;
        return this;
    }

    public String getQrCode() {
        return qrCode;
    }

    public VehicleEntity setQrCode(String qrCode) {
        this.qrCode = qrCode;
        return this;
    }
}
