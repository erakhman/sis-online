package com.beesinergi.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.MenuMapper;
import com.beesinergi.model.AppMenu;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;

@Service("menuService")
public class MenuService implements CommonService<AppMenu> {
	
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public AppMenu findById(int id) {
		return null;
	}

	@Override
	public Paging findAllByPaging(Paging paging, AppMenu param) {
		return null;
	}

	@Override
	public void save(AppMenu object) throws Exception {
	}

	@Override
	public List<AppMenu> findAll(AppMenu param) {
		List<AppMenu> list = menuMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(AppMenu param) {
		return null;
	}
	

}
