package ntu.bt8.newspapervnexpress;

public class NewsItem {
    private String title;
    private String description;
    private String pubDate;
    private String link;
    private String imageUrl;

    public NewsItem(String title, String description, String pubDate, String link, String imageUrl) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.link = link;
        this.imageUrl = imageUrl;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPubDate() { return pubDate; }
    public String getLink() { return link; }
    public String getImageUrl() { return imageUrl; }
}
