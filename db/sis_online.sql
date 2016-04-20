--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

-- Started on 2016-04-20 13:58:14

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2298 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 200 (class 1259 OID 17506)
-- Name: app_menu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE app_menu (
    pk_menu integer NOT NULL,
    menu_code character varying,
    sub_menu character varying,
    created_date timestamp without time zone,
    created_by character varying,
    changed_date timestamp without time zone,
    changed_by character varying,
    menu_order character varying,
    page_name character varying
);


ALTER TABLE app_menu OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 17504)
-- Name: app_menu_pk_menu_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE app_menu_pk_menu_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE app_menu_pk_menu_seq OWNER TO postgres;

--
-- TOC entry 2299 (class 0 OID 0)
-- Dependencies: 199
-- Name: app_menu_pk_menu_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE app_menu_pk_menu_seq OWNED BY app_menu.pk_menu;


--
-- TOC entry 198 (class 1259 OID 17218)
-- Name: app_parameter; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE app_parameter (
    pk_app_parameter integer NOT NULL,
    description character varying,
    name character varying,
    data_type character varying,
    is_viewable character varying,
    value character varying,
    changed_date timestamp without time zone,
    changed_by character varying
);


ALTER TABLE app_parameter OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 17216)
-- Name: app_parameter_pk_app_parameter_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE app_parameter_pk_app_parameter_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE app_parameter_pk_app_parameter_seq OWNER TO postgres;

--
-- TOC entry 2300 (class 0 OID 0)
-- Dependencies: 197
-- Name: app_parameter_pk_app_parameter_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE app_parameter_pk_app_parameter_seq OWNED BY app_parameter.pk_app_parameter;


--
-- TOC entry 202 (class 1259 OID 17517)
-- Name: app_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE app_role (
    pk_role integer NOT NULL,
    role_code character varying,
    role_name character varying,
    created_date timestamp without time zone,
    created_by character varying,
    changed_date timestamp without time zone,
    changed_by character varying,
    is_active character(1)
);


ALTER TABLE app_role OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 17528)
-- Name: app_role_menu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE app_role_menu (
    pk_role_menu integer NOT NULL,
    fk_role integer,
    fk_menu integer,
    created_date timestamp without time zone,
    created_by character varying,
    changed_date timestamp without time zone,
    changed_by character varying
);


ALTER TABLE app_role_menu OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 17526)
-- Name: app_role_menu_pk_role_menu_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE app_role_menu_pk_role_menu_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE app_role_menu_pk_role_menu_seq OWNER TO postgres;

--
-- TOC entry 2301 (class 0 OID 0)
-- Dependencies: 203
-- Name: app_role_menu_pk_role_menu_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE app_role_menu_pk_role_menu_seq OWNED BY app_role_menu.pk_role_menu;


--
-- TOC entry 201 (class 1259 OID 17515)
-- Name: app_role_pk_role_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE app_role_pk_role_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE app_role_pk_role_seq OWNER TO postgres;

--
-- TOC entry 2302 (class 0 OID 0)
-- Dependencies: 201
-- Name: app_role_pk_role_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE app_role_pk_role_seq OWNED BY app_role.pk_role;


--
-- TOC entry 206 (class 1259 OID 17539)
-- Name: app_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE app_user (
    pk_user integer NOT NULL,
    password character varying,
    full_name character varying,
    wrong_password integer,
    is_locked character varying DEFAULT 'N'::character varying,
    change_password character varying,
    user_name character varying,
    last_login_date timestamp without time zone,
    created_date timestamp without time zone,
    created_by character varying,
    changed_date timestamp without time zone,
    changed_by character varying
);


ALTER TABLE app_user OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 17537)
-- Name: app_user_pk_user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE app_user_pk_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE app_user_pk_user_seq OWNER TO postgres;

--
-- TOC entry 2303 (class 0 OID 0)
-- Dependencies: 205
-- Name: app_user_pk_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE app_user_pk_user_seq OWNED BY app_user.pk_user;


--
-- TOC entry 208 (class 1259 OID 17550)
-- Name: app_user_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE app_user_role (
    pk_user_role integer NOT NULL,
    fk_user integer,
    fk_role integer
);


ALTER TABLE app_user_role OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 17548)
-- Name: app_user_role_pk_user_role_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE app_user_role_pk_user_role_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE app_user_role_pk_user_role_seq OWNER TO postgres;

--
-- TOC entry 2304 (class 0 OID 0)
-- Dependencies: 207
-- Name: app_user_role_pk_user_role_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE app_user_role_pk_user_role_seq OWNED BY app_user_role.pk_user_role;


