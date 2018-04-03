package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    // 4: Если у метода нет реализации, то стандартно бросается UnsupportedOperationException.
    // 5: Для уменьшения количества кода при реализации Optional (п. 7, только DataJpa) попробуйте сделать default метод в интерфейсе
    default User getUserMeals(int id) {
        throw new UnsupportedOperationException();
    }
}