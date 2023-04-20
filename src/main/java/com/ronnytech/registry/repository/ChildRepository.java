package com.ronnytech.registry.repository;

import com.ronnytech.registry.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

    List<Child> findChildrenByRegistrationNumber(long registrationNumber, Pageable pageable);
}
