package in.project.Activelife.news;

public class model
{
   String head, imagelink, newslink;

    public model() {
    }

    public model(String head, String imagelink, String newslink) {
        this.head = head;
        this.imagelink = imagelink;
        this.newslink = newslink;
    }

    public String getHead() {
        return head;
    }

    public String getImagelink() {
        return imagelink;
    }

    public String getNewslink() {
        return newslink;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setImagelink(String imagelink) {
        this.imagelink = imagelink;
    }

    public void setNewslink(String newslink) {
        this.newslink = newslink;
    }
}
