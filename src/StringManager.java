import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StringManager {

	
	static Map<String,Integer> mapStringToNum = new HashMap<String,Integer>();
	static Map<String,Integer> mapStringToPos = new HashMap<String,Integer>();
	static ArrayList<String> strs = new ArrayList<>();
	static int[][] wordMatrix;
	
	public static String graphstr;
	
	
	//将字符串规格化
	public String StringFormat(String str) {
		
		str = str.replaceAll("[^a-zA-Z]"," ");
		str = str.replaceAll("\\s{2,}", " ");
		str = str.toLowerCase();
		return str;
	}
	
	public  Map<String,Integer> findEnglishNum(String text){  
        //找出所有的单词  

        String[] textArray = text.split(" ");  
          
        //遍历 记录  
        Map<String, Integer> map = new HashMap<String, Integer>();  
        for (int i = 0; i < textArray.length; i++) {  
            String key = textArray[i];  
            //转为小写  
            String key_l = key.toLowerCase();  
            if(!"".equals(key_l)){  
                Integer num = map.get(key_l);  
                if(num == null || num == 0){  
                    map.put(key_l, 1);  
                }else if(num > 0){  
                    map.put(key_l, num+1);  
                }  
            }  
        }  
        //输出到控制台  
//        System.out.println("各个单词出现的频率为：");  
//        Iterator<String> iter = map.keySet().iterator();  
//        while(iter.hasNext()){  
//            String key = iter.next();  
//            //Integer num = map.get(key);  
//            //System.out.println(key + "\n\t\t" + num + "次\n-------------------");  
//        }  
        
        return map;
    }  
	
	public String CreateDotFormat(String str)
	{

		String[] textArray = str.split(" ");
		
		int pos = 0;
		for (int i = 0; i < textArray.length; i++) {  
            String key = textArray[i];  
            if(!" ".equals(key)){  
                Integer num = mapStringToNum.get(key);  
                if(num == null || num == 0){  
                    mapStringToNum.put(key, 1); 
                    mapStringToPos.put(key, pos);
                    pos = pos+1;
                    strs.add(key);
                }else if(num > 0){  
                    mapStringToNum.put(key, num+1);  
                }  
            }  
        }
		
		int wordNum = mapStringToNum.size();
		int i,j;
		wordMatrix = new int[wordNum][wordNum];
		for(i=0;i<wordNum;i++) {
			for(j=0;j<wordNum;j++) {
				wordMatrix[i][j] = 0;
			}
		}
		int x,y;
		for(i=0;i<textArray.length-1;i++) {
			x = mapStringToPos.get(textArray[i]);
			y = mapStringToPos.get(textArray[i+1]);
			wordMatrix[x][y]= wordMatrix[x][y]+1;
		}
		
//打印有向图的矩阵		
//		for(i=0;i<wordNum;i++) {
//			for(j=0;j<wordNum;j++) {
//				System.out.print(wordMatrix[i][j]+" ");
//			}
//			System.out.println("");
//		}
		
		String DotStr = "";
		for(i=0;i<wordNum;i++) {
			String string = strs.get(i);
			x = mapStringToPos.get(string);
			for(j=0;j<wordNum;j++) {
				if(wordMatrix[x][j]!=0) {
					DotStr = DotStr+string+"->"+strs.get(j)+"[label="+"\""+wordMatrix[x][j]+"\""+"]"+";";						
				}
			}
		}
		
		return DotStr;
	}
	
	
	
	
	
	
	public String queryBridgeWords(String word1, String word2)
	{
		

		ArrayList<String> bridgewords= new ArrayList<>();
		//System.out.println(word1+" "+word2);
		
		if (mapStringToPos.get(word1)==null||mapStringToPos.get(word2)==null)
		{
			if (mapStringToPos.get(word1)==null&&mapStringToPos.get(word2)!=null)
			{
				System.out.println("No "+word1+" in the graph!");
			}
			else if (mapStringToPos.get(word1)!=null&&mapStringToPos.get(word2)==null)
			{
				System.out.println("No "+word2+" in the graph!");
			}
			else
				System.out.println("No "+word1+" and "+word2+" in the graph!");
			return null;
		}
		int x=mapStringToPos.get(word1);
		int y=mapStringToPos.get(word2);
		for (int z=0;z<mapStringToPos.size();z++)
		{
			if (wordMatrix[x][z]!=0&&wordMatrix[z][y]!=0)
			{
				bridgewords.add(strs.get(z));
			}
		}
		if (bridgewords.size()==0)
		{
			System.out.println("No bridge words from "+word1+" to "+ word2+" !");
			return null;
		}
		else if(bridgewords.size()==1)
		{
			System.out.print("The bridge words from "+word1+" to " +word2 +" are:"+bridgewords.get(0));
			return bridgewords.get(0);
		}
		else
		{
			System.out.print("The bridge words from "+word1+" to "+ word2 +" are:"+bridgewords.get(0));
			for (int i=1;i<bridgewords.size()-1;i++)
			{
				System.out.print(","+bridgewords.get(i));
			}
			System.out.print(" and "+bridgewords.get(bridgewords.size()-1));
			System.out.print("\n");
			return bridgewords.get(0);
		}
		
	}
	
	public String generateNewText(String inputText) {
		String outputText="";
		
		
		String[] textArray = inputText.split(" ");
		String bridgeWord;
		int i;
		for(i=0;i<textArray.length-1;i++) {
			bridgeWord = LabOne.sm.queryBridgeWords(textArray[i],textArray[i+1]);
			System.out.println(textArray[i]+"  "+textArray[i+1]);
			outputText = outputText+textArray[i]+" ";
			if(bridgeWord != null) {
				outputText = outputText+bridgeWord+" ";
			}
		}
		outputText = outputText+textArray[i];
		
		
		return outputText;
	}
		
	
	public String randomWalk() {
		
		
		
		
		String result="";
		int n = mapStringToNum.size();

		int[][] myMatrix = new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				myMatrix[i][j] = wordMatrix[i][j];
			}
		}
		int pointX =(int) Math.ceil(Math.random()*n);
		
		while(judgePath(pointX,myMatrix)==0 ||pointX>=n) {
			pointX =(int) (Math.ceil(Math.random()*n));
		}

		int pointY = 0;
		while(judgePath(pointX,myMatrix)!=0) {

			pointY = (int) (Math.ceil(Math.random()*judgePath(pointX,myMatrix)));
			while(pointY==0) {
				pointY = (int) (Math.ceil(Math.random()*judgePath(pointX,myMatrix)));
			}
			for(int i=0;i<n;i++) {
				if(wordMatrix[pointX][i]!=0) {
					pointY= pointY-1;
				}
				if(pointY==0) {
					pointY=i;
					i=n;
				}
			}

			myMatrix[pointX][pointY]=0;
			result = result+strs.get(pointX)+" ";
			pointX = pointY;
		}
		
		result = result+strs.get(pointX);
		
		return result;
		
	}
	
	private int judgePath(int x,int[][] wordMatrix) {
		int path=0;
		int n = mapStringToNum.size();
		for(int i=0;i<n;i++) {
			if(wordMatrix[x][i]!=0) {
				path = path+1;
			}
		}
		
		return path;
		
	}
	


	public void TextToFile(String str) throws IOException {
		File fileText = new File("randomWalk.txt");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(fileText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileWriter.write(str);
		fileWriter.close();
	}
	
	
	private static final int INFINITY = 10000;
	static int[] path;
	static int[] dist;
	//
	public int FindMinDist(int dist[],int collected[])
	{
		int MinV=INFINITY;
		int MinDist = INFINITY;
		//收录
		for (int i=0;i<mapStringToPos.size();i++)
		{
			if (collected[i]==0&&dist[i]<MinDist)
			{
				MinDist=dist[i];
				MinV=i;
			}
		}
		if (MinDist<INFINITY)
			return MinV;
		else
			return INFINITY;
		
	}
	
	public boolean Dijkstra(String word1)
	{
		int collected[]=new int[mapStringToPos.size()];
		dist=new int[mapStringToPos.size()];
		path =new int[mapStringToPos.size()];
		int num1,num2;
		//初始化
		for (int i=0;i<mapStringToPos.size();i++)
		{
			if (wordMatrix[mapStringToPos.get(word1)][i]==0)
			{
				dist[i]=INFINITY;
				path[i]=-1;
			}
			else
			{
				dist[i]=wordMatrix[mapStringToPos.get(word1)][i];
				path[i]=mapStringToPos.get(word1);
			}
			collected[i]=0;
		}
		//收入源点
		dist[mapStringToPos.get(word1)]=0;
		collected[mapStringToPos.get(word1)]=1;
		
		while(true)
		{
			num1=FindMinDist(dist,collected);
			if (num1==INFINITY)
			{
				break;
			}
			collected[num1]=1;
			for (num2=0;num2<mapStringToPos.size();num2++)
			{
				if (collected[num2]==0&&wordMatrix[num1][num2]!=0)
				{
					if (wordMatrix[num1][num2]<0)
						return false;
					if (dist[num1]+wordMatrix[num1][num2]<dist[num2])
					{
						dist[num2]=dist[num1]+wordMatrix[num1][num2];
						path[num2]=num1;
					}
				}
			}
		}
		return true;
		
	}
	
		public String calcShortestPath(String word1, String word2)
		{
			
			if(word2.equals("")) {
				int x = (int) Math.ceil(Math.random()*(strs.size()));
				while(strs.get(x).equals(word1)) {
					x= (int) Math.ceil(Math.random()*(strs.size()));
					//System.out.println(x+"");
				}
				word2 = strs.get(x);
				//System.out.println(word2);
			}
			
			int num1,num2;
			String str1,str2="";
			num1=mapStringToPos.get(word1);
			num2=mapStringToPos.get(word2);
			Dijkstra(word1);
			if (path[num2]==-1)
			{
				System.out.println("不可达");
				return null;
			}
			str1=word2;
			while (path[num2]!=num1)
			{	
				str1=strs.get(path[num2])+"->"+str1;
				num2=path[num2];
			}
			str1=strs.get(path[num2])+"->"+str1;
			System.out.println(str1);
			num1=mapStringToPos.get(word1);
			num2=mapStringToPos.get(word2);				
			str2=word2;
			graphstr=new String(LabOne.DotStr);
			while (path[num2]!=num1)
			{
				str2=strs.get(path[num2])+"->"+str2;
				graphstr=new String(graphstr.replace(str2,str2+"[color="+"\"red\"]"));
				str2=new String(strs.get(path[num2]));
				num2=path[num2];
			}
			str2=strs.get(path[num2])+"->"+str2;
			graphstr=new String(graphstr.replace(str2,str2+"[color="+"\"red\"]"));
			str2.replace(str2,strs.get(path[num2]));
			System.out.println("red test:"+graphstr);
			return str1;
		}

}
