[![Build Status](https://travis-ci.org/vladmeh/topjava.svg?branch=HW07)](https://travis-ci.org/vladmeh/topjava)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f66f758b3974467083812ccb1e95054d)](https://www.codacy.com/app/vladmeh/topjava?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=vladmeh/topjava&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://www.versioneye.com/user/projects/5ab381f70fb24f44833013b0/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5ab381f70fb24f44833013b0)

## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Домашнее задание HW07

- 1: Добавить тесты контроллеров:
  - 1.1 `RootControllerTest.testMeals` для `meals.jsp`
  - 1.2 `ResourceControllerTest` для `style.css` (проверить status и ContentType)
- 2: Реализовать `MealRestController` и протестировать его через `MealRestControllerTest`
  - 2.1 cледите чтобы url в тестах совпадал с параметрами в методе контроллера. Можно добавить логирование `<logger name="org.springframework.web" level="debug"/>` для проверки маршрутизации.
  - 2.2 в параметрах `getBetween` принимать `LocalDateTime` (конвертировать через <a href="http://blog.codeleak.pl/2014/06/spring-4-datetimeformat-with-java-8.html">@DATETIMEFORMAT WITH JAVA 8 DATE-TIME API</a>), а передавать в тестах в формате `ISO_LOCAL_DATE_TIME` (например `'2011-12-03T10:15:30'`). Вызывать `super.getBetween()` пока без проверки на `null`, используя `toLocalDate()/toLocalTime()` (см. Optional п.3)

#### Optional
- 3: Переделать `MealRestController.getBetween` на параметры `LocalDate/LocalTime` c раздельной фильтрацией по времени/дате, работающий при `null` значениях (см. демо и `JspMealController.getBetween`). Заменить `@DateTimeFormat` на свои LocalDate/LocalTime конверторы или форматтеры.
  -  <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#core-convert">Spring Type Conversion</a>
  -  <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#format">Spring Field Formatting</a>
  -  <a href="http://stackoverflow.com/questions/13048368/difference-between-spring-mvc-formatters-and-converters">Difference between Spring MVC formatters and converters</a>
- 4: Протестировать `MealRestController` (SoapUi, curl, IDEA Test RESTful Web Service, Postman). Запросы `curl` занести в отдельный `md` файл (либо `README.md`)
  
**На следующем занятии используется JavaScript/jQuery. Если у вас там пробелы, <a href="https://github.com/JavaOPs/topjava#html-javascript-css">пройдите его основы</a>**

---------------------
## ![error](https://cloud.githubusercontent.com/assets/13649199/13672935/ef09ec1e-e6e7-11e5-9f79-d1641c05cbe6.png) Подсказки по HW07
- 1: Ошибка в тесте _Invalid read array from JSON_ обычно расшифровывается немного ниже: читайте внимательно.
- 2: <a href="https://urvanov.ru/2016/12/03/jackson-и-неизменяемые-объекты/">Jackson и неизменяемые объекты</a>
- 3: <a href="http://www.baeldung.com/jackson">Jackson JSON Tutorial</a>
- 4: Если у meal, приходящий в контроллер, поля null, проверьте `@RequestBody` перед параметром (данные приходят в формате JSON)
- 5: При проблемах с собственным форматтером убедитесь, что в конфигурации `<mvc:annotation-driven...` не дублируется
- 6: **Проверьте выполение ВСЕХ тестов через maven**. В случае проблем проверьте, что не портите константу из `MealTestData`
- 7: `@Autowired` в тестах нужно делать в том месте, где класс будет использоваться. Общий принцип: не размазывать код по классам, объявление переменных держать как можно ближе к ее использованию, группировать (не смешивать) код с разной функциональностью.
- 8: Попробуйте в `RootControllerTest.testMeals` сделать сравнение через `model().attribute("meals", expectedValue)`. Учтите, что вывод результатов через `toString` к сравнению отношения не имеет
- 9: Посмотрите, нет ли в `MealTestData` методов, которые можно сделать общими (через generic и `TestUtil`)
