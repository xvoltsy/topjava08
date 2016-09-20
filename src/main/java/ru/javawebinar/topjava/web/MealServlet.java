package ru.javawebinar.topjava.web;


import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MealWithExceed> mealsWithExceed = MealsUtil.getFilteredWithExceeded(MealsUtil.MEALS, LocalTime.MIN, LocalTime.MAX, MealsUtil.DEFAULT_CALORIES_PER_DAY);

        req.setAttribute("mealList", mealsWithExceed);
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }
}
