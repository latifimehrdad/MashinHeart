package ir.clinicemashin.clinicemashin.models;


public class ModelPoliceFine {
    private String Bill;
    private String City;
    private String Code;
    private String Date;
    private String Discription;
    private String Location;
    private String Pay;
    private Integer Price;
    private String Serial;
    private String Type;

    public ModelPoliceFine(String type, String discription, String code, Integer price, String location, String city, String date, String serial, String pay, String bill) {
        this.Type = type;
        this.Discription = discription;
        this.Code = code;
        this.Price = price;
        this.Location = location;
        this.City = city;
        this.Date = date;
        this.Serial = serial;
        this.Pay = pay;
        this.Bill = bill;
    }


    public String getType() {
        return this.Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getDiscription() {
        return this.Discription;
    }

    public void setDiscription(String discription) {
        this.Discription = discription;
    }

    public String getCode() {
        return this.Code;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    public Integer getPrice() {
        return this.Price;
    }

    public void setPrice(Integer price) {
        this.Price = price;
    }

    public String getLocation() {
        return this.Location;
    }

    public void setLocation(String location) {
        this.Location = location;
    }

    public String getCity() {
        return this.City;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public String getDate() {
        return this.Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getSerial() {
        return this.Serial;
    }

    public void setSerial(String serial) {
        this.Serial = serial;
    }

    public String getPay() {
        return this.Pay;
    }

    public void setPay(String pay) {
        this.Pay = pay;
    }

    public String getBill() {
        return this.Bill;
    }

    public void setBill(String bill) {
        this.Bill = bill;
    }
}
