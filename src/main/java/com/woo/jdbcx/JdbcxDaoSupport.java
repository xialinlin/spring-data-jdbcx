/**

 * @(#)MemberRepository.java 2016年1月21日
 *
 * Copyright 2008-2016 by Woo Cupid.
 * All rights reserved.
 * 
 */
package com.woo.jdbcx;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;

/**
 * @author Woo Cupid
 * @date 2016年1月21日
 * @version $Revision$
 */
public abstract class JdbcxDaoSupport extends NamedParameterJdbcDaoSupport {

	@Autowired
	DataSource dataSource;

	NamedParameterJdbcTemplate jdbcTemplate;

	@PostConstruct
	public void init() {
		setDataSource(dataSource);
		jdbcTemplate = getNamedParameterJdbcTemplate();
	}


	// ============================ multiply fields returned =====================//

	public <T> List<T> queryForListBean(String sql, Map<String, ?> paramMap, Class<T> mapResultToClass)
			throws DataAccessException {
		return jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<T>(mapResultToClass));
	}

	public <T> List<T> queryForListBean(String sql, Object beanParamSource, Class<T> mapResultToClass)
			throws DataAccessException {
		return jdbcTemplate.query(sql, new BeanPropertySqlParameterSource(beanParamSource),
				new BeanPropertyRowMapper<T>(mapResultToClass));
	}

	public <T> List<T> queryForListBean(String sql, Class<T> mapResultToClass) throws DataAccessException {
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(mapResultToClass));
	}

	public Map<String, Object> queryForMap(String sql, Object beanParamSource) throws DataAccessException {
		return jdbcTemplate.queryForMap(sql, new BeanPropertySqlParameterSource(beanParamSource));
	}

	public Map<String, Object> queryForMap(String sql, Map<String, ?> paramMap) throws DataAccessException {
		return jdbcTemplate.queryForMap(sql, paramMap);
	}

	public Map<String, Object> queryForMap(String sql) throws DataAccessException {
		return jdbcTemplate.queryForMap(sql, EmptySqlParameterSource.INSTANCE);
	}

	public List<Map<String, Object>> queryForListMap(String sql, Map<String, ?> paramMap) throws DataAccessException {
		return jdbcTemplate.queryForList(sql, paramMap);
	}

	public List<Map<String, Object>> queryForListMap(String sql, Object beanParamSource) throws DataAccessException {
		return jdbcTemplate.queryForList(sql, new BeanPropertySqlParameterSource(beanParamSource));
	}

	public List<Map<String, Object>> queryForListMap(String sql) throws DataAccessException {
		return jdbcTemplate.queryForList(sql, EmptySqlParameterSource.INSTANCE);
	}

	// ============================ multiply fields returned =====================//


	// ============================ single field returned =====================//
	public <T> T queryForObject(String sql, Class<T> requiredType) throws DataAccessException {
		return jdbcTemplate.queryForObject(sql, EmptySqlParameterSource.INSTANCE, requiredType);
	}

	public <T> T queryForObject(String sql, Object beanParamSource, Class<T> requiredType) throws DataAccessException {
		return jdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(beanParamSource), requiredType);
	}

	public <T> T queryForObject(String sql, Map<String, ?> paramMap, Class<T> requiredType) throws DataAccessException {
		return jdbcTemplate.queryForObject(sql, paramMap, requiredType);
	}

	public <T> List<T> queryForList(String sql, Object beanParamSource, Class<T> elementType)
			throws DataAccessException {
		return jdbcTemplate.queryForList(sql, new BeanPropertySqlParameterSource(beanParamSource), elementType);
	}

	public <T> List<T> queryForList(String sql, Map<String, ?> paramMap, Class<T> elementType)
			throws DataAccessException {
		return jdbcTemplate.queryForList(sql, paramMap, elementType);
	}

	public <T> List<T> queryForList(String sql, Class<T> elementType) throws DataAccessException {
		return jdbcTemplate.queryForList(sql, EmptySqlParameterSource.INSTANCE, elementType);
	}

	// ============================ single field returned =====================//




	public int update(String sql, Object beanParamSource) throws DataAccessException {
		return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(beanParamSource));
	}

	public int update(String sql, Map<String, ?> paramMap) throws DataAccessException {
		return jdbcTemplate.update(sql, paramMap);
	}

	public int update(String sql, Object beanParamSource, KeyHolder generatedKeyHolder)
			throws DataAccessException {
		return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(beanParamSource), generatedKeyHolder);
	}

	public int update(String sql, Object beanParamSource, KeyHolder generatedKeyHolder, String[] keyColumnNames)
			throws DataAccessException {
		return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(beanParamSource), generatedKeyHolder,
				keyColumnNames);
	}

	public int[] batchUpdate(String sql, Map<String, ?>[] batchValues) {
		return jdbcTemplate.batchUpdate(sql, batchValues);
	}

	public int[] batchUpdate(String sql, Object... batchArgs) {
		SqlParameterSource[] params = new SqlParameterSource[batchArgs.length];
		for (int i = 0; i < batchArgs.length; i++) {
			params[i] = new BeanPropertySqlParameterSource(batchArgs[i]);
		}
		return jdbcTemplate.batchUpdate(sql, params);
	}

}
