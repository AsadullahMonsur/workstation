package org.phloxes.utility.assets.vector;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "clip-path")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClipPath {
    @XmlAttribute(name="android:name")
    private String name;

    @XmlAttribute(name="android:pathData")
    private String path_data;

    public ClipPath() {
    }

    @Override
    public String toString() {
        return "ClipPath{" +
                "name='" + name + '\'' +
                ", path_data='" + path_data + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath_data() {
        return path_data;
    }

    public void setPath_data(String path_data) {
        this.path_data = path_data;
    }
}
