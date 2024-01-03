
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
    "fbo_type",
    "fbo_number",
    "fbo_version",
    "fbo_id",
    "deal_date",
    "broker_id",
    "broker_mnemonic",
    "broker_fee",
    "trading_book",
    "comments_field",
    "deal_source",
    "memo_field_1",
    "memo_field_2",
    "memo_field_3",
    "memo_field_4",
    "memo_field_5",
    "memo_field_6",
    "memo_field_7",
    "memo_field_8",
    "memo_field_9",
    "memo_field_10",
    "memo_field_11",
    "memo_field_12",
    "memo_field_13",
    "memo_field_14",
    "memo_field_15",
    "memo_field_16",
    "memo_field_17",
    "memo_field_18",
    "memo_field_19",
    "memo_field_20",
    "lbs_field_1",
    "lbs_field_2",
    "lbs_field_3",
    "lbs_field_4",
    "lbs_field_5",
    "lbs_field_6",
    "lbs_field_7",
    "lbs_field_8",
    "lbs_field_9",
    "lbs_field_10",
    "lbs_field_11",
    "lbs_field_12",
    "lbs_field_13",
    "lbs_field_14",
    "lbs_field_15",
    "lbs_field_16",
    "lbs_field_17",
    "lbs_field_18",
    "lbs_field_19",
    "lbs_field_20",
    "block_trade",
    "rd2000_requires_verification",
    "rd2000_is_verified",
    "last_user_update_id",
    "last_user_update_timestamp",
    "cost_of_carry_ref_rate_code",
    "last_user_update_version",
    "counterparty_string",
    "cover_cpty_id",
    "dept_name",
    "entity_name",
    "subtype",
    "confirm_method",
    "ccil_status",
    "host_deal_data_fbo_id",
    "deal_identifier",
    "margin_type",
    "product_type",
    "query_field",
    "confirmation_state",
    "verification_state",
    "accounting_code",
    "confirmation_event",
    "brokerage_method",
    "portfolio_id_num",
    "sales_or_interbank",
    "spot_risk_ccy",
    "traded_ccy",
    "fx_fwd_pos_risk_method",
    "convert_at_spot",
    "dco_type",
    "fx_pl_ccy_method",
    "margin_owner",
    "margin_value",
    "primary_margin_value",
    "secondary_margin_value",
    "spread_ccy",
    "ccy1_calendar_set",
    "ccy2_calendar_set",
    "fx_sales_trade_description",
    "is_non_deliverable",
    "currency_pair",
    "start_date",
    "end_date",
    "lien_required",
    "traded_amount",
    "margin_sell_down_method",
    "fxp_flow_reference",
    "spot_margin_amount",
    "total_margin_amount",
    "fwd_margin_amount",
    "confirmation_event_far",
    "spot_posn_subtype",
    "fwd_posn_subtype",
    "posn_department",
    "block_trade_type",
    "block_deal_num",
    "is_in_out_swap",
    "grace_period",
    "is_back_to_back",
    "system_info",
    "main_leg",
    "cpty"
})
public class FxPlus {

