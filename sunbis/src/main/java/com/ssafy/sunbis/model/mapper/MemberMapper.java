package com.ssafy.sunbis.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.sunbis.model.dto.MemberDto;

@Repository
@Mapper
public interface MemberMapper {
	MemberDto login(@Param("id") String id, @Param("password") String password);
	List<MemberDto> selectAll();
	MemberDto select(String id);
	int insert(MemberDto member);
	int update(MemberDto member);
	int delete(String id);
}
