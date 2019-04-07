INSERT INTO videoclip (id, title, description, thumbnail, recordingDateTime) VALUES 
('e817cbe5-e2be-44fb-9858-a6cab54ee03e', 'First video', 'Descr1', NULL, parsedatetime('2019-01-15 18:01 UTC', 'yyyy-MM-dd hh:mm z', 'en', 'UTC'));

INSERT INTO videoclip (id, title, description, thumbnail, recordingDateTime) VALUES 
('47b293dd-b6f3-4686-9074-a0223ad35374', 'Second video', 'Descr2', null, parsedatetime('2019-02-16 19:02 UTC', 'yyyy-MM-dd hh:mm z', 'en', 'UTC'));

INSERT INTO videoclip (id, title, description, thumbnail, recordingDateTime) VALUES 
('64f43fdf-72ed-42b9-a20c-5247b3fe0a67', 'Third video', 'Descr3', null, parsedatetime('2019-03-17 20:03 UTC', 'yyyy-MM-dd hh:mm z', 'en', 'UTC'));

INSERT INTO participant (videoclip_id, name) VALUES ('e817cbe5-e2be-44fb-9858-a6cab54ee03e', 'p1_1');
INSERT INTO participant (videoclip_id, name) VALUES ('e817cbe5-e2be-44fb-9858-a6cab54ee03e', 'p1_2');
INSERT INTO participant (videoclip_id, name) VALUES ('e817cbe5-e2be-44fb-9858-a6cab54ee03e', 'p1_3');

INSERT INTO tag (videoclip_id, name) VALUES ('e817cbe5-e2be-44fb-9858-a6cab54ee03e', 't1_1');
INSERT INTO tag (videoclip_id, name) VALUES ('e817cbe5-e2be-44fb-9858-a6cab54ee03e', 't1_2');
INSERT INTO tag (videoclip_id, name) VALUES ('e817cbe5-e2be-44fb-9858-a6cab54ee03e', 't1_3');