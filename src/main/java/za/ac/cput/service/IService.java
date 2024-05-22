package za.ac.cput.service;


public interface IService <T, ID> {
    T create (T entity);
    T read (ID id);
    T update(T entity);
    void delete(ID id);
}
