import java.util.*;

class StringMatch {
    final static int C = 255;

    public static ArrayList<ArrayList<Integer>> find(String[] patterns, String text) {

        //Lägg till ett bitset för varje state när vi generar dem
        ArrayList<BitSet> match = new ArrayList<BitSet>();

        //Lägg till endast de karaktärer som finns
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
            // Går igenom varje character i ett ord, och man börjar på state 0
            int currentState = 0;
            for (int j = 0; j < word.length(); ++j) {
                int character = word.charAt(j);
                // Om edgen med character från currentstate inte finns
                // skapar vi ett nytt state och en edge därimellan
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
                //Create suffixlinks from neighbors to root
            } else {
                int neighToRoot = transition.get(0).get(character);
                suffixLink[neighToRoot] = 0;
                q.add(neighToRoot);
            }
        }

        //Use BFS to build rest of suffixlinks
        while (!q.isEmpty()) { // O(k)
            int state = q.poll();
            for (int character : transition.get(state).keySet()) {
                int nextState = transition.get(state).get(character);
                int fallBackState = suffixLink[state];
                boolean noProperSuffix = !transition.get(fallBackState).containsKey(character);

                while (noProperSuffix) {
                    fallBackState = suffixLink[fallBackState];
                    noProperSuffix = !transition.get(fallBackState).containsKey(character);
                }

                fallBackState = transition.get(fallBackState).get(character);

                suffixLink[nextState] = fallBackState;
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
