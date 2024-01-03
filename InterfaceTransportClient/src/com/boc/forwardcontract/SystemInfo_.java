
package com.boc.forwardcontract;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "buy_ccy",
    "sell_ccy",
    "parent_fbo_id",
    "parent_fbo_min_ver",
    "ccy_one_label",
    "ccy_two_label",
    "ccy_one_signed_spot_amount",
    "ccy_two_signed_spot_amount",
    "ccy_one_signed_amount",
    "ccy_two_signed_amount",
    "settlement_pay_ccy",
    "settlement_rec_ccy",
    "settlement_pay_amount",
    "settlement_rec_amount",
    "settlement_pay_date",
    "settlement_rec_date",
    "customer_rate",
    "internal_rate",
    "open_ccy_one_label",
    "open_ccy_two_label",
    "spot_margin_amount_orig",
    "total_margin_amount_orig",
    "ccy_one_int_spot_pos",
    "ccy_two_int_spot_pos",
    "fwd_margin_amount_orig",
    "ccy_one_int_fwd_pos",
    "ccy_two_int_fwd_pos",
    "ccy_one_int_spot_fwd_pos",
    "ccy_two_int_spot_fwd_pos",
    "ccy_one_signed_spot_fwd_amount",
    "ccy_two_signed_spot_fwd_amount"
})
public class SystemInfo_ {

    @JsonProperty("buy_ccy")
    private String buyCcy;
    @JsonProperty("sell_ccy")
    private String sellCcy;
    @JsonProperty("parent_fbo_id")
    private String parentFboId;
    @JsonProperty("parent_fbo_min_ver")
    private Integer parentFboMinVer;
    @JsonProperty("ccy_one_label")
    private String ccyOneLabel;
    @JsonProperty("ccy_two_label")
    private String ccyTwoLabel;
    @JsonProperty("ccy_one_signed_spot_amount")
    private CcyOneSignedSpotAmount ccyOneSignedSpotAmount;
    @JsonProperty("ccy_two_signed_spot_amount")
    private CcyTwoSignedSpotAmount ccyTwoSignedSpotAmount;
    @JsonProperty("ccy_one_signed_amount")
    private CcyOneSignedAmount ccyOneSignedAmount;
    @JsonProperty("ccy_two_signed_amount")
    private CcyTwoSignedAmount ccyTwoSignedAmount;
    @JsonProperty("settlement_pay_ccy")
    private String settlementPayCcy;
    @JsonProperty("settlement_rec_ccy")
    private String settlementRecCcy;
    @JsonProperty("settlement_pay_amount")
    private SettlementPayAmount settlementPayAmount;
    @JsonProperty("settlement_rec_amount")
    private SettlementRecAmount settlementRecAmount;
    @JsonProperty("settlement_pay_date")
    private String settlementPayDate;
    @JsonProperty("settlement_rec_date")
    private String settlementRecDate;
    @JsonProperty("customer_rate")
    private String customerRate;
    @JsonProperty("internal_rate")
    private Double internalRate;
    @JsonProperty("open_ccy_one_label")
    private String openCcyOneLabel;
    @JsonProperty("open_ccy_two_label")
    private String openCcyTwoLabel;
    @JsonProperty("spot_margin_amount_orig")
    private SpotMarginAmountOrig spotMarginAmountOrig;
    @JsonProperty("total_margin_amount_orig")
    private TotalMarginAmountOrig totalMarginAmountOrig;
    @JsonProperty("ccy_one_int_spot_pos")
    private CcyOneIntSpotPos ccyOneIntSpotPos;
    @JsonProperty("ccy_two_int_spot_pos")
    private CcyTwoIntSpotPos ccyTwoIntSpotPos;
    @JsonProperty("fwd_margin_amount_orig")
    private FwdMarginAmountOrig fwdMarginAmountOrig;
    @JsonProperty("ccy_one_int_fwd_pos")
    private CcyOneIntFwdPos ccyOneIntFwdPos;
    @JsonProperty("ccy_two_int_fwd_pos")
    private CcyTwoIntFwdPos ccyTwoIntFwdPos;
    @JsonProperty("ccy_one_int_spot_fwd_pos")
    private CcyOneIntSpotFwdPos ccyOneIntSpotFwdPos;
    @JsonProperty("ccy_two_int_spot_fwd_pos")
    private CcyTwoIntSpotFwdPos ccyTwoIntSpotFwdPos;
    @JsonProperty("ccy_one_signed_spot_fwd_amount")
    private CcyOneSignedSpotFwdAmount ccyOneSignedSpotFwdAmount;
    @JsonProperty("ccy_two_signed_spot_fwd_amount")
    private CcyTwoSignedSpotFwdAmount ccyTwoSignedSpotFwdAmount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("buy_ccy")
    public String getBuyCcy() {
        return buyCcy;
    }

