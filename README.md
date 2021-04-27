[![Build Status](https://travis-ci.org/dpopkov/job4j.svg?branch=master)](https://travis-ci.org/dpopkov/job4j)
[![codecov](https://codecov.io/gh/dpopkov/job4j/branch/master/graph/badge.svg)](https://codecov.io/gh/dpopkov/job4j)

# Д. Попков

Задания курса [Job4j](http://job4j.ru).

## Некоторые задания, реализованные за время обучения
* [Задания по Memory Model, Garbage Collector и Profiling](chapter_009_gc/src/main/java/ru/job4j/jmm)
* [Генератор шаблонов](chapter_008_ood/src/main/java/ru/job4j/ood/templates)
* [Модель динамического распределения товаров](chapter_008_ood/src/main/java/ru/job4j/ood/store)
* [Модель Парковки машин](chapter_008_ood/src/main/java/ru/job4j/ood/carparking)
* [Модель Warehouse](chapter_008_ood/src/main/java/ru/job4j/ood/warehouse)
* [Парсер вакансий с запуском по расписанию](vacparser/src/main/java/ru/job4j/vacparser)
* [Реализация Proxy для JDBC connection](chapter_007_db/src/test/java/ru/job4j/tracker/ConnectionRollback.java)
* [Задания на XML/XSLT](chapter_007_db/src/main/java/ru/job4j/sqltoxml)
* [Консольный Tracker DB записей](chapter_007_db/src/main/java/ru/job4j/tracker)
* [Утилита поиска файлов](find/src/main/java/ru/job4j/io/find)
* [Симулятор сетевого бота](chapter_006_io/src/main/java/ru/job4j/io/netw/bot)
* [Консольный чат](chapter_006_io/src/main/java/ru/job4j/io/chat)
* [Консольный архиватор](pack/src/main/java/ru/job4j/io/pack)
* [Сканирование файловой системы](chapter_006_io/src/main/java/ru/job4j/search)
* [Задания на Input/Output](chapter_006_io/src/main/java/ru/job4j/io)
* [GUI для приложения Puzzle](puzzle/src/main/java/ru/job4j/puzzle)
* [Реализации Коллекций](chapter_005/src/main/java/ru/job4j/collections)
* [Шахматная доска, каркас](chess/src/main/java/ru/job4j/chess)
* [Консольный Tracker записей](chapter_002/src/main/java/ru/job4j/tracker)
* [Крестики-нолики на JavaFX](chapter_001/src/main/java/ru/job4j/tictactoe)


#### VM options для запуска проектов JavaFX на JDK 11

--module-path c:\path-to-javafx\javafx-sdk-11\lib  
--add-modules=javafx.controls,javafx.fxml
