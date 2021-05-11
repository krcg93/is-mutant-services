package mutant.services.com.infrastructure.shared.converter;

import io.micronaut.context.annotation.Factory;

import java.util.ArrayList;
import java.util.List;

@Factory
public class ConverterMatrix {

    public static List<List<String>> adnMatrix = new ArrayList<>();

    public static void adnArrayToMatrix(List<String> adnAr){
        List<List<String>> finalMtx = new ArrayList<>();
        adnAr.forEach((adn)->{
            List<String> tmpArray = new ArrayList<>();
            for(int i = 0; i <= adn.length()-1;i++){
                tmpArray.add(String.valueOf(adn.charAt(i)));
            }
            finalMtx.add(tmpArray);
        });

        adnMatrix = finalMtx;
    }



    public static List<String> column (int nCol){
        List<String> columns = new ArrayList<>();
        for (int i = 0; i <= adnMatrix.size()-1; i++){
            columns.add(adnMatrix.get(i).get(nCol));
        }

        return columns;
    }

    public static List<String> row (int nRow){
        List<String> rows = new ArrayList<>();
        for (int i = 0; i <= adnMatrix.size()-1;i++)
            rows.add(adnMatrix.get(nRow).get(i));

        return rows;
    }

    public static List<List<String>> diagonalsUp () {
        List<List<String>> lineUp = new ArrayList<>();
        for (int k = 0; k <= (adnMatrix.size() - 1); ++k) {
            List<String> data = new ArrayList<>();
            for (int y = 0; y < adnMatrix.size(); ++y) {
                int x = k + y;
                if (x < 0 || x >= adnMatrix.size()) {
                } else {
                    data.add(adnMatrix.get(y).get(x));
                }
            }
            lineUp.add(data);
        }
        return lineUp;
    }


    public static List<String> diagonalsDownRow(int nRow){
        List<String> lineDown = new ArrayList<>();
        for (int i = 0; i <= adnMatrix.size()-nRow-1; i++)
            lineDown.add(adnMatrix.get(i+nRow).get(i));

        return lineDown;

    }
}