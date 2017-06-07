CREATE OR REPLACE FUNCTION f_replica()
  RETURNS trigger AS
$BODY$
DECLARE 
BEGIN
PERFORM dblink_connect('conexao','host=amazon.kressin.com.br port=8080 user=postgres password=@Sd!()$2@!7 dbname=sd_rastreamentos_database_replicacao');
PERFORM dblink_exec('conexao','INSERT INTO veiculo (codigo, placa, tipo, capacidade, unpac) 
			VALUES ('||CHR(39)||new.codigo||CHR(39)||', '||CHR(39)||new.placa||CHR(39)||', '||CHR(39)||new.tipo||CHR(39)||', '||CHR(39)||new.capacidade||CHR(39)||', '||CHR(39)||new.unpac||CHR(39)||')');
PERFORM dblink_disconnect('conexao');
RETURN new;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION f_replica()
  OWNER TO postgres;

create trigger t_Replica AFTER insert
on veiculo for each row
execute procedure f_Replica();



CREATE OR REPLACE FUNCTION f_replica_update_veiculo()
  RETURNS trigger AS
$BODY$
DECLARE 
BEGIN
PERFORM dblink_connect('conexao','host=amazon.kressin.com.br port=8080 user=postgres password=@Sd!()$2@!7 dbname=sd_rastreamentos_database_replicacao');
PERFORM dblink_exec('conexao','UPDATE veiculo SET placa = ' ||CHR(39) || new.placa ||CHR(39) || ', tipo = '||CHR(39) || new.tipo ||CHR(39) ||', capacidade = ' || new.capacidade || ', unpac = ' ||CHR(39) || new.unpac ||CHR(39) || ' WHERE codigo = ' || new.codigo); 
PERFORM dblink_disconnect('conexao');
RETURN new;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION f_replica_update_veiculo()
  OWNER TO postgres;


create trigger t_replica_update_veiculo AFTER update
on veiculo for each row
execute procedure f_replica_update_veiculo();



CREATE OR REPLACE FUNCTION f_replica_delete_veiculo()
  RETURNS trigger AS
$BODY$
DECLARE 
BEGIN
PERFORM dblink_connect('conexao','host=amazon.kressin.com.br port=8080 user=postgres password=@Sd!()$2@!7 dbname=sd_rastreamentos_database_replicacao');
PERFORM dblink_exec('conexao','DELETE FROM veiculo WHERE codigo = ' ||old.codigo); 
PERFORM dblink_disconnect('conexao');
RETURN old;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION f_replica_delete_veiculo()
  OWNER TO postgres;

create trigger t_replica_delete_veiculo AFTER delete
on veiculo for each row
execute procedure f_replica_delete_veiculo();

CREATE OR REPLACE FUNCTION f_replica_posicao()
  RETURNS trigger AS
$BODY$
DECLARE 
BEGIN
PERFORM dblink_connect('conexao','host=amazon.kressin.com.br port=8080 user=postgres password=@Sd!()$2@!7 dbname=sd_rastreamentos_database_replicacao');
PERFORM dblink_exec('conexao','INSERT INTO posicao (codigo, datahora, latitude, longitude) 
			VALUES ('||CHR(39)||new.codigo||CHR(39)||', '||CHR(39)||new.datahora||CHR(39)||', '||CHR(39)||new.latitude||CHR(39)||', '||CHR(39)||new.longitude||CHR(39)||')');
PERFORM dblink_disconnect('conexao');
RETURN new;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION f_replica_posicao()
  OWNER TO postgres;


create trigger t_replica_posicao AFTER insert
on posicao for each row
execute procedure f_replica_posicao();