--
-- TOC entry 210 (class 1259 OID 17630)
-- Name: hasil_ujian_masuk; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE hasil_ujian_masuk (
    pk_hasil_ujian_masuk integer NOT NULL,
    jumlah_soal integer,
    jawaban_benar integer,
    jawaban_salah integer,
    score double precision,
    fk_pendaftaran integer
);


ALTER TABLE hasil_ujian_masuk OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 17628)
-- Name: hasil_ujian_masuk_pk_hasil_ujian_masuk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hasil_ujian_masuk_pk_hasil_ujian_masuk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hasil_ujian_masuk_pk_hasil_ujian_masuk_seq OWNER TO postgres;

--
-- TOC entry 2305 (class 0 OID 0)
-- Dependencies: 209
-- Name: hasil_ujian_masuk_pk_hasil_ujian_masuk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE hasil_ujian_masuk_pk_hasil_ujian_masuk_seq OWNED BY hasil_ujian_masuk.pk_hasil_ujian_masuk;


--
-- TOC entry 212 (class 1259 OID 17639)
-- Name: jadwal_ujian; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE jadwal_ujian (
    pk_jadwal_ujian integer NOT NULL,
    fk_pelajaran integer,
    fk_kelas integer,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    type character varying,
    fk_tahun_ajaran integer
);


ALTER TABLE jadwal_ujian OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 17637)
-- Name: jadwaL_ujian_pk_ujian_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "jadwaL_ujian_pk_ujian_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "jadwaL_ujian_pk_ujian_seq" OWNER TO postgres;

--
-- TOC entry 2306 (class 0 OID 0)
-- Dependencies: 211
-- Name: jadwaL_ujian_pk_ujian_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "jadwaL_ujian_pk_ujian_seq" OWNED BY jadwal_ujian.pk_jadwal_ujian;


--
-- TOC entry 214 (class 1259 OID 17650)
-- Name: kelas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE kelas (
    pk_kelas integer NOT NULL,
    kode_kelas character varying,
    nama_kelas character varying,
    kuota integer
);


ALTER TABLE kelas OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 17648)
-- Name: kelas_pk_kelas_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE kelas_pk_kelas_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE kelas_pk_kelas_seq OWNER TO postgres;

--
-- TOC entry 2307 (class 0 OID 0)
-- Dependencies: 213
-- Name: kelas_pk_kelas_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE kelas_pk_kelas_seq OWNED BY kelas.pk_kelas;


--
-- TOC entry 186 (class 1259 OID 17157)
-- Name: pelajaran; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pelajaran (
    pk_pelajaran integer NOT NULL,
    kode_pelajaran character varying,
    nama_pelajaran character varying,
    created_date timestamp without time zone,
    created_by character varying,
    changed_date timestamp without time zone,
    changed_by character varying
);


ALTER TABLE pelajaran OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 17155)
-- Name: pelajaran_pk_pelajaran_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pelajaran_pk_pelajaran_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pelajaran_pk_pelajaran_seq OWNER TO postgres;

--
-- TOC entry 2308 (class 0 OID 0)
-- Dependencies: 185
-- Name: pelajaran_pk_pelajaran_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pelajaran_pk_pelajaran_seq OWNED BY pelajaran.pk_pelajaran;


--
-- TOC entry 182 (class 1259 OID 17135)
-- Name: pendaftaran; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pendaftaran (
    pk_pendaftaran integer NOT NULL,
    fk_siswa integer,
    created_date timestamp without time zone,
    created_by character varying,
    changed_date timestamp without time zone,
    changed_by character varying,
    nama_siswa character varying,
    tgl_lahir date,
    nem double precision,
    user_name character varying,
    lulus character(1),
    daftar_ulang character(1),
    kode_pendaftaran character varying,
    biaya_pendaftaran double precision
);


ALTER TABLE pendaftaran OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 17168)
-- Name: pendaftaran_detail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pendaftaran_detail (
    pk_pendaftaran_detail integer NOT NULL,
    fk_pendaftaran integer,
    fk_pelajaran integer,
    nilai integer
);


ALTER TABLE pendaftaran_detail OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 17166)
-- Name: pendaftaran_detail_pk_pendaftaran_detail_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pendaftaran_detail_pk_pendaftaran_detail_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pendaftaran_detail_pk_pendaftaran_detail_seq OWNER TO postgres;

--
-- TOC entry 2309 (class 0 OID 0)
-- Dependencies: 187
-- Name: pendaftaran_detail_pk_pendaftaran_detail_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pendaftaran_detail_pk_pendaftaran_detail_seq OWNED BY pendaftaran_detail.pk_pendaftaran_detail;


--
-- TOC entry 181 (class 1259 OID 17133)
-- Name: pendaftaran_pk_pendaftaran_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pendaftaran_pk_pendaftaran_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pendaftaran_pk_pendaftaran_seq OWNER TO postgres;

