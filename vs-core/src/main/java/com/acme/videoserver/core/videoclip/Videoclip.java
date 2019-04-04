package com.acme.videoserver.core.videoclip;

import java.time.LocalDateTime;
import java.util.UUID;

public interface Videoclip {

    UUID id();

    String title();

    void title(String text);

    Image thumbnail();

    void thumbnail(Image image);

    LocalDateTime recordingDate();

    void recordingDate(LocalDateTime date);

    VideoclipMetadata metadata();

    void metadata(VideoclipMetadata meta);

}
