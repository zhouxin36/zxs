package com.zx.microservice.microservice.databinding.editor;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author zhouxin
 * @since 2019/3/4
 */
public class MultiDateParseEditor extends PropertyEditorSupport {

  private final int exactDateLength;
  private List<DateTimeFormatter> dateFormats;
  private boolean allowEmpty;

  public MultiDateParseEditor(List<DateTimeFormatter> dateFormats, boolean allowEmpty) {
    if (dateFormats == null || dateFormats.size() == 0) {
      throw new IllegalArgumentException("Param dateFormats could not be empty");
    }
    this.dateFormats = dateFormats;
    this.allowEmpty = allowEmpty;
    this.exactDateLength = -1;
  }

  public MultiDateParseEditor(List<DateTimeFormatter> dateFormats, boolean allowEmpty, int exactDateLength) {
    if (dateFormats == null || dateFormats.size() == 0) {
      throw new IllegalArgumentException("Param dateFormats could not be empty");
    }
    this.dateFormats = dateFormats;
    this.allowEmpty = allowEmpty;
    this.exactDateLength = exactDateLength;
  }

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    assert text != null;
    if (this.allowEmpty && !StringUtils.hasText(text)) {
      // Treat empty String as null value.
      setValue(null);
    } else if (this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
      throw new IllegalArgumentException(
          "Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
    } else {
      Exception e1 = null;
      for (DateTimeFormatter dateFormat : dateFormats) {
        try {
          setValue(LocalDateTime.parse(text, dateFormat));
          return;
        } catch (Exception e) {
          e1 = e;
        }
      }
      assert e1 != null;
      throw new IllegalArgumentException("Could not parse date: " + e1.getMessage(), e1);
    }
  }
}