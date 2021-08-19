/*
Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. 
For this problem, assume that your function returns 231 − 1 when the division result overflows.

 

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = truncate(3.33333..) = 3.
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = truncate(-2.33333..) = -2.
Example 3:

Input: dividend = 0, divisor = 1
Output: 0
Example 4:

Input: dividend = 1, divisor = 1
Output: 1
 

Constraints:

-231 <= dividend, divisor <= 231 - 1
divisor != 0
*/

class Solution {
    public int divide(int dividend, int divisor) {
        if (Integer.MIN_VALUE == dividend && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        boolean negative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int result = 0;
        while (dividend - divisor >= 0) {
            int shift = 0;
            while (dividend - (divisor << shift << 1 ) >= 0) {
                ++shift;
            }
            
            dividend -= (divisor << shift);
            result += (1 << shift);
        }
        
        return negative ? -result : result;
    }
}


class Solution {
    public int divide(int dividend, int divisor) {
        if (Integer.MIN_VALUE == dividend && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        boolean negative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int result = 0, shift = 31;
        while (shift >= 0) {
            if ((dividend >>> shift) - divisor >= 0) {
                dividend -= (divisor << shift);
                result += (1 << shift);
            }
            --shift;
        }
        
        return negative ? -result : result;
    }
}

// O(N)
public int divide(int dividend, int divisor) {

    // Special case: overflow.
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
        return Integer.MAX_VALUE;
    }

    /* We need to convert both numbers to negatives
     * for the reasons explained above.
     * Also, we count the number of negatives signs. */
    int negatives = 2;
    if (dividend > 0) {
        negatives--;
        dividend = -dividend;
    }
    if (divisor > 0) {
        negatives--;
        divisor = -divisor;
    }

    /* Count how many times the divisor has to be added
     * to get the dividend. This is the quotient. */
    int quotient = 0;
    while (dividend - divisor <= 0) {
        quotient--;
        dividend -= divisor;
    }

    /* If there was originally one negative sign, then
     * the quotient remains negative. Otherwise, switch
     * it to positive. */
    if (negatives != 1) {
        quotient = -quotient;
    }
    return quotient;
}

private static int HALF_INT_MIN = -1073741824;
// O(log(N))
public int divide(int dividend, int divisor) {

    // Special case: overflow.
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
        return Integer.MAX_VALUE;
    }

    /* We need to convert both numbers to negatives.
     * Also, we count the number of negatives signs. */
    int negatives = 2;
    if (dividend > 0) {
        negatives--;
        dividend = -dividend;
    }
    if (divisor > 0) {
        negatives--;
        divisor = -divisor;
    }

    int quotient = 0;
    /* Once the divisor is bigger than the current dividend,
     * we can't fit any more copies of the divisor into it. */
    while (divisor >= dividend) {
        /* We know it'll fit at least once as divivend >= divisor.
         * Note: We use a negative powerOfTwo as it's possible we might have
         * the case divide(INT_MIN, -1). */
        int powerOfTwo = -1;
        int value = divisor;
        /* Check if double the current value is too big. If not, continue doubling.
         * If it is too big, stop doubling and continue with the next step */
        while (value >= HALF_INT_MIN && value + value >= dividend) {
            value += value;
            powerOfTwo += powerOfTwo;
        }
        // We have been able to subtract divisor another powerOfTwo times.
        quotient += powerOfTwo;
        // Remove value so far so that we can continue the process with remainder.
        dividend -= value;
    }

    /* If there was originally one negative sign, then
     * the quotient remains negative. Otherwise, switch
     * it to positive. */
    if (negatives != 1) {
        return -quotient;
    }
    return quotient;
}


private static int HALF_INT_MIN = -1073741824;// -2**30;

