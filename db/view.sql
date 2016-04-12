create or replace view vw_stock_barang as
select 
	fk_barang,
	kode_barang,
	nama_barang,
	konversi_satuan,
	lookup_value,
	tanggal,
	fk_lookup,
	(select * from fn_stock_awal_barang(fk_barang,tanggal)) stock_awal, 
	coalesce(sum(case when type='beli' then jml end),0)::integer as beli, 
	coalesce(sum(case when type='jual' then jml end),0)::integer as jual,
	(select * from fn_stock_awal_barang(fk_barang,tanggal))+(coalesce(sum(case when type='beli' then jml end),0)-coalesce(sum(case when type='jual' then jml end),0))::integer stock_akhir
from (
select 
	b.tanggal,
	a.fk_barang,
	'beli' as type,
	coalesce(sum(a.qty_barang),0) jml,		
	min(a.stock_awal_barang) stock_awal
from pembelian_detail a, pembelian b
where b.pk_pembelian = a.fk_pembelian 
group by a.fk_barang,b.tanggal
union
select 
	b.tanggal,
	a.fk_barang,
	'jual' as type,
	coalesce(sum(a.qty_barang),0) jml,
	min(a.stock_awal_barang) stock_awal		
from penjualan_detail a, penjualan b
where b.pk_penjualan = a.fk_penjualan 
group by a.fk_barang,b.tanggal
) q, barang z, lookup x 
where q.fk_barang = z.pk_barang and z.fk_lookup = x.pk_lookup
group by kode_barang,nama_barang,fk_barang,konversi_satuan,lookup_value,tanggal,fk_lookup
order by lookup_value,kode_barang;

create or replace view vw_stock_jaminan as
select 
	tanggal, 
	fk_jaminan,
	kode_jaminan,
	nama_jaminan,
	(select * from fn_stock_awal_jaminan(fk_jaminan,tanggal)) stock_awal, 
	coalesce(sum(case when type='beli' then jml end),0) as beli, 
	coalesce(sum(case when type='jual' then jml end),0) as jual,
	(select * from fn_stock_awal_jaminan(fk_jaminan,tanggal))+coalesce(sum(case when type='jual' then jml end),0)-coalesce(sum(case when type='beli' then jml end),0) stock_akhir   
from (
select 
	b.tanggal,
	a.fk_jaminan,
	'beli' as type,
	coalesce(sum(a.qty_jaminan),0) jml,		
	min(a.stock_awal_jaminan) stock_awal
from pembelian_jaminan a, pembelian b
where b.pk_pembelian = a.fk_pembelian 
group by a.fk_jaminan,b.tanggal
union
select 
	b.tanggal,
	a.fk_jaminan,
	'jual' as type,
	coalesce(sum(a.qty_jaminan),0) jml,
	min(a.stock_awal_jaminan) stock_awal		
from penjualan_jaminan a, penjualan b
where b.pk_penjualan = a.fk_penjualan 
group by a.fk_jaminan,b.tanggal
) q, jaminan z 
where q.fk_jaminan = z.pk_jaminan
group by tanggal,kode_jaminan,nama_jaminan,fk_jaminan
order by q.tanggal