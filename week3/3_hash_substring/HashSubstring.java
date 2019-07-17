import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Random;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;
    private static long p = 1000000007L;
    private static long x = Math.max(1, Math.min(p - 1, new Random().nextLong())); // 乱数生成はよりよいやり方があるはず
    private static Map<Character, Integer> charIntMap = new HashMap<Character, Integer>(){{
        put('a', 0);
        put('b', 1);
        put('c', 2);
        put('d', 3);
        put('e', 4);
        put('f', 5);
        put('g', 6);
        put('h', 7);
        put('i', 8);
        put('j', 9);
        put('k', 10);
        put('l', 11);
        put('m', 12);
        put('n', 13);
        put('o', 14);
        put('p', 15);
        put('q', 16);
        put('r', 17);
        put('s', 18);
        put('t', 19);
        put('u', 20);
        put('v', 21);
        put('w', 22);
        put('x', 23);
        put('y', 24);
        put('z', 25);
        put('A', 26);
        put('B', 27);
        put('C', 28);
        put('D', 29);
        put('E', 30);
        put('F', 31);
        put('G', 32);
        put('H', 33);
        put('I', 34);
        put('J', 35);
        put('K', 36);
        put('L', 37);
        put('M', 38);
        put('N', 39);
        put('O', 40);
        put('P', 41);
        put('Q', 42);
        put('R', 43);
        put('S', 44);
        put('T', 45);
        put('U', 46);
        put('V', 47);
        put('W', 48);
        put('X', 49);
        put('Y', 50);
        put('Z', 51);
    }};

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrencesRabinKarp(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();
        for (int i = 0; i + m <= n; ++i) {
            boolean equal = true;
            for (int j = 0; j < m; ++j) {
                if (s.charAt(j) != t.charAt(i + j)) {
                    equal = false;
                    break;
                }
            }
            if (equal)
                occurrences.add(i);
        }
        return occurrences;
    }

    private static List<Integer> getOccurrencesRabinKarp(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> results = new ArrayList<>();
        long pHash = getPolyHash(s, p, x);
        for (int i = 0; i <= n - m; i++) {
            String substr = t.substring(i, i + m);
            long tHash = getPolyHash(substr, p, x);
            if (pHash != tHash) continue;
            if (AreEqual(s, substr)) results.add(i);
        }
        return results;
    }

    private static boolean AreEqual(String s, String t) {
        if (s.length() != t.length()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != t.charAt(i)) return false;
        }
        return true;
    }

    private static long getPolyHash(String s, long p, long x) {
        long hash = 0L;
        for (int i = s.length() - 1; i >= 0; i--) {
            hash = (hash * x + charIntMap.get(s.charAt(i))) % p;
        }
        return hash;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

