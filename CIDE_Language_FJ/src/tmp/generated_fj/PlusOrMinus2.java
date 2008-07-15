package tmp.generated_fj;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PlusOrMinus2 extends PlusOrMinus {
  public PlusOrMinus2(MinusExpressionRest minusExpressionRest, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MinusExpressionRest>("minusExpressionRest", minusExpressionRest)
    }, firstToken, lastToken);
  }
  public PlusOrMinus2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PlusOrMinus2(cloneProperties(),firstToken,lastToken);
  }
  public MinusExpressionRest getMinusExpressionRest() {
    return ((PropertyOne<MinusExpressionRest>)getProperty("minusExpressionRest")).getValue();
  }
}
