package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class attribute_section extends GenASTNode {
  public attribute_section(attribute attribute, attribute_sectionEnd attribute_sectionEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<attribute>("attribute", attribute),
      new PropertyOne<attribute_sectionEnd>("attribute_sectionEnd", attribute_sectionEnd)
    }, firstToken, lastToken);
  }
  public attribute_section(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new attribute_section(cloneProperties(),firstToken,lastToken);
  }
  public attribute getAttribute() {
    return ((PropertyOne<attribute>)getProperty("attribute")).getValue();
  }
  public attribute_sectionEnd getAttribute_sectionEnd() {
    return ((PropertyOne<attribute_sectionEnd>)getProperty("attribute_sectionEnd")).getValue();
  }
}
