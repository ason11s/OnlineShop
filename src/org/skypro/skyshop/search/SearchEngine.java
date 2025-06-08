package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchEngine {

    private final List<Searchable> items;

    public SearchEngine() {
        this.items = new ArrayList<>();
    }

    public void add(Searchable item){
        items.add(item);
    }

    public Map<String, Searchable> search (String term){
        Map<String, Searchable> results = new TreeMap<>();
        String termLower = term.toLowerCase();
        for (Searchable item : items) {
            if (item.getSearchTerm().toLowerCase().contains(termLower)) {
                results.put(item.getSearchTerm(), item);
            }
        }
        return results;
    }

    public Searchable findBestMatch (String search) throws BestResultNotFound{
        int maxCount = 0;
        Searchable bestMatch = null;
        String searchLower = search.toLowerCase();
        for ( Searchable item : items){
            String searchTerm = item.getSearchTerm().toLowerCase();
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
        if (bestMatch == null) {
            throw new BestResultNotFound(search);
        }
        return bestMatch;
        }
    }
