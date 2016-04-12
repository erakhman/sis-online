CREATE TABLE customer_voucher
(
  pk_customer_voucher serial NOT NULL,
  fk_customer integer,
  nama_voucher character varying,
  created_date timestamp without time zone,
  created_by character varying,
  amount double precision,
  sisa_voucher double precision,
  CONSTRAINT customer_voucher_pkey PRIMARY KEY (pk_customer_voucher)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE customer_voucher
  OWNER TO postgres;
  
ALTER TABLE penjualan_bayar ADD COLUMN voucher double precision;