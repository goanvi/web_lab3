package goanvi.prog.lab3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "marks")
public class Mark implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private double xValue;
    @Column
    private double yValue;
    @Column
    private double rValue;
    @Column
    private String hit;
    @Column
    private Date time;
    @Column
    private long leadTime;

    public Mark(double xValue, double yValue, double rValue, String hit, Date time, long leadTime) {
        this.xValue = xValue;
        this.yValue = yValue;
        this.rValue = rValue;
        this.hit = hit;
        this.time = time;
        this.leadTime = leadTime;
    }
}
