package international.pumpkin.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Entity
@Table(name = "DeskUsage")
public class Statistics {

    @Id
    @Column(name = "ID")
    int id;

    @Column(name = "Time")
    Timestamp timeStamp;

    @Column(name = "Message")
    String message;

    @Column(name = "Author")
    String author;

}