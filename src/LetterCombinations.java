/*
    Task:
    (Link: https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/)

    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the
    number could represent. Return the answer in any order.

    A mapping of digits to letters (just like on the telephone buttons) is given below.
    Note that 1 does not map to any letters.
___________________________________________________________________________________________________________
    Example 1:

    Input: digits = "23"
    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

    Example 2:

    Input: digits = ""
    Output: []

    Example 3:

    Input: digits = "2"
    Output: ["a","b","c"]
*/

import java.util.*;

public class LetterCombinations {
    private static final Map<Character, String> digitToLetters = new HashMap<>();

    static {
        digitToLetters.put('2', "abc");
        digitToLetters.put('3', "def");
        digitToLetters.put('4', "ghi");
        digitToLetters.put('5', "jkl");
        digitToLetters.put('6', "mno");
        digitToLetters.put('7', "pqrs");
        digitToLetters.put('8', "tuv");
        digitToLetters.put('9', "wxyz");
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits == null || digits.isEmpty()) {
            return result;
        }

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair("", 0));

        while (!stack.isEmpty()) {
            Pair current = stack.pop();
            String combination = current.combination;
            int index = current.index;

            if (index == digits.length()) {
                result.add(combination);
                continue;
            }

            char digit = digits.charAt(index);
            String letters = digitToLetters.get(digit);

            for (char letter : letters.toCharArray()) {
                stack.push(new Pair(combination + letter, index + 1));
            }
        }

        return result;
    }

    private static class Pair {
        String combination;
        int index;

        Pair(String combination, int index) {
            this.combination = combination;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println(letterCombinations(scn.nextLine()));
    }
}