    @JsonProperty("fbo_type")
    private String fboType;
    @JsonProperty("fbo_number")
    private Integer fboNumber;
    @JsonProperty("fbo_version")
    private Integer fboVersion;
    @JsonProperty("fbo_id")
    private String fboId;
    @JsonProperty("deal_date")
    private String dealDate;
    @JsonProperty("broker_id")
    private String brokerId;
    @JsonProperty("broker_mnemonic")
    private String brokerMnemonic;
    @JsonProperty("broker_fee")
    private Double brokerFee;
    @JsonProperty("trading_book")
    private String tradingBook;
    @JsonProperty("comments_field")
    private String commentsField;
    @JsonProperty("deal_source")
    private String dealSource;
    @JsonProperty("memo_field_1")
    private String memoField1;
    @JsonProperty("memo_field_2")
    private String memoField2;
    @JsonProperty("memo_field_3")
    private String memoField3;
    @JsonProperty("memo_field_4")
    private String memoField4;
    @JsonProperty("memo_field_5")
    private String memoField5;
    @JsonProperty("memo_field_6")
    private String memoField6;
    @JsonProperty("memo_field_7")
    private String memoField7;
    @JsonProperty("memo_field_8")
    private String memoField8;
    @JsonProperty("memo_field_9")
    private String memoField9;
    @JsonProperty("memo_field_10")
    private String memoField10;
    @JsonProperty("memo_field_11")
    private String memoField11;
    @JsonProperty("memo_field_12")
    private String memoField12;
    @JsonProperty("memo_field_13")
    private String memoField13;
    @JsonProperty("memo_field_14")
    private String memoField14;
    @JsonProperty("memo_field_15")
    private String memoField15;
    @JsonProperty("memo_field_16")
    private String memoField16;
    @JsonProperty("memo_field_17")
    private String memoField17;
    @JsonProperty("memo_field_18")
    private String memoField18;
    @JsonProperty("memo_field_19")
    private String memoField19;
    @JsonProperty("memo_field_20")
    private String memoField20;
    @JsonProperty("lbs_field_1")
    private String lbsField1;
    @JsonProperty("lbs_field_2")
    private String lbsField2;
    @JsonProperty("lbs_field_3")
    private String lbsField3;
    @JsonProperty("lbs_field_4")
    private String lbsField4;
    @JsonProperty("lbs_field_5")
    private String lbsField5;
    @JsonProperty("lbs_field_6")
    private String lbsField6;
    @JsonProperty("lbs_field_7")
    private String lbsField7;
    @JsonProperty("lbs_field_8")
    private String lbsField8;
    @JsonProperty("lbs_field_9")
    private String lbsField9;
    @JsonProperty("lbs_field_10")
    private String lbsField10;
    @JsonProperty("lbs_field_11")
    private String lbsField11;
    @JsonProperty("lbs_field_12")
    private String lbsField12;
    @JsonProperty("lbs_field_13")
    private String lbsField13;
    @JsonProperty("lbs_field_14")
    private String lbsField14;
    @JsonProperty("lbs_field_15")
    private String lbsField15;
    @JsonProperty("lbs_field_16")
    private String lbsField16;
    @JsonProperty("lbs_field_17")
    private String lbsField17;
    @JsonProperty("lbs_field_18")
    private String lbsField18;
    @JsonProperty("lbs_field_19")
    private String lbsField19;
    @JsonProperty("lbs_field_20")
    private String lbsField20;
    @JsonProperty("block_trade")
    private String blockTrade;
    @JsonProperty("rd2000_requires_verification")
    private Boolean rd2000RequiresVerification;
    @JsonProperty("rd2000_is_verified")
    private Boolean rd2000IsVerified;
    @JsonProperty("last_user_update_id")
    private String lastUserUpdateId;
    @JsonProperty("last_user_update_timestamp")
    private String lastUserUpdateTimestamp;
    @JsonProperty("cost_of_carry_ref_rate_code")
    private String costOfCarryRefRateCode;
    @JsonProperty("last_user_update_version")
    private Integer lastUserUpdateVersion;
    @JsonProperty("counterparty_string")
    private String counterpartyString;
    @JsonProperty("cover_cpty_id")
    private String coverCptyId;
    @JsonProperty("dept_name")
    private String deptName;
    @JsonProperty("entity_name")
    private String entityName;
    @JsonProperty("subtype")
    private Subtype subtype;
    @JsonProperty("confirm_method")
    private String confirmMethod;
    @JsonProperty("ccil_status")
    private String ccilStatus;
    @JsonProperty("host_deal_data_fbo_id")
    private String hostDealDataFboId;
    @JsonProperty("deal_identifier")
    private String dealIdentifier;
    @JsonProperty("margin_type")
    private String marginType;
    @JsonProperty("product_type")
    private String productType;
    @JsonProperty("query_field")
    private String queryField;
    @JsonProperty("confirmation_state")
    private String confirmationState;
    @JsonProperty("verification_state")
    private String verificationState;
    @JsonProperty("accounting_code")
    private String accountingCode;
    @JsonProperty("confirmation_event")
    private String confirmationEvent;
    @JsonProperty("brokerage_method")
    private String brokerageMethod;
    @JsonProperty("portfolio_id_num")
    private Integer portfolioIdNum;
    @JsonProperty("sales_or_interbank")
    private String salesOrInterbank;
    @JsonProperty("spot_risk_ccy")
    private String spotRiskCcy;
    @JsonProperty("traded_ccy")
    private String tradedCcy;
    @JsonProperty("fx_fwd_pos_risk_method")
    private String fxFwdPosRiskMethod;
    @JsonProperty("convert_at_spot")
    private Boolean convertAtSpot;
    @JsonProperty("dco_type")
    private String dcoType;
    @JsonProperty("fx_pl_ccy_method")
    private String fxPlCcyMethod;
    @JsonProperty("margin_owner")
    private MarginOwner marginOwner;
    @JsonProperty("margin_value")
    private Double marginValue;
    @JsonProperty("primary_margin_value")
    private Double primaryMarginValue;
    @JsonProperty("secondary_margin_value")
    private Double secondaryMarginValue;
    @JsonProperty("spread_ccy")
    private String spreadCcy;
    @JsonProperty("ccy1_calendar_set")
    private String ccy1CalendarSet;
    @JsonProperty("ccy2_calendar_set")
    private String ccy2CalendarSet;
    @JsonProperty("fx_sales_trade_description")
    private String fxSalesTradeDescription;
    @JsonProperty("is_non_deliverable")
    private Boolean isNonDeliverable;
    @JsonProperty("currency_pair")
    private String currencyPair;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("lien_required")
    private String lienRequired;
    @JsonProperty("traded_amount")
    private Double tradedAmount;
    @JsonProperty("margin_sell_down_method")
    private String marginSellDownMethod;
    @JsonProperty("fxp_flow_reference")
    private Integer fxpFlowReference;
    @JsonProperty("spot_margin_amount")
    private SpotMarginAmount spotMarginAmount;
    @JsonProperty("total_margin_amount")
    private TotalMarginAmount totalMarginAmount;
    @JsonProperty("fwd_margin_amount")
    private FwdMarginAmount fwdMarginAmount;
    @JsonProperty("confirmation_event_far")
    private String confirmationEventFar;
    @JsonProperty("spot_posn_subtype")
    private SpotPosnSubtype spotPosnSubtype;
    @JsonProperty("fwd_posn_subtype")
    private FwdPosnSubtype fwdPosnSubtype;
    @JsonProperty("posn_department")
    private PosnDepartment posnDepartment;
    @JsonProperty("block_trade_type")
    private String blockTradeType;
    @JsonProperty("block_deal_num")
    private Integer blockDealNum;
    @JsonProperty("is_in_out_swap")
    private Boolean isInOutSwap;
    @JsonProperty("grace_period")
    private Integer gracePeriod;
    @JsonProperty("is_back_to_back")
    private Boolean isBackToBack;
    @JsonProperty("system_info")
    private SystemInfo systemInfo;
    @JsonProperty("main_leg")
    private MainLeg mainLeg;
    @JsonProperty("cpty")
    private Cpty_ cpty;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fbo_type")
    public String getFboType() {
        return fboType;
    }

