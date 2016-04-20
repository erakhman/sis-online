CREATE SEQUENCE ms_coa_type_n_ct_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE ms_coa_type_n_ct_id_seq
  OWNER TO postgres;

  
CREATE TABLE ms_coa_type
(
  n_ct_id integer NOT NULL DEFAULT nextval('ms_coa_type_n_ct_id_seq'::regclass),
  v_ct_name character varying(100) NOT NULL,
  n_ct_natural_balance smallint NOT NULL, 
  CONSTRAINT ms_coa_type_pkey PRIMARY KEY (n_ct_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ms_coa_type
  OWNER TO postgres;
COMMENT ON COLUMN ms_coa_type.n_ct_natural_balance IS '1. Debit
2. Credit';



CREATE TABLE ms_coa
(
  n_coa_id serial NOT NULL,
  v_acct_no character varying(50) NOT NULL,
  v_desc character varying(100),
  n_type smallint, -- 1. Income...
  n_balance double precision,
  n_natural_balance smallint, -- 1. Debit...
  n_sup_coa_id bigint,
  n_status smallint, -- 1. Active...
  v_acct_name character varying(100) NOT NULL,
  v_who_create character varying(50),
  d_whn_create timestamp without time zone,
  v_who_change character varying(50),
  d_whn_change timestamp without time zone,
  CONSTRAINT ms_coa_pkey PRIMARY KEY (n_coa_id),
  CONSTRAINT ms_coa_n_sup_coa_id_fkey FOREIGN KEY (n_sup_coa_id)
      REFERENCES ms_coa (n_coa_id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT ms_coa_n_type_fkey FOREIGN KEY (n_type)
      REFERENCES ms_coa_type (n_ct_id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT ms_coa_v_acct_no_key UNIQUE (v_acct_no)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ms_coa
  OWNER TO postgres;
COMMENT ON TABLE ms_coa
  IS 'n_sup_coa is superior account id or this account is subaccount of...';
COMMENT ON COLUMN ms_coa.n_type IS '1. Income
2. Other Income
3. Expense
4. Other Expense
5. COGS

6. Cash
7. Bank
8. Other Current Assets
9. Inventory Assets
10. Other Assets
11. Fixed Assets
12. Credit Card
13. Current Liability
14. Long Term Liability
15. Equity';
COMMENT ON COLUMN ms_coa.n_natural_balance IS '1. Debit
2. Credit';
COMMENT ON COLUMN ms_coa.n_status IS '1. Active
0. Inactive';

