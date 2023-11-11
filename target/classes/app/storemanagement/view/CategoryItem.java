/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.storemanagement.view;

/**
 *
 * @author Hung Pham
 */
public class CategoryItem {

    private int id;
    private String name;

    public CategoryItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // ComboBox sẽ sử dụng phương thức này để hiển thị tên của mỗi mục
    @Override
    public String toString() {
        return name;
    }

}
