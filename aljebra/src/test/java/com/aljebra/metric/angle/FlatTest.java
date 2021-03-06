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
package com.aljebra.metric.angle;

import com.aljebra.metric.InnerProduct;
import com.aljebra.vector.Vect;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Flat}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FlatTest {

    /**
     * {@link Flat} resolves to an flat angle.
     */
    @Test
    public void resolvesFlatAngle() {
        final double error = 1.e-6;
        final InnerProduct product = Mockito.mock(InnerProduct.class);
        MatcherAssert.assertThat(
            new Flat().resolve(product).doubleValue(),
            Matchers.closeTo(Math.PI, error)
        );
        MatcherAssert.assertThat(
            new Flat().flat(product), Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new Flat().right(product), Matchers.is(false)
        );
    }

    /**
     * {@link Flat} respects equals and hashcode.
     */
    @Test
    public void respectsEqualAndHashcode() {
        MatcherAssert.assertThat(
            new Flat(),
            Matchers.equalTo(new Flat())
        );
        MatcherAssert.assertThat(
            new Flat().hashCode(),
            Matchers.equalTo(new Flat().hashCode())
        );
        MatcherAssert.assertThat(
            new Flat(),
            Matchers.not(
                Matchers.equalTo(
                    new VectsDegrees(
                        Mockito.mock(Vect.class), Mockito.mock(Vect.class)
                    )
                )
            )
        );
    }
}
