
package com.boc.watchlist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "maxScore",
    "total",
    "matchedRecords"
})
public class MatchedResults {

    @JsonProperty("maxScore")
    private String maxScore;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("matchedRecords")
    private List<MatchedRecord> matchedRecords = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("maxScore")
    public String getMaxScore() {
        return maxScore;
    }

    @JsonProperty("maxScore")
    public void setMaxScore(String maxScore) {
        this.maxScore = maxScore;
    }

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    @JsonProperty("matchedRecords")
    public List<MatchedRecord> getMatchedRecords() {
        return matchedRecords;
    }

    @JsonProperty("matchedRecords")
    public void setMatchedRecords(List<MatchedRecord> matchedRecords) {
        this.matchedRecords = matchedRecords;
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
