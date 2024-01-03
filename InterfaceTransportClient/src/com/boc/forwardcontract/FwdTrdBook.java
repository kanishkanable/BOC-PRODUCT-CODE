
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
    "displayname",
    "name",
    "fetch_parent_trading_book",
    "fbo_id",
    "fbo_number"
})
public class FwdTrdBook {

    @JsonProperty("displayname")
    private String displayname;
    @JsonProperty("name")
    private String name;
    @JsonProperty("fetch_parent_trading_book")
    private String fetchParentTradingBook;
    @JsonProperty("fbo_id")
    private String fboId;
    @JsonProperty("fbo_number")
    private String fboNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("displayname")
    public String getDisplayname() {
        return displayname;
    }

    @JsonProperty("displayname")
    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("fetch_parent_trading_book")
    public String getFetchParentTradingBook() {
        return fetchParentTradingBook;
    }

    @JsonProperty("fetch_parent_trading_book")
    public void setFetchParentTradingBook(String fetchParentTradingBook) {
        this.fetchParentTradingBook = fetchParentTradingBook;
    }

    @JsonProperty("fbo_id")
    public String getFboId() {
        return fboId;
    }

    @JsonProperty("fbo_id")
    public void setFboId(String fboId) {
        this.fboId = fboId;
    }

    @JsonProperty("fbo_number")
    public String getFboNumber() {
        return fboNumber;
    }

    @JsonProperty("fbo_number")
    public void setFboNumber(String fboNumber) {
        this.fboNumber = fboNumber;
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
