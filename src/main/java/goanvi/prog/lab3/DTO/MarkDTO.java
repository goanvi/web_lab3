package goanvi.prog.lab3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
@Data
@AllArgsConstructor
public class MarkDTO {
    private double xValue;
    private double yValue;
    private double rValue;
    private String hit;
    private String time;
    private long leadTime;
}
