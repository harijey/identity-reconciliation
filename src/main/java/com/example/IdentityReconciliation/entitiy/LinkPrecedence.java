package com.example.IdentityReconciliation.entitiy;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum LinkPrecedence {
    PRIMARY("PRIMARY"),SECONDARY("SECONDARY");
    private String value;

    LinkPrecedence(String value){
        this.value=value;
    }
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }
    @JsonCreator
    public static LinkPrecedence fromValue(String value) {
        if (value == null)
            return null;
        LinkPrecedence linkPrecedence = null;
        switch (value.toUpperCase()) {
            case "PRIMARY":
                linkPrecedence = PRIMARY;
                break;
            case "SECONDARY":
                linkPrecedence = SECONDARY;
                break;
        }
        return linkPrecedence;
    }

}
