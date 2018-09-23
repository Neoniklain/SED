alter table un_lesson add column group_id int;
alter table un_lesson add constraint FKrt0da4d7go3syt693onnswwo7 foreign key (group_id) references un_group;

UPDATE un_lesson l
  SET group_id = lg.group_id from  un_lesson_group lg where lg.lesson_id = l.id;

DROP TABLE un_lesson_group;
