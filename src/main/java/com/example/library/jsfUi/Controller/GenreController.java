package com.example.library.jsfUi.Controller;


import com.example.library.DAO.GenreDAO;
import com.example.library.Entity.Genre;
import com.example.library.jsfUi.model.LazyDataTable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import java.util.List;

@ManagedBean
@SessionScope
@Component
@Getter@Setter
public class GenreController extends AbstractController<Genre>{

    // из JSF таблицы обязательно должна быть ссылки на переменные, иначе при использовании постраничности dataTable работает некорректно
    // также - выбранное пользователем значение (кол-во записей на странице) будет сохраняться
    private int rowsCount = 20;
    private int first;

    @Autowired
    private GenreDAO genreDAO;

    private Genre selectedGenre;

    private LazyDataTable<Genre> lazyModel;


    private Page<Genre> genrePages;


    @PostConstruct
    public void init(){
        lazyModel = new LazyDataTable(this);
    }

    public List<Genre> getAll(){
        return genreDAO.getAll();
    }





    @Override
    public Page<Genre> search(int first, int count, String sortField, Sort.Direction sortDirection) {
        return genrePages;
    }
}
