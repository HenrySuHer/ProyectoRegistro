package com.henrysuher.andriod.aplicacion2.Modelo;

import com.orm.SugarRecord;

public class Book extends SugarRecord<Book>{
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    String title;
    String edition;
    public Book() {

    }
    public Book(String title,String edition){
        this.title=title;
        this.edition=edition;
    }
}
