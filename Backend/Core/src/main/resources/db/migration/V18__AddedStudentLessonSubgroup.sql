create table un_student_lesson (id int8 not null, subgroup int4 not null, lesson_id int8, student_id int8, primary key (id));
alter table un_student_lesson add constraint FKp0wc5kb7lqs29ib1h7e1g7alu foreign key (lesson_id) references un_lesson;
alter table un_student_lesson add constraint FK728f33e4f38ac70fg6k9i11mh foreign key (student_id) references un_student;