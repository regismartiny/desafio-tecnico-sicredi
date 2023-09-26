--pauta valida
INSERT INTO pauta (id, nome, descricao, usuario, versao)
VALUES (100, 'PAUTA TESTE', 'DESCRICAO PAUTA TESTE', 'TESTE-INTEGRACAO', NOW())
;

--sessao valida
INSERT INTO sessao_votacao (id, data_inicio, data_fim_validade, id_pauta, usuario, versao)
VALUES (100, NOW(), DATEADD(MINUTE, 5, NOW()), 100, 'TESTE-INTEGRACAO', NOW())
;

--sessao nao iniciada
INSERT INTO sessao_votacao (id, data_inicio, data_fim_validade, id_pauta, usuario, versao)
VALUES (101, DATEADD(HOUR, 1, NOW()), DATEADD(HOUR, 2, NOW()), 100, 'TESTE-INTEGRACAO', NOW())
;

--sessao encerrada
INSERT INTO sessao_votacao (id, data_inicio, data_fim_validade, id_pauta, usuario, versao)
VALUES (102, DATEADD(MINUTE, -10, NOW()), DATEADD(MINUTE, -5, NOW()), 100, 'TESTE-INTEGRACAO', NOW())
;