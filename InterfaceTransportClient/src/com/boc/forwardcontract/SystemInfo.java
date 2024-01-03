
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
    "deal_type",
    "deal_number",
    "version_number",
    "timestamp",
    "deal_status",
    "current_exception",
    "old_exception",
    "is_broker_deal",
    "deal_links_list",
    "site_deal_id",
    "link_deal_id_1",
    "link_deal_id_2",
    "broker_deal_id",
    "dealer_id",
    "user_completion_id",
    "completion_timestamp",
    "user_updated_id",
    "update_timestamp",
    "capture_timestamp",
    "mtm_today",
    "mtm_change",
    "is_ncd",
    "aggregated_into_pl_register",
    "cost_of_carry_ref_rate_ccys",
    "is_backvalued",
    "has_been_converted_to_emu",
    "deal_role",
    "cparty_mnemonic",
    "cparty_shortname_1",
    "cparty_shortname_2",
    "mp_deal_position",
    "is_matched_or_verified",
    "is_in_interdesk",
    "current_event",
    "prev_current_event",
    "was_backvalued",
    "reuters_deal_data_fbo_id_num",
    "has_cngt_cash_flows",
    "entry_date",
    "what_if_trade",
    "is_financial_info_change",
    "system_remarks",
    "last_amendment_date",
    "deal_status_display",
    "is_night_desk_trade",
    "requires_collateral",
    "first_version_timestamp",
    "has_unapproved_data",
    "spread_base_ccy"
})
public class SystemInfo {

    @JsonProperty("deal_type")
    private String dealType;
    @JsonProperty("deal_number")
    private Integer dealNumber;
    @JsonProperty("version_number")
    private Integer versionNumber;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("deal_status")
    private String dealStatus;
    @JsonProperty("current_exception")
    private String currentException;
    @JsonProperty("old_exception")
    private String oldException;
    @JsonProperty("is_broker_deal")
    private Boolean isBrokerDeal;
    @JsonProperty("deal_links_list")
    private String dealLinksList;
    @JsonProperty("site_deal_id")
    private String siteDealId;
    @JsonProperty("link_deal_id_1")
    private String linkDealId1;
    @JsonProperty("link_deal_id_2")
    private String linkDealId2;
    @JsonProperty("broker_deal_id")
    private String brokerDealId;
    @JsonProperty("dealer_id")
    private String dealerId;
    @JsonProperty("user_completion_id")
    private String userCompletionId;
    @JsonProperty("completion_timestamp")
    private String completionTimestamp;
    @JsonProperty("user_updated_id")
    private String userUpdatedId;
    @JsonProperty("update_timestamp")
    private String updateTimestamp;
    @JsonProperty("capture_timestamp")
    private String captureTimestamp;
    @JsonProperty("mtm_today")
    private String mtmToday;
    @JsonProperty("mtm_change")
    private String mtmChange;
    @JsonProperty("is_ncd")
    private Boolean isNcd;
    @JsonProperty("aggregated_into_pl_register")
    private Boolean aggregatedIntoPlRegister;
    @JsonProperty("cost_of_carry_ref_rate_ccys")
    private String costOfCarryRefRateCcys;
    @JsonProperty("is_backvalued")
    private Boolean isBackvalued;
    @JsonProperty("has_been_converted_to_emu")
    private Boolean hasBeenConvertedToEmu;
    @JsonProperty("deal_role")
    private String dealRole;
    @JsonProperty("cparty_mnemonic")
    private String cpartyMnemonic;
    @JsonProperty("cparty_shortname_1")
    private String cpartyShortname1;
    @JsonProperty("cparty_shortname_2")
    private String cpartyShortname2;
    @JsonProperty("mp_deal_position")
    private Integer mpDealPosition;
    @JsonProperty("is_matched_or_verified")
    private Boolean isMatchedOrVerified;
    @JsonProperty("is_in_interdesk")
    private Boolean isInInterdesk;
    @JsonProperty("current_event")
    private String currentEvent;
    @JsonProperty("prev_current_event")
    private String prevCurrentEvent;
    @JsonProperty("was_backvalued")
    private Boolean wasBackvalued;
    @JsonProperty("reuters_deal_data_fbo_id_num")
    private Integer reutersDealDataFboIdNum;
    @JsonProperty("has_cngt_cash_flows")
    private Boolean hasCngtCashFlows;
    @JsonProperty("entry_date")
    private String entryDate;
    @JsonProperty("what_if_trade")
    private Boolean whatIfTrade;
    @JsonProperty("is_financial_info_change")
    private Boolean isFinancialInfoChange;
    @JsonProperty("system_remarks")
    private String systemRemarks;
    @JsonProperty("last_amendment_date")
    private String lastAmendmentDate;
    @JsonProperty("deal_status_display")
    private String dealStatusDisplay;
    @JsonProperty("is_night_desk_trade")
    private Boolean isNightDeskTrade;
    @JsonProperty("requires_collateral")
    private Boolean requiresCollateral;
    @JsonProperty("first_version_timestamp")
    private String firstVersionTimestamp;
    @JsonProperty("has_unapproved_data")
    private Boolean hasUnapprovedData;
    @JsonProperty("spread_base_ccy")
    private String spreadBaseCcy;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("deal_type")
    public String getDealType() {
        return dealType;
    }

