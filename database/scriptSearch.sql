select * from PATIENT where id=100;

select * from PATIENT where id>27;

select male, resultCode, count(*) from PATIENT where id>27 and id <>34 group by male, resultCode;
select female, count(*) from PATIENT where id>27 and id <>34 group by female;
select otherGender, count(*) from PATIENT where id>27 and id <>34 group by otherGender;

select count(*) from PATIENT where id>27 and created_at < '2020-08-25 18:09:00';

select cityName, neighborhoodName, resultCode, count(*) from PATIENT where id>27 and cityName='Salinas' group by cityName, neighborhoodName, resultCode;

select cityName, resultCode, count(*) from PATIENT where id>27  group by cityName, resultCode;

select cityName, count(*) from PATIENT where id>27  group by cityName;

select id, resultCode, created_at, cityName, zipcode, neighborhoodName,  fullName, email, phone, 
IF (ISNULL(visitedPoints), null, (ST_AsText(visitedPoints))) as 'gps' 
from PATIENT where id>156;
/*from PATIENT where id>27 and id <>34 order by id;*/
/*from PATIENT where id>27 and id <>34 order by resultCode desc, cityName;*/

select now();
