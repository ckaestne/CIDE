package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MultiplicativeOperator2 extends MultiplicativeOperator {
  public MultiplicativeOperator2(ASTStringNode slash, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("slash", slash)
    }, firstToken, lastToken);
  }
  public MultiplicativeOperator2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MultiplicativeOperator2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getSlash() {
    return ((PropertyOne<ASTStringNode>)getProperty("slash")).getValue();
  }
}
