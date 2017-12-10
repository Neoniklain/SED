#### Запуск сервера

1. Необходима InteliJ IDEA 2017
2. java jdk1.8.0_151
2. Копируем репозиторий
3. Запускаем через IDEA.
4. Жмем зеленый треугольник

#### Запуск Angular

1. Качаем и устанавливаем Node.js 7.5.0
2. В cmd вводим node -v & npm –v. Должно вывести вот так:
```aidl
> node -v & npm –v
v7.10.0
4.2.0
```
3. Далее преходим в папку с Angular (SED\src\main\webapp\WEB-INF\Angular)
4. Прописываем команду:
```aidl
> npm install
```
5. Начнётся долгая загрузка и установка.
6. После загрузки можно проверить что все в порядке. (вывод будет примерно таким):
```aidl
> ng –v
@angular/cli: 1.0.3
node: 7.10.0
os: win32 x64
```
7. Далее запускаем проект:
```aidl
> npm start
```
8. Ангуляр запустится на порту localhost:4200)

#### База Данных

Для проекта необходима база данных MySQl.

1. Создаем базу с названием unesco
2. Выполняем скрипт который находится [здесь](Дамп%20БД.txt)
3. Eсли нужно то изменяем логин/пароль к базе в файле src/main/java/resources/application.properties
4. Чтобы загружать новости в БД необходимо снять ограничение на отправляемые пакеты в ней.
Sql запрос:
```sql
SET GLOBAL max_allowed_packet=268435456;
```
5. Перед проверкой нужно перезайти в sql.
5. Проверить:   
```sql
SELECT @@max_allowed_packet;
```

##### Ссылки
1. [О снятии ограничения на отправляемые пакеты в БД](https://confluence.atlassian.com/confkb/exceeds-max-allowed-packet-for-mysql-179443425.html)
2. [Node.js 7.5.0](https://nodejs.org/en/download/releases/)