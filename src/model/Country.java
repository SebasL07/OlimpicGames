package model;


public class Country implements Comparable<Country>{

    private String name;
    private int goldMedals;
    private int silverMedals;
    private int bronzeMedals;
    private int totalMedals;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoldMedals() {
        return goldMedals;
    }

    public void setGoldMedals(int goldMedals) {
        this.goldMedals = goldMedals + this.goldMedals;
    }

    public int getSilverMedals() {
        return silverMedals;
    }

    public Country(String name) {
        this.name = name;
        this.goldMedals = 0;
        this.silverMedals = 0;
        this.bronzeMedals = 0;

    }

    public void setSilverMedals(int silverMedals) {
        this.silverMedals = this.silverMedals+silverMedals;
    }

    public int getBronzeMedals() {
        return bronzeMedals;
    }

    public void setBronzeMedals(int bronzeMedals) {
        this.bronzeMedals = bronzeMedals+this.bronzeMedals;
    }

    @Override
    public int compareTo(Country o) {
        int value = o.getGoldMedals()-this.goldMedals;
        if(value == 0){
            value =  o.getSilverMedals()-this.silverMedals;

            if(value == 0){
               value =  o.getBronzeMedals()-this.bronzeMedals;

               if (value == 0){
                   value = this.name.compareTo(o.getName());
               }
            }
        }

        return value;
    }

    public int getTotalMedals(){
        return totalMedals;
    }

    public void setTotalMedals(){
        this.totalMedals = goldMedals+silverMedals+bronzeMedals;
    }


    @Override
    public String toString() {
        return "Pais{" +
                "Nombre: " + name + "\n"+
                "Medallas de oro: " + goldMedals +"\n"+
                "Medallas de plata: " + silverMedals +"\n"+
                "Medallas de bronce: " + bronzeMedals +"\n"+
                "Total de medallas: " + totalMedals +"\n"+
                '}';
    }
}
