select * from PATIENT where id=100;

select * from PATIENT where id>226;

/*TESTS IDs 1-27; 34; 181-182; 193-195; 197-199; 221-223; 225; 227-228. 
ID 210 and 211 ->  duplicated
IDs 302 to 380 -> testagem servidores IF (terceirizados)
*/

select id, fullname, cityName, neighborhoodName, zipCode, resultCode, android_id from PATIENT 
where id>27 and id not in (34, 181,182, 193, 194, 195, 197, 198, 199, 221,223, 225, 227, 228)
	and (cityName='Berizal' or zipCode='39555-000');

select male, resultCode, count(*) from PATIENT where id>27 and id <>34 group by male, resultCode;
select female, count(*) from PATIENT where id>27 and id <>34 group by female;
select otherGender, count(*) from PATIENT where id>27 and id <>34 group by otherGender;

select count(*) from PATIENT where id>27 and created_at < '2020-08-25 18:09:00';

select cityName, neighborhoodName, resultCode, count(*) from PATIENT where id>27 and cityName='Salinas' group by cityName, neighborhoodName, resultCode;

select cityName, resultCode, count(*) from PATIENT where id>27  group by cityName, resultCode;

select cityName, count(*) from PATIENT where id>27  group by cityName;

select * from PATIENT where id > 224;

select id, resultCode, created_at, cityName, zipcode, neighborhoodName,  fullName, email, phone, 
IF (ISNULL(visitedPoints), null, (ST_AsText(visitedPoints))) as 'gps', android_id 
from PATIENT where id>487;
/*from PATIENT where id>27 and id <>34 order by id;*/
/*from PATIENT where id>27 and id <>34 order by resultCode desc, cityName;*/

select * from PATIENT where id=482;

select now();


