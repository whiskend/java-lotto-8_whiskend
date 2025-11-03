package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.*;
import lotto.view.*;

import java.util.*;
import java.util.stream.Collectors;

public class LottoGame {

    public void run() {
        long amount = askPurchaseAmount();
        Money money = new Money(amount);

        java.util.List<Lotto> tickets = buyLottos(money.countOfTickets());
        OutputView.printPurchasedTickets(
                tickets.stream().map(Lotto::getNumbers).collect(java.util.stream.Collectors.toList())
        );

        java.util.List<Integer> winning = askWinningNumbers();
        int bonus = askBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(winning, bonus);

        Result result = matchResults(tickets, winningNumbers);
        OutputView.printStatistics(result.summary());
        OutputView.printRate(result.rate(amount));
    }

    private long askPurchaseAmount() {
        while (true) {
            try {
                return InputView.inputPurchaseAmount();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private java.util.List<Integer> askWinningNumbers() {
        while (true) {
            try {
                return InputView.inputWinningNumbers();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int askBonusNumber() {
        while (true) {
            try {
                return InputView.inputBonusNumber();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Lotto> buyLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for(int i = 0; i < count; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            lottos.add(new Lotto(numbers));
        }
        return  lottos;
    }

    private  Result matchResults(List<Lotto> tickets, WinningNumbers winningNumbers) {
        Result result = new Result();
        for (Lotto ticket : tickets) {
            int matchCount = ticket.countMatches(winningNumbers.mains());
            boolean bonusMatched = ticket.contains(winningNumbers.bonus());
            Rank.of(matchCount, bonusMatched).ifPresent(result::record);
        }
        return result;
    }
}
