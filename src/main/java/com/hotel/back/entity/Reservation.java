package com.hotel.back.entity;

import com.hotel.back.constant.enums.ReservationStatus;
import com.hotel.back.constant.enums.RoomType;

import java.util.Date;

public class Reservation {
    private Integer reservationId;
    private Integer userId;
    private RoomType roomType;
    private Date checkInDate;
    private Date checkOutDate;
    private ReservationStatus reservationStatus;

    public Reservation() {
    }

    public Integer getReservationId() {
        return this.reservationId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public Date getCheckOutDate() {
        return this.checkOutDate;
    }

    public ReservationStatus getReservationStatus() {
        return this.reservationStatus;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Reservation)) return false;
        final Reservation other = (Reservation) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$reservationId = this.getReservationId();
        final Object other$reservationId = other.getReservationId();
        if (this$reservationId == null ? other$reservationId != null : !this$reservationId.equals(other$reservationId))
            return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final Object this$roomType = this.getRoomType();
        final Object other$roomType = other.getRoomType();
        if (this$roomType == null ? other$roomType != null : !this$roomType.equals(other$roomType)) return false;
        final Object this$checkInDate = this.getCheckInDate();
        final Object other$checkInDate = other.getCheckInDate();
        if (this$checkInDate == null ? other$checkInDate != null : !this$checkInDate.equals(other$checkInDate))
            return false;
        final Object this$checkOutDate = this.getCheckOutDate();
        final Object other$checkOutDate = other.getCheckOutDate();
        if (this$checkOutDate == null ? other$checkOutDate != null : !this$checkOutDate.equals(other$checkOutDate))
            return false;
        final Object this$reservationStatus = this.getReservationStatus();
        final Object other$reservationStatus = other.getReservationStatus();
        if (this$reservationStatus == null ? other$reservationStatus != null : !this$reservationStatus.equals(other$reservationStatus))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Reservation;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $reservationId = this.getReservationId();
        result = result * PRIME + ($reservationId == null ? 43 : $reservationId.hashCode());
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $roomType = this.getRoomType();
        result = result * PRIME + ($roomType == null ? 43 : $roomType.hashCode());
        final Object $checkInDate = this.getCheckInDate();
        result = result * PRIME + ($checkInDate == null ? 43 : $checkInDate.hashCode());
        final Object $checkOutDate = this.getCheckOutDate();
        result = result * PRIME + ($checkOutDate == null ? 43 : $checkOutDate.hashCode());
        final Object $reservationStatus = this.getReservationStatus();
        result = result * PRIME + ($reservationStatus == null ? 43 : $reservationStatus.hashCode());
        return result;
    }

    public String toString() {
        return "Reservation(reservationId=" + this.getReservationId() + ", userId=" + this.getUserId() + ", roomType=" + this.getRoomType() + ", checkInDate=" + this.getCheckInDate() + ", checkOutDate=" + this.getCheckOutDate() + ", reservationStatus=" + this.getReservationStatus() + ")";
    }
}
