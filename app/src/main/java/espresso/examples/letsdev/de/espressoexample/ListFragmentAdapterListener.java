/*
 * Project: EspressoExample
 *
 * User: rkoesters
 * Date: 07.06.2018
 *
 * This code is copyright (c) 2018 let's dev GmbH & Co. KG
 * URL: https://www.letsdev.de
 * e-Mail: contact@letsdev.de
 */
package espresso.examples.letsdev.de.espressoexample;

public interface ListFragmentAdapterListener {

    void onListUpdated(int numberOfCheckedItems, int numberOfItems);
}
