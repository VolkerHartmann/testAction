/*
 * Copyright 2020 hartmann-v.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.kit.datamanager.indexer.util;

import com.google.common.io.Files;
import edu.kit.datamanager.indexer.exception.IndexerException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hartmann-v
 */
public class IndexerUtilTest {

  public IndexerUtilTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of downloadResource method, of class GemmaMapping.
   */
  @Test
  public void testDownloadResource() throws URISyntaxException {
    System.out.println("downloadResource");
    assertNotNull(new IndexerUtil());
    URI resourceURL = new URI("https://www.example.org");
    Optional<Path> result = IndexerUtil.downloadResource(resourceURL);
    assertTrue("No file available!", result.isPresent());
    assertTrue("File '" + result.get().toString() + "' doesn't exist!", result.get().toFile().exists());
    assertTrue("Wrong suffix for file '" + result.get().toString() + "'!", result.get().toString().endsWith(IndexerUtil.DEFAULT_SUFFIX));
    assertTrue("Can't delete file '" + result.get().toString() + "'!", result.get().toFile().delete());
  }

  /**
   * Test of downloadResource method, of class GemmaMapping.
   */
  @Test
  public void testDownloadResourceWithPath() throws URISyntaxException {
    System.out.println("downloadResource");
    assertNotNull(new IndexerUtil());
    URI resourceURL = new URI("https://www.example.org/index.html");
    Optional<Path> result = IndexerUtil.downloadResource(resourceURL);
    assertTrue("No file available!", result.isPresent());
    assertTrue("File '" + result.get().toString() + "' doesn't exist!", result.get().toFile().exists());
    assertTrue("Wrong suffix for file '" + result.get().toString() + "'!", result.get().toString().endsWith(".html"));
    assertTrue("Can't delete file '" + result.get().toString() + "'!", result.get().toFile().delete());
  }

  /**
   * Test of downloadResource method, of class GemmaMapping.
   */
  @Test
  public void testDownloadInvalidResource() throws URISyntaxException {
    System.out.println("testDownloadInvalidResource");

    try {
      URI resourceURL = new URI("https://invalidhttpaddress.de");
      Optional<Path> result = IndexerUtil.downloadResource(resourceURL);
      assertTrue(false);
    } catch (IndexerException ie) {
      assertTrue(true);
      assertTrue(ie.getMessage().contains("Error downloading resource"));
    }
  }

  /**
   * Test of downloadResource method, of class GemmaMapping.
   */
  @Test
  public void testDownloadLocalResource() throws URISyntaxException, IOException {
    System.out.println("testDownloadLocalResource");
    File srcFile = new File("src/test/resources/examples/gemma/simple.json");
    assertTrue("File doesn't exist: " + srcFile.toString(), srcFile.exists());
    URI resourceURL = srcFile.toURI();
    Optional<Path> result = IndexerUtil.downloadResource(resourceURL);
    assertTrue("No file available!", result.isPresent());
    assertTrue("File '" + result.get().toString() + "' doesn't exist!", result.get().toFile().exists());
    assertTrue("Wrong suffix for file '" + result.get().toString() + "'!", result.get().toString().endsWith(".json"));
    assertTrue("Can't delete file '" + result.get().toString() + "'!", result.get().toFile().delete());
  }

  /**
   * Test of downloadResource method, of class GemmaMapping.
   */
  @Test
  public void testDownloadLocalJsonFileWithoutSuffix() throws URISyntaxException, IOException {
    System.out.println("testDownloadLocalResource");
    File srcFile = new File("src/test/resources/examples/gemma/simple.json");
    assertTrue("File doesn't exist: " + srcFile.toString(), srcFile.exists());
    Path createTempFile = IndexerUtil.createTempFile(null, "nosuffix");
    Files.copy(srcFile, createTempFile.toFile());
    Optional<Path> result = IndexerUtil.downloadResource(createTempFile.toUri());
    assertTrue("No file available!", result.isPresent());
    assertTrue("File '" + result.get().toString() + "' doesn't exist!", result.get().toFile().exists());
    assertTrue("Wrong suffix for file '" + result.get().toString() + "'!", result.get().toString().endsWith(".json"));
    assertTrue("Can't delete file '" + result.get().toString() + "'!", result.get().toFile().delete());
    assertTrue("Can't delete file '" + createTempFile.toString() + "'!", createTempFile.toFile().delete());
  }

