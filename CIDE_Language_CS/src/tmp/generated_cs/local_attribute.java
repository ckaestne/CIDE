package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class local_attribute extends GenASTNode {
  public local_attribute(attribute_target_specifier attribute_target_specifier, attribute_section attribute_section, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<attribute_target_specifier>("attribute_target_specifier", attribute_target_specifier),
      new PropertyOne<attribute_section>("attribute_section", attribute_section)
    }, firstToken, lastToken);
  }
  public local_attribute(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new local_attribute(cloneProperties(),firstToken,lastToken);
  }
  public attribute_target_specifier getAttribute_target_specifier() {
    return ((PropertyZeroOrOne<attribute_target_specifier>)getProperty("attribute_target_specifier")).getValue();
  }
  public attribute_section getAttribute_section() {
    return ((PropertyOne<attribute_section>)getProperty("attribute_section")).getValue();
  }
}
