package com.example.sortingalgoritmsfx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class HelloController {
    private static final int size=12;
    private static final int canvasHeight=400;
    private static final int canvasWidth=600;
    private static final int barWidth=canvasWidth/size;

    private int[] array = new int[size];
    private int currentIndex=-1;
    private int secondaryIndex=-1;

    private int currentStep = 0;

    private int time=200;

    @FXML
    private Canvas canvas;
    @FXML
    private Button BSButton;
    @FXML
    private ButtonBar bar;
    @FXML
    private ButtonBar bar2;
    @FXML
    private TextField textField;

    @FXML
    public void initialize() {
        generateRandomArray();
        drawArray();
    }

    private void generateRandomArray() {
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * (canvasHeight - 20));
        }
    }

    private void drawArray() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        for (int i = 0; i < size; i++) {
            int barHeight = array[i];
            gc.setFill(Color.BLACK);
            gc.fillRect(i * barWidth, canvasHeight - barHeight, barWidth, barHeight);
            if(currentIndex!=i) {
                if(secondaryIndex!=i) {
                    gc.setFill(Color.BLUE);
                }else{
                    gc.setFill(Color.DARKCYAN);
                }
            }else{
                gc.setFill(Color.DARKBLUE);
            }
            gc.fillRect(i * barWidth+5, canvasHeight - barHeight+5, barWidth-10, barHeight-10);
        }
    }

    @FXML
    protected void onGenerateButtonClick() {
        generateRandomArray();
        currentIndex=-1;
        secondaryIndex=-1;
        drawArray();
    }

    @FXML
    protected void onBubbleSortButtonClick(){
        try{
            time=Integer.parseInt(textField.getText());
        }catch (Exception e){}
        BubbleSort();
        drawArray();
    }

    private void BubbleSort(){
        bar.setDisable(true);
        bar2.setDisable(true);
        Thread sortingThread = new Thread(() -> {
        int buf;
        try {
            for (int i = 0; i < size - 1; i++) {
                currentIndex = i;
                for (int j = i + 1; j < size; j++) {
                    secondaryIndex = j;
                    if (array[i] >= array[j]) {
                        buf = array[i];
                        array[i] = array[j];
                        array[j] = buf;
                    }
                    drawArray();
                    Thread.sleep(time);
                }
            }
            bar.setDisable(false);
            bar2.setDisable(false);
        }catch (Exception e){
            e.printStackTrace();
        }
        });
        sortingThread.start();
    }
}