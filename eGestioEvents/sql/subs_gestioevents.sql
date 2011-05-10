-- Se añade la posibilidad de prerequisitos de los evento
CREATE TABLE pec4."EVENTOREQUISITOS"
(
   id_evento integer NOT NULL, 
   id_evento_req integer NOT NULL, 
   CONSTRAINT pk_eventorequisitos PRIMARY KEY (id_evento, id_evento_req), 
   CONSTRAINT fk_id_evento FOREIGN KEY (id_evento) REFERENCES pec4."EVENTO" (id_evento) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT fk_id_evento_req FOREIGN KEY (id_evento_req) REFERENCES pec4."EVENTO" (id_evento) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE pec4."EVENTOREQUISITOS" OWNER TO tdp;

-- Se añade la funcionalidad de fecha inicio con fecha fin evento
ALTER TABLE pec4."EVENTO" RENAME fecha_celebracion  TO fecha_inicio_celebracion;

ALTER TABLE pec4."EVENTO"
   ADD COLUMN fecha_fin_celebracion date NOT NULL;

-- Se elimina créditos según solución del consultor
ALTER TABLE pec4."EVENTO" DROP COLUMN creditos;

-- Se añade la posibilidad de gestión de plazas por rol
CREATE TABLE pec4."EVENTOROLPLAZAS"
(
   id_evento integer NOT NULL, 
   id_rol integer NOT NULL, 
   plazas integer NOT NULL, 
   CONSTRAINT pk_eventorolplazas PRIMARY KEY (id_evento, id_rol), 
   CONSTRAINT fk_id_evento FOREIGN KEY (id_evento) REFERENCES pec4."EVENTO" (id_evento) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT fk_id_rol FOREIGN KEY (id_rol) REFERENCES pec4."TIPOROL" (id_rol) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT chk_plazas CHECK (plazas > 0)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE pec4."EVENTOROLPLAZAS" OWNER TO tdp;

-- La inscripción debe relacionarse con el usuario, pero tenia el campo contacto como tipo texto, pero se
-- debería referir a usuario y no a contacto (que su pk es un id_contacto integre)
ALTER TABLE pec4."INSCRIPCION" RENAME contacto  TO codigo;
ALTER TABLE pec4."INSCRIPCION" ADD CONSTRAINT fk_usuario FOREIGN KEY (codigo) REFERENCES pec4."USUARIO" (codigo) ON UPDATE NO ACTION ON DELETE NO ACTION;

-- Se elimina la tabla para unificar nombres tanto de tablas como de campos
-- Se ha substituido por EVENTOROLPLAZAS
DROP TABLE pec4."PLAZASTIPOROL";
