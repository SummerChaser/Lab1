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

    public static void main(String[] args) {
     
        ReadFileController.readFile();
        E = new int[wordNum][wordNum]; // 建立边集
        Graph graph = new Graph(wordList, E, wordNum); // 创建图的实例
        CreateAndShowGraph(graph);
        showDirectedGraph(graph);
        createBridgeWordMap(TxtWordArray);
        queryBridgeWord(word1, word2);
        CreateNewText(originText);
        FindMinPath(word1,word2);
        RandomWalk(word);
    }

    

}