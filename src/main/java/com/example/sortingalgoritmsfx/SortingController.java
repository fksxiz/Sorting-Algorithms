package com.example.sortingalgoritmsfx;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class SortingController {
    private static final int size=14;
    private static final int canvasHeight=400;
    private static final int canvasWidth=600;
    private static final int barWidth=canvasWidth/size;

    private int[] array = new int[size];
    private int currentIndex=-1;
    private int secondaryIndex=-1;

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

    //Отрисовка массива
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

    //События
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
        }catch (Exception ignored){}
        BubbleSort();
        drawArray();
    }

    @FXML
    protected void onSelectionSortButtonClick(){
        try{
            time=Integer.parseInt(textField.getText());
        }catch (Exception ignored){}
        SelectionSort();
        drawArray();
    }

    @FXML
    protected void onQuickSortButtonClick(){
        try{
            time=Integer.parseInt(textField.getText());
        }catch (Exception ignored){}
        //QuickSort();
        drawArray();
    }

    @FXML
    protected void onInsertionSortButtonClick(){
        try{
            time=Integer.parseInt(textField.getText());
        }catch (Exception ignored){}
        InsertionSort();
        drawArray();
    }

    //Методы сортировок
    private void BubbleSort(){
        bar.setDisable(true);
        bar2.setDisable(true);
        Thread sortingThread = new Thread(() -> {
        try {
            for (int i = 0; i < size - 1; i++) {
                for (int j = 0; j < size-i-1; j++) {
                    secondaryIndex = j+1;
                    currentIndex = j;
                    if (array[j] > array[j+1]) {
                        swap(array,j,j+1);
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

    private void SelectionSort(){
        bar.setDisable(true);
        bar2.setDisable(true);
        Thread sortingThread = new Thread(() -> {
            try {
                for (int i = 0; i < size - 1; i++) {
                    currentIndex = i;
                    for (int j = i + 1; j < size; j++) {
                        secondaryIndex = j;
                        if (array[i] >= array[j]) {
                            swap(array,i,j);
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

    private void InsertionSort(){
        bar.setDisable(true);
        bar2.setDisable(true);
        Thread sortingThread = new Thread(() -> {
            try {
                for (int i=1;i<size;i++){
                    int key =array[i];
                    int j =i-1;
                    currentIndex=i;
                    while(j>=0&&array[j]>key){
                        array[j+1]=array[j];
                        j--;
                        secondaryIndex=j;
                        drawArray();
                        Thread.sleep(time);
                    }
                    array[j+1]=key;
                    drawArray();
                }

                bar.setDisable(false);
                bar2.setDisable(false);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        sortingThread.start();
    }

    private void QuickSort(int[] array, int low, int high){
        bar.setDisable(true);
        bar2.setDisable(true);
        Thread sortingThread = new Thread(() -> {
            try {
                if(low<high){

                }

                bar.setDisable(false);
                bar2.setDisable(false);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        sortingThread.start();
    }

    private static int partition(int[] arr, int low, int high){
        int pivot =arr[high];
        int i =low-1;

        for(int j =low;j<high;j++){
            if(arr[j]<=pivot){
                i++;
                swap(arr,i,j);
            }
        }

        swap(arr,i+1,high);
        return i+1;

    }

    private void generateRandomArray() {
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * (canvasHeight - 20));
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int buf = arr[i];
        arr[i] = arr[j];
        arr[j] = buf;
    }
}