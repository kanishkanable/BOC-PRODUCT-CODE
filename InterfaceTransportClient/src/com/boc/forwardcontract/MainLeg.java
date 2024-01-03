
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
    "leg_identifier",
    "fx_buy_sell",
    "cpty",
    "ccy_pair",
    "quoted_ccy_pair",
    "value_date_one",
    "value_date_two",
    "spot_date",
    "ccy_one_spot_amount",
    "ccy_two_spot_amount",
    "ccy_one_amount",
    "ccy_two_amount",
    "buy_amount",
    "sell_amount",
    "is_split_date",
    "is_rate_inverted",
    "conversion_pair",
    "non_deliverable_leg",
    "contract_rate",
    "cls_state",
    "ccy_one_open_amount",
    "ccy_two_open_amount",
    "option_start_date",
    "option_end_date",
    "effective_value_date_one",
    "effective_value_date_two",
    "lbs_rate_audit",
    "lbs_spot_rate_audit",
    "lbs_fwd_rate_audit",
    "lbs_spot_equiv",
    "lbs_fwd_equiv",
    "confirm_method",
    "market_spot_rate",
    "spot_margin_points",
    "customer_spot_rate",
    "spot_conversion_rate",
    "spot_margin_amount",
    "spot_risk_entity",
    "total_margin_amount",
    "total_margin_points",
    "spot_trd_book",
    "market_spot_rate_audit",
    "transfer_spot_rate",
    "transfer_fwd_points",
    "transfer_fwd_rate",
    "market_fwd_rate",
    "fwd_margin_points",
    "customer_fwd_rate",
    "fwd_conversion_rate",
    "fwd_margin_amount",
    "market_fwd_points",
    "customer_fwd_points",
    "fwd_risk_entity",
    "fwd_trd_book",
    "market_fwd_rate_audit",
    "system_info"
})
public class MainLeg {