    @JsonProperty("buy_ccy")
    public void setBuyCcy(String buyCcy) {
        this.buyCcy = buyCcy;
    }

    @JsonProperty("sell_ccy")
    public String getSellCcy() {
        return sellCcy;
    }

    @JsonProperty("sell_ccy")
    public void setSellCcy(String sellCcy) {
        this.sellCcy = sellCcy;
    }

    @JsonProperty("parent_fbo_id")
    public String getParentFboId() {
        return parentFboId;
    }

    @JsonProperty("parent_fbo_id")
    public void setParentFboId(String parentFboId) {
        this.parentFboId = parentFboId;
    }

    @JsonProperty("parent_fbo_min_ver")
    public Integer getParentFboMinVer() {
        return parentFboMinVer;
    }

    @JsonProperty("parent_fbo_min_ver")
    public void setParentFboMinVer(Integer parentFboMinVer) {
        this.parentFboMinVer = parentFboMinVer;
    }

    @JsonProperty("ccy_one_label")
    public String getCcyOneLabel() {
        return ccyOneLabel;
    }

    @JsonProperty("ccy_one_label")
    public void setCcyOneLabel(String ccyOneLabel) {
        this.ccyOneLabel = ccyOneLabel;
    }

    @JsonProperty("ccy_two_label")
    public String getCcyTwoLabel() {
        return ccyTwoLabel;
    }

    @JsonProperty("ccy_two_label")
    public void setCcyTwoLabel(String ccyTwoLabel) {
        this.ccyTwoLabel = ccyTwoLabel;
    }

    @JsonProperty("ccy_one_signed_spot_amount")
    public CcyOneSignedSpotAmount getCcyOneSignedSpotAmount() {
        return ccyOneSignedSpotAmount;
    }

    @JsonProperty("ccy_one_signed_spot_amount")
    public void setCcyOneSignedSpotAmount(CcyOneSignedSpotAmount ccyOneSignedSpotAmount) {
        this.ccyOneSignedSpotAmount = ccyOneSignedSpotAmount;
    }

    @JsonProperty("ccy_two_signed_spot_amount")
    public CcyTwoSignedSpotAmount getCcyTwoSignedSpotAmount() {
        return ccyTwoSignedSpotAmount;
    }

    @JsonProperty("ccy_two_signed_spot_amount")
    public void setCcyTwoSignedSpotAmount(CcyTwoSignedSpotAmount ccyTwoSignedSpotAmount) {
        this.ccyTwoSignedSpotAmount = ccyTwoSignedSpotAmount;
    }

    @JsonProperty("ccy_one_signed_amount")
    public CcyOneSignedAmount getCcyOneSignedAmount() {
        return ccyOneSignedAmount;
    }

    @JsonProperty("ccy_one_signed_amount")
    public void setCcyOneSignedAmount(CcyOneSignedAmount ccyOneSignedAmount) {
        this.ccyOneSignedAmount = ccyOneSignedAmount;
    }

    @JsonProperty("ccy_two_signed_amount")
    public CcyTwoSignedAmount getCcyTwoSignedAmount() {
        return ccyTwoSignedAmount;
    }

    @JsonProperty("ccy_two_signed_amount")
    public void setCcyTwoSignedAmount(CcyTwoSignedAmount ccyTwoSignedAmount) {
        this.ccyTwoSignedAmount = ccyTwoSignedAmount;
    }

