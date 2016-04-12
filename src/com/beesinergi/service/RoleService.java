package com.beesinergi.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.mapper.RoleMapper;
import com.beesinergi.mapper.RoleMenuMapper;
import com.beesinergi.model.AppRole;
import com.beesinergi.model.AppRoleMenu;
import com.beesinergi.util.Paging;
import com.beesinergi.util.SystemConstant;

@Service("roleService")
public class RoleService implements CommonService<AppRole> {
	
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public AppRole findById(int id) {
		AppRole appRole = roleMapper.selectByPrimaryKey(id);
		return appRole;
	}

	@Override
	public Paging findAllByPaging(Paging paging, AppRole param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.RoleMapper.selectAll";
		if (param != null){
			if (param.getRoleName() != null){
				param.setRoleName("%"+param.getRoleName().toLowerCase()+"%");
			}
		}
		List<AppRole> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(AppRole object) throws Exception {
		if (object.getPkRole() == null){
			object.setCreatedDate(new Date());
			roleMapper.insert(object);
		} else{
			if (object.getIsActive() == null){
				object.setIsActive(SystemConstant.NO);
			}
			object.setChangedDate(new Date());
			roleMapper.updateByPrimaryKey(object);
			
			roleMenuMapper.deleteByFkRole(object.getPkRole());
		}
		saveRoleMenu(object);
	}
	
	public void saveRoleMenu(AppRole object) {
		for (Integer fkMenu:object.getFkMenuList()){
			AppRoleMenu menu = new AppRoleMenu();
			menu.setFkMenu(fkMenu);
			menu.setFkRole(object.getPkRole());
			roleMenuMapper.insert(menu);
		}
	}

	@Override
	public List<AppRole> findAll(AppRole param) {
		List<AppRole> list = roleMapper.selectAll(param);
		return list;
	}

	@Override
	public Integer getCount(AppRole param) {
		if (param != null){
			if (param.getRoleName() != null){
				param.setRoleName("%"+param.getRoleName().toLowerCase()+"%");
			}
		}
		int count = roleMapper.selectCount(param);
		return count;
	}
	

}
