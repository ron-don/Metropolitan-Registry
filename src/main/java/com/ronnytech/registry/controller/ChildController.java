package com.ronnytech.registry.controller;

import com.ronnytech.registry.model.Child;
import com.ronnytech.registry.service.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChildController {

    private final ChildService childService;

    @PostMapping("/addchild")
    public ResponseEntity<String> addChild(@RequestBody Child child) {
        childService.addChild(child);
        return ResponseEntity.ok("Child added successfully");
    }

    @PutMapping("/editchild")
    public ResponseEntity<String> editChild(@RequestBody Child child) {
        if (childService.updateChild(child)) {
            return ResponseEntity.ok("Child details updated successfully");
        }

        return ResponseEntity.badRequest().body("Child with registration number " + child.getRegistrationNumber() + " not found");

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
