create table venda (
	id INT AUTO_INCREMENT, 
	id_cliente INT NOT NULL,
	id_funcionario INT NOT NULL,
	desconto decimal (10,2),
	total decimal (10,2),
	totalComDesconto decimal (10,2),
    PRIMARY KEY (id),
	CONSTRAINT venda_id_cliente FOREIGN KEY(id_cliente) REFERENCES cliente(id),
	CONSTRAINT venda_id_funcionario FOREIGN KEY(id_funcionario) REFERENCES funcionario(id)
);

create table venda_produto(
	id INT AUTO_INCREMENT,
   	id_venda INT NOT NULL,
	id_produto INT NOT NULL,
	qtd int,
	total decimal (10,2),
	PRIMARY KEY (id),
	CONSTRAINT venda_produto_id_venda FOREIGN KEY(id_venda) REFERENCES venda(id),
	CONSTRAINT venda_produto_id_produto FOREIGN KEY(id_produto) REFERENCES produto(id)
)

INSERT INTO produto  values (NULL,"Xbox 360", '1100,234', '1');

