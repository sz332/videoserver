package com.acme.videoserver.core.library;

public class ConstantQuery implements Query {

    private final String filter;
    private final Integer limit;
    private final Integer offset;

    public ConstantQuery(String filter, Integer limit, Integer offset){
        this.filter = filter;
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public String filter() {
        return filter;
    }

    @Override
    public Integer limit() {
        return limit;
    }

    @Override
    public Integer offset() {
        return offset;
    }
}
