package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class constant_declaration extends GenASTNode {
  public constant_declaration(type type, constant_declarators constant_declarators, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type),
      new PropertyOne<constant_declarators>("constant_declarators", constant_declarators)
    }, firstToken, lastToken);
  }
  public constant_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new constant_declaration(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public constant_declarators getConstant_declarators() {
    return ((PropertyOne<constant_declarators>)getProperty("constant_declarators")).getValue();
  }
}
