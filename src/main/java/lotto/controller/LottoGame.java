package lotto.controller;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.*;
import lotto.view.*;

import java.util.*;
import java.util.stream.Collectors;

public class LottoGame {

    public void run() {
        long amount = InputView.inputPurchaseAmount();
        Money money = new Money(amount);

        List<Lotto> tickets = buyLottos(money.countOfTickets());
        OutputView.printPurchasedTickets(tickets.stream().map(Lotto::getNumbers).collect(Collectors.toList()));

        List<Integer> winning = InputView.inputWinningNumbers();
        int bonus = InputView.inputBonusNumber();

        WinningNumbers winningNumbers = new WinningNumbers(winning, bonus);

        Result result = matchResults(tickets, winningNumbers);

        OutputView.printStatistics(result.summary());
        OutputView.printRate(result.rate(amount));
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
