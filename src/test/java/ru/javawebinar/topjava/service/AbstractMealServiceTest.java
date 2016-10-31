package ru.javawebinar.topjava.service;


import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMealServiceTest extends AbstractBaseTest {

    @Autowired
    protected MealService service;
}
