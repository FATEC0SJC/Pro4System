describe clientes;
create database bd_system2;
create table clientes (
	nome varchar(50) not null,
    empresa varchar(50),
    cargo varchar(40),
    telefone varchar(20),
    email varchar(80) not null,
    primary key (email)
);
create table projetos (
	id_projeto int not null auto_increment,
    nome varchar(50) not null,
    cliente varchar(30) not null,
    foreign key(cliente) references clientes(email),
    empresa varchar(40) references clientes(empresa),
    primary key(id_projeto)
);
create table mensagens (
	id int not null auto_increment,
    remetente varchar(30) not null,
    foreign key (remetente) references clientes(email),
    titulo varchar(50) not null default "Sem Titulo",
    texto text,
    projeto int,
    foreign key (projeto) references projetos(id_projeto),
    data varchar(10),
    primary key(id)
);
