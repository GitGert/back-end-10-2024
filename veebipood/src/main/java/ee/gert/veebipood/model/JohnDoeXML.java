package ee.gert.veebipood.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "root")
public class JohnDoeXML {

//    @XmlElement(name = "city")
    public String city;
//    @XmlElement(name = "firstName")
    public String firstName;
//    @XmlElement(name = "lastName")
    public String lastName;
//    @XmlElement(name = "state")
    public String state;
}
