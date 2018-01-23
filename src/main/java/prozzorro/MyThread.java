package prozzorro;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyThread extends Thread {

    private String url;
    private static long id;
    private  static FileWriter fw;

    static {
        try {
            fw = new FileWriter(new File("d:/soccer.txt"),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyThread() throws IOException {}

    public MyThread(String url) {
        this.url = url;
    }

    @Override
    public void run() {

        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("-------------------------------------------------------------------------------------");
        }
        //System.out.println(doc.body().toString());
        Elements newsHeadlines = doc.getElementsByClass("game_team");//getElementsByTag("div");//select("#mp-itn b a");

        for (Element e :newsHeadlines) {
            try {
                fw.write(id++ + " - " + getId() + " - " + getName() + " - " + e.text() + "\r\n");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println(getId() + " - " + getName() + " - " + e.text());

        }
    }
}