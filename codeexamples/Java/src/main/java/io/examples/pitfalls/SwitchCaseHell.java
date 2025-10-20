package io.examples.pitfalls;

import static io.examples.pitfalls.SwitchCaseHell.ExecRules.*;
import static io.examples.pitfalls.SwitchCaseHell.ExecRules.SIMPLE;
import static io.examples.pitfalls.SwitchCaseHell.ExecRules.TRIPLE;

public class SwitchCaseHell {

    private SwitchCaseHell() {

    }

    /**
     * In this function the break statements are set wild or never. That kind of
     * coding style leads to the fact that especially beginners cannot change the code so that all things work again.
     * There is no need to write such irritating code
     * Except the FALLTHROUGH: case that is no good style but in most cases acceptable.
     * @param rule the switch case criteria
     * @return the result for the selected case
     */
    public static Integer pureOldSwitchCaseWithTraps(ExecRules rule) {
        Integer x = 8;
        Integer y = 12;
        Integer result = 0;
        switch (rule) {
            case SIMPLE:
                result = x +3;
            case FALLTHROUGH:
                y *=9;
                result += 2 * y;
            case DOUBLE:
                result *= 2;
                result += y;
                break;
            case TRIPLE:
                result += 7;
            default:
                result += 3;
        }

        return result;
    }

    /**
     * In this function the break statements are set correctly and strict.
     * Except the FALLTHROUGH: case that is no good style but in most cases acceptable.
     * @param rule the switch case criteria
     * @return the result for the selected case
     */
    public static Integer pureOldSwitchCase(ExecRules rule) {
        Integer x = 8;
        Integer y = 12;
        Integer result = 0;
        switch (rule) {
            case SIMPLE:
                result = x +3;
                break;
            case FALLTHROUGH: // this is seen as ok but no good style
                y *=9;
                result += 2 * y;  /*as you see here the break is missing
                the consequence is that the execution falls through until
                the next break is set here before case TRIPLE: */
            case DOUBLE:
                result += y;
                break;  /*break sets the continuation to the statement after the switch actively*/
            case TRIPLE:
                result += 7;
                break;
            default:
                result += 3;
        }

        return result;
    }

    public static Integer upgradedOldSwitchCaseWithTraps(ExecRules rule) {
        Integer x = 8;
        Integer y = 12;
        Integer result = 0;
        return switch (rule) {
            case SIMPLE:
                yield x +3;
            case FALLTHROUGH: // this is seen as ok but no good style
                y *=9;
                result += 2 * y;
            case DOUBLE:
                result *= 2;
                yield result + y;
            case TRIPLE:
                yield result + 7;
            default:
                yield result + 3;
        };

    }

    public static Integer dummy (Integer x) {
        if (x < 7) {
            System.out.println("lolly");
            dummy (++x);
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
    public static Integer dummy2 (Integer x) {
        if (x < 7) {
            System.out.println("lolly");
            dummy2 (++x);
            System.out.println("pop");
            return x;
        }
        else {
            if (x < 10)
                dummy2(++x);
            System.out.println("poooopel");
            return x;
        }
    }


    public static Integer upgradedOldSwitchCase(ExecRules rule) {
        Integer x = 8;
        Integer y = 12;
        Integer result = 0;
        return switch (rule) {
            case SIMPLE:
                yield x +3;
            case FALLTHROUGH:
                yield y * 9;
            case DOUBLE:
                yield result + y;
                /*yield result = result + y; marked as unreachable */
            case TRIPLE:
                yield result + 7;
            default:
                yield result + 3;
        };
    }


    public static Integer newSwitchCaseExpr(ExecRules rule) {
        Integer x = 8;
        Integer y = 12;
        Integer result = 0;
        return switch (rule) {
            case SIMPLE ->
                    x + 3; /* es wird punktuell genau dieses Statement ausgeführt */
            case FALLTHROUGH ->
                    y * 9;
            case DOUBLE -> /* es wird punktuell genau dieses Statement ausgeführt */
                    result + y;
            case TRIPLE -> /* es wird punktuell genau dieses Statement ausgeführt */
                    result + 7;
            default -> /* es wird punktuell genau dieses Statement ausgeführt */
                    result + 3;
        };
    }

    public enum ExecRules {
        NONE,
        DOUBLE,
        SIMPLE,
        TRIPLE,
        FALLTHROUGH,;
    }

    public static void main(String [] args) {
        System.out.println("Normal Simple ???: " + pureOldSwitchCase(SIMPLE));
        System.out.println("Normal Triple ???: " + pureOldSwitchCase(TRIPLE));
        System.out.println("Normal Double ???: " + pureOldSwitchCase(DOUBLE));
        System.out.println("Normal Fall through ???: " + pureOldSwitchCase(FALLTHROUGH));
        System.out.println("Normal default ???: " + pureOldSwitchCase(NONE));

        System.out.println("With trap Simple ???: " + pureOldSwitchCaseWithTraps(SIMPLE));
        System.out.println("With trap Triple ???: " + pureOldSwitchCaseWithTraps(TRIPLE));
        System.out.println("With trap  Double ???: " + pureOldSwitchCaseWithTraps(DOUBLE));
        System.out.println("With trap  Fall through ???: " + pureOldSwitchCaseWithTraps(FALLTHROUGH));
        System.out.println("With trap  default ???: " + pureOldSwitchCaseWithTraps(NONE));

        System.out.println("Upgraded Simple ???: " + upgradedOldSwitchCase(SIMPLE));
        System.out.println("Upgraded Triple ???: " + upgradedOldSwitchCase(TRIPLE));
        System.out.println("Upgraded Double ???: " + upgradedOldSwitchCase(DOUBLE));
        System.out.println("Upgraded Fall through ???: " + upgradedOldSwitchCase(FALLTHROUGH));
        System.out.println("Upgraded default ???: " + upgradedOldSwitchCase(NONE));

        System.out.println("Upgraded Simple with traps???: " + upgradedOldSwitchCaseWithTraps(SIMPLE));
        System.out.println("Upgraded Triple with traps ???: " + upgradedOldSwitchCaseWithTraps(TRIPLE));
        System.out.println("Upgraded Double with traps ???: " + upgradedOldSwitchCaseWithTraps(DOUBLE));
        System.out.println("Upgraded Fall through really fall ???: " + upgradedOldSwitchCaseWithTraps(FALLTHROUGH));
        System.out.println("Upgraded default with traps ???: " + upgradedOldSwitchCaseWithTraps(NONE));


        System.out.println("new Simple Expr ???: " + newSwitchCaseExpr(SIMPLE));
        System.out.println("new Triple Expr ???: " + newSwitchCaseExpr(TRIPLE));
        System.out.println("new Double Expr: " + newSwitchCaseExpr(DOUBLE));
        System.out.println("new Fall through Expr ???: " + newSwitchCaseExpr(FALLTHROUGH));
        System.out.println("new default ??? Expr: " + newSwitchCaseExpr(NONE));
        System.out.println(dummy(5));
        System.out.println(dummy(7));
        System.out.println(dummy2(5));
        System.out.println(dummy2(7));
    }
}
