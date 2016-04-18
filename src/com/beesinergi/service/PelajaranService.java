package com.beesinergi.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.PelajaranMapper;
import com.beesinergi.model.Pelajaran;
import com.beesinergi.util.Paging;

@Service("pelajaranService")
public class PelajaranService implements CommonService<Pelajaran> {
	
	@Autowired
	private PelajaranMapper pelajaranMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public Pelajaran findById(int id) {
		Pelajaran param = new Pelajaran();
		param.setPkPelajaran(id);
		List<Pelajaran> list = pelajaranMapper.selectAll(param);
		return list.get(0);
	}
	
	public Pelajaran findByName(String nama) {
		Pelajaran param = new Pelajaran();
		param.setNamaPelajaran(nama.toLowerCase());
		List<Pelajaran> list = pelajaranMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, Pelajaran param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.PelajaranMapper.selectAll";
//		if (param != null){
//			if (param.getPelajaranName() != null){
//				param.setPelajaranName("%"+param.getPelajaranName().toLowerCase()+"%");
//			}
//		}
		List<Pelajaran> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}
	
	

	@Override
	public void save(Pelajaran object) {
		if (object.getPkPelajaran() == null){
			object.setCreatedDate(new Date());
			pelajaranMapper.insert(object);
		} else{
//			if (object.getIsActive() == null){
//				object.setIsActive(SystemConstant.NO);
//			}
			object.setChangedDate(new Date());
			pelajaranMapper.updateByPrimaryKey(object);
		}
	}

	@Override
	public List<Pelajaran> findAll(Pelajaran param) {
//		if (param != null){
//			if (param.getPelajaranName() != null){
//				param.setPelajaranName("%"+param.getPelajaranName().toLowerCase()+"%");
//			}
//		}
		List<Pelajaran> list = pelajaranMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(Pelajaran param) {
//		if (param.getIsActive() == null){
//			param.setIsActive(SystemConstant.YES);
//		}
		int count = pelajaranMapper.selectCount(param);
		return count;
	}
	

}
