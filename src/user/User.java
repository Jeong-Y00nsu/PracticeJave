package user;

import java.io.Serializable;

public class User implements Serializable {

    private String id;
    private String pw;

    private String name;

    private String planFileUrl;

    private String salt;

    public User(){}
    public User(String id, String pw, String name, String planListId, String salt) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.planFileUrl = planListId;
        this.salt = salt;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }

    public String getPlanFileUrl() {
        return planFileUrl;
    }

    public String getSalt() {return salt;}

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlanFileUrl(String planFileUrl) {
        this.planFileUrl = planFileUrl;
    }

    public void setSalt(String selt){
        this.salt = selt;
    }
}