    @JsonProperty("deal_type")
    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    @JsonProperty("deal_number")
    public Integer getDealNumber() {
        return dealNumber;
    }

    @JsonProperty("deal_number")
    public void setDealNumber(Integer dealNumber) {
        this.dealNumber = dealNumber;
    }

    @JsonProperty("version_number")
    public Integer getVersionNumber() {
        return versionNumber;
    }

    @JsonProperty("version_number")
    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("deal_status")
    public String getDealStatus() {
        return dealStatus;
    }

    @JsonProperty("deal_status")
    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    @JsonProperty("current_exception")
    public String getCurrentException() {
        return currentException;
    }

    @JsonProperty("current_exception")
    public void setCurrentException(String currentException) {
        this.currentException = currentException;
    }

    @JsonProperty("old_exception")
    public String getOldException() {
        return oldException;
    }

    @JsonProperty("old_exception")
    public void setOldException(String oldException) {
        this.oldException = oldException;
    }

    @JsonProperty("is_broker_deal")
    public Boolean getIsBrokerDeal() {
        return isBrokerDeal;
    }

    @JsonProperty("is_broker_deal")
    public void setIsBrokerDeal(Boolean isBrokerDeal) {
        this.isBrokerDeal = isBrokerDeal;
    }

    @JsonProperty("deal_links_list")
    public String getDealLinksList() {
        return dealLinksList;
    }

    @JsonProperty("deal_links_list")
    public void setDealLinksList(String dealLinksList) {
        this.dealLinksList = dealLinksList;
    }

    @JsonProperty("site_deal_id")
    public String getSiteDealId() {
        return siteDealId;
    }

    @JsonProperty("site_deal_id")
    public void setSiteDealId(String siteDealId) {
        this.siteDealId = siteDealId;
    }

    @JsonProperty("link_deal_id_1")
    public String getLinkDealId1() {
        return linkDealId1;
    }

    @JsonProperty("link_deal_id_1")
    public void setLinkDealId1(String linkDealId1) {
        this.linkDealId1 = linkDealId1;
    }

    @JsonProperty("link_deal_id_2")
    public String getLinkDealId2() {
        return linkDealId2;
    }

    @JsonProperty("link_deal_id_2")
    public void setLinkDealId2(String linkDealId2) {
        this.linkDealId2 = linkDealId2;
    }

    @JsonProperty("broker_deal_id")
    public String getBrokerDealId() {
        return brokerDealId;
    }

    @JsonProperty("broker_deal_id")
    public void setBrokerDealId(String brokerDealId) {
        this.brokerDealId = brokerDealId;
    }

    @JsonProperty("dealer_id")
    public String getDealerId() {
        return dealerId;
    }

    @JsonProperty("dealer_id")
    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    @JsonProperty("user_completion_id")
    public String getUserCompletionId() {
        return userCompletionId;
    }

    @JsonProperty("user_completion_id")
    public void setUserCompletionId(String userCompletionId) {
        this.userCompletionId = userCompletionId;
    }

    @JsonProperty("completion_timestamp")
    public String getCompletionTimestamp() {
        return completionTimestamp;
    }

    @JsonProperty("completion_timestamp")
    public void setCompletionTimestamp(String completionTimestamp) {
        this.completionTimestamp = completionTimestamp;
    }

    @JsonProperty("user_updated_id")
    public String getUserUpdatedId() {
        return userUpdatedId;
    }

    @JsonProperty("user_updated_id")
    public void setUserUpdatedId(String userUpdatedId) {
        this.userUpdatedId = userUpdatedId;
    }

    @JsonProperty("update_timestamp")
    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    @JsonProperty("update_timestamp")
    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    @JsonProperty("capture_timestamp")
    public String getCaptureTimestamp() {
        return captureTimestamp;
    }

    @JsonProperty("capture_timestamp")
    public void setCaptureTimestamp(String captureTimestamp) {
        this.captureTimestamp = captureTimestamp;
    }

