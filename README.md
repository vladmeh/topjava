## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Домашнее задание HW02
- 1: переименовать `MockUserRepositoryImpl` в `InMemoryUserRepositoryImpl` и имплементировать по аналогии с `InMemoryMealRepositoryImpl` (список пользователей возвращать отсортированным по имени)
- 2: сделать `Meal extends AbstractBaseEntity`, `MealWithExceed` перенести в пакет `ru.javawebinar.topjava.to` (transfer objects)
- 3: изменить `MealRepository/InMemoryMealRepositoryImpl`: в одном общем хранилище хранится вся еда всех пользователей. Любой пользователь авторизуется и видит/модифицирует только свою еду.
  - 3.1: если по запрошенному id еда отсутствует или чужая, возвращать `null/false` (см. комментарии в `UserRepository`)
  - 3.2: реализовать хранение еды для каждого пользователя можно с добавлением поля `userId` в `Meal` ИЛИ без него (как нравится). Напомню, что репозиторий один и приложение может работать одновременно с многими пользователями.
  - 3.3: список еды возвращать отсортированным по времени, последние записи наверху
  - 3.4: атомарность операций не требуется  (коллизии при одновременном изменении одного пользователя можно не учитывать)
- 4: Реализовать слои приложения для функциональности "еда". API контроллера должна удовлетворять все потребности демо приложения и ничего лишнего (см. [демо](http://topjava.herokuapp.com)). 
  - **Смотрите на реализацию слоя для user и делаете по аналогии! Если там что-то непонятно, не надо исправлять или делать по своему. Задавайте вопросы. Если действительно нужна правка- я сделаю и напишу всем.**
  - 4.1: после авторизации (сделаем позднее), id авторизованного юзера будет попадать в `AuthorizedUser.id()`. Запрос попадает в контроллер, методы которого будут доступны снаружи по http, т.е. запрос можно будет сделать с ЛЮБЫМ id для еды 
  (не принадлежащем авторизированному пользователю). Нельзя позволять модифицировать/смотреть чужую еду.
  - 4.2: `AuthorizedUser` известен только на слое web (см. реализацию `ProfileRestController`). `MealService` можно тестировать без подмены логики авторизации, принимаем в методах сервиса и репозитория параметр `userId`: id владельца еды.
  - 4.3: если еда не принадлежит авторизированному пользователю или отсутствует, в `MealServiceImpl` бросать `NotFoundException`.
  - 4.4: конвертацию в `MealWithExceeded` можно делать как в слое web, так и в service ([Mapping Entity->DTO: Controller or Service?](http://stackoverflow.com/questions/31644131))
  - 4.5: в `MealServiceImpl` постараться сделать в каждом методе только одни запрос к `MealRepository`
  - 4.6 еще раз: не надо в названиях методов повторять названия класса (`Meal`).
- 5: включить классы еды в контекст Spring (добавить аннотации) и вызвать из `SpringMain` любой метод `MealRestController` (проверить что Spring все корректно заинжектил)

### Optional
- 6: в `MealServlet` сделать инициализацию Spring, достать `MealRestController` из контекста и работать с едой через него (как в `SpringMain`). `pom.xml` НЕ менять, работаем со `spring-context`. Сервлет обращается к контролеру, контроллер вызывает сервис, сервис - репозиторий.
   - 6.1: учесть, что когда будем работать через Spring MVC, `MealServlet` удалим, те вся логика должна быть в контроллере
- 7: добавить в `meals.jsp` и `MealServlet` две отдельные фильтрации еды: по дате и по времени (см. [демо](http://topjava.herokuapp.com))
- 8: добавить выбор текущего залогиненного пользователя (имитация авторизации, сделать Select с двумя элементами со значениями 1 и 2 в `index.html` и `AuthorizedUser.setId(userId)` в `UserServlet`).
Настоящая атворизация будет через Spring Security позже.
---------------------
### ![error](https://cloud.githubusercontent.com/assets/13649199/13672935/ef09ec1e-e6e7-11e5-9f79-d1641c05cbe6.png) Подсказки по HW02 (для проверки, сначала сделайте самостоятельно!)

- 1: **В реализации `InMemoryUserRepositoryImpl`**
  - 1.1: `getByEmail` попробуйте сделать через `stream`
  - 1.2: `delete` попробуйте сделать за одно обращение к map (без `containsKey`)
  - 1.3: предусмотрите случай одинаковых `User.name` (порядок должен быть зафиксированным).
- 2: **В реализации `InMemoryMealRepositoryImpl`**
  - 2.1: В `Meal`, которая приходит в контроллер нет никакой информации о пользователе (еда приходит в контроллер БЕЗ `user/userId`). Она может быть только авторизованного пользователя, поэтому что-то сравнивать с ним никакого смысла нет. По `id` еды и авторизованному пользователю нужно проверить ее принадлежность.
  - 2.2: `get\update\delete` - следите, чтобы не было NPE (`NullPointException` может быть, если в хранилище отсутствует юзер или еда).
  - 2.3: Проверьте сценарий: авторизованный пользователь пробует изменить чужую еду (id еды ему не принадлежит).
  - 2.4: Фильтрацию по датам сделать в репозитории т.к. из базы будем брать сразу отфильтрованные по дням записи. Следите чтобы **первый и последний день не были обрезаны, иначе сумма калорий будет неверная**.
  - 2.5: Если запрашивается список и он пустой, не возвращайте NULL! По пустому списку можно легко итерироваться без риска `NullPoinException`.
  - 2.6: Не дублируйте код в `getAll` и метод с фильтрацией
  - 2.7: попробуйте учесть, что следующая реализация (сортировка, фильтрация) будет делаться прямо в базе данных
- 3: Проверьте, что удалили `Meal.id`, он уже есть в базовом `BaseEntity`
- 4: Проверку `isBetweenDate` сделать в `DateTimeUtil`. Попробуйте использовать дженерики и объединить ее с `isBetweenTime`
- 5: **Реализация 'MealRestController' должен уметь обрабатывать запросы**:
  - 5.1: Отдать свою еду (для отображения в таблице, формат `List<MealWithExceed>`), запрос БЕЗ параметров
  - 5.2: Отдать свою еду, отфильтрованную по startDate, startTime, endDate, endTime
  - 5.3: Отдать/удалить свою еду по id, параметр запроса - id еды. Если еда с этим id чужая или отсутствует - `NotFoundException`
  - 5.4: Сохранить/обновить еду, параметр запроса - Meal. Если обновляемая еда с этим id чужая или отсутствует - `NotFoundException`
  - 5.5: Сервлет мы удалим, а контроллер останется, поэтому возвращать `List<MealWithExceed>` надо из контроллера. И userId принимать в контроллере НЕЛЬЗЯ (иначе - для чего аторизация?). Подмену `MIX/MAX` для `Date/Time` также сделайте здесь.
  - 5.6: В REST при update принято передавать id (см. `AdminRestController.update`)
  - 5.7: Сделайте отдельный `getAll` без применения фильтра
- 6: Проверьте корректную обработку пустых значений date и time в контроллере
- 7: Авторизированного пользователя берем из `AuthorizedUser.id()`, cм. `ProfileRestController`
- 8: В `MealServlet`
  - 8.1: Закрывать springContext в сервлете грамотнее всего в `HttpServlet.destroy()`: если где-то в контексте Spring будет ленивая инициализация, метод-фабрика, не синглтон-scope, то контекст понадобится при работе приложения. У нас такого нет, но делать надо все грамотно.
  - 8.2: Не храните параметры фильтра как члены класса сервлета, это не многопоточно! Один экземпляр сервлета используется всеми запросами на сервер, попробуйте дернуть его из 2х браузеров.