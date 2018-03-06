package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private MealRepository repository;

    @Override
    public void init() throws ServletException {
        super.init();
        repository = new MealRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                Long.valueOf(req.getParameter("id")),
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.valueOf(req.getParameter("calories"))
        );
        repository.save(meal);
        resp.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                Long id = Long.valueOf(req.getParameter("id"));
                LOG.info("Delete {}", id);
                repository.delete(id);
                resp.sendRedirect("meals");
                break;
            case "create":
            case "update":
                id = Long.valueOf(req.getParameter("id"));
                LOG.info("Update {}", id);
                req.setAttribute("meal", repository.findOne(id));
                req.getRequestDispatcher("/mealForm.jsp").forward(req, resp);
            case "all":
            default:
                LOG.info("findAll");
                List<MealWithExceed> mealList = MealsUtil.getFilteredWithExceeded(repository.findAll(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY);
                req.setAttribute("mealList", mealList);
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
                break;
        }
    }
}
