package com.ronnytech.registry.controller;

import com.ronnytech.registry.model.Child;
import com.ronnytech.registry.repository.ChildRepository;
import com.ronnytech.registry.service.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ChildController {

    private final ChildService childService;
    private List<Child> children =  new ArrayList<>();
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
//    @GetMapping("/allchildren")
//    public List<Child> getAllChildren() {
//        return childService.getAllChildren();
//    }

    @GetMapping("/allchildren")
    public ResponseEntity<List<Child>> getAllChildren(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "registrationNumber") String sortBy)
    {
        List<Child> list = childService.getAllChildren(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Child>>(list, new HttpHeaders(), HttpStatus.OK);
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Child>> searchChild(@RequestParam String name) {
//        List<Child> result = new ArrayList<>();
//        for (Child c : children) {
//            if (c.getName().equalsIgnoreCase(name)) {
//                result.add(c);
//            }
//        }
//        if (result.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(result);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
