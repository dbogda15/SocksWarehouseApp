package me.dbogda.sockswarehouseapplication.model.enums;

public enum Color {
    BLACK("black"),
    WHITE("white"),
    RED("red"),
    GREEN("green"),
    GREY("grey");
    private final String color;

    Color(String color) {
        this.color=color;
    }

    public String getColor(){
        return color;
    }
}
