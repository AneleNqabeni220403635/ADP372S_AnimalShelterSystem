package za.ac.cput.service;

import java.util.Set;

public interface IService <T, ID> {
    T create (T t);
    T read (ID id);
    T update(T t);
    Set<T> findAll();
    boolean delete(ID id);
}