    @JsonProperty("fbo_type")
    private String fboType;
    @JsonProperty("fbo_number")
    private Integer fboNumber;
    @JsonProperty("fbo_version")
    private Integer fboVersion;
    @JsonProperty("fbo_id")
    private String fboId;
    @JsonProperty("leg_identifier")
    private String legIdentifier;
    @JsonProperty("fx_buy_sell")
    private String fxBuySell;
    @JsonProperty("cpty")
    private Cpty cpty;
    @JsonProperty("ccy_pair")
    private String ccyPair;
    @JsonProperty("quoted_ccy_pair")
    private String quotedCcyPair;
    @JsonProperty("value_date_one")
    private String valueDateOne;
    @JsonProperty("value_date_two")
    private String valueDateTwo;
    @JsonProperty("spot_date")
    private String spotDate;
    @JsonProperty("ccy_one_spot_amount")
    private CcyOneSpotAmount ccyOneSpotAmount;
    @JsonProperty("ccy_two_spot_amount")
    private CcyTwoSpotAmount ccyTwoSpotAmount;
    @JsonProperty("ccy_one_amount")
    private CcyOneAmount ccyOneAmount;
    @JsonProperty("ccy_two_amount")
    private CcyTwoAmount ccyTwoAmount;
    @JsonProperty("buy_amount")
    private BuyAmount buyAmount;
    @JsonProperty("sell_amount")
    private SellAmount sellAmount;
    @JsonProperty("is_split_date")
    private Boolean isSplitDate;
    @JsonProperty("is_rate_inverted")
    private Boolean isRateInverted;
    @JsonProperty("conversion_pair")
    private String conversionPair;
    @JsonProperty("non_deliverable_leg")
    private Boolean nonDeliverableLeg;
    @JsonProperty("contract_rate")
    private String contractRate;
    @JsonProperty("cls_state")
    private String clsState;
    @JsonProperty("ccy_one_open_amount")
    private CcyOneOpenAmount ccyOneOpenAmount;
    @JsonProperty("ccy_two_open_amount")
    private CcyTwoOpenAmount ccyTwoOpenAmount;
    @JsonProperty("option_start_date")
    private String optionStartDate;
    @JsonProperty("option_end_date")
    private String optionEndDate;
    @JsonProperty("effective_value_date_one")
    private String effectiveValueDateOne;
    @JsonProperty("effective_value_date_two")
    private String effectiveValueDateTwo;
    @JsonProperty("lbs_rate_audit")
    private String lbsRateAudit;
    @JsonProperty("lbs_spot_rate_audit")
    private Double lbsSpotRateAudit;
    @JsonProperty("lbs_fwd_rate_audit")
    private Double lbsFwdRateAudit;
    @JsonProperty("lbs_spot_equiv")
    private LbsSpotEquiv lbsSpotEquiv;
    @JsonProperty("lbs_fwd_equiv")
    private LbsFwdEquiv lbsFwdEquiv;
    @JsonProperty("confirm_method")
    private String confirmMethod;
    @JsonProperty("market_spot_rate")
    private Double marketSpotRate;
    @JsonProperty("spot_margin_points")
    private Double spotMarginPoints;
    @JsonProperty("customer_spot_rate")
    private Double customerSpotRate;
    @JsonProperty("spot_conversion_rate")
    private Double spotConversionRate;
    @JsonProperty("spot_margin_amount")
    private SpotMarginAmount_ spotMarginAmount;
    @JsonProperty("spot_risk_entity")
    private SpotRiskEntity spotRiskEntity;
    @JsonProperty("total_margin_amount")
    private TotalMarginAmount_ totalMarginAmount;
    @JsonProperty("total_margin_points")
    private Double totalMarginPoints;
    @JsonProperty("spot_trd_book")
    private SpotTrdBook spotTrdBook;
    @JsonProperty("market_spot_rate_audit")
    private Double marketSpotRateAudit;
    @JsonProperty("transfer_spot_rate")
    private Double transferSpotRate;
    @JsonProperty("transfer_fwd_points")
    private Double transferFwdPoints;
    @JsonProperty("transfer_fwd_rate")
    private Double transferFwdRate;
    @JsonProperty("market_fwd_rate")
    private Double marketFwdRate;
    @JsonProperty("fwd_margin_points")
    private Double fwdMarginPoints;
    @JsonProperty("customer_fwd_rate")
    private Double customerFwdRate;
    @JsonProperty("fwd_conversion_rate")
    private Double fwdConversionRate;
    @JsonProperty("fwd_margin_amount")
    private FwdMarginAmount_ fwdMarginAmount;
    @JsonProperty("market_fwd_points")
    private Double marketFwdPoints;
    @JsonProperty("customer_fwd_points")
    private Double customerFwdPoints;
    @JsonProperty("fwd_risk_entity")
    private FwdRiskEntity fwdRiskEntity;
    @JsonProperty("fwd_trd_book")
    private FwdTrdBook fwdTrdBook;
    @JsonProperty("market_fwd_rate_audit")
    private Double marketFwdRateAudit;
    @JsonProperty("system_info")
    private SystemInfo_ systemInfo;
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

    @JsonProperty("leg_identifier")
    public String getLegIdentifier() {
        return legIdentifier;
    }

    @JsonProperty("leg_identifier")
    public void setLegIdentifier(String legIdentifier) {
        this.legIdentifier = legIdentifier;
    }

    @JsonProperty("fx_buy_sell")
    public String getFxBuySell() {
        return fxBuySell;
    }

    @JsonProperty("fx_buy_sell")
    public void setFxBuySell(String fxBuySell) {
        this.fxBuySell = fxBuySell;
    }

    @JsonProperty("cpty")
    public Cpty getCpty() {
        return cpty;
    }

    @JsonProperty("cpty")
    public void setCpty(Cpty cpty) {
        this.cpty = cpty;
    }

    @JsonProperty("ccy_pair")
    public String getCcyPair() {
        return ccyPair;
    }

    @JsonProperty("ccy_pair")
    public void setCcyPair(String ccyPair) {
        this.ccyPair = ccyPair;
    }

    @JsonProperty("quoted_ccy_pair")
    public String getQuotedCcyPair() {
        return quotedCcyPair;
    }