  /**
   * Test of downloadResource method, of class GemmaMapping.
   */
  @Test
  public void testDownloadLocalXMLFileWithoutSuffix() throws URISyntaxException, IOException {
    System.out.println("testDownloadLocalResource");
    File srcFile = new File("src/test/resources/examples/gemma/simple.xml");
    assertTrue("File doesn't exist: " + srcFile.toString(), srcFile.exists());
    Path createTempFile = IndexerUtil.createTempFile(null, "nosuffix");
    Files.copy(srcFile, createTempFile.toFile());
    Optional<Path> result = IndexerUtil.downloadResource(createTempFile.toUri());
    assertTrue("No file available!", result.isPresent());
    assertTrue("File '" + result.get().toString() + "' doesn't exist!", result.get().toFile().exists());
    assertTrue("Wrong suffix for file '" + result.get().toString() + "'!", result.get().toString().endsWith(".xml"));
    assertTrue("Can't delete file '" + result.get().toString() + "'!", result.get().toFile().delete());
    assertTrue("Can't delete file '" + createTempFile.toString() + "'!", createTempFile.toFile().delete());
  }

  /**
   * Test of downloadResource method, of class GemmaMapping.
   */
  @Test
  public void testDownloadLocalResourceWithoutSuffix() throws URISyntaxException, IOException {
    System.out.println("testDownloadLocalResource");
    File srcFile = new File("src/test/resources/examples/anyContentWithoutSuffix");
    assertTrue("File doesn't exist: " + srcFile.toString(), srcFile.exists());
    Optional<Path> result = IndexerUtil.downloadResource(srcFile.getAbsoluteFile().toURI());
    assertTrue("No file available!", result.isPresent());
    assertTrue("File '" + result.get().toString() + "' doesn't exist!", result.get().toFile().exists());
    assertTrue("Wrong suffix for file '" + result.get().toString() + "'!", result.get().toString().endsWith(IndexerUtil.DEFAULT_SUFFIX));
    assertTrue("Can't delete file '" + result.get().toString() + "'!", result.get().toFile().delete());
  }

  /**
   * Test of downloadResource method, of class GemmaMapping.
   */
  @Test
  public void testDownloadInvalidLocalResource() throws URISyntaxException, IOException {
    System.out.println("testDownloadInvalidLocalResource");
    try {
      URI resourceURL = new File("/invalid/path/to/local/file").toURI();
      Optional<Path> result = IndexerUtil.downloadResource(resourceURL);
      assertTrue(false);
    } catch (IndexerException ie) {
      assertTrue(true);
      assertTrue(ie.getMessage().contains("Error downloading resource"));
    }
  }

  /**
   * Test of downloadResource method, of class GemmaMapping.
   */
  @Test
  public void testDownloadResourceNoParameter() throws URISyntaxException {
    System.out.println("downloadResource");
    Optional<Path> result = IndexerUtil.downloadResource(null);
    assertFalse(result.isPresent());
  }

  /**
   * Test of createTempFile method, of class IndexerUtil.
   */
  @Test
  public void testCreateTempFile() {
    System.out.println("createTempFile");
    String[] prefix = {null, null, null, "", "", "", "prefix", "prefix", "prefix"};
    String[] suffix = {null, "", "suffix", null, "", "suffix", null, "", "suffix"};
    HashSet<String> allPaths = new HashSet<>();
    String path = null;
    for (int index = 0; index < prefix.length; index++) {
      Path tmpPath = IndexerUtil.createTempFile(prefix[index], suffix[index]);
      String tmpFile = tmpPath.getFileName().toString();
      path = tmpPath.getParent().toString();
      assertFalse(allPaths.contains(tmpFile));
      allPaths.add(tmpFile);
      if ((prefix[index] != null) && (!prefix[index].trim().isEmpty())) {
        assertTrue(tmpFile.startsWith(prefix[index]));
      } else {
        assertTrue(tmpFile.startsWith(IndexerUtil.DEFAULT_PREFIX));
      }
      if ((suffix[index] != null) && (!suffix[index].trim().isEmpty())) {
        assertTrue(tmpFile.endsWith(suffix[index]));
      } else {
        assertTrue(tmpFile.endsWith(IndexerUtil.DEFAULT_SUFFIX));
      }
    }
    for (String filename : allPaths) {
      IndexerUtil.removeFile(Paths.get(path, filename));
    }
  }

