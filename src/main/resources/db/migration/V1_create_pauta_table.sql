SET client_encoding TO utf8;

CREATE TABLE pauta (
	id serial NOT NULL,
	nome VARCHAR(20) NOT NULL,
	descricao VARCHAR(100) NOT NULL,
	usuario VARCHAR(20) NOT NULL,
	versao TIMESTAMP without TIME ZONE NOT NULL,
	CONSTRAINT pk_pauta PRIMARY KEY ( id )
);

CREATE SEQUENCE pauta_seq
    START WITH 1
    INCREMENT BY 1;