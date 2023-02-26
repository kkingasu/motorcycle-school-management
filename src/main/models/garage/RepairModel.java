package main.models.garage;

import java.util.Date;

;
public class RepairModel {

    String repairID;
    String VIN;
    Date problemDate;
    Date repairDate;
    String problemDesc;
    boolean isOperational;
    float repairCost;

    public void setRepairID(String repairID) {
        this.repairID = repairID;
    }

    public String getRepairID() {
        return this.repairID;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getVIN() {
        return this.VIN;
    }

    public void setProblemDate(Date problemDate) {
        this.problemDate = problemDate;
    }

    public Date getProblemDate() {
        return this.problemDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public Date getRepairDate() {
        return this.repairDate;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc;
    }

    public String getProblemDesc() {
        return this.problemDesc;
    }

    public void setOperationalStatus(boolean status) {
        this.isOperational = status;
    }
    public boolean getOperationalStatus() {
        return this.isOperational;
    }
    public void setRepairCost(float repairCost) {
        this.repairCost = repairCost;
    }

    public float getRepairCost() {
        return this.repairCost;
    }
}
