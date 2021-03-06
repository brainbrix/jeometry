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
package com.aljebra.scalar.condition;

import com.aljebra.field.Field;
import com.aljebra.scalar.Scalar;
import com.aljebra.scalar.Throwing;

/**
 * Predicate interface. A predicate could be resolved to true or false given
 * the field.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Predicate {

    /**
     * Evaluates this predicate on the given field.
     * @param field Scalars field
     * @return A boolean evaluation of the predicate
     */
    boolean resolve(final Field<?> field);

    /**
     * Returns a scalar that evaluates to the first passed scalar if this
     * predicate is true, and evaluates to the second passed scalar if this
     * predicate is false.
     * @param truth Scalar if this predicate is true
     * @param lies Scalar if this predicate is false
     * @return A scalar whose evaluation depends on this predicate
     */
    default Scalar ifElse(final Scalar truth, final Scalar lies) {
        return new Ternary(this, truth, lies);
    }

    /**
     * Returns a scalar that evaluates to the first passed scalar if this
     * predicate is true, and throws the passed exception if this
     * predicate is false.
     * @param truth Scalar if this predicate is true
     * @param err Exception to throw if this predicate is false
     * @return A scalar whose evaluation depends on this predicate
     */
    default Scalar ifElse(final Scalar truth, final RuntimeException err) {
        return this.ifElse(truth, new Throwing(err));
    }
}
