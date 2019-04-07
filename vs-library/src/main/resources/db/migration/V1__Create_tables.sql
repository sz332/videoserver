CREATE TABLE videoclip (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(2000),
    thumbnail VARCHAR(MAX),
    recordingDateTime TIMESTAMP WITH TIME ZONE
);

CREATE TABLE participant (
    videoclip_id VARCHAR(36) NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (videoclip_id) REFERENCES videoclip(id) ON DELETE CASCADE,
    PRIMARY KEY (videoclip_id, name)
);

CREATE TABLE tag (
    videoclip_id VARCHAR(36) NOT NULL,
    name VARCHAR(255) NOT NULL,
    FOREIGN KEY (videoclip_id) REFERENCES videoclip(id) ON DELETE CASCADE,
    PRIMARY KEY (videoclip_id, name)
);