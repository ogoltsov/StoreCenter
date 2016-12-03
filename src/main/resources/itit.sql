create database rCenter;
use rCenter;

DROP TABLE IF EXISTS ResourceAuthors;
DROP TABLE IF EXISTS ResourceSpeciality;
DROP TABLE IF EXISTS Resource;
DROP TABLE IF EXISTS Author;
DROP TABLE IF EXISTS Status;
DROP TABLE IF EXISTS Type;
DROP TABLE IF EXISTS Speciality;
DROP TABLE IF EXISTS Users;

CREATE TABLE Status (
  id    INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(25) NOT NULL UNIQUE
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE Type (
  id    INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(50) NOT NULL UNIQUE
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE Speciality (
  id    INT AUTO_INCREMENT PRIMARY KEY,
  code  VARCHAR(8)   NOT NULL UNIQUE,
  title VARCHAR(100) NOT NULL UNIQUE
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;


CREATE TABLE Author (
  id              INT AUTO_INCREMENT PRIMARY KEY,
  lastname        VARCHAR(100) NOT NULL,
  firstname       VARCHAR(100) NOT NULL,
  middlename      VARCHAR(100) NOT NULL
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE Resource (
  id       INT      AUTO_INCREMENT PRIMARY KEY,
  title    VARCHAR(500) NOT NULL UNIQUE,
  date     DATETIME DEFAULT CURRENT_TIMESTAMP,
  statusId INT          NOT NULL,
  typeId   INT          NOT NULL,
  FOREIGN KEY (statusId) REFERENCES Status (id),
  FOREIGN KEY (typeId) REFERENCES Type (id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE ResourceSpeciality (
  resId  INT NOT NULL,
  specId INT NOT NULL,
  FOREIGN KEY (resId) REFERENCES Resource (id),
  FOREIGN KEY (specId) REFERENCES Speciality (id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE ResourceAuthors (
  resId  INT NOT NULL,
  authId INT NOT NULL,
  FOREIGN KEY (resId) REFERENCES Resource (id),
  FOREIGN KEY (authId) REFERENCES Author (id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

CREATE TABLE Users
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(25) NOT NULL,
  role VARCHAR(25) DEFAULT 'user' NOT NULL,
  firstname VARCHAR(25) NOT NULL,
  lastname VARCHAR(25) NOT NULL,
  isDelete TINYINT(1) DEFAULT '0'
);
CREATE INDEX roleId ON Users (role)

INSERT INTO Status (title) VALUES ("Проверка");
INSERT INTO Status (title) VALUES ("Готов");
INSERT INTO Status (title) VALUES ("Доработка");
INSERT INTO Status (title) VALUES ("Сертификат");

INSERT INTO Type (title) VALUES ("Учебник");
INSERT INTO Type (title) VALUES ("Презентация");
INSERT INTO Type (title) VALUES ("Учебное пособие");
INSERT INTO Type (title) VALUES ("Мультимедийная презентация");

INSERT INTO Speciality (code, title) VALUES ("5В060200", "Информатика");
INSERT INTO Speciality (code, title) VALUES ("5В070300", "Информационные системы");
INSERT INTO Speciality (code, title) VALUES ("5В070400", "Вычислительная техника и программное обеспечение");
INSERT INTO Speciality (code, title) VALUES ("5В070500", "Математическое и компьютерное моделирование");
INSERT INTO Speciality (code, title) VALUES ("5В100200", "Системы информационной безопасности");
INSERT INTO Speciality (code, title) VALUES ("5В070100", "Биотехнология");
INSERT INTO Speciality (code, title) VALUES ("5В072100", "Химическая технология органических веществ");
INSERT INTO Speciality (code, title) VALUES ("5В073100", "Безопасность жизнедеятельности и защита окружающей среды");
INSERT INTO Speciality (code, title) VALUES ("5В073700", "Обогащение полезных ископаемых");

INSERT INTO Speciality (code, title) VALUES ("5В051000", "Государственное и местное управление");
INSERT INTO Speciality (code, title) VALUES ("5В051100", "Маркетинг");
INSERT INTO Speciality (code, title) VALUES ("5В050700", "Менеджмент");
INSERT INTO Speciality (code, title) VALUES ("5В090800", "Оценка");
INSERT INTO Speciality (code, title) VALUES ("5В090400", "Социально-культурный сервис");
INSERT INTO Speciality (code, title) VALUES ("5В050800", "Учет и аудит");
INSERT INTO Speciality (code, title) VALUES ("5В050600", "экономика");


INSERT INTO Author (lastname, firstname, middlename)
VALUES ("Огольцов", "Кирилл", "Сергеевич");
INSERT INTO Author (lastname, firstname, middlename)
VALUES ("Баринова", "Татьяна", "Андреевна");
INSERT INTO Author (lastname, firstname, middlename)
VALUES ("Фролов", "Евгений", "Владимирович");
INSERT INTO Author (lastname, firstname, middlename)
VALUES ("Салихов", "Ильдар", "Марсельевич");
INSERT INTO Author (lastname, firstname, middlename)
VALUES ("Попов", "Виктор", "Сергеевич");
INSERT INTO Author (lastname, firstname, middlename)
VALUES ("Таренков", "Вячеслав", "Дмитриевич");
INSERT INTO Author (lastname, firstname, middlename)
VALUES ("Жиенбаева", "Зарина", "Ериковна");
INSERT INTO Author (lastname, firstname, middlename)
VALUES ("Воронина", "Карина", "Александровна");
INSERT INTO Author (lastname, firstname, middlename)
VALUES ("Салапаев", "Алексей", "Иванович");

INSERT INTO Resource (title, date, statusId, typeId) VALUES ("Базы данных", "2015-01-24", 3, 3);
INSERT INTO Resource (title, date, statusId, typeId) VALUES ("Веб-программирование", "2016-01-13", 2, 1);
INSERT INTO Resource (title, date, statusId, typeId) VALUES ("Интернет-технологии", "2014-05-20", 1, 2);
INSERT INTO Resource (title, date, statusId, typeId) VALUES ("Информатика", "2016-04-01", 4, 4);
INSERT INTO Resource (title, date, statusId, typeId) VALUES ("Компьютерная графика", "2015-09-19", 1, 3);
INSERT INTO Resource (title, date, statusId, typeId) VALUES ("Компьютерные сети", "2015-12-20", 3, 1);
INSERT INTO Resource (title, date, statusId, typeId) VALUES ("Основы информационной безопасности", "2016-03-25", 3, 3);

INSERT INTO ResourceSpeciality (resId, specId) VALUES (1, 15);
INSERT INTO ResourceSpeciality (resId, specId) VALUES (2, 12);
INSERT INTO ResourceSpeciality (resId, specId) VALUES (2, 14);
INSERT INTO ResourceSpeciality (resId, specId) VALUES (2, 13);
INSERT INTO ResourceSpeciality (resId, specId) VALUES (3, 10);
INSERT INTO ResourceSpeciality (resId, specId) VALUES (3, 11);
INSERT INTO ResourceSpeciality (resId, specId) VALUES (3, 6);
INSERT INTO ResourceSpeciality (resId, specId) VALUES (3, 3);
INSERT INTO ResourceSpeciality (resId, specId) VALUES (4, 2);
INSERT INTO ResourceSpeciality (resId, specId) VALUES (5, 1);
INSERT INTO ResourceSpeciality (resId, specId) VALUES (5, 4);
INSERT INTO ResourceSpeciality (resId, specId) VALUES (5, 7);
INSERT INTO ResourceSpeciality (resId, specId) VALUES (6, 3);
INSERT INTO ResourceSpeciality (resId, specId) VALUES (6, 9);
INSERT INTO ResourceSpeciality (resId, specId) VALUES (7, 7);

INSERT INTO ResourceAuthors (resId, authId) VALUES (1, 1);
INSERT INTO ResourceAuthors (resId, authId) VALUES (2, 1);
INSERT INTO ResourceAuthors (resId, authId) VALUES (2, 2);
INSERT INTO ResourceAuthors (resId, authId) VALUES (2, 3);
INSERT INTO ResourceAuthors (resId, authId) VALUES (3, 5);
INSERT INTO ResourceAuthors (resId, authId) VALUES (3, 4);
INSERT INTO ResourceAuthors (resId, authId) VALUES (4, 7);
INSERT INTO ResourceAuthors (resId, authId) VALUES (4, 8);
INSERT INTO ResourceAuthors (resId, authId) VALUES (5, 4);
INSERT INTO ResourceAuthors (resId, authId) VALUES (6, 3);
INSERT INTO ResourceAuthors (resId, authId) VALUES (7, 8);
INSERT INTO ResourceAuthors (resId, authId) VALUES (7, 9);
INSERT INTO ResourceAuthors (resId, authId) VALUES (4, 4);
INSERT INTO ResourceAuthors (resId, authId) VALUES (2, 7);
INSERT INTO ResourceAuthors (resId, authId) VALUES (1, 8);
INSERT INTO ResourceAuthors (resId, authId) VALUES (5, 3);

INSERT INTO Users (email, password, role, firstname, lastname)
VALUES ("admin@gmail.com", "admin", "admin", "Administrator", "God");
INSERT INTO Users (email, password, role, firstname, lastname)
VALUES ("user@gmail.com", "userPassword", "user", "User", "Plain");
INSERT INTO Users (email, password, role, firstname, lastname)
VALUES ("moderator@gmail.com", "moderatorPassword", "moderator", "Moderator", "Moder");