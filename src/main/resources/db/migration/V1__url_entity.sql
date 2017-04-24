CREATE TABLE short_url
(
  id        SERIAL                                              NOT NULL,
  url   CHARACTER VARYING(1024) COLLATE pg_catalog."default" NOT NULL,
  hash_key   CHARACTER VARYING(1024) COLLATE pg_catalog."default" NOT NULL,
  string_id   CHARACTER VARYING(1024) COLLATE pg_catalog."default" ,
  CONSTRAINT batch_pkey PRIMARY KEY (id),
  CONSTRAINT uk_rf7lm9yiteal0u3bm8bnk8i23 UNIQUE (hash_key)
);
