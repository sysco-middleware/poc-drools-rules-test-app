package no.sysco.middleware;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * POJO representing sample object with its metadata
 *
 * @author 100tsa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Component {

    public String id;
    public String type;
    public String name;
    public long score;
    public List<String> messages;
    public Map<String, String> metadata;

    public Component() {
        this.messages = new LinkedList<>();
    }

    public Component(String id, String type, Map<String, String> metadata) {
        this.id = id;
        this.type = type;
        this.messages = new LinkedList<>();
        this.metadata = metadata;
        this.score = 0;
    }

    @Override
    public String toString() {
        return "Component{" + "id=" + id + ", messages=" + messages + '}';
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

}
