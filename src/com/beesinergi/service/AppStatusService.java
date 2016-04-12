package com.beesinergi.service;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.AppStatusMapper;
import com.beesinergi.model.AppStatus;
import com.beesinergi.util.Paging;

@Service("appStatusService")
public class AppStatusService implements CommonService<AppStatus> {
	
	@Autowired
	private AppStatusMapper appStatusMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public List<AppStatus> findAllByType(String type) {
		List<AppStatus> list = appStatusMapper.selectByType(type);
		return list;
	}

	@Override
	public AppStatus findById(int id) {
		return null;
	}

	@Override
	public Paging findAllByPaging(Paging paging, AppStatus param) {
		return null;
	}

	@Override
	public void save(AppStatus object) throws Exception {
	}

	@Override
	public List<AppStatus> findAll(AppStatus param) {
		return null;
	}

	@Override
	public Integer getCount(AppStatus param) {
		return null;
	}
	

}
