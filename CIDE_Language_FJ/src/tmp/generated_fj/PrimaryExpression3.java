package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimaryExpression3 extends PrimaryExpression {
  public PrimaryExpression3(FieldInvoke fieldInvoke, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<FieldInvoke>("fieldInvoke", fieldInvoke)
    }, firstToken, lastToken);
  }
  public PrimaryExpression3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimaryExpression3(cloneProperties(),firstToken,lastToken);
  }
  public FieldInvoke getFieldInvoke() {
    return ((PropertyOne<FieldInvoke>)getProperty("fieldInvoke")).getValue();
  }
}
