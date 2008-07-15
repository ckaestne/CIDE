package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class attribute_target_specifier extends GenASTNode {
  public attribute_target_specifier(attribute_target attribute_target, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<attribute_target>("attribute_target", attribute_target)
    }, firstToken, lastToken);
  }
  public attribute_target_specifier(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new attribute_target_specifier(cloneProperties(),firstToken,lastToken);
  }
  public attribute_target getAttribute_target() {
    return ((PropertyOne<attribute_target>)getProperty("attribute_target")).getValue();
  }
}
