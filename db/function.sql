CREATE OR REPLACE FUNCTION fn_stock_awal_barang(fk_barang integer, tanggal timestamp)
RETURNS integer AS $$
select b.stock_awal_barang from pembelian a, pembelian_detail b where a.pk_pembelian = b.fk_pembelian and a.tanggal = $2 
and b.fk_barang = $1
union 
select b.stock_awal_barang from penjualan a, penjualan_detail b where a.pk_penjualan = b.fk_penjualan and a.tanggal = $2 
and b.fk_barang = $1
$$ LANGUAGE sql;

CREATE OR REPLACE FUNCTION fn_stock_awal_jaminan(fk_jaminan integer, tanggal timestamp)
RETURNS integer AS $$
select b.stock_awal_jaminan from pembelian a, pembelian_jaminan b where a.pk_pembelian = b.fk_pembelian and a.tanggal = $2 
and b.fk_jaminan = $1
union 
select b.stock_awal_jaminan from penjualan a, penjualan_jaminan b where a.pk_penjualan = b.fk_penjualan and a.tanggal = $2 
and b.fk_jaminan = $1
$$ LANGUAGE sql;
