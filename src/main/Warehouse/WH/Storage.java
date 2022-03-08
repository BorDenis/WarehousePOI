package WH;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Storage {

    private Map<String, Integer> storageMap = new HashMap<>();
    private int size = 1000;
    Scanner sc = new Scanner(System.in);
    String command;

    public Storage (){
        createNewStorage(size);
    }

    public Storage(int size){
        this();
        this.size = size;
    }

    public Storage(String defaultStorage){ // параметр который не используется, нужен для того что бы отличить конструктор от основного
        this();
        storageMap.put("огурцы", 99);
        storageMap.put("помидоры", 45);
        storageMap.put("редиска", 55);
        storageMap.put("баклажаны", 20);
        storageMap.put("укроп", 32);
        storageMap.put("лук", 38);
        storageMap.put("арбузы", 10);
        storageMap.put("капуста", 201);


    }

    public void createNewStorage(int size) {
        this.storageMap = new HashMap<>();
    }

    public void addNewItem(String item, int value){
        if (getCount() == size) System.out.println("Хранилище полностью заполнено. Невозможно добавить.");
        else if (getCount()+value > size) {
            System.out.print("Количество превышает размер хранилища. Вы можете \"отменить\"/\"cancel\" или " +
                    "\"добавить\"/\"add\" количество в размере " + (size-getCount()) + "\nЧто выбираете: ");
            while (true){
                command = sc.nextLine();
                if (command.equals("отменить") || command.equals("cancel")) {
                    System.out.println("Добавление отменено.");
                    break;
                }
                else if (command.equals("добавить") || command.equals("add")) {
                    storageMap.put(item, size-getCount());
                    System.out.println("Позиция успешно добавлена/обновлена.");
                    break;
                }
                else {
                    System.out.print("Введена неверная команда. Попробуйтес снова: ");
                }
            }
        }
        else {
            storageMap.put(item, value);
            System.out.println("Позиция успешно добавлена/обновлена.");
        }

    }

    public void getPrintedMap(){
        if (storageMap.isEmpty()) System.out.println("Хранилище пусто.");
        else{
            for (Map.Entry<String, Integer> pair : storageMap.entrySet()) //перебираем все ключ:значения мапы
            {
                String key = pair.getKey();
                int value = pair.getValue();
                System.out.println(key + " = " + value);
            }
        }
    }

    public String find(String item){
        if (String.valueOf(storageMap.get(item)).equals("null")) {
            return null;
        }
        else {
            return String.valueOf(storageMap.get(item));
        }
    }

    public void removeItem(String item){
        storageMap.remove(item);
    }

    public int getCount(){ //перебирает все значение позиций и возвращет их сумму
        int value = 0;
        for (Map.Entry<String, Integer> pair : storageMap.entrySet())
        {
            value += pair.getValue();
        }
        return value;
    }

    public int getStoreSize(){
        return size;
    }

    public void deleteAllItems(){
        storageMap.clear();
    }
    public Map<String, Integer> getMap(){
        return storageMap;
    }



}

