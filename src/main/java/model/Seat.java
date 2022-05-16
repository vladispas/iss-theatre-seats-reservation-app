package model;

import java.io.Serializable;

public class Seat implements Identifiable<Tuple>, Serializable {

    private Integer posX;
    private Integer posY;
    private Double price;

    public Seat() {
        this.posX = 0;
        this.posY = 0;
        this.price = 0.0;
    }

    public Seat(Double price) {
        this.price = price;
    }

    @Override
    public Tuple getID() {
        return new Tuple(posX, posY);
    }

    @Override
    public void setID(Tuple ID) {
        this.posX = ID.getPosX();
        this.posY = ID.getPosY();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", price=" + price +
                '}';
    }
}
