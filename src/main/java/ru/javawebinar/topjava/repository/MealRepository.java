package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {
    // null if updated meal do not belong to userId
    Meal save(Meal meal, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Meal get(int id, int userId);

    // ORDERED dateTime desc
    List<Meal> getAll(int userId);

    // ORDERED dateTime desc
    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    // 4: Если у метода нет реализации, то стандартно бросается UnsupportedOperationException.
    // 5: Для уменьшения количества кода при реализации Optional (п. 7, только DataJpa) попробуйте сделать default метод в интерфейсе
    default Meal getMealUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
