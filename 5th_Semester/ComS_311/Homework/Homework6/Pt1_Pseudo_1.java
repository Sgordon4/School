public int coinChange(int[] coins, int amount) {    
    
	if (amount < 1) {
	  return 0;
	}

	return coinChange(coins, amount, new int[amount + 1]);
}

private int coinChange(int[] coins, int remainder, int[] dp) {
	if (remainder < 0) {
	  return -1;
	}
	if (remainder == 0) {
	  return 0;
	}

	// We already have an answer cached. Return it.
	if (dp[remainder] != 0) {
	  return dp[remainder];
	}

	int minimum = Integer.MAX_VALUE;
	for (int coin : coins) {
	  int changeResult = coinChange(coins, remainder - coin, dp);

	  if (changeResult >= 0 && changeResult < minimum) {
		minimum = 1 + changeResult;
	  }
	}
	
	dp[remainder] = (minimum == Integer.MAX_VALUE) ? -1 : minimum;

	// Return the sub problem's answer
	return dp[remainder];
}