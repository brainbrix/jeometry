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
package com.jeometry.twod.line;

import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.vector.Vect;
import com.jeometry.twod.line.analytics.Perpendicular;
import com.jeometry.twod.line.analytics.PointInLine;
import com.jeometry.twod.point.RandomPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link PerpLine}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class PerpLineTest {

    /**
     * {@link PerpLine} builds a perpendicular line.
     */
    @Test
    public void buildsPerpendicularLine() {
        final Line line = new RandomLine();
        MatcherAssert.assertThat(
            new Perpendicular(line, new PerpLine(line)).resolve(new Decimal()),
            Matchers.is(true)
        );
    }

    /**
     * {@link PerpLine} builds a perpendicular line to a vertical line.
     */
    @Test
    public void buildsPerpendicularToVerticalLine() {
        final Line line = new VerticalLine();
        MatcherAssert.assertThat(
            new Perpendicular(line, new PerpLine(line)).resolve(new Decimal()),
            Matchers.is(true)
        );
    }

    /**
     * {@link PerpLine} builds a perpendicular line passing by a given point.
     */
    @Test
    public void buildsPerpendicularPassingBy() {
        final Line line = new RandomLine();
        final Vect pnt = new RandomPoint();
        final Line perp = new PerpLine(line, pnt);
        final Decimal field = new Decimal();
        MatcherAssert.assertThat(
            new Perpendicular(line, perp).resolve(field),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new PointInLine(pnt, perp).resolve(field),
            Matchers.equalTo(true)
        );
    }
}
