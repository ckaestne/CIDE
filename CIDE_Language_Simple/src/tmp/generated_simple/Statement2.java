package tmp.generated_simple;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement2 extends Statement {
  public Statement2(IASTNode ifStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<IASTNode,Block>("ifStatement", ifStatement, "block")
    }, firstToken, lastToken);
  }
  public Statement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement2(cloneProperties(),firstToken,lastToken);
  }
  public IASTNode getIfStatement() {
    return ((PropertyWrapper<IASTNode,Block>)getProperty("ifStatement")).getValue();
  }
}
