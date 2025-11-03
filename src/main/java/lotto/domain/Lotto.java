package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);

        // 정렬 + 불면 리스트
        List<Integer> sorted = numbers.stream().sorted().collect(Collectors.toUnmodifiableList());
        this.numbers = sorted;
    }

    private void validate(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("[ERROR] 로또 번호가 비어 있습니다.");
        }
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        // 범위&중복 검증
        Set<Integer> set = new HashSet<>();
        for (Integer n : numbers) {
            if (n == null) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 null일 수 없습니다.");
            }
            if (n < 1 || n > 45) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
            }
            if (!set.add(n)) { //set에 numbers의 n이 추가가 안 된다면?
                throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
            }
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    // 당첨 번호와의 일치 개수 계산
    public int countMatches (Set < Integer > winning) {
        int cnt = 0;
        for (Integer n : numbers) {
            if (winning.contains(n)) {
                cnt++;
            }
        }
        return cnt;
    }

    // 특정 숫자 포함 여부(보너스 매칭 확인용)
    public boolean contains ( int n){
        return numbers.contains(n);
    }
}