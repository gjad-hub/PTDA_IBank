/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.Staff.components.profile.tablecomponents;

/**
 *
 * @author ricar
 */
public interface ProfileTableActionEvent {

    public void onEdit(int row);

    public void onCancel(int row);

    public void onSave(int row, String value);
}
