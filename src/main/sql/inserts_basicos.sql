insert into usuario (nome,email, senha, perfil) values ('Usuario 1', 'usuario1@gmail.com', '123', 'CLIENTE');
insert into usuario (nome,email, senha, perfil) values ('Usuario 2', 'usuario2@gmail.com', '123', 'CLIENTE');
insert into usuario (nome,email, senha, perfil) values ('Gerente 1', 'gerente1@gmail.com', '123', 'GERENTE');
insert into usuario (nome,email, senha, perfil) values ('Gerente 2', 'gerente2@gmail.com', '123', 'GERENTE');

insert into demanda (nome, descricao, data_inicio, data_cadastro, cliente_id)
values('Projeto Ajuste no Cadastro ', 'Primeiro projeto no sistema Zeus', TO_TIMESTAMP('2018-12-01', 'YYYY-MM-DD'), CURRENT_TIMESTAMP, 1 );
insert into demanda (nome, descricao, data_inicio, data_cadastro, cliente_id)
values('Defeito no sistema', 'Descrição do defeito do sistema em ambiente de produção', TO_TIMESTAMP('2018-12-05', 'YYYY-MM-DD'), CURRENT_TIMESTAMP, 2 );