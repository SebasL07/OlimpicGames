package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CountryContainer {

    public ArrayList<Country> countries;

    public CountryContainer() {
        countries = new ArrayList<>();
    }

    public String showMedals(){
        sort();

        String msg = "";

        int place = 1;

        if(countries.isEmpty()){
            msg = "Aun no hay paises registrados";
        }else{

            for (Country country : countries){
                msg += place+". "+country.toString()+"\n";
                place++;
            }
        }

        return msg;
    }

    public String showTotalMedals(){
        sortByTotalMedals();

        String msg = "";

        int place = 1;

        if(countries.isEmpty()){
            msg = "Aun no hay paises registrados";
        }else{

            for (Country country : countries){
                msg += place +". "+ country.toString()+"\n";
                place++;
            }
        }

        return msg;
    }

    public String showByNames(){
        selectionSortByNames(countries);

        String msg = "";

        if(countries.isEmpty()){
            msg = "Aun no hay paises registrados";
        }else{

            for (Country country : countries){
                msg += country.toString()+"\n";
            }
        }

        return msg;
    }



    public void sort(){
        Collections.sort(countries);
    }

    public void sortByTotalMedals(){
        Collections.sort(countries, new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return o2.getTotalMedals()-o1.getTotalMedals();
            }
        });
    }

    private static void selectionSortByNames(ArrayList<Country> array) {
        long startTime = System.nanoTime();
        for (int rojo = 0; rojo < array.size() - 1; rojo++) {
            for (int azul = rojo + 1; azul < array.size(); azul++) {
                if (array.get(rojo).getName().compareTo(array.get(azul).getName()) > 0) {
                    // get values to swap
                    Country valorRojo = array.get(rojo);
                    Country valorAzul = array.get(azul);
                    // swap
                    array.set(rojo, valorAzul);
                    array.set(azul, valorRojo);
                }
            }
        }
    }

    public void modifyCountries(String option){

        String[] strings;

        strings = option.split("::");

        Country country = containsCountry(strings[0]);

        if (country == null) {
            countries.add(new Country(strings[0]));
            country = countries.get(countries.size()-1);
        }



        switch (strings[1].toLowerCase()){

            case ("oro"):
                country.setGoldMedals(Integer.parseInt(strings[2]));
                break;

            case ("plata"):
                country.setSilverMedals(Integer.parseInt(strings[2]));
                break;

            case ("bronce"):
                country.setBronzeMedals(Integer.parseInt(strings[2]));
                break;
        }

        country.setTotalMedals();


    }

    public Country containsCountry(String option){

        Country countryRe = null;

        for(Country country : countries){

            if (country.getName().equals(option)){
                countryRe = country;
                break;
            }
        }

        return countryRe;
    }


}
