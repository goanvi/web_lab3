package goanvi.prog.lab3.DAO;

import org.hibernate.Session;

import java.util.List;

public interface BaseDAO<T> {
    void create(T value, Session session);
    void update(T value, Session session);
    List<T> findAll(Session session);
    void deleteById(long id, Session session);

    T findById(long id, Session session);

    void deleteAll(Session session);
}
