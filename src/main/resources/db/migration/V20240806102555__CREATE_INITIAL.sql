create table cliente(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL ,
    dataNascimento DATE
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8mb4;

create table noticia(
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL ,
    link VARCHAR(255)
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8mb4;