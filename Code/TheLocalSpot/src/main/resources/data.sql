INSERT INTO "GENUSER" (ID, VERSION, FIRST_NAME, LAST_NAME, EMAIL, ROLE, ADDRESS, ZIP_CODE, PHONE_NUMBER, PREFERENCES) VALUES
(1, 1, 'John', 'A', 'test1@gmail.com', 'General User', '101 Cherry Lane', '12345', '+(111)-111-1111', 'Music,Gaming,Sports'),
(2, 1, 'John', 'B', 'test2@gmail.com', 'General User', '102 Cherry Lane', '12345', '+(222)-222-2222', 'Sports'),
(3, 1, 'John', 'C', 'test3gmail.com', 'General User', '103 Cherry Lane', '12345', '+(333)-333-3333', 'Recreational,Theatre'),
(4, 1, 'John', 'D', 'test4@gmail.com', 'General User', '104 Cherry Lane', '12345', '+(444)-444-4444', 'Music,Comedy,Theatre,Gaming,Sports,Recreational');
INSERT INTO "COORDINATOR" (ID, VERSION, FIRST_NAME, LAST_NAME, GENRE, EMAIL, ROLE, ADDRESS, ZIP_CODE, PHONE_NUMBER) VALUES
(1, 1, 'Jane', 'A', 'Music', 'test5@gmail.com', 'Coordinator', '105 Cherry Lane', 12345, '1233211231'),
(2, 1, 'Jane', 'B', 'Sports', 'test6@gmail.com', 'Coordinator', '106 Cherry Lane', 12345, '1233211232'),
(3, 1, 'Jane', 'C', 'Comedy', 'test7@gmail.com', 'Coordinator', '107 Cherry Lane', 12345, '1233211233');

INSERT INTO "EVENTS" (ID, VERSION, COORD_USER_ID, HOST_USER_ID, PLACE_ID, EVENT_STATUS, EVENT_TYPE, EVENT_NAME, EVENT_TIME, DATE_START, DATE_END, EVENT_CAPACITY, EVENT_INFO, MAX_TICKETS, AVAILABLE_TICKETS, TICKET_PRICE) VALUES
(1, 1, 1, 1, 1, 'TRUE', 'Music', 'eventA', '12:00', '2023-12-30', '2023-12-31', '100', 'This is the info for event A', '100', '100', '5.00'),
(2, 1, 1, 1, 1, 'TRUE', 'Recreational', 'eventB', '13:00', '2023-12-30', '2023-12-31', '100', 'This is the info for event B', '100', '100', '5.00'),
(3, 1, 1, 1, 1, 'TRUE', 'Sports', 'eventC', '14:00', '2023-12-30', '2023-12-31', '100', 'This is the info for event C', '100', '100', '5.00');