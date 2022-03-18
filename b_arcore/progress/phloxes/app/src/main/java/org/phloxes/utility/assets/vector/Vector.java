package org.phloxes.utility.assets.vector;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "vector")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vector {
   @XmlAttribute(name="android:name")
   private String name;

   @XmlAttribute(name="android:height")
   private String height;

   @XmlAttribute(name="android:width")
   private String width;

   @XmlAttribute(name="android:viewportHeight")
   private String viewport_height;

   @XmlAttribute(name="android:viewportWidth")
   private String viewport_width;

   @XmlAttribute(name="android:alpha")
   private String alpha;

   @XmlAttribute(name="android:autoMirrored")
   private String auto_mirrored;

   @XmlAttribute(name="android:tint")
   private String tint;

   @XmlAttribute(name="android:tintMode")
   private String tint_mode;

   @XmlAttribute(name="android:opticalInsetTop")
   private String optical_inset_top;

   @XmlAttribute(name="android:opticalInsetBottom")
   private String optical_inset_bottom;

   @XmlAttribute(name="android:opticalInsetLeft")
   private String optical_inset_left;

   @XmlAttribute(name="android:opticalInsetRight")
   private String optical_inset_right;

   @XmlElement(name="group")
   private List<Group> group_list;

   @XmlElement(name="path")
   private List<Path> path_list;

   public Vector() {
      group_list = new ArrayList<Group>();
      path_list = new ArrayList<Path>();
   }

   @Override
   public String toString() {
      return "Vector{" +
              "name='" + name + '\'' +
              ", height='" + height + '\'' +
              ", width='" + width + '\'' +
              ", viewport_height='" + viewport_height + '\'' +
              ", viewport_width='" + viewport_width + '\'' +
              ", alpha='" + alpha + '\'' +
              ", auto_mirrored='" + auto_mirrored + '\'' +
              ", tint='" + tint + '\'' +
              ", tint_mode='" + tint_mode + '\'' +
              ", optical_inset_top='" + optical_inset_top + '\'' +
              ", optical_inset_bottom='" + optical_inset_bottom + '\'' +
              ", optical_inset_left='" + optical_inset_left + '\'' +
              ", optical_inset_right='" + optical_inset_right + '\'' +
              '}';
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getHeight() {
      return height;
   }

   public void setHeight(String height) {
      this.height = height;
   }

   public String getWidth() {
      return width;
   }

   public void setWidth(String width) {
      this.width = width;
   }

   public String getViewport_height() {
      return viewport_height;
   }

   public void setViewport_height(String viewport_height) {
      this.viewport_height = viewport_height;
   }

   public String getViewport_width() {
      return viewport_width;
   }

   public void setViewport_width(String viewport_width) {
      this.viewport_width = viewport_width;
   }

   public String getAlpha() {
      return alpha;
   }

   public void setAlpha(String alpha) {
      this.alpha = alpha;
   }

   public String getAuto_mirrored() {
      return auto_mirrored;
   }

   public void setAuto_mirrored(String auto_mirrored) {
      this.auto_mirrored = auto_mirrored;
   }

   public String getTint() {
      return tint;
   }

   public void setTint(String tint) {
      this.tint = tint;
   }

   public String getTint_mode() {
      return tint_mode;
   }

   public void setTint_mode(String tint_mode) {
      this.tint_mode = tint_mode;
   }

   public String getOptical_inset_top() {
      return optical_inset_top;
   }

   public void setOptical_inset_top(String optical_inset_top) {
      this.optical_inset_top = optical_inset_top;
   }

   public String getOptical_inset_bottom() {
      return optical_inset_bottom;
   }

   public void setOptical_inset_bottom(String optical_inset_bottom) {
      this.optical_inset_bottom = optical_inset_bottom;
   }

   public String getOptical_inset_left() {
      return optical_inset_left;
   }

   public void setOptical_inset_left(String optical_inset_left) {
      this.optical_inset_left = optical_inset_left;
   }

   public String getOptical_inset_right() {
      return optical_inset_right;
   }

   public void setOptical_inset_right(String optical_inset_right) {
      this.optical_inset_right = optical_inset_right;
   }

   public List<Group> getGroup_list() {
      return group_list;
   }

   public void setGroup_list(List<Group> group_list) {
      this.group_list = group_list;
   }

   public List<Path> getPath_list() {
      return path_list;
   }

   public void setPath_list(List<Path> path_list) {
      this.path_list = path_list;
   }
}
