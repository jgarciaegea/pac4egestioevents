-- Table: pec4."TIPOEVENTOROL"

-- DROP TABLE pec4."TIPOEVENTOROL";

CREATE TABLE pec4."TIPOEVENTOROL"
(
  id_tipo_evento integer NOT NULL,
  id_rol integer NOT NULL,
  CONSTRAINT pk_tipoeventorol PRIMARY KEY (id_tipo_evento, id_rol),
  CONSTRAINT fk_tipoeventorol_rol FOREIGN KEY (id_rol)
      REFERENCES pec4."TIPOROL" (id_rol) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_tipoeventorol_tipoevento FOREIGN KEY (id_tipo_evento)
      REFERENCES pec4."TIPOEVENTO" (id_tipo_evento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pec4."TIPOEVENTOROL" OWNER TO tdp;
