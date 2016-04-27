package ua.in.mordas.codejam.round1a;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TheLastWord {

    public static void main(String[] args) throws Exception {
        String fileIn = "/Users/volodymyrmordas/Downloads/roundA1/A-large.in";
        String fileOut = "/Users/volodymyrmordas/Downloads/roundA1/A-large.out";
        List<String> in = new ArrayList<>();

        try {
            File file = new File(fileIn);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                in.add(line);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        in.remove(0);

        File fout = new File(fileOut);
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        int counter = 1;
        for (String word : in){
            String lastWord = doing(word);
            bw.append(String.format("Case #%d: %s\n", counter, String.valueOf(lastWord)));
            System.out.println(String.format("Case #%d: %s\n", counter, String.valueOf(lastWord)));
            counter++;
        }

        bw.close();
    }


    public static String doing(String theWord){
        List<String> theLastWord = new ArrayList<>();
        theLastWord.add(String.valueOf(theWord.charAt(0)));

        for (int i = 1; i < theWord.length() ; i++) {
            List<String> tmpList = theLastWord(theLastWord.get(theLastWord.size() - 1),
                    String.valueOf(theWord.charAt(i)));
            Collections.sort(tmpList);
            theLastWord.addAll(tmpList);
        }

//        Collections.sort(theLastWord);
        return theLastWord.get(theLastWord.size() - 1);
    }


    public static List<String> theLastWord(String a, String b){
        List<String> theWords = new ArrayList<>();
        theWords.add(b + a);
        theWords.add(a + b);

        return theWords;
    }
}
