/*
 * Copyright (c) 2017 Helmut Neemann
 * Use of this source code is governed by the GPL v3 license
 * that can be found in the LICENSE file.
 */
package de.neemann.digiblock.core.flipflops;

import de.neemann.digiblock.core.BitsException;
import de.neemann.digiblock.core.NodeException;
import de.neemann.digiblock.core.ObservableValue;
import de.neemann.digiblock.core.ObservableValues;
import de.neemann.digiblock.core.element.ElementAttributes;
import de.neemann.digiblock.core.element.ElementTypeDescription;
import de.neemann.digiblock.core.element.Keys;

import static de.neemann.digiblock.core.ObservableValues.ovs;
import static de.neemann.digiblock.core.element.PinInfo.input;

/**
 * The D Flipflop
 */
public class FlipflopDAsync extends FlipflopD {

    /**
     * The D-FF description
     */
    public static final ElementTypeDescription DESCRIPTION
            = new ElementTypeDescription("D_FF_AS", FlipflopDAsync.class,
            input("Set"), input("D"), input("C").setClock(), input("Clr"))
            .addAttribute(Keys.ROTATE)
            .addAttribute(Keys.MIRROR)
            .addAttribute(Keys.BITS)
            .addAttribute(Keys.LABEL)
            .addAttribute(Keys.DEFAULT)
            .addAttribute(Keys.INVERTER_CONFIG)
            .addAttribute(Keys.VALUE_IS_PROBE);

    private ObservableValue setVal;
    private ObservableValue clrVal;

    /**
     * Creates a new instance
     *
     * @param attributes the attributes
     */
    public FlipflopDAsync(ElementAttributes attributes) {
        super(attributes);
    }

    @Override
    public void readInputs() throws NodeException {
        super.readInputs();
        if (setVal.getBool()) setValue(-1);
        else if (clrVal.getBool()) setValue(0);
    }

    @Override
    public void setInputs(ObservableValues inputs) throws BitsException {
        super.setInputs(ovs(inputs.get(1), inputs.get(2)));
        setVal = inputs.get(0).addObserverToValue(this).checkBits(1, this, 0);
        clrVal = inputs.get(3).addObserverToValue(this).checkBits(1, this, 3);
    }

}
