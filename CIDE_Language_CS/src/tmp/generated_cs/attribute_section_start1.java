package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class attribute_section_start1 extends attribute_section_start {
  public attribute_section_start1(global_attribute_target_specifier global_attribute_target_specifier, attribute_section attribute_section, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<global_attribute_target_specifier>("global_attribute_target_specifier", global_attribute_target_specifier),
      new PropertyOne<attribute_section>("attribute_section", attribute_section)
    }, firstToken, lastToken);
  }
  public attribute_section_start1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new attribute_section_start1(cloneProperties(),firstToken,lastToken);
  }
  public global_attribute_target_specifier getGlobal_attribute_target_specifier() {
    return ((PropertyOne<global_attribute_target_specifier>)getProperty("global_attribute_target_specifier")).getValue();
  }
  public attribute_section getAttribute_section() {
    return ((PropertyOne<attribute_section>)getProperty("attribute_section")).getValue();
  }
}
