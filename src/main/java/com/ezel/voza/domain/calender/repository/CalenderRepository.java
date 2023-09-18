package com.ezel.voza.domain.calender.repository;

import com.ezel.voza.domain.calender.entity.Calender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalenderRepository extends JpaRepository<Calender, Long> {
}
