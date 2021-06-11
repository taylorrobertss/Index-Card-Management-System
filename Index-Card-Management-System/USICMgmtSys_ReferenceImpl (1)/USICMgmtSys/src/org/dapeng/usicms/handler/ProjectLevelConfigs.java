/*******************************************************************************
 * Copyright (C) 2021 dapeng
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
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 ******************************************************************************/
package org.dapeng.usicms.handler;

import java.util.ArrayList;
import java.util.List;

import org.dapeng.usicms.model.UserStory;

public class ProjectLevelConfigs {
	public static String projectName = "";
	public static List<UserStory> userStories = new ArrayList<>();

	public static String FIELD_SPLITTER = "&*&";
	public static String RECORD_SPLITTER = "/n&-----------&/n";

	public static void UpdateUserStoryStatus(String idName, String newStatus) {
		String id = idName.split("--")[0];
		for (UserStory us : userStories) {
			if (us.getId().equalsIgnoreCase(id)) {
				us.setStatus(newStatus);
			}
		}
	}

}
