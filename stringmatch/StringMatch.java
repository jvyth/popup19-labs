import java.util.*;

/*
 * The purpose of this class is to quickly find all occurrences of one or more patterns in a text.
 *
 * This is done using KMP if we're only searching for one pattern, or
 * Aho-Corasick when we have multiple patterns.
 *
 * @author Carl Nyströmer
 * @author Jakob Vyth
 */
class StringMatch {
    // Maximum number of possible characters
    final static int C = 255;

    /*
     * Use KMP to find all occurrences of pattern in text.
     *
     * @param pattern The pattern to find.
     * @param text The text to search in.
     * @return The indicies in text where the pattern is found.
     */
    public static ArrayList<Integer> find(String pattern, String text) {
        int n = pattern.length();
        // jump[i] keeps track of
        int[] jump = new int[n];

        //For substring of pattern of length i, keep track of the starting index of the suffix, if there is
        //prefix which is also a suffix in the substring.
        for (int i = 1; i < n; ++i)
            int j = jump[i-1]; //Use j to keep counting on previous iterations

            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = jump[j-1]; //Find the previous j which can be used to continue the appending of characters...
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            jump[i] = j;
        }

        int i = 0;
        int j = 0;
        ArrayList<Integer> ans = new ArrayList<Integer>();
        while (i < text.length()) {
            if (pattern.charAt(j) == text.charAt(i)) {
                ++j;
                ++i;
            }

            if (j == n) {
                ans.add(i-j);
                j = jump[j-1];
            } else if (i < text.length() && pattern.charAt(j) != text.charAt(i) ) {
                if (j != 0){
                    j = jump[j-1]; // If we can't match i with j, look at how much we can keep/jump forward.
                } else {
                    ++i; // If we cannot jump and j = 0, we simply increment i. "reset"
                }
            }
        }
        return ans;
    }

    /*
     *  Use Aho-Corasick to find every occurrence of every pattern in text.
     *
     *  @param patterns The patterns to search for.
     *  @param text The text we're searching in.
     *  @return An arraylist containing, for each pattern, an arraylist which holds
     *          the indicies in text where that pattern is found.
     */
    public static ArrayList<ArrayList<Integer>> find(String[] patterns, String text) {

        //Add a bitset for every state when we generate the states.
        ArrayList<BitSet> match = new ArrayList<BitSet>();

        //Add all possible characters
        ArrayList<HashMap<Integer, Integer>> transition = new ArrayList<HashMap<Integer, Integer>>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>(patterns.length);

        /*
        ======================BUILD TRIE==============================
        */
        transition.add(new HashMap<Integer, Integer>());
        match.add(new BitSet());
        for (int i = 0; i < patterns.length; ++i) { // O(k)
            result.add(new ArrayList<Integer>());
            String word = patterns[i];
            // Go through every character in the word
            int currentState = 0;
            for (int j = 0; j < word.length(); ++j) {
                int character = word.charAt(j);
                // If there is no edge from currentstate with that character
                // create a new state and an edge in between
                if (!transition.get(currentState).containsKey(character)){
                    transition.get(currentState).put(character, transition.size());
                    transition.add(new HashMap<Integer, Integer>());
                    match.add(new BitSet());
                }
                currentState = transition.get(currentState).get(character);
            }
            match.get(currentState).set(i);
        }

        /*
        ======================ADD LINKS==============================
        */
        int[] suffixLink = new int[transition.size()];
        Queue<Integer> q = new LinkedList<Integer>();

        //If there's no transition from root with a character, make it a transition to itself.
        for (int character = 0; character < C; ++character) { // O(255)
            if(!transition.get(0).containsKey(character)){
                transition.get(0).put(character, 0);
            } else { //Create suffixlinks from neighbors to root
                int neighToRoot = transition.get(0).get(character);
                suffixLink[neighToRoot] = 0;
                q.add(neighToRoot);
            }
        }

        //Use BFS to build rest of suffixlinks (longest suffixes in a branch which is prefix in other branch)
        //Works with some kind of recursion magic. Each suffix link is based off of previous suffix links.
        //In each state, you set suffix link for next state.
        while (!q.isEmpty()) { // O(k)
            int state = q.poll();
            // get all neighbors from state
            for (int character : transition.get(state).keySet()) {
                // get neighboring state
                int nextState = transition.get(state).get(character);
                // get suffix link
                int fallBackState = suffixLink[state];
                // can we transition to character from fallBackState?
                boolean noProperSuffix = !transition.get(fallBackState).containsKey(character);

                // if we can't, fall back until we can (we can always transition form root) ...
                while (noProperSuffix) {
                    fallBackState = suffixLink[fallBackState];
                    noProperSuffix = !transition.get(fallBackState).containsKey(character);
                }

                // ... and transition
                fallBackState = transition.get(fallBackState).get(character);

                // the suffixLink from nextState will be the transitioned fallBackState.
                suffixLink[nextState] = fallBackState;
                // if some pattern is a substring of some other pattern, we will save that goal states
                // for the substrings in the superstring also.
                match.get(nextState).or(match.get(fallBackState));

                q.add(nextState);
            }
        }

        /*
        ======================FIND PATTERNS==============================
        */
        int currentState = 0;
        for (int i = 0; i < text.length(); ++i) { // O(|text| + |patterns|)
            int character = text.charAt(i);
            if (transition.get(currentState).containsKey(character)) {
                currentState = transition.get(currentState).get(character);
            } else {
                while (!transition.get(currentState).containsKey(character)) {
                    currentState = suffixLink[currentState];
                }
                currentState = transition.get(currentState).get(character);
            }
            if (match.get(currentState).isEmpty()) {
                continue;
            }
            BitSet matching = match.get(currentState);
            for (int word = matching.nextSetBit(0); word >= 0; word = matching.nextSetBit(word+1)) {
                int position = i - patterns[word].length() + 1;
                result.get(word).add(position);
            }
        }

        return result;
    }
}
