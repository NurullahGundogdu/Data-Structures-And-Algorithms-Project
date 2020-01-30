package GTU;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;


public class MAIN {
    public static void main(String [] args){

        assistantTest();
        myTest();

    }

    private static void assistantTest(){
        ExperimentList list = new ExperimentList();
        Random generator = new Random();
        generator.setSeed(3);
        boolean completed = true;
        float acc;
        int day;
        String time = "timeInfo";
        for(int i=0; i<20; i++) {
            System.out.println("----------------------");
            day =  generator.nextInt(4) ;
            String setup = "setup"+Integer.toString(i);
            acc = (float) (i*0.1);
            Experiment e = new Experiment(setup, time , completed, day, acc);
            System.out.println("Add new experiment:");
            System.out.println(e.toString());
            list.addExp(e);
            list.listAll();
        }

        System.out.println("----------------------");
        System.out.println("getExp(0,3) Result:");
        Experiment e = list.getExp(0,3);
        System.out.println(e.toString());
        System.out.println("----------------------");
        System.out.println("setExp(0,3), accuracy=1.0");
        e.setAccuracy((float) 1.0);
        list.setExp(0,3, e);
        e = list.getExp(0,3);
        System.out.println("----------------------");
        System.out.println("getExp Result:");
        e = list.getExp(0,3);
        System.out.println(e.toString());
        System.out.println("----------------------");
        System.out.println("listExp(0) Result:");
        list.listExp(0);
        System.out.println("----------------------");
        System.out.println("removeExp(0,0) Result:");
        System.out.println(list.getExp(0,0).toString());
        list.removeExp(0, 0);
        list.listAll();
        System.out.println("----------------------");
        System.out.println("removeExp(1,0) Result:");
        System.out.println(list.getExp(1,0).toString());
        list.removeExp(1, 0);
        list.listAll();
        System.out.println("----------------------");
        System.out.println("removeExp(1,0) Result:");
        System.out.println(list.getExp(1,0).toString());
        list.removeExp(1, 0);
        list.listAll();
        System.out.println("----------------------");
        System.out.println("removeExp(3,6) Result:");
        System.out.println(list.getExp(3,6).toString());
        list.removeExp(3, 6);
        list.listAll();
        System.out.println("----------------------");
        System.out.println("orderExperiment Result:");
        ExperimentList orderedList = list.orderExperiments();

        Iterator itr =  orderedList.iterator();
        while(itr.hasNext()) {
            System.out.println(itr.next().toString());
        }
        System.out.println("----------------------");
        System.out.println("orderDay(2) Result:");
        list.orderDay(2);
        list.listAll();
        System.out.println("----------------------");
        System.out.println("removeDay(3) Result:");
        list.removeDay(3);
        list.listAll();


    }
    private static void myTest(){

        System.out.println(" ");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("__________________________________myTest Function_______________________________");
        System.out.println(" ");

        ExperimentList<Experiment> expList=new ExperimentList<>();

        expList.addExp(new Experiment("Exp1",time(),true,6,17));
        expList.addExp(new Experiment("Exp2",time(),false,1,22));
        expList.addExp(new Experiment("Exp3",time(),true,7,33));
        expList.addExp(new Experiment("Exp4",time(),true,7,50));
        expList.addExp(new Experiment("Exp5",time(),false,12,32));
        expList.addExp(new Experiment("Exp6",time(),false,7,30));
        expList.addExp(new Experiment("Exp7",time(),false,68,10));
        expList.addExp(new Experiment("Exp8",time(),true,88,100));
        expList.addExp(new Experiment("Exp9",time(),false,48,30));
        expList.addExp(new Experiment("Exp10",time(),true,18,11));
        expList.addExp(new Experiment("Exp11",time(),false,88,20));
        expList.addExp(new Experiment("Exp12",time(),true,68,33));
        expList.addExp(new Experiment("Exp13",time(),true,6,17));
        expList.addExp(new Experiment("Exp14",time(),false,1,22));
        expList.addExp(new Experiment("Exp15",time(),true,7,33));
        expList.addExp(new Experiment("Exp16",time(),true,3,50));
        expList.addExp(new Experiment("Exp17",time(),false,7,32));
        expList.addExp(new Experiment("Exp18",time(),false,7,30));
        expList.addExp(new Experiment("Exp19",time(),true,68,10));
        expList.addExp(new Experiment("Exp20",time(),true,88,100));
        expList.addExp(new Experiment("Exp21",time(),false,48,30));
        expList.addExp(new Experiment("Exp22",time(),true,18,11));
        expList.addExp(new Experiment("Exp23",time(),false,88,20));
        expList.addExp(new Experiment("Exp24",time(),true,68,33));


        System.out.println("_________________________ADDEXP FUNCTION_______________________");
        System.out.println(" ");
        System.out.println("Add Experiments to ExpList");
        System.out.println(" ");
        for (Experiment o : expList) {
            System.out.println("ExpList    " + "SETUP: " + o.getSetup() + "  DAY: " + o.getDay() + "    TIME: _" + o.getTime() + "   COMPLETED: " + o.isCompleted() + "   ACCURACY: " + o.getAccuracy());
        }
        System.out.println(" ");

        System.out.println("_________________________NEXTDAY_______________________");
        System.out.println(" ");
        Experiment exp=expList.getHead();
        while (exp!=null){
            System.out.println("ExpList nextday   "+"SETUP: "+exp.getSetup()+"  DAY: "+exp.getDay()+"    TIME: _"+exp.getTime()+"   COMPLETED: "+exp.isCompleted()+"   ACCURACY: "+exp.getAccuracy());
            exp=exp.getNextDay();
        }
        System.out.println(" ");


        System.out.println("_________________________GETEXP FUNCTION_______________________");
        System.out.println(" ");

        Experiment exe=expList.getExp(88,0);
        System.out.println("Experiment(day:88/index:0)    "+"SETUP: "+exe.getSetup()+"  DAY: "+exe.getDay()+"    TIME: _"+exe.getTime()+"   COMPLETED: "+exe.isCompleted()+"   ACCURACY: "+exe.getAccuracy());
        System.out.println(" ");


        System.out.println("_________________________LISTEXP FUNCTION_______________________");
        System.out.println(" ");
        expList.listExp(7);
        System.out.println(" ");


        System.out.println("________________________SETEXP FUNCTION_______________________");
        System.out.println(" ");
        expList.setExp(88,1,new Experiment("Exp1",time(),true,6,1));
        for (Experiment o : expList) {
            System.out.println("Exp3    " + "SETUP: " + o.getSetup() + "  DAY: " + o.getDay() + "    TIME: _" + o.getTime() + "   COMPLETED: " + o.isCompleted() + "   ACCURACY: " + o.getAccuracy());
        }
        System.out.println(" ");

        System.out.println("_________________________ORDER EXPERİMENT FUNCTION_______________________");
        System.out.println(" ");
        ExperimentList<Experiment> expList2=expList.orderExperiments();
        for (Experiment o : expList2) {
            System.out.println("ExpList2    " + "SETUP: " + o.getSetup() + "  DAY: " + o.getDay() + "    TIME: _" + o.getTime() + "   COMPLETED: " + o.isCompleted() + "   ACCURACY: " + o.getAccuracy());
        }
        System.out.println(" ");



        System.out.println("_________________________LIST_______________________");
        System.out.println(" ");
        for (Experiment o : expList) {
            System.out.println("ExpList    " + "SETUP: " + o.getSetup() + "  DAY: " + o.getDay() + "    TIME: _" + o.getTime() + "   COMPLETED: " + o.isCompleted() + "   ACCURACY: " + o.getAccuracy());
        }
        System.out.println(" ");


        System.out.println("_________________________REMOVEEXP FUNCTION_______________________");
        System.out.println(" ");
        expList.removeExp(88,0);
        for (Experiment o : expList) {
            System.out.println("ExpList  Remove  " + "SETUP: " + o.getSetup() + "  DAY: " + o.getDay() + "    TIME: _" + o.getTime() + "   COMPLETED: " + o.isCompleted() + "   ACCURACY: " + o.getAccuracy());
        }
        System.out.println(" ");


        System.out.println("_________________________REMOVEDAY FUNCTION_______________________");
        System.out.println(" ");
        expList.removeDay(68);
        for (Experiment o : expList) {
            System.out.println("ExpList  Remove  " + "SETUP: " + o.getSetup() + "  DAY: " + o.getDay() + "    TIME: _" + o.getTime() + "   COMPLETED: " + o.isCompleted() + "   ACCURACY: " + o.getAccuracy());
        }
        System.out.println(" ");



        System.out.println("_________________________LIST_______________________");
        System.out.println(" ");
        for (Experiment o : expList) {
            System.out.println("ExpList    " + "SETUP: " + o.getSetup() + "  DAY: " + o.getDay() + "    TIME: _" + o.getTime() + "   COMPLETED: " + o.isCompleted() + "   ACCURACY: " + o.getAccuracy());
        }
        System.out.println(" ");


        System.out.println("_________________________ORDERDAY FUNCTION_______________________");
        System.out.println(" ");
        expList.orderDay(7);
        for (Experiment o : expList) {
            System.out.println("ExpList    " + "SETUP: " + o.getSetup() + "  DAY: " + o.getDay() + "    TIME: _" + o.getTime() + "   COMPLETED: " + o.isCompleted() + "   ACCURACY: " + o.getAccuracy());
        }
        System.out.println(" ");

    }

    private static String time(){
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