    @JsonProperty("fbo_type")
    public void setFboType(String fboType) {
        this.fboType = fboType;
    }

    @JsonProperty("fbo_number")
    public Integer getFboNumber() {
        return fboNumber;
    }

    @JsonProperty("fbo_number")
    public void setFboNumber(Integer fboNumber) {
        this.fboNumber = fboNumber;
    }

    @JsonProperty("fbo_version")
    public Integer getFboVersion() {
        return fboVersion;
    }

    @JsonProperty("fbo_version")
    public void setFboVersion(Integer fboVersion) {
        this.fboVersion = fboVersion;
    }

    @JsonProperty("fbo_id")
    public String getFboId() {
        return fboId;
    }

    @JsonProperty("fbo_id")
    public void setFboId(String fboId) {
        this.fboId = fboId;
    }

    @JsonProperty("deal_date")
    public String getDealDate() {
        return dealDate;
    }

    @JsonProperty("deal_date")
    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    @JsonProperty("broker_id")
    public String getBrokerId() {
        return brokerId;
    }

    @JsonProperty("broker_id")
    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    @JsonProperty("broker_mnemonic")
    public String getBrokerMnemonic() {
        return brokerMnemonic;
    }

    @JsonProperty("broker_mnemonic")
    public void setBrokerMnemonic(String brokerMnemonic) {
        this.brokerMnemonic = brokerMnemonic;
    }

    @JsonProperty("broker_fee")
    public Double getBrokerFee() {
        return brokerFee;
    }

    @JsonProperty("broker_fee")
    public void setBrokerFee(Double brokerFee) {
        this.brokerFee = brokerFee;
    }

    @JsonProperty("trading_book")
    public String getTradingBook() {
        return tradingBook;
    }

    @JsonProperty("trading_book")
    public void setTradingBook(String tradingBook) {
        this.tradingBook = tradingBook;
    }

    @JsonProperty("comments_field")
    public String getCommentsField() {
        return commentsField;
    }

    @JsonProperty("comments_field")
    public void setCommentsField(String commentsField) {
        this.commentsField = commentsField;
    }

    @JsonProperty("deal_source")
    public String getDealSource() {
        return dealSource;
    }

    @JsonProperty("deal_source")
    public void setDealSource(String dealSource) {
        this.dealSource = dealSource;
    }

    @JsonProperty("memo_field_1")
    public String getMemoField1() {
        return memoField1;
    }

    @JsonProperty("memo_field_1")
    public void setMemoField1(String memoField1) {
        this.memoField1 = memoField1;
    }

    @JsonProperty("memo_field_2")
    public String getMemoField2() {
        return memoField2;
    }

    @JsonProperty("memo_field_2")
    public void setMemoField2(String memoField2) {
        this.memoField2 = memoField2;
    }

    @JsonProperty("memo_field_3")
    public String getMemoField3() {
        return memoField3;
    }

    @JsonProperty("memo_field_3")
    public void setMemoField3(String memoField3) {
        this.memoField3 = memoField3;
    }

    @JsonProperty("memo_field_4")
    public String getMemoField4() {
        return memoField4;
    }

    @JsonProperty("memo_field_4")
    public void setMemoField4(String memoField4) {
        this.memoField4 = memoField4;
    }

    @JsonProperty("memo_field_5")
    public String getMemoField5() {
        return memoField5;
    }

    @JsonProperty("memo_field_5")
    public void setMemoField5(String memoField5) {
        this.memoField5 = memoField5;
    }

    @JsonProperty("memo_field_6")
    public String getMemoField6() {
        return memoField6;
    }

    @JsonProperty("memo_field_6")
    public void setMemoField6(String memoField6) {
        this.memoField6 = memoField6;
    }

    @JsonProperty("memo_field_7")
    public String getMemoField7() {
        return memoField7;
    }

