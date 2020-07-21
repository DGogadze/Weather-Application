import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        Document page = Jsoup.parse(new URL("https://amindi.ge/"),3000);
    }
}
