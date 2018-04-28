[![Build Status](https://travis-ci.org/vladmeh/topjava.svg?branch=HW08)](https://travis-ci.org/vladmeh/topjava)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f66f758b3974467083812ccb1e95054d)](https://www.codacy.com/app/vladmeh/topjava?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=vladmeh/topjava&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://www.versioneye.com/user/projects/5ab381f70fb24f44833013b0/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5ab381f70fb24f44833013b0)

## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Домашнее задание HW08

- 1: Перевести `meals` на `datatables` (`meals.jsp`, `MealAjaxController`).
  - 1.1 Реализовать добавление записи еды через модальное окно Bootstrap и удаление еды по ajax (БЕЗ редактирования).
  - 1.2 При вставке данных по AJAX пропадает все JSP форматирование, чинить перерисовку НЕ надо. Следующий урок- будем делать datatable по AJAX и форматирование на стороне клиента.
- 2: Т.к. HTML атрибут id у каждого элемента документа должен быть уникален, нужно избавиться от дублирования `id="${user.id}"` в строках таблиц users и meals (переместить атрибут id в тэг `<tr>` или передавать в качестве параметра функций через `onclick`)

#### Optional.
- 3: Перевести работу фильтра на AJAX. Попробуйте после модификации таблицы (например добавлении записи) обновлять ее также с учетом фильтра.
- 4: Сделать кнопку сброса фильтра.
- 5: Реализовать enable/disable User через checkbox в `users.jsp` с сохранением в DB.
Неактивных пользователей выделить css стилем. Проверьте, как у вас первоначально (или по F5) отображаются неактивные пользователи (если меняете css при enable/disable)


---------------------
## ![error](https://cloud.githubusercontent.com/assets/13649199/13672935/ef09ec1e-e6e7-11e5-9f79-d1641c05cbe6.png) Подсказки по HW08
- 1: enable/disable делать c `@Transactional` (можно реализовать как на уровне репозитория, так и на уровне сервиса через несколько sql, которые должны быть в одной транзакции)
- 2: в `datatablesUtil.js` следует выносите только общие скрипты (cкрипты еды размещайте в  `mealDatatables.js`, пользователей в `userDatatables.js`)
- 3: если в контроллер приходит `null` проверте в `Network` вкладке браузера в каком формате приходят данные и в каком формате в контроллере вы их принимаете (`consumes`).
- 4: при реализации `enable/disable` лучше явно указывать нужное состояние, чем переключать на противоположное. Если параллельно вам кто-то изменит состояние, то будет несоответствие UI и DB.
