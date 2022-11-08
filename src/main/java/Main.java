import goanvi.prog.lab3.DTO.MarkDTO;
import goanvi.prog.lab3.Service.MarkService;
import goanvi.prog.lab3.model.Mark;
import goanvi.prog.lab3.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MarkService markService = new MarkService();
        Mark mark = new Mark(1,1,1,"hit",new Date(),1);
        markService.addMark(mark);
        markService.
        List<MarkDTO> marks = markService.getMarks();
        System.out.println(marks);
    }
}