--
-- TOC entry 2310 (class 0 OID 0)
-- Dependencies: 181
-- Name: pendaftaran_pk_pendaftaran_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pendaftaran_pk_pendaftaran_seq OWNED BY pendaftaran.pk_pendaftaran;


--
-- TOC entry 184 (class 1259 OID 17146)
-- Name: siswa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE siswa (
    pk_siswa integer NOT NULL,
    nama_siswa character varying,
    tgl_lahir date,
    tempat_lahir character varying,
    alamat character varying,
    nama_ayah character varying,
    nama_ibu character varying,
    alamat_ayah character varying,
    alamat_ibu character varying,
    telp_ortu character varying,
    nem double precision,
    created_date timestamp without time zone,
    created_by character varying,
    changed_date timestamp without time zone,
    changed_by character varying,
    nis character varying
);


ALTER TABLE siswa OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 17144)
-- Name: siswa_pk_siswa_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE siswa_pk_siswa_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE siswa_pk_siswa_seq OWNER TO postgres;

--
-- TOC entry 2311 (class 0 OID 0)
-- Dependencies: 183
-- Name: siswa_pk_siswa_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE siswa_pk_siswa_seq OWNED BY siswa.pk_siswa;


--
-- TOC entry 196 (class 1259 OID 17206)
-- Name: soal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE soal (
    pk_soal integer NOT NULL,
    level integer,
    soal_pelajaran character varying,
    pilihan_a character varying,
    pilihan_b character varying,
    pilihan_c character varying,
    pilihan_d character varying,
    pilihan_e character varying,
    jawaban character varying,
    created_date timestamp without time zone,
    created_by character varying,
    changed_date timestamp without time zone,
    changed_by character varying,
    fk_pelajaran integer
);


ALTER TABLE soal OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 17204)
-- Name: soal_pk_soal_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE soal_pk_soal_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE soal_pk_soal_seq OWNER TO postgres;

--
-- TOC entry 2312 (class 0 OID 0)
-- Dependencies: 195
-- Name: soal_pk_soal_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE soal_pk_soal_seq OWNED BY soal.pk_soal;


--
-- TOC entry 190 (class 1259 OID 17176)
-- Name: tahun_ajaran; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tahun_ajaran (
    pk_tahun_ajaran integer NOT NULL,
    tahun_ajaran character varying,
    created_date timestamp without time zone,
    created_by character varying,
    changed_date timestamp without time zone,
    changed_by character varying,
    is_active character(1)
);


ALTER TABLE tahun_ajaran OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 17174)
-- Name: tahun_ajaran_pk_tahun_ajaran_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tahun_ajaran_pk_tahun_ajaran_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tahun_ajaran_pk_tahun_ajaran_seq OWNER TO postgres;

--
-- TOC entry 2313 (class 0 OID 0)
-- Dependencies: 189
-- Name: tahun_ajaran_pk_tahun_ajaran_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tahun_ajaran_pk_tahun_ajaran_seq OWNED BY tahun_ajaran.pk_tahun_ajaran;


--
-- TOC entry 192 (class 1259 OID 17187)
-- Name: ujian_masuk; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ujian_masuk (
    pk_ujian_masuk integer NOT NULL,
    fk_tahun_ajaran integer,
    fk_pelajaran integer,
    created_date timestamp without time zone,
    created_by character varying,
    changed_date timestamp without time zone,
    changed_by character varying,
    fk_pendaftaran integer,
    tahun_ajaran character varying
);


ALTER TABLE ujian_masuk OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 17195)
-- Name: ujian_masuk_detail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE ujian_masuk_detail (
    pk_ujian_masuk_detail integer NOT NULL,
    fk_ujian_masuk integer,
    fk_soal integer,
    jawaban_soal character varying,
    jawaban_siswa character varying,
    fk_pendaftaran integer
);


ALTER TABLE ujian_masuk_detail OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 17193)
-- Name: ujian_masuk_detail_pk_ujian_masuk_detail_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ujian_masuk_detail_pk_ujian_masuk_detail_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ujian_masuk_detail_pk_ujian_masuk_detail_seq OWNER TO postgres;

--
-- TOC entry 2314 (class 0 OID 0)
-- Dependencies: 193
-- Name: ujian_masuk_detail_pk_ujian_masuk_detail_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ujian_masuk_detail_pk_ujian_masuk_detail_seq OWNED BY ujian_masuk_detail.pk_ujian_masuk_detail;


--
-- TOC entry 191 (class 1259 OID 17185)
-- Name: ujian_masuk_pk_ujian_masuk_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE ujian_masuk_pk_ujian_masuk_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ujian_masuk_pk_ujian_masuk_seq OWNER TO postgres;

