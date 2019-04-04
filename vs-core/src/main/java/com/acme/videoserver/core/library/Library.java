package com.acme.videoserver.core.library;

import java.util.List;
import java.util.UUID;

import com.acme.videoserver.core.videoclip.Videoclip;

public interface Library {

    void add(Videoclip clip);

    List<Videoclip> list();

    Videoclip retrieve(UUID videoClipId);

}
