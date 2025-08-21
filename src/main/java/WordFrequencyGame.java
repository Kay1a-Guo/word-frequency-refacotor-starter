import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.io.CharArrayWriter;

import java.time.LocalDateTime;

public class WordFrequencyGame {
    public String getResult(String inputStr) {


        if (inputStr.split("\\s+").length != 1) {

            try {

                //split the input string with 1 to n pieces of spaces
                List<Input> inputList = getInputList(inputStr);
                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map = getListMap(inputList);
                List<Input> list = getWordCount(map);
                inputList = sortWord(list);
                StringJoiner joiner = spliceWord(inputList);

                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        } else {
            return inputStr + " 1";
        }
    }

    private StringJoiner spliceWord(List<Input> inputList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Input w : inputList) {
            String s = w.getValue() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner;
    }

    private List<Input> sortWord(List<Input> list) {
        List<Input> inputList;
        inputList = list;

        inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
        return inputList;
    }

    private List<Input> getWordCount(Map<String, List<Input>> map) {
        List<Input> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        return list;
    }

    private List<Input> getInputList(String inputStr) {
        String[] arr = inputStr.split("\\s+");

        List<Input> inputList = new ArrayList<>();
        for (String s : arr) {
            Input input = new Input(s, 1);
            inputList.add(input);
        }
        return inputList;
    }


    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (map.containsKey(input.getValue())) {
                map.get(input.getValue()).add(input);
            } else {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            }
        }


        return map;
    }


}