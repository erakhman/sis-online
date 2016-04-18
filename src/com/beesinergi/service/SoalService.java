package com.beesinergi.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.SoalMapper;
import com.beesinergi.model.Soal;
import com.beesinergi.util.Paging;

@Service("soalService")
public class SoalService implements CommonService<Soal> {
	
	@Autowired
	private SoalMapper soalMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public Soal findById(int id) {
		Soal param = new Soal();
		param.setPkSoal(id);
		List<Soal> list = soalMapper.selectAll(param);
		return list.get(0);
	}
	

	@Override
	public Paging findAllByPaging(Paging paging, Soal param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.SoalMapper.selectAll";
//		if (param != null){
//			if (param.getSoalName() != null){
//				param.setSoalName("%"+param.getSoalName().toLowerCase()+"%");
//			}
//		}
		List<Soal> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}
	
	

	@Override
	public void save(Soal object) {
		if (object.getPkSoal() == null){
			object.setCreatedDate(new Date());
			soalMapper.insert(object);
		} else{
//			if (object.getIsActive() == null){
//				object.setIsActive(SystemConstant.NO);
//			}
			object.setChangedDate(new Date());
			soalMapper.updateByPrimaryKey(object);
		}
	}

	@Override
	public List<Soal> findAll(Soal param) {
//		if (param != null){
//			if (param.getSoalName() != null){
//				param.setSoalName("%"+param.getSoalName().toLowerCase()+"%");
//			}
//		}
		List<Soal> list = soalMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(Soal param) {
//		if (param.getIsActive() == null){
//			param.setIsActive(SystemConstant.YES);
//		}
		int count = soalMapper.selectCount(param);
		return count;
	}
	

}
