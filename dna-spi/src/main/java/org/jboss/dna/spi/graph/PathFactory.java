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

/**
 * A factory for creating {@link Path paths}.
 * @author Randall Hauch
 */
public interface PathFactory extends ValueFactory<Path> {

    /**
     * Create an absolute path with the supplied segment names, in order. If no segments are provided, the result will be the root
     * path.
     * @param segmentNames the names of the segments
     * @return the new path
     * @throws IllegalArgumentException if at least one segment name is provided and if any of the supplied segment names are null
     */
    Path create( Name... segmentNames );

    /**
     * Create an absolute path with the supplied segments, in order. If no segments are provided, the result will be the root
     * path.
     * @param segments the segments
     * @return the new path
     * @throws IllegalArgumentException if at least one segment is provided and if any of the supplied segments are null
     */
    Path create( Path.Segment... segments );

    /**
     * Create a relative path with the supplied segment names, in order. If no segments are provided, the result will be the root
     * path.
     * @param segmentNames the names of the segments
     * @return the new path
     * @throws IllegalArgumentException if at least one segment name is provided and if any of the supplied segment names are null
     */
    Path createRelativePath( Name... segmentNames );

    /**
     * Create a relative path with the supplied segments, in order. If no segments are provided, the result will be the root path.
     * @param segments the segments
     * @return the new path
     * @throws IllegalArgumentException if at least one segment is provided and if any of the supplied segments are null
     */
    Path createRelativePath( Path.Segment... segments );

    /**
     * Create a path by appending the supplied names to the parent path.
     * @param parentPath the path that is to provide the basis for the new path
     * @param segmentName the name of the segment to be appended to the parent path
     * @param index the index for the new segment
     * @return the new path
     * @throws IllegalArgumentException if the parent path reference or the segment name is null, or if the index is invalid
     */
    Path create( Path parentPath, Name segmentName, int index );

    /**
     * Create a path by appending the supplied names to the parent path. If no names are appended, the parent path is returned.
     * @param parentPath the path that is to provide the basis for the new path
     * @param segmentNames the names of the segments that are to be appended, in order, to the parent path
     * @return the new path
     * @throws IllegalArgumentException if the parent path reference is null, or if at least one segment name is provided and if
     * any of the supplied segment names are null
     */
    Path create( Path parentPath, Name... segmentNames );

    /**
     * Create a path by appending the supplied names to the parent path. If no names are appended, the parent path is returned.
     * @param parentPath the path that is to provide the basis for the new path
     * @param segments the segments that are to be appended, in order, to the parent path
     * @return the new path
     * @throws IllegalArgumentException if the parent path reference is null, or if at least one segment name is provided and if
     * any of the supplied segment names are null
     */
    Path create( Path parentPath, Path.Segment... segments );

    /**
     * Create a path segment given the supplied segment name. The resulting segment will have no index.
     * @param segmentName the name of the segment
     * @return the segment
     * @throws IllegalArgumentException if the segment name reference is null
     */
    Path.Segment createSegment( String segmentName );

    /**
     * Create a path segment given the supplied segment name and index.
     * @param segmentName the name of the new segment
     * @param index the index of the new segment
     * @return the segment
     * @throws IllegalArgumentException if the segment name reference is null or if the index is invalid
     */
    Path.Segment createSegment( String segmentName, int index );

    /**
     * Create a path segment given the supplied segment name. The resulting segment will have no index.
     * @param segmentName the name of the segment
     * @return the segment
     * @throws IllegalArgumentException if the segment name reference is null
     */
    Path.Segment createSegment( Name segmentName );

    /**
     * Create a path segment given the supplied segment name and index.
     * @param segmentName the name of the new segment
     * @param index the index of the new segment
     * @return the segment
     * @throws IllegalArgumentException if the segment name reference is null or if the index is invalid
     */
    Path.Segment createSegment( Name segmentName, int index );

}
