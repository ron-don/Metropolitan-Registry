package com.ronnytech.registry.service;

import com.ronnytech.registry.model.Child;
import com.ronnytech.registry.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

//    public Page<Child> searchChildrenByName(String name, Pageable pageable) {
//        Page<Child> children = childRepository.findByNameContainingIgnoreCase(name, pageable);
//        return children.map(child -> new Child(child));
//    }

//    public List<Child> getAllChildren(Integer pageNo, Integer pageSize)
//    {
//        Pageable pageable = (Pageable) PageRequest.of(pageNo, pageSize);
//
//        Page<Child> pagedResult = childRepository.findAll((org.springframework.data.domain.Pageable) pageable);
//
//        if(pagedResult.hasContent()) {
//            return pagedResult.getContent();
//        } else {
//            return new ArrayList<Child>();
//        }
//    }

//     search child by name method
    public List<Child> searchChild(String name) {
        return childRepository.findByNameContainingIgnoreCase(name);
    }
}
