package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class print_stmtEndP extends GenASTNode {
  public print_stmtEndP(ArrayList<test> test, ASTTextNode text581, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<test>("test", test),
      new PropertyZeroOrOne<ASTTextNode>("text581", text581)
    }, firstToken, lastToken);
  }
  public print_stmtEndP(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new print_stmtEndP(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<test> getTest() {
    return ((PropertyOneOrMore<test>)getProperty("test")).getValue();
  }
  public ASTTextNode getText581() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text581")).getValue();
  }
}
