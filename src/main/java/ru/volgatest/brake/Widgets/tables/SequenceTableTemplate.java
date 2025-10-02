/*
 * To change this license tableHeader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.volgatest.brake.Widgets.tables;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Abstract table that can add/remove/copy and select elements.
 *  Allows disable buttons
 */
abstract public class SequenceTableTemplate<T> extends VBox{
    public static final int ELEMENT_SIZE = 30;
    
    Button addBtn = new Button("+");
    Button removeBtn = new Button("-");  
    Button copyBtn = new Button("Copy");  
    Button upBtn = new Button("\u25B2");  
    Button downBtn = new Button("\u25BC");
    
    protected HBox buttonBox = new HBox(3);
    //GridPane tableHeader = new GridPane();
    public HBox tableHeader = new HBox(5);
    
    VBox statesBox = new VBox(2);
    ScrollPane statesScrollPane = new ScrollPane(statesBox);
    
    //public ObservableList<T> items = FXCollections.observableArrayList();
    public List<T> items;
    
    public Map<T,SequenceItemView> views = new HashMap<>();
    
    //public SequenceTableTemplate(boolean twoClutch, int synchronizersNum, List<SynchStateDescriptor> descriptors){
    
    public SequenceTableTemplate(List<T> initItemsList){
        this.items = initItemsList;
        
        addBtn.setMinSize(ELEMENT_SIZE, ELEMENT_SIZE);
        addBtn.setMaxSize(ELEMENT_SIZE, ELEMENT_SIZE);
        addBtn.setOnAction(eh -> {
            int selectedIndex = getSelectedIndex();
            T newItem = createNewItem();
            if(selectedIndex < 0 || selectedIndex == (items.size()-1)){
                items.add(newItem);
            }else{
                items.add(selectedIndex, newItem);
            }
            onItemsListChange();
        });
        
        removeBtn.setMinSize(ELEMENT_SIZE, ELEMENT_SIZE);
        removeBtn.setMaxSize(ELEMENT_SIZE, ELEMENT_SIZE);
        removeBtn.setOnAction(eh -> {
            int selectedIndex = getSelectedIndex();
            if(selectedIndex < 0){
                return;
            }
            items.remove(selectedIndex);
            onItemsListChange();
        });
        
        copyBtn.setMinSize(ELEMENT_SIZE*2, ELEMENT_SIZE);
        copyBtn.setMaxSize(ELEMENT_SIZE*2, ELEMENT_SIZE);
        copyBtn.setOnAction(eh -> {
            int selectedIndex = getSelectedIndex();
            if(selectedIndex < 0){
                return;
            }
            T itemClone = copyItem(items.get(selectedIndex));
            if(itemClone == null){
                return;
            }
            if(selectedIndex == (items.size()-1)){
                items.add(itemClone);
            }else{
                items.add(selectedIndex, itemClone);
            }
            onItemsListChange();
        });
        
        upBtn.setMinSize(ELEMENT_SIZE*2, ELEMENT_SIZE);
        upBtn.setMaxSize(ELEMENT_SIZE*2, ELEMENT_SIZE);
        upBtn.setOnAction(eh -> {
            int selectedIndex = getSelectedIndex();
            if(selectedIndex <= 0){
                return;
            }
            T removedItem = items.remove(selectedIndex);
            items.add(selectedIndex-1, removedItem);
            onItemsListChange();
        });
        
        downBtn.setMinSize(ELEMENT_SIZE*2, ELEMENT_SIZE);
        downBtn.setMaxSize(ELEMENT_SIZE*2, ELEMENT_SIZE);
        downBtn.setOnAction(eh -> {
            int selectedIndex = getSelectedIndex();
            if(selectedIndex == (items.size()-1) || selectedIndex < 0){
                return;
            }            
            T removedItem = items.remove(selectedIndex);
            items.add(selectedIndex+1, removedItem);
            onItemsListChange();
        });
        
        
        buttonBox.getChildren().addAll(addBtn, removeBtn, copyBtn, upBtn, downBtn);
        
        //statesScrollPane.setPrefSize(2000, 2000);
        
        statesScrollPane.setPrefViewportHeight(1000);
        statesScrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        statesScrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);  
        statesScrollPane.setFitToHeight(true);
        statesScrollPane.setFitToWidth(true);
        //statesScrollPane.setPrefHeight(600);  
        
