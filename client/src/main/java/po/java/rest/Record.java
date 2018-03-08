package po.java.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Record {

    private long id;
    private String depart;
    private  String descrition;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    @Override
    public String toString() {
        return "Record dublicate {" +
                "id=" + id +
                ", depart='" + depart + '\'' +
                ", descrition='" + descrition + '\'' +
                '}';
    }
}