    @JsonProperty("mtm_today")
    public String getMtmToday() {
        return mtmToday;
    }

    @JsonProperty("mtm_today")
    public void setMtmToday(String mtmToday) {
        this.mtmToday = mtmToday;
    }

    @JsonProperty("mtm_change")
    public String getMtmChange() {
        return mtmChange;
    }

    @JsonProperty("mtm_change")
    public void setMtmChange(String mtmChange) {
        this.mtmChange = mtmChange;
    }

    @JsonProperty("is_ncd")
    public Boolean getIsNcd() {
        return isNcd;
    }

    @JsonProperty("is_ncd")
    public void setIsNcd(Boolean isNcd) {
        this.isNcd = isNcd;
    }

    @JsonProperty("aggregated_into_pl_register")
    public Boolean getAggregatedIntoPlRegister() {
        return aggregatedIntoPlRegister;
    }

    @JsonProperty("aggregated_into_pl_register")
    public void setAggregatedIntoPlRegister(Boolean aggregatedIntoPlRegister) {
        this.aggregatedIntoPlRegister = aggregatedIntoPlRegister;
    }

    @JsonProperty("cost_of_carry_ref_rate_ccys")
    public String getCostOfCarryRefRateCcys() {
        return costOfCarryRefRateCcys;
    }

    @JsonProperty("cost_of_carry_ref_rate_ccys")
    public void setCostOfCarryRefRateCcys(String costOfCarryRefRateCcys) {
        this.costOfCarryRefRateCcys = costOfCarryRefRateCcys;
    }

    @JsonProperty("is_backvalued")
    public Boolean getIsBackvalued() {
        return isBackvalued;
    }

    @JsonProperty("is_backvalued")
    public void setIsBackvalued(Boolean isBackvalued) {
        this.isBackvalued = isBackvalued;
    }

    @JsonProperty("has_been_converted_to_emu")
    public Boolean getHasBeenConvertedToEmu() {
        return hasBeenConvertedToEmu;
    }

    @JsonProperty("has_been_converted_to_emu")
    public void setHasBeenConvertedToEmu(Boolean hasBeenConvertedToEmu) {
        this.hasBeenConvertedToEmu = hasBeenConvertedToEmu;
    }

    @JsonProperty("deal_role")
    public String getDealRole() {
        return dealRole;
    }

    @JsonProperty("deal_role")
    public void setDealRole(String dealRole) {
        this.dealRole = dealRole;
    }

    @JsonProperty("cparty_mnemonic")
    public String getCpartyMnemonic() {
        return cpartyMnemonic;
    }

    @JsonProperty("cparty_mnemonic")
    public void setCpartyMnemonic(String cpartyMnemonic) {
        this.cpartyMnemonic = cpartyMnemonic;
    }

    @JsonProperty("cparty_shortname_1")
    public String getCpartyShortname1() {
        return cpartyShortname1;
    }

    @JsonProperty("cparty_shortname_1")
    public void setCpartyShortname1(String cpartyShortname1) {
        this.cpartyShortname1 = cpartyShortname1;
    }

    @JsonProperty("cparty_shortname_2")
    public String getCpartyShortname2() {
        return cpartyShortname2;
    }

    @JsonProperty("cparty_shortname_2")
    public void setCpartyShortname2(String cpartyShortname2) {
        this.cpartyShortname2 = cpartyShortname2;
    }

    @JsonProperty("mp_deal_position")
    public Integer getMpDealPosition() {
        return mpDealPosition;
    }

    @JsonProperty("mp_deal_position")
    public void setMpDealPosition(Integer mpDealPosition) {
        this.mpDealPosition = mpDealPosition;
    }

    @JsonProperty("is_matched_or_verified")
    public Boolean getIsMatchedOrVerified() {
        return isMatchedOrVerified;
    }

    @JsonProperty("is_matched_or_verified")
    public void setIsMatchedOrVerified(Boolean isMatchedOrVerified) {
        this.isMatchedOrVerified = isMatchedOrVerified;
    }

    @JsonProperty("is_in_interdesk")
    public Boolean getIsInInterdesk() {
        return isInInterdesk;
    }

    @JsonProperty("is_in_interdesk")
    public void setIsInInterdesk(Boolean isInInterdesk) {
        this.isInInterdesk = isInInterdesk;
    }

    @JsonProperty("current_event")
    public String getCurrentEvent() {
        return currentEvent;
    }

    @JsonProperty("current_event")
    public void setCurrentEvent(String currentEvent) {
        this.currentEvent = currentEvent;
    }

