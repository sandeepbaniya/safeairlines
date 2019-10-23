
-- INSERT INTO `airport` VALUES (9,'Cedar Rapids','JFK'),(2,'Cedar Rapids','CID');
-- INSERT INTO `safeairlines`.`airport` (`description`, `name`) VALUES ('Chicago', 'O-Hare International Airport	');
-- INSERT INTO `safeairlines`.`airport` (`description`, `name`) VALUES ('Texas', 'Dallas/Fort Worth International Airport');
-- INSERT INTO `safeairlines`.`airport` (`description`, `name`) VALUES ('Denver Denver International Airport', 'DEN - Denver International Airport');
-- INSERT INTO `safeairlines`.`airport` (`description`, `name`) VALUES ('Los Angeles - Los Angeles International Airport', 'LAX - Los Angeles International Airport');



-- INSERT INTO `safeairlines`.`flight` (`arrival_date`, `dept_date`, `flight_number`, `numb_seat`, `plane_number`, `price`, `arrival_place_id`, `depature_place_id`) VALUES ('2019-11-24', '2019-11-22', 'FL0003', '50', 'PL0004', '450', '1', '2');
-- INSERT INTO `safeairlines`.`flight` (`arrival_date`, `dept_date`, `flight_number`, `numb_seat`, `plane_number`, `price`, `arrival_place_id`, `depature_place_id`) VALUES ('2019-11-12', '2019-11-10', 'FL0004', '90', 'PL0005', '650', '12', '13');

INSERT INTO `role` VALUES (1,'USER'),(2,'ADMIN');

INSERT INTO `user` VALUES(18,'user@gmail.com','user','user',NULL,'$2a$10$dFi2oxht3qrS1eDpTLmsNuny3nK5vWnfgAeg7ZeLGBdaXiWbQiPYW',NULL),
                          (19,'admin@gmail.com','admin','admin',NULL,'$2a$10$DqMgM9Go7Q2Uxdgr0SU3eOmiwmxjp81ZCRV1BY03kTiMDtxz4haFG',NULL);


INSERT INTO `users_roles` VALUES (7,1),(8,1),(9,2),(10,1),(11,1),(12,1),(18,1),(19,2);


