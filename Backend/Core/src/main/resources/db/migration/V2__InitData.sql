INSERT INTO un_role (id, role) VALUES ((select nextval ('role_sequence_gen')), 'ADMIN');
INSERT INTO un_role (id, role) VALUES ((select nextval ('role_sequence_gen')), 'STUDENT');
INSERT INTO un_role (id, role) VALUES ((select nextval ('role_sequence_gen')), 'PROFESSOR');
INSERT INTO un_role (id, role) VALUES ((select nextval ('role_sequence_gen')), 'ENGINEER');
INSERT INTO un_role (id, role) VALUES ((select nextval ('role_sequence_gen')), 'GUEST');


INSERT INTO un_institute (id, name) VALUES ((select nextval('institute_sequence_gen')), 'Институт Фундаментальных наук');

INSERT INTO un_department (id, name, institute_id) VALUES ((select nextval('department_sequence_gen')), 'Юнеско', 1);

INSERT INTO un_group (id, name, department_id) VALUES ((select nextval('group_sequence_gen')), 'М-178', 1);

INSERT INTO un_user (id, email, password, photo, userfio, username) VALUES ((select nextval('user_sequence_gen')), 'admin@mail.com', '$2a$10$S0ixd2pvaPAODHYhx0dHUOQZZEK5PmsAggEk5jVuWfzZHCoJeF01m', NULL, 'Администратор', 'admin');
INSERT INTO un_user (id, email, password, photo, userfio, username) VALUES ((select nextval('user_sequence_gen')), 'Savobem@mail.ru', '$2a$10$9SwnoxyRwC2EYX7gEqB35ORD8kYi1aLWskh.QGmjdYIo4JEJxI.6S', NULL, 'Владислав Михайлов', 'prof');
INSERT INTO un_user (id, email, password, photo, userfio, username) VALUES ((select nextval('user_sequence_gen')), 'Bomyalveb@mail.ru', '$2a$10$S0ixd2pvaPAODHYhx0dHUOQZZEK5PmsAggEk5jVuWfzZHCoJeF01m', NULL, 'Константин Нестеров', 'styd');

INSERT INTO un_professor (id, department_id, user_id) VALUES ((select nextval('professor_sequence_gen')), 1, 2);

INSERT INTO un_student (id, group_id, user_id) VALUES ((select nextval('student_sequence_gen')), 1, 3);

INSERT INTO un_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO un_user_role (user_id, role_id) VALUES (2, 3);
INSERT INTO un_user_role (user_id, role_id) VALUES (3, 2);

INSERT INTO un_point_type (id, name) VALUES ((select nextval('point_type_sequence_gen')), 'Посещение');
INSERT INTO un_point_type (id, name) VALUES ((select nextval('point_type_sequence_gen')), 'Лабораторная');
INSERT INTO un_point_type (id, name) VALUES ((select nextval('point_type_sequence_gen')), 'Семестровая');
INSERT INTO un_point_type (id, name) VALUES ((select nextval('point_type_sequence_gen')), 'Контрольная работа');
INSERT INTO un_point_type (id, name) VALUES ((select nextval('point_type_sequence_gen')), 'Отчет');

INSERT INTO un_field_of_knowledge (id, name) VALUES ((select nextval('field_of_knowledge_sequence_gen')), 'ИТ');
INSERT INTO un_field_of_knowledge (id, name) VALUES ((select nextval('field_of_knowledge_sequence_gen')), 'Математика');
INSERT INTO un_field_of_knowledge (id, name) VALUES ((select nextval('field_of_knowledge_sequence_gen')), 'Другое');

INSERT INTO un_discipline (id, name, field_of_knowledge_id) VALUES ((select nextval('discipline_sequence_gen')), 'Программирование', 1);
INSERT INTO un_discipline (id, name, field_of_knowledge_id) VALUES ((select nextval('discipline_sequence_gen')), 'Базы Данных', 1);
INSERT INTO un_discipline (id, name, field_of_knowledge_id) VALUES ((select nextval('discipline_sequence_gen')), 'Математический анализ', 2);
INSERT INTO un_discipline (id, name, field_of_knowledge_id) VALUES ((select nextval('discipline_sequence_gen')), 'Физическая культуроа', 3);

INSERT INTO un_room (id, room) VALUES ((select nextval('room_sequence_gen')), '101');
INSERT INTO un_room (id, room) VALUES ((select nextval('room_sequence_gen')), '102');
INSERT INTO un_room (id, room) VALUES ((select nextval('room_sequence_gen')), '103');