package com.example.IdentityReconciliation.models;

public enum ErrorKey {
    INVALID_EVALUATION_DATE("Invalid evaluation date"),
    IMAGE("image"),
    IMAGE_UPLOAD("imageUpload"),


    IMAGE_EXTENSION("imageExtension"),

    MEMBER_NOT_FOUND("Member id not found"),
    ORDER_ITEM_NOT_FOUND("Order Item not found"),
    ORDER_NOT_FOUND("Order not found"),
    INVALID_DATE_FORMAT("Invalid date format"),
    GIFT_NOT_FOUND("GiftSku not found"),
    CAN_NOT_REDEEM("can not redeem"),
    LOYALTY_POINTS_ARE_NOT_ENOUGH("loyalty active points are not enough"),
    NO_REDEEMED_GIFTS("no redeemed gifts"),
    GIFT_IS_OUT_OF_STOCK("Gift is out of Stock"),
    UPDATE_LOYALTY_POINTS_FAILED("Update loyalty points failed"),
    LOYALTY_GIFT_STATUS_NOT_FOUND("loyalty gift status not found"),
    LOYALTY_POINTS_NOT_SUFFICIENT("loyalty points insufficient"),
    LOYALTY_GIFT_NAME("loyalty gift name"),
    LOYALTY_GIFT_CODE("loyalty gift code"),
    LOYALTY_GIFT_INFO("loyalty gift info"),
    TERMS_AND_CONDITIONS("terms and conditions"),
    LOYALTY_GIFT_STATUS("loyalty gift status");


    String key;

    ErrorKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