    @JsonProperty("memo_field_7")
    public void setMemoField7(String memoField7) {
        this.memoField7 = memoField7;
    }

    @JsonProperty("memo_field_8")
    public String getMemoField8() {
        return memoField8;
    }

    @JsonProperty("memo_field_8")
    public void setMemoField8(String memoField8) {
        this.memoField8 = memoField8;
    }

    @JsonProperty("memo_field_9")
    public String getMemoField9() {
        return memoField9;
    }

    @JsonProperty("memo_field_9")
    public void setMemoField9(String memoField9) {
        this.memoField9 = memoField9;
    }

    @JsonProperty("memo_field_10")
    public String getMemoField10() {
        return memoField10;
    }

    @JsonProperty("memo_field_10")
    public void setMemoField10(String memoField10) {
        this.memoField10 = memoField10;
    }

    @JsonProperty("memo_field_11")
    public String getMemoField11() {
        return memoField11;
    }

    @JsonProperty("memo_field_11")
    public void setMemoField11(String memoField11) {
        this.memoField11 = memoField11;
    }

    @JsonProperty("memo_field_12")
    public String getMemoField12() {
        return memoField12;
    }

    @JsonProperty("memo_field_12")
    public void setMemoField12(String memoField12) {
        this.memoField12 = memoField12;
    }

    @JsonProperty("memo_field_13")
    public String getMemoField13() {
        return memoField13;
    }

    @JsonProperty("memo_field_13")
    public void setMemoField13(String memoField13) {
        this.memoField13 = memoField13;
    }

    @JsonProperty("memo_field_14")
    public String getMemoField14() {
        return memoField14;
    }

    @JsonProperty("memo_field_14")
    public void setMemoField14(String memoField14) {
        this.memoField14 = memoField14;
    }

    @JsonProperty("memo_field_15")
    public String getMemoField15() {
        return memoField15;
    }

    @JsonProperty("memo_field_15")
    public void setMemoField15(String memoField15) {
        this.memoField15 = memoField15;
    }

    @JsonProperty("memo_field_16")
    public String getMemoField16() {
        return memoField16;
    }

    @JsonProperty("memo_field_16")
    public void setMemoField16(String memoField16) {
        this.memoField16 = memoField16;
    }

    @JsonProperty("memo_field_17")
    public String getMemoField17() {
        return memoField17;
    }

    @JsonProperty("memo_field_17")
    public void setMemoField17(String memoField17) {
        this.memoField17 = memoField17;
    }

    @JsonProperty("memo_field_18")
    public String getMemoField18() {
        return memoField18;
    }

    @JsonProperty("memo_field_18")
    public void setMemoField18(String memoField18) {
        this.memoField18 = memoField18;
    }

    @JsonProperty("memo_field_19")
    public String getMemoField19() {
        return memoField19;
    }

    @JsonProperty("memo_field_19")
    public void setMemoField19(String memoField19) {
        this.memoField19 = memoField19;
    }

    @JsonProperty("memo_field_20")
    public String getMemoField20() {
        return memoField20;
    }

    @JsonProperty("memo_field_20")
    public void setMemoField20(String memoField20) {
        this.memoField20 = memoField20;
    }

    @JsonProperty("lbs_field_1")
    public String getLbsField1() {
        return lbsField1;
    }

    @JsonProperty("lbs_field_1")
    public void setLbsField1(String lbsField1) {
        this.lbsField1 = lbsField1;
    }

    @JsonProperty("lbs_field_2")
    public String getLbsField2() {
        return lbsField2;
    }

    @JsonProperty("lbs_field_2")
    public void setLbsField2(String lbsField2) {
        this.lbsField2 = lbsField2;
    }

    @JsonProperty("lbs_field_3")
    public String getLbsField3() {
        return lbsField3;
    }

    @JsonProperty("lbs_field_3")
    public void setLbsField3(String lbsField3) {
        this.lbsField3 = lbsField3;
    }

    @JsonProperty("lbs_field_4")
    public String getLbsField4() {
        return lbsField4;
    }

    @JsonProperty("lbs_field_4")
    public void setLbsField4(String lbsField4) {
        this.lbsField4 = lbsField4;
    }

    @JsonProperty("lbs_field_5")
    public String getLbsField5() {
        return lbsField5;
    }

    @JsonProperty("lbs_field_5")
    public void setLbsField5(String lbsField5) {
        this.lbsField5 = lbsField5;
    }

    @JsonProperty("lbs_field_6")
    public String getLbsField6() {
        return lbsField6;
    }

    @JsonProperty("lbs_field_6")
    public void setLbsField6(String lbsField6) {
        this.lbsField6 = lbsField6;
    }

    @JsonProperty("lbs_field_7")
    public String getLbsField7() {
        return lbsField7;
    }

    @JsonProperty("lbs_field_7")
    public void setLbsField7(String lbsField7) {
        this.lbsField7 = lbsField7;
    }

    @JsonProperty("lbs_field_8")
    public String getLbsField8() {
        return lbsField8;
    }

