INSERT INTO videoclip (id, title, description, thumbnail, recordingDateTime) VALUES 
('e817cbe5-e2be-44fb-9858-a6cab54ee03e', 'First video', 'Descr1', 
'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/4QBmRXhpZgAATU0AKgAAAAgABAEaAAUAAAABAAAAPgEbAAUAAAABAAAARgEoAAMAAAABAAIAAAExAAIAAAAQAAAATgAAAAAAAABgAAAAAQAAAGAAAAABcGFpbnQubmV0IDQuMS42AP/bAEMADAgJCgkHDAoJCg0MDA4RHRMREBARIxkbFR0qJSwrKSUoKC40QjguMT8yKCg6Tjo/REdKS0otN1FXUUhWQklKR//bAEMBDA0NEQ8RIhMTIkcwKDBHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR//AABEIACEAMgMBIgACEQEDEQH/xAAfAAABBQEBAQEBAQAAAAAAAAAAAQIDBAUGBwgJCgv/xAC1EAACAQMDAgQDBQUEBAAAAX0BAgMABBEFEiExQQYTUWEHInEUMoGRoQgjQrHBFVLR8CQzYnKCCQoWFxgZGiUmJygpKjQ1Njc4OTpDREVGR0hJSlNUVVZXWFlaY2RlZmdoaWpzdHV2d3h5eoOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4eLj5OXm5+jp6vHy8/T19vf4+fr/xAAfAQADAQEBAQEBAQEBAAAAAAAAAQIDBAUGBwgJCgv/xAC1EQACAQIEBAMEBwUEBAABAncAAQIDEQQFITEGEkFRB2FxEyIygQgUQpGhscEJIzNS8BVictEKFiQ04SXxFxgZGiYnKCkqNTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqCg4SFhoeIiYqSk5SVlpeYmZqio6Slpqeoqaqys7S1tre4ubrCw8TFxsfIycrS09TV1tfY2dri4+Tl5ufo6ery8/T19vf4+fr/2gAMAwEAAhEDEQA/APNvKk2bxGdvrt4qPoa6PDPGF24XFUCnnTFDHkA0yTKAyeKm6ACtpNKgxxGSfqary6UUlG5xHGepPJ/CgDNPSmV0Fvp+kthJriTcDg46/wAqq63o39nYlgcyW7HALdR9f8aAMqiiigDctVurklppRHCOCw5z7CpY57S2ZljkDtn7zDNJOI7iPar7EQYESjHPp9Kh+y2r8xqBjqoz/WiwXJG1VpX8uPlj6cAVYt4LeJjPfXHnSYyoAIVf8az5bRsYiCjj1NNW1nETI0vBx74p2C5HPMgui0fTOcip5tQa6tJLZ2AXb8ufbpUP9nr1Lk0v2JF6c0WC5lZNFav2Rfb8qKLCuWD/AMfb/QUn/L9/wEUUUxFg/wAX40i9vpRRQAdqb3FFFAC0UUUCP//Z', 
parsedatetime('2019-01-15 18:01 UTC', 'yyyy-MM-dd hh:mm z', 'en', 'UTC'));

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