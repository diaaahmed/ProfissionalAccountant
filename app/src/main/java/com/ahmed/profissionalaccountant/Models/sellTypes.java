package com.ahmed.profissionalaccountant.Models;

public class sellTypes {

    private Integer sellType;
    private String sellTypeName;

    public sellTypes(Integer sellType, String sellTypeName) {
        this.sellType = sellType;
        this.sellTypeName = sellTypeName;
    }

    @Override
    public String toString() {
        return  sellTypeName
                ;
    }

    public Integer getSellType() {
        return sellType;
    }

    public void setSellType(Integer sellType) {
        this.sellType = sellType;
    }

    public String getSellTypeName() {
        return sellTypeName;
    }

    public void setSellTypeName(String sellTypeName) {
        this.sellTypeName = sellTypeName;
    }
}
