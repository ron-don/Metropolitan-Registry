package com.ronnytech.registry.repository;

import com.ronnytech.registry.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Long> {
}
