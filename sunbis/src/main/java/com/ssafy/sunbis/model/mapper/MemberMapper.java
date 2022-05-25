package com.ssafy.sunbis.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.sunbis.model.dto.MemberDto;

@Repository
@Mapper
public interface MemberMapper {
	MemberDto login(@Param("id") String id, @Param("password") String password)throws Exception;
	List<MemberDto> selectAll() throws Exception;
	MemberDto select(String id)throws Exception;
	int insert(MemberDto member)throws Exception;
	int update(MemberDto member)throws Exception;
	int delete(String id)throws Exception;
}