--
-- TOC entry 2315 (class 0 OID 0)
-- Dependencies: 191
-- Name: ujian_masuk_pk_ujian_masuk_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE ujian_masuk_pk_ujian_masuk_seq OWNED BY ujian_masuk.pk_ujian_masuk;


--
-- TOC entry 2100 (class 2604 OID 17509)
-- Name: pk_menu; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY app_menu ALTER COLUMN pk_menu SET DEFAULT nextval('app_menu_pk_menu_seq'::regclass);


--
-- TOC entry 2099 (class 2604 OID 17221)
-- Name: pk_app_parameter; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY app_parameter ALTER COLUMN pk_app_parameter SET DEFAULT nextval('app_parameter_pk_app_parameter_seq'::regclass);


--
-- TOC entry 2101 (class 2604 OID 17520)
-- Name: pk_role; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY app_role ALTER COLUMN pk_role SET DEFAULT nextval('app_role_pk_role_seq'::regclass);


--
-- TOC entry 2102 (class 2604 OID 17531)
-- Name: pk_role_menu; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY app_role_menu ALTER COLUMN pk_role_menu SET DEFAULT nextval('app_role_menu_pk_role_menu_seq'::regclass);


--
-- TOC entry 2103 (class 2604 OID 17542)
-- Name: pk_user; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY app_user ALTER COLUMN pk_user SET DEFAULT nextval('app_user_pk_user_seq'::regclass);


--
-- TOC entry 2105 (class 2604 OID 17553)
-- Name: pk_user_role; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY app_user_role ALTER COLUMN pk_user_role SET DEFAULT nextval('app_user_role_pk_user_role_seq'::regclass);


--
-- TOC entry 2106 (class 2604 OID 17633)
-- Name: pk_hasil_ujian_masuk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hasil_ujian_masuk ALTER COLUMN pk_hasil_ujian_masuk SET DEFAULT nextval('hasil_ujian_masuk_pk_hasil_ujian_masuk_seq'::regclass);


--
-- TOC entry 2107 (class 2604 OID 17642)
-- Name: pk_jadwal_ujian; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jadwal_ujian ALTER COLUMN pk_jadwal_ujian SET DEFAULT nextval('"jadwaL_ujian_pk_ujian_seq"'::regclass);


--
-- TOC entry 2108 (class 2604 OID 17653)
-- Name: pk_kelas; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY kelas ALTER COLUMN pk_kelas SET DEFAULT nextval('kelas_pk_kelas_seq'::regclass);


--
-- TOC entry 2093 (class 2604 OID 17160)
-- Name: pk_pelajaran; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pelajaran ALTER COLUMN pk_pelajaran SET DEFAULT nextval('pelajaran_pk_pelajaran_seq'::regclass);


--
-- TOC entry 2091 (class 2604 OID 17138)
-- Name: pk_pendaftaran; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pendaftaran ALTER COLUMN pk_pendaftaran SET DEFAULT nextval('pendaftaran_pk_pendaftaran_seq'::regclass);


--
-- TOC entry 2094 (class 2604 OID 17171)
-- Name: pk_pendaftaran_detail; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pendaftaran_detail ALTER COLUMN pk_pendaftaran_detail SET DEFAULT nextval('pendaftaran_detail_pk_pendaftaran_detail_seq'::regclass);


--
-- TOC entry 2092 (class 2604 OID 17149)
-- Name: pk_siswa; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY siswa ALTER COLUMN pk_siswa SET DEFAULT nextval('siswa_pk_siswa_seq'::regclass);


--
-- TOC entry 2098 (class 2604 OID 17209)
-- Name: pk_soal; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY soal ALTER COLUMN pk_soal SET DEFAULT nextval('soal_pk_soal_seq'::regclass);


--
-- TOC entry 2095 (class 2604 OID 17179)
-- Name: pk_tahun_ajaran; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tahun_ajaran ALTER COLUMN pk_tahun_ajaran SET DEFAULT nextval('tahun_ajaran_pk_tahun_ajaran_seq'::regclass);


--
-- TOC entry 2096 (class 2604 OID 17190)
-- Name: pk_ujian_masuk; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ujian_masuk ALTER COLUMN pk_ujian_masuk SET DEFAULT nextval('ujian_masuk_pk_ujian_masuk_seq'::regclass);


--
-- TOC entry 2097 (class 2604 OID 17198)
-- Name: pk_ujian_masuk_detail; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ujian_masuk_detail ALTER COLUMN pk_ujian_masuk_detail SET DEFAULT nextval('ujian_masuk_detail_pk_ujian_masuk_detail_seq'::regclass);


