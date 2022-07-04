package com.example.library.jsfUi.model;

import com.example.library.jsfUi.Controller.AbstractController;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import javax.persistence.SecondaryTable;
import java.util.List;
import java.util.Map;



@Getter@Setter
public class LazyDataTable<T> extends LazyDataModel<T> {

    private AbstractController<T> abstractController;

    public LazyDataTable(AbstractController<T> abstractController){
        this.abstractController = abstractController;
    }


    @Override
    public int count(Map<String, FilterMeta> map) {
        return 0;
    }

    @Override
    public List<T> load(int i, int i1, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
        return null;
    }


    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        int pageNumber = first / pageSize;

        Sort.Direction sortDirection = Sort.Direction.ASC;

        if(sortOrder != null ){
            switch (sortOrder){
                case DESCENDING:
                    sortDirection = Sort.Direction.DESC;
                    break;
            }
        }
        Page<T>searchResult = abstractController.search(pageNumber,pageSize,sortField,sortDirection);

        this.setRowCount((int) searchResult.getTotalElements());

        return searchResult.getContent() ;
    }
}
