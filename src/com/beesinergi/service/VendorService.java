package com.beesinergi.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.VendorMapper;
import com.beesinergi.model.Vendor;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;

@Service("vendorService")
public class VendorService implements CommonService<Vendor> {
	
	@Autowired
	private VendorMapper vendorMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public Vendor findById(int id) {
		Vendor param = new Vendor();
		param.setPkVendor(id);
		List<Vendor> list = vendorMapper.selectAll(param);
		return list.get(0);
	}

	@Override
	public Paging findAllByPaging(Paging paging, Vendor param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.VendorMapper.selectAll";
		if (param != null){
			if (param.getVendorName() != null){
				param.setVendorName("%"+param.getVendorName().toLowerCase()+"%");
			}
		}
		List<Vendor> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(Vendor object) throws Exception {
		if (object.getPkVendor() == null){
			object.setCreatedDate(new Date());
			vendorMapper.insert(object);
		} else{
			if (object.getIsActive() == null){
				object.setIsActive(SystemConstant.NO);
			}
			object.setChangedDate(new Date());
			vendorMapper.updateByPrimaryKey(object);
		}
	}

	@Override
	public List<Vendor> findAll(Vendor param) {
		if (param.getIsActive() == null){
			param.setIsActive(SystemConstant.YES);
		}
		List<Vendor> list = vendorMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(Vendor param) {
		if (param != null){
			if (param.getVendorName() != null){
				param.setVendorName("%"+param.getVendorName().toLowerCase()+"%");
			}
		}
		int count = vendorMapper.selectCount(param);
		return count;
	}
	

}
