package lotto;

import lotto.controller.LottoGame;

public class Application {
    public static void main(String[] args) {
        try{
            new LottoGame().run();
        } catch(IllegalArgumentException e) {
            System.out.println((e.getMessage()));;
        }
    }
}
