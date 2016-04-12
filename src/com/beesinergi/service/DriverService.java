package com.beesinergi.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.DriverMapper;
import com.beesinergi.model.Driver;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;

@Service("driverService")
public class DriverService implements CommonService<Driver> {
	
	@Autowired
	private DriverMapper driverMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public Driver findById(int id) {
		Driver param = new Driver();
		param.setPkDriver(id);
		List<Driver> list = driverMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, Driver param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.DriverMapper.selectAll";
		if (param != null){
			if (param.getDriverName() != null){
				param.setDriverName("%"+param.getDriverName().toLowerCase()+"%");
			}
		}
		List<Driver> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Driver object) throws Exception {
		if (object.getPkDriver() == null){
			object.setCreatedDate(new Date());
			driverMapper.insert(object);
		} else{
			if (object.getIsActive() == null){
				object.setIsActive(SystemConstant.NO);
			}
			object.setChangedDate(new Date());
			driverMapper.updateByPrimaryKey(object);
		}
	}

	@Override
	public List<Driver> findAll(Driver param) {
		if (param.getIsActive() == null){
			param.setIsActive(SystemConstant.YES);
		}
		List<Driver> list = driverMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(Driver param) {
		if (param != null){
			if (param.getDriverName() != null){
				param.setDriverName("%"+param.getDriverName().toLowerCase()+"%");
			}
		}
		int count = driverMapper.selectCount(param);
		return count;
	}
	

}
