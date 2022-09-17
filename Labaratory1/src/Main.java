import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringCalculator{
    Pattern pat, pat1;

    public StringCalculator(){
        String regular = "//.*]\n";
        pat = Pattern.compile(regular);
        pat1 = Pattern.compile("//.\n");
    }
    private final ArrayList <String> listDelimiters = new ArrayList<>();

    private boolean hasDelimiters(String numbers){
        return  numbers.charAt(0) =='/' && numbers.charAt(1) == '/';
    }

    public int add(String numbers) throws Exception {
        if (numbers.isEmpty()) return 0;
        initDelimitersList();
        String NumbersWithoutDelimiters = numbers;
        if (hasDelimiters(numbers)) {
            String numbersDelimiters;
            String[] StrArr = FindSubstringWithDelimiters(numbers);
            NumbersWithoutDelimiters = StrArr[1];
            numbersDelimiters = StrArr[0];
            AddDelimiters(numbersDelimiters);

        }
        sortListDelimiters();
        ArrayList<Integer> DividedNumbers = DivideByDelimiters(NumbersWithoutDelimiters);
        return checkedSum(DividedNumbers);
    }

    private int checkedSum(ArrayList <Integer> DividedNumbers) throws Exception {
        int sum = 0;
        for(int num: DividedNumbers){
            if (num<0){
                throw GenerateNegativeException(DividedNumbers);
            }
            if (num<=1000)
                sum+=num;
        }
        return sum;
    }

    private Exception GenerateNegativeException(ArrayList <Integer> DividedNumbers){
        StringBuilder ExceptionText = new StringBuilder("Negative numbers: ");
        for(int num: DividedNumbers){
            if(num<0){
                ExceptionText.append(num).append(" ");
            }
        }
        return new Exception(String.valueOf(ExceptionText));
    }

    private void AddDelimiters(String numbersDelimiters){
        if (numbersDelimiters.charAt(0) =='[' && numbersDelimiters.charAt(numbersDelimiters.length()-1) == ']'){
            numbersDelimiters = numbersDelimiters.substring(1,numbersDelimiters.length()-1);
        }
        String[] DelimitersArr = numbersDelimiters.split("]\\[");
        listDelimiters.addAll(Arrays.asList(DelimitersArr));
    }

    private void initDelimitersList(){
        listDelimiters.clear();
        listDelimiters.add("\n");
        listDelimiters.add(",");
    }

    private String[] FindSubstringWithDelimiters(String numbers) throws Exception {
        Matcher mat = pat.matcher(numbers);
        if (!mat.find()){
            mat = pat1.matcher(numbers);
            if(!mat.find()){
                throw new Exception("Error in Delimiter list");
            }
        }
        String firstMatch = mat.group();
        firstMatch = firstMatch.substring(2, mat.end() - 1);
        numbers = numbers.substring(mat.end());
        return new String[]{firstMatch, numbers};
    }

    private void sortListDelimiters(){
        for (int i = 0; i<listDelimiters.size(); i++) {
            for(int j = 0; j<listDelimiters.size()-1;j++){
                if (listDelimiters.get(j).length()<listDelimiters.get(j+1).length()){
                    String a = listDelimiters.get(j);
                    listDelimiters.set(j, listDelimiters.get(j + 1));
                    listDelimiters.set(j+1, a);
                }
            }
        }
    }

    private int isDelimiter_ReturnLength(String number, int num){
        for (String delimiter: listDelimiters) {

            if (number.length()>=delimiter.length()-num && delimiter.equals(number.substring(num,delimiter.length()+num))){
                return delimiter.length();
            }
        }
        return 0;
    }

    private ArrayList <Integer> DivideByDelimiters(String numbersWithoutDelimiterList) throws Exception {
        ArrayList <Integer> DividedNumbers = new ArrayList<>();
        int endIndex = 0;
        int DelimiterLen;
        while (numbersWithoutDelimiterList.length()>endIndex){
            if ((DelimiterLen = isDelimiter_ReturnLength(numbersWithoutDelimiterList,endIndex))>0){
                if (endIndex==0){
                    throw new Exception("two delimiters");
                }

                DividedNumbers.add(Integer.parseInt(numbersWithoutDelimiterList.substring(0,endIndex)));
                numbersWithoutDelimiterList = numbersWithoutDelimiterList.substring(endIndex+DelimiterLen);
                endIndex = 0;
                if(numbersWithoutDelimiterList.isEmpty()){
                    throw new Exception("Delimiter in the end!");
                }
            }
            else endIndex++;
        }
        DividedNumbers.add(Integer.parseInt(numbersWithoutDelimiterList.substring(0,endIndex)));
        return DividedNumbers;
    }
}


public class Main {

    public static void main(String[] args) throws Exception {
        StringCalculator calculator = new StringCalculator();
        String question = "//[***][*][**][123]\n2*2***21232";
        System.out.println(question.replace("\n","\\n")+" = "+calculator.add(question));
    }
}