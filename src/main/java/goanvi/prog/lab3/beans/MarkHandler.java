package goanvi.prog.lab3.beans;


import goanvi.prog.lab3.DTO.MarkDTO;
import goanvi.prog.lab3.Service.MarkService;
import goanvi.prog.lab3.model.LazyMarkDataModel;
import goanvi.prog.lab3.model.Mark;
import lombok.*;
import org.icefaces.ace.model.table.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "markHendler")
@SessionScoped
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MarkHandler implements Serializable {
    @ManagedProperty(value = "#{mark}")
    private MarkMaker markMaker;
    private List<MarkDTO> marks;

    private LazyDataModel<MarkDTO> lazyMarks;
    @PostConstruct
    public void init() {
        lazyMarks = new LazyMarkDataModel(markService);
    }
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private final MarkService markService;

    public MarkHandler(){
        this.markService = new MarkService();
    }

    public void addMark(){
        Mark mark = markMaker.makeNewMark();
        markService.addMark(mark);
    }

    public List<MarkDTO> getMarks(){
        marks = markService.getMarks();
        return marks;
    }

    public void clearStorage(){
        markService.removeAllMark();
    }

    public void createStorage(){
        markService.createStorage();
    }
}
