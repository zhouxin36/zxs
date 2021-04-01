package com.zx.jdkanalysis.performance.resize;

final public class StateAndId {
    private String id, state;

    public StateAndId(String id, String state) {
        this.id = id; this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}