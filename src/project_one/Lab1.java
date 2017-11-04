package project_one;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class Lab1 {
    static int[][] E;
    static int[][] D;
    static int[][] path;
    static String[] TxtWordArray;
    static int wordNum = 0;
    static boolean flag = true;
    static final int INFINITY = 10000;
    static StringBuffer preStr = new StringBuffer();
    static StringBuffer pathWay = new StringBuffer();
    static StringBuffer randomPath = new StringBuffer();
    static List<String> wordList = new ArrayList<String>();
    static List<String> edgePairList = new ArrayList<String>();
    static HashMap<String, List<String>> map = new HashMap<String, List<String>>();
    static Pattern p = Pattern.compile("[.,\"\\?!:' ]");

    public static void preDeal() throws IOException {
        int c;
        InputStream fi = new FileInputStream("/Users/summerchaser/Desktop/1.txt");
        preStr = new StringBuffer();
        wordList = new ArrayList<String>();
        map = new HashMap<String, List<String>>();
        edgePairList = new ArrayList<String>();
        
        
        while ((c = fi.read()) != -1) {
            Character m = new Character((char) c);
            if (Character.isLetter(m)) {
                preStr.append(m.toString());
            } else if (p.matcher(m.toString()).matches()) {
                preStr.append(" ");
            }
        }
        // create word array
        TxtWordArray = preStr.toString().toLowerCase().trim().split("\\s+");
       // System.out.print("The Text equal to : ");
        for (String word : TxtWordArray) {
           // System.out.print(word + " ");
            if (!wordList.contains(word)) {
                wordList.add(word);
                wordNum++; // recored word number
            }
        }
        System.out.println();
       // System.out.println("\nThe number of word is ：" + wordNum);
        E = new int[wordNum][wordNum]; // 建立边集
        buildEdge();
        Graph graph = new Graph(wordList, E, wordNum); // 创建图的实例
        showDirectedGraph(graph);
        createBridgeMap();
        fi.close();
    }
    static String queryBridgeWords(String word1, String word2) {
        if (!(wordList.contains(word1) && wordList.contains(word2))) {
            if (wordList.contains(word1)) {
                return "No \""+word2+"\" in the graph!";
            }else if (wordList.contains(word2)) {
                return "No \""+word1+"\" in the graph!";
            }else {
                return "No \""+word1+"\" and \""+word2+"\" in the graph!";
            }
        }else if (wordList.contains(word1) && wordList.contains(word2)) {
            String key = word1 + "#" + word2;
            
            if (!map.containsKey(key)) {
                return "No bridge words from \""+word1+ "\" to \""+word2+"\"!";
            } else {
                StringBuffer result = new StringBuffer("The bridge words from \"" + word1 + "\" to \"" + word2 + "\" is:");
                for (String bridge : map.get(key)) {
                    result.append(bridge + " ");
                }
                return result.toString();
            }
        }else {
            return "input error";
        } 

    }

    protected static void showDirectedGraph(Graph graph) {
       // System.out.println("The graph presented is ：");
        for (int i = 0; i < graph.wordNum; i++) {
            for (int j = 0; j < graph.wordNum; j++) {
                if (E[i][j] != INFINITY) {
                    String word1 = graph.wordList.get(i);
                    String word2 = graph.wordList.get(j);
                    //System.out.printf("Edge: " + word1 + " --> " + word2 + "   Weigth: %d\n", E[i][j]);
                }
            }
        }
    }

    protected static void buildEdge() {
        int preNum, curNum, i, j;
        String pre = "#";
        for (String word : TxtWordArray) {
            if (pre != "#") {
                preNum = wordList.indexOf(pre);
                curNum = wordList.indexOf(word);
                E[preNum][curNum]++;
                pre = word;
            } else {
                pre = word;
            }
        }
        //System.out.println("The graph matrix is ：");
        for (i = 0; i < wordNum; i++) {
            for (j = 0; j < wordNum; j++) {
                if (E[i][j] == 0) {
                    E[i][j] = INFINITY;
                   // System.out.printf("0 ");
                } else {
                    //System.out.printf("%d ", E[i][j]);
                }
            }
           // System.out.println("");
        }
    }

    protected static void createBridgeMap() {
        int i;
        for (i = 0; i < TxtWordArray.length - 2; i++) {
            String key = TxtWordArray[i] + "#" + TxtWordArray[i + 2];
            if (map.containsKey(key)) {
                map.get(key).add(TxtWordArray[i + 1]);
            } else {
                List<String> valueList = new ArrayList<String>();
                valueList.add(TxtWordArray[i + 1]);
                map.put(key, valueList);
            }
        }
    }

    protected static String generateNewText(String inputText) {
        int i;
        String[] TextWord = inputText.toLowerCase().trim().split("\\s+");
        StringBuffer newText = new StringBuffer();
        newText.append(TextWord[0] + " ");
        System.out.println("New text created is ：");
        for (i = 0; i < TextWord.length - 1; i++) {
            String key = TextWord[i] + "#" + TextWord[i + 1];
            if (map.containsKey(key)) {
                String bridge = map.get(key).get(0);
                newText.append(bridge + " " + TextWord[i + 1] + " ");
            } else {
                newText.append(TextWord[i + 1] + " ");
            }
        }
        return newText.toString();
    }

    protected static void getPath(int start, int end) {
        if (path[start][end] == -1) {
            return;
        } else {
            getPath(start, path[start][end]);
            pathWay.append(wordList.get(path[start][end]) + " -> ");
        }
    }

    static String calcShortestPath(String word1, String word2) {
        if (wordList.contains(word1) && wordList.contains(word2)) {
            System.out.println("The path " + word1 + " to " + word2 + " is :");
            int start = wordList.indexOf(word1);
            int end = wordList.indexOf(word2);
            if (D[start][end] != INFINITY) {
                pathWay.append(word1 + " -> ");
                getPath(start, end);
                pathWay.append(word2);
                String py = pathWay.toString();
                pathWay.delete(0, pathWay.length());
                return py;
            } else {
                return "no access";
            }
        } else {
            return "input error";
        }
    }

    static void findPathToOther(String word) {
        int s = wordList.indexOf(word);
        int i;
        for (i = 0; i < wordNum; i++) {
            pathWay.delete(0, pathWay.length());
            if (i != s) {
                String word1 = wordList.get(s);
                String word2 = wordList.get(i);
                System.out.println(calcShortestPath(word1, word2));
            }
        }
    }

    static void floyd() {
        int i, j;
        D = new int[wordNum][wordNum];
        path = new int[wordNum][wordNum];
        for (i = 0; i < wordNum; i++) {
            for (j = 0; j < wordNum; j++) {
                D[i][j] = E[i][j];
                path[i][j] = -1;
            }
        }
        for (int k = 0; k < wordNum; k++) {
            for (i = 0; i < wordNum; i++) {
                for (j = 0; j < wordNum; j++) {
                    if (D[i][k] + D[k][j] < D[i][j]) {
                        D[i][j] = D[i][k] + D[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
    }

//    static boolean isEnd(final int s) {
//        for (int i = 0; i < wordNum; i++) {
//            String edgePair = String.valueOf(s) + "#" + String.valueOf(i);
//            if (E[s][i] != INFINITY && !edgePairList.contains(edgePair)) { // 如果存在边
//                return false;
//            }
//        }
//        return true; // 不存在边，即是尽头
//    }

    static String randomWalk() {
        int ranNum = (int) Math.round(Math.random() * (wordNum - 1));
        String ranWord = wordList.get(ranNum);
        randomPath.append(ranWord);
        System.out.println("System choose -> " + ranWord + "\nRandom walk is ：");
        walkFrom(ranNum);
        String rp = randomPath.toString();
        randomPath.delete(0, randomPath.length());
        return rp;
    }

    static void walkFrom(final int s) {
        for (int i = 0; i < wordNum; i++) {
            if (flag) {
                String edgePair = String.valueOf(s) + "#" + String.valueOf(i);
                if (E[s][i] != INFINITY && !edgePairList.contains(edgePair)) {
                    edgePairList.add(edgePair);
                    randomPath.append(" -> " + wordList.get(i));
                    walkFrom(i);}
//                } else if (isEnd(s)) {
//                    flag = false;
//                    return;
//                }
            }
        }
    }
}
// /Users/summerchaser/Desktop/111.txt
