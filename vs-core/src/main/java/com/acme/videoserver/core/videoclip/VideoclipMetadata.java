package com.acme.videoserver.core.videoclip;

import java.util.List;

public interface VideoclipMetadata {

    String description();

    void description(String text);

    List<String> participants();

    void participants(List<String> list);

    List<String> tags();

    void tags(List<String> tags);

}
