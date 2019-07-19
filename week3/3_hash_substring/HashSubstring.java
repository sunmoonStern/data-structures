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
        long[] tHashes = preComputeHashes(t, m, p, x);
        for (int i = 0; i <= n - m; i++) {
            if (pHash != tHashes[i]) continue;
            if (AreEqual(s, t.substring(i, i + m))) results.add(i);
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
            hash = (hash * x + s.charAt(i)) % p;
        }
        return hash;
    }

    private static long[] preComputeHashes(String t, int m, long p, long x) {
        String s = t.substring(t.length() - m, t.length());
        long[] hashes = new long[t.length() - m + 1];
        hashes[t.length() - m] = getPolyHash(s, p, x);
        long y = 1;
        for (int i = 1; i <= m; i++) {
            y = (y * x) % p;
        }
        for (int i = t.length() - m - 1; i >= 0; i--) {
            hashes[i] = ((x * hashes[i + 1] + t.charAt(i) - y * t.charAt(i + m)) % p + p) % p;
        }
        return hashes;
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

