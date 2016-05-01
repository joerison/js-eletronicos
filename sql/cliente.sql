create table cliente (
	id INT AUTO_INCREMENT, 
	nome varchar(30), 
	cpf varchar(30), 
	email varchar(30), 
	celular varchar(30), 
	sexo char(1), 
	PRIMARY KEY (id)
);

INSERT INTO cliente values ('',"Fernando Souza", "123.456.789-01", "fernandosouza@email.com", "8888-8888", 'M');
INSERT INTO cliente values ('',"Fernanda Silva", "789.456.789-01", "fernandasilva@email.com", "9999-9999", 'F');



