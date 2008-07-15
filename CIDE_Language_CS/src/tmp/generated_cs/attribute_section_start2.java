package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class attribute_section_start2 extends attribute_section_start {
  public attribute_section_start2(attribute_target_specifier attribute_target_specifier, attribute_section attribute_section1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<attribute_target_specifier>("attribute_target_specifier", attribute_target_specifier),
      new PropertyOne<attribute_section>("attribute_section1", attribute_section1)
    }, firstToken, lastToken);
  }
  public attribute_section_start2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new attribute_section_start2(cloneProperties(),firstToken,lastToken);
  }
  public attribute_target_specifier getAttribute_target_specifier() {
    return ((PropertyZeroOrOne<attribute_target_specifier>)getProperty("attribute_target_specifier")).getValue();
  }
  public attribute_section getAttribute_section1() {
    return ((PropertyOne<attribute_section>)getProperty("attribute_section1")).getValue();
  }
}
