create table un_lesson_group (group_id int8 not null, lesson_id int8 not null, primary key (group_id, lesson_id));

alter table un_lesson_group add constraint FK241s6hqsgu50ogcp0wkis6if1 foreign key (group_id) references un_group;
alter table un_lesson_group add constraint FK3v8xu5psjgj796of54unxo9ni foreign key (lesson_id) references un_lesson;

INSERT INTO un_lesson_group(lesson_id, group_id) SELECT id,group_id FROM un_lesson;

ALTER TABLE un_lesson DROP CONSTRAINT FKrt0da4d7go3syt693onnswwo7;
ALTER TABLE un_lesson DROP COLUMN group_id;