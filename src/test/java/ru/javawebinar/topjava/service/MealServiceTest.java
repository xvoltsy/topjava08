package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    protected MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(MEAL1.getId(), USER_ID);
        MEAL_MATCHER.assertEquals(meal, MEAL1);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL1.getId(), USER_ID);
        MEAL_MATCHER.assertCollectionEquals(service.getAll(USER_ID), Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2));
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        MEAL_MATCHER.assertCollectionEquals(Arrays.asList(MEAL3, MEAL2, MEAL1),
                service.getBetweenDates(LocalDate.of(2015, Month.MAY, 30), LocalDate.of(2015, Month.MAY, 30), USER_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        MEAL_MATCHER.assertCollectionEquals(USER_MEALS, service.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal meal = service.get(MEAL1.getId(), USER_ID);
        meal.setCalories(5000);
        meal.setDescription("Банкет");
        service.save(meal, USER_ID);
        Assert.assertEquals(service.get(MEAL1.getId(), USER_ID).getDescription(), "Банкет");
        Assert.assertEquals(service.get(MEAL1.getId(), USER_ID).getCalories(), 5000);
    }

    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.now(), "Полдник", 1500);
        Meal created = service.save(newMeal, USER_ID);
        int newMealId = created.getId();
        newMeal.setId(newMealId);
        MEAL_MATCHER.assertEquals(newMeal, service.get(newMealId, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testSaveForWrongUser() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.now(), "Полдник", 1500);
        Meal created = service.save(newMeal, ADMIN_ID);
        int newMealId = created.getId();
        newMeal.setId(newMealId);
        Assert.assertNull(service.get(newMealId, USER_ID));
    }


}
