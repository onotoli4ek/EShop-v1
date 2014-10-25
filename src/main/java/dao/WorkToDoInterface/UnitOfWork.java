package dao.WorkToDoInterface;

public interface UnitOfWork<T, E extends Exception> {

    public T doInTx() throws E;

}