    @JsonProperty("quoted_ccy_pair")
    public void setQuotedCcyPair(String quotedCcyPair) {
        this.quotedCcyPair = quotedCcyPair;
    }

    @JsonProperty("value_date_one")
    public String getValueDateOne() {
        return valueDateOne;
    }

    @JsonProperty("value_date_one")
    public void setValueDateOne(String valueDateOne) {
        this.valueDateOne = valueDateOne;
    }

    @JsonProperty("value_date_two")
    public String getValueDateTwo() {
        return valueDateTwo;
    }

    @JsonProperty("value_date_two")
    public void setValueDateTwo(String valueDateTwo) {
        this.valueDateTwo = valueDateTwo;
    }

    @JsonProperty("spot_date")
    public String getSpotDate() {
        return spotDate;
    }

    @JsonProperty("spot_date")
    public void setSpotDate(String spotDate) {
        this.spotDate = spotDate;
    }

    @JsonProperty("ccy_one_spot_amount")
    public CcyOneSpotAmount getCcyOneSpotAmount() {
        return ccyOneSpotAmount;
    }

    @JsonProperty("ccy_one_spot_amount")
    public void setCcyOneSpotAmount(CcyOneSpotAmount ccyOneSpotAmount) {
        this.ccyOneSpotAmount = ccyOneSpotAmount;
    }

    @JsonProperty("ccy_two_spot_amount")
    public CcyTwoSpotAmount getCcyTwoSpotAmount() {
        return ccyTwoSpotAmount;
    }

    @JsonProperty("ccy_two_spot_amount")
    public void setCcyTwoSpotAmount(CcyTwoSpotAmount ccyTwoSpotAmount) {
        this.ccyTwoSpotAmount = ccyTwoSpotAmount;
    }

    @JsonProperty("ccy_one_amount")
    public CcyOneAmount getCcyOneAmount() {
        return ccyOneAmount;
    }

    @JsonProperty("ccy_one_amount")
    public void setCcyOneAmount(CcyOneAmount ccyOneAmount) {
        this.ccyOneAmount = ccyOneAmount;
    }

    @JsonProperty("ccy_two_amount")
    public CcyTwoAmount getCcyTwoAmount() {
        return ccyTwoAmount;
    }

    @JsonProperty("ccy_two_amount")
    public void setCcyTwoAmount(CcyTwoAmount ccyTwoAmount) {
        this.ccyTwoAmount = ccyTwoAmount;
    }

    @JsonProperty("buy_amount")
    public BuyAmount getBuyAmount() {
        return buyAmount;
    }

    @JsonProperty("buy_amount")
    public void setBuyAmount(BuyAmount buyAmount) {
        this.buyAmount = buyAmount;
    }

    @JsonProperty("sell_amount")
    public SellAmount getSellAmount() {
        return sellAmount;
    }

    @JsonProperty("sell_amount")
    public void setSellAmount(SellAmount sellAmount) {
        this.sellAmount = sellAmount;
    }

    @JsonProperty("is_split_date")
    public Boolean getIsSplitDate() {
        return isSplitDate;
    }

    @JsonProperty("is_split_date")
    public void setIsSplitDate(Boolean isSplitDate) {
        this.isSplitDate = isSplitDate;
    }

    @JsonProperty("is_rate_inverted")
    public Boolean getIsRateInverted() {
        return isRateInverted;
    }

    @JsonProperty("is_rate_inverted")
    public void setIsRateInverted(Boolean isRateInverted) {
        this.isRateInverted = isRateInverted;
    }

    @JsonProperty("conversion_pair")
    public String getConversionPair() {
        return conversionPair;
    }

    @JsonProperty("conversion_pair")
    public void setConversionPair(String conversionPair) {
        this.conversionPair = conversionPair;
    }

    @JsonProperty("non_deliverable_leg")
    public Boolean getNonDeliverableLeg() {
        return nonDeliverableLeg;
    }

    @JsonProperty("non_deliverable_leg")
    public void setNonDeliverableLeg(Boolean nonDeliverableLeg) {
        this.nonDeliverableLeg = nonDeliverableLeg;
    }

