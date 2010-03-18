package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ExplicitConstructorInvocation2 extends ExplicitConstructorInvocation {
  public ExplicitConstructorInvocation2(PrimaryExpression primaryExpression, Arguments arguments1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<PrimaryExpression>("primaryExpression", primaryExpression),
      new PropertyOne<Arguments>("arguments1", arguments1)
    }, firstToken, lastToken);
  }
  public ExplicitConstructorInvocation2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ExplicitConstructorInvocation2(cloneProperties(),firstToken,lastToken);
  }
  public PrimaryExpression getPrimaryExpression() {
    return ((PropertyZeroOrOne<PrimaryExpression>)getProperty("primaryExpression")).getValue();
  }
  public Arguments getArguments1() {
    return ((PropertyOne<Arguments>)getProperty("arguments1")).getValue();
  }
}
