package org.phloxes.utility.assets.vector;

public class PatternX {
    private String start_open_tag;
    private String attributes;
    private String end_open_tag;
    private String element;
    private String close_tag;

    public PatternX() {

    }

    public String output(){
        return (start_open_tag+"\n"+
                attributes+
                end_open_tag+"\n"+
                element+"\n"+
                close_tag);
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public void setStart_open_tag(String start_open_tag) {
        this.start_open_tag = start_open_tag;
    }

    public void setEnd_open_tag(String end_open_tag) {
        this.end_open_tag = end_open_tag;
    }

    public void setClose_tag(String close_tag) {
        this.close_tag = close_tag;
    }
}
