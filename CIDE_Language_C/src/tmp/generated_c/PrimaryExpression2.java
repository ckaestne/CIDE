package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimaryExpression2 extends PrimaryExpression {
  public PrimaryExpression2(Constant constant, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Constant>("constant", constant)
    }, firstToken, lastToken);
  }
  public PrimaryExpression2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimaryExpression2(cloneProperties(),firstToken,lastToken);
  }
  public Constant getConstant() {
    return ((PropertyOne<Constant>)getProperty("constant")).getValue();
  }
}
