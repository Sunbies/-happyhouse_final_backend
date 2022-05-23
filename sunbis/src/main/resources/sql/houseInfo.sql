select * from member;
update member set password='ssafy', name='ssafy' where id = 'ssafy';
select * from houseinfo;
select * from housedeal ;
-- 필요한 쿼리문 정리
-- 1. 시도코드 가지고오기
select left(sidoCode,2) sidoCode, sidoName from sidocode order by sidoCode;
-- 2. 구군코드 가지고오기 #{sido}
select left(gugunCode,5) gugunCode, gugunName from guguncode where left(gugunCode,2) = "11" order by gugunCode;

--  3. 동 가지고오기 #{gugun} 
select distinct dong, dongCode	from houseinfo	where left(dongCode, 5) = "11410" order by dong;

-- 4. 동 안에 있는 아파트들의 거래정보 가지고오기 #{dong} 
-- 가져와야할 정보 : aptCode, apartmentName, sidoName, gugunName, dongCode, dong, jibun, roadName, buildYear, lng, lat,
--               no, dealAmount, dealYear, dealMonth, dealDay, area, floor
select * from houseinfo ;
-- 우선 houseinfo안에서 dongCode가 일치하는 aptCode들을 다 찾는다.
select aptCode from houseinfo where dongCode = '1111018700';
-- 이제 여기서 housedeal에서 aptCode가 일치하는 애들을 가지고온다.
select * from housedeal where aptCode = any(select aptCode from houseinfo where dongCode = '1111018700');
 -- 이제 필요한 컬럼들을 모아놔야한다.
 -- 가져와야 할 정보
 -- houseinfo = aptCode, apartmentName, dong, jibun, roadName, buildYear, lng, lat
 -- housedeal = no, dealAmount, dealYear, dealMonth, dealDay, area, floor
 -- sidoCode = sidoName
 -- guguncode = gugunName
 -- 이렇게 가지고와야한다.
 -- 일단 sidoName, gugunName만 빼고 구해보자
select * from housedeal as d 
left join houseinfo as i on d.aptCode = i.aptCode
left join sidocode as si on left(i.dongcode,2) = left(si.sidocode,2)
left join guguncode as gu on left(i.dongcode,5) = left(gu.guguncode,5)
where dongcode = '1111018700'
order by i.aptCode;

select * from housedeal as d 
left join houseinfo as i on d.aptCode = i.aptCode
left join dongcode as o on o.dongCode = i.dongCode
where i.dongcode = '1111018700'
order by i.aptCode;

-- 가져와야할 정보 : aptCode, apartmentName, sidoName, gugunName, dongCode, dong, jibun, roadName, buildYear, lng, lat,
--               no, dealAmount, dealYear, dealMonth, dealDay, area, floor
 -- houseinfo = aptCode, apartmentName, dong, jibun, roadName, buildYear, lng, lat
 -- housedeal = no, dealAmount, dealYear, dealMonth, dealDay, area, floor
 -- sidoCode = sidoName
 -- guguncode = gugunName
 -- 결과
select i.aptCode, i.apartmentName, o.sidoName, o.gugunName, 
i.dong, i.jibun, i.roadName, 
i.buildYear, i.lng, i.lat, d.no, d.dealAmount, d.dealYear, d.dealMonth, d.dealDay,
d.area, d.floor from housedeal as d 
left join houseinfo as i on d.aptCode = i.aptCode
left join dongcode as o on o.dongCode = i.dongCode
where i.dongcode = '1111018700'
order by i.aptCode;

-- 5. 동 안에 있는 아파트들의 거래정보 중에 년 월이 일치하는 거래정보들만 가지고오기
select i.aptCode, i.apartmentName, o.sidoName, o.gugunName, 
i.dong, i.jibun, i.roadName, 
i.buildYear, i.lng, i.lat, d.no, d.dealAmount, d.dealYear, d.dealMonth, d.dealDay,
d.area, d.floor from housedeal as d 
left join houseinfo as i on d.aptCode = i.aptCode
left join dongcode as o on o.dongCode = i.dongCode
where i.dongcode = '1111018700' 
and d.dealYear = "2017"
and d.dealMonth = "3"
order by d.dealDay;

select dongCode, dongName
		from dongcode
		where left(dongCode,5) = '11200'
		order by dongCode;

