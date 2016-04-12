package com.beesinergi.mapper;

import com.beesinergi.model.PendaftaranDetail;

public interface PendaftaranDetailMapper extends CommonMapper<PendaftaranDetail> {
	
	public void deleteByFkPendaftaran(int fkPendaftaran);
	
}
