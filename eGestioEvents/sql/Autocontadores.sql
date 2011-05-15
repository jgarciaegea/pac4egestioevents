-- Añadir al campo id_centro una secuencia automática de la tabla CENTRODOCENTE ----

CREATE SEQUENCE seq_centrodocente
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_centrodocente OWNER TO tdp;

ALTER TABLE pec4."CENTRODOCENTE" ALTER COLUMN id_centro SET
DEFAULT nextval('seq_centrodocente'::regclass);


-- Añadir al campo id_contacto una secuencia automática de la tabla CONTACTO ----

CREATE SEQUENCE seq_contacto
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_contacto OWNER TO tdp;

ALTER TABLE pec4."CONTACTO" ALTER COLUMN id_contacto SET
DEFAULT nextval('seq_contacto'::regclass);


-- Añadir al campo id_datos_bancarios una secuencia automática de la tabla DATOSBANCARIOS ----

CREATE SEQUENCE seq_datosbancarios
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_datosbancarios OWNER TO tdp;

ALTER TABLE pec4."DATOSBANCARIOS" ALTER COLUMN id_datos_bancarios SET
DEFAULT nextval('seq_datosbancarios'::regclass);


-- Añadir al campo id_datos_bancarios una secuencia automática de la tabla TELEFONO ----

CREATE SEQUENCE seq_telefono
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_telefono OWNER TO tdp;

ALTER TABLE pec4."TELEFONO" ALTER COLUMN id_telefono SET
DEFAULT nextval('seq_telefono'::regclass);

-- Añadir al campo id_datos_bancarios una secuencia automática de la tabla UNIVERSIDAD ----

CREATE SEQUENCE seq_universidad
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_universidad OWNER TO tdp;

ALTER TABLE pec4."UNIVERSIDAD" ALTER COLUMN id_universidad SET
DEFAULT nextval('seq_universidad'::regclass);


