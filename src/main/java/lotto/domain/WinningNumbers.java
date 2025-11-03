package lotto.domain;

import java.util.*;

public class WinningNumbers {
    private final Set<Integer> main; //6개
    private final int bonus;

    public WinningNumbers(List<Integer> mains, int bonus) {
        this.main = validateMain(mains);
        this.bonus = validateBonus(bonus, main);
    }

    private Set<Integer> validateMain(List<Integer> mains) {
        if (mains == null || mains.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        Set<Integer> set = new HashSet<>();
        for (Integer n : mains) {
            if (n == null || n < 1 || n > 45){
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 1부터 45 사이여야 합니다.");
            }
            if (!set.add(n)) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
            }
        }
        return Collections.unmodifiableSet(set);
    }

    private int validateBonus (int bonus, Set<Integer> main) {
        if(bonus < 1 || bonus > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이여야 합니다.");
        }
        if(main.contains(bonus)){
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
        return bonus;
    }

    public Set<Integer> mains() { return main; }
    public int bonus() {return bonus;}
}
