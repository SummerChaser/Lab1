package project_one;

import java.util.List;

public class Graph{
	int wordNum;//顶点数
	List<String> wordList; //顶点集
	int[][] E; //边集

	public Graph(List<String> wordList,int[][] E,int wordNum){
		this.wordList = wordList;
		this.E = E;
		this.wordNum = wordNum;
	}
	

}