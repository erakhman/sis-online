package com.beesinergi.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.HasilUjianMasukMapper;
import com.beesinergi.model.HasilUjianMasuk;
import com.beesinergi.model.Pendaftaran;
import com.beesinergi.util.Paging;

@Service("hasilUjianMasukService")
public class HasilUjianMasukService implements CommonService<HasilUjianMasuk> {
	
	@Autowired
	private HasilUjianMasukMapper hasilUjianMasukMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public HasilUjianMasuk findById(int id) {return null;}

	@Override
	public Paging findAllByPaging(Paging paging, HasilUjianMasuk param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.HasilUjianMasukMapper.selectAll";
		List<Pendaftaran> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(HasilUjianMasuk object) throws Exception {}

	@Override
	public List<HasilUjianMasuk> findAll(HasilUjianMasuk param) {
		List<HasilUjianMasuk> list = hasilUjianMasukMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(HasilUjianMasuk param) {
		int count = hasilUjianMasukMapper.selectCount(param);
		return count;
	}

}
