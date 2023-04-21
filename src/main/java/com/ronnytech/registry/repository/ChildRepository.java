package com.ronnytech.registry.repository;

import com.ronnytech.registry.model.Child;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {

    List<Child> findChildrenByRegistrationNumber(long registrationNumber, Pageable pageable);

    List<Child> findByNameContainingIgnoreCase(String name);
}
