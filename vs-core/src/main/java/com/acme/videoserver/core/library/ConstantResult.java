package com.acme.videoserver.core.library;

import java.util.List;

public class ConstantResult<T> implements Result<T> {

    private final List<T> result;
    private final int total;

    public ConstantResult(List<T> result, int total){
        this.result = result;
        this.total = total;
    }

    @Override
    public List<T> result() {
        return null;
    }

    @Override
    public int total() {
        return 0;
    }
}
