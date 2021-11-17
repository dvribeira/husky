/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://gitlab.com/ehealth-connector/api/wikis/Team/
 * For exact developer information, please refer to the commit history of the forge.
 *
 * This code is made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * This line is intended for UTF-8 encoding checks, do not modify/delete: äöüéè
 *
 */

package org.husky.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File handling utilities
 *
 */
public class FileUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class.getName());
	
	private FileUtil() {
		throw new IllegalStateException("This is a utility class!");
	}

	/**
	 * Returns a combined Path of path1 and path2.
	 *
	 * @param path1
	 *            the first part (head) of the combined path.
	 * @param path2
	 *            the second part (tail) of the combined path.
	 * @return the combined Path of path1 and path2.
	 */
	@Deprecated(forRemoval = true) // Use File or Path
	public static String combinePath(String path1, String path2) {
		if (!(path1.endsWith("/") || path1.endsWith("\\")))
			return path1 + "/" + path2;
		else
			return path1 + path2;
	}

	/**
	 * Returns a random File from within the given path
	 *
	 * @param path
	 *            The path where a random file will be selected
	 * @return A random File from within the given path
	 */
	public static File getRandomFileFromPath(String path) {
		File retVal = null;
		var dir = new File(path);
		File[] files = dir.listFiles();

		if (Util.getRand() == null) {
			try {
				Util.setRand(SecureRandom.getInstanceStrong());
			} catch (NoSuchAlgorithmException e) {
				LOGGER.error(e.getMessage(), e);
				Util.setRand(new Random());
			}
		}

		retVal = files[Util.getRand().nextInt(files.length)];
		return retVal;
	}

	/**
	 * Saves the given String to the given File (plain text). The file will be
	 * overwritten by this method.
	 *
	 * @param outputString
	 *            This string will be saved into the given file
	 * @param outputFileName
	 *            The given string will be saved to this file
	 */
	public static void saveString2File(String outputString, String outputFileName) {
		var outputFile = new File(outputFileName);
		try {
			Files.deleteIfExists(outputFile.toPath());
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		try (Writer out = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8))) {
			out.write(outputString);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
