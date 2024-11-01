package ee.gert.veebipood.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class Token {
    private String token;
    private Date expiration;
}
