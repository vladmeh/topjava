package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(userMeal -> save(1, userMeal));

        this.save(2, new Meal(LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0)), "Завтрак", 1000));
        this.save(2, new Meal(LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 0)), "Обед", 700));
        this.save(2, new Meal(LocalDateTime.of(LocalDate.now(), LocalTime.of(20, 0)), "Ужин", 500));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        /*Map<Integer, Meal> mealsOfUser = repository.get(userId) == null
                ? new ConcurrentHashMap<>()
                : repository.get(userId);*/

        //https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html#computeIfAbsent-K-java.util.function.Function-
        Map<Integer, Meal> mealsOfUser = repository.computeIfAbsent(userId, newMeals -> new ConcurrentHashMap<>());

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            mealsOfUser.put(meal.getId(), meal);
            //repository.put(userId, mealsOfUser);

            return meal;
        }

        // treat case: update, but absent in storage
        return mealsOfUser.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int mealId) {
        Map<Integer, Meal> mealsOfUser = repository.get(userId);

        if (mealsOfUser.get(mealId) == null) return false;

        mealsOfUser.remove(mealId);
        return true;
    }

    @Override
    public Meal get(int userId, int mealId) {
        Map<Integer, Meal> mealsOfUser = repository.get(userId);
        if (mealsOfUser == null) return null;

        return mealsOfUser.get(mealId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        Map<Integer, Meal> mealsOfUser = repository.get(userId);

        if (mealsOfUser == null) return null;

        return mealsOfUser.values().stream().sorted(
                Comparator.comparing(Meal::getDateTime)
                        .reversed()
        ).collect(Collectors.toList());
    }
}

