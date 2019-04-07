package com.acme.videoserver.core.library;

import java.util.Collection;

public interface Library {

    void add(Videoclip clip) throws LibraryAccessException;

    Collection<Videoclip> clips() throws LibraryAccessException;

    Videoclip clip(String clipId) throws LibraryAccessException;

}
