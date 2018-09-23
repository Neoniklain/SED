create sequence access_right_sequence_gen start 1 increment 1;

create sequence user_access_right_sequence_gen start 1 increment 1;

CREATE TABLE un_access_right (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    header character varying(255),
    caption character varying(255)
);

CREATE TABLE un_user_access_right (
    id bigint NOT NULL,
    access_right_id bigint NOT NULL,
    user_id bigint NOT NULL,
    allow boolean NOT NULL
);

ALTER TABLE ONLY un_access_right
    ADD CONSTRAINT un_access_right_pkey PRIMARY KEY (id);

ALTER TABLE ONLY un_user_access_right
    ADD CONSTRAINT un_user_access_right_pkey PRIMARY KEY (id);

ALTER TABLE ONLY un_user_access_right
    ADD CONSTRAINT fk6og0o225pxxfrd0wkii6vhduc FOREIGN KEY (access_right_id) REFERENCES un_access_right(id);

ALTER TABLE ONLY un_user_access_right
    ADD CONSTRAINT fkaxame3b4k04046u0w67qkd5uw FOREIGN KEY (user_id) REFERENCES un_user(id);