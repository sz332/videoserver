package com.acme.videoserver.core.library;

public interface Query {

    String filter();

    Integer limit();

    Integer offset();
}
