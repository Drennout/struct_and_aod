package my.h.t;

public class HashTableTester {
    public static void main(String[] args) {
        Map<Integer, String>map = new Map<>();

        map.add(1101, "U1");
        map.add(1102, "U2");
        map.add(1101, "U3");
        map.add(1104, "U4");
        map.add(1105, "U5");
        map.add(1102, "U6");
        map.add(1106, "U7");

        System.out.println(map.get(1101));
        System.out.println(map.remove(1101));
        System.out.println(map.get(1101));

        System.out.println(map.remove(1102));
        System.out.println(map.get(1102));
        System.out.println(map.get(1104));
    }
}
