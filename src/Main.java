// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        List list = new List();
        list.add(1);
        System.out.print("inseret 1: ");
        list.print();
        list.add(3, 4, 5, 6, 6, 7, 7, 8, 9);
        System.out.print("insert numbers: ");
        list.print();
        list.add(2, list.find(1));
        System.out.print("insert 2 after 1: ");
        list.print();
        list.delete(list.find(6));
        System.out.print("delete node 6: ");
        list.print();
        list.delete(7);
        System.out.print("delete int 7: ");
        list.print();
        list.delete(0);
        System.out.print("list is: ");
        list.print();
        list.revert();
        System.out.print("revert:  ");
        list.print();
        System.out.println(list.head.value);
        System.out.println(list.head.next.value);
        System.out.println(list.tail.value);




    }
}