        this.getChildren().addAll(buttonBox,tableHeader,statesScrollPane);
    }
    

    
    private int getSelectedIndex(){
        for(var lineNode: statesBox.getChildren()){
            SequenceItemView state = (SequenceItemView)(((HBox)lineNode).getChildren().get(1));
            if(state.isSelected()){
                return state.getIndex();
            }
        }
        return -1;
    }
    
    public void onItemsListChange(){
        views.clear();
        statesBox.getChildren().clear();
        
        List<T> displayedItemsList = items.stream().filter(item -> isItemDisplayed(item)).toList();        
        
        for(int itemIndex=0; itemIndex < displayedItemsList.size(); itemIndex++){
            SequenceItemView itemView = createItemView(displayedItemsList.get(itemIndex));            
            itemView.setIndex(itemIndex);
            
            Label itemTableIndexLb = new Label(String.valueOf(itemIndex));
            itemTableIndexLb.setMaxWidth(itemView.getHight());
            itemTableIndexLb.setMinWidth(itemView.getHight());
            itemTableIndexLb.setPrefHeight(itemView.getHight());
            itemTableIndexLb.setAlignment(Pos.CENTER);
            
            
            HBox box = new HBox(itemTableIndexLb, itemView.getPane());
            box.setStyle("-fx-border-color: derive(-fx-base, 10%);");
            box.setPrefHeight(itemView.getHight());
            
            box.setOnMouseClicked(eh -> {
                setLineSelected(itemView);
            });
            statesBox.getChildren().add(box);
            views.put(displayedItemsList.get(itemIndex),itemView);
        }
    }
    
    public void setLineSelected(int i){
        if(i<0 || statesBox.getChildren().size()<=i){
            return;
        }
        setLineSelected((SequenceItemView)(((HBox)statesBox.getChildren().get(i)).getChildren().get(1)));    
    }
    
    public void setLineSelected(SequenceItemView line){
        for(var lineNode: statesBox.getChildren()){
            Label lineIndexLb = (Label)(((HBox)lineNode).getChildren().get(0));
            SequenceItemView state = (SequenceItemView)(((HBox)lineNode).getChildren().get(1));
            
            lineIndexLb.setStyle(state == line?"-fx-border-color: LIGHTSTEELBLUE;" : "-fx-border-color: derive(-fx-base, 10%);");
            state.setSelected(state == line);
        }
    }   
    
    public void enableButtons(boolean enable, boolean hide){
        buttonBox.setDisable(!enable);
        buttonBox.setVisible(hide);
    }
    
    public void setTablePrefWidth(int prefWidth){
        statesScrollPane.setPrefWidth(prefWidth);
    }
    
    public void setTableEditable(boolean shown){
        buttonBox.setManaged(shown);
        buttonBox.setVisible(shown);
    }
    
    abstract public boolean isItemDisplayed(T item);
    abstract public T createNewItem();
    abstract public T copyItem(T t);
    abstract public SequenceItemView createItemView(T item);
    //abstract void fillTableLabelsHeader();
}



/*
    class SynchStateView extends HBox{
        public final SynchStateDescriptor descriptor;
        public final int sequenceIndex; 
        public boolean selected;
        
        LimitedFloatField gearRatioField;
        public SynchStateView(int sequenceIndex, SynchStateDescriptor descriptor){
            super(3);
            this.descriptor = descriptor;
            this.sequenceIndex = sequenceIndex;
            Label lineLb = new Label(String.valueOf(sequenceIndex));
            lineLb.setMaxSize(PositionSelector.ONE_CELL_WIDTH,PositionSelector.HIGHT);
            lineLb.setMinSize(PositionSelector.ONE_CELL_WIDTH,PositionSelector.HIGHT);
            lineLb.setAlignment(Pos.CENTER);
            lineLb.setOnMouseClicked(eh -> {
                setLineSelected(this);
            });
            
            this.getChildren().add(lineLb);
                        
            if(twoClutch){
                PositionSelector clutchSelector = new PositionSelector(descriptor.isFirstClutch, null, "cl 1","cl 2");
                clutchSelector.currentPosition.addListener(il -> descriptor.isFirstClutch = clutchSelector.currentPosition.getValue() == 1);
                this.getChildren().add(clutchSelector);
            }
            for(int synchIndex=0; synchIndex<synchronizersNum; synchIndex++){    
                PositionSelector positionSelector = new PositionSelector(true, null, "-1","0","1");                
                positionSelector.selectPosition(descriptor.synchStates[synchIndex]);
                positionSelector.setIntId(synchIndex);
                
                positionSelector.currentPosition.addListener(il -> {
                    byte state = switch(positionSelector.currentPosition.getValue()){
                        case 0 -> -1;
                        case 1 -> 0;
                        case 2 -> 1;
                        default -> 0;
                    };
                    descriptor.synchStates[positionSelector.getIntId()]=state;
                });
                this.getChildren().add(positionSelector);
            }
            
            gearRatioField = new LimitedFloatField(null, null, -50, 50, descriptor.gearRatio); 
            gearRatioField.setFieldSize(PositionSelector.ONE_CELL_WIDTH*3,PositionSelector.HIGHT);
            gearRatioField.value.addListener(il -> {
                descriptor.gearRatio = gearRatioField.getValue();
                changeGearRatioFieldColor(descriptor.gearRatio);
            });
            this.getChildren().add(gearRatioField);
            
            changeGearRatioFieldColor(descriptor.gearRatio);            
            setSelected(false);
        }
        
        private void changeGearRatioFieldColor(float newValue){
            if(newValue < 0){
                gearRatioField.setStyle("-fx-border-color: ORANGE;");
            }else if(newValue == 0){
                gearRatioField.setStyle("-fx-border-color: LIGHTSTEELBLUE;");
            }else{
                gearRatioField.setStyle("-fx-border-color: GREEN;");
            }
        }
        
        public void setSelected(boolean selected){
            this.selected = selected;
            this.setStyle(selected?"-fx-border-color: LIGHTSTEELBLUE;" : "-fx-border-color: derive(-fx-base, 10%);");  
        }
    }
*/

/*
private byte getSynchState(int synchIndex){
    return ByteBuffer.allocate(8).putLong(forbiddenStates.get(sequenceIndex)).array()[synchIndex];
}

private void setSynchState(int synchIndex, byte value){
    ByteBuffer buf = ByteBuffer.allocate(8);            
    buf.putLong(forbiddenStates.get(sequenceIndex));
    buf.put(synchIndex, value);

    forbiddenStates.set(sequenceIndex, Long.valueOf(buf.getLong(0)));
}
int forvardGearsNum;
int rearGearsNum;
int clutchNum;

public SequenceTableTemplate(int forvardGearsNum,int rearGearsNum, int clutchNum){
}
*/