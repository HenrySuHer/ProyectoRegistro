package com.henrysuher.andriod.aplicacion2;

import com.orm.SugarRecord;

public class Book extends SugarRecord<Book> {
    String title;
    String edition;
    public Book() {

    }
    public Book(String title,String edition){
        this.title=title;
        this.edition=edition;
    }
}
