insert into usuario (nome,email, senha, perfil) values ('Usuario 1', 'usuario1@gmail.com', '123', 'CLIENTE');
insert into usuario (nome,email, senha, perfil) values ('Usuario 2', 'usuario2@gmail.com', '123', 'CLIENTE');
insert into usuario (nome,email, senha, perfil) values ('Gerente 1', 'gerente1@gmail.com', '123', 'GERENTE');
insert into usuario (nome,email, senha, perfil) values ('Gerente 2', 'gerente2@gmail.com', '123', 'GERENTE');

insert into demanda (nome, descricao, situacao, data_inicio, data_cadastro, cliente_id)
values('Projeto Ajuste no Cadastro ', 'Primeiro projeto no sistema Zeus', 1, TO_TIMESTAMP('2018-12-01', 'YYYY-MM-DD'), CURRENT_TIMESTAMP, 1 );
insert into demanda (nome, descricao, situacao, data_inicio, data_cadastro, cliente_id)
values('Serviço de Consultoria', 'Primeiro serviço de consultoria', 2, TO_TIMESTAMP('2018-12-01', 'YYYY-MM-DD'), CURRENT_TIMESTAMP, 1 );
insert into demanda (nome, descricao, situacao, data_inicio, data_cadastro, cliente_id)
values('Projeto de Conta Corrente', 'Descrição do Projeto de Conta Corrente', 6, TO_TIMESTAMP('2018-12-01', 'YYYY-MM-DD'), CURRENT_TIMESTAMP, 1 );
insert into demanda (nome, descricao, situacao, data_inicio, data_cadastro, cliente_id)
values('Projeto Cadastro de Clientes', 'Descrição do Projeto Cadastro de Clientes', 6, TO_TIMESTAMP('2018-12-01', 'YYYY-MM-DD'), CURRENT_TIMESTAMP, 2 );
insert into demanda (nome, descricao, situacao, data_inicio, data_cadastro, cliente_id)
values('Defeito no sistema', 'Descrição do defeito do sistema em ambiente de produção', 1, TO_TIMESTAMP('2018-12-05', 'YYYY-MM-DD'), CURRENT_TIMESTAMP, 2 );