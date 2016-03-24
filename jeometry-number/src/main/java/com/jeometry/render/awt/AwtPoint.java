/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2016, Hamdi Douss
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.jeometry.render.awt;

import com.jeometry.geometry.twod.Shape;
import com.jeometry.geometry.twod.point.XyVector;
import com.jeometry.model.algebra.field.Field;
import com.jeometry.model.decimal.Decimal;
import java.awt.Graphics2D;

/**
 * Awt Point painter that draws a point on an AWT graphics.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class AwtPoint extends AbstractAwtPaint {

    /**
     * Ctor.
     * @param field Field for scalar operations
     */
    public AwtPoint(final Field<Double> field) {
        super(field, XyVector.class);
    }

    /**
     * Ctor.
     */
    public AwtPoint() {
        this(new Decimal());
    }

    @Override
    public void draw(final Shape renderable, final Graphics2D graphics,
        final AwtContext context) {
        final int size = 4;
        final XyVector point = (XyVector) renderable.renderable();
        final int scale = context.scale();
        final int width = context.width();
        final int height = context.height();
        final Double xcoor = context.center().dblx();
        final Double ycoor = context.center().dbly();
        final int xpoint = width / 2
            + (int) (
                scale * this.field().actual(point.xcoor()) - xcoor * scale
            ) - size / 2;
        final int ypoint = height / 2
            - (int) (
                scale * this.field().actual(point.ycoor()) + ycoor * scale
            ) - size / 2;
        graphics.drawRect(xpoint, ypoint, size, size);
    }

}
