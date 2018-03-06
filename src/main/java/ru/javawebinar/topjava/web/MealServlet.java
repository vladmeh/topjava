package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MemoryMealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

    private MealRepository repository;

    @Override
    public void init() throws ServletException {
        super.init();
        repository = new MemoryMealRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        Meal meal = new Meal((id.isEmpty()) ? null : Long.valueOf(id),
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.valueOf(req.getParameter("calories"))
        );

        LOG.info((id.isEmpty())? "create new meal" : "update: {}", id);
        repository.save(meal);
        resp.sendRedirect("meals");
    }

    /**
     * @see <a href="http://www.java2s.com/Tutorials/Java_Date_Time/java.time/LocalDateTime/LocalDateTime_truncatedTo_TemporalUnit_unit_example.htm">LocalDateTime.now().truncatedTo(ChronoUnit.HOURS)</a>
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        String id = req.getParameter("id");

        switch (action == null ? "all" : action) {
            case "delete":

                LOG.info("delete: {}", id);
                repository.delete(Long.valueOf(id));
                resp.sendRedirect("meals");
                break;
            case "create":
            case "update":
                Meal meal = (id == null)
                        ? new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS), "", 1000)
                        : repository.findOne(Long.valueOf(id));
                req.setAttribute("meal", meal);
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
