package com.javaFEX.ui.components;

import java.util.ArrayList;

public class Pages {
    private String header;
    private String opening_tag;
    private String closing_tag;
    private ArrayList<Page> pageList;
    private String data;

    public Pages() {
        this.header = "<?xml version = \"1.0\" encoding = \"UTF-8\"?> @Token#";
        this.opening_tag = "<Pages> @Token#";
        this.closing_tag = "</Pages> @Token#";
        data = header+opening_tag+closing_tag;
        pageList = new ArrayList<Page>();
    }

    public void setPageList(ArrayList<Page> pageList) {
        this.pageList = pageList;
    }

    public String output() {
        if(pageList.size()==0){
            return data;
        }
        int length = 0;
        data = header + opening_tag;
        while (length < pageList.size()){
            data = data + pageList.get(length).output();
            length++;
        }
        data = data + closing_tag;
        return data;
    }
}
