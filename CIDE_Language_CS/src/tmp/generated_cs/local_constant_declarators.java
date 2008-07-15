package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class local_constant_declarators extends GenASTNode {
  public local_constant_declarators(local_constant_declarator local_constant_declarator, local_constant_declarators local_constant_declarators, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<local_constant_declarator>("local_constant_declarator", local_constant_declarator),
      new PropertyZeroOrOne<local_constant_declarators>("local_constant_declarators", local_constant_declarators)
    }, firstToken, lastToken);
  }
  public local_constant_declarators(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new local_constant_declarators(cloneProperties(),firstToken,lastToken);
  }
  public local_constant_declarator getLocal_constant_declarator() {
    return ((PropertyOne<local_constant_declarator>)getProperty("local_constant_declarator")).getValue();
  }
  public local_constant_declarators getLocal_constant_declarators() {
    return ((PropertyZeroOrOne<local_constant_declarators>)getProperty("local_constant_declarators")).getValue();
  }
}
