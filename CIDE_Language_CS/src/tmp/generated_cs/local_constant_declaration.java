package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class local_constant_declaration extends GenASTNode {
  public local_constant_declaration(type type, local_constant_declarators local_constant_declarators, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type),
      new PropertyOne<local_constant_declarators>("local_constant_declarators", local_constant_declarators)
    }, firstToken, lastToken);
  }
  public local_constant_declaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new local_constant_declaration(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public local_constant_declarators getLocal_constant_declarators() {
    return ((PropertyOne<local_constant_declarators>)getProperty("local_constant_declarators")).getValue();
  }
}