--
-- TOC entry 2276 (class 0 OID 17506)
-- Dependencies: 200
-- Data for Name: app_menu; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY app_menu (pk_menu, menu_code, sub_menu, created_date, created_by, changed_date, changed_by, menu_order, page_name) FROM stdin;
1	pendaftaran	nav.pendaftaran	\N	\N	\N	\N	1	PendaftaranActionBean
2	user	nav.user	\N	\N	\N	\N	2	UserActionBean
3	role	nav.role	\N	\N	\N	\N	3	RoleActionBean
4	ujianMasuk	nav.ujianMasuk	\N	\N	\N	\N	4	UjianMasukActionBean
5	hasilUjianMasuk	nav.hasilUjianMasuk	\N	\N	\N	\N	5	HasilUjianMasukActionBean
6	tahunAjaran	nav.tahunAjaran	\N	\N	\N	\N	6	TahunAjaranActionBean
\.


--
-- TOC entry 2316 (class 0 OID 0)
-- Dependencies: 199
-- Name: app_menu_pk_menu_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('app_menu_pk_menu_seq', 6, true);


--
-- TOC entry 2274 (class 0 OID 17218)
-- Dependencies: 198
-- Data for Name: app_parameter; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY app_parameter (pk_app_parameter, description, name, data_type, is_viewable, value, changed_date, changed_by) FROM stdin;
1	PAGE_LIMIT	PAGE_LIMIT	1	\N	20	\N	\N
2	DURASI_UJIAN	DURASI_UJIAN	1	\N	30	\N	\N
\.


--
-- TOC entry 2317 (class 0 OID 0)
-- Dependencies: 197
-- Name: app_parameter_pk_app_parameter_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('app_parameter_pk_app_parameter_seq', 3, true);


--
-- TOC entry 2278 (class 0 OID 17517)
-- Dependencies: 202
-- Data for Name: app_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY app_role (pk_role, role_code, role_name, created_date, created_by, changed_date, changed_by, is_active) FROM stdin;
2	CS	Calon Siswa	\N	\N	\N	\N	Y
1	ADM	Administrator	\N	\N	2016-04-19 20:41:40.683	\N	Y
\.


--
-- TOC entry 2280 (class 0 OID 17528)
-- Dependencies: 204
-- Data for Name: app_role_menu; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY app_role_menu (pk_role_menu, fk_role, fk_menu, created_date, created_by, changed_date, changed_by) FROM stdin;
5	2	4	\N	\N	\N	\N
13	1	1	\N	\N	\N	\N
14	1	2	\N	\N	\N	\N
15	1	3	\N	\N	\N	\N
16	1	5	\N	\N	\N	\N
17	1	6	\N	\N	\N	\N
\.


--
-- TOC entry 2318 (class 0 OID 0)
-- Dependencies: 203
-- Name: app_role_menu_pk_role_menu_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('app_role_menu_pk_role_menu_seq', 17, true);


--
-- TOC entry 2319 (class 0 OID 0)
-- Dependencies: 201
-- Name: app_role_pk_role_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('app_role_pk_role_seq', 2, true);


--
-- TOC entry 2282 (class 0 OID 17539)
-- Dependencies: 206
-- Data for Name: app_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY app_user (pk_user, password, full_name, wrong_password, is_locked, change_password, user_name, last_login_date, created_date, created_by, changed_date, changed_by) FROM stdin;
12	s+graVfig8oeIH8h8hMJUKTejpUnHtn/	edy rakhman	0	Y	N	erakhman	2016-04-19 19:14:03.576	2016-04-18 19:46:46.469	System	2016-04-19 19:14:03.576	\N
13	gPju1KwWh+jcJS2ZHKS4Wrhv79CIFqoj	arifullah	0	Y	N	arifullah	2016-04-19 19:16:00.526	2016-04-18 19:47:38.78	System	2016-04-19 19:16:00.526	\N
14	sz03vDzbC/lQToFK6JiOQ79tdGl2rdiq	rany	0	Y	N	rany	2016-04-19 19:16:41.356	2016-04-18 19:47:49.924	System	2016-04-19 19:16:41.356	\N
1	M+VBdCqQUBrNwIuGYy7EFa/HiuX12TNk	Administrator	0	N	N	admin	2016-04-19 20:45:34.163	2016-03-30 09:35:04.135163	System	2016-04-19 20:45:34.163	\N
\.


--
-- TOC entry 2320 (class 0 OID 0)
-- Dependencies: 205
-- Name: app_user_pk_user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('app_user_pk_user_seq', 14, true);


