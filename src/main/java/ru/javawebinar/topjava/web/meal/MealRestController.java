package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {

    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal) {
        int userId = AuthorizedUser.id();
        log.info("create {} for user {}", meal, userId);
        checkNew(meal);
        return service.create(userId, meal);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete {} for user {}", id, userId);
        service.delete(userId, id);
    }

    public Meal get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get {} for user {}", id, userId);
        return service.get(userId, id);
    }

    public void update(Meal meal, int id) {
        int userId = AuthorizedUser.id();
        log.info("update {} with id={} for user {}", meal, id, userId);
        assureIdConsistent(meal, id);
        service.update(userId, meal);
    }

    public List<MealWithExceed> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealWithExceed> getFilterDateTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        int userId = AuthorizedUser.id();
        log.info("getFilterDateTime");

        List<Meal> mealList = service.getFilterDateTime(userId,
                startDate == null ? LocalDateTime.MIN : LocalDateTime.of(startDate, LocalTime.MIN),
                endDate == null ? LocalDateTime.MAX : LocalDateTime.of(endDate, LocalTime.MAX)
        );

        return MealsUtil.getFilteredWithExceeded(mealList, MealsUtil.DEFAULT_CALORIES_PER_DAY,
                startTime == null ? LocalTime.MIN : startTime,
                endTime == null ? LocalTime.MAX : endTime
        );
    }

}