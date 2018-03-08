/*
 * File : Record.java
 * Description : Record bean
 *
 * Author : Popov Denys
 * Created : 26/02/18
 *
 * Modified : { date: 28/02/18
 *             ,time: 10:51 PM }
 * Modified by: Popov Denys
 *
 * Last modification : Record bean -> Entity
 */

package po.po.domain;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "Record")
@XmlRootElement
public class Record implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String depart;
    private  String descrition;

    public Record() {}

    public Record(String depart, String descrition) {
        this.depart = depart;
        this.descrition = descrition;
    }

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
        return "Record{" +
                "id=" + id +
                ", depart='" + depart + '\'' +
                ", descrition='" + descrition + '\'' +
                '}';
    }
}
