create table usuario (
	id INT AUTO_INCREMENT, 
	login varchar(30), 
	senha varchar(30), 
	PRIMARY KEY (id)
);

insert into usuario values ('', "joe", "joe");
insert into usuario values ('', "admin", "admin");