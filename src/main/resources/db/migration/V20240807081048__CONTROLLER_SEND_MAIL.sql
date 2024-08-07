CREATE TABLE noticia_x_cliente
(
    id INT PRIMARY KEY AUTO_INCREMENT,

    id_noticia INT NOT NULL,
    id_cliente INT NOT NULL,

    CONSTRAINT fk_noticia_x_cliente_noticia FOREIGN KEY (id_noticia) REFERENCES noticia (id),
    CONSTRAINT fk_noticia_x_cliente_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id)

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;