package org.phloxes.utility.assets.vector;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "path")
@XmlAccessorType(XmlAccessType.FIELD)
public class Path {
    @XmlAttribute(name="android:name")
    private String name;

    @XmlAttribute(name="android:fillAlpha")
    private String fill_alpha;

    @XmlAttribute(name="android:fillColor")
    private String fill_color;

    @XmlAttribute(name="android:fillType")
    private String fill_type;

    @XmlAttribute(name="android:pathData")
    private String path_data;

    @XmlAttribute(name="android:strokeAlpha")
    private String stroke_alpha;

    @XmlAttribute(name="android:strokeColor")
    private String stroke_color;

    @XmlAttribute(name="android:strokeWidth")
    private String stroke_width;

    @XmlAttribute(name="android:strokeLineCap")
    private String stroke_line_cap;

    @XmlAttribute(name="android:strokeLineJoin")
    private String stroke_line_join;

    @XmlAttribute(name="android:strokeMiterLimit")
    private String stroke_miter_limit;

    @XmlAttribute(name="android:trimPathEnd")
    private String trim_path_end;

    @XmlAttribute(name="android:trimPathOffset")
    private String trim_path_offset;

    @XmlAttribute(name="android:trimPathStart")
    private String trim_path_start;

    @Override
    public String toString() {
        return "Path{" +
                "name='" + name + '\'' +
                ", fill_alpha='" + fill_alpha + '\'' +
                ", fill_color='" + fill_color + '\'' +
                ", fill_type='" + fill_type + '\'' +
                ", path_data='" + path_data + '\'' +
                ", stroke_alpha='" + stroke_alpha + '\'' +
                ", stroke_color='" + stroke_color + '\'' +
                ", stroke_width='" + stroke_width + '\'' +
                ", stroke_line_cap='" + stroke_line_cap + '\'' +
                ", stroke_line_join='" + stroke_line_join + '\'' +
                ", stroke_miter_limit='" + stroke_miter_limit + '\'' +
                ", trim_path_end='" + trim_path_end + '\'' +
                ", trim_path_offset='" + trim_path_offset + '\'' +
                ", trim_path_start='" + trim_path_start + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFill_alpha() {
        return fill_alpha;
    }

    public void setFill_alpha(String fill_alpha) {
        this.fill_alpha = fill_alpha;
    }

    public String getFill_color() {
        return fill_color;
    }

    public void setFill_color(String fill_color) {
        this.fill_color = fill_color;
    }

    public String getFill_type() {
        return fill_type;
    }

    public void setFill_type(String fill_type) {
        this.fill_type = fill_type;
    }

    public String getPath_data() {
        return path_data;
    }

    public void setPath_data(String path_data) {
        this.path_data = path_data;
    }

    public String getStroke_alpha() {
        return stroke_alpha;
    }

    public void setStroke_alpha(String stroke_alpha) {
        this.stroke_alpha = stroke_alpha;
    }

    public String getStroke_color() {
        return stroke_color;
    }

    public void setStroke_color(String stroke_color) {
        this.stroke_color = stroke_color;
    }

    public String getStroke_width() {
        return stroke_width;
    }

    public void setStroke_width(String stroke_width) {
        this.stroke_width = stroke_width;
    }

    public String getStroke_line_cap() {
        return stroke_line_cap;
    }

    public void setStroke_line_cap(String stroke_line_cap) {
        this.stroke_line_cap = stroke_line_cap;
    }

    public String getStroke_line_join() {
        return stroke_line_join;
    }

    public void setStroke_line_join(String stroke_line_join) {
        this.stroke_line_join = stroke_line_join;
    }

    public String getStroke_miter_limit() {
        return stroke_miter_limit;
    }

    public void setStroke_miter_limit(String stroke_miter_limit) {
        this.stroke_miter_limit = stroke_miter_limit;
    }

    public String getTrim_path_end() {
        return trim_path_end;
    }

    public void setTrim_path_end(String trim_path_end) {
        this.trim_path_end = trim_path_end;
    }

    public String getTrim_path_offset() {
        return trim_path_offset;
    }

    public void setTrim_path_offset(String trim_path_offset) {
        this.trim_path_offset = trim_path_offset;
    }

    public String getTrim_path_start() {
        return trim_path_start;
    }

    public void setTrim_path_start(String trim_path_start) {
        this.trim_path_start = trim_path_start;
    }
}
