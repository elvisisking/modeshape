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
package org.jboss.dna.spi.graph;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Comparator;
import net.jcip.annotations.Immutable;
import org.jboss.dna.spi.SpiI18n;

/**
 * @author Randall Hauch
 */
@Immutable
public enum PropertyType {

    STRING("String", ValueComparators.STRING_COMPARATOR, String.class),
    BINARY("Binary", ValueComparators.BINARY_COMPARATOR, Binary.class),
    LONG("Long", ValueComparators.LONG_COMPARATOR, Long.class),
    DOUBLE("Double", ValueComparators.DOUBLE_COMPARATOR, Double.class),
    DECIMAL("Decimal", ValueComparators.DECIMAL_COMPARATOR, BigDecimal.class),
    DATE("Date", ValueComparators.DATE_TIME_COMPARATOR, DateTime.class),
    BOOLEAN("Boolean", ValueComparators.BOOLEAN_COMPARATOR, Boolean.class),
    NAME("Name", ValueComparators.NAME_COMPARATOR, Name.class),
    PATH("Path", ValueComparators.PATH_COMPARATOR, Path.class),
    REFERENCE("Reference", ValueComparators.REFERENCE_COMPARATOR, Reference.class),
    URI("URI", ValueComparators.URI_COMPARATOR, URI.class),
    OBJECT("Object", ValueComparators.OBJECT_COMPARATOR, Object.class);

    private final String name;
    private final Comparator comparator;
    private final Class valueClass;

    private PropertyType( String name, Comparator comparator, Class valueClass ) {
        this.name = name;
        this.comparator = comparator;
        this.valueClass = valueClass;
    }

    public Class getValueClass() {
        return this.valueClass;
    }

    public String getName() {
        return this.name;
    }

    public Comparator getComparator() {
        return this.comparator;
    }

    public boolean isInstance( Object value ) {
        return this.valueClass.isInstance(value);
    }

    public static PropertyType discoverType( Object value ) {
        if (value == null) {
            throw new ValueFormatException(SpiI18n.unableToDiscoverPropertyTypeForNullValue.text());
        }
        for (PropertyType type : PropertyType.values()) {
            if (type == OBJECT) continue;
            if (type.isInstance(value)) return type;
        }
        return OBJECT;
    }
}
