create database pro4system;
use pro4system;

create table empresas(
	id int not null auto_increment,
    nome varchar(50),
    primary key (id)
);

create table usuarios(
	id int not null auto_increment,
	nome varchar(30) not null,
    email varchar(50) not null,
    telefone varchar(15),
    empresa int not null,
    foreign key (empresa) references empresas(id),
    cargo varchar(30),
    senha varchar(30) not null,
    tipo int not null,
    primary key (id)
);

create table projetos(
	id int not null auto_increment,
    nome varchar(50) not null,
    empresa int not null,
    foreign key(empresa) references empresas(id),
    primary key (id)
);

create table participantes (
	idProjeto int not null,
    foreign key (idProjeto)references projetos(id),
    idUsuario int not null,
    foreign key (idUsuario) references usuarios(id)
);

create table mensagens(
	id int not null auto_increment,
    titulo varchar(50),
    texto text,
    remetente int not null,
    foreign key (remetente) references usuarios(id),
    projeto int not null,
    foreign key (projeto) references projetos(id),
    tipo int,
    dia date,
    hora time,
    primary key(id)
);

create table relatorio (
	idUsuario int not null,
    foreign key (idUSuario) references usuarios(id),
    idProjeto int,
    foreign key(idProjeto) references projetos(id),    
    interacoes int,
    tempoRespostaTotal int
);