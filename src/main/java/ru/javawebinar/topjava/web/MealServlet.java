package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.debug("get List<MealWithExceed>");
        List<MealWithExceed> mealList = MealsUtil.getFilteredWithExceeded(MealsUtil.MEALS, LocalTime.of(0, 0, 0), LocalTime.of(23, 59, 59), MealsUtil.CALORIES_PER_DAY);

        LOG.debug("set attribute mealList");
        req.setAttribute("mealList", mealList);

        LOG.debug("forward meals");
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
