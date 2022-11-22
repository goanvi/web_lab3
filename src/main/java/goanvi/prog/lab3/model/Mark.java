package goanvi.prog.lab3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
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
    private String time;
    @Column
    private long leadTime;

    public Mark(double xValue, double yValue, double rValue, String hit, String time, long leadTime) {
        this.xValue = xValue;
        this.yValue = yValue;
        this.rValue = rValue;
        this.hit = hit;
        this.time = time;
        this.leadTime = leadTime;
    }
}
