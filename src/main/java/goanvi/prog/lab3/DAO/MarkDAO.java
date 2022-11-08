package goanvi.prog.lab3.DAO;

import goanvi.prog.lab3.model.Mark;
import goanvi.prog.lab3.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MarkDAO implements BaseDAO<Mark> {

    @Override
    public void create(Mark value, Session session) {
        session.save(value);
    }

    @Override
    public void update(Mark value, Session session) {
        session.merge(value);
        session.flush();
    }

    @Override
    public List<Mark> findAll(Session session) {
        return session.createNativeQuery("SELECT * FROM marks", Mark.class).getResultList();
    }

    @Override
    public void deleteById(long id, Session session) {
        session.createQuery("delete from goanvi.prog.lab3.model.Mark where id = :id", MarkDAO.class).setParameter("id", id).executeUpdate();
    }

    @Override
    public Mark findById(long id, Session session) {
       return session.get(Mark.class,id);
    }

    @Override
    public void deleteAll(Session session){
        session.createNativeQuery("TRUNCATE marks", Mark.class).executeUpdate();
    }

    public void createTable(Session session){
        session.createNativeQuery("create table if not exists marks" +
                "(" +
                "    id       bigserial" +
                "        primary key," +
                "    xvalue   double precision not null," +
                "    yvalue   double precision not null," +
                "    rvalue   double precision not null," +
                "    hit      varchar(4)       not null," +
                "    time     timestamp        not null," +
                "    leadtime bigint           not null" +
                ");").executeUpdate();
    }
}
