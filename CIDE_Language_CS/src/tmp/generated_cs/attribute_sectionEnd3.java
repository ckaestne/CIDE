package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class attribute_sectionEnd3 extends attribute_sectionEnd {
  public attribute_sectionEnd3(attribute_section attribute_section, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<attribute_section>("attribute_section", attribute_section)
    }, firstToken, lastToken);
  }
  public attribute_sectionEnd3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new attribute_sectionEnd3(cloneProperties(),firstToken,lastToken);
  }
  public attribute_section getAttribute_section() {
    return ((PropertyOne<attribute_section>)getProperty("attribute_section")).getValue();
  }
}
