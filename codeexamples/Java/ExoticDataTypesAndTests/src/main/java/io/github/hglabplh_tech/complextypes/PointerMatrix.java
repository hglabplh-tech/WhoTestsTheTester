/*
 * Copyright (c)
 */

package io.github.hglabplh_tech.complextypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PointerMatrix<T,V> {

    private final List<List<V>> shadowMatrix;

    private final Integer cyInit;

    private final Integer cxInit;

    private XYCoord realEndPoint = new XYCoord(0, 0);

    private Element<T, V> leftUp;

    private Element<T, V> rightDown;

    private Element<T, V> rightUp;

    private Element<T, V> leftDown;

    public PointerMatrix(Integer cxInit, Integer cyInit, List<V> values) {
        this.cyInit = cyInit;
        this.cxInit = cxInit;
        this.shadowMatrix = new ArrayList<>(cyInit);

        for (Integer yIndex = 0; yIndex < cyInit; yIndex++) {
            this.shadowMatrix.add(yIndex, new ArrayList<>(cxInit));
        }
        initValues(values);
    }

    private void initValues(List<V> values) {
            int valIndex = 0;
            for (int yIndex = 0;yIndex < this.getCyInit()
                    && valIndex < values.size();yIndex++) {
                List<V> xElements = getShadowMatrix().get(yIndex);
                for (int index = 0; index < this.getCxInit()
                        && valIndex < values.size();index++) {
                    xElements.add(index, values.get(valIndex));
                    valIndex++;
                    this.getRealEndPoint().incrementX();
                }
                if (valIndex < values.size()) {
                    this.getRealEndPoint().resetX();
                }
                this.getRealEndPoint().incrementY();
            }
            buildPointersNet();
    }

    private void buildPointersNet() {
        int y = 0;
        for (List<V> xElements : getShadowMatrix()) {
            int x = 0;
            for (V value: xElements) {
                if (x < (xElements.size() -1)) {
                    innerCompareXY(value, y, x);
                } else if (x < xElements.size() ) {
                    innerCompareXY(value, y, x);
                }

            }
        }
    }

    private void innerCompareXY(V value,int y, int x) {
        if (y < (getRealEndPoint().getY() - 1)) {
            deepComp(value, y, x);
        }
        if (y < this.getRealEndPoint().getY()) {
            deepComp(value, y, x);
        }

    }

    private void deepComp(V value, int y, int x) {
        if (y > 0) {
            xCompare(value, x);
        } else if (y == 0) {
            xCompare(value, x);
        }
    }

    private void xCompare(V value, int x) {
        if (x > 0) {
        } else if (x == 0) {
        }
    }

    public List<List<V>> getShadowMatrix() {
        return shadowMatrix;
    }

    public XYCoord getRealEndPoint() {
        return realEndPoint;
    }

    public Integer getCyInit() {
        return cyInit;
    }

    public Integer getCxInit() {
        return cxInit;
    }

    public Element<T, V> getLeftUp() {
        return leftUp;
    }

    public Element<T, V> getRightDown() {
        return rightDown;
    }

    public Element<T, V> getRightUp() {
        return rightUp;
    }

    public Element<T, V> getLeftDown() {
        return leftDown;
    }

    public enum PointerType {
        UP("up"),
        DOWN("down"),
        LEFT("left"),
        RIGHT("right"),
        UP_LEFT("up-left"),
        UP_RIGHT("up-right"),
        DOWN_LEFT("down-left"),
        DOWN_RIGHT("down-right"),
        ;

        private final String pointerTypeName;

        PointerType(String pointerTypeName) {
            this.pointerTypeName = pointerTypeName;
        }
    }

    public record Pointer<T>(PointerType PType, T reference) {

    }

    public static class Element<T, V> {
        private  V elementValue;
        private Pointer<T> upPointer;

        private Pointer<T> downPointer;

        private Pointer<T> leftPointer;

        private Pointer<T> rightPointer;

        private Pointer<T> upLeftPointer;

        private Pointer<T> downRightPointer;

        private Pointer<T> downLeftPointer;

        private Pointer<T> upRightPointer;

        public Element() {

        }

        public Element(Pointer<T> upPointer, Pointer<T> downPointer,
                       Pointer<T> leftPointer, Pointer<T> rightPointer,
                       Pointer<T> upLeftPointer, Pointer<T> downRightPointer,
                       Pointer<T> downLeftPointer, Pointer<T> upRightPointer,
                       V elementValue) {
            this.upPointer = upPointer;
            this.downPointer = downPointer;
            this.leftPointer = leftPointer;
            this.rightPointer = rightPointer;
            this.upLeftPointer = upLeftPointer;
            this.downRightPointer = downRightPointer;
            this.downLeftPointer = downLeftPointer;
            this.upRightPointer = upRightPointer;
            this.elementValue = elementValue;
        }

        public Pointer<T> getUpPointer() {
            return upPointer;
        }

        public Pointer<T> getDownPointer() {
            return downPointer;
        }

        public Pointer<T> getLeftPointer() {
            return leftPointer;
        }

        public Pointer<T> getRightPointer() {
            return rightPointer;
        }

        public Pointer<T> getUpLeftPointer() {
            return upLeftPointer;
        }

        public Pointer<T> getDownRightPointer() {
            return downRightPointer;
        }

        public Pointer<T> getDownLeftPointer() {
            return downLeftPointer;
        }

        public Pointer<T> getUpRightPointer() {
            return upRightPointer;
        }

        public V getElementValue() {
            return elementValue;
        }

        public static <T, V> ElementBuilder<T, V>  getBuilder() {
            return new ElementBuilder<>();
        }

        public static class ElementBuilder<T, V> {

            private Element<T, V> theElement;
            public ElementBuilder () {
                this.theElement = new Element<>();

            }

            public ElementBuilder elementValue(V value) {
                theElement.elementValue = value;
                return this;
            }

            public ElementBuilder upPointer(Pointer<T> upPointer) {
                theElement.upPointer = upPointer;
                return this;
            }

            public ElementBuilder downPointer(Pointer<T> downPointer) {
                theElement.downPointer = downPointer;
                return this;
            }

            public Pointer<T> getLeftPointer() {
                return leftPointer;
            }

            public Pointer<T> getRightPointer() {
                return rightPointer;
            }

            public Pointer<T> getUpLeftPointer() {
                return upLeftPointer;
            }

            public Pointer<T> getDownRightPointer() {
                return downRightPointer;
            }

            public Pointer<T> getDownLeftPointer() {
                return downLeftPointer;
            }

            public Pointer<T> getUpRightPointer() {
                return upRightPointer;
            }
        }
    }

    public static class XYCoord {
        private Integer x;

        private Integer y;

        public XYCoord(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        public Integer getX() {
            return x;
        }

        public Integer getY() {
            return y;
        }

        public Integer resetX() {
            this.x = 0;
            return this.getX();
        }

        public Integer resetY() {
            this.y = 0;
            return this.getY();
        }

        public Integer incrementX() {
            this.x++;
            return this.getX();
        }

        public Integer incrementY() {
            this.y++;
            return this.getY();
        }

        public Integer decrementX() {
            this.x--;
            return this.getX();
        }

        public Integer decrementY() {
            this.y--;
            return this.getY();
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            XYCoord xyCoord = (XYCoord) o;
            return Objects.equals(getX(), xyCoord.getX()) && Objects.equals(getY(), xyCoord.getY());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getX(), getY());
        }
    }
}
