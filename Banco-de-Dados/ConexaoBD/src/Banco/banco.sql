create table veiculo(
	codigo integer not null,
	placa char(7) not null,
	tipo integer not null,
	capacidade integer not null,
	unpac char(5) not null,
	primary key(codigo)
);

CREATE SEQUENCE posicao_seq;

create table posicao(
	codigo integer not null,
	seq integer DEFAULT NEXTVAL('posicao_seq'),
	datahora timestamp not null,
	latitude float not null,
	longitude float not null,
	primary key(codigo),
	foreign key(codigo) references veiculo 
);

