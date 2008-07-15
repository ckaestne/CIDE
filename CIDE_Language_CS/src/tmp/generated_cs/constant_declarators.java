package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class constant_declarators extends GenASTNode {
  public constant_declarators(constant_declarator constant_declarator, ArrayList<constant_declarator> constant_declarator1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<constant_declarator>("constant_declarator", constant_declarator),
      new PropertyZeroOrMore<constant_declarator>("constant_declarator1", constant_declarator1)
    }, firstToken, lastToken);
  }
  public constant_declarators(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new constant_declarators(cloneProperties(),firstToken,lastToken);
  }
  public constant_declarator getConstant_declarator() {
    return ((PropertyOne<constant_declarator>)getProperty("constant_declarator")).getValue();
  }
  public ArrayList<constant_declarator> getConstant_declarator1() {
    return ((PropertyZeroOrMore<constant_declarator>)getProperty("constant_declarator1")).getValue();
  }
}
