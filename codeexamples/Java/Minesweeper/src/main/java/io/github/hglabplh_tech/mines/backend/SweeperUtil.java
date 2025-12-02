/*
 * Copyright (c)arald Glab-Plhak 2025
 */

package io.github.hglabplh_tech.mines.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This utility is for the calculations and the logic in the background
 */
public class SweeperUtil {

    private final Integer numFields;
    private final Integer cx;
    private final Integer cy;
    private final Integer numMines;
    private final List<List<ButtDescr>> fieldsList = new ArrayList<>();
    private final Boolean[] shadowArray;
    private Integer negativeHits;

    /**
     * CTOR for the utility class
     * @param cx the count in x direction
     * @param cy the count in y direction
     * @param numMines the number of mines we generate
     */
    public SweeperUtil(Integer cx, Integer cy, Integer numMines) {
        this.numFields = cx * cy;
        this.numMines = numMines;
        this.cx = cx;
        this.cy = cy;

        this.negativeHits = 0;
        this.shadowArray = new Boolean[this.numFields];

    }

    /**
     * In this method we calculate the mines by random and place them in the fields-array
     * @return the complete fields array - list
     */
    public List<List<ButtDescr>> calculateMines() {
        Random rand = new Random();
        int arrIndex = 0;
        for (int cyInd = 0; cyInd < this.cy; cyInd++) {
            this.fieldsList.add(new ArrayList<>());
            for (int cxInd = 0; cxInd < this.cx; cxInd++) {
                this.fieldsList.get(cyInd).add(cxInd, new ButtDescr(Boolean.FALSE, Boolean.FALSE));
                this.shadowArray[arrIndex] = Boolean.FALSE;
                arrIndex++;
            }
        }
        for (int index = 0; index < this.numMines; index++) {
            Integer mineIndex = rand.nextInt(this.numFields - 1);
            this.shadowArray[mineIndex] = Boolean.TRUE;
        }

        arrIndex = 0;
        for (int cyInd = 0; cyInd < this.cy; cyInd++) {
            for (int cxInd = 0; cxInd < this.cx; cxInd++) {
                Boolean temp = this.shadowArray[arrIndex];
                this.fieldsList.get(cyInd).add(cxInd, new ButtDescr(Boolean.FALSE, temp));
                arrIndex++;
            }
        }
        return getFieldsList();
    }

    /**
     * This method builds a button name which is later also used for the logic
     * @param x the x coordinate
     * @param y the y coordinate
     * @param isMine true / false is a mine
     * @return the calculated name as splittable String
     */
    public String makeButtonName(Integer x, Integer y, Boolean isMine) {
        return new StringBuilder()
                .append(x)
                .append('#')
                .append(y)
                .append('#')
                .append(isMine).toString();
    }

    /**
     * detects if the given Button is a mine or not
     * @param buttonName the name which contains as we see in makeButtonName contains information  about the button
     * @return true = isMine / fase = is No Mine
     */
    public Boolean isMineHit(String buttonName) {
        String[] buttonValues = buttonName.split("#");
        Integer x = Integer.valueOf(buttonValues[0]);
        Integer y = Integer.valueOf(buttonValues[1]);
        ButtDescr temp = this.fieldsList.get(y).get(x);
        Boolean mineHit = Boolean.valueOf(buttonValues[2]);
        if (!temp.isProcessed() && !mineHit) {
            this.negativeHits++;
        }
        this.fieldsList.get(y).add(x, new ButtDescr(Boolean.TRUE, temp.isMine));
        return Boolean.valueOf(mineHit);
    }

    /**
     * Used for setting the right button to the right icon
     * @param buttonName the name of the button used to get x / y
     * @param compName the second button name containing the position
     * @return true / false = position fits
     */
    public Boolean compNamesXY(String buttonName, String compName) {
        String[] buttonValues = buttonName.split("#");
        Integer x = Integer.valueOf(buttonValues[0]);
        Integer y = Integer.valueOf(buttonValues[1]);
        String[] compValues = compName.split("#");
        Integer cox = Integer.valueOf(compValues[0]);
        Integer coy = Integer.valueOf(compValues[1]);
        if (cox.equals(x) && coy.equals(y)) {
           return true;
        }

        return false;
    }


    /**
     * Getter for positive end flag
     * @return the flag
     */
    public boolean isPositiveEnd() {
        return (boolean) (this.negativeHits >= (this.numFields - this.numMines));
    }

    /**
     * The complete number of fields in the area
     * @return the number of fields
     */
    public Integer getNumFields() {
        return this.numFields;
    }

    /**
     * The number of mines
     * @return number of mines
     */
    public Integer getNumMines() {
        return this.numMines;
    }

    /**
     * Get the count x
     * @return the count x
     */
    public Integer getCx() {
        return cx;
    }

    /**
     * get the count y
     * @return the count y
     */
    public Integer getCy() {
        return cy;
    }

    /**
     * Get the plain shadow array  used for mine calculation
     * @return the flat array
     */
    public Boolean[] getShadowArray() {
        return shadowArray;
    }

    /**
     * get the button field with descriptions for each button
     * @return the fields list in list
     */
    public List<List<ButtDescr>> getFieldsList() {
        return Collections.unmodifiableList(this.fieldsList);
    }

    public static class ButtDescr  {

        private final boolean isProcessed;
        private final boolean isMine;
        public ButtDescr(boolean isProcessed, boolean isMine) {
            this.isProcessed = isProcessed;
            this.isMine = isMine;
        }

        public boolean isProcessed() {
            return isProcessed;
        }

        public boolean isMine() {
            return isMine;
        }
    }
}
