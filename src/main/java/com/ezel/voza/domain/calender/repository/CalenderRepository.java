package com.ezel.voza.domain.calender.repository;

import com.ezel.voza.domain.calender.entity.Calender;
import com.ezel.voza.domain.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalenderRepository extends JpaRepository<Calender, Long> {

    List<Calender> findAllByGroupAndDate(Group group, String date);
}
