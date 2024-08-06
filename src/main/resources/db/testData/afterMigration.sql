set foreign_key_checks = 0;

lock tables cliente write, noticia write;

delete
from cliente;
delete
from noticia;


set foreign_key_checks = 1;

alter table cliente
    auto_increment = 1;
alter table noticia
    auto_increment = 1;



insert into cliente (id, nome, email, data_nascimento) VALUES
   (1, 'Nome teste um', 'teste@test.com', CURRENT_DATE),
   (2, 'Nome teste dois', 'teste2@test.com', null);


insert into noticia (id, titulo, descricao, link) VALUES
    (1, 'Titulo aleatorio 1', 'uma descrição 1', 'http"//localhost:8080/syonet'),
    (2, 'Titulo aleatorio 2', 'uma descrição 2', null);

unlock tables;