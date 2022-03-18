package org.phloxes.utility.assets.vector;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "group")
@XmlAccessorType(XmlAccessType.FIELD)
public class Group {
    @XmlAttribute(name="android:name")
    private String name;

    @XmlAttribute(name="android:pivotX")
    private String pivot_x;

    @XmlAttribute(name="android:pivotY")
    private String pivot_y;

    @XmlAttribute(name="android:rotation")
    private String rotation;

    @XmlAttribute(name="android:scaleX")
    private String scale_x;

    @XmlAttribute(name="android:scaleY")
    private String scale_y;

    @XmlAttribute(name="android:translateX")
    private String translate_x;

    @XmlAttribute(name="android:translateY")
    private String translate_y;

    @XmlElement(name="path")
    private List<Path> path_list;

    @XmlElement(name="clip-path")
    private List<ClipPath> clip_path_list;

    public Group() {
        this.path_list = new ArrayList<Path>();
        this.clip_path_list = new ArrayList<ClipPath>();
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", pivot_x='" + pivot_x + '\'' +
                ", pivot_y='" + pivot_y + '\'' +
                ", rotation='" + rotation + '\'' +
                ", scale_x='" + scale_x + '\'' +
                ", scale_y='" + scale_y + '\'' +
                ", translate_x='" + translate_x + '\'' +
                ", translate_y='" + translate_y + '\'' +
                ", path_list=" + path_list +
                ", clip_path_list=" + clip_path_list +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPivot_x() {
        return pivot_x;
    }

    public void setPivot_x(String pivot_x) {
        this.pivot_x = pivot_x;
    }

    public String getPivot_y() {
        return pivot_y;
    }

    public void setPivot_y(String pivot_y) {
        this.pivot_y = pivot_y;
    }

    public String getRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public String getScale_x() {
        return scale_x;
    }

    public void setScale_x(String scale_x) {
        this.scale_x = scale_x;
    }

    public String getScale_y() {
        return scale_y;
    }

    public void setScale_y(String scale_y) {
        this.scale_y = scale_y;
    }

    public String getTranslate_x() {
        return translate_x;
    }

    public void setTranslate_x(String translate_x) {
        this.translate_x = translate_x;
    }

    public String getTranslate_y() {
        return translate_y;
    }

    public void setTranslate_y(String translate_y) {
        this.translate_y = translate_y;
    }

    public List<Path> getPath_list() {
        return path_list;
    }

    public void setPath_list(List<Path> path_list) {
        this.path_list = path_list;
    }

    public List<ClipPath> getClip_path_list() {
        return clip_path_list;
    }

    public void setClip_path_list(List<ClipPath> clip_path_list) {
        this.clip_path_list = clip_path_list;
    }
}
