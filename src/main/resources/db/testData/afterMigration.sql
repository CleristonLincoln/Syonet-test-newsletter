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



insert into cliente (id, nome, email, dataNascimento) VALUES
   (1, 'Nome teste um', 'teste@test.com', CURRENT_DATE),
   (2, 'Nome teste dois', 'teste2@test.com', null);




unlock tables;