    @JsonProperty("contract_rate")
    public String getContractRate() {
        return contractRate;
    }

    @JsonProperty("contract_rate")
    public void setContractRate(String contractRate) {
        this.contractRate = contractRate;
    }

    @JsonProperty("cls_state")
    public String getClsState() {
        return clsState;
    }

    @JsonProperty("cls_state")
    public void setClsState(String clsState) {
        this.clsState = clsState;
    }

    @JsonProperty("ccy_one_open_amount")
    public CcyOneOpenAmount getCcyOneOpenAmount() {
        return ccyOneOpenAmount;
    }

    @JsonProperty("ccy_one_open_amount")
    public void setCcyOneOpenAmount(CcyOneOpenAmount ccyOneOpenAmount) {
        this.ccyOneOpenAmount = ccyOneOpenAmount;
    }

    @JsonProperty("ccy_two_open_amount")
    public CcyTwoOpenAmount getCcyTwoOpenAmount() {
        return ccyTwoOpenAmount;
    }

    @JsonProperty("ccy_two_open_amount")
    public void setCcyTwoOpenAmount(CcyTwoOpenAmount ccyTwoOpenAmount) {
        this.ccyTwoOpenAmount = ccyTwoOpenAmount;
    }

    @JsonProperty("option_start_date")
    public String getOptionStartDate() {
        return optionStartDate;
    }

    @JsonProperty("option_start_date")
    public void setOptionStartDate(String optionStartDate) {
        this.optionStartDate = optionStartDate;
    }

    @JsonProperty("option_end_date")
    public String getOptionEndDate() {
        return optionEndDate;
    }

    @JsonProperty("option_end_date")
    public void setOptionEndDate(String optionEndDate) {
        this.optionEndDate = optionEndDate;
    }

    @JsonProperty("effective_value_date_one")
    public String getEffectiveValueDateOne() {
        return effectiveValueDateOne;
    }

    @JsonProperty("effective_value_date_one")
    public void setEffectiveValueDateOne(String effectiveValueDateOne) {
        this.effectiveValueDateOne = effectiveValueDateOne;
    }

    @JsonProperty("effective_value_date_two")
    public String getEffectiveValueDateTwo() {
        return effectiveValueDateTwo;
    }

    @JsonProperty("effective_value_date_two")
    public void setEffectiveValueDateTwo(String effectiveValueDateTwo) {
        this.effectiveValueDateTwo = effectiveValueDateTwo;
    }

    @JsonProperty("lbs_rate_audit")
    public String getLbsRateAudit() {
        return lbsRateAudit;
    }

    @JsonProperty("lbs_rate_audit")
    public void setLbsRateAudit(String lbsRateAudit) {
        this.lbsRateAudit = lbsRateAudit;
    }

    @JsonProperty("lbs_spot_rate_audit")
    public Double getLbsSpotRateAudit() {
        return lbsSpotRateAudit;
    }

    @JsonProperty("lbs_spot_rate_audit")
    public void setLbsSpotRateAudit(Double lbsSpotRateAudit) {
        this.lbsSpotRateAudit = lbsSpotRateAudit;
    }

    @JsonProperty("lbs_fwd_rate_audit")
    public Double getLbsFwdRateAudit() {
        return lbsFwdRateAudit;
    }

    @JsonProperty("lbs_fwd_rate_audit")
    public void setLbsFwdRateAudit(Double lbsFwdRateAudit) {
        this.lbsFwdRateAudit = lbsFwdRateAudit;
    }

    @JsonProperty("lbs_spot_equiv")
    public LbsSpotEquiv getLbsSpotEquiv() {
        return lbsSpotEquiv;
    }

    @JsonProperty("lbs_spot_equiv")
    public void setLbsSpotEquiv(LbsSpotEquiv lbsSpotEquiv) {
        this.lbsSpotEquiv = lbsSpotEquiv;
    }

    @JsonProperty("lbs_fwd_equiv")
    public LbsFwdEquiv getLbsFwdEquiv() {
        return lbsFwdEquiv;
    }

