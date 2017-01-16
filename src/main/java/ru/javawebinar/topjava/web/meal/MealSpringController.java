package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Controller
public class MealSpringController {

    private static final Logger LOG = LoggerFactory.getLogger(MealSpringController.class);

    @Autowired
    private MealService service;

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    private String meals(Model model) {
        LOG.info("getAll");
        model.addAttribute("meals", MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }

    @RequestMapping(value = "meals/delete", method = RequestMethod.GET)
    private String deleteMeal(@RequestParam("id") int mealId) {
        LOG.info("Delete {}", mealId);
        service.delete(mealId, AuthorizedUser.id());
        return "redirect:/meals";
    }

    @RequestMapping(value = "meals/create", method = RequestMethod.GET)
    private String createMeal(Model model) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "", 1000);
        model.addAttribute("meal", meal);
        return "meal";
    }

    @RequestMapping(value = "meals/update", method = RequestMethod.GET)
    private String updateMeal(@RequestParam("id") int mealId, Model model) {
        Meal meal = service.get(mealId, AuthorizedUser.id());
        model.addAttribute("meal", meal);
        return "meal";
    }

    @RequestMapping(method = RequestMethod.POST)
    private String addMeal(@RequestParam("id") int mealId, HttpServletRequest request) {
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));
        if (meal.isNew()) {
            LOG.info("Create {}", meal);
            service.save(meal, mealId);
        } else {
            LOG.info("Update {}", meal);
            service.update(meal, mealId);
        }

        return "redirect:/meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

}
