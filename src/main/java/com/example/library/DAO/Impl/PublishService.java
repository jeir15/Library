package com.example.library.DAO.Impl;

import com.example.library.DAO.PublisherDAO;
import com.example.library.Entity.Publisher;
import com.example.library.Repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class PublishService implements PublisherDAO {

    @Autowired
    PublisherRepository publisherRepository;

    @Override
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @Override
    public List<Publisher> search(String... strings) {
        return publisherRepository.findAllByNameContainingIgnoreCaseOrderByName(strings[0]);
    }

    @Override
    public Publisher get(long id) {
        Optional<Publisher> publisher = publisherRepository.findById(id);
        if(publisher.isPresent()){
            return publisher.get();
        }else
            return null;

    }

    @Override
    public Publisher save(Publisher obj) {
        return publisherRepository.save(obj);
    }

    @Override
    public void delete(Publisher obj) {
        publisherRepository.delete(obj);
    }

    @Override
    public Page<Publisher> getAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        return publisherRepository.findAll(pageRequest);
    }

    @Override
    public Page<Publisher> search(int pageNumber, int pageSize, String... searchStr) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        return publisherRepository.findByNameContainingIgnoreCaseOrderByName(searchStr[0],pageRequest);
    }
}
