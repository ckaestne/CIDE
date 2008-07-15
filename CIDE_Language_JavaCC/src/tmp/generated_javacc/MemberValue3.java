package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MemberValue3 extends MemberValue {
  public MemberValue3(ConditionalExpression conditionalExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ConditionalExpression>("conditionalExpression", conditionalExpression)
    }, firstToken, lastToken);
  }
  public MemberValue3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MemberValue3(cloneProperties(),firstToken,lastToken);
  }
  public ConditionalExpression getConditionalExpression() {
    return ((PropertyOne<ConditionalExpression>)getProperty("conditionalExpression")).getValue();
  }
}
