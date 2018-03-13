package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface MealRepository {
    Meal save(int userId, Meal meal);

    boolean delete(int userId, int mealId);

    Meal get(int userId, int mealId);

    List<Meal> getAll(int userId);

    List<Meal> getFilterDateTime(int userId, LocalDateTime startDate, LocalDateTime endDate);
}
