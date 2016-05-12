CREATE OR REPLACE FUNCTION fn_tahun_ajaran()
RETURNS integer AS $$
select pk_tahun_ajaran from tahun_ajaran where is_active = 'Y'
$$ LANGUAGE sql;
