package goanvi.prog.lab3.beans;

import goanvi.prog.lab3.model.Mark;
import goanvi.prog.lab3.utils.HitMark;
import lombok.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@ManagedBean(name = "mark")
@SessionScoped
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MarkMaker implements Serializable {
    //String
    private double xValue;
    private Double yValue;
    private double rValue = 1;

    private double step;

    private double percent;

    private int position;

    private int rMin;

    private int rMax;
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private final HitMark hitMark;
    private final DateTimeFormatter timeFormatter;

    public MarkMaker() {
        this.position = 0;
        this.step= 0.5;
        this.percent =100d/6;
        this.rMin=1;
        this.rMax=4;
        hitMark = new HitMark();
        timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    public Mark makeNewMark(){
        long nanoTime = System.nanoTime();
        String hit = hitMark.hitMark(xValue, yValue, rValue)?"hit":"miss";
        LocalTime time = LocalTime.now();
        return new Mark(xValue,yValue,rValue,hit,time.format(timeFormatter),nanoTime);
    }

    public void setPosition(int position){
        this.position = position;
        this.rValue = getRValue();
    }

    public double getRValue() {
        return position*step + rMin;
    }
}
