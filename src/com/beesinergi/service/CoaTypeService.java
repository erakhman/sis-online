package com.beesinergi.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.CoaTypeMapper;
import com.beesinergi.mapper.PelajaranMapper;
import com.beesinergi.model.CoaType;
import com.beesinergi.model.Pelajaran;
import com.beesinergi.util.Paging;

@Service("coaTypeService")
public class CoaTypeService implements CommonService<CoaType> {
	
	@Autowired
	private CoaTypeMapper coaTypeMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public CoaType findById(int id) {
		CoaType param = new CoaType();
		param.setPkCoaType(id);
		List<CoaType> list = coaTypeMapper.selectAll(param);
		return list.get(0);
	}
	
	public CoaType findByName(String nama) {
		CoaType param = new CoaType();
		param.setCoaTypeName(nama.toLowerCase());
		List<CoaType> list = coaTypeMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, CoaType param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.CoaTypeMapper.selectAll";
//		if (param != null){
//			if (param.getPelajaranName() != null){
//				param.setPelajaranName("%"+param.getPelajaranName().toLowerCase()+"%");
//			}
//		}
		List<CoaType> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}
	
	

	@Override
	public void save(CoaType object) {
		if (object.getPkCoaType() == null){
			coaTypeMapper.insert(object);
		} else{
			coaTypeMapper.updateByPrimaryKey(object);
		}
	}

	@Override
	public List<CoaType> findAll(CoaType param) {
		List<CoaType> list = coaTypeMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(CoaType param) {
		int count = coaTypeMapper.selectCount(param);
		return count;
	}
	

}
