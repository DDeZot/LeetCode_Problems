/*
    Task:
    (Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/)

    You are given an integer array prices where prices[i] is the price of a given stock on the ith day,
    and an integer k.

    Find the maximum profit you can achieve. You may complete at most k transactions:
    i.e. you may buy at most k times and sell at most k times.

    Note: You may not engage in multiple transactions simultaneously
    (i.e., you must sell the stock before you buy again).
____________________________________________________________________________________________________________

    Example 1:

    Input: k = 2, prices = [2,4,1]
    Output: 2
    Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

    Example 2:

    Input: k = 2, prices = [3,2,6,5,0,3]
    Output: 7
    Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
    Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BuyAndSell {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int k = Integer.parseInt(scn.nextLine());
        String str = scn.nextLine();
        int[] prices = Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(topKBestDeals(findAllDeals(prices), k));
    }

    private static int topKBestDeals(List<Integer> deals, int k){
        if(deals == null)
            return -1;

        int n = deals.size();

        if (k >= n / 2) {
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (deals.get(i) > deals.get(i - 1)) {
                    maxProfit += deals.get(i) - deals.get(i - 1);
                }
            }
            return maxProfit;
        }

        int[][] bestDeals = new int[k + 1][n];

        for (int i = 1; i <= k; i++) {
            int maxDiff = -deals.getFirst();
            for (int j = 1; j < n; j++) {
                bestDeals[i][j] = Math.max(bestDeals[i][j - 1], deals.get(j) + maxDiff);
                maxDiff = Math.max(maxDiff, bestDeals[i - 1][j] - deals.get(j));
            }
        }

        return bestDeals[k][n - 1];
    }

    private static List<Integer> findAllDeals(int[] prices) {
        List<Integer> deals = new ArrayList<>();
        int localBottom = -1;

        if(prices.length == 0 || prices.length == 1)
            return null;

        if(prices[1] > prices[0])
            localBottom = prices[0];

        for(int i = 1; i < prices.length - 1; i++){
            if(prices[i-1] >= prices[i] && prices[i] < prices[i+1])
                localBottom = prices[i];


            if(localBottom != -1 && prices[i-1] <= prices[i] && prices[i] > prices[i+1]) {
                deals.add(localBottom);
                deals.add(prices[i]);
                localBottom = 0;
            }
        }

        if(prices[prices.length-1] > localBottom && localBottom != -1) {
            deals.add(localBottom);
            deals.add(prices[prices.length - 1]);
        }
        return deals;
    }
}

