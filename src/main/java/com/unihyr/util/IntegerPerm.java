package com.unihyr.util;

/**
 * IntegerPerm is a reversible keyed permutation of the integers.
 * This class is not cryptographically secure as the F function
 * is too simple and there are not enough rounds.
 *
 * @author Martin Ross
 */
public final class IntegerPerm {

    //////////////////
    // Private Data //
    //////////////////

    /** Non-zero default key, from www.random.org */
    private final static int DEFAULT_KEY = 0x6CFB18E2;

    private final static int LOW_16_MASK = 0xFFFF;
    private final static int HALF_SHIFT = 16;
    private final static int NUM_ROUNDS = 4;

    /** Permutation key */
    private int mKey;

    /** Round key schedule */
    private static int[] mRoundKeys = new int[NUM_ROUNDS];

    //////////////////
    // Constructors //
    //////////////////
    public IntegerPerm() { this(DEFAULT_KEY); }

    public IntegerPerm(int key) { setKey(key); }

    ////////////////////
    // Public Methods //
    ////////////////////
    /** Sets a new value for the key and key schedule. */
    public void setKey(int newKey) {
        assert (NUM_ROUNDS == 4) : "NUM_ROUNDS is not 4";
        mKey = newKey;

        mRoundKeys[0] = mKey & LOW_16_MASK;
        mRoundKeys[1] = ~(mKey & LOW_16_MASK);
        mRoundKeys[2] = mKey >>> HALF_SHIFT;
        mRoundKeys[3] = ~(mKey >>> HALF_SHIFT);
    } // end setKey()

    /** Returns the current value of the key. */
    public int getKey() { return mKey; }

    /**
     * Calculates the enciphered (i.e. permuted) value of the given integer
     * under the current key.
     *
     * @param plain the integer to encipher.
     *
     * @return the enciphered (permuted) value.
     */
    public static int encipher(int plain) {
        // 1 Split into two halves.
        int rhs = plain & LOW_16_MASK;
        int lhs = plain >>> HALF_SHIFT;

        // 2 Do NUM_ROUNDS simple Feistel rounds.
        for (int i = 0; i < NUM_ROUNDS; ++i) {
            if (i > 0) {
                // Swap lhs <-> rhs
                final int temp = lhs;
                lhs = rhs;
                rhs = temp;
            } // end if
            // Apply Feistel round function F().
            rhs ^= F(lhs, i);
        } // end for

        // 3 Recombine the two halves and return.
        return (lhs << HALF_SHIFT) + (rhs & LOW_16_MASK);
    } // end encipher()


    /**
     * Calculates the deciphered (i.e. inverse permuted) value of the given
     * integer under the current key.
     *
     * @param cypher the integer to decipher.
     *
     * @return the deciphered (inverse permuted) value.
     */
    public static int decipher(int cypher) {
        // 1 Split into two halves.
        int rhs = cypher & LOW_16_MASK;
        int lhs = cypher >>> HALF_SHIFT;

        // 2 Do NUM_ROUNDS simple Feistel rounds.
        for (int i = 0; i < NUM_ROUNDS; ++i) {
            if (i > 0) {
                // Swap lhs <-> rhs
                final int temp = lhs;
                lhs = rhs;
                rhs = temp;
            } // end if
            // Apply Feistel round function F().
            rhs ^= F(lhs, NUM_ROUNDS - 1 - i);
        } // end for

        // 4 Recombine the two halves and return.
        return (lhs << HALF_SHIFT) + (rhs & LOW_16_MASK);
    } // end decipher()

    /////////////////////
    // Private Methods //
    /////////////////////

    // The F function for the Feistel rounds.
    private static int F(int num, int round) {
        // XOR with round key.
        num ^= mRoundKeys[round];
        // Square, then XOR the high and low parts.
        num *= num;
        return (num >>> HALF_SHIFT) ^ (num & LOW_16_MASK);
    } // end F()

} // end class IntegerPerm