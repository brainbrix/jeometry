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
package com.jeometry.geometry.twod.line;

import com.jeometry.geometry.twod.point.MidSegPoint;
import com.jeometry.geometry.twod.segment.Segment;
import com.jeometry.model.algebra.field.OrderedField;
import com.jeometry.model.algebra.vector.Vect;
import lombok.ToString;

/**
 * A line defined by being the perpendicular bisector of a segment.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public final class PerpBisector implements Line {

    /**
     * Scalar field.
     */
    private final OrderedField<?> field;

    /**
     * The segment.
     */
    private final Segment seg;

    /**
     * Constructor.
     * @param seg The segment to bisect
     * @param field Field for scalar randomization
     */
    public PerpBisector(final Segment seg, final OrderedField<?> field) {
        this.seg = seg;
        this.field = field;
    }

    @Override
    public Vect direction() {
        return new PerpLine(new SgtLine(this.seg), this.field).direction();
    }

    @Override
    public Vect point() {
        return new MidSegPoint(this.seg, this.field);
    }

}