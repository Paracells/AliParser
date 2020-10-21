package ru.paracells.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("productId")
    @Expose
    private Long productId;
    @SerializedName("sellerId")
    @Expose
    private Long sellerId;
    @SerializedName("oriMinPrice")
    @Expose
    private String oriMinPrice;
    @SerializedName("oriMaxPrice")
    @Expose
    private String oriMaxPrice;
    @SerializedName("promotionId")
    @Expose
    private Long promotionId;
    @SerializedName("startTime")
    @Expose
    private Long startTime;
    @SerializedName("endTime")
    @Expose
    private Long endTime;
    @SerializedName("phase")
    @Expose
    private Long phase;
    @SerializedName("productTitle")
    @Expose
    private String productTitle;
    @SerializedName("minPrice")
    @Expose
    private String minPrice;
    @SerializedName("maxPrice")
    @Expose
    private String maxPrice;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("totalStock")
    @Expose
    private String totalStock;
    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("orders")
    @Expose
    private String orders;
    @SerializedName("soldout")
    @Expose
    private Boolean soldout;
    @SerializedName("productImage")
    @Expose
    private String productImage;
    @SerializedName("productDetailUrl")
    @Expose
    private String productDetailUrl;
    @SerializedName("trace")
    @Expose
    private String trace;
    @SerializedName("totalTranpro3")
    @Expose
    private String totalTranpro3;
    @SerializedName("productPositiveRate")
    @Expose
    private String productPositiveRate;
    @SerializedName("productAverageStar")
    @Expose
    private String productAverageStar;
    @SerializedName("itemEvalTotalNum")
    @Expose
    private Long itemEvalTotalNum;
    @SerializedName("gmtCreate")
    @Expose
    private Long gmtCreate;

    @Override
    public String toString() {

        return
                productId + ","+
                        sellerId + "," +
                        oriMinPrice + "," +
                        oriMaxPrice + "," +
                        promotionId + "," +
                        startTime + "," +
                        endTime + "," +
                        phase + "," +
                        productTitle + "," +
                        minPrice + "," +
                        maxPrice + "," +
                        discount + "," +
                        totalStock + "," +
                        stock + "," +
                        orders + "," +
                        soldout + "," +
                        productImage + "," +
                        productDetailUrl + "," +
                        trace + "," +
                        totalTranpro3 + "," +
                        productPositiveRate + "," +
                        productAverageStar + "," +
                        itemEvalTotalNum + "," +
                        gmtCreate + ",";

    }
}