    @JsonProperty("lbs_field_8")
    public void setLbsField8(String lbsField8) {
        this.lbsField8 = lbsField8;
    }

    @JsonProperty("lbs_field_9")
    public String getLbsField9() {
        return lbsField9;
    }

    @JsonProperty("lbs_field_9")
    public void setLbsField9(String lbsField9) {
        this.lbsField9 = lbsField9;
    }

    @JsonProperty("lbs_field_10")
    public String getLbsField10() {
        return lbsField10;
    }

    @JsonProperty("lbs_field_10")
    public void setLbsField10(String lbsField10) {
        this.lbsField10 = lbsField10;
    }

    @JsonProperty("lbs_field_11")
    public String getLbsField11() {
        return lbsField11;
    }

    @JsonProperty("lbs_field_11")
    public void setLbsField11(String lbsField11) {
        this.lbsField11 = lbsField11;
    }

    @JsonProperty("lbs_field_12")
    public String getLbsField12() {
        return lbsField12;
    }

    @JsonProperty("lbs_field_12")
    public void setLbsField12(String lbsField12) {
        this.lbsField12 = lbsField12;
    }

    @JsonProperty("lbs_field_13")
    public String getLbsField13() {
        return lbsField13;
    }

    @JsonProperty("lbs_field_13")
    public void setLbsField13(String lbsField13) {
        this.lbsField13 = lbsField13;
    }

    @JsonProperty("lbs_field_14")
    public String getLbsField14() {
        return lbsField14;
    }

    @JsonProperty("lbs_field_14")
    public void setLbsField14(String lbsField14) {
        this.lbsField14 = lbsField14;
    }

    @JsonProperty("lbs_field_15")
    public String getLbsField15() {
        return lbsField15;
    }

    @JsonProperty("lbs_field_15")
    public void setLbsField15(String lbsField15) {
        this.lbsField15 = lbsField15;
    }

    @JsonProperty("lbs_field_16")
    public String getLbsField16() {
        return lbsField16;
    }

    @JsonProperty("lbs_field_16")
    public void setLbsField16(String lbsField16) {
        this.lbsField16 = lbsField16;
    }

    @JsonProperty("lbs_field_17")
    public String getLbsField17() {
        return lbsField17;
    }

    @JsonProperty("lbs_field_17")
    public void setLbsField17(String lbsField17) {
        this.lbsField17 = lbsField17;
    }

    @JsonProperty("lbs_field_18")
    public String getLbsField18() {
        return lbsField18;
    }

    @JsonProperty("lbs_field_18")
    public void setLbsField18(String lbsField18) {
        this.lbsField18 = lbsField18;
    }

    @JsonProperty("lbs_field_19")
    public String getLbsField19() {
        return lbsField19;
    }

    @JsonProperty("lbs_field_19")
    public void setLbsField19(String lbsField19) {
        this.lbsField19 = lbsField19;
    }

    @JsonProperty("lbs_field_20")
    public String getLbsField20() {
        return lbsField20;
    }

    @JsonProperty("lbs_field_20")
    public void setLbsField20(String lbsField20) {
        this.lbsField20 = lbsField20;
    }

    @JsonProperty("block_trade")
    public String getBlockTrade() {
        return blockTrade;
    }

    @JsonProperty("block_trade")
    public void setBlockTrade(String blockTrade) {
        this.blockTrade = blockTrade;
    }

    @JsonProperty("rd2000_requires_verification")
    public Boolean getRd2000RequiresVerification() {
        return rd2000RequiresVerification;
    }

    @JsonProperty("rd2000_requires_verification")
    public void setRd2000RequiresVerification(Boolean rd2000RequiresVerification) {
        this.rd2000RequiresVerification = rd2000RequiresVerification;
    }

    @JsonProperty("rd2000_is_verified")
    public Boolean getRd2000IsVerified() {
        return rd2000IsVerified;
    }

    @JsonProperty("rd2000_is_verified")
    public void setRd2000IsVerified(Boolean rd2000IsVerified) {
        this.rd2000IsVerified = rd2000IsVerified;
    }

    @JsonProperty("last_user_update_id")
    public String getLastUserUpdateId() {
        return lastUserUpdateId;
    }

    @JsonProperty("last_user_update_id")
    public void setLastUserUpdateId(String lastUserUpdateId) {
        this.lastUserUpdateId = lastUserUpdateId;
    }

    @JsonProperty("last_user_update_timestamp")
    public String getLastUserUpdateTimestamp() {
        return lastUserUpdateTimestamp;
    }

    @JsonProperty("last_user_update_timestamp")
    public void setLastUserUpdateTimestamp(String lastUserUpdateTimestamp) {
        this.lastUserUpdateTimestamp = lastUserUpdateTimestamp;
    }

    @JsonProperty("cost_of_carry_ref_rate_code")
    public String getCostOfCarryRefRateCode() {
        return costOfCarryRefRateCode;
    }

    @JsonProperty("cost_of_carry_ref_rate_code")
    public void setCostOfCarryRefRateCode(String costOfCarryRefRateCode) {
        this.costOfCarryRefRateCode = costOfCarryRefRateCode;
    }

