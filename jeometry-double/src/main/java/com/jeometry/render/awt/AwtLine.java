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

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Decimal;
import com.jeometry.model.decimal.DblPoint;
import com.jeometry.twod.Shape;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.analytics.Intercept;
import com.jeometry.twod.line.analytics.Slope;
import com.jeometry.twod.line.analytics.Vertical;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Awt Line painter that draws a line on an AWT graphics.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class AwtLine extends AbstractAwtPaint {

    /**
     * Ctor.
     * @param field Field for scalar operations
     */
    public AwtLine(final Field<Double> field) {
        super(field, Line.class);
    }

    /**
     * Ctor.
     */
    public AwtLine() {
        this(new Decimal());
    }

    @Override
    public void draw(final Shape renderable, final Graphics2D graphics,
        final AwtContext context) {
        final Line line = (Line) renderable.renderable();
        if (new Vertical(line).resolve(this.field())) {
            AwtLine.vertical(graphics, line, context);
        } else {
            this.regular(graphics, line, context);
        }
    }

    /**
     * Draws a regular (non-vertical) line.
     * @param graphics Awt Graphics to draw upon
     * @param line Line to draw
     * @param context AwtContext
     */
    private void regular(final Graphics2D graphics,
        final Line line, final AwtContext context) {
        final int width = context.width();
        final AwtTransform transform = new AwtTransform(context);
        final Double xstart = transform.inverse(new Point(0, 0)).dblx();
        final Double xend = transform.inverse(new Point(width, 0)).dblx();
        final Double slope = this.field().actual(new Slope(line));
        final Double intercept = this.field().actual(new Intercept(line));
        final Point start = transform.transform(
            new DblPoint(xstart, slope * xstart + intercept)
        );
        final Point end = transform.transform(
            new DblPoint(xend, slope * xend + intercept)
        );
        graphics.drawLine(start.x, start.y, end.x, end.y);
    }

    /**
     * Draws a vertical line.
     * @param graphics Awt Graphics to draw upon
     * @param line Line to draw
     * @param context AwtContext
     */
    private static void vertical(final Graphics2D graphics, final Line line,
        final AwtContext context) {
        final Point point = new AwtTransform(context).transform(line.point());
        graphics.drawLine(point.x, 0, point.x, context.height());
    }

}
