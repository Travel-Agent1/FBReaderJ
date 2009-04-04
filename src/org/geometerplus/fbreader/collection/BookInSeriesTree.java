/*
 * Copyright (C) 2009 Geometer Plus <contact@geometerplus.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.geometerplus.fbreader.collection;

import org.geometerplus.fbreader.description.BookDescription;

public final class BookInSeriesTree extends BookTree {
	BookInSeriesTree(CollectionTree parent, BookDescription description) {
		super(parent, description);
	}

	@Override
	public int compareTo(CollectionTree tree) {
		if (tree instanceof BookInSeriesTree) {
			final int difference = Description.getNumberInSeries() - ((BookTree)tree).Description.getNumberInSeries();
			if (difference != 0) {
				return difference;
			}
		}
		return super.compareTo(tree);
	}
}