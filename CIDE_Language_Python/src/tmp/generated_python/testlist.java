package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class testlist extends GenASTNode {
  public testlist(test test, ArrayList<test> test1, ASTTextNode text589, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<test>("test", test),
      new PropertyZeroOrMore<test>("test1", test1),
      new PropertyZeroOrOne<ASTTextNode>("text589", text589)
    }, firstToken, lastToken);
  }
  public testlist(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new testlist(cloneProperties(),firstToken,lastToken);
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
  public ArrayList<test> getTest1() {
    return ((PropertyZeroOrMore<test>)getProperty("test1")).getValue();
  }
  public ASTTextNode getText589() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text589")).getValue();
  }
}
