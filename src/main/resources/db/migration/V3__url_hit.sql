CREATE TABLE url_hit
(
  id           SERIAL  NOT NULL,
  short_url_id INTEGER NOT NULL,
  language     CHARACTER VARYING(512) COLLATE pg_catalog."default",
  user_agent   CHARACTER VARYING(512) COLLATE pg_catalog."default",
  client_ip    CHARACTER VARYING(64) COLLATE pg_catalog."default",
  created_at   TIMESTAMP DEFAULT now(),
  CONSTRAINT hit_pkey PRIMARY KEY (id)
);
