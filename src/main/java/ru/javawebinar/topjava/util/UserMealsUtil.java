package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    static Map<LocalDate, Integer> mapDay = new HashMap<>();

    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> results = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();


        resultString(results);
        System.out.println();

        List<UserMealWithExceed> resultsRec = getFilteredWithExceeded_Rec(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

        resultString(resultsRec);


    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> list = new ArrayList<>();
        for (UserMeal um : mealList) {
            if (TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime)) {
                UserMealWithExceed userMealWithExceed = new UserMealWithExceed(
                        um.getDateTime(),
                        um.getDescription(),
                        um.getCalories(),
                        um.getCalories() <= caloriesPerDay);
                list.add(userMealWithExceed);
            }
        }
        return list;
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded_Rec(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> list = new ArrayList<>();

        mapDay.merge(mealList.get(0).getDateTime().toLocalDate(), mealList.get(0).getCalories(), (a, b) -> a + b);
        if (mealList.size() > 1) {
            list.addAll(getFilteredWithExceeded_Rec(mealList.subList(1, mealList.size()),
                    startTime, endTime, caloriesPerDay));
        }
        LocalTime time = mealList.get(0).getDateTime().toLocalTime();
        if (TimeUtil.isBetween(time, startTime, endTime)) {
            list.add(new UserMealWithExceed(
                    mealList.get(0).getDateTime(),
                    mealList.get(0).getDescription(),
                    mealList.get(0).getCalories(),
                    mapDay.get(mealList.get(0).getDateTime().toLocalDate()) <= caloriesPerDay));
        }
        return list;
    }


    public static void resultString(List<UserMealWithExceed> results){
        for (UserMealWithExceed result:
                results) {
            System.out.println(result.toString());
        }
    }
}
