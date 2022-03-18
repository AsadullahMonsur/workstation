package org.phloxes.note;

public class VectorGNote {
/*
max depth 20 [20 times nested from root tag]
<Root>

-D
   -E

   -E

   -E
-D
   -E
      -F
      -F
-D

<Root>
//in this manner
*/
/*
private String library;
   private List<String> fields;
   private List<String> output_lists;
   private String name;
   private String alpha;
   private String auto_mirrored;
   private String height;
   private String width;
   private String viewport_height;
   private String viewport_width;
   private String tint;
   private String tint_mode;
   private String optical_inset_top;
   private String optical_inset_bottom;
   private String optical_inset_left;
   private String optical_inset_right;

   public Vector() {
     fields = new ArrayList<String>();
     output_lists = new ArrayList<String>();
     library = "xmlns:aapt=\"http://schemas.android.com/aapt\"\n" +
               "xmlns:android=\"http://schemas.android.com/apk/res/android\"";

     fields.add("android:name=");
     fields.add("android:alpha=");
     fields.add("android:autoMirrored=");
     fields.add("android:height=");
     fields.add("android:width=");
     fields.add("android:viewportHeight=");
     fields.add("android:viewportWidth=");
     fields.add("android:tint=");
     fields.add("android:tintMode=");
     fields.add("android:opticalInsetTop=");
     fields.add("android:opticalInsetBottom=");
     fields.add("android:opticalInsetLeft=");
     fields.add("android:opticalInsetRight=");
   }

   private void insertion(String s,int index){
      String field = fields.get(index);
      output_lists.add((field+s));
   }

   public String output(){
      StringBuilder output = new StringBuilder();
      output.append(library);

      for (String s: output_lists){
        output.append("\n").append(s);
      }
      return output.toString();
   }

   public String getLibrary() {
      return library;
   }

   public void setLibrary(String library) {
      this.library = library;
   }

   public List<String> getFields() {
      return fields;
   }

   public void setFields(List<String> fields) {
      this.fields = fields;
   }

   public List<String> getOutput_lists() {
      return output_lists;
   }

   public void setOutput_lists(List<String> output_lists) {
      this.output_lists = output_lists;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
      insertion(name,0);
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
      insertion(viewport_height,2);
   }

   public String getViewport_width() {
      return viewport_width;
   }

   public void setViewport_width(String viewport_width) {
      this.viewport_width = viewport_width;
      insertion(viewport_width,3);
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
*/

}
