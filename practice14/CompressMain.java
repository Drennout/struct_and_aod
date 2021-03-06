package aod.practice14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

public class CompressMain {
    public static void main(String[] args) {
        System.out.println("Введите строку");
//        String text = new Scanner(System.in).nextLine();
          String text = "пупкин василий кириллович";
        TreeMap<Character, Integer> frequencies = countFrequency(text);

        ArrayList<CodeTreeNode> codeTreeNodes = new ArrayList<>();
        for (Character c: frequencies.keySet()){
            codeTreeNodes.add(new CodeTreeNode(c, frequencies.get(c)));
        }

        CodeTreeNode tree = huffman(codeTreeNodes);


        TreeMap<Character, String> codes = new TreeMap<>();
        for(Character c: frequencies.keySet()) {
            codes.put(c, tree.getCodeForCharacter(c, ""));
        }

        System.out.println("Таблица префиксных кодов: " + codes.toString());

        // кодируем текст, заменяем сиволы соответствующими кодами
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            encoded.append(codes.get(text.charAt(i)));
        }

        System.out.println("Размер исходной строки: " + text.getBytes().length * 8 + " бит");
        System.out.println("Размер сжатой строки: " + encoded.length() + " бит");
        System.out.println("Биты сжатой строки: " + encoded);

        // декодируем сжатую информацию обратно
        String decoded = huffmanDecode(encoded.toString(), tree);

        System.out.println("Расшифровано: " + decoded);

    }

    private static TreeMap<Character, Integer> countFrequency(String text){
        TreeMap<Character, Integer> freqMap = new TreeMap<>();

        for (int i = 0; i < text.length(); i++){
            Character c = text.charAt(i);
            Integer count = freqMap.get(c);
            freqMap.put(c, count != null ? count + 1: 1);
        }
        return freqMap;
    }

    private static  CodeTreeNode huffman(ArrayList<CodeTreeNode> codeTreeNodes){
        while (codeTreeNodes.size() > 1){
            Collections.sort(codeTreeNodes);
            CodeTreeNode left = codeTreeNodes.remove(codeTreeNodes.size() - 1);
            CodeTreeNode right = codeTreeNodes.remove(codeTreeNodes.size() - 1);

            CodeTreeNode parent = new CodeTreeNode(null, right.weight + left.weight, left, right);
            codeTreeNodes.add(parent);
        }

        return codeTreeNodes.get(0);
    }

    public static String huffmanDecode(String encoded, CodeTreeNode tree){
        StringBuilder decoded = new StringBuilder();

        CodeTreeNode node = tree;
        for (int i = 0; i< encoded.length(); i++){
            node = encoded.charAt(i) == '0' ? node.left : node.right;

            if (node.content != null){
                decoded.append(node.content);
                node = tree;
            }
        }
        return decoded.toString();
    }

    private static class CodeTreeNode implements Comparable<CodeTreeNode>{
        Character content;
        int weight;
        CodeTreeNode left, right;

        public CodeTreeNode(Character content, int weight){
            this.content = content;
            this.weight = weight;
        }

        public CodeTreeNode(Character content, int weight, CodeTreeNode left, CodeTreeNode right){
            this.content = content;
            this.weight = weight;
            this.right = right;
            this.left = left;
        }
        @Override
        public int compareTo(CodeTreeNode o) {
            return o.weight - weight;
        }

        //извлечение кода для символа
        public String getCodeForCharacter(Character ch, String parentPath){
            if (content == ch)
                return parentPath;
            else{
                if(left != null){
                    String path = left.getCodeForCharacter(ch, parentPath + 0);
                    if (path != null)
                        return path;
                }
                if(right != null){
                    String path = right.getCodeForCharacter(ch, parentPath + 1);
                    if(path != null)
                        return path;
                }
            }

            return null;
        }
    }

}