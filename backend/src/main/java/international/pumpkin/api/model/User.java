package international.pumpkin.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "Username")
    private String name;

    @Column(name = "Password")
    private String pwHash;

    @Column(name = "ColorPassword")
    private String colorHash;

    @Column(name = "IsNewUser")
    private boolean isNewUser;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPwHash() {
        return pwHash;
    }

    public String getColorHash() {
        return colorHash;
    }

    public boolean isNewUser() {
        return isNewUser;
    }
}