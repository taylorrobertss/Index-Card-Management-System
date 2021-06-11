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
package org.dapeng.usicms.model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.dapeng.usicms.handler.ProjectLevelConfigs;

public class UserStory {
	private String name;
	private String id;
	private String Description;
	private int estimate;
	private String type;
	private int duration;
	private String project;
	private Date timeStamp;
	private Date lastModified;
	private int storyPoint;
	private String creator;

	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getEstimate() {
		return estimate;
	}

	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public int getStoryPoint() {
		return storyPoint;
	}

	public void setStoryPoint(int storyPoint) {
		this.storyPoint = storyPoint;
	}

	public void addUserStory(UserStory us) {
		ProjectLevelConfigs.userStories.add(us);
		StringBuilder sb = new StringBuilder();
		sb.append(us.getName()).append(ProjectLevelConfigs.FIELD_SPLITTER).append(us.getId())
				.append(ProjectLevelConfigs.FIELD_SPLITTER).append(us.getDuration())
				.append(ProjectLevelConfigs.FIELD_SPLITTER).append(us.getEstimate())
				.append(ProjectLevelConfigs.FIELD_SPLITTER).append(us.getProject())
				.append(ProjectLevelConfigs.FIELD_SPLITTER).append(us.getStoryPoint())
				.append(ProjectLevelConfigs.FIELD_SPLITTER).append(us.getDescription())
				.append(ProjectLevelConfigs.FIELD_SPLITTER).append(us.getStatus())
				.append(ProjectLevelConfigs.FIELD_SPLITTER).append(us.getType())
				.append(ProjectLevelConfigs.RECORD_SPLITTER);
		try {
			FileWriter fw = new FileWriter(ProjectLevelConfigs.projectName, true);
			fw.write(sb.toString());
			fw.close();
		} catch (Exception ex) {

		}

		System.out.println(ProjectLevelConfigs.userStories.get(0).getId());
	}

	public void readUserStory() {
		try {
			String userStoryInString = readFile(ProjectLevelConfigs.projectName, StandardCharsets.US_ASCII);
			String[] records = userStoryInString.split(ProjectLevelConfigs.RECORD_SPLITTER);
			for (String rec : records) {
				String[] fields = rec.split(ProjectLevelConfigs.FIELD_SPLITTER);
				UserStory us = new UserStory();
				us.setName(fields[0]);
				us.setId(fields[1]);
				us.setDuration(Integer.parseInt(fields[2]));
				us.setEstimate(Integer.parseInt(fields[3]));
				us.setProject(fields[4]);
				us.setStoryPoint(Integer.parseInt(fields[5]));
				us.setDescription(fields[6]);
				us.setStatus(fields[7]);
				us.setType(fields[8]);
				ProjectLevelConfigs.userStories.add(us);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
