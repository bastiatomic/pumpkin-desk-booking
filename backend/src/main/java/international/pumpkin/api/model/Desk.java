package international.pumpkin.api.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DeskBaseData")
public class Desk {

    @Id
    @Column(name = "ID")
    int id;

    @Column(name = "Name")
    String name;

    @Column(name = "Floor")
    int floor;

    @Column(name = "Blocked")
    int isBlocked;

    @Column(name = "Equipment")
    String equipment;

    @Column(name = "MaxUser")
    int maxUser;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFloor() {
        return floor;
    }

    public boolean isBlocked() {
        return isBlocked==1;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setBlocked(boolean blocked) {
        if (blocked)
            isBlocked = 1;
        else isBlocked = 0;
    }

    public int getMaxUser() {
        return maxUser;
    }
}