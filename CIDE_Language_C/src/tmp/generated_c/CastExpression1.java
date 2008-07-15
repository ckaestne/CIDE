package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CastExpression1 extends CastExpression {
  public CastExpression1(TypeName typeName, CastExpression castExpression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<TypeName>("typeName", typeName),
      new PropertyOne<CastExpression>("castExpression", castExpression)
    }, firstToken, lastToken);
  }
  public CastExpression1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CastExpression1(cloneProperties(),firstToken,lastToken);
  }
  public TypeName getTypeName() {
    return ((PropertyOne<TypeName>)getProperty("typeName")).getValue();
  }
  public CastExpression getCastExpression() {
    return ((PropertyOne<CastExpression>)getProperty("castExpression")).getValue();
  }
}
