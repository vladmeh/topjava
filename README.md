[![Build Status](https://travis-ci.org/vladmeh/topjava.svg?branch=HW06)](https://travis-ci.org/vladmeh/topjava)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f66f758b3974467083812ccb1e95054d)](https://www.codacy.com/app/vladmeh/topjava?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=vladmeh/topjava&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://www.versioneye.com/user/projects/5ab381f70fb24f44833013b0/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5ab381f70fb24f44833013b0)

## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Домашнее задание HW06
- 1.1 Починить тесты `InMemoryAdminRestControllerSpringTest/InMemoryAdminRestControllerTest`  (в новой версии Spring классы `spring-mvc` требуют `WebApplicationContext`, поэтому поправьте `mock.xml`)
- 1.2 Починить Jdbc тесты (валидацию исключить)
  - <a href="http://iliachemodanov.ru/ru/blog-ru/12-tools/57-junit-ignore-test-by-condition-ru">org.junit.Assume</a>
  - <a href="http://www.ekiras.com/2015/09/spring-how-to-get-current-profiles-in-spring-application.html">How to get Current Profiles in Spring Application</a>
- 1.3 Удалить сервлеты и перенести функциональность `MealServlet` в `JspMealController` контроллер (по аналогии с `RootController`).
`MealRestController` у нас останется, с ним будем работать позже. 
  - 1.3.1 разнести запросы на update/delete/.. по разным методам (попробуйте вообще без `action=`). Можно по аналогии с `RootController#setUser` принимать `HttpServletRequest request` (аннотации на параметры и адаптеры для `LocalDate/Time` мы введем позже). 
  - 1.3.2 в одном контроллере нельзя использовать другой. Чтобы не дублировать код можно сделать наследование.
  - 1.3.3 добавить локализацию и `jsp:include` в `mealForm.jsp / meals.jsp`

#### Optional
- 2.1 Добавить транзакционность (`DataSourceTransactionManager`) в Jdbc реализации  
- 2.2 Добавить еще одну роль к ADMIN (будет 2 роли: `ROLE_USER, ROLE_ADMIN`)
- 2.3 Добавить проверку ролей в UserTestData.assertMatch
- 2.4 Починить тесты в `JdbcUserRepositoryImpl` (добавить роли). Доставать можно двумя способами: одним запросом с JOIN либо двумя запросами: отдельно `users` и отдельно `roles`.
  - 2.4.1 В реализации `getAll` НЕ делать запрос ролей для каждого юзера (N+1 select)
  - 2.4.2 При save посмотрите на <a href="https://www.mkyong.com/spring/spring-jdbctemplate-batchupdate-example/">batchUpdate()</a>

---------------------
## ![error](https://cloud.githubusercontent.com/assets/13649199/13672935/ef09ec1e-e6e7-11e5-9f79-d1641c05cbe6.png) Подсказки по HW06
- 1: Неверная кодировка UTF-8 с Spring обычно решается фильтром `CharacterEncodingFilter`:
```
    <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```
- 2: **Если не поднимается контекст Spring, смотрим причину в верху самого нижнего эксепшена.** Все ошибки на отсутствия бина в контексте или его нескольких реализациях относятся к пониманию основ: Spring application context. Если нет понимания этих основ, двигаться дальше нельзя, нужно вернуться к видео Спринг, где объясняется что это такое. Также пересмотрите видео [Тестирование UserService через AssertJ](https://drive.google.com/file/d/1SPMkWMYPvpk9i0TA7ioa-9Sn1EGBtClD). Начиная с 11.30 как раз разбираются подобные ошибки.
- 3: Если неправильно формируется url относительно контекста приложения (например `/topjava/meals/meals`), посмотрите на
  -  <a href="http://stackoverflow.com/questions/4764405/how-to-use-relative-paths-without-including-the-context-root-name">Relative paths in JSP</a>
  -  <a href="http://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/mvc.html#mvc-redirecting-redirect-prefix">Spring redirect: prefix</a>
- 4: При проблемах с запуском томкат проверьте запущенные `java` процессы, нет ли в `TOMCAT_HOME\webapps` приложения каталога `topjava`, логи tomcat - нет ли проблем с доступом к каталогам или контекстом Spring.
- 5: Если создаете List с одним значением или Map с одним ключом-значением, пользуйтесь `Collections.singleton..`
- 6: В MealController общую часть `@RequestMapping(value = "/meals")` лучше вынести на уровень класса
- 7: Не забывайте при реализации `JdbcUserRepositoryImpl` про `Map.computeIfAbsent` и `EnumSet`
- 8: Проверьте `@Transactional(readOnly = true)` сверху `Jdbc..RepositoryImpl`
- 9: Проверьте, что `config\messages\app_ru.properties` у вас в кодировке UTF-8 (в любом редакторе/вьюере или при отключенном [Transparent native-to-ascii conversion](https://github.com/JavaOPs/topjava/wiki/IDEA#%D0%9F%D0%BE%D1%81%D1%82%D0%B0%D0%B2%D0%B8%D1%82%D1%8C-%D0%BA%D0%BE%D0%B4%D0%B8%D1%80%D0%BE%D0%B2%D0%BA%D1%83-utf-8) в IDEA). ASCII коды нужны были только для JSP.