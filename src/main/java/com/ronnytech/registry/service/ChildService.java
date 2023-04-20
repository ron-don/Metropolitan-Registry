package com.ronnytech.registry.service;

import com.ronnytech.registry.model.Child;
import com.ronnytech.registry.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildService {
    private final ChildRepository childRepository;

    // fetch all children method
    public List<Child> getAllChildren() {

        List<Child> children = new ArrayList<>();

        childRepository.findAll().forEach(children::add);

        return children;
    }

    // add a child method
    public void addChild(Child child) {
        childRepository.save(child);
    }

    // edit/update child details method
    public boolean updateChild(Child child) {
        childRepository.save(child);
        return true;
    }

    public List<Child> getAllChildren(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = (Pageable) PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Child> pagedResult = childRepository.findAll((org.springframework.data.domain.Pageable) paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Child>();
        }
    }

    // search child by name method
//    public List<Child> searchChild(String name) {
//        List<Child> result = new ArrayList<>();
//        for (Child c : children) {
//            if (c.getName().equalsIgnoreCase(name)) {
//                result.add(c);
//            }
//        }
//        if (result.isEmpty()) {
//            return
//        }
//
//    }
}
