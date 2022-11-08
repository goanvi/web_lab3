package goanvi.prog.lab3.utils;

import org.hibernate.Session;

public abstract class TransactionCallable<T> {
    public abstract T execute(Session session);
}
