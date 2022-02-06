create database Welfare;
use Welfare;

create table adm(
	ID int primary key auto_increment unique not null,
    Nome varchar(50) not null,
    EMail varchar(50) not null unique,
    Telefone varchar(15) not null,
    Senha varchar(15) not null,
    Perfil varchar(20) not null default "Administrador",
    DataVencimento date not null
);

insert into adm(Nome, EMail, Telefone, Senha, Perfil, DataVencimento)
values ('Administrador', 'admin@admin.com' , '(00)000000000', 'admin', 'Administrador', '9999-99-99');

create table pacientes(
	ID int primary key auto_increment unique not null,
    Nome varchar(50) not null,
    EMail varchar(50) not null unique,
    Telefone varchar(15) not null,
    Senha varchar(15) not null,
    Perfil varchar(20) not null default "Paciente",
    IDADM int not null,
	foreign key (IDADM) references adm (ID)
);

insert into pacientes(Nome, EMail, Telefone, Senha, IDADM)
values ('Paciente', 'paciente@paciente.com' , '(00)000000000', 'paciente', '1');

create table consultas(
	ID int primary key auto_increment unique not null,
    Nome varchar(150) not null,
    EMail varchar(150) not null,
    Telefone varchar(15) not null,
    Dia varchar(10) not null,
    Hora varchar(10) not null,
    IDADM int not null,
	foreign key (IDADM) references adm (ID)
);

create table orientação(
	ID int primary key auto_increment unique not null,
    Nome varchar(150) not null,
    EMail varchar(150) not null,
    Orientação varchar(500) not null,
    IDADM int not null,
	foreign key (IDADM) references adm (ID)
);

create table cardápio(
	ID int primary key auto_increment unique not null,
    Nome varchar(150) not null,
    EMail varchar(150) not null,
    Dia varchar(10) not null,
    Domingo varchar(500),
    Segunda varchar(500),
    Terça varchar(500),
    Quarta varchar(500),
    Quinta varchar(500),
    Sexta varchar(500),
    Sábado varchar(500),
    IDADM int not null,
	foreign key (IDADM) references adm (ID)
);

create table metas(
	ID int primary key auto_increment unique not null,
    Nome varchar(150) not null,
    EMail varchar(150) not null,
    Metas varchar(500) not null,
    IDADM int not null,
	foreign key (IDADM) references adm (ID)
);

create table atualização(
	ID int primary key auto_increment unique not null,
    AtualizaçãoObrigatória int not null default 0,
    Versão varchar (150) not null
);
