package org.skypro.skyshop.search;

public class SearchEngine {

    private Searchable[] items;

    public SearchEngine(int size) {
        this.items = new Searchable[size];
    }

    private int currentIndex = 0;

    public void add(Searchable item){
        if(currentIndex < items.length){
            items[currentIndex] = item;
            currentIndex++;
        }
    }
    public Searchable[] search(String term){
        Searchable[] results = new Searchable[5];
        int resultsIndex = 0;
        for (Searchable item: items) {
            if (item != null && item.getSearchTerm().toLowerCase().contains(term.toLowerCase())){
                results [resultsIndex] = item;
                resultsIndex++;
            }
            if (resultsIndex == 5){
                break;
            }
        }
        return results;
    }
    public Searchable findBestMatch (String search) throws BestResultNotFound{
        int maxCount = 0;
        Searchable bestMatch = null;
        for ( Searchable item : items){
           if (item == null) continue;
           String searchTerm = item.getSearchTerm().toLowerCase();
           String searchLower = search.toLowerCase();
            int count = 0;
            int index = 0;
            while ((index = searchTerm.indexOf(searchLower, index)) != -1){
                count++;
                index += searchLower.length();
            }
            if (count > maxCount){
                maxCount = count;
                bestMatch = item;
            }
        }
        return bestMatch;
        }
    }
