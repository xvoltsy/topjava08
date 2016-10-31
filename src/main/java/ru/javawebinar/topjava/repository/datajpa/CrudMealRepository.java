package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * gkislin
 * 02.10.2016
 */
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Query("UPDATE Meal m SET m=:meal WHERE m.user.id=:userId")
    Meal save(@Param("meal") Meal meal, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    Meal findOne(@Param("id") Integer id, @Param("userId") int userId);

    @Query("SELECT m FROM Meal m WHERE m.id=:userId")
    List<Meal> findAll(@Param("userId") int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startTime AND :endTime ORDER BY m.dateTime DESC")
    List<Meal> getBetween(@Param("startDate") LocalDateTime startTime,
                          @Param("endDate") LocalDateTime endTime,
                          @Param("userId") int userId);
}
