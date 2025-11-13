package io.calculator.backend.util;

import java.util.regex.Pattern;

import static io.calculator.backend.util.NumParseTok.Token.TYPE.*;
import static java.util.regex.Pattern.compile;

public class NumParseTok {

    public static final String INTNUM_REGEX = "\\d+";
    public static final String DECIMAL_REGEX = "\\d+,*\\d*";
    public static final String BLANK_REGEX = "\\d[\\t ]*";
    public static final String NUMBER_REGEX = "[" + INTNUM_REGEX + DECIMAL_REGEX + "]";

    public enum Token {
        PLUS(0x01, compileMe(buildOperand(NUMBER_REGEX, "+", NUMBER_REGEX)), OP),
        MINUS(0x02, compileMe(buildOperand(NUMBER_REGEX, "-", NUMBER_REGEX)), OP),
        DIVIDE(0x04, compileMe(buildOperand(NUMBER_REGEX, "/", NUMBER_REGEX)), OP),
        MODULO(0x04, compileMe(buildOperand(NUMBER_REGEX, "%", NUMBER_REGEX)), OP),
        MULTIPLY(0x08, compileMe(buildOperand(NUMBER_REGEX, "*", NUMBER_REGEX)), OP),
        POWER(0x10, compileMe(buildOperand(INTNUM_REGEX, "**", INTNUM_REGEX)), OP),
        SQRT(0x11, compileMe(buildFunTwoParms("sqrt", INTNUM_REGEX, INTNUM_REGEX)), FUN),
        BRACKET(0x12, compileMe(BLANK_REGEX + "(" + BLANK_REGEX), LB),
        RBRACKET(0x13, compileMe(BLANK_REGEX + ")" + BLANK_REGEX), RB),
        ANUMBER(0x15, compileMe(NUMBER_REGEX), NUM),
        ;

        private final int tokNum;
        private final Pattern regExPattern;
        private final TYPE tokType;

        Token(int tokNum, Pattern regExPattern, TYPE tokType) {
            this.tokNum = tokNum;
            this.regExPattern = regExPattern;
            this.tokType = tokType;
        }

        public int getTokNum() {
            return this.tokNum;
        }

        public Pattern getRegEx() {
            return this.regExPattern;
        }

        public TYPE getTokType() {
            return this.tokType;
        }

        private static Pattern compileMe(String regEx) {
            try {
                return compile(regEx, Pattern.MULTILINE | Pattern.UNIX_LINES);
            } catch (Throwable t) {
                System.err.println(t.getMessage());
                throw new IllegalStateException("something went wrong", t);
            }
        }

        private static String buildOperand(String operand_0, String operator, String operand_1) {
            return BLANK_REGEX + operand_0 + BLANK_REGEX + operator + BLANK_REGEX + operand_1;
        }

        private static String buildFunTwoParms(String funName, String parmOne, String parmTwo) {
            return BLANK_REGEX + funName + BLANK_REGEX + "(" + BLANK_REGEX
                    + parmOne + BLANK_REGEX + "," + BLANK_REGEX + parmTwo + ")" + BLANK_REGEX;
        }

        private static String buildFunOneParm(String funName, String parameter) {
            return BLANK_REGEX + funName + BLANK_REGEX + "(" + BLANK_REGEX
                    + parameter + BLANK_REGEX + ")" + BLANK_REGEX;
        }

        @Override
        public String toString() {
            return "Token{" +
                    "tokNum=" + this.tokNum +
                    ", regEx='" + this.regExPattern + '\'' +
                    ", tokType=" + this.tokType +
                    '}';
        }

        public enum TYPE {
            OP,
            LB,
            RB,
            NUM,
            FUN,
        }
    }

    public NumParseTok() {
    }
}
