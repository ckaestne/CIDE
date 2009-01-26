package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class return_stmt extends GenASTNode {
  public return_stmt(ASTStringNode return_kw, SmartTestList smartTestList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("return_kw", return_kw),
      new PropertyZeroOrOne<SmartTestList>("smartTestList", smartTestList)
    }, firstToken, lastToken);
  }
  public return_stmt(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new return_stmt(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getReturn_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("return_kw")).getValue();
  }
  public SmartTestList getSmartTestList() {
    return ((PropertyZeroOrOne<SmartTestList>)getProperty("smartTestList")).getValue();
  }
}
