import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.awt.*;
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
        Element temperatureInfo = page.select("div[class=a current_temp left]").first();

        /** Information Strings */
        StringBuilder cityNameString = new StringBuilder();
        StringBuilder additionalInformationString = new StringBuilder();
        StringBuilder dateString = new StringBuilder();
        StringBuilder temperatureString = new StringBuilder();

        /** Patterns & Matchers */
        Pattern cityNamePattern = Pattern.compile("\\n\\s.+\\n");
        Matcher cityNameMatcher = cityNamePattern.matcher(currentCityName.toString());
        while (cityNameMatcher.find()){
            /** add to city name label */
            cityNameString.append(cityNameMatcher.group().substring(1));
        }
        Pattern datePattern = Pattern.compile(".+,\\s\\d{1,2}\\s.+");
        Matcher dateMatcher = datePattern.matcher(currentDate.toString());
        while (dateMatcher.find()){
            /** add to date label */
            dateString.append(dateMatcher.group());
        }
        Pattern additionalInformationPattern = Pattern.compile("\\n\\s\\s.+\\s\\n");
        Matcher additionalInformationMatcher = additionalInformationPattern.matcher(additionalInformation.toString());
        while (additionalInformationMatcher.find()){
            /** add to additionalInformation label */
            additionalInformationString.append(additionalInformationMatcher.group().substring(2));
        }
        Pattern temperaturePattern = Pattern.compile("\\n\\s.+\\n");
        Matcher temperatureMatcher = temperaturePattern.matcher(temperatureInfo.toString());
        while (temperatureMatcher.find()){
            /** add to temperature label */
            temperatureString.append(temperatureMatcher.group().substring(1));
        }

        /** User Interface */
        JFrame applicationWindow = new JFrame("Weather");
        JPanel container = new JPanel();

        /** Window Properties */
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screen.width/2;
        int height = screen.height/2;
        applicationWindow.setSize(width,height);
        applicationWindow.setLocation(width/2,height/2);
        applicationWindow.setResizable(true);
        applicationWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        applicationWindow.add(container);
        applicationWindow.setVisible(true);

        /** Panel Properties */
        container.setBackground(Color.orange);
        container.setLayout(null);

        /** Container Elements */
        JLabel temperatureLabel = new JLabel();
        JLabel additionalInformationLabel = new JLabel();
        JLabel cityNameLabel = new JLabel();
        JLabel dateLabel = new JLabel();

        /** Label properties */
        String temperature = "ტემპერატურა";
        temperatureLabel.setText(temperature+ " : " + temperatureString.toString());
        temperatureLabel.setBounds(370,200,300,20);
        container.add(temperatureLabel);
        int x =0 ;
        try {
            x = Integer.parseInt(temperatureString.toString());
        } catch (Exception e){
            Pattern intPattern = Pattern.compile("\\d{1,2}");
            Matcher matcher = intPattern.matcher(temperatureString.toString());
            while (matcher.find()){
                x = Integer.parseInt(matcher.group());
            }
        }
        if (x>=25){
            temperatureLabel.setForeground(Color.RED);
        } else if (x<25){
            temperatureLabel.setForeground(Color.green);
        } else if (x<10){
            temperatureLabel.setForeground(Color.GRAY);
        } else temperatureLabel.setForeground(Color.cyan);

        String additionalInformationForLabel = "საერთო მდგომარეობა";
        additionalInformationLabel.setText(additionalInformationForLabel+ " : " + additionalInformationString.toString());
        additionalInformationLabel.setBounds(35,200,300,20);
        container.add(additionalInformationLabel);

        String cityName = "ქალაქის სახელი";
        cityNameLabel.setText(cityName + " : " + cityNameString.toString());
        cityNameLabel.setBounds(370,25,300,20);
        container.add(cityNameLabel);

        String date = "თარიღი";
        dateLabel.setText(date + " : " + dateString.toString());
        dateLabel.setBounds(35,25,300,20);
        container.add(dateLabel);
    }
}
