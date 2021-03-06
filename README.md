## Тестовое задание по созданию фото-галереи
Стек:
* spring boot + web
* angular
* sqlite

### Реализовать HTTP API фото-галереи
### Возможности API:
* Загрузка фото - реализовано
* Сохранять данные о новых файлах в БД - реализовано
* Генерировать preview - реализовано 
* Просмотр списка фото - реализовано
* Удаление фото - реализовано
Дополнительные требования:
* Формат ответа - JSON - в цело реализовано
* В качестве БД использовать sqlite - реализовано, но на том же H2 через Hibernate было бы часов на 6 быстрее и хранилось бы так же на диске
* Время на выполнение: не более 4-х часов

### Результат:
Результатом считается ссылка на публичный репозиторий на любом Git-хостинге либо
архив, присланный в Telegram / Whatsapp / Email.

Реальный срок выполнения:
* 0,5 часа создание каркаса spring + angular
* 1 час для создания подключения к SQLite, чтение мануалов и настройка (решение почти из коробки, но не автоматическое, как со стандартными БД)
* 1 час на контроллеры и уточнения, как лучше сделать загрузку катинок. Завершена серверная часть. Был вариант загружать картинки в виде объектов image (id + byte[]) и уже на клиентской стороне обрабатывать и выводить картинки. Отказался, посчитал, что картинки тогда бы грузились только на моём сайте, без отдельной обработки.
* 1,5 часа на вёрстку двух страниц: сама галерея + страница редактирования/ добавления
* 2,5 часа на попытки заставить hibernate нормально работать с sqlite, базовой поддержки от создателей не предусмотрено. По факту - есть сырые реализации, но нормально не работают, принято решение реализовать через стандартный JDBC.
* 3 часа борьбы с ошибкой: java.sql.SQLException: not implemented by SQLite JDBC driver, в итоге она возникает даже при попытке работать через SQLiteDataSource. Как всегда проблема в невнимательности, не заметил отсылку на ошибку в поле setBlob, заменил на setBytes. В итоге отказался полностью от методов с Blob, однако в базе элемент отмечен как Blob.
* 1 часа ручная проверка работы всех сервисов/контроллеров, отлов ошибок/опечаток
* 1,5 часа работа над оформлением: zoom изображений, рамочки, центровка
Итого: 12 часов против необходимых 4.

### Screenshots:
![gora1](https://user-images.githubusercontent.com/58567444/85932618-3cc0c580-b8d6-11ea-8175-06945d4ab100.png)

![gora2](https://user-images.githubusercontent.com/58567444/85932630-595cfd80-b8d6-11ea-812c-263f24209b93.png)