--
-- TOC entry 2284 (class 0 OID 17550)
-- Dependencies: 208
-- Data for Name: app_user_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY app_user_role (pk_user_role, fk_user, fk_role) FROM stdin;
1	1	1
5	3	2
7	5	2
8	6	2
12	10	2
13	11	2
14	12	2
15	13	2
16	14	2
\.


--
-- TOC entry 2321 (class 0 OID 0)
-- Dependencies: 207
-- Name: app_user_role_pk_user_role_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('app_user_role_pk_user_role_seq', 16, true);


--
-- TOC entry 2286 (class 0 OID 17630)
-- Dependencies: 210
-- Data for Name: hasil_ujian_masuk; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hasil_ujian_masuk (pk_hasil_ujian_masuk, jumlah_soal, jawaban_benar, jawaban_salah, score, fk_pendaftaran) FROM stdin;
12	2	1	1	50	24
13	2	0	2	0	25
14	2	2	0	100	26
\.


--
-- TOC entry 2322 (class 0 OID 0)
-- Dependencies: 209
-- Name: hasil_ujian_masuk_pk_hasil_ujian_masuk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hasil_ujian_masuk_pk_hasil_ujian_masuk_seq', 14, true);


--
-- TOC entry 2323 (class 0 OID 0)
-- Dependencies: 211
-- Name: jadwaL_ujian_pk_ujian_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('"jadwaL_ujian_pk_ujian_seq"', 1, false);


--
-- TOC entry 2288 (class 0 OID 17639)
-- Dependencies: 212
-- Data for Name: jadwal_ujian; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY jadwal_ujian (pk_jadwal_ujian, fk_pelajaran, fk_kelas, start_date, end_date, type, fk_tahun_ajaran) FROM stdin;
\.


--
-- TOC entry 2290 (class 0 OID 17650)
-- Dependencies: 214
-- Data for Name: kelas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY kelas (pk_kelas, kode_kelas, nama_kelas, kuota) FROM stdin;
\.


--
-- TOC entry 2324 (class 0 OID 0)
-- Dependencies: 213
-- Name: kelas_pk_kelas_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('kelas_pk_kelas_seq', 1, false);


--
-- TOC entry 2262 (class 0 OID 17157)
-- Dependencies: 186
-- Data for Name: pelajaran; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pelajaran (pk_pelajaran, kode_pelajaran, nama_pelajaran, created_date, created_by, changed_date, changed_by) FROM stdin;
1	MTK	Matematika	\N	\N	\N	\N
2	IND	B.Indonesia	\N	\N	\N	\N
3	ENG	B.Inggris	\N	\N	\N	\N
4	IPS	IPS	\N	\N	\N	\N
\.


--
-- TOC entry 2325 (class 0 OID 0)
-- Dependencies: 185
-- Name: pelajaran_pk_pelajaran_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pelajaran_pk_pelajaran_seq', 4, true);


--
-- TOC entry 2258 (class 0 OID 17135)
-- Dependencies: 182
-- Data for Name: pendaftaran; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pendaftaran (pk_pendaftaran, fk_siswa, created_date, created_by, changed_date, changed_by, nama_siswa, tgl_lahir, nem, user_name, lulus, daftar_ulang, kode_pendaftaran, biaya_pendaftaran) FROM stdin;
24	\N	2016-04-18 19:46:46.438	\N	\N	\N	edy rakhman	2016-04-18	100	erakhman	\N	\N	\N	\N
25	\N	2016-04-18 19:47:38.765	\N	\N	\N	arifullah	2016-04-18	90	arifullah	\N	\N	\N	\N
26	\N	2016-04-18 19:47:49.908	\N	\N	\N	rany	2016-04-18	80	rany	\N	\N	\N	\N
\.


--
-- TOC entry 2264 (class 0 OID 17168)
-- Dependencies: 188
-- Data for Name: pendaftaran_detail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pendaftaran_detail (pk_pendaftaran_detail, fk_pendaftaran, fk_pelajaran, nilai) FROM stdin;
\.


--
-- TOC entry 2326 (class 0 OID 0)
-- Dependencies: 187
-- Name: pendaftaran_detail_pk_pendaftaran_detail_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pendaftaran_detail_pk_pendaftaran_detail_seq', 29, true);


--
-- TOC entry 2327 (class 0 OID 0)
-- Dependencies: 181
-- Name: pendaftaran_pk_pendaftaran_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pendaftaran_pk_pendaftaran_seq', 26, true);


