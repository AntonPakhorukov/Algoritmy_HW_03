
public class List {
    Node head;
    Node tail;

    public void add(int value) { // метод добавления в конец связанного списка
        Node node = new Node(); // создаем новую ноду
        node.value = value; // присваиваем ей добавляемое значение
        node.next = null; // следующего значения не существует
        node.previous = null; // предыдущего значения не существет
        if (head == null) { // проверяем, если головы нет, то
            head = node; // голове присваиваем значение ноды
            tail = node; // и хвосту присваиваем так же значение ноды
        } else { // если нода существет, список не пустой, то
            /*
            tail.next = node; // берем текущий хвост и говорим, что его следующая нода равна нашей ноде
            node.previous = tail; // наша предыдущая нода начинает ссылаться на хвост
            tail = node; // и теперь обновляем значение хвоста
             */
            Node currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = node;
            node.previous = currentNode;
            tail = node;
        }
    }

    public void print() {
        if (head != null) {
            Node currentNode = head;
            System.out.print("[ ");
            while (currentNode != null) {
                System.out.print(currentNode.value + " ");
                currentNode = currentNode.next;
            }
            System.out.println("]");
        } else {
            System.out.println("List is empty");
        }
    }

    public void add (int value, Node node) { // добавление в середину связанного списка
        Node next = node.next; // то, на что ссылается нода в текущий момент
        Node newNode = new Node(); // так же создаем ноду
        newNode.value = value; // присваиваем значение ноды
        node.next = newNode; // берем текущую ноду и говорим, что ее следующее значение это новая нода
        newNode.previous = node; // предыдущее значение новой ноды, это нода
        if (next == null) { // делаем проверку хвоста, если следующее значение = null
            tail = newNode; // то хвост равен новой ноде
        } else { // в обратном случае
            next.previous = newNode; // меняем ссылки, предыдущее значение следующей ноды равно новой ноде
            newNode.next = next; // следующее значение новой ноды равно следующе ноды
        }
    }

    // Перегрузка метода добавления (нескольких данных за раз)

    public void add(int... args) { // int... когда принимает несколько данных отдного типа (args => массив)
        for (int i = 0; i < args.length; i++) {
            this.add(args[i]);
        }
        /*
        Node currentNode = new Node();
        Node newNode = new Node();
        newNode.next = null;
        newNode.previous = null;
        */

    }

    public void delete (Node node) { // метод удаления ноды
        Node previous = node.previous; // создаем новую переменную со ссылкой на предыдущую ноду
        Node next = node.next; //создаем ноду некст со ссылкой на следующую ноду
        if (head == null) { // обрабатывем случай, когда список пустой
            return;
        } else { // и случай, когда только одно значение
            if (node == head && node.next == null) {
                head = null;
                tail = null;
                return;
            }
        }
        if (previous == null) { // обрабатываем случай, когда удаляемая нода является головой
            next.previous = null; // говорим, что предыдущее значение будет равно null
            head = next; // и голова станет node.next
        } else {
            if (next == null) {// обрабатываем случай, когда удаляемая нода равна хвосту
                previous.next = null; // у предыдущего значения, следующее значение равно null
                tail = previous; // и теперь хвост равен предыдущему значению
            } else {
                previous.next = next; // изменили ссылки, теперь предыдущая нода ссылается на следующую (не на текущую)
                next.previous = previous; // и ссылка следующей ноды на предыдущую ссылается на предыдущую (не на текущую)
                // тем самы мы убрали ссылки на текущую ноду, когда удаляемая нода в середине списка
            }
        }
    }

    public void delete(int value) {
        Node currentNode = head;
        while (true) {
            if (currentNode == null) {
                this.print(); // если список пустой, скажем об этом
                return;
            } else {
                while (currentNode.value != value) {
                    if (currentNode.next == null) {
                        System.out.println(value + " not in list"); // скажем, что не нашли значение
                        return;
                    }
                    currentNode = currentNode.next;
                }
                if (currentNode.previous == null) { // если искомое значение = head
                    head = currentNode.next;
                    head.previous = null;
                } else if (currentNode.next == null) { // если искомое значение = tail
                    currentNode.previous.next = null;
                    tail = currentNode.previous;
                    currentNode = null;
                } else {
                    Node prevNode = currentNode.previous;
                    Node nextNode = currentNode.next;
                    currentNode = null;
                    prevNode.next = nextNode;
                    nextNode.previous = prevNode;
                }
            }
            return;
        }
    }

    public Node find(int value) { // метод будет искать ноду в нашем списке
        Node currentNode = head; // точка старта поиска - голова
        while (currentNode != null) { // пока нода существует
            if (currentNode.value == value) { // если нода найдена
                return currentNode; // вернуть ноду
            }
            currentNode = currentNode.next; // если нода не найдена, обновляем currentNode
            // и переходим на следующую ноду
        }
        return null; // если ноду не нашли, возвращаем пустоту
    }

    public void revert() { // разворот двусвязного списка
        Node currentNode = head; // создаем ноду и присваиваем значение головы
        while (currentNode != null) { // пока currentNode не равна пустоте
            Node next = currentNode.next; // создаем ноду next и присваиваем значение currentNode.next
            Node previous = currentNode.previous; // создаем ноду previous  и присваиваем значение currentNode.previous
            currentNode.next = previous; // далее меняем ссылки местами, ссылке на следующую присваиваем ссылку на предыдущую
            currentNode.previous = next; // а предыдущей присваиваем ссылку на следующую
            if (previous == null) { // и запускаем проверку на tail / head
                tail = currentNode;
            }
            if (next == null) { // если следующего значения нет, то это становится головой
                head = currentNode;
            }
            currentNode = next; // переходим на следующую ноду
        }
    }
}
