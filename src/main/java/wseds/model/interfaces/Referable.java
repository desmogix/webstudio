/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wseds.model.interfaces;

import java.util.ArrayList;

/**
 *
 * @author luigi@santivetti
 */
public interface Referable
{
    public ArrayList<Referable> getReference();

}