--
-- TOC entry 2260 (class 0 OID 17146)
-- Dependencies: 184
-- Data for Name: siswa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY siswa (pk_siswa, nama_siswa, tgl_lahir, tempat_lahir, alamat, nama_ayah, nama_ibu, alamat_ayah, alamat_ibu, telp_ortu, nem, created_date, created_by, changed_date, changed_by, nis) FROM stdin;
9	ade kemput	\N	\N	\N	\N	\N	\N	\N	\N	\N	2016-03-29 00:26:55.465	\N	2016-03-29 00:41:55.551	\N	\N
10	edy	\N	\N	\N	\N	\N	\N	\N	\N	\N	2016-03-30 09:14:24.215	\N	\N	\N	\N
11	edy	\N	\N	\N	\N	\N	\N	\N	\N	\N	2016-03-30 09:19:18.067	\N	2016-03-30 09:19:38.5	\N	\N
12	engkong	\N	\N	\N	\N	\N	\N	\N	\N	\N	2016-03-30 16:19:06.461	\N	2016-03-30 16:19:14.103	\N	\N
13	fatih	\N	\N	\N	\N	\N	\N	\N	\N	\N	2016-03-31 18:55:34.045	\N	\N	\N	\N
\.


--
-- TOC entry 2328 (class 0 OID 0)
-- Dependencies: 183
-- Name: siswa_pk_siswa_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('siswa_pk_siswa_seq', 13, true);


--
-- TOC entry 2272 (class 0 OID 17206)
-- Dependencies: 196
-- Data for Name: soal; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY soal (pk_soal, level, soal_pelajaran, pilihan_a, pilihan_b, pilihan_c, pilihan_d, pilihan_e, jawaban, created_date, created_by, changed_date, changed_by, fk_pelajaran) FROM stdin;
1	1	Apa nama ibukota indonesia	Jakarta	Bandung	Depok	Serang	Bogor	Jakarta	\N	\N	\N	\N	4
2	2	Apa nama mata uang indonesia	Ringgit	Rupiah	Yen	Dollar	Pondsterling	Rupiah	\N	\N	\N	\N	4
3	3	Siapa presiden indonesia saat ini	Gusdur	Habibie	Jokowi	Soekarno	Megawati	Jokowi	\N	\N	\N	\N	4
4	1	1 + 1	2	3	4	5	6	2	\N	\N	\N	\N	1
5	1	2 x 3	5	6	7	8	9	6	\N	\N	\N	\N	1
6	\N	Dengan lapang dada beliau menerima cobaan itu.\r\nArtinya ungkapan lapang dada adalah	\tikhlas	terpaksa	tergesa-gesa	biasa	\N	ikhlas	\N	\N	\N	\N	2
7	\N	What the meaning of kemput	emang gue pikirin	ga tahu	ga jelas	bodo amat	\N	ga tahu	\N	\N	\N	\N	3
8	\N	\tPaman membeli rumah mewah secara tunai.\r\nSinonim kata tunai adalah	cicilan	\tangsuran	\tkontan	\tkredit	\N	\tkontan	\N	\N	\N	\N	2
9	\N	what the meaning of kupret	au ah	pusing	bingung	ngerujak	\N	ngerujak	\N	\N	\N	\N	3
\.


--
-- TOC entry 2329 (class 0 OID 0)
-- Dependencies: 195
-- Name: soal_pk_soal_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('soal_pk_soal_seq', 9, true);


--
-- TOC entry 2266 (class 0 OID 17176)
-- Dependencies: 190
-- Data for Name: tahun_ajaran; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tahun_ajaran (pk_tahun_ajaran, tahun_ajaran, created_date, created_by, changed_date, changed_by, is_active) FROM stdin;
2	2014/2015	2016-04-19 20:46:49.461	\N	2016-04-19 20:47:01.147	\N	\N
1	2016/2017	\N	\N	2016-04-19 20:47:13.176	\N	Y
\.


--
-- TOC entry 2330 (class 0 OID 0)
-- Dependencies: 189
-- Name: tahun_ajaran_pk_tahun_ajaran_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tahun_ajaran_pk_tahun_ajaran_seq', 2, true);


--
-- TOC entry 2268 (class 0 OID 17187)
-- Dependencies: 192
-- Data for Name: ujian_masuk; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ujian_masuk (pk_ujian_masuk, fk_tahun_ajaran, fk_pelajaran, created_date, created_by, changed_date, changed_by, fk_pendaftaran, tahun_ajaran) FROM stdin;
50	\N	1	2016-04-19 19:15:11.113	\N	\N	\N	24	\N
51	\N	1	2016-04-19 19:16:27.534	\N	\N	\N	25	\N
52	\N	1	2016-04-19 19:16:51.121	\N	\N	\N	26	\N
\.


