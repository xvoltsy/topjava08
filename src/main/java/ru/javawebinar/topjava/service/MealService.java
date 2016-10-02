package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {

    Collection<Meal> getAll();

    Meal get(int id, int userId);

    void save(Meal meal);

    void delete(int id, int userId);
}
