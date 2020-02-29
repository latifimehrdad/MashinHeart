package ir.clinicemashin.clinicemashin.databases;

import java.util.Date;

import io.realm.RealmObject;
import ir.clinicemashin.clinicemashin.models.ModelPosition;

public class DataBasePositions extends RealmObject {

    private Date PositionDate;
    private Double PositonLat;
    private Double PositionLong;

    public void DataBasePositions(ModelPosition position){
        PositionDate = position.getPositionDate();
        PositonLat = position.getPositonLat();
        PositionLong = position.getPositionLong();
    }

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
