package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class if_stmt extends GenASTNode {
  public if_stmt(test test, suite suite, ArrayList<elif_stmt> elif_stmt, suite suite1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<test>("test", test),
      new PropertyOne<suite>("suite", suite),
      new PropertyZeroOrMore<elif_stmt>("elif_stmt", elif_stmt),
      new PropertyZeroOrOne<suite>("suite1", suite1)
    }, firstToken, lastToken);
  }
  public if_stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new if_stmt(cloneProperties(),firstToken,lastToken);
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
  public suite getSuite() {
    return ((PropertyOne<suite>)getProperty("suite")).getValue();
  }
  public ArrayList<elif_stmt> getElif_stmt() {
    return ((PropertyZeroOrMore<elif_stmt>)getProperty("elif_stmt")).getValue();
  }
  public suite getSuite1() {
    return ((PropertyZeroOrOne<suite>)getProperty("suite1")).getValue();
  }
}
