># Задание
### Урок 3. Продвинутая работа с исключениями в Java
Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
Фамилия Имя Отчество дата_рождения номер_телефона пол

Форматы данных:
* фамилия, имя, отчество - строки
* датарождения - строка формата dd.mm.yyyy
* номертелефона - целое беззнаковое число без форматирования
* пол - символ латиницей f или m.
Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида

<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

Не забудьте закрыть соединение с файлом.

При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.
___
___
># Порядок работы с программой
* При написании программы старался использовать принципы **SOLID** и **MVC**. Только не придумал, что в папку `view` положить.
* В папке `model` находятся классы `Person` (содержит данные о человеке, геттеры и переопределенный метод `ToString` для вывода в файл или в консоль в нужном формате), `FileIOException` и `InvalidDataFormatException` - собственные классы исключений.
* В папке `controller` находится собственно класс контроллера `UserController`, который содержит методы ввода данных пользователем, их парсинга, валидации и записи в файл.
* В папке `files` содержатся создаваемые программой файлы с данными людей.
* Файл `Main` содержит собственно метод `main`, создающий экземпляр контроллера и запускающий бизнес-логику.
* Вводимые пользователем данные валидируются следующим образом:
    * ФИО должно содержать только буквы.
    * Дата рождения - строка в формате `dd.mm.yyyy`. Проверяется количество символов и возраст (не более 200 лет).
    * Номер телефона должен содержать 11 цифр, знак "+" может присутствовать или отсутствовать.
    * Пол обозначается русскими буквами <м> или <ж>.
    * Всего должно быть введено 6 параметров.
    * Несколько пробелов между параметрами считаются одним пробелом.
* В аргументах `FileWriter` указан параметр `true`, чтобы данные дописывались в файл, а не писались заново.
