package com.maximaLibri.maximaLibriV2.dataGeneration;

import com.jayway.jsonpath.JsonPath;
import com.maximaLibri.maximaLibriV2.model.Book;
import com.maximaLibri.maximaLibriV2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

import java.net.*;
import java.io.*;

import com.paralleldots.paralleldots.App;

//@Component
public class AddDescriptionAndKeywordsForBooks {

    @Autowired
    BookRepository bookRepository;

    /** script care sa completeze baza de date cu descrierile (parsate de pe goodreads) si cuvinetele cheie (generate de un api extern) ale cartilor din baza de date*/
    //@EventListener(ApplicationReadyEvent.class)
    public void addDescriptionAndKeywordsForBooks() {

        List<Book> bookList = bookRepository.findAll();
        String baseURL = "https://www.goodreads.com/search?q=";

        App pd = new App("UlQbgmtuLUkMygYSueumxD40Ve4znCI0DedqdJxD1uE");

        for (Book book : bookList) {
        //{Book book = bookList.get(0);
        System.out.println(book.getIsbn());
            try {
                URL oracle = new URL(baseURL+book.getIsbn());
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(oracle.openStream()));

                String inputLine;
                Boolean intoDescriptionDiv = false;
                Boolean passedFirstSpan = false;
                StringBuffer description = null;
                while ((inputLine = in.readLine()) != null){
                    if(inputLine.contains("id=\"description\"")) intoDescriptionDiv=true;
                    if(inputLine.contains("<span") && intoDescriptionDiv && passedFirstSpan) description = new StringBuffer(inputLine);
                    if(inputLine.contains("<span") && intoDescriptionDiv) passedFirstSpan=true;
                    if(intoDescriptionDiv && inputLine.contains("</div>")) intoDescriptionDiv=false;
                }
                in.close();
                if(description!=null) {
                    Integer first = description.indexOf(">");
                    description.delete(0, first + 1);
                    first = description.indexOf("<br />");
                    while (first != -1) {
                        description.delete(first, first + 6);
                        description.insert(first, "\n\n");
                        first = description.indexOf("<br />");
                    }
                    first = description.indexOf("<i>");
                    while(first!=-1) {
                        Integer second = description.indexOf("</i>");
                        description.delete(first,second+4);
                        first = description.indexOf("<i>");
                    }

                    first = description.indexOf("</span>");
                    description.delete(first, first + 7);

                    //book.setDescription(description.toString());

                    String keywords = null;
                    keywords = pd.keywords(description.toString());
                    List<String> keywordList = JsonPath.read(keywords,"$.keywords[*].keyword");
                    StringBuffer keywordsString = new StringBuffer();
                    for(String word : keywordList) keywordsString.append(";").append(word);
                    if(keywordsString.length()>0) keywordsString.delete(0,1);
                    //book.setKeywords(keywordsString.toString());
                    //bookRepository.save(book);

                }
                else {
                    //book.setDescription("");
                    //book.setKeywords("");
                    //bookRepository.save(book);
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
