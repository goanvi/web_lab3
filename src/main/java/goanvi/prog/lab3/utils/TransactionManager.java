package goanvi.prog.lab3.utils;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

public class TransactionManager {
    public static <T> T doInTransaction(TransactionCallable<T> callable) throws TransactionException {
        T result = null;
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = callable.execute(session);
            transaction.commit();
        } catch (RuntimeException exception) {
            if (transaction != null && transaction.isActive()) transaction.rollback();
            throw new TransactionException("Transaction error", exception);
        }
        return result;
    }
}
