package com.beesinergi.mapper;

import java.util.List;

import com.beesinergi.model.Pendaftaran;

public interface PendaftaranMapper extends CommonMapper<Pendaftaran> {

	public int examPass(List<Integer> pkPendaftaranList);
	
}
