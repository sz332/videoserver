package com.acme.videoserver.core.library;

import java.util.List;

public interface Result<T> {

    List<T> result();

    int  total();

}
