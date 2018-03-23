[![Build Status](https://travis-ci.org/vladmeh/topjava.svg?branch=master)](https://travis-ci.org/vladmeh/topjava)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f66f758b3974467083812ccb1e95054d)](https://www.codacy.com/app/vladmeh/topjava?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=vladmeh/topjava&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://www.versioneye.com/user/projects/5ab381f70fb24f44833013b0/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5ab381f70fb24f44833013b0)

## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Домашнее задание HW4

- 1: Сделать из `Meal` Hibernate entity
  - <a href="http://stackoverflow.com/questions/17137307">Hibernate Validator: @NotNull, @NotEmpty, @NotBlank</a>
  - <a href="https://en.wikibooks.org/wiki/Java_Persistence/ManyToOne">Реализация ManyToOne</a>
- 2: Имплементировать и протестировать `JpaMealRepositoryImpl`

#### Optional

- 3: Добавить в тесты `MealServiceTest` функциональность `@Rule`:
  - 3.1: проверку Exception
  - 3.2: вывод в лог времени выполнения каждого теста 
  - 3.3: вывод сводки в конце класса: имя теста - время выполнения 
-  <a href="https://github.com/junit-team/junit/wiki/Rules">JUnit @Rules</a>
-  <a href="http://blog.qatools.ru/junit/junit-rules-tutorial#expectedexcptn">замена ExpectedException</a>

---------------------
### ![error](https://cloud.githubusercontent.com/assets/13649199/13672935/ef09ec1e-e6e7-11e5-9f79-d1641c05cbe6.png) Подсказки по HW4
-  1: Тк. JPQL работает с объектами мы не можем использовать userId для сохранения. Можно сделать например так:

        User ref = em.getReference(User.class, userId);
        meal.setUser(ref);

   При этом от User нам нужет только id. Создается lazy прокси над id, которая обращается к базе при запросе любого поля. Т.е. у нас запроса в базу за юзером не будет- проверьте по логам Hibernate

**Внимание: проверять запросы Hibernate нужно через run. Если делаете debug и брекпойнт, то могут делаться лишние запросы к базе (дебаггер дергает `toString`)**
   
- 2: В JPQL запросах можно писать: `m.user.id=:userId`
- 3: При реализации `JpaMealRepositoryImpl` предпочтительно не использовать `try-catch` в логике реализации. Но если очень хочется, то ловить только специфичекские эксепшены (пр. `NoResultException`), чтобы, например, при отсутствии коннекта к базе приложение отвечало адекватно.
- 4: Мы будем смотреть генерацию db скриптов из модели, для корректной генерации нужно в `Meal` добавить `uniqueConstraints`
- 5: Когда будете чинить `MealServiceTest`, помните, что тесты пишутся для приложения, а не наоборот.
- 6: При записи в базу через `namedQuery` валидация ентити не работает, только валидация в бд