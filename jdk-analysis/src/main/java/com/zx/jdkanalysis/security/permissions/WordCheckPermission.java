package com.zx.jdkanalysis.security.permissions;

import java.security.Permission;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WordCheckPermission extends Permission {

  private String action;

  /**
   * Constructs a permission with the specified name.
   *
   * @param target name of the Permission object being created.
   */
  public WordCheckPermission(String target, String anAction) {
    super(target);
    action = anAction;
  }

  @Override
  public boolean implies(Permission other) {
    if (!(other instanceof WordCheckPermission)) {
      return false;
    }
    WordCheckPermission b = (WordCheckPermission) other;
    if (action.equals("insert")) {
      if (b.action.equals("avoid")) {
        return b.badWordSet().containsAll(badWordSet());
      } else if (b.action.equals("insert")) {
        for (String badWord : badWordSet()) {
          if (b.getName().contains(badWord)) {
            return false;
          }
        }
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (!getClass().equals(other.getClass())) {
      return false;
    }
    WordCheckPermission b = (WordCheckPermission) other;
    if (!Objects.equals(action, b.action)) {
      return false;
    }
    if ("insert".equals(action)) {
      return Objects.equals(getName(), b.getName());
    } else if ("avoid".equals(action)) {
      return badWordSet().equals((b.badWordSet()));
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), action);
  }

  @Override
  public String getActions() {
    return action;
  }

  public Set<String> badWordSet() {
    return new HashSet<>(Arrays.asList(getName().split(",")));
  }
}
