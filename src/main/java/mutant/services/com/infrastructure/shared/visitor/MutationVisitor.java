package mutant.services.com.infrastructure.shared.visitor;

import mutant.services.com.infrastructure.shared.converter.ConverterMatrix;
import mutant.services.com.infrastructure.shared.dto.IsMutantDto;
import mutant.services.com.infrastructure.shared.dto.MutantDto;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class MutationVisitor implements MutationVisitorI{

    @Inject
    private ModelMapper mapper;

    @Override
    public Mono<IsMutantDto> isMutant(MutantDto mutantDto) {
        ConverterMatrix.adnArrayToMatrix(mutantDto.getDna());
        LinkedHashMap<Integer, List<List<String>>> mapMatrix = new LinkedHashMap<>();
        mapMatrix.put(0, columns(mutantDto.getDna()));
        mapMatrix.put(1, rows(mutantDto.getDna()));
        mapMatrix.put(2, diagonals(mutantDto.getDna()));
        IsMutantDto isMutantDto = new IsMutantDto();
        isMutantDto.setMutant(checkConsecutive(mapMatrix, 4));

        return Mono.just(isMutantDto);
    }

    private List<List<String>> columns (List<String> adn){
        List<List<String>> columns = new ArrayList<>();
        IntStream.range(0, ConverterMatrix.adnMatrix.size()).forEach(idx -> columns.add(ConverterMatrix.column(idx)));

        return columns;
    }

    private List<List<String>> rows(List<String> adn){
        List<List<String>> rows = new ArrayList<>();
        IntStream.range(0, ConverterMatrix.adnMatrix.size()).forEach(idx -> rows.add(ConverterMatrix.row(idx)));

        return rows;

    }

    private List<List<String>> diagonals(List<String> adn) {
        List<List<String>> diagonals = new ArrayList<>();
        diagonals.addAll(ConverterMatrix.diagonalsUp());
        IntStream.range(0, ConverterMatrix.adnMatrix.size()).forEach((idx)->{
            if (idx>0){
                diagonals.add(ConverterMatrix.diagonalsDownRow(idx));
            }
        });
        return diagonals;
    }

    private boolean checkConsecutive (LinkedHashMap<Integer, List<List<String>>> arrays, int stop) {
        for(Map.Entry<Integer, List<List<String>>> data : arrays.entrySet()){
            for (List<String> value: data.getValue()){
                int count = 0;
                for (int i = 0; i <= value.size()-1; i++){
                    String last = (i==0)?value.get(0):value.get(i-1);
                    String actual = value.get(i);
                    if (last.equals(actual)){
                        count++;
                        if (count==stop){
                            return true;
                        }
                    }else{
                        count = 0;
                    }
                }
            }
        }
        return false;
    }
}
