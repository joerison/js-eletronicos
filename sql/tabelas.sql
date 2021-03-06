create table cliente (
	id INT AUTO_INCREMENT, 
	nome varchar(30), 
	cpf varchar(30), 
	email varchar(30), 
	celular varchar(30), 
	sexo char(1), 
	PRIMARY KEY (id)
);

create table funcionario (
	id INT AUTO_INCREMENT, 
	nome varchar (30),
	cpf varchar (20),
	email varchar (20),
	celular varchar (20),
	sexo char(1),
	login varchar(30), 
	senha varchar(30), 
	PRIMARY KEY (id)
);

create table categoria (
	id INT AUTO_INCREMENT, 
	nome varchar (30),
	PRIMARY KEY (id)
);

create table produto (
	id INT AUTO_INCREMENT, 
	nome varchar (30),
	preco decimal (10,2),
	id_categoria INT NOT NULL,
    PRIMARY KEY (id),
	CONSTRAINT produto_categoria FOREIGN KEY(id_categoria) REFERENCES categoria(id)
);

create table venda (
	id INT AUTO_INCREMENT, 
	id_cliente INT NOT NULL,
	id_funcionario INT NOT NULL,
	desconto decimal (10,2),
	total decimal (10,2),
	data date,
    PRIMARY KEY (id),
	CONSTRAINT venda_id_cliente FOREIGN KEY(id_cliente) REFERENCES cliente(id),
	CONSTRAINT venda_id_funcionario FOREIGN KEY(id_funcionario) REFERENCES funcionario(id)
);

create table venda_item(
	id INT AUTO_INCREMENT,
   	id_venda INT NOT NULL,
	id_produto INT NOT NULL,
	qtd int,
	total decimal (10,2),
	PRIMARY KEY (id),
	CONSTRAINT venda_item_id_venda FOREIGN KEY(id_venda) REFERENCES venda(id),
	CONSTRAINT venda_item_id_produto FOREIGN KEY(id_produto) REFERENCES produto(id)
);

insert into funcionario values (NULL, "Joerison Silva", "04061269178","joerison@gmail.com","8560-8939",'M',"joe", "joe");

INSERT INTO cliente values (NULL,"Fernando Souza", "123.456.789-01", "fernandosouza45645@email.com", "8888-8888", 'M');
INSERT INTO cliente values (NULL,"Carolina Silva", "789.456.789-01", "carolinasilva90899@email.com", "9999-9999", 'F');

insert into categoria values (NULL, "Console");
insert into categoria values (NULL, "Periferico");
insert into categoria values (NULL, "Jogo");

INSERT INTO produto  values (NULL,"Xbox 360", '1100.00', 1);
INSERT INTO produto  values (NULL,"Teclado", '50.00', 2);
INSERT INTO produto  values (NULL,"Zelda", '150.00', 3);
INSERT INTO produto  values (NULL,"Mario", '110.00', 3);

