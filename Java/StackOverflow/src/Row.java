import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "row")
public class Row {
    @XmlAttribute(name = "Id")
    private int Id;
    @XmlAttribute(name = "Reputation")
    private int Reputation;
    @XmlAttribute(name = "CreationDate")
    private Date CreationDate;
    @XmlAttribute(name = "DisplayName")
    private String DisplayName;
    @XmlAttribute(name = "LastAccessDate")
    private Date LastAccessDate;
    @XmlAttribute(name = "WebsiteUrl")
    private String WebsiteUrl;
    @XmlAttribute(name = "Location")
    private String Location;
    @XmlAttribute(name = "AboutMe")
    private String AboutMe;
    @XmlAttribute(name = "Views")
    private int Views;
    @XmlAttribute(name = "UpVotes")
    private int UpVotes;
    @XmlAttribute(name = "DownVotes")
    private int DownVotes;
    @XmlAttribute(name = "Age")
    private int Age;
    @XmlAttribute(name = "AccountId")
    private int AccountId;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getReputation() {
        return Reputation;
    }

    public void setReputation(int reputation) {
        Reputation = reputation;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public Date getLastAccessDate() {
        return LastAccessDate;
    }

    public void setLastAccessDate(Date lastAccessDate) {
        LastAccessDate = lastAccessDate;
    }

    public String getWebsiteUrl() {
        return WebsiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        WebsiteUrl = websiteUrl;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAboutMe() {
        return AboutMe;
    }

    public void setAboutMe(String aboutMe) {
        AboutMe = aboutMe;
    }

    public int getViews() {
        return Views;
    }

    public void setViews(int views) {
        Views = views;
    }

    public int getUpVotes() {
        return UpVotes;
    }

    public void setUpVotes(int upVotes) {
        UpVotes = upVotes;
    }

    public int getDownVotes() {
        return DownVotes;
    }

    public void setDownVotes(int downVotes) {
        DownVotes = downVotes;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }
}
