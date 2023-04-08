package model;

import java.util.Objects;

public class Room implements  IRoom {

    private String roomNumber;
    private Double price;
    private RoomType roomType;
    private boolean isFree; // not sure I should have this

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.isFree = true;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return isFree;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType +
                ", isFree=" + isFree() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return isFree() == room.isFree() && getRoomNumber().equals(room.getRoomNumber()) && price.equals(room.price) && getRoomType() == room.getRoomType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber(), price, getRoomType(), isFree());
    }
}
