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

    /**
     * Here one fallthrough takes place which is no clean coding since each branch should deliver
     * one defined return via yield
     * @param rule the switch value
     * @return the result of the switch
     */
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


    /**
     * Here we see the 'new' keyword yield with which a result is given back and which also, like break, sets the continuation
     * to go on after the switch. In this case here each branch ends with a yield so we have a clear and clean logic.
     * @param rule the switch parameter
     * @return the switch result
     */
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


    /**
     * Here we see a switch EXPRESSION instead of a switch statement. All continuations are implicitly set
     * @param rule the switch parameter
     * @return the switch expression return
     */
    public static Integer newSwitchCaseExpr(ExecRules rule) {
        Integer x = 8;
        Integer y = 12;
        Integer result = 0;
        return switch (rule) {
            case SIMPLE ->
                    x + 3; /*only THIS statement is executed and produces a  defined result */
            case FALLTHROUGH ->
                    y * 9;
            case DOUBLE -> /*only THIS statement is executed and produces a  defined result */
                    result + y;
            case TRIPLE -> /*only THIS statement is executed and produces a  defined result */
                    result + 7;
            default -> /*only THIS statement is executed and produces a  defined result */
                    result + 3;
        };
    }


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

    public static void main(String [] args) {
        StringBuffer buffer = new StringBuffer()
                .append("Normal Simple: " + pureOldSwitchCase(SIMPLE))
                .append(" ; ").append("Normal Triple: " + pureOldSwitchCase(TRIPLE))
                .append(" ; ").append("Normal Double: " + pureOldSwitchCase(DOUBLE))
                .append(" ; ").append("Normal Fall through: " + pureOldSwitchCase(FALLTHROUGH))
                .append(" ; ").append("Normal default: " + pureOldSwitchCase(NONE))
                .append("\n\n")
                .append("With trap Simple: " + pureOldSwitchCaseWithTraps(SIMPLE))
                .append(" ; ").append("With trap Triple: " + pureOldSwitchCaseWithTraps(TRIPLE))
                .append(" ; ").append("With trap  Double: " + pureOldSwitchCaseWithTraps(DOUBLE))
                .append(" ; ").append("With trap  Fall through: " + pureOldSwitchCaseWithTraps(FALLTHROUGH))
                .append(" ; ").append("With trap  default: " + pureOldSwitchCaseWithTraps(NONE))
                .append("\n\n")
                .append("Upgraded Simple: " + upgradedOldSwitchCase(SIMPLE))
                .append(" ; ").append("Upgraded Triple: " + upgradedOldSwitchCase(TRIPLE))
                .append(" ; ").append("Upgraded Double: " + upgradedOldSwitchCase(DOUBLE))
                .append(" ; ").append("Upgraded Fall through: " + upgradedOldSwitchCase(FALLTHROUGH))
                .append(" ; ").append("Upgraded default: " + upgradedOldSwitchCase(NONE))
                .append("\n\n")
                .append("Upgraded Simple with traps: " + upgradedOldSwitchCaseWithTraps(SIMPLE))
                .append(" ; ").append("Upgraded Triple with traps: " + upgradedOldSwitchCaseWithTraps(TRIPLE))
                .append(" ; ").append("Upgraded Double with traps: " + upgradedOldSwitchCaseWithTraps(DOUBLE))
                .append(" ; ").append("Upgraded Fall through really fall: " + upgradedOldSwitchCaseWithTraps(FALLTHROUGH))
                .append(" ; ").append("Upgraded default with traps: " + upgradedOldSwitchCaseWithTraps(NONE))
                .append("\n\n")
                .append("new Simple Expr: " + newSwitchCaseExpr(SIMPLE))
                .append(" ; ").append("new Triple Expr: " + newSwitchCaseExpr(TRIPLE))
                .append(" ; ").append("new Double Expr: " + newSwitchCaseExpr(DOUBLE))
                .append(" ; ").append("new Fall through Expr: " + newSwitchCaseExpr(FALLTHROUGH))
                .append(" ; ").append("new default Expr: " + newSwitchCaseExpr(NONE))
                .append("\n\n");
        System.out.println(buffer);
        System.out.println(confusingRecur(5));
        System.out.println(confusingRecur(7));
        System.out.println(moreConfusingRecur(5));
        System.out.println(moreConfusingRecur(7));
    }

    public enum ExecRules {
        NONE,
        DOUBLE,
        SIMPLE,
        TRIPLE,
        FALLTHROUGH,;
    }
}
