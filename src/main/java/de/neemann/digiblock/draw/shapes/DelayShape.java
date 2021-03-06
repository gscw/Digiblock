/*
 * Copyright (c) 2016 Helmut Neemann
 * Use of this source code is governed by the GPL v3 license
 * that can be found in the LICENSE file.
 */
package de.neemann.digiblock.draw.shapes;

import de.neemann.digiblock.core.Observer;
import de.neemann.digiblock.draw.elements.IOState;
import de.neemann.digiblock.draw.elements.Pin;
import de.neemann.digiblock.draw.elements.Pins;
import de.neemann.digiblock.draw.graphics.Graphic;
import de.neemann.digiblock.draw.graphics.Polygon;
import de.neemann.digiblock.draw.graphics.Style;
import de.neemann.digiblock.draw.graphics.Vector;

import static de.neemann.digiblock.core.element.PinInfo.input;
import static de.neemann.digiblock.core.element.PinInfo.output;
import static de.neemann.digiblock.draw.shapes.GenericShape.SIZE;
import static de.neemann.digiblock.draw.shapes.GenericShape.SIZE2;

/**
 * The delays shape
 */
public class DelayShape implements Shape {


    /**
     * Creates a new instance
     */
    public DelayShape() {
    }

    @Override
    public Pins getPins() {
        return new Pins()
                .add(new Pin(new Vector(SIZE * 2, 0), output("out")))
                .add(new Pin(new Vector(0, 0), input("in")));
    }

    @Override
    public Interactor applyStateMonitor(IOState ioState, Observer guiObserver) {
        return null;
    }

    @Override
    public void drawTo(Graphic graphic, Style heighLight) {
        graphic.drawPolygon(
                new Polygon(true)
                        .add(1, -SIZE2)
                        .add(SIZE * 2 - 1, -SIZE2)
                        .add(SIZE * 2 - 1, SIZE2)
                        .add(1, SIZE2), Style.NORMAL);
        graphic.drawLine(new Vector(SIZE2, 0), new Vector(SIZE * 2 - SIZE2, 0), Style.THIN);
        int bar = SIZE2 / 2;
        graphic.drawLine(new Vector(SIZE2, bar), new Vector(SIZE2, -bar), Style.THIN);
        graphic.drawLine(new Vector(SIZE * 2 - SIZE2, bar), new Vector(SIZE * 2 - SIZE2, -bar), Style.THIN);
    }
}
