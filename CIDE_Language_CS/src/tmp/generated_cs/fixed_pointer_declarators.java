package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class fixed_pointer_declarators extends GenASTNode {
  public fixed_pointer_declarators(fixed_pointer_declarator fixed_pointer_declarator, ArrayList<fixed_pointer_declarator> fixed_pointer_declarator1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<fixed_pointer_declarator>("fixed_pointer_declarator", fixed_pointer_declarator),
      new PropertyZeroOrMore<fixed_pointer_declarator>("fixed_pointer_declarator1", fixed_pointer_declarator1)
    }, firstToken, lastToken);
  }
  public fixed_pointer_declarators(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new fixed_pointer_declarators(cloneProperties(),firstToken,lastToken);
  }
  public fixed_pointer_declarator getFixed_pointer_declarator() {
    return ((PropertyOne<fixed_pointer_declarator>)getProperty("fixed_pointer_declarator")).getValue();
  }
  public ArrayList<fixed_pointer_declarator> getFixed_pointer_declarator1() {
    return ((PropertyZeroOrMore<fixed_pointer_declarator>)getProperty("fixed_pointer_declarator1")).getValue();
  }
}
