[![Build Status](https://travis-ci.org/vladmeh/topjava.svg?branch=HW09)](https://travis-ci.org/vladmeh/topjava)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f66f758b3974467083812ccb1e95054d)](https://www.codacy.com/app/vladmeh/topjava?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=vladmeh/topjava&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://www.versioneye.com/user/projects/5ab381f70fb24f44833013b0/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5ab381f70fb24f44833013b0)

## ![hw](https://cloud.githubusercontent.com/assets/13649199/13672719/09593080-e6e7-11e5-81d1-5cb629c438ca.png) Домашнее задание HW9

- 1: Реализовать для meal Binding/ Update/ Validation. Проверить работу при пустом значении `calories`.
- 2: Перевести `meals.jsp` на работу по ajax. Стиль строки таблицы сделать в зависимости от `exceeded`, время отображать без `T`. Добавить i18n.
- 3: Починить meals тесты, добавить тест на неавторизованный доступ

#### Optional
- 4: Подключить datetime-picker к фильтрам и модальному окну добавления/редактирования еды
  - <a href="http://xdsoft.net/jqplugins/datetimepicker/">DateTimePicker jQuery plugin</a>
  - [jQuery: конверторы](https://jquery-docs.ru/jQuery.ajax/#using-converters)

Попробуйте при запросах по REST оставить стандартный ISO формат (с разделителем `T`)

## ![error](https://cloud.githubusercontent.com/assets/13649199/13672935/ef09ec1e-e6e7-11e5-9f79-d1641c05cbe6.png) Проверка в HW09
- 1: Проверьте, что при добавлении и редактировании пользователя и еды у вас корректно отображаются заголовки модального окна:
"Добавить/Редактировать еду пользователя"
- 2: Не дублируйте
```
<c:forEach var='key' ...
i18n['${key}'] = ...
```
- 3: Для подключения css и js datetimepicker-а посмотрите в его jar (или поищите в проекте по Ctrl+Shift+N: `datetimepicker`)
- 4: datetimepicker работает корректно в Хроме, если убрать в `type` в `<input type="date/time/datetime-local" ..`
- 5: Если появляются проблемы с JS типа `... is not defined` - обратите внимание на порядок загрузки скриптов и атрибут `defer`. Скрипты должны идти в нужном порядке. Если определяете скрипт прямо в jsp, он выполняется до `defer` скриптов.
- 6: Не дублируйте обработку ошибок в `BindingResult` в ajax контроллерах
- 7: Проверьте редактирование еды: открыть на редактирование и сохранить не должно приводить к ошибкам с форматом времени.
- 8: Проверьте в `RootController.meals()`, его нужно тоже поправить