import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedditComment {

    @SerializedName("archived")
    @Expose
    private Boolean archived;
    @SerializedName("downs")
    @Expose
    private Integer downs;
    @SerializedName("link_id")
    @Expose
    private String linkId;
    @SerializedName("score_hidden")
    @Expose
    private Boolean scoreHidden;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("author_flair_css_class")
    @Expose
    private Object authorFlairCssClass;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("ups")
    @Expose
    private Integer ups;
    @SerializedName("distinguished")
    @Expose
    private Object distinguished;
    @SerializedName("gilded")
    @Expose
    private Integer gilded;
    @SerializedName("edited")
    @Expose
    private Boolean edited;
    @SerializedName("retrieved_on")
    @Expose
    private Integer retrievedOn;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("created_utc")
    @Expose
    private String createdUtc;
    @SerializedName("subreddit")
    @Expose
    private String subreddit;
    @SerializedName("controversiality")
    @Expose
    private Integer controversiality;
    @SerializedName("author_flair_text")
    @Expose
    private Object authorFlairText;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("subreddit_id")
    @Expose
    private String subredditId;

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public Integer getDowns() {
        return downs;
    }

    public void setDowns(Integer downs) {
        this.downs = downs;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public Boolean getScoreHidden() {
        return scoreHidden;
    }

    public void setScoreHidden(Boolean scoreHidden) {
        this.scoreHidden = scoreHidden;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getAuthorFlairCssClass() {
        return authorFlairCssClass;
    }

    public void setAuthorFlairCssClass(Object authorFlairCssClass) {
        this.authorFlairCssClass = authorFlairCssClass;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getUps() {
        return ups;
    }

    public void setUps(Integer ups) {
        this.ups = ups;
    }

    public Object getDistinguished() {
        return distinguished;
    }

    public void setDistinguished(Object distinguished) {
        this.distinguished = distinguished;
    }

    public Integer getGilded() {
        return gilded;
    }

    public void setGilded(Integer gilded) {
        this.gilded = gilded;
    }

    public Boolean getEdited() {
        return edited;
    }

    public void setEdited(Boolean edited) {
        this.edited = edited;
    }

    public Integer getRetrievedOn() {
        return retrievedOn;
    }

    public void setRetrievedOn(Integer retrievedOn) {
        this.retrievedOn = retrievedOn;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(String createdUtc) {
        this.createdUtc = createdUtc;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public Integer getControversiality() {
        return controversiality;
    }

    public void setControversiality(Integer controversiality) {
        this.controversiality = controversiality;
    }

    public Object getAuthorFlairText() {
        return authorFlairText;
    }

    public void setAuthorFlairText(Object authorFlairText) {
        this.authorFlairText = authorFlairText;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubredditId() {
        return subredditId;
    }

    public void setSubredditId(String subredditId) {
        this.subredditId = subredditId;
    }

}