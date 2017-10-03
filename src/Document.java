import javax.print.Doc;
import java.util.Date;

/**
 * Created by hp pc on 28-09-2017.
 */
public abstract class Document {
    private String title;
    private String author;
    private Date date;
    protected PublishingLocation publishingLocation;

    public Document(String title, String author, Date date, String city, String state, String postCode){
        this.title = title;
        this.author = author;
        this.date = date;
        this.publishingLocation = new PublishingLocation(city, state, postCode);

    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }



    public int compareWithGeneralDate(Date date){
        return this.date.compareTo(date);
    }

    public boolean sameAuthor(Document article){
        return author.equals(article.author);
    }

    public int compareDates(Document article){
        return date.compareTo(article.date);
    }

}
