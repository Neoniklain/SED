ALTER TABLE ONLY un_room
    ADD CONSTRAINT un_room_uniq UNIQUE (room);

ALTER TABLE ONLY un_pair_type
    ADD CONSTRAINT un_pair_type_uniq UNIQUE (type);

ALTER TABLE ONLY un_institute
    ADD CONSTRAINT un_institute_uniq UNIQUE (name);

ALTER TABLE ONLY un_group
    ADD CONSTRAINT un_group_uniq UNIQUE (name);

ALTER TABLE ONLY un_field_of_knowledge
    ADD CONSTRAINT un_fieldOfKnowledge_uniq UNIQUE (name);

ALTER TABLE ONLY un_discipline
    ADD CONSTRAINT un_discipline_uniq UNIQUE (name);

ALTER TABLE ONLY un_department
    ADD CONSTRAINT un_department_uniq UNIQUE (name);