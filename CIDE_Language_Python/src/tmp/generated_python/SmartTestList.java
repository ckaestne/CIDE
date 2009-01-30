package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SmartTestList extends GenASTNode {
  public SmartTestList(test test, ArrayList<test> test1, ASTTextNode text588, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<test>("test", test),
      new PropertyZeroOrMore<test>("test1", test1),
      new PropertyZeroOrOne<ASTTextNode>("text588", text588)
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
  public ASTTextNode getText588() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text588")).getValue();
  }
}
