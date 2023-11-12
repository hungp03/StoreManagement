/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.storemanagement.model;

/**
 *
 * @author Hung Pham
 */
public class CategoryItem extends BaseEntity{

    private String name;

    public CategoryItem(int id, String name) {
        super(id);
        this.name = name;
    }

    @Override
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
