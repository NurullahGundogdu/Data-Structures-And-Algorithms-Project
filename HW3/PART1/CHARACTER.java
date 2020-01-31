package PART1;

public class CHARACTER {

    private Character name;
    private int row=1;
    private int column=0;

    /**
     *
     * @param name
     * @param row
     * @param column
     * variable assignments are made
     */
    public CHARACTER(Character name, int row, int column){
        this.name=name;
        this.row=row;
        this.column=column;
    }


    /**
     *
     * @return name of character
     */
    public Character getName() {
        return name;
    }

    /**
     * @param name
     * set character name
     */
    public void setName(Character name) {
        this.name = name;
    }

    /**
     *
     * @return row of character
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @param row
     * set character row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     *
     * @return column of character
     */
    public int getColumn() {
        return column;
    }

    /**
     *
     * @param column
     * set character column
     */
    public void setColumn(int column) {
        this.column = column;
    }

}
