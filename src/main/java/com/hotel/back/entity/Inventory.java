package com.hotel.back.entity;

import java.math.BigDecimal;

public class Inventory {
    private Integer itemId;
    private String itemName;
    private Integer quantity;
    private BigDecimal unitPrice;

    public Inventory() {
    }

    public Integer getItemId() {
        return this.itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Inventory)) return false;
        final Inventory other = (Inventory) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$itemId = this.getItemId();
        final Object other$itemId = other.getItemId();
        if (this$itemId == null ? other$itemId != null : !this$itemId.equals(other$itemId)) return false;
        final Object this$itemName = this.getItemName();
        final Object other$itemName = other.getItemName();
        if (this$itemName == null ? other$itemName != null : !this$itemName.equals(other$itemName)) return false;
        final Object this$quantity = this.getQuantity();
        final Object other$quantity = other.getQuantity();
        if (this$quantity == null ? other$quantity != null : !this$quantity.equals(other$quantity)) return false;
        final Object this$unitPrice = this.getUnitPrice();
        final Object other$unitPrice = other.getUnitPrice();
        if (this$unitPrice == null ? other$unitPrice != null : !this$unitPrice.equals(other$unitPrice)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Inventory;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $itemId = this.getItemId();
        result = result * PRIME + ($itemId == null ? 43 : $itemId.hashCode());
        final Object $itemName = this.getItemName();
        result = result * PRIME + ($itemName == null ? 43 : $itemName.hashCode());
        final Object $quantity = this.getQuantity();
        result = result * PRIME + ($quantity == null ? 43 : $quantity.hashCode());
        final Object $unitPrice = this.getUnitPrice();
        result = result * PRIME + ($unitPrice == null ? 43 : $unitPrice.hashCode());
        return result;
    }

    public String toString() {
        return "Inventory(itemId=" + this.getItemId() + ", itemName=" + this.getItemName() + ", quantity=" + this.getQuantity() + ", unitPrice=" + this.getUnitPrice() + ")";
    }
}
