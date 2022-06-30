package com.example.library.DAO.Impl;

import com.example.library.DAO.GenreDAO;
import com.example.library.Entity.Genre;
import com.example.library.Repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class GenreService implements GenreDAO {

    @Autowired
    GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> search(String... strings) {
        return genreRepository.findByNameContainingIgnoreCaseOrderByName(strings[0]);
    }

    @Override
    public Genre get(long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if(genre.isPresent()){
            return genre.get();

        }else
            return null;

    }

    @Override
    public Genre save(Genre obj) {
        return genreRepository.save(obj);
    }

    @Override
    public void delete(Genre obj) {
        genreRepository.delete(obj);
    }

    @Override
    public Page<Genre> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        return genreRepository.findAll(pageRequest);
    }

    @Override
    public Page<Genre> search(int pageNumber, int pageSize, String... searchStr) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        return genreRepository.findByNameContainingIgnoreCaseOrderByName(searchStr[0],pageRequest);
    }


}
