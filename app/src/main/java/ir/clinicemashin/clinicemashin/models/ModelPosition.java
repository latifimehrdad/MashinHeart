package ir.clinicemashin.clinicemashin.models;

import java.util.Date;

public class ModelPosition {

    private Date PositionDate;
    private Double PositonLat;
    private Double PositionLong;

    public Date getPositionDate() {
        return PositionDate;
    }

    public void setPositionDate(Date positionDate) {
        PositionDate = positionDate;
    }

    public Double getPositonLat() {
        return PositonLat;
    }

    public void setPositonLat(Double positonLat) {
        PositonLat = positonLat;
    }

    public Double getPositionLong() {
        return PositionLong;
    }

    public void setPositionLong(Double positionLong) {
        PositionLong = positionLong;
    }
}
