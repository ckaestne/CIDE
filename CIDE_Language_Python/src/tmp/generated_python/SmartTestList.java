package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SmartTestList extends GenASTNode {
  public SmartTestList(test test, ArrayList<test> test1, ASTTextNode text11, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<test>("test", test),
      new PropertyZeroOrMore<test>("test1", test1),
      new PropertyZeroOrOne<ASTTextNode>("text11", text11)
    }, firstToken, lastToken);
  }
  public SmartTestList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SmartTestList(cloneProperties(),firstToken,lastToken);
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
  public ArrayList<test> getTest1() {
    return ((PropertyZeroOrMore<test>)getProperty("test1")).getValue();
  }
  public ASTTextNode getText11() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text11")).getValue();
  }
}
