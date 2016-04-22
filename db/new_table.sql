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


CREATE TABLE ms_book_category
(
  id serial NOT NULL,
  category_code character(5),
  category_description character varying(255),
  CONSTRAINT ms_book_category_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ms_book_category
  OWNER TO postgres;


CREATE TABLE ms_book_shelf
(
  id serial NOT NULL,
  shelf_code character varying(30),
  shelf_name character varying(100),
  CONSTRAINT ms_book_shelf_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ms_book_shelf
  OWNER TO postgres;


CREATE TABLE ms_book_location
(
  id serial NOT NULL,
  shelf_id integer,
  code character varying(20),
  location_description character varying(255),
  CONSTRAINT ms_book_location_pkey PRIMARY KEY (id),
  CONSTRAINT fk_ms_book_location_to_ms_book_shelf FOREIGN KEY (shelf_id)
      REFERENCES ms_book_shelf (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ms_book_location
  OWNER TO postgres;

-- Index: fki_ms_book_location_to_ms_book_shelf

-- DROP INDEX fki_ms_book_location_to_ms_book_shelf;

CREATE INDEX fki_ms_book_location_to_ms_book_shelf
  ON ms_book_location
  USING btree
  (shelf_id);


CREATE TABLE ms_book_publisher
(
  id serial NOT NULL,
  code character varying(5),
  publisher_name character varying(100),
  address character varying(255),
  phone_no character varying(40),
  CONSTRAINT ms_book_publisher_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ms_book_publisher
  OWNER TO postgres;


CREATE TABLE ms_book
(
  id serial NOT NULL,
  isbn character varying(20),
  code character varying(20),
  title character varying(20),
  category integer,
  author character varying(100),
  publisher integer,
  location integer,
  status integer, -- 1 = available,  0 = borrowed
  received_date date, -- date when book is received for the first time
  created_date timestamp without time zone,
  changed_date timestamp without time zone,
  created_by character varying(40),
  changed_by character varying(40),
  CONSTRAINT ms_book_pkey PRIMARY KEY (id),
  CONSTRAINT fk_ms_book_to_ms_book_location FOREIGN KEY (location)
      REFERENCES ms_book_location (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ms_book_to_ms_category FOREIGN KEY (category)
      REFERENCES ms_book_category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_ms_book_to_ms_publisher FOREIGN KEY (publisher)
      REFERENCES ms_book_publisher (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ms_book
  OWNER TO postgres;
COMMENT ON COLUMN ms_book.status IS '1 = available,  0 = borrowed';
COMMENT ON COLUMN ms_book.received_date IS 'date when book is received for the first time';


-- Index: fki_ms_book_to_ms_book_location

-- DROP INDEX fki_ms_book_to_ms_book_location;

CREATE INDEX fki_ms_book_to_ms_book_location
  ON ms_book
  USING btree
  (location);

-- Index: fki_ms_book_to_ms_category

-- DROP INDEX fki_ms_book_to_ms_category;

CREATE INDEX fki_ms_book_to_ms_category
  ON ms_book
  USING btree
  (category);

-- Index: fki_ms_book_to_ms_publisher

-- DROP INDEX fki_ms_book_to_ms_publisher;

CREATE INDEX fki_ms_book_to_ms_publisher
  ON ms_book
  USING btree
  (publisher);
  
 
  
CREATE TABLE ms_penalty_type
(
  id serial NOT NULL,
  tahun_ajaran_id integer,
  penalty_type integer, -- 1=suspend, 2 = pay base on late date
  nominal double precision, -- fill only when penalty type is 2 (pay base on late date), the value is daily basis
  CONSTRAINT ms_penalty_type_pkey PRIMARY KEY (id),
  CONSTRAINT fk_ms_penalty_type_to_tahun_ajaran FOREIGN KEY (tahun_ajaran_id)
      REFERENCES tahun_ajaran (pk_tahun_ajaran) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ms_penalty_type
  OWNER TO postgres;
COMMENT ON COLUMN ms_penalty_type.penalty_type IS '1=suspend, 2 = pay base on late date';
COMMENT ON COLUMN ms_penalty_type.nominal IS 'fill only when penalty type is 2 (pay base on late date), the value is daily basis';


-- Index: fki_ms_penalty_type_to_tahun_ajaran

-- DROP INDEX fki_ms_penalty_type_to_tahun_ajaran;

CREATE INDEX fki_ms_penalty_type_to_tahun_ajaran
  ON ms_penalty_type
  USING btree
  (tahun_ajaran_id);


CREATE TABLE ms_library_annual_fee
(
  id serial NOT NULL,
  tahun_ajaran_id integer,
  annual_fee double precision,
  CONSTRAINT ms_library_annual_fee_pkey PRIMARY KEY (id),
  CONSTRAINT fk_ms_library_annual_fee_to_tahun_ajaran FOREIGN KEY (tahun_ajaran_id)
      REFERENCES tahun_ajaran (pk_tahun_ajaran) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ms_library_annual_fee
  OWNER TO postgres;

-- Index: fki_ms_library_annual_fee_to_tahun_ajaran

-- DROP INDEX fki_ms_library_annual_fee_to_tahun_ajaran;

CREATE INDEX fki_ms_library_annual_fee_to_tahun_ajaran
  ON ms_library_annual_fee
  USING btree
  (tahun_ajaran_id);



CREATE TABLE ms_library_member
(
  id serial NOT NULL,
  member_code character varying(20),
  member_name character varying(255),
  member_type integer, -- 1=student, 2=teacher, 3=adminstratif employee (TU)
  member_identity_no character varying(20), -- NIS if student, NIP if is teacher or employee
  member_status integer, -- 1 = active, 2 = suspend, 3 = non active
  start_date date,
  end_date date,
  annual_fee_status integer, -- 1 = paid, 0 = not paid
  created_date timestamp without time zone,
  changed_date timestamp without time zone,
  created_by character varying(40),
  changed_by character varying(40),
  suspend_date date,
  end_suspend_date date,
  CONSTRAINT ms_library_member_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ms_library_member
  OWNER TO postgres;
COMMENT ON COLUMN ms_library_member.member_type IS '1=student, 2=teacher, 3=adminstratif employee (TU)';
COMMENT ON COLUMN ms_library_member.member_identity_no IS 'NIS if student, NIP if is teacher or employee';
COMMENT ON COLUMN ms_library_member.member_status IS '1 = active, 2 = suspend, 3 = non active';
COMMENT ON COLUMN ms_library_member.annual_fee_status IS '1 = paid, 0 = not paid';



CREATE TABLE member_annual_fee
(
  id serial NOT NULL,
  member_id integer,
  annual_fee_id integer,
  nominal_fee double precision,
  CONSTRAINT member_annual_fee_pkey PRIMARY KEY (id),
  CONSTRAINT fk_member_annual_fee_to_ms_library_annual_fee FOREIGN KEY (annual_fee_id)
      REFERENCES ms_library_annual_fee (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_member_annual_fee_to_ms_library_member FOREIGN KEY (member_id)
      REFERENCES ms_library_member (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE member_annual_fee
  OWNER TO postgres;

-- Index: fki_member_annual_fee_to_ms_library_annual_fee

-- DROP INDEX fki_member_annual_fee_to_ms_library_annual_fee;

CREATE INDEX fki_member_annual_fee_to_ms_library_annual_fee
  ON member_annual_fee
  USING btree
  (annual_fee_id);

-- Index: fki_member_annual_fee_to_ms_library_member

-- DROP INDEX fki_member_annual_fee_to_ms_library_member;

CREATE INDEX fki_member_annual_fee_to_ms_library_member
  ON member_annual_fee
  USING btree
  (member_id);



CREATE TABLE book_history
(
  id serial NOT NULL,
  book_id integer,
  borrowed_by integer,
  start_date date,
  end_date date,
  return_date date,
  created_date timestamp without time zone,
  changed_date timestamp without time zone,
  created_by character varying(40),
  changed_by character varying(40),
  CONSTRAINT book_history_pkey PRIMARY KEY (id),
  CONSTRAINT fk_book_history_to_ms_book FOREIGN KEY (book_id)
      REFERENCES ms_book (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_book_history_to_ms_library_member FOREIGN KEY (borrowed_by)
      REFERENCES ms_library_member (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE book_history
  OWNER TO postgres;

-- Index: fki_book_history_to_ms_book

-- DROP INDEX fki_book_history_to_ms_book;

CREATE INDEX fki_book_history_to_ms_book
  ON book_history
  USING btree
  (book_id);

-- Index: fki_book_history_to_ms_library_member

-- DROP INDEX fki_book_history_to_ms_library_member;

CREATE INDEX fki_book_history_to_ms_library_member
  ON book_history
  USING btree
  (borrowed_by);


  
CREATE TABLE book_penalty
(
  id serial NOT NULL,
  history_id integer,
  penalty_type integer,
  total_late_day integer,
  nominal double precision,
  CONSTRAINT book_penalty_pkey PRIMARY KEY (id),
  CONSTRAINT fk_book_penalty_to_book_history FOREIGN KEY (history_id)
      REFERENCES book_history (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE book_penalty
  OWNER TO postgres;

-- Index: fki_book_penalty_to_book_history

-- DROP INDEX fki_book_penalty_to_book_history;

CREATE INDEX fki_book_penalty_to_book_history
  ON book_penalty
  USING btree
  (history_id);
  
  
  
CREATE TABLE ms_staff
(
  id serial NOT NULL,
  staff_code character varying(20),
  staff_name character varying(255),
  staff_dob date,
  staff_sex character(1),
  staff_marital_status character(1),
  staff_address character varying(255),
  staff_phone_no character varying(40),
  staff_entrance_date date,
  staff_type integer, -- 1=guru, 2=TU
  staff_status integer, -- 1=PNS/Karyawan Tetap, 2=Honorer
  CONSTRAINT ms_staff_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ms_staff
  OWNER TO postgres;
COMMENT ON COLUMN ms_staff.staff_type IS '1=guru, 2=TU';
COMMENT ON COLUMN ms_staff.staff_status IS '1=PNS/Karyawan Tetap, 2=Honorer';



CREATE TABLE class_history
(
  id serial NOT NULL,
  home_room_teacher_id integer,
  class_id integer,
  tahun_ajaran_id integer,
  CONSTRAINT class_history_pkey PRIMARY KEY (id),
  CONSTRAINT fk_class_history_to_kelas FOREIGN KEY (class_id)
      REFERENCES kelas (pk_kelas) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_class_history_to_ms_staff FOREIGN KEY (home_room_teacher_id)
      REFERENCES ms_staff (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_class_history_to_tahun_ajaran FOREIGN KEY (tahun_ajaran_id)
      REFERENCES tahun_ajaran (pk_tahun_ajaran) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE class_history
  OWNER TO postgres;

-- Index: fki_class_history_to_kelas

-- DROP INDEX fki_class_history_to_kelas;

CREATE INDEX fki_class_history_to_kelas
  ON class_history
  USING btree
  (class_id);

-- Index: fki_class_history_to_ms_staff

-- DROP INDEX fki_class_history_to_ms_staff;

CREATE INDEX fki_class_history_to_ms_staff
  ON class_history
  USING btree
  (home_room_teacher_id);

-- Index: fki_class_history_to_tahun_ajaran

-- DROP INDEX fki_class_history_to_tahun_ajaran;

CREATE INDEX fki_class_history_to_tahun_ajaran
  ON class_history
  USING btree
  (tahun_ajaran_id);


  
CREATE TABLE student_class_history
(
  id serial NOT NULL,
  class_history_id integer,
  student_id integer,
  CONSTRAINT student_class_history_pkey PRIMARY KEY (id),
  CONSTRAINT fk_sch_to_class_history FOREIGN KEY (class_history_id)
      REFERENCES class_history (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_sch_to_student FOREIGN KEY (student_id)
      REFERENCES siswa (pk_siswa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE student_class_history
  OWNER TO postgres;

-- Index: fki_sch_to_class_history

-- DROP INDEX fki_sch_to_class_history;

CREATE INDEX fki_sch_to_class_history
  ON student_class_history
  USING btree
  (class_history_id);

-- Index: fki_sch_to_student

-- DROP INDEX fki_sch_to_student;

CREATE INDEX fki_sch_to_student
  ON student_class_history
  USING btree
  (student_id);



