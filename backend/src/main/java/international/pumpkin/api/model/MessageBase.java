package international.pumpkin.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "MessageBase")
public class MessageBase{

    @Id
    @Column(name = "ID")
    int id;

    @Column(name = "Content")
    String content;

    @Column(name = "Author")
    String author;
    
}