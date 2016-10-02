package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.user.ProfileRestController;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {

    @Autowired
    private MealService service;
    @Autowired
    private ProfileRestController profileRestController;

    public Collection<Meal> getAll() {
        return service.getAll();
    };

    public Meal get(int id) {
        return service.get(id, profileRestController.get().getId());
    }

    public void save(Meal meal) {
        service.save(meal);
    }

    public void delete(int id) {
        service.delete(id, profileRestController.get().getId());
    }

}
