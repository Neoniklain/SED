INSERT INTO un_role (id, role) VALUES (1, 'ADMIN');
INSERT INTO un_role (id, role) VALUES (2, 'STUDENT');
INSERT INTO un_role (id, role) VALUES (3, 'PROFESSOR');
INSERT INTO un_role (id, role) VALUES (4, 'ENGINEER');
INSERT INTO un_role (id, role) VALUES (5, 'GUEST');

INSERT INTO un_institute (id, name) VALUES (1, 'Институт Фундаментальных наук');

INSERT INTO un_department (id, name, institute_id) VALUES (1, 'Юнеско', 1);

INSERT INTO un_group (id, name, department_id) VALUES (1, 'М-178', 1);

INSERT INTO un_user (id, email, password, photo, userfio, username) VALUES (1, 'admin@mail.com', '$2a$10$S0ixd2pvaPAODHYhx0dHUOQZZEK5PmsAggEk5jVuWfzZHCoJeF01m', NULL, 'Администратор', 'admin');
INSERT INTO un_user (id, email, password, photo, userfio, username) VALUES (2, 'Savobem@mail.ru', '$2a$10$9SwnoxyRwC2EYX7gEqB35ORD8kYi1aLWskh.QGmjdYIo4JEJxI.6S', NULL, 'Владислав Михайлов', 'prof');
INSERT INTO un_user (id, email, password, photo, userfio, username) VALUES (3, 'Bomyalveb@mail.ru', '$2a$10$S0ixd2pvaPAODHYhx0dHUOQZZEK5PmsAggEk5jVuWfzZHCoJeF01m', NULL, 'Константин Нестеров', 'styd');

INSERT INTO un_professor (id, department_id, user_id) VALUES (1, 1, 2);

INSERT INTO un_student (id, group_id, user_id) VALUES (1, 1, 3);

INSERT INTO un_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO un_user_role (user_id, role_id) VALUES (2, 3);
INSERT INTO un_user_role (user_id, role_id) VALUES (3, 2);

INSERT INTO un_point_type (id, name) VALUES (1, 'Посещение');
INSERT INTO un_point_type (id, name) VALUES (2, 'Лабораторная');
INSERT INTO un_point_type (id, name) VALUES (3, 'Семестровая');
INSERT INTO un_point_type (id, name) VALUES (4, 'Контрольная работа');
INSERT INTO un_point_type (id, name) VALUES (5, 'Отчет');

INSERT INTO un_field_of_knowledge (id, name) VALUES (1, 'ИТ');
INSERT INTO un_field_of_knowledge (id, name) VALUES (2, 'Математика');
INSERT INTO un_field_of_knowledge (id, name) VALUES (3, 'Другое');

INSERT INTO un_discipline (id, name, field_of_knowledge_id) VALUES (1, 'Программирование', 1);
INSERT INTO un_discipline (id, name, field_of_knowledge_id) VALUES (2, 'Базы Данных', 1);
INSERT INTO un_discipline (id, name, field_of_knowledge_id) VALUES (3, 'Математический анализ', 2);
INSERT INTO un_discipline (id, name, field_of_knowledge_id) VALUES (4, 'Физическая культуроа', 3);

INSERT INTO un_room (id, room) VALUES (1, '101');
INSERT INTO un_room (id, room) VALUES (2, '102');
INSERT INTO un_room (id, room) VALUES (3, '103');