
/*public class LinkedListblah {
    Node head;
    Node tail;
    public class Node{
        Node next;
        public Node(){
            head = tail = null;
        }
        public Node(E item){

        }
    }
    public boolean removeLast() {
        if (head == null)
        throw new NoSuchElementException();
        else {
            if(head == tail){
                head=tail=null;
            } else if ( head != tail){
                tail = head;
                for(int i =0; i< size-2; i++){
                    tail = tail.next;
                }
                tail.next = null;
            }
            size--;
        }

    }
    public boolean addFirst(E item){
        Node val = head;
        head = new Node(item);
        if(tail == null){
            tail = head;
        } else {
            head.next = val;
        }
        size++;
        return true;
    }
}*/
