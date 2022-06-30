package com.example.librarypet.DAO.Impl;

import com.example.library.DAO.AuthorDAO;
import com.example.library.Entity.Author;
import com.example.library.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService implements AuthorDAO {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> search(String... strings) {
        return authorRepository.findByFioContainingIgnoreCaseOrderByFio(strings[0]);
    }

    @Override
    public Author get(long id) {
        Optional<Author> bookmark = authorRepository.findById(id);
        if(bookmark.isPresent()){
            return bookmark.get();
        }else
            return null;

    }

    @Override
    public Author save(Author obj) {
        return authorRepository.save(obj);
    }

    @Override
    public void delete(Author author) {
        authorRepository.delete(author);
    }

    @Override
    public Page<Author> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return authorRepository.findAll(pageRequest);
    }

    @Override
    public Page<Author> search(int pageNumber, int pageSize, String... searchStr) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return authorRepository.findByFioContainingIgnoreCaseOrderByFio(searchStr[0],pageRequest);
    }


}
