package com.hotel.back.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Bill {
    private Integer billId;
    private Integer reservationId;
    private BigDecimal amount;
    private Date issuedDate;

    public Bill() {
    }

    public Integer getBillId() {
        return this.billId;
    }

    public Integer getReservationId() {
        return this.reservationId;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Date getIssuedDate() {
        return this.issuedDate;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Bill)) return false;
        final Bill other = (Bill) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$billId = this.getBillId();
        final Object other$billId = other.getBillId();
        if (this$billId == null ? other$billId != null : !this$billId.equals(other$billId)) return false;
        final Object this$reservationId = this.getReservationId();
        final Object other$reservationId = other.getReservationId();
        if (this$reservationId == null ? other$reservationId != null : !this$reservationId.equals(other$reservationId))
            return false;
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        final Object this$issuedDate = this.getIssuedDate();
        final Object other$issuedDate = other.getIssuedDate();
        if (this$issuedDate == null ? other$issuedDate != null : !this$issuedDate.equals(other$issuedDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Bill;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $billId = this.getBillId();
        result = result * PRIME + ($billId == null ? 43 : $billId.hashCode());
        final Object $reservationId = this.getReservationId();
        result = result * PRIME + ($reservationId == null ? 43 : $reservationId.hashCode());
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        final Object $issuedDate = this.getIssuedDate();
        result = result * PRIME + ($issuedDate == null ? 43 : $issuedDate.hashCode());
        return result;
    }

    public String toString() {
        return "Bill(billId=" + this.getBillId() + ", reservationId=" + this.getReservationId() + ", amount=" + this.getAmount() + ", issuedDate=" + this.getIssuedDate() + ")";
    }
}
