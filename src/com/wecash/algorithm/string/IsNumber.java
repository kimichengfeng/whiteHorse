package com.wecash.algorithm.string;

import java.util.HashMap;
import java.util.Map;

/**
 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。

 */
public class IsNumber {

    /**
     * 本题使用有限状态自动机。根据字符类型和合法数值的特点，先定义状态，再画出状态转移图，最后编写代码即可。
     * @param s
     * @return
     */
//    public boolean isNumber(String s) {
//        Map[] states = {
//                new HashMap<>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
//                new HashMap<>() {{ put('d', 2); put('.', 4); }},                           // 1.
//                new HashMap<>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
//                new HashMap<>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
//                new HashMap<>() {{ put('d', 3); }},                                        // 4.
//                new HashMap<>() {{ put('s', 6); put('d', 7); }},                           // 5.
//                new HashMap<>() {{ put('d', 7); }},                                        // 6.
//                new HashMap<>() {{ put('d', 7); put(' ', 8); }},                           // 7.
//                new HashMap<>() {{ put(' ', 8); }}                                         // 8.
//        };
//        int p = 0;
//        char t;
//        for(char c : s.toCharArray()) {
//            if(c >= '0' && c <= '9') t = 'd';
//            else if(c == '+' || c == '-') t = 's';
//            else if(c == 'e' || c == 'E') t = 'e';
//            else if(c == '.' || c == ' ') t = c;
//            else t = '?';
//            if(!states[p].containsKey(t)) return false;
//            p = (int)states[p].get(t);
//        }
//        return p == 2 || p == 3 || p == 7 || p == 8;
//    }
//    class Solution {
//        public boolean isNumber(String s) {
//            Map<State, Map<CharType, State>> transfer = new HashMap<State, Map<CharType, State>>();
//            Map<CharType, State> initialMap = new HashMap<CharType, State>() {{
//                put(CharType.CHAR_SPACE, State.STATE_INITIAL);
//                put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
//                put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
//                put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
//            }};
//            transfer.put(State.STATE_INITIAL, initialMap);
//            Map<CharType, State> intSignMap = new HashMap<CharType, State>() {{
//                put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
//                put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
//            }};
//            transfer.put(State.STATE_INT_SIGN, intSignMap);
//            Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
//                put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
//                put(CharType.CHAR_EXP, State.STATE_EXP);
//                put(CharType.CHAR_POINT, State.STATE_POINT);
//                put(CharType.CHAR_SPACE, State.STATE_END);
//            }};
//            transfer.put(State.STATE_INTEGER, integerMap);
//            Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
//                put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
//                put(CharType.CHAR_EXP, State.STATE_EXP);
//                put(CharType.CHAR_SPACE, State.STATE_END);
//            }};
//            transfer.put(State.STATE_POINT, pointMap);
//            Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
//                put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
//            }};
//            transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);
//            Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
//                put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
//                put(CharType.CHAR_EXP, State.STATE_EXP);
//                put(CharType.CHAR_SPACE, State.STATE_END);
//            }};
//            transfer.put(State.STATE_FRACTION, fractionMap);
//            Map<CharType, State> expMap = new HashMap<CharType, State>() {{
//                put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
//                put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
//            }};
//            transfer.put(State.STATE_EXP, expMap);
//            Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
//                put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
//            }};
//            transfer.put(State.STATE_EXP_SIGN, expSignMap);
//            Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
//                put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
//                put(CharType.CHAR_SPACE, State.STATE_END);
//            }};
//            transfer.put(State.STATE_EXP_NUMBER, expNumberMap);
//            Map<CharType, State> endMap = new HashMap<CharType, State>() {{
//                put(CharType.CHAR_SPACE, State.STATE_END);
//            }};
//            transfer.put(State.STATE_END, endMap);
//
//            int length = s.length();
//            State state = State.STATE_INITIAL;
//
//            for (int i = 0; i < length; i++) {
//                CharType type = toCharType(s.charAt(i));
//                if (!transfer.get(state).containsKey(type)) {
//                    return false;
//                } else {
//                    state = transfer.get(state).get(type);
//                }
//            }
//            return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
//        }
//
//        public CharType toCharType(char ch) {
//            if (ch >= '0' && ch <= '9') {
//                return CharType.CHAR_NUMBER;
//            } else if (ch == 'e' || ch == 'E') {
//                return CharType.CHAR_EXP;
//            } else if (ch == '.') {
//                return CharType.CHAR_POINT;
//            } else if (ch == '+' || ch == '-') {
//                return CharType.CHAR_SIGN;
//            } else if (ch == ' ') {
//                return CharType.CHAR_SPACE;
//            } else {
//                return CharType.CHAR_ILLEGAL;
//            }
//        }
//
//        enum State {
//            STATE_INITIAL,
//            STATE_INT_SIGN,
//            STATE_INTEGER,
//            STATE_POINT,
//            STATE_POINT_WITHOUT_INT,
//            STATE_FRACTION,
//            STATE_EXP,
//            STATE_EXP_SIGN,
//            STATE_EXP_NUMBER,
//            STATE_END,
//        }
//
//        enum CharType {
//            CHAR_NUMBER,
//            CHAR_EXP,
//            CHAR_POINT,
//            CHAR_SIGN,
//            CHAR_SPACE,
//            CHAR_ILLEGAL,
//        }
//    }

}
