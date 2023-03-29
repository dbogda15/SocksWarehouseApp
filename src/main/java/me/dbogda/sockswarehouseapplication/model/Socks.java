package me.dbogda.sockswarehouseapplication.model;

import lombok.Data;
import me.dbogda.sockswarehouseapplication.model.enums.Color;
import me.dbogda.sockswarehouseapplication.model.enums.Size;

import java.util.Objects;

@Data
public class Socks {
    private final Color color;
    private final Size size;
    private final int cottonPart;

    public Socks(Color color, Size size, int cottonPart) {
        this.color = color;
        this.size = size;

        if (cottonPart >= 0 && cottonPart <= 100) {
            this.cottonPart = cottonPart;
        } else
            throw new RuntimeException();
    }

    public Color getColor() {
        return color;
    }

    public Size getSize() {
        return size;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return cottonPart == socks.cottonPart && color == socks.color && size == socks.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size, cottonPart);
    }

    @Override
    public String toString() {
        return "Socks color: " + color.getColor() + ", size = " + size.getSize() + ", cotton part = " + cottonPart + "%";
    }
}
