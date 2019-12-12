package ir.clinicemashin.clinicemashin.models;


import ir.clinicemashin.clinicemashin.viewmodels.FragmentAddCarViewModel;

public class ModelAddCar {
    private Integer CarBodyInsurance;
    private int CarBrand;
    private String CarChassisNumber;
    private String CarColor;
    private Integer CarKm;
    private Integer CarPersonInsurance;
    private int CarTagIran;
    private int CarTagLeft;
    private String CarTagLetter;
    private int CarTagRight;
    private String CarType;
    private int CarUseAverage;
    private int CarYearCreated;
    private Boolean Gearbox;
    private Integer ID;
    private Integer LastChangeDate;

    public ModelAddCar(FragmentAddCarViewModel addCarViewModel) {
        this.ID = addCarViewModel.getID();
        this.CarBrand = addCarViewModel.getCarBrand();
        this.CarYearCreated = addCarViewModel.getCarYearCreated();
        this.CarUseAverage = addCarViewModel.getCarUseAverage();
        this.CarTagLeft = addCarViewModel.getCarTagLeft();
        this.CarTagRight = addCarViewModel.getCarTagRight();
        this.CarTagIran = addCarViewModel.getCarTagIran();
        this.Gearbox = addCarViewModel.getGearbox();
        this.CarType = addCarViewModel.getCarType();
        this.CarColor = addCarViewModel.getCarColor();
        this.CarPersonInsurance = addCarViewModel.getCarPersonInsurance();
        this.CarBodyInsurance = addCarViewModel.getCarBodyInsurance();
        this.CarChassisNumber = addCarViewModel.getCarChassisNumber();
        this.CarTagLetter = addCarViewModel.getCarTagLetter();
        this.CarKm = Integer.valueOf(addCarViewModel.getCarKm());
        this.LastChangeDate = addCarViewModel.getLastChangeDate();
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID2) {
        this.ID = ID2;
    }

    public int getCarBrand() {
        return this.CarBrand;
    }

    public void setCarBrand(int carBrand) {
        this.CarBrand = carBrand;
    }

    public int getCarYearCreated() {
        return this.CarYearCreated;
    }

    public void setCarYearCreated(int carYearCreated) {
        this.CarYearCreated = carYearCreated;
    }

    public int getCarUseAverage() {
        return this.CarUseAverage;
    }

    public void setCarUseAverage(int carUseAverage) {
        this.CarUseAverage = carUseAverage;
    }

    public int getCarTagLeft() {
        return this.CarTagLeft;
    }

    public void setCarTagLeft(int carTagLeft) {
        this.CarTagLeft = carTagLeft;
    }

    public int getCarTagRight() {
        return this.CarTagRight;
    }

    public void setCarTagRight(int carTagRight) {
        this.CarTagRight = carTagRight;
    }

    public int getCarTagIran() {
        return this.CarTagIran;
    }

    public void setCarTagIran(int carTagIran) {
        this.CarTagIran = carTagIran;
    }

    public Boolean getGearbox() {
        return this.Gearbox;
    }

    public void setGearbox(Boolean gearbox) {
        this.Gearbox = gearbox;
    }

    public String getCarType() {
        return this.CarType;
    }

    public void setCarType(String carType) {
        this.CarType = carType;
    }

    public String getCarColor() {
        return this.CarColor;
    }

    public void setCarColor(String carColor) {
        this.CarColor = carColor;
    }

    public Integer getCarPersonInsurance() {
        return this.CarPersonInsurance;
    }

    public void setCarPersonInsurance(Integer carPersonInsurance) {
        this.CarPersonInsurance = carPersonInsurance;
    }

    public Integer getCarBodyInsurance() {
        return this.CarBodyInsurance;
    }

    public void setCarBodyInsurance(Integer carBodyInsurance) {
        this.CarBodyInsurance = carBodyInsurance;
    }

    public String getCarChassisNumber() {
        return this.CarChassisNumber;
    }

    public void setCarChassisNumber(String carChassisNumber) {
        this.CarChassisNumber = carChassisNumber;
    }

    public String getCarTagLetter() {
        return this.CarTagLetter;
    }

    public void setCarTagLetter(String carTagLetter) {
        this.CarTagLetter = carTagLetter;
    }

    public Integer getCarKm() {
        return this.CarKm;
    }

    public void setCarKm(Integer carKm) {
        this.CarKm = carKm;
    }

    public Integer getLastChangeDate() {
        return this.LastChangeDate;
    }

    public void setLastChangeDate(Integer lastChangeDate) {
        this.LastChangeDate = lastChangeDate;
    }
}
