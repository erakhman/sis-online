ALTER TABLE penjualan_bayar ADD COLUMN cash double precision;

-- Table: voucher

-- DROP TABLE voucher;

CREATE TABLE voucher
(
  pk_voucher serial NOT NULL,
  fk_customer integer,
  nama_voucher character varying,
  amount double precision,
  created_date timestamp without time zone,
  created_by character varying,
  sisa_voucher double precision,
  tanggal timestamp without time zone,
  changed_date timestamp without time zone,
  changed_by character varying,
  CONSTRAINT voucher_pkey PRIMARY KEY (pk_voucher)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE voucher
  OWNER TO postgres;