    @JsonProperty("lbs_fwd_equiv")
    public void setLbsFwdEquiv(LbsFwdEquiv lbsFwdEquiv) {
        this.lbsFwdEquiv = lbsFwdEquiv;
    }

    @JsonProperty("confirm_method")
    public String getConfirmMethod() {
        return confirmMethod;
    }

    @JsonProperty("confirm_method")
    public void setConfirmMethod(String confirmMethod) {
        this.confirmMethod = confirmMethod;
    }

    @JsonProperty("market_spot_rate")
    public Double getMarketSpotRate() {
        return marketSpotRate;
    }

    @JsonProperty("market_spot_rate")
    public void setMarketSpotRate(Double marketSpotRate) {
        this.marketSpotRate = marketSpotRate;
    }

    @JsonProperty("spot_margin_points")
    public Double getSpotMarginPoints() {
        return spotMarginPoints;
    }

    @JsonProperty("spot_margin_points")
    public void setSpotMarginPoints(Double spotMarginPoints) {
        this.spotMarginPoints = spotMarginPoints;
    }

    @JsonProperty("customer_spot_rate")
    public Double getCustomerSpotRate() {
        return customerSpotRate;
    }

    @JsonProperty("customer_spot_rate")
    public void setCustomerSpotRate(Double customerSpotRate) {
        this.customerSpotRate = customerSpotRate;
    }

    @JsonProperty("spot_conversion_rate")
    public Double getSpotConversionRate() {
        return spotConversionRate;
    }

    @JsonProperty("spot_conversion_rate")
    public void setSpotConversionRate(Double spotConversionRate) {
        this.spotConversionRate = spotConversionRate;
    }

    @JsonProperty("spot_margin_amount")
    public SpotMarginAmount_ getSpotMarginAmount() {
        return spotMarginAmount;
    }

    @JsonProperty("spot_margin_amount")
    public void setSpotMarginAmount(SpotMarginAmount_ spotMarginAmount) {
        this.spotMarginAmount = spotMarginAmount;
    }

    @JsonProperty("spot_risk_entity")
    public SpotRiskEntity getSpotRiskEntity() {
        return spotRiskEntity;
    }

    @JsonProperty("spot_risk_entity")
    public void setSpotRiskEntity(SpotRiskEntity spotRiskEntity) {
        this.spotRiskEntity = spotRiskEntity;
    }

    @JsonProperty("total_margin_amount")
    public TotalMarginAmount_ getTotalMarginAmount() {
        return totalMarginAmount;
    }

    @JsonProperty("total_margin_amount")
    public void setTotalMarginAmount(TotalMarginAmount_ totalMarginAmount) {
        this.totalMarginAmount = totalMarginAmount;
    }

    @JsonProperty("total_margin_points")
    public Double getTotalMarginPoints() {
        return totalMarginPoints;
    }

    @JsonProperty("total_margin_points")
    public void setTotalMarginPoints(Double totalMarginPoints) {
        this.totalMarginPoints = totalMarginPoints;
    }

    @JsonProperty("spot_trd_book")
    public SpotTrdBook getSpotTrdBook() {
        return spotTrdBook;
    }

    @JsonProperty("spot_trd_book")
    public void setSpotTrdBook(SpotTrdBook spotTrdBook) {
        this.spotTrdBook = spotTrdBook;
    }

    @JsonProperty("market_spot_rate_audit")
    public Double getMarketSpotRateAudit() {
        return marketSpotRateAudit;
    }

    @JsonProperty("market_spot_rate_audit")
    public void setMarketSpotRateAudit(Double marketSpotRateAudit) {
        this.marketSpotRateAudit = marketSpotRateAudit;
    }

    @JsonProperty("transfer_spot_rate")
    public Double getTransferSpotRate() {
        return transferSpotRate;
    }

    @JsonProperty("transfer_spot_rate")
    public void setTransferSpotRate(Double transferSpotRate) {
        this.transferSpotRate = transferSpotRate;
    }

    @JsonProperty("transfer_fwd_points")
    public Double getTransferFwdPoints() {
        return transferFwdPoints;
    }

