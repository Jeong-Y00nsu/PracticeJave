package plan;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Plan implements Serializable, Comparable<Plan> {

    private String id;
    private String title;
    private String text;
    private LocalDate startDt;
    private LocalDate endDt;

    public Plan(){}
    public Plan(String id, String title, String text, LocalDate startDt, LocalDate endDt) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.startDt = startDt;
        this.endDt = endDt;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public LocalDate getStartDt() {
        return startDt;
    }

    public LocalDate getEndDt() {
        return endDt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setStartDt(LocalDate startDt) {
        this.startDt = startDt;
    }

    public void setEndDt(LocalDate endDt) {
        this.endDt = endDt;
    }

    @Override
    public String toString() {
        return "["+this.getId()+"] title: "+this.getTitle()+", text: "+this.getText()+", start: "+this.getStartDt()+", end: "+this.getEndDt();
    }

    @Override
    public int compareTo(Plan o) {
        if(this.startDt.compareTo(o.startDt)!=0) {
            return this.startDt.compareTo(o.startDt);
        } else {
            return this.endDt.compareTo(o.endDt);
        }
    }
}
