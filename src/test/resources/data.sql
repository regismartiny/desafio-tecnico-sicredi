INSERT INTO pauta (id, nome, descricao, usuario, versao)
VALUES (100, 'PAUTA TESTE', 'DESCRICAO PAUTA TESTE', 'TESTE-INTEGRACAO', NOW())
;

INSERT INTO sessao_votacao (id, data_inicio, data_fim_validade, id_pauta, usuario, versao)
VALUES (100, NOW(), DATEADD(MINUTE, 5, NOW()), 100, 'TESTE-INTEGRACAO', NOW())
;