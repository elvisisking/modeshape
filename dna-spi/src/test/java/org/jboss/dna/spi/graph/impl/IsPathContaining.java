/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors. 
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.dna.spi.graph.impl;

import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsEqual.equalTo;
import java.util.ArrayList;
import java.util.Collection;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.jboss.dna.spi.graph.Path;
import org.jboss.dna.spi.graph.PathFactory;
import org.jboss.dna.spi.graph.Path.Segment;
import org.junit.matchers.TypeSafeMatcher;

/**
 * @author Randall Hauch
 */
public class IsPathContaining extends TypeSafeMatcher<Iterable<Segment>> {

    private final Matcher<? super Segment> elementMatcher;

    public IsPathContaining( Matcher<? super Segment> elementMatcher ) {
        this.elementMatcher = elementMatcher;
    }

    @Override
    public boolean matchesSafely( Iterable<Segment> collection ) {
        for (Segment item : collection) {
            if (elementMatcher.matches(item)) {
                return true;
            }
        }
        return false;
    }

    public void describeTo( Description description ) {
        description.appendText("a path containing ").appendDescriptionOf(elementMatcher);
    }

    @Factory
    public static Matcher<Iterable<Segment>> hasSegment( Matcher<? super Segment> elementMatcher ) {
        return new IsPathContaining(elementMatcher);
    }

    @Factory
    public static Matcher<Iterable<Segment>> hasSegment( Segment element ) {
        return hasSegment(equalTo(element));
    }

    @Factory
    public static Matcher<Iterable<Segment>> hasSegments( Matcher<Path.Segment>... elementMatchers ) {
        Collection<Matcher<? extends Iterable<Path.Segment>>> all = new ArrayList<Matcher<? extends Iterable<Path.Segment>>>(elementMatchers.length);

        for (Matcher<Path.Segment> elementMatcher : elementMatchers) {
            Matcher<Iterable<Path.Segment>> itemMatcher = hasSegment(elementMatcher);
            all.add(itemMatcher);
        }

        return allOf(all);
    }

    @Factory
    public static Matcher<Iterable<Segment>> hasSegments( Segment... elements ) {
        Collection<Matcher<? extends Iterable<Segment>>> all = new ArrayList<Matcher<? extends Iterable<Segment>>>(elements.length);
        for (Segment element : elements) {
            all.add(hasSegment(element));
        }
        return allOf(all);
    }

    @Factory
    public static Matcher<Iterable<Segment>> hasSegment( PathFactory pathFactory, String element ) {
        Path.Segment segment = pathFactory.createSegment(element);
        return hasSegment(equalTo(segment));
    }

    @Factory
    public static Matcher<Iterable<Segment>> hasSegments( PathFactory pathFactory, String... segments ) {
        Collection<Matcher<? extends Iterable<Segment>>> all = new ArrayList<Matcher<? extends Iterable<Segment>>>(segments.length);
        for (String element : segments) {
            all.add(hasSegment(pathFactory, element));
        }
        return allOf(all);
    }
}
