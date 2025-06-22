package org.skypro.skyshop.search;

public class BestResultNotFound extends Exception {
        public BestResultNotFound (String searchTerm){
            super("Не найдено ни одного подходящего результата для запроса: \"" + searchTerm + "\"");
        }
    }
