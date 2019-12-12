package ir.clinicemashin.clinicemashin.utility;

public class Citys {

    private String Name;
    private double Lat;
    private double Long;


    public Citys(String name, double lat, double aLong) {
        Name = name;
        Lat = lat;
        Long = aLong;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLong() {
        return Long;
    }

    public void setLong(double aLong) {
        Long = aLong;
    }
}
