SET client_encoding TO utf8;

CREATE TABLE voto (
	id serial NOT NULL,
	cpf_associado VARCHAR(11) NOT NULL,
	opcao_voto INTGER NOT NULL,
	id_sessao_votacao INTEGER NOT NULL,
	usuario VARCHAR(20) NOT NULL,
	versao TIMESTAMP without TIME ZONE NOT NULL,
	CONSTRAINT pk_voto PRIMARY KEY ( id ),
	CONSTRAINT fk_voto_sessao_votacao FOREIGN KEY (id_sessao_votacao) REFERENCES sessao_votacao(id) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE SEQUENCE voto_seq
    START WITH 1
    INCREMENT BY 1;