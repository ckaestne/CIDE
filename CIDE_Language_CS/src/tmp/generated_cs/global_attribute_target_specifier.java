package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class global_attribute_target_specifier extends GenASTNode {
  public global_attribute_target_specifier(global_attribute_target global_attribute_target, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<global_attribute_target>("global_attribute_target", global_attribute_target)
    }, firstToken, lastToken);
  }
  public global_attribute_target_specifier(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new global_attribute_target_specifier(cloneProperties(),firstToken,lastToken);
  }
  public global_attribute_target getGlobal_attribute_target() {
    return ((PropertyOne<global_attribute_target>)getProperty("global_attribute_target")).getValue();
  }
}
