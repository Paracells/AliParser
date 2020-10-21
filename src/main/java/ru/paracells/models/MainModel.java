package ru.paracells.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainModel {

    @SerializedName("contextId")
    @Expose
    private String contextId;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("code")
    @Expose
    private Long code;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("finished")
    @Expose
    private Boolean finished;
    @SerializedName("page")
    @Expose
    private Long page;
    @SerializedName("pageSize")
    @Expose
    private Long pageSize;
    @SerializedName("postback")
    @Expose
    private String postback;
    @SerializedName("pin")
    @Expose
    private String pin;

    public List<Result> getResults() {
        return results;
    }

    @Override
    public String toString() {

        return "MainModel{" +
                "contextId='" + contextId + '\'' +
                ", success=" + success +
                ", code=" + code +
                ", results=" + results +
                ", finished=" + finished +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", postback='" + postback + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }
}



