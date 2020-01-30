package GTU;

public class Experiment {

    private String setup;
    private int day;
    private String time;
    private boolean completed;
    private float accuracy;
    private Experiment next=null;
    private Experiment nextDay=null;

    /**
     *
     * @param setup
     * @param time
     * @param completed
     * @param day
     * @param accuracy
     */
    public Experiment(String setup, String time, boolean completed,  int day, float accuracy) {
        this.setup = setup;
        this.day = day;
        this.time = time;
        this.completed = completed;
        this.accuracy = accuracy;
    }


    /**
     *
     * @return next
     */
    public Experiment getNext() { return next; }

    /**
     *
     * @return nextday
     */
    public Experiment getNextDay() { return nextDay; }

    /**
     *
     * @return seturp
     */
    public String getSetup() { return setup; }

    /**
     *
     * @return day
     */
    public int getDay() { return day; }
    /**
     *
     * @return time
     */
    public String getTime() { return time; }
    /**
     *
     * @return completed
     */
    public boolean isCompleted() { return completed; }
    /**
     * @return accuracy
     */
    public float getAccuracy() { return accuracy; }

    /**
     * @param setup
     */
    public void setSetup(String setup) { this.setup = setup; }

    /**
     *
     * @param day
     */
    public void setDay(int day) { this.day = day; }

    /**
     *
     * @param time
     */
    public void setTime(String time) { this.time = time; }

    /**
     *
     * @param completed
     */
    public void setCompleted(boolean completed) { this.completed = completed; }

    /**
     *
     * @param accuracy
     */
    public void setAccuracy(float accuracy) { this.accuracy = accuracy; }

    /**
     *
     * @param nextDay
     */
    public void setNextDay(Experiment nextDay) { this.nextDay = nextDay; }

    /**
     *
     * @param next
     */
    public void setNext(Experiment next) { this.next = next; }

    /**
     *
     * @return constructur
     */
    @Override
    public String toString() {
        return "Experiment{" +
                "setup='" + setup + '\'' +
                ", day=" + day +
                ", time='" + time + '\'' +
                ", accuracy=" + accuracy +
                ", completed=" + completed +
                '}';
    }




}
