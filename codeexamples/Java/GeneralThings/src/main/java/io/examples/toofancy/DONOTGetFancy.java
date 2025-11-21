package io.examples.toofancy;

import java.io.StringBufferInputStream;

public class DONOTGetFancy {
    public static Integer confusingRecur(Integer x) {
        if (x < 7) {
            System.out.println("lolly");
            confusingRecur(++x);
            System.out.println("pop");
        }
        else
            System.out.println("poooopel");
        return x;
    }

    /**
     * A kind of "brain fuck" with recursion
     * @param x input
     * @return counter
     */
    public static Integer moreConfusingRecur(Integer x) {
        if (x < 7) {
            System.out.println("lolly");
            moreConfusingRecur(++x);
            System.out.println("pop");
            return x;
        }
        else {
            if (x < 10)
                moreConfusingRecur(++x);
            System.out.println("poooopel");
            return x;
        }
    }

    public static void main(String[] args) {
        System.out.println(confusingRecur(5));
        System.out.println(confusingRecur(7));
        System.out.println(moreConfusingRecur(5));
        System.out.println(moreConfusingRecur(7));
    }
}
