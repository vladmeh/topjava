package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(userMeal -> save(1, userMeal));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        Map<Integer, Meal> mealsOfUser = repository.get(userId);

        if (mealsOfUser == null)
            mealsOfUser = new ConcurrentHashMap<>();

        if (meal.isNew()){
            meal.setId(counter.incrementAndGet());
            mealsOfUser.put(meal.getId(), meal);
            repository.put(userId, mealsOfUser);

            return meal;
        }

        // treat case: update, but absent in storage
        return mealsOfUser.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public void delete(int userId, int mealId) {
        Map<Integer, Meal> mealsOfUser = repository.get(userId);

        if (mealsOfUser.get(mealId) == null) return;

        mealsOfUser.remove(mealId);
    }

    @Override
    public Meal get(int userId, int mealId) {
        Map<Integer, Meal> mealsOfUser = repository.get(userId);
        if (mealsOfUser == null) return null;

        return mealsOfUser.get(mealId);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Map<Integer, Meal> mealsOfUser = repository.get(userId);

        if (mealsOfUser == null) return null;

        return mealsOfUser.values().stream().sorted(
                Comparator.comparing(Meal::getDateTime)
                .reversed()
        ).collect(Collectors.toList());
    }
}

