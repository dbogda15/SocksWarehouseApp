package me.dbogda.sockswarehouseapplication.model.enums;

public enum Size {
    SIZE36("36"),
    SIZE37("37"),
    SIZE38("38"),
    SIZE39("39"),
    SIZE40("40"),
    SIZE41("41"),
    SIZE42("42"),
    SIZE43("43"),
    SIZE44("44");
    private final String size;

    Size(String size) {
        this.size=size;
    }

    public String getSize(){
        return size;
    }
}
