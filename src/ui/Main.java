package ui;

import model.Country;
import model.CountryContainer;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import com.google.gson.Gson;
import java.io.*;

public class Main {
    private Scanner sc;
    private CountryContainer container;

    private Gson gson;

    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema de ordenamiento de Los Juegos Olimpicos Paris 2024\n\n" +
                "Iniciando app. . .");

        Main objMain = new Main();

        objMain.askIfReadJson();

        int op = 1;

        do{
            op = objMain.Menu();
            objMain.executeOp(op);
        }while (op != 0);

    }

    public Main(){
        sc = new Scanner(System.in);
        container = new CountryContainer();
        gson = new Gson();
    }

    public int Menu(){
        System.out.println("¿Que desea hacer?\n" +
                "1. Ingresar un pais\n" +
                "2. Mostrar medalleria\n" +
                "3. Mostrar total de medallas\n" +
                "4. Mostrar paises\n" +
                "0. Salir del programa");

        return sc.nextInt();
    }

    public void askIfReadJson(){
        File file = new File("countries.txt");

        if (file.exists()){
            System.out.println("Se ha encontrado un archivo con información guardada. ¿Desea recuperar la información? Y/N");
            String op = sc.nextLine().toLowerCase();

            if (op.equals("y")){
                readGson();
            }
        }
    }

    public void executeOp(int op){
        switch (op){

            case 1:
                modifyCountries();
                break;

            case 2:
                System.out.println(container.showMedals());
                break;

            case 3:
                System.out.println(container.showTotalMedals());
                break;

            case 4:
                System.out.println(container.showByNames());
                break;

            case 0:
                System.out.println("Desea guardar los datos. Y/N");
                sc.nextLine();
                String option = sc.nextLine().toLowerCase();
                if(option.equals("y")){
                    writeGson();
                }
                System.out.println("Gracias por utilizar la app. Vuelva pronto");
                break;

            default:
                System.out.println("Elija una opcion valida");
                break;
        }
    }

    public void modifyCountries(){

        System.out.println("Ingrese el pais que deseee añadir o modificar siguiendo el siguiente ejemplo: Colombia::oro::3");
        sc.nextLine();
        String option = sc.nextLine();

        container.modifyCountries(option);
    }

    public void writeGson(){

        String json = gson.toJson(container.countries);

        try{
            FileOutputStream fos = new FileOutputStream(new File("countries.txt"));
            fos.write(json.getBytes(StandardCharsets.UTF_8));
            fos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readGson(){

        File file = new File("countries.txt");

        if (file.exists()){
            try {
                FileInputStream fis = new FileInputStream(file);

                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

                String json = "";
                String line;

                if ((line = reader.readLine())!=null){
                    json = line;
                }

                fis.close();

                Country[] countriesFromJson = gson.fromJson(json, Country[].class);

                container.countries.addAll(Arrays.asList(countriesFromJson));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }


        }
    }




}
