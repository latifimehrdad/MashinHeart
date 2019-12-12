package ir.clinicemashin.clinicemashin.models;


import ir.clinicemashin.clinicemashin.viewmodels.FragmentInsuranceViewModel;

public class ModelInsurance {
    private Integer CarId;
    private Integer InsuranceDate;
    private int InsuranceType;
    private Integer Money;
    private String Title;

    public ModelInsurance(FragmentInsuranceViewModel fragmentInsuranceViewModel) {
        this.InsuranceType = fragmentInsuranceViewModel.getInsuranceType();
        this.Title = fragmentInsuranceViewModel.getTitle();
        this.Money = fragmentInsuranceViewModel.getMoney();
        this.InsuranceDate = fragmentInsuranceViewModel.getInsuranceDate();
        this.CarId = fragmentInsuranceViewModel.getCarId();
    }

    public int getInsuranceType() {
        return this.InsuranceType;
    }

    public void setInsuranceType(int insuranceType) {
        this.InsuranceType = insuranceType;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public Integer getMoney() {
        return this.Money;
    }

    public void setMoney(Integer money) {
        this.Money = money;
    }

    public Integer getInsuranceDate() {
        return this.InsuranceDate;
    }

    public void setInsuranceDate(Integer insuranceDate) {
        this.InsuranceDate = insuranceDate;
    }

    public Integer getCarId() {
        return this.CarId;
    }

    public void setCarId(Integer carId) {
        this.CarId = carId;
    }
}