    @JsonProperty("last_user_update_version")
    public Integer getLastUserUpdateVersion() {
        return lastUserUpdateVersion;
    }

    @JsonProperty("last_user_update_version")
    public void setLastUserUpdateVersion(Integer lastUserUpdateVersion) {
        this.lastUserUpdateVersion = lastUserUpdateVersion;
    }

    @JsonProperty("counterparty_string")
    public String getCounterpartyString() {
        return counterpartyString;
    }

    @JsonProperty("counterparty_string")
    public void setCounterpartyString(String counterpartyString) {
        this.counterpartyString = counterpartyString;
    }

    @JsonProperty("cover_cpty_id")
    public String getCoverCptyId() {
        return coverCptyId;
    }

    @JsonProperty("cover_cpty_id")
    public void setCoverCptyId(String coverCptyId) {
        this.coverCptyId = coverCptyId;
    }

    @JsonProperty("dept_name")
    public String getDeptName() {
        return deptName;
    }

    @JsonProperty("dept_name")
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @JsonProperty("entity_name")
    public String getEntityName() {
        return entityName;
    }

    @JsonProperty("entity_name")
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @JsonProperty("subtype")
    public Subtype getSubtype() {
        return subtype;
    }

    @JsonProperty("subtype")
    public void setSubtype(Subtype subtype) {
        this.subtype = subtype;
    }

    @JsonProperty("confirm_method")
    public String getConfirmMethod() {
        return confirmMethod;
    }

    @JsonProperty("confirm_method")
    public void setConfirmMethod(String confirmMethod) {
        this.confirmMethod = confirmMethod;
    }

    @JsonProperty("ccil_status")
    public String getCcilStatus() {
        return ccilStatus;
    }

    @JsonProperty("ccil_status")
    public void setCcilStatus(String ccilStatus) {
        this.ccilStatus = ccilStatus;
    }

    @JsonProperty("host_deal_data_fbo_id")
    public String getHostDealDataFboId() {
        return hostDealDataFboId;
    }

    @JsonProperty("host_deal_data_fbo_id")
    public void setHostDealDataFboId(String hostDealDataFboId) {
        this.hostDealDataFboId = hostDealDataFboId;
    }

    @JsonProperty("deal_identifier")
    public String getDealIdentifier() {
        return dealIdentifier;
    }

    @JsonProperty("deal_identifier")
    public void setDealIdentifier(String dealIdentifier) {
        this.dealIdentifier = dealIdentifier;
    }

    @JsonProperty("margin_type")
    public String getMarginType() {
        return marginType;
    }

    @JsonProperty("margin_type")
    public void setMarginType(String marginType) {
        this.marginType = marginType;
    }

    @JsonProperty("product_type")
    public String getProductType() {
        return productType;
    }

    @JsonProperty("product_type")
    public void setProductType(String productType) {
        this.productType = productType;
    }

    @JsonProperty("query_field")
    public String getQueryField() {
        return queryField;
    }

    @JsonProperty("query_field")
    public void setQueryField(String queryField) {
        this.queryField = queryField;
    }

    @JsonProperty("confirmation_state")
    public String getConfirmationState() {
        return confirmationState;
    }

    @JsonProperty("confirmation_state")
    public void setConfirmationState(String confirmationState) {
        this.confirmationState = confirmationState;
    }

    @JsonProperty("verification_state")
    public String getVerificationState() {
        return verificationState;
    }

    @JsonProperty("verification_state")
    public void setVerificationState(String verificationState) {
        this.verificationState = verificationState;
    }

    @JsonProperty("accounting_code")
    public String getAccountingCode() {
        return accountingCode;
    }

    @JsonProperty("accounting_code")
    public void setAccountingCode(String accountingCode) {
        this.accountingCode = accountingCode;
    }

    @JsonProperty("confirmation_event")
    public String getConfirmationEvent() {
        return confirmationEvent;
    }

    @JsonProperty("confirmation_event")
    public void setConfirmationEvent(String confirmationEvent) {
        this.confirmationEvent = confirmationEvent;
    }

    @JsonProperty("brokerage_method")
    public String getBrokerageMethod() {
        return brokerageMethod;
    }

    @JsonProperty("brokerage_method")
    public void setBrokerageMethod(String brokerageMethod) {
        this.brokerageMethod = brokerageMethod;
    }

    @JsonProperty("portfolio_id_num")
    public Integer getPortfolioIdNum() {
        return portfolioIdNum;
    }

    @JsonProperty("portfolio_id_num")
    public void setPortfolioIdNum(Integer portfolioIdNum) {
        this.portfolioIdNum = portfolioIdNum;
    }

    @JsonProperty("sales_or_interbank")
    public String getSalesOrInterbank() {
        return salesOrInterbank;
    }

    @JsonProperty("sales_or_interbank")
    public void setSalesOrInterbank(String salesOrInterbank) {
        this.salesOrInterbank = salesOrInterbank;
    }

