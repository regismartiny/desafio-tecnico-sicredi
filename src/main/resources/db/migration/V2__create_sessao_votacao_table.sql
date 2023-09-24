CREATE TABLE sessao_votacao (
	id serial NOT NULL,
	data_inicio TIMESTAMP without TIME ZONE NOT NULL,
	data_fim_validade TIMESTAMP without TIME ZONE NOT NULL,
	id_pauta INTEGER NOT NULL,
	usuario VARCHAR(20) NOT NULL,
	versao TIMESTAMP without TIME ZONE NOT NULL,
	CONSTRAINT pk_sessao_votacao PRIMARY KEY ( id ),
	CONSTRAINT fk_sessao_votacao_pauta FOREIGN KEY (id_pauta) REFERENCES pauta(id) ON UPDATE CASCADE ON DELETE NO ACTION
);

CREATE SEQUENCE sessao_votacao_seq
    START WITH 1
    INCREMENT BY 1;