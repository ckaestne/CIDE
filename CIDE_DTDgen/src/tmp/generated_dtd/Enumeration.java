package tmp.generated_dtd;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Enumeration extends GenASTNode {
  public Enumeration(ASTStringNode name, ArrayList<ASTStringNode> name1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("name", name),
      new PropertyZeroOrMore<ASTStringNode>("name1", name1)
    }, firstToken, lastToken);
  }
  public Enumeration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new Enumeration(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getName() {
    return ((PropertyOne<ASTStringNode>)getProperty("name")).getValue();
  }
  public ArrayList<ASTStringNode> getName1() {
    return ((PropertyZeroOrMore<ASTStringNode>)getProperty("name1")).getValue();
  }
}
