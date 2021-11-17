
package aiss.model.spotify.userssavedtracks;

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
    "added_at",
    "track"
})
public class Item {

    @JsonProperty("added_at")
    private String addedAt;
    @JsonProperty("track")
    private Track track;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("added_at")
    public String getAddedAt() {
        return addedAt;
    }

    @JsonProperty("added_at")
    public void setAddedAt(String addedAt) {
        this.addedAt = addedAt;
    }

    @JsonProperty("track")
    public Track getTrack() {
        return track;
    }

    @JsonProperty("track")
    public void setTrack(Track track) {
        this.track = track;
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
