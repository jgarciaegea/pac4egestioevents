-- Se añade la posibilidad de prerequisitos de los evento
CREATE TABLE EVENTOREQUISITOS
(
   id_evento integer NOT NULL, 
   id_evento_req integer NOT NULL, 
   CONSTRAINT pk_eventorequisitos PRIMARY KEY (id_evento, id_evento_req), 
   CONSTRAINT fk_id_evento FOREIGN KEY (id_evento) REFERENCES EVENTO (id_evento) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT fk_id_evento_req FOREIGN KEY (id_evento_req) REFERENCES EVENTO (id_evento) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE EVENTOREQUISITOS OWNER TO tdp;

-- Se añade la funcionalidad de fecha inicio con fecha fin evento
ALTER TABLE EVENTO RENAME fecha_celebracion  TO fecha_inicio_celebracion;

ALTER TABLE EVENTO
   ADD COLUMN fecha_fin_celebracion date NOT NULL;

-- Se elimina créditos según solución del consultor
ALTER TABLE EVENTO DROP COLUMN creditos;

-- Se añade la posibilidad de gestión de plazas por rol
CREATE TABLE EVENTOROLPLAZAS
(
   id_evento integer NOT NULL, 
   id_rol integer NOT NULL, 
   plazas integer NOT NULL, 
   CONSTRAINT pk_eventorolplazas PRIMARY KEY (id_evento, id_rol), 
   CONSTRAINT fk_id_evento FOREIGN KEY (id_evento) REFERENCES EVENTO (id_evento) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT fk_id_rol FOREIGN KEY (id_rol) REFERENCES TIPOROL (id_rol) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT chk_plazas CHECK (plazas > 0)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE EVENTOROLPLAZAS OWNER TO tdp;

-- La inscripción debe relacionarse con el usuario, pero tenia el campo contacto como tipo texto, pero se
-- debería referir a usuario y no a contacto (que su pk es un id_contacto integre)
ALTER TABLE INSCRIPCION RENAME contacto  TO codigo;
ALTER TABLE INSCRIPCION ADD CONSTRAINT fk_usuario FOREIGN KEY (codigo) REFERENCES USUARIO (codigo) ON UPDATE NO ACTION ON DELETE NO ACTION;

-- Se elimina la tabla para unificar nombres tanto de tablas como de campos
-- Se ha substituido por EVENTOROLPLAZAS
DROP TABLE PLAZASTIPOROL;

CREATE SEQUENCE seq_evento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_evento OWNER TO tdp;
ALTER TABLE EVENTO ALTER COLUMN id_evento SET DEFAULT nextval('seq_evento'::regclass);

CREATE OR REPLACE VIEW v_consulta_eventos_calendario AS 
 SELECT evento.id_evento, evento.id_centro, universidad.id_universidad, evento.nombre AS evento, evento.fecha_inicio_celebracion, 
	evento.fecha_fin_celebracion, evento.umbral, centrodocente.nombre AS centrodocente, universidad.nombre AS universidad, evento.estado,
	case when evento.estado = 3 then true
	else false
	end as eventoCancelado, 
	case when evento.estado = 3 then false
	else 
		case when evento.fecha_fin_celebracion < current_date then true
		else false
		end
	end as eventoFinalizado
   FROM evento, centrodocente, universidad
  WHERE evento.id_centro = centrodocente.id_centro AND centrodocente.id_universidad = universidad.id_universidad;

ALTER TABLE v_consulta_eventos_calendario OWNER TO tdp;
COMMENT ON VIEW v_consulta_eventos_calendario IS 'Gestiona la información necesaria para poder filtrar dentro de la gestión del calendario de eventos';

