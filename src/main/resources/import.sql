-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
insert into cardtype (id, name, createdat) values(nextval('hibernate_sequence'), 'Credit','2023.01.01');
insert into cardtype (id, name, createdat) values(nextval('hibernate_sequence'), 'Debit','2023.01.01');


insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1234','1234',  1, 2025, 123, 323, 61, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1235','1234',  2, 2025, 124, 323, 62, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1236','1234',  3, 2025, 125, 323, 63, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1237','1234',  4, 2025, 126, 324, 64, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1238','1234',  5, 2025, 127, 324, 65, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1239','1234',  6, 2025, 128, 324, 66, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1240','1234',  7, 2025, 129, 324, 67, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1241','1234',  8, 2025, 130, 324, 68, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1242','1234',  9, 2025, 131, 324, 69, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1243','1234', 10, 2025, 132, 325, 70, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1244','1234', 11, 2025, 133, 325, 71, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1245','1234', 12, 2025, 134, 325, 72, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1246','1234', 11, 2025, 135, 325, 73, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1247','1234', 10, 2025, 136, 325, 74, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1248','1234',  9, 2025, 137, 325, 75, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1249','1234',  8, 2025, 138, 325, 76, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1250','1234',  7, 2025, 139, 325, 77, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1251','1234',  6, 2025, 140, 325, 78, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1252','1234',  5, 2025, 141, 325, 79, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1253','1234',  4, 2025, 142, 325, 80, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1254','1234',  3, 2025, 143, 325, 81, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1255','1234',  2, 2025, 144, 326, 82, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1256','1234',  1, 2025, 145, 326, 83, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1257','1234',  1, 2025, 146, 326, 84, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1258','1234',  2, 2025, 147, 326, 85, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1259','1234',  3, 2025, 148, 326, 86, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1260','1234',  4, 2025, 149, 326, 87, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1261','1234',  5, 2025, 150, 326, 88, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1262','1234',  6, 2025, 151, 326, 89, 1, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1263','1234',  7, 2025, 152, 326, 90, 1, '2023.01.01');




insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1264','1234',  8, 2025, 153, 323,  1, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1265','1234',  9, 2025, 154, 323,  2, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1266','1234', 10, 2025, 155, 323,  3, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1267','1234', 11, 2025, 156, 324,  4, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1268','1234', 12, 2025, 157, 324,  5, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1269','1234', 12, 2025, 158, 324,  6, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1270','1234', 11, 2025, 159, 324,  7, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1271','1234', 10, 2025, 160, 324,  8, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1272','1234',  9, 2025, 161, 324,  9, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1273','1234',  8, 2025, 162, 325, 10, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1274','1234',  7, 2025, 163, 325, 11, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1275','1234',  6, 2025, 164, 325, 12, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1276','1234',  5, 2025, 165, 325, 13, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1277','1234',  4, 2025, 166, 325, 14, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1278','1234',  3, 2025, 167, 325, 15, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1279','1234',  2, 2025, 168, 325, 16, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1280','1234',  1, 2025, 169, 325, 17, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1281','1234',  1, 2025, 170, 325, 18, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1282','1234',  2, 2025, 171, 325, 19, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1283','1234',  3, 2025, 172, 325, 20, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1284','1234',  4, 2025, 173, 325, 21, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1285','1234',  5, 2025, 174, 326, 22, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1286','1234',  6, 2025, 175, 326, 23, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1287','1234',  7, 2025, 176, 326, 24, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1288','1234',  8, 2025, 177, 326, 25, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1289','1234',  9, 2025, 178, 326, 26, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1290','1234', 10, 2025, 179, 326, 27, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1291','1234', 11, 2025, 180, 326, 28, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1292','1234', 12, 2025, 181, 326, 29, 2, '2023.01.01');
insert into card (id, serial, pin, month, year, cvv, customerId, productId, cardTypeId, createdat) values(nextval('hibernate_sequence'), '1234-1234-1234-1293','1234', 12, 2025, 182, 326, 30, 2, '2023.01.01');