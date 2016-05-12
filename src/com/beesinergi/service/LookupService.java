package com.beesinergi.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.LookupMapper;
import com.beesinergi.model.Lookup;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;

@Service("lookupService")
public class LookupService implements CommonService<Lookup>{
	
	@Autowired
	private LookupMapper lookupMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public Lookup findById(int id) {
		Lookup param = new Lookup();
		param.setPkLookup(id);
		List<Lookup> list = lookupMapper.selectAll(param);
		return list.get(0);
	}
	
	public List<Lookup> findAllByGroup(int type) {
		Lookup param = new Lookup();
		param.setFkLookupGroup(type);
		List<Lookup> list = lookupMapper.selectAll(param);
		return list;
	}

	@Override
	public Paging findAllByPaging(Paging paging, Lookup param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.LookupMapper.selectAll";
		List<Lookup> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Lookup object) {
		if (object.getPkLookup() == null){
			object.setCreatedDate(new Date());
			lookupMapper.insert(object);
		} else{
			if (object.getIsActive() == null){
				object.setIsActive(SystemConstant.NO);
			}
			object.setChangedDate(new Date());
			lookupMapper.updateByPrimaryKey(object);
		}
	}

	@Override
	public Integer getCount(Lookup param) {
		if (param.getIsActive() == null){
			param.setIsActive(SystemConstant.YES);
		}
		int count = lookupMapper.selectCount(param);
		return count;
	}

	@Override
	public List<Lookup> findAll(Lookup param) {
		if (param.getIsActive() == null){
			param.setIsActive(SystemConstant.YES);
		}
		List<Lookup> list = lookupMapper.selectAll(param);
		return list;
	}
	


}