    @JsonProperty("spot_risk_ccy")
    public String getSpotRiskCcy() {
        return spotRiskCcy;
    }

    @JsonProperty("spot_risk_ccy")
    public void setSpotRiskCcy(String spotRiskCcy) {
        this.spotRiskCcy = spotRiskCcy;
    }

    @JsonProperty("traded_ccy")
    public String getTradedCcy() {
        return tradedCcy;
    }

    @JsonProperty("traded_ccy")
    public void setTradedCcy(String tradedCcy) {
        this.tradedCcy = tradedCcy;
    }

    @JsonProperty("fx_fwd_pos_risk_method")
    public String getFxFwdPosRiskMethod() {
        return fxFwdPosRiskMethod;
    }

    @JsonProperty("fx_fwd_pos_risk_method")
    public void setFxFwdPosRiskMethod(String fxFwdPosRiskMethod) {
        this.fxFwdPosRiskMethod = fxFwdPosRiskMethod;
    }

    @JsonProperty("convert_at_spot")
    public Boolean getConvertAtSpot() {
        return convertAtSpot;
    }

    @JsonProperty("convert_at_spot")
    public void setConvertAtSpot(Boolean convertAtSpot) {
        this.convertAtSpot = convertAtSpot;
    }

    @JsonProperty("dco_type")
    public String getDcoType() {
        return dcoType;
    }

    @JsonProperty("dco_type")
    public void setDcoType(String dcoType) {
        this.dcoType = dcoType;
    }

    @JsonProperty("fx_pl_ccy_method")
    public String getFxPlCcyMethod() {
        return fxPlCcyMethod;
    }

    @JsonProperty("fx_pl_ccy_method")
    public void setFxPlCcyMethod(String fxPlCcyMethod) {
        this.fxPlCcyMethod = fxPlCcyMethod;
    }

    @JsonProperty("margin_owner")
    public MarginOwner getMarginOwner() {
        return marginOwner;
    }

    @JsonProperty("margin_owner")
    public void setMarginOwner(MarginOwner marginOwner) {
        this.marginOwner = marginOwner;
    }

    @JsonProperty("margin_value")
    public Double getMarginValue() {
        return marginValue;
    }

    @JsonProperty("margin_value")
    public void setMarginValue(Double marginValue) {
        this.marginValue = marginValue;
    }

    @JsonProperty("primary_margin_value")
    public Double getPrimaryMarginValue() {
        return primaryMarginValue;
    }

    @JsonProperty("primary_margin_value")
    public void setPrimaryMarginValue(Double primaryMarginValue) {
        this.primaryMarginValue = primaryMarginValue;
    }

    @JsonProperty("secondary_margin_value")
    public Double getSecondaryMarginValue() {
        return secondaryMarginValue;
    }

    @JsonProperty("secondary_margin_value")
    public void setSecondaryMarginValue(Double secondaryMarginValue) {
        this.secondaryMarginValue = secondaryMarginValue;
    }

    @JsonProperty("spread_ccy")
    public String getSpreadCcy() {
        return spreadCcy;
    }

    @JsonProperty("spread_ccy")
    public void setSpreadCcy(String spreadCcy) {
        this.spreadCcy = spreadCcy;
    }

    @JsonProperty("ccy1_calendar_set")
    public String getCcy1CalendarSet() {
        return ccy1CalendarSet;
    }

    @JsonProperty("ccy1_calendar_set")
    public void setCcy1CalendarSet(String ccy1CalendarSet) {
        this.ccy1CalendarSet = ccy1CalendarSet;
    }

    @JsonProperty("ccy2_calendar_set")
    public String getCcy2CalendarSet() {
        return ccy2CalendarSet;
    }

    @JsonProperty("ccy2_calendar_set")
    public void setCcy2CalendarSet(String ccy2CalendarSet) {
        this.ccy2CalendarSet = ccy2CalendarSet;
    }

    @JsonProperty("fx_sales_trade_description")
    public String getFxSalesTradeDescription() {
        return fxSalesTradeDescription;
    }

    @JsonProperty("fx_sales_trade_description")
    public void setFxSalesTradeDescription(String fxSalesTradeDescription) {
        this.fxSalesTradeDescription = fxSalesTradeDescription;
    }

    @JsonProperty("is_non_deliverable")
    public Boolean getIsNonDeliverable() {
        return isNonDeliverable;
    }

    @JsonProperty("is_non_deliverable")
    public void setIsNonDeliverable(Boolean isNonDeliverable) {
        this.isNonDeliverable = isNonDeliverable;
    }

    @JsonProperty("currency_pair")
    public String getCurrencyPair() {
        return currencyPair;
    }

    @JsonProperty("currency_pair")
    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    @JsonProperty("start_date")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("end_date")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("end_date")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("lien_required")
    public String getLienRequired() {
        return lienRequired;
    }

    @JsonProperty("lien_required")
    public void setLienRequired(String lienRequired) {
        this.lienRequired = lienRequired;
    }

    @JsonProperty("traded_amount")
    public Double getTradedAmount() {
        return tradedAmount;
    }

