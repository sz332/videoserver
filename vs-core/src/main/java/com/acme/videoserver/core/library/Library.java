package com.acme.videoserver.core.library;

import java.util.List;

public interface Library {

    void add(Videoclip clip) throws LibraryAccessException;

    List<Videoclip> clips() throws LibraryAccessException;

    Result<Videoclip> clips(Query query) throws LibraryAccessException;

    Videoclip clip(String clipId) throws LibraryAccessException;

}
