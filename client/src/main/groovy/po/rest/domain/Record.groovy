package po.rest.domain

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlRootElement

@XmlAccessorType(XmlAccessType.FIELD)   // only for Groovy
@XmlRootElement
class Record {

    private long id
    private String depart
    private  String descrition

    @Override
    public String toString() {
        return "Record dublicate {" +
                "id=" + id +
                ", depart='" + depart + '\'' +
                ", descrition='" + descrition + '\'' +
                '}'
    }
}