    @JsonProperty("prev_current_event")
    public String getPrevCurrentEvent() {
        return prevCurrentEvent;
    }

    @JsonProperty("prev_current_event")
    public void setPrevCurrentEvent(String prevCurrentEvent) {
        this.prevCurrentEvent = prevCurrentEvent;
    }

    @JsonProperty("was_backvalued")
    public Boolean getWasBackvalued() {
        return wasBackvalued;
    }

    @JsonProperty("was_backvalued")
    public void setWasBackvalued(Boolean wasBackvalued) {
        this.wasBackvalued = wasBackvalued;
    }

    @JsonProperty("reuters_deal_data_fbo_id_num")
    public Integer getReutersDealDataFboIdNum() {
        return reutersDealDataFboIdNum;
    }

    @JsonProperty("reuters_deal_data_fbo_id_num")
    public void setReutersDealDataFboIdNum(Integer reutersDealDataFboIdNum) {
        this.reutersDealDataFboIdNum = reutersDealDataFboIdNum;
    }

    @JsonProperty("has_cngt_cash_flows")
    public Boolean getHasCngtCashFlows() {
        return hasCngtCashFlows;
    }

    @JsonProperty("has_cngt_cash_flows")
    public void setHasCngtCashFlows(Boolean hasCngtCashFlows) {
        this.hasCngtCashFlows = hasCngtCashFlows;
    }

    @JsonProperty("entry_date")
    public String getEntryDate() {
        return entryDate;
    }

    @JsonProperty("entry_date")
    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    @JsonProperty("what_if_trade")
    public Boolean getWhatIfTrade() {
        return whatIfTrade;
    }

    @JsonProperty("what_if_trade")
    public void setWhatIfTrade(Boolean whatIfTrade) {
        this.whatIfTrade = whatIfTrade;
    }

    @JsonProperty("is_financial_info_change")
    public Boolean getIsFinancialInfoChange() {
        return isFinancialInfoChange;
    }

    @JsonProperty("is_financial_info_change")
    public void setIsFinancialInfoChange(Boolean isFinancialInfoChange) {
        this.isFinancialInfoChange = isFinancialInfoChange;
    }

    @JsonProperty("system_remarks")
    public String getSystemRemarks() {
        return systemRemarks;
    }

    @JsonProperty("system_remarks")
    public void setSystemRemarks(String systemRemarks) {
        this.systemRemarks = systemRemarks;
    }

    @JsonProperty("last_amendment_date")
    public String getLastAmendmentDate() {
        return lastAmendmentDate;
    }

    @JsonProperty("last_amendment_date")
    public void setLastAmendmentDate(String lastAmendmentDate) {
        this.lastAmendmentDate = lastAmendmentDate;
    }

    @JsonProperty("deal_status_display")
    public String getDealStatusDisplay() {
        return dealStatusDisplay;
    }

    @JsonProperty("deal_status_display")
    public void setDealStatusDisplay(String dealStatusDisplay) {
        this.dealStatusDisplay = dealStatusDisplay;
    }

    @JsonProperty("is_night_desk_trade")
    public Boolean getIsNightDeskTrade() {
        return isNightDeskTrade;
    }

    @JsonProperty("is_night_desk_trade")
    public void setIsNightDeskTrade(Boolean isNightDeskTrade) {
        this.isNightDeskTrade = isNightDeskTrade;
    }

    @JsonProperty("requires_collateral")
    public Boolean getRequiresCollateral() {
        return requiresCollateral;
    }

    @JsonProperty("requires_collateral")
    public void setRequiresCollateral(Boolean requiresCollateral) {
        this.requiresCollateral = requiresCollateral;
    }

    @JsonProperty("first_version_timestamp")
    public String getFirstVersionTimestamp() {
        return firstVersionTimestamp;
    }

    @JsonProperty("first_version_timestamp")
    public void setFirstVersionTimestamp(String firstVersionTimestamp) {
        this.firstVersionTimestamp = firstVersionTimestamp;
    }

    @JsonProperty("has_unapproved_data")
    public Boolean getHasUnapprovedData() {
        return hasUnapprovedData;
    }

    @JsonProperty("has_unapproved_data")
    public void setHasUnapprovedData(Boolean hasUnapprovedData) {
        this.hasUnapprovedData = hasUnapprovedData;
    }

    @JsonProperty("spread_base_ccy")
    public String getSpreadBaseCcy() {
        return spreadBaseCcy;
    }

    @JsonProperty("spread_base_ccy")
    public void setSpreadBaseCcy(String spreadBaseCcy) {
        this.spreadBaseCcy = spreadBaseCcy;
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
