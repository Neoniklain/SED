--
-- PostgreSQL database dump
--

-- Dumped from database version 10.2
-- Dumped by pg_dump version 10.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: issue_collaborators; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE issue_collaborators (
    user_id integer,
    issue_id integer
);


ALTER TABLE issue_collaborators OWNER TO postgres;

--
-- Name: issues; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE issues (
    id integer NOT NULL,
    name text,
    user_id integer
);


ALTER TABLE issues OWNER TO postgres;

--
-- Name: news; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE news (
    id integer NOT NULL,
    header text,
    author text,
    content text,
    tags text,
    date date,
    image text
);


ALTER TABLE news OWNER TO postgres;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE roles (
    id integer NOT NULL,
    role text
);


ALTER TABLE roles OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    id integer NOT NULL,
    username text,
    password text,
    email text
);


ALTER TABLE users OWNER TO postgres;

--
-- Name: users_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users_roles (
    user_id integer,
    role_id integer
);


ALTER TABLE users_roles OWNER TO postgres;

--
-- Data for Name: issue_collaborators; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY issue_collaborators (user_id, issue_id) FROM stdin;
\.


--
-- Data for Name: issues; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY issues (id, name, user_id) FROM stdin;
1	Для теста	1
\.


--
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY news (id, header, author, content, tags, date, image) FROM stdin;
17	ДОСТИЖЕНИЯ ВИЗУАЛЬНОЙ АНАЛИТИКИ И БИОЭКОНОМИКИ ОБСУДЯТ НА ВСЕРОССИЙСКИХ КОНФЕРЕНЦИЯХ В КЕМГУ	Admin	Кемеровский госуниверситет стал победителем двух конкурсов Минобрнауки на проведение всероссийских научных конференций по визуальной аналитике и инновационному развитию биоэкономики территорий и проблемам менеджмента качества окружающей среды. Напомним, что Министерство образования и науки Российской Федерации продолжает реализацию федеральной целевой программы «Исследование и разработка по приоритетным направлениям развития научно-технологического комплекса России на 2014-2020 годы». Своей основной целью организаторы конференций, которые пройдут на базе опорного вуза Кузбасса, называют выстраивание эффективной коммуникации между научными, коммерческими, исследовательскими и инновационными сообществами по вопросам развития биоэкономики, а также обсуждение современных проблем и достижений в области визуальной аналитики в России и за рубежом.		2017-11-08	
2	В КУЗБАССЕ ОТМЕТИЛИ ЗАСЛУГИ УЧЁНЫХ		9 февраля в областной администрации состоялся торжественный Губернаторский приём, посвящённый празднованию Дня российской науки. Его участниками стали выдающиеся ученые Кузбасса и России. Диплома победителя областного конкурса «Лучшая научная школа 2017 года» удостоен Казин Эдуард Михайлович, профессор кафедры физиологии человека и психофизиологии, доктор биологических наук, профессор. Медалью «За особый вклад в развитие Кузбасса» I степени отмечена Араева Людмила Алексеевна, заведующая кафедрой стилистики и риторики, доктор филологических наук, профессор. Медаль «За особый вклад в развитие Кузбасса» III степени вручена Кирсанову Михаилу Павловичу, заведующему кафедрой техносферной безопасности, доктору технических наук, профессору.  Звание «Почетный профессор Кузбасса» присвоено Шевченко Татьяне Викторовне, профессору кафедры общей и неорганической химии, доктору технических наук. Зяблицкая Галина Ивановна, старший преподаватель кафедры бухгалтерского учета, анализа, аудита и налогообложения, стала победителем областного конкурса «Лучший учебник (учебное пособие) 2017 года.		2018-02-10	data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAQCAwMDAgQDAwMEBAQEBQkGBQUFBQsICAYJDQsNDQ0LDAwOEBQRDg8TDwwMEhgSExUWFxcXDhEZGxkWGhQWFxb/2wBDAQQEBAUFBQoGBgoWDwwPFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhYWFhb/wAARCAB6AOMDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD4uOs3g0P+xoJ2W1LbmjH8R969E+BWnafLdTLqMm2RoflbPSvK7INJdIqjOSBxXV6Tqw0vxdYguRAcJKB6Vz4y7jyrqFFWdz2680TRLrTxyYYbdSNzD/WGvC/Fk8jySWhkY20M7eUue1fSHi6O2vvhLNdaeMtHAXRl7cV8v3khkt1ldiWYkkelceW+9UubYhrl0ItNS3t9QjuXPCHOPU1s+FXvtR8ew3MUQcFgil/uqPU10X7Pvwm1f4paxIIJhZ6bbyBJrpxxn0HvX0H4+/Zv03wx4G+06Fqs09xBzJnv6kV0Y3FUlemtzGjGV1J7HkuueAba9uJrm+1CNiqZRYuPmrkPDkMlj4uW2NwP3B7elbEtzqVncLaQs0h3FWkJ6VyusajJY+Lnjtk8yV/vHGTn2rzacarbhf0OycocvMj1L4xeLvDQ8L2Og2Nj51wzB7yY9cDr75NRat8X4YfA8WmaDB9iuo1wsgXBB9aXWPh/G/wFm8b3kE63CDcWZcbfwPavE4Z2nbf27CvZw8YOFuqPJklJ3NrxV4i1zxBIJdU1O4vZR0aVs4+grDkhZ2VQxJzzmtbRVsnuBFcZDPwKm8RWEOn3QEb7twzTUff5SudLQo6XIdO1OO4ichwcfLXqnhWXUdU1a1sEvGiuJhk+aeFHc0v7Lfw3HivXpNe1WL/iVab8x3D5ZHH9BWt8QPGvhe013U59Os1WWDdBbMnBJHHBHQZqqmEjXu+iFHGOk+Vbsy/jVrDaJcR+HdLvHa7ZR58kT9B747muQ0/wV4h8Q2u2K7RQvRZpOn4VD8O5xqXjqH7bIryXUuZJJTnaO5r6E8VeF/BVvosbaLqbRaoy5RwDtkbuK8/FYl4ZqlSj8zqo0nUXPNnzXqXw78Q6TJvuYkkQsA0qHKr7mvqD4U+Bvhr8O/hcfFuoaxDPq0kG7e7Ac4ztUV5O2tajZ3j2d5bsrHKyK44f3Ga4/wARXW68eF3b7OvKxsc7fanh8XUre7IzxFGy30Pt79lGKK88G33je/1COSW83OsYPywp2X8utfI37TviDVfF3i7VNRW18yxt7kxwzrn7qnGR+Oa6n4FeJNb034O6yljdyLC3meWrPwPpWj+z/wCE/HL+E76HUvCrX9reAsrOgDc9vmrWpU9l72m5lRhds+Z0laTaM8Kea73wlGdW002MSSKxAGSpxXW/C74MS3HxiudJ8UhdNhiYypA/DMCeAM16r8TPAw8D2a3ulyQT2Y7bAGWs8Xj4XUYq7N4UZPW9jz/TPBcenaTgXNu8pQeWjJzmvMvjFoE2i3cGotBHFuOJTGcA+9ehtr082oRuhWRSSORyprk/iprlre/Z7TUI8K8nzKfauWhKTmdUrWPf/wBjLRNP1/4XLfX923mI7BTuxha8f/aU+J2taR441Dw3od7/AKNbHYX68nsK9Q+Aep2EHw1uX091i+QiNVYdcdwK+UvG0k114w1Oa5YtK10+7P1rsw1BNuUkcM7Ko0mdh8P4/EXiLw9dX814ryLMFDzn7wPpXd6Tot1oWoPpuvOsCLCsqyHo4PQj2rw2DU7+HS2sIrqSK3ZtxVDg5HvXYaVq/ivxo8UM00l0bOARpI/Hyj19avGYaEIKd9zSjUnKVuiO/wD7R0CJjG11CxUnJHQ80V5hJNb2kjW8yt5kZIbHrRXB7GJ1+0kYPh3Tvs95LvO7yeh9a1PDegSa34ys7Utt3tkn2qXSrdftbSIS0Ui7sj1rvfg5ov2nxBca9KfKtNLgZ5GPGcDpW9Sq5VTBe7TbO00XxBoHhnw/qWg6rqcYSKEjDsM5x05r5ovL2H+0Lj7OpMbSsUB7KTxR4u1Bta8X32poX23FwTGD6ZwK6XS/hh4qvdK+22tmpaRdyxk/MRXTSp0sNeTdrmVp1Fa1z2H9nD4g2vhr4Zw6dbWU11fI0k5toF5bnlmI7V7H8O/HHiLx7ot3bXOlw6db7dokkc5OR2FfNPwP1nVvBN9eQX9gLK6mi8h5LiLdiPPO3tXvnhfxZpaaKs0AdUA+WRl2hq8vFwSm2le/U6qfwanF/FP4ay6Lps13YzmS9kkBDE4jbd2FeTeAdIvU8YlYfJuNYhlOfNP7tfyr6L8cWureMfhnqFzpAka9jIe0jU8kj0r5eV9W0eSe5lkkgud5EnOHDA88/WuvAqUoSb32OXES1UV2Ou+I/jDxtc6bceBNT1G2jtHOXWFBgr6Z9K43T/Ct0seIJo5PpWLq2rXV3cvO7PJMw5Zjkmuy+DryT3hMxJCLn6V2RXsl7pzVJOKRy8kcml68Ptke0wjPPepI5pdZ1IysdqMcLn09q6zxRpcGu31zIZBGsPG/bmuk/ZX0XQIPFk+ueJIHuLS0lFtYBYiyvKepx3qa2KVOm6nU1o0vaNXLGj/F+PRfh1L4J0jTHsmZSklww2nnqa8n15XXdOj+Yv619AftLeE9A1m7udU8PPCl9br+9jQbenZh6182Sar5TspHIYq6HsaWExzq07L5ouphYwlexqfCuS2bx1ZyagG+ztJhgDivsnwjqujz2sNnBo9v5dj/AKliMn65r4w022vJp4bq1t2XB3cV7T8N/EfiPUvD8lvFJ9naNvLfC/NXHmkL2mmdOEmldM3f2ormyWS3vjLb286nogAz7V414wiWXS11RZRtkGCK7T46aQ0vhWKCa5MuoPIGG5ua4nwfpV/q9vJ4YmVWuZzi3yeGPpmsMJBwhGotrlVpRm3E9S/ZG1HRrrSlsLxkBsj5sqyn5WO7v7V9NWfxD0y3Xyf7Vs0iXspGF/GvlH4MeD/7G1TVdG8UWFzFc6aY5pYreQYnjJyAD3BxXrDeFfDWtXH9ppZXFgj9LdnxuX3A4oxcUqr1ZWGV4XSJP2lb+y1u/sdY0m8ga5t0I86CQbj+XpWf4i1c+J/hhBazXDyak0fLg8ZFWLjQ9JRmhtLOBBGCYwVzub0Jri7q4vG8Q/2TGFhEajzGiHyq3oMVztuUUl0NHSUbybOV0vT1tfiFpulXEwlEi+dOv90L1zXA/HfV7PWfiJePpyKtpbHyY9vRiOp/OuymvzoHiHxJLcEPqM0Pl27SdVBHBH5k14/qDGLIY5cnmvYwcIqN+pwVW3PyNDRfE+saJB5Wn6hNChP3A3yk/Suk8O+F2u7qPWPEkrxrfHzFUKf3n4155kvcKO24c19U6P4q8NHwpa258qW30uFFkPk5CtjvSxtadKK5epdCipt3OB1bw/4OvLVre1hto5wuABLtY/SpfDVhdafbzHSrHfJYxKHBbDSKema0/ilY6D4g0ddX8PpA0sPzPJCNpX6iuE8K63dxasR5lzJJMnluQcKyjn9K4YuVSO+3RnQo8kjtY/Cp1Jft0+jxxyTfMyGUcGis1dbUjL3Mu7POHorP3zTTuec+F9VktLSZcbsLla0tM8fXi2sthG2yG5+WVF/jHpXOaEV81w//ADyOPyqDS7WGTU4hFksWr1+WPMzzZpcquemaTpen3WjjUZbSMbGyoA9K6Sz8fS2hjktZlEioBsHYfSuf1K4XTPBnl5wzDAFcnpF/HPay4j+ZX2h/X1qZYZV2kycPiJUlJ9D1P/hYEN7MJdXsLcxDOTgbm96xdH12LWtWBg1SSHSfPKRwtxtA6nHpXIGQ6jLHYwkI8xCFj2Heut8I6Lpa6lJYhf8AR4UBmKdXC8tz711xy2lFrl9CPrcnfm9T2bW/iPpPg74fxweG9Qhub+5Xy4ihzs9WNeG3k0Gr2N1b3v8ArJ33POwGdxOSaz/E2pW95rE1xZ2qWduzbYIl/gUcD8apNdLDCACfbPf3r1JRpcvJGKUUedTU0+Zu7ZJHpkGlXklxeGG4gMZC7fvD6itTwEg0zQLq9dGj8zPlh1IOO3WucaRnbzSQWU5XI9Oa9Z/bM+I+mapovgnTNJtraN49HWW8aBAMOQBg4+hrzcRRUXeOx0+9UaR4vq3ia4huJbUYaKRiWOOa+mPgB4ntPD3wc0u3jithcTuXWSeLcoZjySexr5RW1hvlLmY72PQc1678E9f12z8F3ekRvFcQ2b/JHKmSAfevJzCknRTXc9XCVEpcrPWviJb+EPJmvo5pPtk6E3Bik+R39cV8keJrVpPGlxBaRkiSX5V9vWvYfF1xfx+H7jUdQYW0eMRov3pG7AV6H+wN4B+GXijTdUv/AB0ttfavLMFhinm2tEmOwqMtpyjzSvoVi6sYpHNfsm/D/UfGvxE07SZoVXT7XE12QPvKOi/ia+0dW+FngbSPOuTpkUK+Xucpx0HU1pfDn4ceA/Bt4974Zs/sckygMVlLAj8TTv2gLixg+D+vXDXc0LxWTsJI2+bOOK7KuHVXSS0PMjVcdU9T86fjpqt5ffF7VDplvIbGGUx25J+XaO9TeFJkthFMsDGZSG83PKn1BqGN7u/ZEbHzDn1H1qeG4tbKOWKQ72jHA7CuiFJcvKloOVR7vc6GHxIBr13Lqcl06XkKiS4BO47egzWovjfTYbRLXRXvJCww4lOcH2rgNP1Q3etWSagDFpfnjzVRcswHYV3PhGX4f3fiC8u9Sv206xhRpoo2jw74/h9jXNVwMpO8Vc6aWLUYWeho2t9qd1o9zPM7KkUTOWLYxgcDNecp8T007TCI7QtfFuR2z65qT4jeMp/EV01jpQ/s3RFbbHFnDzD1b/Csa5/sNNO+zXUSyE98gNn1zVU8vUV72rFUxnMznfEHiXUPEGvPqmoFfOZQuEGAAKl0vwkNdj+0Q6tCr55Rx0rM1mG2tvntnPkk9G+8KqWeoSxTAwSMvPVTW3Jyqy0JcnJXRt+I/Aer6HZi+LR3USEMfL61714T8R+G7jwjbWqaRbL/AGlGk1wxIA4GNpFcLZXgf4exy3DBmKEEnvVfwDqMMtjcWraekz2670O7BCmuHGxcoJ9jbB1byaZ3WqXlrBYXGn6Zp1vb2uw5mRcFx6e9cDpLwXPiWCLRLZpGhcRsXX7xc4OPYV1mm6fdXOk3Etw6xr5DGFM8KSDyfpXB/AHVTZ+PrK1ng85pZgiIvO9gexriw8b3l2OutLSyPZrP4b3wtlA8Pxt15OPWipvFV1+0Je+Ibu50XwfeQ6e8n+jIQpwoGB39s0V3fV5dzyvbS7ny9pMGLOa4kGzCYQmpfAaRnXA8zKoXpk1W2NdaZJcTSsAPuoDgCs6xikkYLA7CRmwpzjHvXRHVs6Jxukmdr46vZr+4j0+xRpnUcKgz/KqVvpt9p8kNs9tMF2ktlerdzXT+ELCx0rR452ne5uJwwmkwQC38I/T9a39N12e1jKyhHgHKEjOR7HqD+lKOKdNvljcj2Kta5xPhm5hsdalnuEBUW74DD+LHFWLfxVJp+l3NnZIPtF6m2SbuinqB9a6bxRfeH9ft2iuIsXO3CTJHtlQ/UcMPY15zLF/Z100UzeZJ1UgcEetd+HxarW0s0YVKDgn1TLAcx/PK244/Kqkd3Lc6hIUcKsY61Be3DMrdRT/C0SvDK7nGXrpvd2M+VJNsvLOzH5zgL3PNaet+E2n8K2uvyXJdJeGVuyg9qy7518tiTtjX8M1teINTkk8FaZo6OQI13v8Aj0FY103ZBG62MmxjRhttLdVjUhVAXlz2Ar6c+Dvwf/sf4ftf6pNsudQUT3BbgRrjIH4Cua/Y7+BuufES7j8SKkcOh6TMPmmB/wBJkHJ2+wrU/bG+J9zbXU3w78MzGNox5eo3KHBQdNi+9eHmXtq9WGHpLTq/67HoYSVOnGVST1PHPjV4itNc8YSWGlSbtN08+WH/AIXYdSKxfC51e71iHTvC1ndXOpScottncoHViR0A9TxWTdRi1sfKjTn+dfcH7FHws8OeHfhHp2va2B/bGup9sucY3LEx/dRk+gXDY9WrsrVIYGgkl5IxUXXm2cx8AdS+I/gbz08Z3p1qGSMeVYw3JeWFsZwXxgcfWul/aH+Jtl4g+Dt54f0qw1C01S7dIzHOAybCeSHB9OxAr17UfBfhS4hb7DLNG0inJEnOfevnz4kWUGm67JDNc5SNv3TZHP8Aj06mvMjmFZy1LlhKaPCWtdS0ax837JJIZAcEIecdcfSsXTYn1G0eVf45D1PQV9Z6T4w8BHwDa2uptZ2t5EjM4dAAzcqWX/eA5FfM+l+HtVv/ABdeaf4b0y5v0knkaBLdN37vOQfoM17GExHtL+7ZHLXpcmt7s2fAukWMFtdaxqYAgtIsW+f4n9q4vXVh8+S5uJAxyW2j1ruvGvhz4iDR4bCbwfqtrp1muPMFs371upY45ry++hvbm8NjFbzRuOCJUKkfXNdqrRa5YO5gqcr3kZVncSXOoS3LLuKHbGh6IPXFXHtLaX559rE+xU1u6XptpYWLqygTZ/eN6n2rA8QXPlynYN6/XpStZGild6HpP7Hvwj8PfFf4wTeGfEE12umR6fJcsbeTa+4EADP41xv7S3hDwn4J+O+qeEPBmoXN3pumbUllncMyy4y6gjrjiuq/ZB8f3Pgvx5qWo2Hlme80x7VTIcGMk/eHuK4i/wDD8uvfFCbSNFvJtQvtVumZrmcZLSHLMWI6DPftXJUlFXua0+ZzsWdP1zW9U0ZfD+lWCSQxoWZY4svtUckmvUP2R/B39vabrOqXTqsZdIEBHPAyf51b+B/we8T6Yt2uo3semSajEYJFWPzZo0zyy7cgE/WvYNJ0TRPhh4GTTNOkkmZA0kj8CaZieuzHb614eMzDDyi6dOV35HfRozjJNrQ8H/aZ1e08O7fCGjTbrlo83sqn7inon1NeJWN9Pp+oR3VrK0U0JDRshwVYdDmvfvG3wcHiGaTWdK1W6mv9RdpSNTZIlds8gMOB2wK8I8feHdW8L6xJpGrW8kNxG+G3DFdmBqUHBQg7sirzOV2dna/HT4l29usMfjTVAqDAAkH+FFebxwrsGUz7k0V32Rz8kexc8QYtrV4o+EZgQKpeHZGGpRLGMs3ygZ7nivftJ03wRazCKSyjmZVGBNyBxUzfDnwlP4gsdXsY44Vt7pJp4kbiUA5I9q8unjoJWkmdsqEnqjk9WtNItp7Gztb51to7dY7wyLjy7kLgvwTlSehrn71L+zs1uop/tVmzbWaH5vKb0YdRn1r2L/hC/CI0m4sbO6jmlvp91zKUCtCMnaiqOg3NkmqvhvwFNo1xJHqetxLbrkNbMvmbx9T2rFYiMVc1lRbPIrHWF8lsPde6sc4/Oq2qMl+3mAgsq53k/eB/z/OpviVYQH4iX8fh+CbyBgyLGPljPQ//AFqd4gtNI02RLeKCfagG9TKZAr4zycAjOc47V6eF5XWi11/yOSvFqnIxY9NuLuTajfL7DgfjVxbSPSrTYJ97ZyQPWkW9vZ8iytyFHBKj5aq6zDcRQNJcTxRtjlSefyr1pVKcX5nnqMpaMrXkxuriOPOSzDirWrXuzK7uQuB+Fa/wX8D6h451C+Gn6hZx3FmqkJK2N4PUj6V6v4o/Zy0TTPhreeINX8chr61gaVbSFF2sR/D61w1sdRhPlctTqhh5vpofRX7B/wAZfAVz8HLfwlY3sen6hpNqXmt5iFaQgElge+TXw/458Tx67421nWpbkGa8v5pG+m84/QCuf0NNPs71Lm21O5tplBwytt6jpkUn9i6YWLtdSMxYlsEZ+taU6bjJsxtG5csZTfSu+SVQEKcd6+svhv8AFVdWhOh6PplxJPpOmqxjjmWQSLHGNxGPu9B16Zr5HVIdOXNpM7Kf7x619g/s+a5pGhfDDQfEugeFpdT1HWNOTTbpoplTyXQ4fqMZbbyTxxjvXn5rGLpx5kdWD+N2HfDz45arq2uPptz4fkg2yCIrbxyvsPvIeD36LirXxy8K3En2XWDI8X2hv3hfjZ7n0r0AXWgaRcf2zb6JZW+rSL+8K4fYT7dAfWuZ+IXiDUZtLfVpdkyqMGInC7P4if8AgOa8GUlzJwVjv5e54J8arNdF0dYhm4jskWeOTaFMkUh5OMnjOD16Gpf2f/jloHgfR7yS1tZTrk8iglkyDEP4VP1rH1nxVH4l1+ea7sIbexytolnACFjhGeB9ck5ry3VrK1TWLj+zDhHkJijJyQM8Cvdy9ylRlSm/6ZwYqnD2ilFH0/rn7W3iCSzK2mjQxSY+VpTuUn3FeUeO/iTqnjbUF1bV0tVuEXYFt4QiqPw615ylr4g2EeTlcdG5zTPs+trGw+xyHd2UV10YYai7wtcwqQqz+I0NY1ktIxLYX0Heua1bVoXykaybj61HqUV+Iy8sUkQJ2jcMc+lZ8dnJJcKgYEsfWuhzvsEKSjuafhue6ila6hcx7Rjdmvaf2U9FB1C98SXZjij2+RDI05R3Ofn245x06dTXkFxZzw2YWMLtUdFrtvh3eatZeGU+zF4zAWll2uGxGcfMMfw5z+Jrysy55Ydxjuzqwyj7S7PrqbxPa6dpHkf2lGpWPd5UPDY9CTn25PPNYLeSJnublw00kgkmZjnG0njnsB+deJWXjjTIUWS9WZY7kDEpOVBHck+/atIazq+o2bJpfmTozHEkbbsr9a+W+pVYa2PTsujPVBrEUsML2MLMQQJ4GPyggYOPX/DFYHxA8PaV41t2tL3T4bhDERazrkvat/dDDkL6dq4fR7vUjZyXbTTskMnkypKB8xHZl59ua2/CPibUrfVJbi4jAEq4KqeE/wAKm06M7wewrKS1PnfxNo9xofiC70mXczWspTJHUdR+hFFfT17Z+HLy6e6vdKtpJ5OXeSIFjxxn8MUV7sc6hyrmjqcTwrueO+JpXSztGztuAvJH8a1o+Db5bjUI4L2Z1Vlwsitjn0NWfFOkie6WLA/cqChU8EY7GsfVNPOlJFcPu8tv41/h96hcso26nXZpXPSbe3mtG/dXHmRPyjsPmHtmruozvqOnvbvc4nQYGetecWOta1DYvHaXsN0F5i3HqKq6b4qvjcqs8TLOWw5B5zWSpzfyBtFjxBrb+Bobic6XZs9wxJluCWZnPRgB94AZ4OK5bS7uWaPzUhD/AGgmWTzvv7m5JNXviFcnWLiHeQ3kffU9TVGzcW0e5mXdjkE8n2r2cHG0OfqebipO/KyZdVWKTYsSowYF0C4wfeobi00i7uJLie1WaaTks5yPyrL1qfVb24aWLT4WijBCSNMFx7jH9aak0gWMl03MuTsfcFPoSOM13Kxy8rWx0ngTxQPCd7cQafaw2clwOXC5DD69qk8VaneeI2kEur3EAddrxK3y4rh/EM7GJZOPl6+1O0vUPNswNzC4h+6c8Ovoaj2NNz5uXUvmny6M9D+Gfhb4bWwY+K1vLyTcNoU4UD8K9Z0nX/hDplmbG00OxsYVXKXTwCRlPvmvnOz1rAGW4+vSrp1WGZPKfDbuK562BVSV+dm0MTKO8Udd8TtEtfGPiaK/03xXoMkAUItvZW/luFz1K+tej+BfhrqV18M5L/R4LqXwvY3DrHPHcbpnmGPNYW+QWTPG7nnPHevJvhbZS6z4wg8M+BvDs2p6pdSqjvBGSkOSAXlkxiONc8seBX3/AKp4Ssfh/wDBO10u2VbptIsj5902UiEmC0k23q3zFjjjPFOrR5KXK5XsZ+3n7VTirHznb3dpYSRwaWsyzDAmLxKjyH/aC8HFdVoln4g1fRb2XS13agIGWzhIDbnPAyG4/PisKxtbG+8ZWbPeR2cF86iS6m+VYlPO5vTirv7YHjzxN8Jvhn4c1n4beItJt4davntoprGCOaUJGgZi7SKcs24A5HQn1rxqOHdeWjO2pWa3WrPH9S8F3HhvxHfWHiLTrjSbmwyHglXaWOMgjsVIOQRwa5qZtNhYiys4UXd97YNxPrmu18bfEa4+Lus2smmaZeW9va2+zy55VkZCeTgqAAM9q5y98L6jEkjpayNtPYV2xq+zTp3HGk5JSe5QZ8puApnnMcANtPpWNqU+oadeuk9vIioecik/tJZ/mDfStEk0Jppl3xBp6augjO1JxgrnoTXG+K9Pj8PYS7u1+2AgiCNO3ua6N71iwZ2+ZehBrvfhL8El+MdnfanF4oh06+hUxBLm2MqnA45ByK0hWVJWm7Iyq03J8yPJNJuo72EbWJz2xWl4BhNj4/g0m8aa3t9X/cP8pXerMBjB4NfRPwO/Zu1jwL4yttb1rWtHvEs94W2WEyKxIwGO7uPSvfbP4drr+6+jvtHvt6GJi9qEeHIwQB2IzXPVxsKl4U1zIcKPL70nY+erjR/hzFb2HhmK8sZ4oVCAiVHbecsSwByATkZ9qsX+saJodo+maPplvEqrjfEOWrmtS0fXNA8Zar4Nl0jT47i2doJbo2sheUDjfu6cgA5GBUFrbQWUHk6jJK8kPVn4Q/Q9xXHNdGz0o8ujSOX1jxFJa6kgl3xqr5Kr/EQcgkfTj8KtW/jbTpZJDBaebIV27V/r+tcx44vW1K4d9PiH7l8tIy5BH071zWknUrSJmtURWLZcAZYjsSar6rGyn1MJSu7HoVx4v13zm2aMWXPByKKp6Tb+JZtOhlXTr6QOuQyQFlP0Peil7Zr7C+4y9lH+Z/ebfwx0nVtZvo4GaaOCEDzDKOPoK7H4leG7CHRvKjkDhU5Hoah0/wAX2S262vkJZ3jICdn3W461JJOLqF/tBJXGcnvXNUnLnvax2ctkeJXbXVjFJaWkblg/GB2qvC9/LMbeclbmAgg9+eRXqckWmaXcFri3aR3VmjKrnn0NeYXuoRf8JFcTTELJcOen8JHSu6lPnvZGM1ZBummaWbeBKp+bcOlZeqXCWfy20X2i7l6MTn8z6e1bd1eWDxy3jSKr7BvXGPxrl7zVNIubraLm7t5eheIHb/jXpYb4Dz66bmSw2GvsrOl5HJgZe2ZB5bZ7EV0Xwz1TS/D2qNez2psjMvlXVpcxCWFxnIZc9CMVhaZYzbvtNtfXknozKQD+dT6okpjL3ckkm1flBUH+RrZtPQycW1Znt9m3g/X7VUuPD+mXgm4226eXIR67TkEe/Fcp40+GHhYF7zSkuNPlhYk2/nExOBzg5+6cehxweK5r4W6sUuoWiZgrqMZ7D0xXbeOdTYo4MvzeUpYDvwf8ax1XU4/ejK1zB0w+BbEtYajo226hO2XzD826ud8ea34UtdJlg0XTE+2E7VlYnEfv71zfxI1EtrlvJyZDZxiQg9SM4z+GK51me4kRY0ZpHPCryWYnAH1rSMXuzop0no7n0J/wTK8R3GlftOWuhBbh7PxPYzWN0IY2faVHmxuwUHCh0wWPADZOK+wf2wvGs2naT4f8E2Qm1Ea1eZvJAm2Sa3jYbohjruLKuepwazf2LfA+mfC34R6XHYaP5PirVrZZdXu5VUzNIx3eVvXqiAgBc47kZzXs9vo/hq01Y+NtUT7TqGg6fN/pUseVtUy0krpnA3HGM9QF4xk1lWvUi1FlKcVUu0cJpvhvwd4O+GOoa3r/AIXjuZrfTpbiGK9Ty5jFHEX2AOPkchT1wc1+e37VHx0ufjLqWleT4YtfDulaMkgtLKGczM7PtBd32qPuooACgDnrmvoX49ftR6J8VdPuPBfw18OeJNYvdVheKa9nsjssYGXbJIltFvZ3CsQHbAXdnmvjDxVpsWja9c6XDeLdfZ32GQKFww4K8EjIPoauhRjSikkaRjKbc5I+kP2eV07RvhtazQ3UB1C+kG6MgFjnsK9K8eNL4fms5LmDNvKgeYImSK574A+E/C3hf4baT4j10edcxKJeeecdhXfaf468M+KNWKvEskLnGCv3QK8etL942j0acJOJzN1B4S8X2LWtnpb7pUwWaIg5r5u+JHhq58K+Lp9NKsI2O6LPpX2zfa5oGi2DGxtoMY4IUZr5f/aG1mz1rXxcKo81CenpVYSUnUtbQmquWGp5pZxHBD556161+zf4lm8MzX6RzmNJyCcHrXlP2uMEgJk1LbavNbHMIZffFehWoxqRcWc0akk7n1H4g+KNtb2eZtRhhZhlUZ/mb6CsHwj+0XH4V8RLfpm7t5PlvLcZG9ezKf7wr53kv2uJGkmDMzdWbrVaYoy8VphcFgKVnOLlL1svwX6mNapiJ6KSS9P6/I9X/aG+PA8X+LptU8KwXmnRXcSJcxXbKxLKNoZSvTKgcHuK8vXW7zVrxY5rllfOG5ILH3rFvo96lcc44NYt1DIkhCSyFu53EAV2VMJhqjvQXL+P56jo1qsI8tR3PWoNM3aeyZWNdvLswAP41l6fd+G9C8R295qVxDf29ud72kTFvPOfu5HAxg559BXmiq8jfv55XiXqWc8/SrFmjXE6sI+CwVFHA9lFVh8mjJ3qT08v6ZdXFu3uo+vPDf7RFouiW62HgC7W2VSIljuYwoXJ4AxRXjnh3THTRbdfMkO1cEjpnJ6UVwzllSk0qLfzl/mYqji2r8/4If4blt7OH+0sh1j4VZH3HFbeoeKTdWI+zMuegC15KkkgtdokYLu6A8dK0vCLN9vxuPX1rzalFNuTPYdRs9Q0WKaezea8cMzKce1eSeLtKa28Qszy+pIHrXrehk/Yzz3FeW+MyW8TXOST+970YK/tWZ1vhMi+0ua9ijEE5VjwAfusfQ1z/wBnnsNa8i8haKRTyrf55rvdKVdkfyjr6VB8YUT+zdPl2L5m7G/HzY9M16kZa2OSS0uU9PvpJV2QRs4XgkDjP1pNUecQnzEOD1wQaXwzxboB/dFS+IP+PdqX2iL6EnwrlDX4AHEa8D2rc8YaoZJJRnG9gq/hxWF8KQPtEpx/DR4mJ/tJR/tU3qzjcbyMTW7cXut3F1eZjtYkyCv3ioGAPbNYVvPLBdLPBI0UkbBo3Q4KMOhB9RXQ2/zeGdUZuW8ocnr94VzP8VVSbaZ2uKjZI/Uj9iv4laB4y+COk6stwLjVtPt47LVUY5e0uUXbucddsgAdXPBJI6iuX/bB+JY0L9m/xC8eoeXc67ZtpDODsivriTiQW6ZyQiF90nTtzkV8K/s76xq+jfFSxk0jVb3T3uI5YpmtLh4jImwnaxUjIyAcHjiuo/bgv765+Oktnc3txNb2OnWqWsMkrMlurRKzBFJwoLEkgdSc0cvvWOb2a5yT9lf9oXxB8B9N8UR+GtB0u8vfEVtHHFe3W4SWLx79rDH31+ckocAlVPbFeR6le3V9qNxqF9cSXF3dzNPPNIctJIzFmYnuSSTUC0n8VaKKTb7nQfUHwt8Vzah4HtNsS3KQRbPLY8Z+ldV4dutU1DSZo7Kyt4Bn5nEeCK8p/ZBJfU5InO5M/dPI6+lfTOoRRQ6BJ5UaR/7qgfyrwcV+7qSSPRoyvBXR5J4g1TUIF+xJK0r4wfauas/h/qniDUjNPv2sa6u1UN4hk3AN83cV698PYovJX90nT+6KlVpU43W5nVV3Znlej/BWMQgvCSfpVtvg/bJ1gH5V9BIqi3GFH5VSvFG1uB+VcssTUb3HGCsfMHjj4eW+n27OsQXAryfVLVYbho8jg19Q/GL/AI9ZPpXzD4oJ/tWTnvXq4GpKa1OWtGxmzQKVwKzby1DZHb271qN0qCevTg2mc5lx6arMZbl/Lgj5OOT+HvVnw+pl1qBlj2KW2W8fpz1+tOv+VUHpnpSeFyf+EysxnpKmPzr6i6WEdl0PPheVbVn03oPhGS20e2hWNWCxjk+p5P6mit2xkk+xx/O33R3or8vlUk5PU+mUVY//2Q==
\.


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY roles (id, role) FROM stdin;
1	ADMIN
2	USER
3	MANAGER
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (id, username, password, email) FROM stdin;
1	Neoniklain	12345	vet-vash@yandex.ru
2	test	12345	test@mail.com
\.


--
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users_roles (user_id, role_id) FROM stdin;
1	1
2	1
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 2, true);


--
-- Name: issues issues_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY issues
    ADD CONSTRAINT issues_pkey PRIMARY KEY (id);


--
-- Name: news news_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY news
    ADD CONSTRAINT news_pkey PRIMARY KEY (id);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: issue_collaborators issue_collaborators_issue_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY issue_collaborators
    ADD CONSTRAINT issue_collaborators_issue_id_fkey FOREIGN KEY (issue_id) REFERENCES issues(id);


--
-- Name: issue_collaborators issue_collaborators_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY issue_collaborators
    ADD CONSTRAINT issue_collaborators_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: users_roles users_roles_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users_roles
    ADD CONSTRAINT users_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES roles(id);


--
-- Name: users_roles users_roles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users_roles
    ADD CONSTRAINT users_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);


--
-- PostgreSQL database dump complete
--

