package mutant.services.com.infrastructure.shared.dto;

public class MutantsCountDto {
    private Integer countMutantDna;
    private Integer countHumanDna;
    private Integer ratio;

    public Integer getCountMutantDna() {
        return countMutantDna;
    }

    public void setCountMutantDna(Integer countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public Integer getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(Integer countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }
}