public int divide(int dividend, int divisor) {

    // Special case: overflow.
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
        return Integer.MAX_VALUE;
    }

    /* We need to convert both numbers to negatives.
     * Also, we count the number of negatives signs. */
    int negatives = 2;
    if (dividend > 0) {
        negatives--;
        dividend = -dividend;
    }
    if (divisor > 0) {
        negatives--;
        divisor = -divisor;
    }

    ArrayList<Integer> doubles = new ArrayList<>();
    ArrayList<Integer> powersOfTwo = new ArrayList<>();

    /* Nothing too exciting here, we're just making a list of doubles of 1 and
     * the divisor. This is pretty much the same as Approach 2, except we're
     * actually storing the values this time. */
    int powerOfTwo = -1;
    while (divisor >= dividend) {
        doubles.add(divisor);
        powersOfTwo.add(powerOfTwo);
        // Prevent needless overflows from occurring...
        if (divisor < HALF_INT_MIN) {
            break;
        }
        divisor += divisor;
        powerOfTwo += powerOfTwo;
    }

    int quotient = 0;
    /* Go from largest double to smallest, checking if the current double fits.
     * into the remainder of the dividend. */
    for (int i = doubles.size() - 1; i >= 0; i--) {
        if (doubles.get(i) >= dividend) {
            // If it does fit, add the current powerOfTwo to the quotient.
            quotient += powersOfTwo.get(i);
            // Update dividend to take into account the bit we've now removed.
            dividend -= doubles.get(i);
        }
    }

    /* If there was originally one negative sign, then
     * the quotient remains negative. Otherwise, switch
     * it to positive. */
    if (negatives != 1) {
        return -quotient;
    }
    return quotient;
}


private static int HALF_INT_MIN = -1073741824;

public int divide(int dividend, int divisor) {

    // Special case: overflow.
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
        return Integer.MAX_VALUE;
    }

    /* We need to convert both numbers to negatives.
     * Also, we count the number of negatives signs. */
    int negatives = 2;
    if (dividend > 0) {
        negatives--;
        dividend = -dividend;
    }
    if (divisor > 0) {
        negatives--;
        divisor = -divisor;
    }

    /* In the first loop, we simply find the largest double of divisor
     * that fits into the dividend.
     * The >= is because we're working in negatives. In essence, that
     * piece of code is checking that we're still nearer to 0 than we
     * are to INT_MIN. */
    int highestDouble = divisor;
    int highestPowerOfTwo = -1;
    while (highestDouble >= HALF_INT_MIN && dividend <= highestDouble + highestDouble) {
        highestPowerOfTwo += highestPowerOfTwo;
        highestDouble += highestDouble;
    }

    /* In the second loop, we work out which powers of two fit in, by
     * halving highestDouble and highestPowerOfTwo repeatedly.
     * We can do this using bit shifting so that we don't break the
     * rules of the question :-) */
    int quotient = 0;
    while (dividend <= divisor) {
        if (dividend <= highestDouble) {
            quotient += highestPowerOfTwo;
            dividend -= highestDouble;
        }
        /* We know that these are always even, so no need to worry about the
         * annoying "bit-shift-odd-negative-number" case. */
        highestPowerOfTwo >>= 1;
        highestDouble >>= 1;
    }

    /* If there was originally one negative sign, then
     * the quotient remains negative. Otherwise, switch
     * it to positive. */
    if (negatives != 1) {
        return -quotient;
    }
    return quotient;
}

private static int HALF_INT_MIN = -1073741824;

public int divide(int dividend, int divisor) {

    // Special cases: overflow.
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
        return Integer.MAX_VALUE;
    }
    if (dividend == Integer.MIN_VALUE && divisor == 1) {
        return Integer.MIN_VALUE;
    }

    /* We need to convert both numbers to negatives.
     * Also, we count the number of negatives signs. */
    int negatives = 2;
    if (dividend > 0) {
        negatives--;
        dividend = -dividend;
    }
    if (divisor > 0) {
        negatives--;
        divisor = -divisor;
    }

    /* We want to find the largest doubling of the divisor in the negative 32-bit
     * integer range that could fit into the dividend.
     * Note if it would cause an overflow by being less than HALF_INT_MIN,
     * then we just stop as we know double it would not fit into INT_MIN anyway. */
    int maxBit = 0;
    while (divisor >= HALF_INT_MIN && divisor + divisor >= dividend) {
        maxBit += 1;
        divisor += divisor;
    }

    int quotient = 0;
    /* We start from the biggest bit and shift our divisor to the right
     * until we can't shift it any further */
    for (int bit = maxBit; bit >= 0; bit--) {
        /* If the divisor fits into the dividend, then we should set the current
         * bit to 1. We can do this by subtracting a 1 shifted by the appropriate
         * number of bits. */
        if (divisor >= dividend) {
            quotient -= (1 << bit);
            /* Remove the current divisor from the dividend, as we've now
             * considered this part. */
            dividend -= divisor;
        }
        /* Shift the divisor to the right so that it's in the right place
         * for the next positon we're checking at. */
        divisor = (divisor + 1) >> 1;
    }

    /* If there was originally one negative sign, then
     * the quotient remains negative. Otherwise, switch
     * it to positive. */
    if (negatives != 1) {
        quotient = -quotient;
    }
    return quotient;
}