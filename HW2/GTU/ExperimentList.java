package GTU;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ExperimentList<E> implements Iterable<E>{

    private class LinkedListIterator implements Iterator<E> {

        private Experiment nextNodeToReturn = head;

        /**
         *
         * @return boolean if has next
         */
        @Override
        public boolean hasNext() {
            return nextNodeToReturn != null;
        }

        /**
         *
         * @return Experiment and take next
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator exceeded.");
            }
            E ret = (E) nextNodeToReturn;
            nextNodeToReturn = nextNodeToReturn.getNext();
            return ret;
        }
    }

    private Experiment head=null;
    private static int size=0;
    /**
     *
     * @return head
     */
    public Experiment getHead() { return head; }

    /**
     *
     * @param e
     */
    public void setHead(Experiment e) { head=e; }

    /**
     *
     * @return iterator object
     */
    public Iterator<E> iterator(){
        return new LinkedListIterator();
    }

    /**
     * print list
     */
    public void listAll()
    {
        System.out.println("List experiment view:");
        Experiment last = head;
        while( last != null) {
            System.out.println(last.toString());
            last = last.getNext();
        }
        System.out.println("List day view:");
        last = head;
        while( last != null) {
            System.out.println(last.toString());
            last = last.getNextDay();
        }
    }


    /**
     *
     * @param exp
     * take exp and add to list
     */
    public void addExp(Experiment exp) {

        if (head == null) {
            head = exp;                             //liste bos ise basa ekliyor
        }else if (head.getNext() == null){
            if(exp.getDay()<head.getDay()){
                exp.setNext(head);          //listede sadece bir eleman varsa 2. ye ekliyor günlerin büyüklük durumuna gore yerlerini degistiriyor
                head=exp;
            }else{
                head.setNext(exp);
            }
        }else if(exp.getDay()<head.getDay()){
            exp.setNext(head);            //listenin basina ekleme yapıyor
            head=exp;
        }else{
            boolean a=false;

            Iterator<Experiment>iter2= (Iterator<Experiment>) this.iterator();

            while (iter2.hasNext()){
                Experiment o=iter2.next();
                if(o.getDay()==exp.getDay() && o.getNext()==null){
                    o.setNext(exp);
                    a=true;         //listenin sonuna ekleme yapıyor
                    break;
                }
                if((o.getDay()==exp.getDay() && o.getNext().getDay()!=exp.getDay())){
                    a=true;
                    exp.setNext(o.getNext());
                    o.setNext(exp);
                    break;
                }
            }
            if(!a){
                iter2= (Iterator<Experiment>) this.iterator();
                while (iter2.hasNext()){
                    Experiment o=iter2.next();
                    if (o.getDay()<exp.getDay() && o.getNext()==null){
                        o.setNext(exp);
                        break;
                    }
                    if((o.getDay()<exp.getDay() && o.getNext().getDay()>exp.getDay())){
                        exp.setNext(o.getNext());
                        o.setNext(exp);
                        break;
                    }
                }
            }
        }
        this.daycontact();
        size++;

    }

    /**
     * create a connection between days
     */
    private void daycontact(){
        Experiment temp=head;
        Experiment temp2=head;

        while (temp!=null) {
            if(temp.getDay() != temp2.getDay()) {
                temp2.setNextDay(temp);
                while (temp2 != null) {
                    if(temp2.getDay()==temp.getDay())
                        break;
                    temp2=temp2.getNext();
                }
            }
            temp=temp.getNext();
        }
    }

    /**
     * delete connection between days
     */
    private void dayContactDelete(){
        Experiment temp=head;

        while (temp!=null){
            temp.setNextDay(null);
            temp=temp.getNext();
        }
    }

    /**
     * search for days if list dont have
     * @param day
     */
    private void findFault2(int day){
        Experiment temp=head;
        int num=0;
        while (temp!=null){
            if(temp.getDay()==day)
                num++;
            temp=temp.getNext();
        }
        if(num==0)
            throw new IndexOutOfBoundsException("Day    " + Integer.toString(day));
    }

    /**
     * search for days and index if list dont have
     * @param day
     * @param index
     */
    private void findFault(int day,int index){
        Experiment temp=head;
        int num=0;
        while (temp!=null){
            if(temp.getDay()==day)
                num++;
            temp=temp.getNext();
        }
        if(num==0)
            throw new IndexOutOfBoundsException("Day    " + Integer.toString(day));
        if(num<=index || index<0 || index>=size)
            throw new IndexOutOfBoundsException("Index    " + Integer.toString(index));
    }

    /**
     * if experiments completed in given day print
     * @param day
     */
    public void listExp(int day){

        this.findFault2(day);

        ExperimentList<Experiment> exp=new ExperimentList<>();

        Iterator<Experiment> iter= (Iterator<Experiment>) this.iterator();

        while (iter.hasNext()){
            Experiment o=iter.next();
            if(o.getDay()==day)                         //girilen gunu bulup true olanlari listeye ekleyip donduruyor
                if(o.isCompleted()) {
                    Experiment exe=new Experiment(o.getSetup(),o.getTime(),o.isCompleted(),o.getDay(),o.getAccuracy());
                    exp.addExp(exe);
                }
        }

        while(exp.getHead()!=null){

            System.out.println(exp.getHead().toString());

            exp.setHead(exp.getHead().getNext());
        }

    }

    /**
     * return experiment in given day and index
     * @param day
     * @param index
     * @return experiment
     */
    public Experiment getExp(int day, int index){

        this.findFault(day,index);

        int num=0;

        Iterator<Experiment>iter= (Iterator<Experiment>) this.iterator();

                                        //girilen gunun ve indexteki experimentı donduruyor
        while (iter.hasNext()){
            Experiment o=iter.next();


            if(o.getDay()==day){
                num++;
                if(num-1==index)
                    return o;
            }
        }

        return null;
    }

    /**
     * set experiment in given day and index
     * @param day
     * @param index
     * @param e
     */
    public void setExp(int day, int index, Experiment e ){

        this.findFault(day,index);

        int num=0;
        Iterator<Experiment>iter= (Iterator<Experiment>) this.iterator();
        while (iter.hasNext()){
            Experiment o=iter.next();

            if(o.getDay()==day){
                num++;                                              //girilen index ve daydeki yere girilen experiment set edilir
                if(num-1==index){
                    o.setDay(e.getDay());
                    o.setAccuracy(e.getAccuracy());
                    o.setCompleted(e.isCompleted());
                    o.setTime(e.getTime());
                    o.setSetup(e.getSetup());
                }
            }
        }
    }

    /**
     * sort list by accuracy and return new list
     * @return ExperimentList
     */
    public ExperimentList orderExperiments(){

        ExperimentList<Experiment> exp=new ExperimentList<>();

        Iterator<Experiment> iter= (Iterator<Experiment>) this.iterator();

        while (iter.hasNext()){
            Experiment o=iter.next();
            Experiment exe=new Experiment(o.getSetup(),o.getTime(),o.isCompleted(),o.getDay(),o.getAccuracy());
            exp.addExp(exe);
        }

        Experiment temp=exp.head;
        Experiment sorted=null;
        while (temp != null) {
            Experiment next = temp.getNext();
            sorted=sortedAccuracy(temp,sorted);
            temp = next;
        }

        ExperimentList<Experiment> ex =new ExperimentList<>();
        ex.addExp(sorted);

        return ex;
    }

    /**
     * helper function for orderExperiments
     * @param newnode
     * @param sorted
     * @return Experiment
     */
    private Experiment sortedAccuracy(Experiment newnode,Experiment sorted) {

        if (sorted == null || sorted.getAccuracy() >= newnode.getAccuracy()) {
                newnode.setNext(sorted);
                sorted = newnode;
        }
        else {

            Experiment current = sorted;
            if(!newnode.isCompleted()){
                while(current.getNext() != null){
                    current = current.getNext();
                }

                current.setNext(newnode);
                newnode.setNext(null);
            }else {
                while (current.getNext() != null && current.getNext().getAccuracy() < newnode.getAccuracy() && current.getNext().isCompleted()) {
                    current = current.getNext();

                }
                newnode.setNext(current.getNext());
                current.setNext(newnode);
            }

        }
        return sorted;
    }

    /**
     * remove Experiment in given day and index
     * @param day
     * @param index
     */
    public void removeExp(int day, int index){

        this.findFault(day,index);

        int num=-1;

        if(head.getDay()==day && index==0)
            head=head.getNext();
        else {
            Experiment exe=head;
            Experiment exe2=exe;
            while (exe != null) {
                if (exe.getDay() == day){
                    num++;
                }
                if (num == index) {
                    exe2.setNext(exe.getNext());
                    break;
                }
                exe2=exe;
                exe = exe.getNext();
            }
        }
        size--;
        this.dayContactDelete();
        this.daycontact();
    }

    /**
     * remove given day from list
     * @param day
     */
    public void removeDay(int day){

        this.findFault2(day);
        int num=0,num2=0;

        Iterator<Experiment>iter= (Iterator<Experiment>) this.iterator();
        while (iter.hasNext()){
            if(iter.next().getDay()==day)
                num++;
        }
        while (num2<num){
            this.removeExp(day,0);
            num2++;
        }
    }

    /**
     * sort given day by accuracy
     * @param day
     */
    public void orderDay(int day){

        this.findFault2(day);


        int num=0;

        ExperimentList<Experiment> exp=new ExperimentList<>();

        Iterator<Experiment> iter= (Iterator<Experiment>) this.iterator();

        while (iter.hasNext()){

            Experiment o=iter.next();
            if(o.getDay()==day){
                Experiment exe=new Experiment(o.getSetup(),o.getTime(),o.isCompleted(),o.getDay(),o.getAccuracy());
                exp.addExp(exe);
                num++;
            }
        }
        exp=exp.orderExperiments();

        for (int i=0; i<num; i++) {
            this.setExp(day,i,exp.getHead());
            exp.setHead(exp.getHead().getNext());
        }

        this.dayContactDelete();
        this.daycontact();
    }
}
