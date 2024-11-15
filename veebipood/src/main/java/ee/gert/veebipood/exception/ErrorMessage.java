package ee.gert.veebipood.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class ErrorMessage {
    private String name;
    private Date date;
}
