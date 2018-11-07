create table un_lesson_event_pair (lessons_event_id int8 not null, pair_id int8 not null, primary key (pair_id, lessons_event_id));
alter table un_lesson_event_pair add constraint FKg34j563bbuanm0n1cph3qtr6r foreign key (lessons_event_id) references un_lesson_event;
alter table un_lesson_event_pair add constraint FKcexnub0ji782yp1mklqlvhhs1 foreign key (pair_id) references un_pair;