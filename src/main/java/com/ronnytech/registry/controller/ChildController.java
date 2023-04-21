package com.ronnytech.registry.controller;

import com.ronnytech.registry.model.Child;
import com.ronnytech.registry.repository.ChildRepository;
import com.ronnytech.registry.service.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/children")
@RequiredArgsConstructor
public class ChildController {

    private final ChildService childService;
//    private List<Child> children =  new ArrayList<>();
    private final ChildRepository childRepository;

    @PostMapping("/addchild")
    public ResponseEntity<Child> addChild(@RequestBody Child child) {
        childService.addChild(child);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/editchild/{registrationNumber}")
    public ResponseEntity<Child> editChild(@PathVariable Long registrationNumber, @RequestBody Child child) {

        return (!childRepository.existsById(registrationNumber))
                ? new ResponseEntity<>(childRepository.save(child),
                HttpStatus.CREATED)
                : new ResponseEntity<>(childRepository.save(child), HttpStatus.OK);
    }
    @GetMapping("/allchildren")
    public List<Child> getAllChildren() {
        return childService.getAllChildren();
    }

//    @GetMapping("/allchildren")
//    public ResponseEntity<List<Child>> getAllChildren(
//            @RequestParam(defaultValue = "0") Integer pageNo,
//            @RequestParam(defaultValue = "10") Integer pageSize)
//    {
//        List<Child> list = childService.getAllChildren(pageNo, pageSize);
//
//        return new ResponseEntity<List<Child>>(list, new HttpHeaders(), HttpStatus.OK);
//    }

//    @GetMapping("/search/{name}")
//    public ResponseEntity<Page<Child>> searchChildrenByName(@RequestParam(defaultValue = "") String name,
//                                                               @RequestParam(defaultValue = "0") int page,
//                                                               @RequestParam(defaultValue = "10") int per_page) {
//        Page<Child> children = childService.searchChildrenByName(name, (Pageable) PageRequest.of(page, per_page));
//        return ResponseEntity.ok(children);
//    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Child>> searchChild(@PathVariable String name) {
        List<Child> result = childService.searchChild(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
