-- Data insertion example
INSERT INTO patient(male, citySalinas, phoneDWA, emailDWA, email, visitedPoints) VALUES (true, true, true, false, 'luizneto.ifnmg@gmail.com', ST_GeometryFromText('MULTIPOINT(-18.5779703 -45.4514505)'))

-- Data fetch example
SELECT ST_AsBinary(visitedPoints) FROM patient
	-- You may use ST_AsText(visitedPoints) if you aren't going to work with Blob response