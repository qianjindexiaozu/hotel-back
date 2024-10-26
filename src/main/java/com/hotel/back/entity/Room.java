package com.hotel.back.entity;

import com.hotel.back.constant.enums.RoomStatus;
import com.hotel.back.constant.enums.RoomType;

import java.math.BigDecimal;

public class Room {
    private Integer roomId;
    private String roomNumber;
    private RoomType roomType;
    private RoomStatus roomStatus;
    private BigDecimal roomPrice;

    public Room() {
    }

    public Integer getRoomId() {
        return this.roomId;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public RoomStatus getRoomStatus() {
        return this.roomStatus;
    }

    public BigDecimal getRoomPrice() {
        return this.roomPrice;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public void setRoomPrice(BigDecimal roomPrice) {
        this.roomPrice = roomPrice;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Room)) return false;
        final Room other = (Room) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$roomId = this.getRoomId();
        final Object other$roomId = other.getRoomId();
        if (this$roomId == null ? other$roomId != null : !this$roomId.equals(other$roomId)) return false;
        final Object this$roomNumber = this.getRoomNumber();
        final Object other$roomNumber = other.getRoomNumber();
        if (this$roomNumber == null ? other$roomNumber != null : !this$roomNumber.equals(other$roomNumber))
            return false;
        final Object this$roomType = this.getRoomType();
        final Object other$roomType = other.getRoomType();
        if (this$roomType == null ? other$roomType != null : !this$roomType.equals(other$roomType)) return false;
        final Object this$roomStatus = this.getRoomStatus();
        final Object other$roomStatus = other.getRoomStatus();
        if (this$roomStatus == null ? other$roomStatus != null : !this$roomStatus.equals(other$roomStatus))
            return false;
        final Object this$roomPrice = this.getRoomPrice();
        final Object other$roomPrice = other.getRoomPrice();
        if (this$roomPrice == null ? other$roomPrice != null : !this$roomPrice.equals(other$roomPrice)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Room;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $roomId = this.getRoomId();
        result = result * PRIME + ($roomId == null ? 43 : $roomId.hashCode());
        final Object $roomNumber = this.getRoomNumber();
        result = result * PRIME + ($roomNumber == null ? 43 : $roomNumber.hashCode());
        final Object $roomType = this.getRoomType();
        result = result * PRIME + ($roomType == null ? 43 : $roomType.hashCode());
        final Object $roomStatus = this.getRoomStatus();
        result = result * PRIME + ($roomStatus == null ? 43 : $roomStatus.hashCode());
        final Object $roomPrice = this.getRoomPrice();
        result = result * PRIME + ($roomPrice == null ? 43 : $roomPrice.hashCode());
        return result;
    }

    public String toString() {
        return "Room(roomId=" + this.getRoomId() + ", roomNumber=" + this.getRoomNumber() + ", roomType=" + this.getRoomType() + ", roomStatus=" + this.getRoomStatus() + ", roomPrice=" + this.getRoomPrice() + ")";
    }
}
