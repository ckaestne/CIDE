package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class listmakerEnd2 extends listmakerEnd {
  public listmakerEnd2(ArrayList<test> test, ASTTextNode text591, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<test>("test", test),
      new PropertyZeroOrOne<ASTTextNode>("text591", text591)
    }, firstToken, lastToken);
  }
  public listmakerEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new listmakerEnd2(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<test> getTest() {
    return ((PropertyZeroOrMore<test>)getProperty("test")).getValue();
  }
  public ASTTextNode getText591() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text591")).getValue();
  }
}
