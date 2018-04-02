[![Build Status](https://travis-ci.org/vladmeh/topjava.svg?branch=HW05)](https://travis-ci.org/vladmeh/topjava)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f66f758b3974467083812ccb1e95054d)](https://www.codacy.com/app/vladmeh/topjava?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=vladmeh/topjava&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://www.versioneye.com/user/projects/5ab381f70fb24f44833013b0/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5ab381f70fb24f44833013b0)

## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) ![video](https://cloud.githubusercontent.com/assets/13649199/13672715/06dbc6ce-e6e7-11e5-81a9-04fbddb9e488.png) <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFZFdWWFdwams0eGM">Домашнее задание HW05</a>

- 1: Имплементировать `DataJpaMealRepositoryImpl`
- 2: Разделить реализации Repository по профилям Spring: `jdbc`, `jpa`, `datajpa` (общее в профилях можно объединять, например `<beans profile="datajpa,jpa">`). 
  - 2.1: Профили выбора DB (`postgres/hsqldb`) и реализации репозитория (`jdbc/datajpa/jpa`) независимы друг от друга и при запуске задать приложения (тестов) нужно задать тот и другой. 
  - 2.2: Для интеграции с IDEA не забудте выставить в `spring-db.xml` справа вверху в `Change Profiles...` профили, например `datajpa, postgres`
  - 2.3: Общие части для всех в `spring-db.xml` можно оставить как есть без профилей вверху файла (до первого `<beans profile=` ).
- 3: Сделать тесты всех реализаций (`jdbc, jpa, datajpa`) через наследование (без дублирования)
  -  3.1 **сделать один базовый класс для `MealServiceTest` и `UserServiceTest`**.
  -  3.2 сводку по времени выполнения тестов также сделать для `user`
- 4: Запустить все тесты: `mvn test` (в IDEA Maven Lifecycle - test, кнопку skipTest отжать)

#### Optional

- 5: Разделить `JdbcMealRepositoryImpl` для HSQLDB (она не умеет работать с Java8 Time API) и Postgres через `@Profile` (для Postgres оставить `LocalDateTime`). Цель задания -  потренироваться с [паттерном шаблонный метод](https://refactoring.guru/ru/design-patterns/template-method) и профилями Spring. Бины Spring мы разделяем (фильтруем) по разным профилям с помощью `beans profile` в xml конфигурации и `@Profile` (те мы конфигурируем, какие бины попадут в контекст Spring в зависимости от активных профилей приложения). 
Абстрактные классы не создаются и в контекст не попадают. Профили, заданные в `@Profile` пересекаются с активными профилями приложения: если пересечение есть, то бин включается в контекст (cм. реализацию `@Profile` и в ней `ProfileCondition`, можно подебажить). Например при сконфигуренном `@Profile({"postgres","jdbc"})` бин попадет в контекст, если в профилях запущенного приложения есть хотя бы один из них (например "jdbc"). После выполнения разделения можно предложить решение проще.
- 6: Починить `MealServlet` и использовать в `SpringMain` реализацию DB: добавить профили. Попробуйте поднять Spring контекст без использования `spring.profiles.active`.
- 7: Сделать и протестировать в сервисах методы (тесты и реализация только для `DataJpa`)
  - 7.1:  достать по id пользователя вместе с его едой
  - 7.2:  достать по id еду вместе с пользователем
  - 7.3:  обращения к DB сделать в одной транзакции (можно сделать разные варианты). <a href="https://en.wikibooks.org/wiki/Java_Persistence/OneToMany">Java Persistence/OneToMany</a>

---------------------
### ![error](https://cloud.githubusercontent.com/assets/13649199/13672935/ef09ec1e-e6e7-11e5-9f79-d1641c05cbe6.png) Подсказки по HW05
- 1: Для того, чтобы не запускались родительские классы тестов нужно сделать их `abstract`
- 2: В реализациях `JdbcMealRepository` **код не должен дублироваться**. Если вы возвращаете тип `Object`, посмотрите в сторону <a href="http://www.quizful.net/post/java-generics-tutorial">дженериков</a>.
- 3: В `MealServlet/SpringMain` в момент `setActiveProfiles` контекст спринга еще не должен быть инициализирован, иначе выставление профиля уже ничего не будет делать.
- 4: Если у метода нет реализации, то стандартно бросается `UnsupportedOperationException`.
- 5: Для уменьшения количества кода при реализации _Optional_ (п. 7, только `DataJpa`) попробуйте сделать `default` метод в интерфейсе
- 6: В Data-Jpa метод для ссылки на entity (аналог `em.getReference`) : `T getOne(ID id)`
- 7: Проверьте, что в `DataJpaMealRepositoryImpl` все обращения к DB выполняются в **одной транзакции**
- 8: Для `достать по id пользователя вместе с его едой` я в `User` добавил `List<Meal> meals`
- 9: Проверьте, что все тесты запускаются из Maven (имена классов тестов удовлетворяют соглашению) и итоги тестов класса выводятся корректно (не копятся)
- 10: `@ActiveProfiles` принимает в качестве параметра строку, либо **массив** строк. В тестах можно задавать несколько `@ActiveProfiles` в разных классах, они суммируются 
- 11: В релизации 7.1 учесть, что у юзера может отсутствовать еда
- 12: [Ordering a join fetched collection in JPA using JPQL/HQL](http://stackoverflow.com/questions/5903774/ordering-a-join-fetched-collection-in-jpa-using-jpql-hql)
- 13: `<beans profile=` в конфигурации контекста должны находиться после всех остальных объявлений.
- 14: По умолчанию [maven-surefire-plugin](http://maven.apache.org/surefire/maven-surefire-plugin/examples/inclusion-exclusion.html) включает в тесты классы, заканчивающиеся на Test.
