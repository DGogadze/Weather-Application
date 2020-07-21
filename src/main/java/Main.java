import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        Document page = Jsoup.parse(new URL("https://amindi.ge/"),3000);
        /** Elements */
        Element currentCityName = page.select("div[class=current_city]").first();
        Element currentDate = page.select("div[class=current_time]").first();
        Element additionalInformation = page.select("div[class=current_phrase left]").first();
        Element temperature = page.select("div[class=a current_temp left]").first();

        /** Patterns & Matchers */
        Pattern cityNamePattern = Pattern.compile("\\n\\s.+\\n");
        Matcher cityNameMatcher = cityNamePattern.matcher(currentCityName.toString());
        while (cityNameMatcher.find()){
            /** add to city name label */
            System.out.print(cityNameMatcher.group().substring(1));
        }
        Pattern datePattern = Pattern.compile(".+,\\s\\d{1,2}\\s.+");
        Matcher dateMatcher = datePattern.matcher(currentDate.toString());
        while (dateMatcher.find()){
            /** add to date label */
            System.out.println(dateMatcher.group());
        }
        Pattern additionalInformationPattern = Pattern.compile("\\n\\s\\s.+\\s\\n");
        Matcher additionalInformationMatcher = additionalInformationPattern.matcher(additionalInformation.toString());
        while (additionalInformationMatcher.find()){
            /** add to additionalInformation label */
            System.out.print(additionalInformationMatcher.group().substring(2));
        }
        Pattern temperaturePattern = Pattern.compile("\\n\\s.+\\n");
        Matcher temperatureMatcher = temperaturePattern.matcher(temperature.toString());
        while (temperatureMatcher.find()){
            System.out.println(temperatureMatcher.group().substring(1));
        }
    }
}
