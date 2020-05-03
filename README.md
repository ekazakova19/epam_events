Проектная работа от EPAM
Цель: Задача для проекта: Необходимо построить фреймворк для автоматизации Е2Е тестирования сайта с обязательным тестовым покрытием. Обязательным является использование библиотеки Healenium и Report Portal. Задачи с пометкой «*дополнительно» выполняются по желанию.
Что будем тестировать: Приложение https://events.epam.com/ предоставляет информацию о мероприятиях, которые проводит EPAM. Сайт позволяет посмотреть предстоящие/прошедшие мероприятия в разных городах, информацию о спикерах, докладах, календарь мероприятий.
Требования к фреймворку:
1. Java + Maven/Gradle + TestNG/Junit 5 проект
2. Настроено логирование (Log4J, SLF4J или любая другая библиотека)
3. Реализована возможность кроссбаузерного тестирования
4. Реализована возможность удаленного запуска тестов (Selenoid или Selenium Grid)
5. Реализована возможность параллельного запуска тестов
6. Код проекта хранится в Git (важна частота и содержание коммитов)
7. Для работы со страницами используется паттерн Page Object
8. Код оформлен согласно Java Code Conventions, комментарии в стиле Javadoc приветствуются
*Дополнительно:
Для стабилизации нахождения локаторов используется библиотека Healenium (https://github.com/healenium/healenium-web)
Подключен Report Portal (https://reportportal.io/)
Настроена интеграция с CI и запуск тестов по расписанию

Обязательное тестовое покрытие:
UPCOMING EVENTS
1. Просмотр предстоящих мероприятий:
    ✓1.1 Пользователь переходит на вкладку events ---OpenEventTab
    ✓1.2 Пользователь нажимает на Upcoming Events -- clickOnUpcomingEvents
    ✓1.3 На странице отображаются карточки предстоящих мероприятий. Количество карточек равно счетчику на кнопке Upcoming Events
           - что если карточки не найдены потому что нет ивентов?
2. Просмотр карточек мероприятий:
    2.1 Пользователь переходит на вкладку events ---
    2.2 Пользователь нажимает на Upcoming Events openUpcomingEvents
    2.3 На странице отображаются карточки предстоящих мероприятий.
    2.4 В карточке указана информация о мероприятии:
        • место проведения, язык
        • название мероприятия
        • дата мероприятия
        • информация о регистрации
        • список спикеров
        Важно проверить порядок отображаемых блоков с информацией в карточке мероприятия
3. Валидация дат предстоящих мероприятий:
    3.1 Пользователь переходит на вкладку events
    3.2 Пользователь нажимает на Upcoming Events
    3.3 На странице отображаются карточки предстоящих мероприятий.
    3.4 В блоке This week даты проведения мероприятий больше или равны текущей дате и находятся в пределах текущей недели.

5. Просмотр детальной информации о мероприятии:
    5.1 Пользователь переходит на вкладку events
    5.2 Пользователь нажимает на Upcoming Events
    5.3 На странице отображаются карточки предстоящих мероприятий.
    5.4 Пользователь нажимает на любую карточку
    5.5 Происходит переход на страницу с подробной информацией о мероприятии
    5.6 На странице с информацией о мероприятии отображается шапка с кнопкой для регистрации, основная часть с программой мероприятия, датой, временем, местом проведения

PAST EVENTS
4. Просмотр прошедших мероприятий в Канаде:
    4.1 Пользователь переходит на вкладку events
    4.2 Пользователь нажимает на Past Events
    4.3 Пользователь нажимает на Location в блоке фильтров и выбирает Canada в выпадающем списке
    4.4 На странице отображаются карточки прошедших мероприятий. Количество карточек равно счетчику на кнопке Past Events. Даты проведенных мероприятий меньше текущей даты.

Talks Library Filtering
6. Фильтрация докладов по категориям:
    6.1 Пользователь переходит на вкладку Talks Library
    6.2 Пользователь нажимает на More Filters
    6.3 Пользователь выбирает: Category – Design, Location – Belarus, Language – English, На вкладке фильтров
    6.4 На странице отображаются карточки соответствующие правилам выбранных фильтров

Talks Library Search
7. Поиск докладов по ключевому слову:
    7.1 Пользователь переходит на вкладку Talks Library
    7.2 Пользователь вводит ключевое слово Azure в поле поиска
    7.3 На странице отображаются доклады, содержащие в названии ключевое слово поиска
*Дополнительно: Тестовое покрытие может быть расширено для функциональности фильтрации