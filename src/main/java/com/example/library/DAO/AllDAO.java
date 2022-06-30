package com.example.library.DAO;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AllDAO <T> {
    //Получение всех записей без постраничности
    List<T> getAll();

    //Поиск записей с любым количеством параметров
    List<T> search(String ... strings);

    //получение обьекта
    T get(long id);

    //сохранение
    T save(T obj);

    //удаление
    void delete(T obj);

    // Получение всех записей с постраничностью
    Page<T> getAll(int pageNumber, int pageSize);

    // поиск с постраничностью
    Page<T> search(int pageNumber, int pageSize, String ... searchStr);
}