  /**
   * Test of removeFile method, of class IndexerUtil.
   */
  @Test
  public void testRemoveFile() {
    System.out.println("removeFile");
    Path createTempFile = IndexerUtil.createTempFile("testRemoveDir", ".txt");
    try {
      IndexerUtil.removeFile(createTempFile.getParent());
      assertTrue(false);
    } catch (IndexerException ie) {
      assertTrue(ie.getMessage().contains("Error removing file"));
    }
    assertTrue(createTempFile.toFile().exists());
    IndexerUtil.removeFile(createTempFile);
    assertFalse(createTempFile.toFile().exists());
  }

  /**
   * Test of fixFileExtension method, of class IndexerUtil.
   */
  @Test
  public void testFixFileExtensionXml() throws IOException {
    System.out.println("testFixFileExtensionXml");
    File srcFile = new File("src/test/resources/examples/gemma/simple.xml");
    assertTrue("File doesn't exist: " + srcFile.toString(), srcFile.exists());
    String[] extensions = {"nosuffix", "xml", ".xml ", ".xsd", ".json"};
    for (String extension : extensions) {
      Path createTempFile = IndexerUtil.createTempFile(null, extension);
      Files.copy(srcFile, createTempFile.toFile());
      Path result = IndexerUtil.fixFileExtension(createTempFile);
      assertTrue(result.toString().endsWith(".xml"));
      assertTrue("Can't delete file '" + result.toString() + "'!", result.toFile().delete());
    }
  }

  @Test
  public void testFixFileExtensionJson() throws IOException {
    System.out.println("testFixFileExtensionJson");
    File srcFile = new File("src/test/resources/examples/gemma/simple.json");
    assertTrue("File doesn't exist: " + srcFile.toString(), srcFile.exists());
    String[] extensions = {"nosuffix", "json", ".json ", ".xml"};
    for (String extension : extensions) {
      Path createTempFile = IndexerUtil.createTempFile(null, extension);
      Files.copy(srcFile, createTempFile.toFile());
      Path result = IndexerUtil.fixFileExtension(createTempFile);
      assertTrue(result.toString().endsWith(".json"));
      assertTrue("Can't delete file '" + result.toString() + "'!", result.toFile().delete());

    }
  }

  @Test
  public void testFixFileExtensionUnknown() throws IOException {
    System.out.println("testFixFileExtensionUnknown");
    File srcFile = new File("src/test/resources/examples/anyContentWithoutSuffix");
    assertTrue("File doesn't exist: " + srcFile.toString(), srcFile.exists());
    String[] extensions = {"nosuffix", "json", ".json ", ".xml"};
    for (String extension : extensions) {
      Path createTempFile = IndexerUtil.createTempFile(null, extension);
      Files.copy(srcFile, createTempFile.toFile());
      Path result = IndexerUtil.fixFileExtension(createTempFile);
      assertTrue(result.toString().endsWith(extension));
      assertTrue("Can't delete file '" + result.toString() + "'!", result.toFile().delete());

    }
  }

  @Test
  public void testFixFileExtensionWrongFile() throws IOException {
    System.out.println("testFixFileExtensionUnknown");
    File srcFile = new File("/tmp");
    Path result = IndexerUtil.fixFileExtension(srcFile.toPath());
    assertEquals(result, srcFile.toPath());
    srcFile = new File("/invalid/path/for/file");
    result = IndexerUtil.fixFileExtension(srcFile.toPath());
    assertEquals(result, srcFile.toPath());
    srcFile = null;
    result = IndexerUtil.fixFileExtension(null);
    assertNull(result);
  }

}
