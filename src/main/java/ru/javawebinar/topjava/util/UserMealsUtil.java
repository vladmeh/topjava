package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    private static Map<LocalDate, Integer> mapDay = new HashMap<>();

    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        setMapDay(mealList);

        List<UserMealWithExceed> results = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();

        System.out.println(resultString(results));
        System.out.println();

        List<UserMealWithExceed> resultsStream = getFilteredWithExceededByStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        System.out.println(resultsStream);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> list = new ArrayList<>();
        Iterator<UserMeal> iterator = mealList.iterator();
        iterator.forEachRemaining(userMeal -> {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                UserMealWithExceed userMealWithExceed = new UserMealWithExceed(
                        userMeal.getDateTime(),
                        userMeal.getDescription(),
                        userMeal.getCalories(),
                        mapDay.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay
                );
                list.add(userMealWithExceed);
            }
        });
        return list;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededByStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        return mealList.stream()
                .filter(userMeal -> TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .map(userMeal -> new UserMealWithExceed(
                        userMeal.getDateTime(),
                        userMeal.getDescription(),
                        userMeal.getCalories(),
                        mealList.stream()
                                .filter(um -> userMeal.getDateTime().toLocalDate().equals(um.getDateTime().toLocalDate()))
                                .mapToInt(UserMeal::getCalories)
                                .sum() > caloriesPerDay))
                .collect(Collectors.toList());
    }

    private static void setMapDay(List<UserMeal> mealList) {

        Iterator<UserMeal> iterator = mealList.iterator();
        iterator.forEachRemaining(userMeal ->
                mapDay.merge(
                        userMeal.getDateTime().toLocalDate(),
                        userMeal.getCalories(), (a, b) -> a + b
                )
        );
    }

    private static String resultString(List<UserMealWithExceed> results) {

        StringBuilder string = new StringBuilder();

        for (UserMealWithExceed result : results) {
            string.append(result.toString());
        }

        return string.toString();
    }
}
