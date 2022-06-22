package sample;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking {

    List<String> lista = new ArrayList<>();
    Ranking(){
        odczyt();
    }

    public List getLista() {
        return lista;
    }

    void dodajRanking(String nick, int punkty){
        String text = nick+", a Jego wynik to: " + punkty;
        lista.add(text);
        sortowanie();
    }

    public void sortowanie() {
        if (lista.size() > 1) {
            for (int i = 0; i < lista.size() - 1; i++) {
                String[] tab1 = lista.get(i).split(" ");
                String[] tab2 = lista.get(i + 1).split(" ");
                if (Integer.parseInt(tab1[5]) < Integer.parseInt(tab2[5])) {
                    Collections.swap(lista, i, i + 1);
                    i = 0;
                } else if (Integer.parseInt(tab1[5]) < Integer.parseInt(tab2[5])) {
                }

            }
        }
    }

    public void odczyt(){
        BufferedReader bufferedReader = null;
        try {
            File file = new File("src\\dane.txt");
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                lista.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void zapis(){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream("src\\dane.txt"));
            for(String text : lista){
                pw.write(text);
                pw.write("\n");
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