    @JsonProperty("transfer_fwd_points")
    public void setTransferFwdPoints(Double transferFwdPoints) {
        this.transferFwdPoints = transferFwdPoints;
    }

    @JsonProperty("transfer_fwd_rate")
    public Double getTransferFwdRate() {
        return transferFwdRate;
    }

    @JsonProperty("transfer_fwd_rate")
    public void setTransferFwdRate(Double transferFwdRate) {
        this.transferFwdRate = transferFwdRate;
    }

    @JsonProperty("market_fwd_rate")
    public Double getMarketFwdRate() {
        return marketFwdRate;
    }

    @JsonProperty("market_fwd_rate")
    public void setMarketFwdRate(Double marketFwdRate) {
        this.marketFwdRate = marketFwdRate;
    }

    @JsonProperty("fwd_margin_points")
    public Double getFwdMarginPoints() {
        return fwdMarginPoints;
    }

    @JsonProperty("fwd_margin_points")
    public void setFwdMarginPoints(Double fwdMarginPoints) {
        this.fwdMarginPoints = fwdMarginPoints;
    }

    @JsonProperty("customer_fwd_rate")
    public Double getCustomerFwdRate() {
        return customerFwdRate;
    }

    @JsonProperty("customer_fwd_rate")
    public void setCustomerFwdRate(Double customerFwdRate) {
        this.customerFwdRate = customerFwdRate;
    }

    @JsonProperty("fwd_conversion_rate")
    public Double getFwdConversionRate() {
        return fwdConversionRate;
    }

    @JsonProperty("fwd_conversion_rate")
    public void setFwdConversionRate(Double fwdConversionRate) {
        this.fwdConversionRate = fwdConversionRate;
    }

    @JsonProperty("fwd_margin_amount")
    public FwdMarginAmount_ getFwdMarginAmount() {
        return fwdMarginAmount;
    }

    @JsonProperty("fwd_margin_amount")
    public void setFwdMarginAmount(FwdMarginAmount_ fwdMarginAmount) {
        this.fwdMarginAmount = fwdMarginAmount;
    }

    @JsonProperty("market_fwd_points")
    public Double getMarketFwdPoints() {
        return marketFwdPoints;
    }

    @JsonProperty("market_fwd_points")
    public void setMarketFwdPoints(Double marketFwdPoints) {
        this.marketFwdPoints = marketFwdPoints;
    }

    @JsonProperty("customer_fwd_points")
    public Double getCustomerFwdPoints() {
        return customerFwdPoints;
    }

    @JsonProperty("customer_fwd_points")
    public void setCustomerFwdPoints(Double customerFwdPoints) {
        this.customerFwdPoints = customerFwdPoints;
    }

    @JsonProperty("fwd_risk_entity")
    public FwdRiskEntity getFwdRiskEntity() {
        return fwdRiskEntity;
    }

    @JsonProperty("fwd_risk_entity")
    public void setFwdRiskEntity(FwdRiskEntity fwdRiskEntity) {
        this.fwdRiskEntity = fwdRiskEntity;
    }

    @JsonProperty("fwd_trd_book")
    public FwdTrdBook getFwdTrdBook() {
        return fwdTrdBook;
    }

    @JsonProperty("fwd_trd_book")
    public void setFwdTrdBook(FwdTrdBook fwdTrdBook) {
        this.fwdTrdBook = fwdTrdBook;
    }

    @JsonProperty("market_fwd_rate_audit")
    public Double getMarketFwdRateAudit() {
        return marketFwdRateAudit;
    }

    @JsonProperty("market_fwd_rate_audit")
    public void setMarketFwdRateAudit(Double marketFwdRateAudit) {
        this.marketFwdRateAudit = marketFwdRateAudit;
    }

    @JsonProperty("system_info")
    public SystemInfo_ getSystemInfo() {
        return systemInfo;
    }

    @JsonProperty("system_info")
    public void setSystemInfo(SystemInfo_ systemInfo) {
        this.systemInfo = systemInfo;
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