    @JsonProperty("settlement_pay_ccy")
    public String getSettlementPayCcy() {
        return settlementPayCcy;
    }

    @JsonProperty("settlement_pay_ccy")
    public void setSettlementPayCcy(String settlementPayCcy) {
        this.settlementPayCcy = settlementPayCcy;
    }

    @JsonProperty("settlement_rec_ccy")
    public String getSettlementRecCcy() {
        return settlementRecCcy;
    }

    @JsonProperty("settlement_rec_ccy")
    public void setSettlementRecCcy(String settlementRecCcy) {
        this.settlementRecCcy = settlementRecCcy;
    }

    @JsonProperty("settlement_pay_amount")
    public SettlementPayAmount getSettlementPayAmount() {
        return settlementPayAmount;
    }

    @JsonProperty("settlement_pay_amount")
    public void setSettlementPayAmount(SettlementPayAmount settlementPayAmount) {
        this.settlementPayAmount = settlementPayAmount;
    }

    @JsonProperty("settlement_rec_amount")
    public SettlementRecAmount getSettlementRecAmount() {
        return settlementRecAmount;
    }

    @JsonProperty("settlement_rec_amount")
    public void setSettlementRecAmount(SettlementRecAmount settlementRecAmount) {
        this.settlementRecAmount = settlementRecAmount;
    }

    @JsonProperty("settlement_pay_date")
    public String getSettlementPayDate() {
        return settlementPayDate;
    }

    @JsonProperty("settlement_pay_date")
    public void setSettlementPayDate(String settlementPayDate) {
        this.settlementPayDate = settlementPayDate;
    }

    @JsonProperty("settlement_rec_date")
    public String getSettlementRecDate() {
        return settlementRecDate;
    }

    @JsonProperty("settlement_rec_date")
    public void setSettlementRecDate(String settlementRecDate) {
        this.settlementRecDate = settlementRecDate;
    }

    @JsonProperty("customer_rate")
    public String getCustomerRate() {
        return customerRate;
    }

    @JsonProperty("customer_rate")
    public void setCustomerRate(String customerRate) {
        this.customerRate = customerRate;
    }

    @JsonProperty("internal_rate")
    public Double getInternalRate() {
        return internalRate;
    }

    @JsonProperty("internal_rate")
    public void setInternalRate(Double internalRate) {
        this.internalRate = internalRate;
    }

    @JsonProperty("open_ccy_one_label")
    public String getOpenCcyOneLabel() {
        return openCcyOneLabel;
    }

    @JsonProperty("open_ccy_one_label")
    public void setOpenCcyOneLabel(String openCcyOneLabel) {
        this.openCcyOneLabel = openCcyOneLabel;
    }

    @JsonProperty("open_ccy_two_label")
    public String getOpenCcyTwoLabel() {
        return openCcyTwoLabel;
    }

    @JsonProperty("open_ccy_two_label")
    public void setOpenCcyTwoLabel(String openCcyTwoLabel) {
        this.openCcyTwoLabel = openCcyTwoLabel;
    }

    @JsonProperty("spot_margin_amount_orig")
    public SpotMarginAmountOrig getSpotMarginAmountOrig() {
        return spotMarginAmountOrig;
    }

    @JsonProperty("spot_margin_amount_orig")
    public void setSpotMarginAmountOrig(SpotMarginAmountOrig spotMarginAmountOrig) {
        this.spotMarginAmountOrig = spotMarginAmountOrig;
    }

    @JsonProperty("total_margin_amount_orig")
    public TotalMarginAmountOrig getTotalMarginAmountOrig() {
        return totalMarginAmountOrig;
    }

    @JsonProperty("total_margin_amount_orig")
    public void setTotalMarginAmountOrig(TotalMarginAmountOrig totalMarginAmountOrig) {
        this.totalMarginAmountOrig = totalMarginAmountOrig;
    }

    @JsonProperty("ccy_one_int_spot_pos")
    public CcyOneIntSpotPos getCcyOneIntSpotPos() {
        return ccyOneIntSpotPos;
    }

