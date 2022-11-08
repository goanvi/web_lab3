package goanvi.prog.lab3.Service;

import goanvi.prog.lab3.DAO.MarkDAO;
import goanvi.prog.lab3.DTO.MarkDTO;
import goanvi.prog.lab3.model.Mark;
import goanvi.prog.lab3.utils.TransactionCallable;
import goanvi.prog.lab3.utils.TransactionManager;
import org.hibernate.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MarkService {
    private final MarkDAO markDAO;
    private final SimpleDateFormat sdf;
    public MarkService(){
        this.markDAO = new MarkDAO();
        sdf = new SimpleDateFormat("HH:mm:ss");
    }

    public void addMark(Mark mark){
        TransactionManager.doInTransaction(new TransactionCallable<Mark>() {
            @Override
            public Mark execute(Session session){
                long leadTime = mark.getLeadTime();
                mark.setLeadTime((System.nanoTime()-leadTime)/1000);
                markDAO.create(mark,session);
                return mark;
            }
        });
    }

    public void removeAllMark(){
        TransactionManager.doInTransaction(new TransactionCallable<Mark>() {
            @Override
            public Mark execute(Session session){
                markDAO.deleteAll(session);
                return null;
            }
        });

    }

    public List<MarkDTO> getMarks(){
        List<MarkDTO> marksDTO = new ArrayList<>();
        List<Mark> marks = TransactionManager.doInTransaction(new TransactionCallable<List<Mark>>() {
            @Override
            public List<Mark> execute(Session session){
                return markDAO.findAll(session);
            }
        });
        marks.forEach(mark -> {
            MarkDTO markDTO = new MarkDTO(
                    mark.getXValue(),
                    mark.getYValue(),
                    mark.getRValue(),
                    mark.getHit(),
                    sdf.format(mark.getTime()),
                    mark.getLeadTime()

            );
            marksDTO.add(markDTO);
        });
        return marksDTO;
    }

    public void createStorage(){
        TransactionManager.doInTransaction(new TransactionCallable<Void>() {
           @Override
           public Void execute(Session session) {
                markDAO.createTable(session);
               return null;
           }
        });
    }
}
