package lotto.view;

import lotto.domain.Rank;
import java.math.BigDecimal;
import java.util.*;

public class OutputView {

    public static void printPurchasedTickets(List<List<Integer>> tickets) {
        System.out.println(tickets.size() + "개를 구매했습니다.");
        for (List<Integer> ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public static void printStatistics(Map<Rank, Integer> summary) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + summary.get(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + summary.get(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + summary.get(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + summary.get(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + summary.get(Rank.FIRST) + "개");
    }

    public static void printRate(BigDecimal rate) {
        System.out.println("총 수익률은 " + rate + "%입니다.");
    }
}
