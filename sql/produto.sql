create table produto (
	id INT AUTO_INCREMENT, 
	nome varchar (30),
	preco decimal (10,2),
	id_categoria INT NOT NULL,
    PRIMARY KEY (id),
	CONSTRAINT produto_categoria FOREIGN KEY(id_categoria) REFERENCES categoria(id)
);

INSERT INTO produto  values (NULL,"Xbox 360", '1100,234', '1');
INSERT INTO produto  values (NULL,"Zelda", '150,234', '3');
INSERT INTO produto  values (NULL,"Mario", '110,234', '3');