--
-- TOC entry 2270 (class 0 OID 17195)
-- Dependencies: 194
-- Data for Name: ujian_masuk_detail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ujian_masuk_detail (pk_ujian_masuk_detail, fk_ujian_masuk, fk_soal, jawaban_soal, jawaban_siswa, fk_pendaftaran) FROM stdin;
98	50	4	2	2	\N
99	50	5	6	7	\N
100	51	4	2	\N	\N
101	51	5	6	\N	\N
102	52	4	2	2	\N
103	52	5	6	6	\N
\.


--
-- TOC entry 2331 (class 0 OID 0)
-- Dependencies: 193
-- Name: ujian_masuk_detail_pk_ujian_masuk_detail_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ujian_masuk_detail_pk_ujian_masuk_detail_seq', 103, true);


--
-- TOC entry 2332 (class 0 OID 0)
-- Dependencies: 191
-- Name: ujian_masuk_pk_ujian_masuk_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('ujian_masuk_pk_ujian_masuk_seq', 52, true);


--
-- TOC entry 2128 (class 2606 OID 17514)
-- Name: app_menu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY app_menu
    ADD CONSTRAINT app_menu_pkey PRIMARY KEY (pk_menu);


--
-- TOC entry 2126 (class 2606 OID 17226)
-- Name: app_parameter_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY app_parameter
    ADD CONSTRAINT app_parameter_pkey PRIMARY KEY (pk_app_parameter);


--
-- TOC entry 2132 (class 2606 OID 17536)
-- Name: app_role_menu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY app_role_menu
    ADD CONSTRAINT app_role_menu_pkey PRIMARY KEY (pk_role_menu);


--
-- TOC entry 2130 (class 2606 OID 17525)
-- Name: app_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY app_role
    ADD CONSTRAINT app_role_pkey PRIMARY KEY (pk_role);


--
-- TOC entry 2134 (class 2606 OID 17547)
-- Name: app_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY app_user
    ADD CONSTRAINT app_user_pkey PRIMARY KEY (pk_user);


--
-- TOC entry 2136 (class 2606 OID 17555)
-- Name: app_user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY app_user_role
    ADD CONSTRAINT app_user_role_pkey PRIMARY KEY (pk_user_role);


--
-- TOC entry 2138 (class 2606 OID 17635)
-- Name: hasil_ujian_masuk_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hasil_ujian_masuk
    ADD CONSTRAINT hasil_ujian_masuk_pkey PRIMARY KEY (pk_hasil_ujian_masuk);


--
-- TOC entry 2140 (class 2606 OID 17647)
-- Name: jadwaL_ujian_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY jadwal_ujian
    ADD CONSTRAINT "jadwaL_ujian_pkey" PRIMARY KEY (pk_jadwal_ujian);


--
-- TOC entry 2142 (class 2606 OID 17658)
-- Name: kelas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY kelas
    ADD CONSTRAINT kelas_pkey PRIMARY KEY (pk_kelas);


--
-- TOC entry 2114 (class 2606 OID 17165)
-- Name: pelajaran_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pelajaran
    ADD CONSTRAINT pelajaran_pkey PRIMARY KEY (pk_pelajaran);


--
-- TOC entry 2116 (class 2606 OID 17173)
-- Name: pendaftaran_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pendaftaran_detail
    ADD CONSTRAINT pendaftaran_detail_pkey PRIMARY KEY (pk_pendaftaran_detail);


--
-- TOC entry 2110 (class 2606 OID 17143)
-- Name: pendaftaran_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pendaftaran
    ADD CONSTRAINT pendaftaran_pkey PRIMARY KEY (pk_pendaftaran);


--
-- TOC entry 2112 (class 2606 OID 17154)
-- Name: siswa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY siswa
    ADD CONSTRAINT siswa_pkey PRIMARY KEY (pk_siswa);


--
-- TOC entry 2124 (class 2606 OID 17214)
-- Name: soal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY soal
    ADD CONSTRAINT soal_pkey PRIMARY KEY (pk_soal);


--
-- TOC entry 2118 (class 2606 OID 17184)
-- Name: tahun_ajaran_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tahun_ajaran
    ADD CONSTRAINT tahun_ajaran_pkey PRIMARY KEY (pk_tahun_ajaran);


--
-- TOC entry 2122 (class 2606 OID 17203)
-- Name: ujian_masuk_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ujian_masuk_detail
    ADD CONSTRAINT ujian_masuk_detail_pkey PRIMARY KEY (pk_ujian_masuk_detail);


--
-- TOC entry 2120 (class 2606 OID 17192)
-- Name: ujian_masuk_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ujian_masuk
    ADD CONSTRAINT ujian_masuk_pkey PRIMARY KEY (pk_ujian_masuk);


--
-- TOC entry 2297 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-04-20 13:58:17

--
-- PostgreSQL database dump complete
--

