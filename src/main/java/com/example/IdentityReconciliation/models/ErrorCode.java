package com.example.IdentityReconciliation.models;

public enum ErrorCode {
    INVALID_EVALUATION_DATE("Invalid evaluation date"),
    MEMBER_NOT_FOUND("Member id not found"),
    ORDER_ITEM_NOT_FOUND("Order Item not found"),
    ORDER_NOT_FOUND("Order not found"),
    INVALID_DATE_FORMAT("Invalid date format"),
    FAILED("failed"),
    GIFT_NOT_FOUND("GiftSku not found"),
    CAN_NOT_REDEEM("Can not redeem"),
    LOYALTY_POINTS_ARE_NOT_ENOUGH("loyalty active points are not enough"),
    NO_REDEEMED_GIFTS("no redeemed gifts"),
    GIFT_IS_OUT_OF_STOCK("Gift is out of Stock"),
    UPDATE_LOYALTY_POINTS_FAILED("Update loyalty points failed"),
    LOYALTY_GIFT_STATUS_NOT_FOUND("loyalty gift status not found"),
    LOYALTY_POINTS_NOT_SUFFICIENT("loyalty points insufficient"),
    INVALID("invalid"),
    LOYALTY_GIFT_STATUS_NOT_ACTIVE("loyalty gift status not active");


    String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}