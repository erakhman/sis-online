package com.beesinergi.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beesinergi.exception.ErrorHolder;
import com.beesinergi.exception.SystemException;
import com.beesinergi.mapper.JadwalUjianMapper;
import com.beesinergi.mapper.UserMapper;
import com.beesinergi.mapper.UserRoleMapper;
import com.beesinergi.model.AppRole;
import com.beesinergi.model.AppUser;
import com.beesinergi.model.AppUserRole;
import com.beesinergi.model.JadwalUjian;
import com.beesinergi.util.Paging;
import com.beesinergi.util.PasswordUtil;
import com.beesinergi.util.SystemConstant;
import com.beesinergi.util.SystemParameter;

@Service("userService")
public class UserService implements CommonService<AppUser> {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private JadwalUjianMapper jadwalUjianMapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public AppUser findById(int id) {
		AppUser user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public Paging findAllByPaging(Paging paging, AppUser param) {
		SqlSession session = sqlSessionFactory.openSession();
		String sqlMapper = "com.beesinergi.mapper.UserMapper.selectAll";
		List<AppUser> list = session.selectList(sqlMapper, param, new RowBounds(paging.getOffset(),paging.getLimit()));
		Paging result = new Paging(list);
		session.close();
		return result;
	}

	@Override
	public void save(AppUser object) {
		object.setFullName(object.getFullName());
		if (object.getPkUser() == null){
			generateDefaultPassword(object);
			object.setCreatedDate(new Date());
			object.setWrongPassword(0);
			userMapper.insert(object);
		} else{
			if (object.getIsLocked() == null){
				object.setIsLocked(SystemConstant.YES);
			}
			object.setChangedDate(new Date());
			userMapper.updateByPrimaryKey(object);
		}
		if (object.getFkRoleList() != null){
			deleteUserRole(object);
			saveUserRole(object);
		}
	}
	
	public void deleteUserRole(AppUser user) {
		userRoleMapper.deleteByFkUser(user.getPkUser());
	}
	
	public void saveUserRole(AppUser user) {
		for (Integer fkRole:user.getFkRoleList()){
			AppUserRole userRole = new AppUserRole();
			userRole.setFkUser(user.getPkUser());
			userRole.setFkRole(fkRole);
			userRoleMapper.insert(userRole);
		}
	}
	
	public void generateDefaultPassword(AppUser user) {
		String encryptPassword = PasswordUtil.getEncryptPassword(SystemParameter.DEFAULT_PASSWORD);
		user.setPassword(encryptPassword);
		user.setChangePassword(SystemConstant.YES);
	}
	
	public void delete(List<Integer> pkuser) {
		userMapper.deleteByPrimaryKey(pkuser);
	}
	
	public void resetPassword(List<Integer> pkAppUsers) {
		for (Integer pkAppUser:pkAppUsers){
			AppUser user = new AppUser();
			user.setPkUser(pkAppUser);
			user.setIsLocked(SystemConstant.NO);
			user.setWrongPassword(0);
			generateDefaultPassword(user);
			save(user);
		}
	}

	@Override
	public Integer getCount(AppUser param) {
		int count = userMapper.selectCount(param);
		return count;
	}

	@Override
	public List<AppUser> findAll(AppUser param) {
		List<AppUser> list = userMapper.selectAll(param);
		return list;
	}
	
	public AppUser login(AppUser param) throws SystemException {
		AppUser user = userMapper.selectByUserName(param.getUserName());
		boolean userNotFound = false;
		if (user != null){
			if (user.getIsLocked().equals(SystemConstant.YES)){
				throw new SystemException(new ErrorHolder("User anda terkunci, silahkan hubungi Administrator."));
			} 
			List<AppUserRole> roleList = user.getUserRoleList();
			if (!roleList.isEmpty()){
				AppUserRole role = roleList.get(0);
				if (role.getFkRole().equals(SystemConstant.PK_USER_ROLE_CALON_SISWA)){
					JadwalUjian param2 = new JadwalUjian();
					param2.setIsActive(SystemConstant.YES);
					param2.setFkLookupType(SystemConstant.JadwalUjianType.UJIAN_MASUK);
					List<JadwalUjian> jadwalUjian = jadwalUjianMapper.selectAll(param2);
					if (jadwalUjian.isEmpty()){
						throw new SystemException(new ErrorHolder("Saat ini belum ada jadwal ujian."));
					}
				}
			}
			if (PasswordUtil.checkPassword(param.getPassword(),user.getPassword())){
				user.setLastLoginDate(new Date());
			} else{
				int wrongPassword = user.getWrongPassword();
				wrongPassword++;
				user.setWrongPassword(wrongPassword);
				if (wrongPassword >= SystemParameter.MAX_WRONG_PASSWORD){
					user.setIsLocked(SystemConstant.YES);
				}
				userNotFound = true;
			}
			save(user);
		} else{
			userNotFound = true;
		}
		if (userNotFound){
			throw new SystemException(new ErrorHolder("Username/Password yang anda masukan salah."));
		}
		return user;
	}

}
