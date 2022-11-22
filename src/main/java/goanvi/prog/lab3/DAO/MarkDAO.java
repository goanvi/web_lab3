package goanvi.prog.lab3.DAO;

import goanvi.prog.lab3.model.Mark;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
        return session.createQuery("from Mark", Mark.class).getResultList();
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

    public List<Mark> getRange(int first, int range, Session session){
        return session.createQuery("from Mark mark order by mark.id" , Mark.class).setFirstResult(first).setMaxResults(range).getResultList();
    }

    public Long getNumberOfElements(Session session){
        return (Long) session.createQuery("select count(mark) from Mark mark").uniqueResult();
    }

    public List<Mark> findByLeadTimeAndTime(Session session, String currentTime, Long leadTime){
        Query query;
        if (currentTime==null && leadTime==null) {
            query = session.createQuery("from Mark");
        } else if (currentTime == null){
            query = session.createQuery("from Mark mark where mark.leadTime = :leadTime", Mark.class)
                    .setParameter("leadTime",leadTime);
        } else if (leadTime == null) {
            query = session.createQuery("from Mark mark where mark.time = :time", Mark.class)
                    .setParameter("time", currentTime);
        }else {
            query = session.createQuery("from Mark mark where mark.time = :time and mark.leadTime = :leadTime", Mark.class)
                    .setParameter("time",currentTime).setParameter("leadTime",leadTime);
        }
        return query.getResultList();

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
                "    time     varchar          not null," +
                "    leadtime bigint           not null" +
                ");").executeUpdate();
    }
}
