create sequence visitation_config_sequence_gen start 1 increment 1;

CREATE TABLE un_visitation_config (
    id bigint NOT NULL,
    is_auto_generated boolean NOT NULL,
    value integer NOT NULL,
    lesson_id bigint
);

ALTER TABLE public.visitation_config_sequence_gen
  OWNER TO postgres;

CREATE TABLE visitation_config_entity_dates (
    visitation_config_entity_id bigint NOT NULL,
    dates timestamp without time zone
);

ALTER TABLE ONLY un_visitation_config
    ADD CONSTRAINT un_visitation_config_pkey PRIMARY KEY (id);

ALTER TABLE ONLY visitation_config_entity_dates
    ADD CONSTRAINT fkbpnq3of0ketg1fbvpoxm6y64u FOREIGN KEY (visitation_config_entity_id) REFERENCES un_visitation_config(id);

ALTER TABLE ONLY un_visitation_config
    ADD CONSTRAINT fkcu43f6ktf6dnwva3q3ihl5skc FOREIGN KEY (lesson_id) REFERENCES un_lesson(id);

alter table un_visitation_config add constraint un_visitation_unique unique (lesson_id);
