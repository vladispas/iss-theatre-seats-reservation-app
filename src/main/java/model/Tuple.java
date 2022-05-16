package model;

import java.util.Objects;

public class Tuple {

    private Integer posX;
    private Integer posY;

    public Tuple(Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "posX=" + posX +
                ", posY=" + posY +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return Objects.equals(posX, tuple.posX) && Objects.equals(posY, tuple.posY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }
}
