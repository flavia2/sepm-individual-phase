-- insert initial test data
-- the IDs are hardcoded to enable references between further test data
-- negative IDs are used to not interfere with user-entered data and allow clean deletion of test data


DELETE FROM sport where ID < 0;
INSERT INTO sport (ID, NAME, DESCRIPTION)
VALUES (-1, 'Polo', 'Polo is a horseback mounted team sport.')
     , (2, 'Dressage', 'Dressage is a form of riding performed in exhibition and competition.')
     , (3, 'Foxhunting', 'Fox hunting is an activity involving the tracking, chase and, if caught, the killing of a fox.')
     , (4, 'Horseball', 'Horseball is a game played on horseback where a ball is handled and points are scored by shooting it through a hoop with a diameter of 1m. ')
     , (5, 'Yabusame', 'Yabusame is a type of mounted archery in traditional Japanese archery.')
     , (6, 'Campdrafting', 'Campdrafting is a unique Australian sport involving a horse and rider working cattle.')
     , (7, 'Reining', 'Reining is a western riding competition for horses where the riders guide the horses through a precise pattern of circles, spins, and stops.')
     , (8, 'Roadster', 'Roadster is a type of driving competition for horses and ponies where the horse and exhibitor appear in equipment similar to that used in harness racing.')
     , (9, 'Barrel racing', 'Barrel racing is a rodeo event in which a horse and rider attempt to run a cloverleaf pattern around preset barrels in the fastest time.')
     , (10, 'Trail riding', 'Trail riding is riding outdoors on trails, bridle paths, and forest roads, but not on roads regularly used by motorised traffic. ')
;
INSERT INTO horse (ID, NAME, DESCRIPTION, BIRTHDAY, GENDER, SPORT, PARENTID1, PARENTID2)
VALUES (1,'Tae','cool','1998-06-04','male',1,null,null),
       (2,'Alice','cute','1997-03-27', 'female',2,null,null),
       (3,'Wendy','','2000-08-14','female',null,2,1),
       (4,'Marcel','','2000-07-01','male',3,null,null),
       (5,'Jennifer','','2003-03-01','female',10,3,4),
       (6,'Elisabeth',null,'2008-08-29','female',null,null,null),
       (7,'Zac','','2003-09-01','male',8,null,null),
       (8,'Andrew','','2014-05-17','male',5,5,7),
       (9,'Max','','2013-05-17','male',4,2,null),
       (10,'Mike','','1998-05-17','male',2,null,null);