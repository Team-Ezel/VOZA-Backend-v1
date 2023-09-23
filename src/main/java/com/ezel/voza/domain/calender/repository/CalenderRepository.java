package com.ezel.voza.domain.calender.repository;

import com.ezel.voza.domain.calender.entity.Calender;
import com.ezel.voza.domain.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CalenderRepository extends JpaRepository<Calender, Long> {

    List<Calender> findAllByGroupAndDate(Group group, String date);

    @Query("SELECT c FROM Calender c WHERE DATE(c.startDate) <= :date AND DATE(c.endDate) >= :date AND c.group = :group")
    List<Calender> findAll(@Param("group") Group group, @Param("date") LocalDate date);
}