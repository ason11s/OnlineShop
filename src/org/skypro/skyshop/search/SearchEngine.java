package org.skypro.skyshop.search;

import java.util.*;

public class SearchEngine {

    private final Set<Searchable> items;

    public SearchEngine() {
        this.items = new HashSet<>();
    }

    public void add(Searchable item){
        items.add(item);
    }

    public Set<Searchable> search (String term){
        Set<Searchable> results = new TreeSet<>((s1,s2)->{
            int lengthCompare = Integer.compare(s2.getSearchTerm().length(), s1.getSearchTerm().length());
            if (lengthCompare != 0) {
                return lengthCompare;
            }
            return s1.getSearchTerm().compareTo(s2.getSearchTerm());
        });
        String termLower = term.toLowerCase();

        for (Searchable item : items) {
            if (item.getSearchTerm().toLowerCase().contains(termLower)) {
                results.add(item);
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
