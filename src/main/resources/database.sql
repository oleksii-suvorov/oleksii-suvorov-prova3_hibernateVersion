create DATABASE localGisfo;
CREATE SCHEMA IF NOT EXISTS newfont_dati;

create table IF NOT EXISTS newfont_dati.projects (
    pk_projects INT primary key not NULL
);

create table IF NOT EXISTS newfont_dati.pcab_nodes (
	pk_pcab_nodes INT not null, 
	id_entity_classification int not null, 
	id SERIAL primary KEY
);

INSERT INTO newfont_dati.projects 
values
    (11111111),
	(22222222),
	(66666666),
	(77777777),
	(12121212),
	(12345678),
	(99999999),
	(44444444),
	(00000000),
	(33333333);

INSERT INTO newfont_dati.pcab_nodes 
values
	(11111111, 123),
	(22222222, 123),
	(11111111, 123),
	(22222222, 322),
	(66666666, 123),
	(66666666, 321),
	(77777777, 321),
	(22222222, 123),
	(99999999, 123),
	(12121212, 321);




