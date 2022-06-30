package com.example.library.DAO.Impl;

import com.example.library.DAO.BookDAO;
import com.example.library.Entity.Book;
import com.example.library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class BookService implements BookDAO {

    @Autowired
    BookRepository bookRepository;


    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> search(String... strings) {
        return null;
    }

    @Override
    public Book get(long id) {
        Optional<Book> book = bookRepository.findById(id);

        if(book.isPresent()){
            return book.get();
        }else
            return null;
    }

    @Override
    public Book save(Book obj) {

        bookRepository.save(obj);

        if(obj.getContent()!=null){
            bookRepository.updateContent(obj.getContent(), obj.getId());
        }

        return obj;
    }

    @Override
    public void delete(Book obj) {
        bookRepository.delete(obj);
    }

    @Override
    public Page<Book> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        return bookRepository.findAll(pageRequest);
    }

    @Override
    public Page<Book> search(int pageNumber, int pageSize, String... searchStr) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        return bookRepository.findByNameContainingIgnoreCaseOrAuthorFioContainingIgnoreCaseOrderByName(searchStr[0],searchStr[0],pageRequest);
    }



    @Override
    public List<Book> findTopBooks(int limit) {
        PageRequest pageRequest = PageRequest.of(0,limit);
        return bookRepository.findTopBooks(pageRequest);
    }

    @Override
    public byte[] getContent(long id) {
        return bookRepository.getContent(id);
    }


    @Override
    public Page<Book> findByGenre(int pageNumber, int pageSize, int genreId) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return  bookRepository.findByGenre(genreId,pageRequest);
    }

    @Override
    public Page<Book> findAllBook(Pageable pageable){
        return bookRepository.findAll(pageable);
    }




    @Override
    public void updateViewCount(long viewCount, long id) {
        bookRepository.updateViewCount(viewCount,id);
    }

    @Override
    public void updateRating(long totalRating, long totalVoteCount, int avgRating, long id) {
        bookRepository.updateRating(totalRating,totalVoteCount,avgRating,id);
    }


}
