
CREATE TABLE public.fabricante (
  id serial,
  descricao character varying(255) NOT NULL,
  nome character varying(255) NOT NULL,
  CONSTRAINT fabricante_pkey PRIMARY KEY (id)
);

CREATE TABLE public.modelo (
  id serial,
  categoria character varying(25) NOT NULL,
  descricao character varying(255) NOT NULL,
  fabricante_id bigint NOT NULL,
  CONSTRAINT modelo_pkey PRIMARY KEY (id),
  CONSTRAINT modelo_fabricante_fk FOREIGN KEY (fabricante_id)
      REFERENCES public.fabricante (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.carro (
  id serial,
  chassi character varying(255) NOT NULL,
  placa character varying(10) NOT NULL,
  valor_da_diaria numeric(19,2) NOT NULL,
  situacao character varying(25) NOT NULL,
  modelo_id bigint NOT NULL,
  CONSTRAINT carro_pkey PRIMARY KEY (id),
  CONSTRAINT carro_modelo_fk FOREIGN KEY (modelo_id)
      REFERENCES public.modelo (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE public.motorista (
  id serial,
  cnh character varying(30) NOT NULL,
  cpf character varying(20) NOT NULL,
  nome character varying(255) NOT NULL,
  sexo character varying(25) NOT NULL,
  CONSTRAINT motorista_pkey PRIMARY KEY (id)
);

CREATE TABLE public.locacao (
  id serial,
  data_de_devolucao date,
  data_de_locacao date,
  valor_da_diaria_contratada numeric(19,2),
  valor_total numeric(19,2),
  carro_id bigint NOT NULL,
  motorista_id bigint NOT NULL,
  CONSTRAINT locacao_pkey PRIMARY KEY (id),
  CONSTRAINT locacao_motorista_fk FOREIGN KEY (motorista_id)
      REFERENCES public.motorista (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT locacao_carro_fk FOREIGN KEY (carro_id)
      REFERENCES public.carro (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);