-- Table: public.ms_school_profile

-- DROP TABLE public.ms_school_profile;

CREATE TABLE public.ms_school_profile
(
  pk_school_profile integer NOT NULL DEFAULT nextval('ms_school_profile_pk_school_profile_seq'::regclass),
  school_name character varying,
  nis character varying,
  nss character varying,
  npsn character varying,
  postal_code integer,
  phone_no character varying,
  fax_no character varying,
  fk_city integer,
  fk_district integer,
  fk_province integer,
  website character varying,
  email character varying,
  CONSTRAINT ms_school_profile_pkey PRIMARY KEY (pk_school_profile)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.ms_school_profile
  OWNER TO postgres;

-- Table: public.ms_city

-- DROP TABLE public.ms_city;

CREATE TABLE public.ms_city
(
  pk_city integer NOT NULL DEFAULT nextval('ms_city_pk_city_seq'::regclass),
  city_code character varying,
  city_name character varying,
  CONSTRAINT ms_city_pkey PRIMARY KEY (pk_city)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.ms_city
  OWNER TO postgres;

  
-- Table: public.ms_district

-- DROP TABLE public.ms_district;

CREATE TABLE public.ms_district
(
  pk_district integer NOT NULL DEFAULT nextval('ms_district_pk_district_seq'::regclass),
  district_code character varying,
  district_name character varying,
  CONSTRAINT ms_district_pkey PRIMARY KEY (pk_district)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.ms_district
  OWNER TO postgres;

  
-- Table: public.ms_province

-- DROP TABLE public.ms_province;

CREATE TABLE public.ms_province
(
  pk_province integer NOT NULL DEFAULT nextval('ms_province_pk_province_seq'::regclass),
  province_code character varying,
  province_name character varying,
  CONSTRAINT ms_province_pkey PRIMARY KEY (pk_province)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.ms_province
  OWNER TO postgres;
