package ir.clinicemashin.clinicemashin.models;


import ir.clinicemashin.clinicemashin.viewmodels.FragmentRepairViewModel;

public class ModelRepair {
    private String Brand;
    private Integer CarId;
    private Integer ID;
    private Integer Money;
    private Integer RepairDate;
    private Integer RepairKm;
    private String RepairWhy;
    private String Title;

    public ModelRepair(FragmentRepairViewModel viewModel) {
        this.ID = viewModel.getID();
        this.CarId = viewModel.getCarId();
        this.RepairKm = viewModel.getRepairKm();
        this.RepairWhy = viewModel.getRepairWhy();
        this.Money = viewModel.getMoney();
        this.Brand = viewModel.getBrand();
        this.Title = viewModel.getTitle();
        this.RepairDate = viewModel.getRepairDate();
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID2) {
        this.ID = ID2;
    }

    public Integer getCarId() {
        return this.CarId;
    }

    public void setCarId(Integer carId) {
        this.CarId = carId;
    }

    public Integer getRepairKm() {
        return this.RepairKm;
    }

    public void setRepairKm(Integer repairKm) {
        this.RepairKm = repairKm;
    }

    public String getRepairWhy() {
        return this.RepairWhy;
    }

    public void setRepairWhy(String repairWhy) {
        this.RepairWhy = repairWhy;
    }

    public Integer getMoney() {
        return this.Money;
    }

    public void setMoney(Integer money) {
        this.Money = money;
    }

    public String getBrand() {
        return this.Brand;
    }

    public void setBrand(String brand) {
        this.Brand = brand;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public Integer getRepairDate() {
        return this.RepairDate;
    }

    public void setRepairDate(Integer repairDate) {
        this.RepairDate = repairDate;
    }
}
