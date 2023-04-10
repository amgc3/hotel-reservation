package model;

public class Room implements  IRoom {

    private final String roomNumber;
    private Double price;
    private RoomType roomType;

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    public void setRoomPrice(Double price) {
        this.price = price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return this.getRoomPrice() == 0.0;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (!getRoomNumber().equals(room.getRoomNumber())) return false;
        if (!getRoomPrice().equals(room.getRoomPrice())) return false;
        return getRoomType() == room.getRoomType();
    }

    @Override
    public int hashCode() {
        int result = getRoomNumber().hashCode();
        result = 31 * result + getRoomPrice().hashCode();
        result = 31 * result + getRoomType().hashCode();
        return result;
    }
}
