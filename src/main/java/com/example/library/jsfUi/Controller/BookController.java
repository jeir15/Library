package com.example.library.jsfUi.Controller;


import com.example.library.DAO.BookDAO;
import com.example.library.Entity.Book;
import com.example.library.jsfUi.enums.SearchType;
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
public class BookController extends AbstractController<Book> {

    // по-умолчанию сколько книг отображать на странице
    public static final int DEFAULT_PAGE_SIZE = 20;

    // из JSF таблицы обязательно должна быть ссылки на переменные, иначе при использовании постраничности dataGrid работает некорректно (не отрабатывает bean)
    // также - выбранное пользователем значение (кол-во записей на странице) будет сохраняться

    private int rowsCount = DEFAULT_PAGE_SIZE;


    // сколько показывать популярных книг
    public static final int TOP_BOOKS_LIMIT = 5;


    // запоминает последний выбранный вариант поиска
    private SearchType searchType;


    // будет автоматически подставлен BookService, т.к. Spring контейнер по-умолчанию ищет бин-реализацию по типу
    @Autowired
    private BookDAO bookDAO;

    // класс-утилита, которая помогает выводить данные постранично (работает в паре с компонентами на странице JSF)
    private LazyDataTable<Book> lazyModel;

    //хранит список найденных книг
    private Page<Book> bookPages;

    // хранит полученные ТОП книги (может использоваться наприемр для получения изображений книги)
    private List<Book> topBooks;

    @PostConstruct
    public void init(){
        lazyModel = new LazyDataTable(this);
    }


    @Override
    public Page<Book> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        if(sortField == null){
            sortField="name";
        }

        if(searchType == null){
            bookPages = bookDAO.getAll(pageNumber, pageSize);
        }else {
            switch (searchType){
                case SEARCH_GENRE:
                    break;
                case SEARCH_TEXT:
                    break;
                case ALL:
                    bookPages = bookDAO.getAll(pageNumber,pageSize);
                    break;
            }
        }
        return bookPages;
    }



    public List<Book> getTopBooks(){
        topBooks = bookDAO.findTopBooks(TOP_BOOKS_LIMIT);
        return topBooks;
    }

}
