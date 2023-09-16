package com.dev.fullstackdemo.test;

import com.dev.fullstackdemo.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Object, Long> {
}
