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

insert into funcionario values ('', "Joerison Silva", "04061269178","joerison@gmail.com","8560-8939",'M',"joe", "joe");
insert into funcionario values ('', "admin", "admin");