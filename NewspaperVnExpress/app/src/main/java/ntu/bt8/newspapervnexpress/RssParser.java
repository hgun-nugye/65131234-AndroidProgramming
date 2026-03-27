package ntu.bt8.newspapervnexpress;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RssParser {
    public static List<NewsItem> parse(InputStream inputStream) throws XmlPullParserException, IOException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(inputStream, null);

        List<NewsItem> newsList = new ArrayList<>();
        String title = null;
        String link = null;
        String description = null;
        String pubDate = null;
        String imageUrl = null;
        boolean insideItem = false;

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                if (parser.getName().equalsIgnoreCase("item")) {
                    insideItem = true;
                } else if (insideItem) {
                    if (parser.getName().equalsIgnoreCase("title")) {
                        title = parser.nextText();
                    } else if (parser.getName().equalsIgnoreCase("link")) {
                        link = parser.nextText();
                    } else if (parser.getName().equalsIgnoreCase("description")) {
                        description = parser.nextText();
                        // Extract image from description (VnExpress usually puts img in description as CDATA)
                        imageUrl = extractImageUrl(description);
                        description = cleanDescription(description);
                    } else if (parser.getName().equalsIgnoreCase("pubDate")) {
                        pubDate = parser.nextText();
                    }
                }
            } else if (eventType == XmlPullParser.END_TAG && parser.getName().equalsIgnoreCase("item")) {
                newsList.add(new NewsItem(title, description, pubDate, link, imageUrl));
                insideItem = false;
            }
            eventType = parser.next();
        }
        return newsList;
    }

    private static String extractImageUrl(String description) {
        if (description == null) return null;
        Pattern pattern = Pattern.compile("src=\"(.*?)\"");
        Matcher matcher = pattern.matcher(description);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static String cleanDescription(String description) {
        if (description == null) return "";
        // Remove HTML tags from description
        return description.replaceAll("<.*?>", "").trim();
    }
}
