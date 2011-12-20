/*
 * Copyright (C) 2003-2011 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.wikbook.template.test.freemarker;

import org.wikbook.template.freemarker.caller.AttributeCallerMethod;
import org.wikbook.template.freemarker.caller.JavadocCallerMethod;
import org.wikbook.template.test.AbstractFreemarkerTestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a>
 * @version $Revision$
 */
public class FreemarkerTypeTestCase extends AbstractFreemarkerTestCase {


  @Override
  public void setUp() throws Exception {

    super.setUp();

  }

  public void testExist() throws Exception {

    Map<String, Object> data = buildModel("B");
    assertEquals(2, data.size());
    Iterator i = data.keySet().iterator();
    assertEquals("@AnnotationB", i.next());
    assertEquals("@AnnotationA", i.next());

  }

  public void testAnnotationSimpleValues() throws Exception {

    Map<String, Object> data = buildModel("B");
    AttributeCallerMethod attributeCaller = (AttributeCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("attribute");
    assertEquals("b", attributeCaller.exec(Arrays.asList("value")).toString());

  }

  public void testAnnotationMultipleValues() throws Exception {

    Map<String, Object> data = buildModel("B");
    AttributeCallerMethod attributeCaller = (AttributeCallerMethod) ((Map<String, Object>) data.get("@AnnotationB")).get("attribute");
    assertEquals("[a, b]", attributeCaller.exec(Arrays.asList("value")).toString());

  }

  public void testAnnotationMultipleListValues() throws Exception {

    Map<String, Object> data = buildModel("B");
    AttributeCallerMethod attributeCaller = (AttributeCallerMethod) ((Map<String, Object>) data.get("@AnnotationB")).get("attribute");
    assertEquals("[a, b]", attributeCaller.exec(Arrays.asList("list:value")).toString());

  }

  public void testAnnotationMultipleFlatValues() throws Exception {

    Map<String, Object> data = buildModel("B");
    AttributeCallerMethod attributeCaller = (AttributeCallerMethod) ((Map<String, Object>) data.get("@AnnotationB")).get("attribute");
    assertEquals("a, b", attributeCaller.exec(Arrays.asList("flat:value")).toString());

  }

  public void testTypeName() throws Exception {

    Map<String, Object> data = buildModel("B");
    assertEquals("B", ((Map<String, Map<String, String>>) data.get("@AnnotationB")).get("type").get("name"));
    assertEquals("model.B", ((Map<String, Map<String, String>>) data.get("@AnnotationB")).get("type").get("fullName"));
    assertEquals("false", ((Map<String, Map<String, String>>) data.get("@AnnotationB")).get("type").get("isArray"));

  }

  public void testElementName() throws Exception {

    Map<String, Object> data = buildModel("B");
    assertEquals("B", ((Map<String, String>) data.get("@AnnotationA")).get("elementName"));

  }

  public void testAnnotationName() throws Exception {

    Map<String, Object> data = buildModel("B");
    assertEquals("AnnotationA", ((Map<String, String>) data.get("@AnnotationA")).get("name"));

  }

  public void testJavadocGeneralComment() throws Exception {

    Map<String, Object> data = buildModel("C");
    JavadocCallerMethod docCaller = (JavadocCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("doc");
    assertEquals("General comment.", docCaller.exec(new ArrayList()).toString());

  }

  public void testJavadocSingleValue() throws Exception {

    Map<String, Object> data = buildModel("C");
    JavadocCallerMethod docCaller = (JavadocCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("doc");
    assertEquals("[1.0]", docCaller.exec(Arrays.asList("since")).toString());

  }

  public void testJavadocMultipleValue() throws Exception {

    Map<String, Object> data = buildModel("C");
    JavadocCallerMethod docCaller = (JavadocCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("doc");
    assertEquals("[foo, bar]", docCaller.exec(Arrays.asList("author")).toString());

  }

  public void testJavadocNoValue() throws Exception {

    Map<String, Object> data = buildModel("C");
    JavadocCallerMethod docCaller = (JavadocCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("doc");
    assertEquals("[deprecated]", docCaller.exec(Arrays.asList("deprecated")).toString());

  }

  public void testJavadocDoesntExist() throws Exception {

    Map<String, Object> data = buildModel("C");
    JavadocCallerMethod docCaller = (JavadocCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("doc");
    assertEquals("", docCaller.exec(Arrays.asList("foo")).toString());

  }

  public void testJavadocSingleListValue() throws Exception {

    Map<String, Object> data = buildModel("C");
    JavadocCallerMethod docCaller = (JavadocCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("doc");
    assertEquals("[1.0]", docCaller.exec(Arrays.asList("list:since")).toString());

  }

  public void testJavadocMultipleListValue() throws Exception {

    Map<String, Object> data = buildModel("C");
    JavadocCallerMethod docCaller = (JavadocCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("doc");
    assertEquals("[foo, bar]", docCaller.exec(Arrays.asList("list:author")).toString());

  }

  public void testJavadocListNoValue() throws Exception {

    Map<String, Object> data = buildModel("C");
    JavadocCallerMethod docCaller = (JavadocCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("doc");
    assertEquals("[deprecated]", docCaller.exec(Arrays.asList("list:deprecated")).toString());

  }

  public void testJavadocListDoesntExist() throws Exception {

    Map<String, Object> data = buildModel("C");
    JavadocCallerMethod docCaller = (JavadocCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("doc");
    assertEquals("[]", docCaller.exec(Arrays.asList("list:foo")).toString());

  }

  public void testJavadocSingleFlatValue() throws Exception {

    Map<String, Object> data = buildModel("C");
    JavadocCallerMethod docCaller = (JavadocCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("doc");
    assertEquals("1.0", docCaller.exec(Arrays.asList("flat:since")).toString());

  }

  public void testJavadocMultipleFlatValue() throws Exception {

    Map<String, Object> data = buildModel("C");
    JavadocCallerMethod docCaller = (JavadocCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("doc");
    assertEquals("foo, bar", docCaller.exec(Arrays.asList("flat:author")).toString());

  }

  public void testJavadocFlatNoValue() throws Exception {

    Map<String, Object> data = buildModel("C");
    JavadocCallerMethod docCaller = (JavadocCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("doc");
    assertEquals("deprecated", docCaller.exec(Arrays.asList("flat:deprecated")).toString());

  }

  public void testJavadocFlatDoesntExist() throws Exception {

    Map<String, Object> data = buildModel("C");
    JavadocCallerMethod docCaller = (JavadocCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("doc");
    assertEquals("", docCaller.exec(Arrays.asList("flat:foo")).toString());

  }

  public void testJavadocBloc() throws Exception {

    Map<String, Object> data = buildModel("C");
    JavadocCallerMethod docCaller = (JavadocCallerMethod) ((Map<String, Object>) data.get("@AnnotationA")).get("doc");
    assertEquals(
        " here there is\n" +
        "   a\n" +
        " bloc",
        docCaller.exec(Arrays.asList("bloc:data")).toString());

  }
  
}
