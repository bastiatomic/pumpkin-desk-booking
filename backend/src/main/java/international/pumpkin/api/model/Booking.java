package international.pumpkin.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @Column(name = "ID")
    int id;

    @Column(name = "BookedUSER")
    int userID;

    @Column(name = "Desk_ID")
    int deskID;

    @Column(name = "BookingStartTime")
    Timestamp startTime;

    @Column(name = "BookingEndTime")
    Timestamp endTime;

    @Column(name = "BookingUsed")
    boolean isActivated;

    public int getDeskID() {
        return deskID;
    }

    public void setDeskID(int deskID) {
        this.deskID = deskID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }



}