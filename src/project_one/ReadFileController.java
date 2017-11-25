public class ReadFileController {
    public void readFile(){
        int c;
        InputStream fi = new FileInputStream("/Users/summerchaser/Desktop/111.txt");
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

    }

}