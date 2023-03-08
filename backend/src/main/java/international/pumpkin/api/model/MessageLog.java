package international.pumpkin.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Entity
@Table(name = "MessageLog")
public class MessageLog {

    @Id
    @Column(name = "ID")
    int id;

    @Column(name = "Desk_ID")
    int desk_id;

    @Column(name = "TimeStamp")
    Timestamp TimeStamp;

    @Column(name = "value")
    long value;
}
