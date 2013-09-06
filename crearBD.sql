

DROP TABLE RAIZ;

create table RAIZ(
RAIZ VARCHAR(50) NOT NULL,
NATURALEZA VARCHAR(5)
);

DROP TABLE CLASIFICACION_RAIZ;

CREATE TABLE CLASIFICACION_RAIZ (
RAIZ VARCHAR(50) NOT NULL,
TERMINACION VARCHAR(30) NOT NULL,
TIPO VARCHAR (20),
GENERO VARCHAR(5),
NUMERO VARCHAR(5)
);

insert into raiz values ('mane','V');
insert into raiz values ('conduc','V');
commit;

insert into clasificacion_raiz values('mane','ja',null,'N','S');
insert into clasificacion_raiz values('mane','jar',null,'N','S');
insert into clasificacion_raiz values('mane','jo',null,'N','S');
insert into clasificacion_raiz values('mane','jando',null,'N','S');
insert into clasificacion_raiz values('conduc','e',null,'N','S');
commit;