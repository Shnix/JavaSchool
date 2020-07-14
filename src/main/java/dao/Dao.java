package dao;

import java.util.List;

interface Dao<T> {
    void add(T t);

    void delete(T t);

    void update(T t);

    List<T> list();

    T getById(int id);
}
