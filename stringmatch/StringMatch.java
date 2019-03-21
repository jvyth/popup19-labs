import java.util.*;

class StringMatch {
    final static int C = 95;

    public static ArrayList<ArrayList<Integer>> find(String[] patterns, String text) {
        int maxStates = 1;
        for (String s : patterns) {
            maxStates += s.length();
        }

        BitSet[] match = new BitSet[maxStates];
        for (int i = 0; i < maxStates; ++i) {
            match[i] = new BitSet(patterns.length);
        }

        int[][] transition = new int[maxStates][C];
        for (int[] arr : transition) {
            Arrays.fill(arr, -1);
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>(patterns.length);

        int states = 1;
        for (int i = 0; i < patterns.length; ++i) {
            result.add(new ArrayList<Integer>());

            String word = patterns[i];
            int currentState = 0;
            for (int j = 0; j < word.length(); ++j) {
                int character = word.charAt(j) - ' ';

                if (transition[currentState][character] == -1) {
                    transition[currentState][character] = states;
                    states += 1;
                }
                currentState = transition[currentState][character];
            }

            match[currentState].set(i);
        }

        int[] suffixLink = new int[maxStates];
        Arrays.fill(suffixLink, -1);
        Queue<Integer> q = new LinkedList<Integer>();
        for (int character = 0; character < C; ++character) {
            int state = transition[0][character];
            if (state == -1) {
                transition[0][character] = 0;
            } else {
                suffixLink[state] = 0;
                q.add(state);
            }
        }

        while (!q.isEmpty()) {
            int state = q.poll();

            for (int character = 0; character < C; ++character) {
                int nextState = transition[state][character];
                if (nextState != -1) {
                    int fallBackState = suffixLink[state];

                    while (transition[fallBackState][character] == -1) {
                        fallBackState = suffixLink[fallBackState];
                    }

                    fallBackState = transition[fallBackState][character];
                    suffixLink[nextState] = fallBackState;

                    match[nextState].or(match[fallBackState]);

                    q.add(nextState);
                }
            }
        }

        int currentState = 0;

        for (int i = 0; i < text.length(); ++i) {
            int character = text.charAt(i) - ' ';
            if (transition[currentState][character] != -1) {
                currentState = transition[currentState][character];
            } else {
                while (transition[currentState][character] == -1) {
                    currentState = suffixLink[currentState];
                }
                currentState = transition[currentState][character];
            }

            if (match[currentState].isEmpty()) {
                continue;
            }

            for (int j = 0; j < patterns.length; ++j) {
                BitSet word = new BitSet(patterns.length);
                word.set(j);
                if (match[currentState].intersects(word)) {
                    int position = i - patterns[j].length() + 1;
                    result.get(j).add(position);
                }
            }
        }

        return result;
    }
}