    @JsonProperty("traded_amount")
    public void setTradedAmount(Double tradedAmount) {
        this.tradedAmount = tradedAmount;
    }

    @JsonProperty("margin_sell_down_method")
    public String getMarginSellDownMethod() {
        return marginSellDownMethod;
    }

    @JsonProperty("margin_sell_down_method")
    public void setMarginSellDownMethod(String marginSellDownMethod) {
        this.marginSellDownMethod = marginSellDownMethod;
    }

    @JsonProperty("fxp_flow_reference")
    public Integer getFxpFlowReference() {
        return fxpFlowReference;
    }

    @JsonProperty("fxp_flow_reference")
    public void setFxpFlowReference(Integer fxpFlowReference) {
        this.fxpFlowReference = fxpFlowReference;
    }

    @JsonProperty("spot_margin_amount")
    public SpotMarginAmount getSpotMarginAmount() {
        return spotMarginAmount;
    }

    @JsonProperty("spot_margin_amount")
    public void setSpotMarginAmount(SpotMarginAmount spotMarginAmount) {
        this.spotMarginAmount = spotMarginAmount;
    }

    @JsonProperty("total_margin_amount")
    public TotalMarginAmount getTotalMarginAmount() {
        return totalMarginAmount;
    }

    @JsonProperty("total_margin_amount")
    public void setTotalMarginAmount(TotalMarginAmount totalMarginAmount) {
        this.totalMarginAmount = totalMarginAmount;
    }

    @JsonProperty("fwd_margin_amount")
    public FwdMarginAmount getFwdMarginAmount() {
        return fwdMarginAmount;
    }

    @JsonProperty("fwd_margin_amount")
    public void setFwdMarginAmount(FwdMarginAmount fwdMarginAmount) {
        this.fwdMarginAmount = fwdMarginAmount;
    }

    @JsonProperty("confirmation_event_far")
    public String getConfirmationEventFar() {
        return confirmationEventFar;
    }

    @JsonProperty("confirmation_event_far")
    public void setConfirmationEventFar(String confirmationEventFar) {
        this.confirmationEventFar = confirmationEventFar;
    }

    @JsonProperty("spot_posn_subtype")
    public SpotPosnSubtype getSpotPosnSubtype() {
        return spotPosnSubtype;
    }

    @JsonProperty("spot_posn_subtype")
    public void setSpotPosnSubtype(SpotPosnSubtype spotPosnSubtype) {
        this.spotPosnSubtype = spotPosnSubtype;
    }

    @JsonProperty("fwd_posn_subtype")
    public FwdPosnSubtype getFwdPosnSubtype() {
        return fwdPosnSubtype;
    }

    @JsonProperty("fwd_posn_subtype")
    public void setFwdPosnSubtype(FwdPosnSubtype fwdPosnSubtype) {
        this.fwdPosnSubtype = fwdPosnSubtype;
    }

    @JsonProperty("posn_department")
    public PosnDepartment getPosnDepartment() {
        return posnDepartment;
    }

    @JsonProperty("posn_department")
    public void setPosnDepartment(PosnDepartment posnDepartment) {
        this.posnDepartment = posnDepartment;
    }

    @JsonProperty("block_trade_type")
    public String getBlockTradeType() {
        return blockTradeType;
    }

    @JsonProperty("block_trade_type")
    public void setBlockTradeType(String blockTradeType) {
        this.blockTradeType = blockTradeType;
    }

    @JsonProperty("block_deal_num")
    public Integer getBlockDealNum() {
        return blockDealNum;
    }

    @JsonProperty("block_deal_num")
    public void setBlockDealNum(Integer blockDealNum) {
        this.blockDealNum = blockDealNum;
    }

    @JsonProperty("is_in_out_swap")
    public Boolean getIsInOutSwap() {
        return isInOutSwap;
    }

    @JsonProperty("is_in_out_swap")
    public void setIsInOutSwap(Boolean isInOutSwap) {
        this.isInOutSwap = isInOutSwap;
    }

    @JsonProperty("grace_period")
    public Integer getGracePeriod() {
        return gracePeriod;
    }

    @JsonProperty("grace_period")
    public void setGracePeriod(Integer gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    @JsonProperty("is_back_to_back")
    public Boolean getIsBackToBack() {
        return isBackToBack;
    }

    @JsonProperty("is_back_to_back")
    public void setIsBackToBack(Boolean isBackToBack) {
        this.isBackToBack = isBackToBack;
    }

    @JsonProperty("system_info")
    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    @JsonProperty("system_info")
    public void setSystemInfo(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }

    @JsonProperty("main_leg")
    public MainLeg getMainLeg() {
        return mainLeg;
    }

    @JsonProperty("main_leg")
    public void setMainLeg(MainLeg mainLeg) {
        this.mainLeg = mainLeg;
    }

    @JsonProperty("cpty")
    public Cpty_ getCpty() {
        return cpty;
    }

    @JsonProperty("cpty")
    public void setCpty(Cpty_ cpty) {
        this.cpty = cpty;
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
