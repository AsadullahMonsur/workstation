package sample;


import javafx.animation.FillTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Process implements Runnable{

    Rectangle anchors[][];
    int array[][];
    int x,y;

    int selector;

    Process(Rectangle ap,int x,int y,int [][]array, Rectangle anchors[][], int selector){
        this.anchors = anchors;
        this.array = array;
        x = x;
        y = y;
        this.selector = selector;

    }

    public void run(){
        try{
            Rectangle nodes[] = new Rectangle[9];
            nodes[0] = anchors[x][y];
            array[x][y] = 1;

            FillTransition f0 = new FillTransition();
            FillTransition f1 = new FillTransition();
            FillTransition f2 = new FillTransition();
            FillTransition f3 = new FillTransition();
            FillTransition f4 = new FillTransition();
            FillTransition f5 = new FillTransition();
            FillTransition f6 = new FillTransition();
            FillTransition f7 = new FillTransition();
            FillTransition f8 = new FillTransition();


            f0.setAutoReverse(true);

            f0.setCycleCount(FillTransition.INDEFINITE);

            f0.setDuration(Duration.millis(3000));

            f0.setFromValue(Color.GREEN);

            f0.setToValue(Color.BLUE);

            f0.setShape(nodes[0]);


            if((x+2)<8 && (y-1)>=0 && (x+2)>=0 && (y-1)<8){

                if(array[x+2][y-1]!=1){
                    nodes[1] = anchors[x+2][y-1];
                    array[x+2][y-1] = 1;


                    f1.setAutoReverse(true);

                    f1.setCycleCount(FillTransition.INDEFINITE);

                    f1.setDuration(Duration.millis(3000));

                    f1.setFromValue(Color.GREEN);

                    f1.setToValue(Color.BLUE);

                    f1.setShape(nodes[1]);

                }

            }
            if((x+2)<8 && (y+1)>=0 && (x+2)>=0 && (y+1)<8){

                if(array[x+2][y+1]!=1){
                    nodes[2] = anchors[x+2][y+1];
                    array[x+2][y+1] = 1;

                    f2.setAutoReverse(true);

                    f2.setCycleCount(FillTransition.INDEFINITE);

                    f2.setDuration(Duration.millis(3000));

                    f2.setFromValue(Color.GREEN);

                    f2.setToValue(Color.BLUE);

                    f2.setShape(nodes[2]);
                }

            }
            if((x+1)<8 && (y+2)>=0 && (x+1)>=0 && (y+2)<8){

                if(array[x+1][y+2]!=1){
                    nodes[3] = anchors[x+1][y+2];
                    array[x+1][y+2] = 1;

                    f3.setAutoReverse(true);

                    f3.setCycleCount(FillTransition.INDEFINITE);

                    f3.setDuration(Duration.millis(3000));

                    f3.setFromValue(Color.GREEN);

                    f3.setToValue(Color.BLUE);

                    f3.setShape(nodes[3]);
                }
            }
            if((x-1)<8 && (y+2)>=0 && (x-1)>=0 && (y+2)<8){

                if(array[x-1][y+2]!=1){
                    nodes[4] = anchors[x-1][y+2];
                    array[x-1][y+2] = 1;

                    f4.setAutoReverse(true);

                    f4.setCycleCount(FillTransition.INDEFINITE);

                    f4.setDuration(Duration.millis(3000));

                    f4.setFromValue(Color.GREEN);

                    f4.setToValue(Color.BLUE);

                    f4.setShape(nodes[4]);
                }

            }
            if((x-2)<8 && (y+1)>=0 && (x-2)>=0 && (y+1)<8){

                if(array[x-2][y+1]!=1){
                    nodes[5] = anchors[x-2][y+1];
                    array[x-2][y+1] = 1;

                    f5.setAutoReverse(true);

                    f5.setCycleCount(FillTransition.INDEFINITE);

                    f5.setDuration(Duration.millis(3000));

                    f5.setFromValue(Color.GREEN);

                    f5.setToValue(Color.BLUE);

                    f5.setShape(nodes[5]);
                }
            }
            if((x-2)<8 && (y-1)>=0 && (x-2)>=0 && (y-1)<8){

                if(array[x-2][y-1]!=1){
                    nodes[6] = anchors[x-2][y-1];
                    array[x-2][y-1] = 1;

                    f6.setAutoReverse(true);

                    f6.setCycleCount(FillTransition.INDEFINITE);

                    f6.setDuration(Duration.millis(3000));

                    f6.setFromValue(Color.GREEN);

                    f6.setToValue(Color.BLUE);

                    f6.setShape(nodes[6]);
                }
            }
            if((x-1)<8 && (y-2)>=0 && (x-1)>=0 && (y-2)<8){

                if(array[x-1][y-2]!=1){
                    nodes[7] = anchors[x-1][y-2];
                    array[x-1][y-2] = 1;

                    f7.setAutoReverse(true);

                    f7.setCycleCount(FillTransition.INDEFINITE);

                    f7.setDuration(Duration.millis(3000));

                    f7.setFromValue(Color.GREEN);

                    f7.setToValue(Color.BLUE);

                    f7.setShape(nodes[7]);
                }

            }
            if((x+1)<8 && (y-2)>=0 && (x+1)>=0 && (y-2)<8){

                if(array[x+1][y-2]!=1){
                    nodes[8] = anchors[x+1][y-2];
                    array[x+1][y-2] = 1;

                    f8.setAutoReverse(true);

                    f8.setCycleCount(FillTransition.INDEFINITE);

                    f8.setDuration(Duration.millis(3000));

                    f8.setFromValue(Color.GREEN);

                    f8.setToValue(Color.BLUE);

                    f8.setShape(nodes[8]);
                }
            }

            SequentialTransition s =  new SequentialTransition(f0,f1,f2,f3,f4,f5,f6,f7,f8);
            s.play();

        }
        catch(Exception e){
            System.out.println("Exception");
        }

    }

}
