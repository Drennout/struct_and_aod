package practice12;

import java.awt.desktop.SystemEventListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<PhonePerson> persons_1 = new ArrayList<>();

        // Список контактных данных
        persons_1.add( new PhonePerson("+7-888-000-00-01", "Adress1", "Person1"));
        persons_1.add( new PhonePerson("+8-888-000-00-02", "Adress2", "Person2"));
        persons_1.add( new PhonePerson("+8-999-000-00-05", "Adress5", "Person5"));
        persons_1.add( new PhonePerson("+7-999-000-00-03", "Adress3", "Person3"));
        persons_1.add( new PhonePerson("+7-888-000-00-04", "Adress4", "Person4"));

//      Метод зобоаботки. Сначала Вносятся все данные в List.txt,
//      затем записываются данные в CodePerson.txt, по введенному коду телефона(-***-(три цифры после
//      региоального кода(+7, например))), вводить надо тольк цифры, без тире.
//      Затем вводится региональный номер(первая цифра в номере), по которому будет перезаписан файл
//      List.txt. Если ничего не надо удалять, просто введите любой другой символ, кроме цифры.
        Handler.writer(persons_1);


        ArrayList<PhonePerson> persons_2 = new ArrayList<>();


//      Введите размер нового листа, для внесения новых данных,
//      эти данные будут записаны в файл с удалением. Файл с контактными данными, записананный по введенному коду
//      перезаписан не будет, новые данные дополнят его.
//      Если надо удлаить данные из List.txt, введите нулевое значение листа
        System.out.println("Enter size new array");
        int size = new Scanner(System.in).nextInt();
        if(size > 0){
            for (int i = 0; i < size; i++)
                persons_2.add(new PhonePerson(
                        new Scanner(System.in).next(),
                        new Scanner(System.in).next(),
                        new Scanner(System.in).next()));
        }
        Handler.remove(persons_2);
    }
}
