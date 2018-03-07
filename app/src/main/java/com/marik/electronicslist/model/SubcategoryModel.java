package com.marik.electronicslist.model;

public class SubcategoryModel  implements BaseInterface {
    private String subName;

    public String getName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