    @JsonProperty("ccy_one_int_spot_pos")
    public void setCcyOneIntSpotPos(CcyOneIntSpotPos ccyOneIntSpotPos) {
        this.ccyOneIntSpotPos = ccyOneIntSpotPos;
    }

    @JsonProperty("ccy_two_int_spot_pos")
    public CcyTwoIntSpotPos getCcyTwoIntSpotPos() {
        return ccyTwoIntSpotPos;
    }

    @JsonProperty("ccy_two_int_spot_pos")
    public void setCcyTwoIntSpotPos(CcyTwoIntSpotPos ccyTwoIntSpotPos) {
        this.ccyTwoIntSpotPos = ccyTwoIntSpotPos;
    }

    @JsonProperty("fwd_margin_amount_orig")
    public FwdMarginAmountOrig getFwdMarginAmountOrig() {
        return fwdMarginAmountOrig;
    }

    @JsonProperty("fwd_margin_amount_orig")
    public void setFwdMarginAmountOrig(FwdMarginAmountOrig fwdMarginAmountOrig) {
        this.fwdMarginAmountOrig = fwdMarginAmountOrig;
    }

    @JsonProperty("ccy_one_int_fwd_pos")
    public CcyOneIntFwdPos getCcyOneIntFwdPos() {
        return ccyOneIntFwdPos;
    }

    @JsonProperty("ccy_one_int_fwd_pos")
    public void setCcyOneIntFwdPos(CcyOneIntFwdPos ccyOneIntFwdPos) {
        this.ccyOneIntFwdPos = ccyOneIntFwdPos;
    }

    @JsonProperty("ccy_two_int_fwd_pos")
    public CcyTwoIntFwdPos getCcyTwoIntFwdPos() {
        return ccyTwoIntFwdPos;
    }

    @JsonProperty("ccy_two_int_fwd_pos")
    public void setCcyTwoIntFwdPos(CcyTwoIntFwdPos ccyTwoIntFwdPos) {
        this.ccyTwoIntFwdPos = ccyTwoIntFwdPos;
    }

    @JsonProperty("ccy_one_int_spot_fwd_pos")
    public CcyOneIntSpotFwdPos getCcyOneIntSpotFwdPos() {
        return ccyOneIntSpotFwdPos;
    }

    @JsonProperty("ccy_one_int_spot_fwd_pos")
    public void setCcyOneIntSpotFwdPos(CcyOneIntSpotFwdPos ccyOneIntSpotFwdPos) {
        this.ccyOneIntSpotFwdPos = ccyOneIntSpotFwdPos;
    }

    @JsonProperty("ccy_two_int_spot_fwd_pos")
    public CcyTwoIntSpotFwdPos getCcyTwoIntSpotFwdPos() {
        return ccyTwoIntSpotFwdPos;
    }

    @JsonProperty("ccy_two_int_spot_fwd_pos")
    public void setCcyTwoIntSpotFwdPos(CcyTwoIntSpotFwdPos ccyTwoIntSpotFwdPos) {
        this.ccyTwoIntSpotFwdPos = ccyTwoIntSpotFwdPos;
    }

    @JsonProperty("ccy_one_signed_spot_fwd_amount")
    public CcyOneSignedSpotFwdAmount getCcyOneSignedSpotFwdAmount() {
        return ccyOneSignedSpotFwdAmount;
    }

    @JsonProperty("ccy_one_signed_spot_fwd_amount")
    public void setCcyOneSignedSpotFwdAmount(CcyOneSignedSpotFwdAmount ccyOneSignedSpotFwdAmount) {
        this.ccyOneSignedSpotFwdAmount = ccyOneSignedSpotFwdAmount;
    }

    @JsonProperty("ccy_two_signed_spot_fwd_amount")
    public CcyTwoSignedSpotFwdAmount getCcyTwoSignedSpotFwdAmount() {
        return ccyTwoSignedSpotFwdAmount;
    }

    @JsonProperty("ccy_two_signed_spot_fwd_amount")
    public void setCcyTwoSignedSpotFwdAmount(CcyTwoSignedSpotFwdAmount ccyTwoSignedSpotFwdAmount) {
        this.ccyTwoSignedSpotFwdAmount = ccyTwoSignedSpotFwdAmount;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
