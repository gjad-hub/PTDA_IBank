/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pt.ua.ibank.interfaces.internalFrames.staff.profile.deposit;

import pt.ua.ibank.interfaces.internalFrames.staff.profile.personal.*;

/**
 *
 * @author ricar
 */
public interface DepositTableActionEvent {

    public void onAcceptTransaction(int row);

    public void onRejectTransaction(int row);

}
