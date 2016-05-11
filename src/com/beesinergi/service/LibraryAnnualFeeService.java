package com.beesinergi.service;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.LibraryAnnualFeeMapper;
import com.beesinergi.model.LibraryAnnualFee;

import com.beesinergi.util.Paging;


@Service("libraryAnnualFeeService")
public class LibraryAnnualFeeService implements CommonService<LibraryAnnualFee> {
	
	@Autowired
	private LibraryAnnualFeeMapper libraryAnnualFeeMapper;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public LibraryAnnualFee findById(int id) {
		LibraryAnnualFee libraryAnnualFee = libraryAnnualFeeMapper.selectByPrimaryKey(id);
		return libraryAnnualFee;
	}

	@Override
	public Paging findAllByPaging(Paging paging, LibraryAnnualFee param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.LibraryAnnualFeeMapper.selectAll";
		/*if (param != null){
			if (param.getTahun_ajaran() != null){
				param.setTahun_ajaran("%"+param.getTahun_ajaran().toLowerCase()+"%");
			}
		}*/
		List<LibraryAnnualFee> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(LibraryAnnualFee object) throws Exception {
		if (object.getId() == null){			
			libraryAnnualFeeMapper.insert(object);
		} else{
			
			libraryAnnualFeeMapper.updateByPrimaryKey(object);
			
			
		}
		
	}
	
	
	@Override
	public List<LibraryAnnualFee> findAll(LibraryAnnualFee param) {
		List<LibraryAnnualFee> list = libraryAnnualFeeMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(LibraryAnnualFee param) {
		/*if (param != null){
			if (param.getTahun_ajaran() != null){
				param.setTahun_ajaran("%"+param.getTahun_ajaran().toLowerCase()+"%");
			}
		}*/
		int count = libraryAnnualFeeMapper.selectCount(param);
		return count;
	}
	

}
