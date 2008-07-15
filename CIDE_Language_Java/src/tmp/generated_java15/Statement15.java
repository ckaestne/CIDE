package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement15 extends Statement {
  public Statement15(IASTNode synchronizedStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyWrapper<IASTNode,Statement>("synchronizedStatement", synchronizedStatement, "block")
    }, firstToken, lastToken);
  }
  public Statement15(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement15(cloneProperties(),firstToken,lastToken);
  }
  public IASTNode getSynchronizedStatement() {
    return ((PropertyWrapper<IASTNode,Statement>)getProperty("synchronizedStatement")).getValue();
  }
}
