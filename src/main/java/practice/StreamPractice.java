package practice;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.Candidate;
import model.Cat;
import model.Person;

public class StreamPractice {

    public int findMinEvenNumber(List<String> numbers) {
        return numbers.stream()
                .flatMap(e -> Arrays.stream(e.split(",")))
                .mapToInt(Integer::parseInt)
                .filter(e -> e % 2 == 0)
                .min()
                .orElseThrow(() ->
                        new RuntimeException("Can't get min value from list:" + numbers));
    }

    public Double getOddNumsAverage(List<Integer> numbers) {
        return IntStream.range(0, numbers.size())
                .map(e -> e % 2 == 0 ? numbers.get(e) : numbers.get(e) - 1)
                .filter(e -> e % 2 == 1)
                .average()
                .orElseThrow(NoSuchElementException::new);

    }

    public List<Person> selectMenByAge(List<Person> peopleList, int fromAge, int toAge) {
        return peopleList.stream()
                .filter(e -> e.getSex() == Person.Sex.MAN)
                .filter(e -> e.getAge() > fromAge && e.getAge() <= toAge)
                .collect(Collectors.toList());
    }

    public List<Person> getWorkablePeople(int fromAge, int femaleToAge,
                                          int maleToAge, List<Person> peopleList) {
        return peopleList.stream()
                .filter(e -> e.getAge() >= fromAge)
                .filter(e -> e.getAge() <= maleToAge
                        && e.getSex() == Person.Sex.MAN
                        || e.getAge() <= femaleToAge
                        && e.getSex() == Person.Sex.WOMAN)
                .collect(Collectors.toList());
    }

    public List<String> getCatsNames(List<Person> peopleList, int femaleAge) {
        return peopleList.stream()
                .filter(e -> e.getSex() == Person.Sex.WOMAN)
                .filter(e -> e.getAge() >= femaleAge)
                .flatMap(e -> e.getCats().stream())
                .map(Cat::getName)
                .collect(Collectors.toList());
    }

    public List<String> validateCandidates(List<Candidate> candidates) {
        return candidates.stream()
                .filter(new CandidateValidator())
                .map(Candidate::getName)
                .sorted()
                .collect(Collectors.toList());
    }
}
