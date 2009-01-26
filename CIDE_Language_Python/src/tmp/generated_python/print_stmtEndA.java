package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class print_stmtEndA extends GenASTNode {
  public print_stmtEndA(test test, ArrayList<test> test1, ASTTextNode text5, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<test>("test", test),
      new PropertyZeroOrMore<test>("test1", test1),
      new PropertyZeroOrOne<ASTTextNode>("text5", text5)
    }, firstToken, lastToken);
  }
  public print_stmtEndA(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new print_stmtEndA(cloneProperties(),firstToken,lastToken);
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
  public ArrayList<test> getTest1() {
    return ((PropertyZeroOrMore<test>)getProperty("test1")).getValue();
  }
  public ASTTextNode getText5() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text5")).getValue();
  }
}
