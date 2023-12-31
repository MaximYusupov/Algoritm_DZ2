// Реализация метода разворота односвязного списка
import java.util.Iterator;

public class Main {
    
    public static void main(String[] args) {
        SingleLinkList<Contact> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Contact(121, "Петров Михаил Петрович", "+7983145632"));
        contactList.addToEnd(new Contact(122, "Сидоров Антон Александрович", "+79884536291"));
        contactList.addToEnd(new Contact(123, "Жилин Александр Харламович", "+7984536293"));
        contactList.addToEnd(new Contact(124, "Утёсов Артём Дронович", "+7945362924"));
        contactList.addToEnd(new Contact(125, "Трушкин Анатолий Семёнович", "+7987453629"));

        for (Object contact : contactList) {
            System.out.println(contact);
        }
        contactList.reverse();

        System.out.println("----------------------------");

        for (Object contact : contactList) {
            System.out.println(contact);
        }
    }
    static class Contact {
        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    // Класс списка
    public static class SingleLinkList<T> implements Iterable {
        ListItem<T> head;
        ListItem<T> tail;

        public Iterator iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;

                public boolean hasNext() {
                    return current != null;
                }

                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        // Класс отдельного элемента
        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        // Голова пустая
        public boolean isEmpty() {
            return head == null;
        }

        // Заполнение списка
        public void addToEnd(T item) {
            //Выделение памяти для списка
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;

            // Если, голова и хвост пустая
            if (isEmpty()) {
                head = newItem;
                tail = newItem;
            } else { //Если, не пустая то передаём элементу адрес и ставим в хвост
                tail.next = newItem;
                tail = newItem;
            }
        }

        // Метод разворота списка
        public void reverse() {
            if (!isEmpty() && head.next != null) {//Если голова не равна нулю
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}